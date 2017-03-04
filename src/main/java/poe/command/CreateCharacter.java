package poe.command;

import java.util.ArrayList;
import java.util.List;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.command.PureImmutableCharacter.ImmutableCharacterBuilder;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill;
import poe.entity.PoeCharacter;
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
		final PoeCharacter character = new PoeCharacter(request.getCharacterClass());

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
				.withPassiveSkills(character.getPassiveSkillsWithoutRoot())
				.withStats(character.getAttributes())
				.withStatValues(character.getStatValues())
				.build();

		result.setCharacter(pure);
		result.setUrl(new PoeComUrlBuilder()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withPassiveSkillIds(request.getPassiveSkillIds())
				.toUrl());
	}

	public interface CreateCharacterRequest
	{
		CharacterClass getCharacterClass();

		List<Integer> getPassiveSkillIds();
	}

	public interface CreateCharacterResult
	{
		void setCharacter(ImmutableCharacter immutableCharacter);

		void setUrl(String url);
	}
}
