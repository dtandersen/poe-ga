package poe.command;

import static org.junit.Assert.assertThat;
import static poe.entity.PoeMatchers.hasCharacter;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.entity.Attribute;
import poe.entity.ImmutableCharacter;
import poe.entity.PoeMatchers;
import poe.entity.Stat;
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

		assertThat(theCharacter(), PoeMatchers.hasStats()
				.withStat(Attribute.STRENGTH, 14)
				.withStat(Attribute.DEXTERITY, 14)
				.withStat(Attribute.INTELLIGENCE, 32)
				.withStat(Attribute.LIFE, 57)
				.withStat(Attribute.MANA, 56)
				.withStat(Attribute.EVASION_RATING, 58)
				.withStat(Attribute.ACCURACY, 28));
	}

	@Test
	public void level1Marauder()
	{
		createCharacter(CharacterClass.MARAUDER);

		assertThat(theCharacter(), PoeMatchers.hasStats()
				.withStat(Attribute.STRENGTH, 32)
				.withStat(Attribute.DEXTERITY, 14)
				.withStat(Attribute.INTELLIGENCE, 14)
				.withStat(Attribute.LIFE, 66)
				.withStat(Attribute.MANA, 47)
				.withStat(Attribute.EVASION_RATING, 58)
				.withStat(Attribute.ACCURACY, 28));
	}

	@Test
	public void passiveDexterity()
	{
		createCharacter(CharacterClass.MARAUDER, new Integer[] { 60942, 6741 });

		assertThat(theCharacter(), hasCharacter()
				.withStatValue(Stat.DEXTERITY, 10)
				.withStatValue(Stat.STRENGTH, 10));
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
