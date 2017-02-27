package poe.command;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static poe.entity.CharacterClass.MARAUDER;
import static poe.entity.CharacterClass.WITCH;
import static poe.entity.PoeMatchers.hasStats2;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Before;
import org.junit.Test;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.command.PureImmutablePassiveSkill.ImmutablePassiveSkillBuilder;
import poe.entity.Attribute;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.ImmutableCharacter.ImmutablePassiveSkill;
import poe.entity.PassiveSkillTree;
import poe.entity.PoeMatchers;
import poe.entity.Stat;
import poe.repository.PassiveSkillRepository;
import poe.repository.json.JsonPassiveSkillRepository;

public class CreateCharacterTest
{
	private CreateCharacterResultImplementation result;

	private PassiveSkillRepository jsonSkillRepo;

	private PassiveSkillTree passiveSkillTree;

	@Before
	public void setUp()
	{
		jsonSkillRepo = new JsonPassiveSkillRepository();
		passiveSkillTree = new PassiveSkillTree(jsonSkillRepo.all());
	}

	@Test
	public void level1Witch()
	{
		createCharacter(WITCH);

		assertThat(theCharacter(), PoeMatchers.hasStats()
				.withStat(Attribute.STRENGTH, 14)
				.withStat(Attribute.DEXTERITY, 14)
				.withStat(Attribute.INTELLIGENCE, 32)
				.withStat(Attribute.LIFE, 57)
				.withStat(Attribute.MANA, 56)
				.withStat(Attribute.EVASION_RATING, 58)
				.withStat(Attribute.ACCURACY, 28));

		assertThat(theCharacter(), hasNoPassiveSkills());
	}

	@Test
	public void level1Marauder()
	{
		createCharacter(MARAUDER);

		assertThat(theCharacter(), PoeMatchers.hasStats()
				.withStat(Attribute.STRENGTH, 32)
				.withStat(Attribute.DEXTERITY, 14)
				.withStat(Attribute.INTELLIGENCE, 14)
				.withStat(Attribute.LIFE, 66)
				.withStat(Attribute.MANA, 47)
				.withStat(Attribute.EVASION_RATING, 58)
				.withStat(Attribute.ACCURACY, 28));

		// assertThat(theCharacter(), hasPassive(classPassive(MARAUDER)));
		assertThat(theCharacter(), hasNoPassiveSkills());
	}

	@Test
	public void passiveDexterity()
	{
		createCharacter(CharacterClass.SCION, new Integer[] { 62103, 2151 });

		assertThat(theCharacter(), hasStats2()
				.withStatValue(Stat.MANA_REGEN, 20)
				.withStatValue(Stat.PROJ_DAMAGE, 8)
				.withStatValue(Stat.DEXTERITY, 5)
				.withStatValue(Stat.INTELLIGENCE, 5));

		assertThat(theCharacter(), PoeMatchers.hasPassives(
				passiveWithId(62103),
				passiveWithId(2151)));
	}

	private Matcher<ImmutableCharacter> hasNoPassiveSkills()
	{
		return new TypeSafeDiagnosingMatcher<ImmutableCharacter>() {
			@Override
			public void describeTo(final Description description)
			{
				description.appendText("a character with no passive skills");
			}

			@Override
			protected boolean matchesSafely(final ImmutableCharacter item, final Description mismatchDescription)
			{
				final Matcher<Collection<? extends Object>> matcher = Matchers.empty();
				if (!matcher.matches(item.getPassiveSkills()))
				{
					matcher.describeMismatch(item.getPassiveSkills(), mismatchDescription);
					return false;
				}
				return true;
			}
		};
	}

	@Test
	public void marauderSkillsDeduplicate()
	{
		createCharacter(CharacterClass.MARAUDER, new Integer[] { 31628, 31628 });

		assertThat(theCharacter(), hasStats2()
				.withStatValue(Stat.MAX_LIFE_PLUS, 16)
				.withStatValue(Stat.MELEE_PHYSICAL_DAMAGE, 16));

		assertThat(theCharacter(), PoeMatchers.hasPassives(passiveWithId(31628)));
	}

	@Test
	public void impossibleMarauderSkills()
	{
		createCharacter(CharacterClass.MARAUDER, new Integer[] { 31628, 38148 });

		assertThat(theCharacter(), hasStats2()
				.withStatValue(Stat.MAX_LIFE_PLUS, 16)
				.withStatValue(Stat.MELEE_PHYSICAL_DAMAGE, 16));

		assertThat(theCharacter(), PoeMatchers.hasPassives(passiveWithId(31628)));
	}

	@Test
	public void marauderUrl()
	{
		createCharacter(CharacterClass.MARAUDER, new Integer[] { 31628 });

		assertThat(result, PoeMatchers.hasUrl(equalTo("https://www.pathofexile.com/passive-skill-tree/AAAABAEAAHuM")));
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

	private ImmutablePassiveSkill classPassive(final CharacterClass characterClass)
	{
		return passsiveNamed(characterClass.getRootPassiveSkillName());
	}

	private ImmutablePassiveSkill passsiveNamed(final String name)
	{
		return ImmutablePassiveSkillBuilder.passiveSkill().from(passiveSkillTree.findByName(name)).build();
	}

	private ImmutablePassiveSkill passiveWithId(final int passiveSkillId)
	{
		return ImmutablePassiveSkillBuilder.passiveSkill().from(passiveSkillTree.find(passiveSkillId)).build();
	}

	public final class CreateCharacterResultImplementation implements CreateCharacterResult
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
