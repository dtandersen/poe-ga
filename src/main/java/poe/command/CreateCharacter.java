package poe.command;

import java.util.List;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.command.PureImmutableCharacter.ImmutableCharacterBuilder;
import poe.entity.Attribute;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkillTree;
import poe.repository.PassiveSkillRepository;

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
		final int level = 1;
		final PoeCharacter character = new PoeCharacter();

		baseStats(level, character);
		final List<PassiveSkill> passiveTree = passiveSkillRepository.all();
		final PassiveSkillTree pst = new PassiveSkillTree(passiveTree);
		final PassiveSkill root = pst.findByName(request.getCharacterClass().getRootPassiveSkillName());
		// character.apply(root);
		character.applyPassives(request.getPassiveSkillIds(), pst);

		final ImmutableCharacter pure = new ImmutableCharacterBuilder()
				.withPassiveSkills(character.getPassiveSkills())
				.withStats(character.getStats())
				.withStatValues(character.getStatValues())
				.build();

		result.setCharacter(pure);
		result.setUrl(new PoeComUrlBuilder()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withPassiveSkillIds(request.getPassiveSkillIds())
				.toUrl());
	}

	private void baseStats(final int level, final PoeCharacter character)
	{
		final CharacterClass characterClass = request.getCharacterClass();
		character.stat(Attribute.STRENGTH, characterClass.getStrength());
		character.stat(Attribute.DEXTERITY, characterClass.getDexterity());
		character.stat(Attribute.INTELLIGENCE, characterClass.getIntelligence());
		character.stat(Attribute.LIFE, 38 + level * 12 + character.stat(Attribute.STRENGTH) / 2);
		character.stat(Attribute.MANA, (40 - 6) + level * 6 + character.stat(Attribute.INTELLIGENCE) / 2);
		final int dexdiv5 = dexDiv5(character);
		character.stat(Attribute.EVASION_RATING, 53 + level * 3 + dexdiv5);
		character.stat(Attribute.ACCURACY, character.stat(Attribute.DEXTERITY) * 2);
	}

	private int dexDiv5(final PoeCharacter character)
	{
		final float dex = character.stat(Attribute.DEXTERITY);
		final float gg = dex % 5;
		if (gg == 0) { return (int)(dex / 5); }

		final int g = (int)(dex - gg);
		final int f = g / 5;
		return f;
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
