package poe.command;

import static org.junit.Assert.assertThat;
import org.junit.Test;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.entity.ImmutableCharacter;
import poe.entity.Stat;

public class CreateCharacterTest
{
	private CreateCharacterResultImplementation result;

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

	private void createCharacter(final CharacterClass witch)
	{
		final CreateCharacter command = new CreateCharacter();
		result = new CreateCharacterResultImplementation();
		command.setRequest(new CreateCharacterRequest() {
			@Override
			public CharacterClass getCharacterClass()
			{
				return witch;
			}
		});
		command.setResult(result);
		command.execute();
	}

	private ImmutableCharacter theCharacter()
	{
		return result.character;
	}

	private TypeSafeDiagnosingMatcherExtension hasStats()
	{
		return new TypeSafeDiagnosingMatcherExtension();
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
