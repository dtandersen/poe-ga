package poe.command;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static poe.entity.PoeMatchers.hasCharacter;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matcher;
import org.junit.Test;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.entity.Attribute;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.PoeMatchers;
import poe.entity.Stat;
import poe.matcher.ComposableMatcher;
import poe.repository.PassiveSkillRepository;
import poe.repository.json.JsonPassiveSkillRepository;

public class CreateCharacterTest
{
	private CreateCharacterResultImplementation result;

	private final PassiveSkillRepository jsonSkillRepo = new JsonPassiveSkillRepository();

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

	@Test
	public void marauderUrl()
	{
		createCharacter(CharacterClass.MARAUDER, new Integer[] { 31628 });

		assertThat(result, hasUrl(equalTo("https://www.pathofexile.com/passive-skill-tree/AAAABAEAAHuM")));
	}

	private Matcher<CreateCharacterResultImplementation> hasUrl(final Matcher<String> matcher)
	{
		return new ComposableMatcher<CreateCharacterTest.CreateCharacterResultImplementation, String>(matcher) {
			@Override
			protected String getValue(final CreateCharacterResultImplementation item)
			{
				return result.getUrl();
			}
		};
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

	private void createCharacter(final CharacterClass characterClass)
	{
		createCharacter(characterClass, new Integer[0]);
	}

	private ImmutableCharacter theCharacter()
	{
		return result.character;
	}

	private final class CreateCharacterResultImplementation implements CreateCharacterResult
	{
		public ImmutableCharacter character;

		private String url;

		@Override
		public void setCharacter(final ImmutableCharacter character)
		{
			this.character = character;
		}

		public String getUrl()
		{
			return url;
		}

		@Override
		public void setUrl(final String url)
		{
			this.url = url;
		}

	}
}
