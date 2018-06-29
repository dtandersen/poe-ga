package poe.command;

import java.util.ArrayList;
import java.util.List;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.command.model.ImmutableCharacter;
import poe.command.model.ItemDescription;
import poe.command.model.PureImmutableCharacter.ImmutableCharacterBuilder;
import poe.entity.CharInstance.PoeCharacter;
import poe.entity.CharacterClass;
import poe.entity.CharacterItem;
import poe.entity.CharacterItem.CharacterItemBuilder;
import poe.entity.PassiveSkill;
import poe.entity.PoeComUrlBuilder;
import poe.entity.StatParser;
import poe.entity.StatValue;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;

public class CreateCharacter extends BaseCommand<CreateCharacterRequest, CreateCharacterResult>
{
	private final PassiveSkillRepository passiveSkillRepository;

	public CreateCharacter(final PassiveSkillRepository passiveSkillRepository)
	{
		this.passiveSkillRepository = passiveSkillRepository;
	}

	@Override
	public void execute()
	{
		final PoeCharacter character = new PoeCharacter(request.getCharacterClass(), request.getLevel());
		request
				.getItems()
				.stream()
				.map(item -> parseItem(item))
				.forEach(item -> character.addItem(item));

		final List<PassiveSkill> passiveTree = passiveSkillRepository.all();
		final PassiveSkillTree pst = new PassiveSkillTree(passiveTree);
		final PassiveSkill root = pst.findByName(request.getCharacterClass().getRootPassiveSkillName());
		character.addPassiveSkill(root);
		final List<PassiveSkill> passiveSkills = new ArrayList<>();
		for (final int PassiveSkillId : request.getPassiveSkillIds())
		{
			passiveSkills.add(pst.find(PassiveSkillId));
		}
		character.addPassiveSkills(passiveSkills);

		final ImmutableCharacter pure = new ImmutableCharacterBuilder()
				.withLevel(character.getLevel())
				.withPassiveSkills(character.getPassiveSkillsWithoutRoot())
				.withStats(character.getAttributes())
				.withStatValues(character.getStatValues())
				.withAdjustedStats(character.getAdjustedStats())
				.build();

		result.setCharacter(pure);
		result.setUrl(new PoeComUrlBuilder()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withPassiveSkillIds(request.getPassiveSkillIds())
				.toUrl());
	}

	private CharacterItem parseItem(final ItemDescription item)
	{
		final StatParser parser = new StatParser();

		CharacterItemBuilder item2 = CharacterItemBuilder.item();

		for (final String skillDescription : item.getSkillDescriptions())
		{
			final StatValue stat = parser.parseItem(skillDescription);
			item2 = item2.withStatValue(stat);
		}
		final CharacterItem characterItem = item2.build();

		return characterItem;
	}

	public interface CreateCharacterRequest
	{
		CharacterClass getCharacterClass();

		int getLevel();

		List<Integer> getPassiveSkillIds();

		List<ItemDescription> getItems();
	}

	public interface CreateCharacterResult
	{
		void setCharacter(ImmutableCharacter immutableCharacter);

		void setUrl(String url);
	}
}
