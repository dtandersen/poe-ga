package poe.command;

import static org.junit.Assert.assertThat;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveMatcher;
import poe.entity.PassiveSkillAttributeType;
import poe.entity.PoeMatchers;
import poe.entity.Stat;
import poe.entity.StatMatcher;
import poe.repo.JsonSkillRepo;
import poe.repo.SkillRepo;

public class CreateCharacterTest
{
	private CreateCharacterResultImplementation result;

	private final SkillRepo jsonSkillRepo = new JsonSkillRepo();

	@Test
	public void level1Witch()
	{
		createCharacter(CharacterClass.WITCH);

		assertThat(theCharacter(), hasStats()
				.withStat(Stat.STRENGTH, 14)
				.withStat(Stat.DEXTERITY, 14)
				.withStat(Stat.INTELLIGENCE, 32)
				.withStat(Stat.LIFE, 57)
				.withStat(Stat.MANA, 56)
				.withStat(Stat.EVASION_RATING, 58)
				.withStat(Stat.ACCURACY, 28));
	}

	@Test
	public void level1Marauder()
	{
		createCharacter(CharacterClass.MARAUDER);

		assertThat(theCharacter(), hasStats()
				.withStat(Stat.STRENGTH, 32)
				.withStat(Stat.DEXTERITY, 14)
				.withStat(Stat.INTELLIGENCE, 14)
				.withStat(Stat.LIFE, 66)
				.withStat(Stat.MANA, 47)
				.withStat(Stat.EVASION_RATING, 58)
				.withStat(Stat.ACCURACY, 28));
	}

	@Test
	public void passiveDexterity()
	{
		createCharacter(CharacterClass.MARAUDER, new Integer[] { 60942 });

		assertThat(theCharacter(), hasPassives()
				.withPassive(PassiveSkillAttributeType.DEXTERITY, 10));
	}

	private PassiveMatcher hasPassives()
	{
		return PoeMatchers.passiveMatcher();
	}

	private void createCharacter(final CharacterClass marauder, final Integer[] passiveSkillIds)
	{
		final CreateCharacter command = new CreateCharacter(jsonSkillRepo);
		result = new CreateCharacterResultImplementation();
		command.setRequest(new CreateCharacterRequest() {
			@Override
			public CharacterClass getCharacterClass()
			{
				return marauder;
			}

			@Override
			public List<Integer> getPassiveSkillIds()
			{
				return Arrays.asList(passiveSkillIds);
			}
		});
		command.setResult(result);
		command.execute();
	}

	private void createCharacter(final CharacterClass witch)
	{
		final CreateCharacter command = new CreateCharacter(jsonSkillRepo);
		result = new CreateCharacterResultImplementation();
		command.setRequest(new CreateCharacterRequest() {
			@Override
			public CharacterClass getCharacterClass()
			{
				return witch;
			}

			@Override
			public List<Integer> getPassiveSkillIds()
			{
				return Arrays.asList(new Integer[0]);
			}
		});
		command.setResult(result);
		command.execute();
	}

	private ImmutableCharacter theCharacter()
	{
		return result.character;
	}

	private StatMatcher hasStats()
	{
		return new StatMatcher();
	}

	private final class CreateCharacterResultImplementation implements CreateCharacterResult
	{
		public ImmutableCharacter character;

		@Override
		public void setCharacter(final ImmutableCharacter character)
		{
			this.character = character;
		}
	}
}
