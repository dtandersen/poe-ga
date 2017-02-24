package poe.command;

import java.util.List;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill;
import poe.entity.Attribute;
import poe.repo.SkillRepo;

public class CreateCharacter extends BaseCommand<CreateCharacterRequest, CreateCharacterResult>
{
	private final SkillRepo skillRepo;

	public CreateCharacter(final SkillRepo skillRepo)
	{
		this.skillRepo = skillRepo;
	}

	@Override
	void execute()
	{
		final int level = 1;
		final ImmutableCharacterProxy character = new ImmutableCharacterProxy();

		baseStats(level, character);
		final List<PassiveSkill> passiveTree = skillRepo.all();
		applyPassives(character, request.getPassiveSkillIds(), passiveTree);

		result.setCharacter(character);
	}

	private void applyPassives(
			final ImmutableCharacterProxy character,
			final List<Integer> passiveSkillIds,
			final List<PassiveSkill> passiveTree)
	{
		if (passiveSkillIds.isEmpty())
		{
			return;
		}
		final PassiveSkill passive = find(passiveTree, passiveSkillIds.get(0));
		character.apply(passive);
	}

	private PassiveSkill find(final List<PassiveSkill> passiveTree, final Integer integer)
	{
		for (final PassiveSkill passiveSkill : passiveTree)
		{
			if (passiveSkill.getId() == integer)
			{
				return passiveSkill;
			}
		}

		return null;
	}

	private void baseStats(final int level, final ImmutableCharacterProxy character)
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

	private int dexDiv5(final ImmutableCharacterProxy character)
	{
		final float dex = character.stat(Attribute.DEXTERITY);
		final float gg = dex % 5;
		if (gg == 0)
		{
			return (int)(dex / 5);
		}

		final int g = (int)(dex - gg);
		final int f = g / 5;
		return f;
	}

	public static interface CreateCharacterRequest
	{
		CharacterClass getCharacterClass();

		List<Integer> getPassiveSkillIds();
	}

	public static interface CreateCharacterResult
	{
		void setCharacter(ImmutableCharacter immutableCharacter);
	}
}
