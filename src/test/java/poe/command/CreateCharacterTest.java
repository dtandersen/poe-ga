package poe.command;

import static org.hamcrest.Matchers.equalTo;
import static org.hobsoft.hamcrest.compose.ComposeMatchers.compose;
import static org.hobsoft.hamcrest.compose.ComposeMatchers.hasFeature;
import static org.junit.Assert.assertThat;
import static poe.entity.CharacterClass.MARAUDER;
import static poe.entity.PoeMatchers.hasStats2;
import static poe.entity.PoeMatchers.passiveSkillEqualTo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.command.PureImmutablePassiveSkill.ImmutablePassiveSkillBuilder;
import poe.command.model.ImmutableCharacter;
import poe.command.model.ImmutableCharacter.ImmutablePassiveSkill;
import poe.command.model.ItemDescription;
import poe.entity.Attribute;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkill.PassiveSkillBuilder;
import poe.entity.PoeMatchers;
import poe.entity.Stat;
import poe.entity.StatValue;
import poe.entity.StatValue.StatBuilder;
import poe.repository.InMemoryPassiveSkillRepository;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;
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
		createCharacter("" +
				"characterClass: witch",
				"level: 1",
				"passiveSkills: []");

		assertThat(theCharacter(), PoeMatchers.hasStats()
				.withStat(Attribute.STRENGTH, 14)
				.withStat(Attribute.DEXTERITY, 14)
				.withStat(Attribute.INTELLIGENCE, 32)
				.withStat(Attribute.LIFE, 57)
				.withStat(Attribute.MANA, 56)
				.withStat(Attribute.EVASION_RATING, 58)
				.withStat(Attribute.ACCURACY, 28));

		assertThat(theCharacter(), hasNoPassiveSkills());
		assertThat(theCharacter(), hasAdjustedStats(
				StatValue.of(Stat.STRENGTH, 14),
				StatValue.of(Stat.DEXTERITY, 14),
				StatValue.of(Stat.INTELLIGENCE, 32),
				StatValue.of(Stat.MAXIMUM_LIFE, 57),
				StatValue.of(Stat.MANA_BONUS, 56),
				StatValue.of(Stat.EVASION_RATING, 58),
				StatValue.of(Stat.ACC_PLUS, 28)));
	}

	private Matcher<ImmutableCharacter> hasAdjustedStats(final StatValue... stats)
	{
		// return Matchers.containsInAnyOrder(stats);
		return compose("a character with", hasFeature("stats", ImmutableCharacter::getAdjustedStats, Matchers.containsInAnyOrder(stats)));
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

	@Test
	public void marauderSkillsDeduplicate()
	{
		createCharacter(CharacterClass.MARAUDER, new Integer[] { 31628, 31628 });

		assertThat(theCharacter(), hasStats2()
				.withStatValue(Stat.MAXIMUM_LIFE, 16)
				.withStatValue(Stat.MELEE_PHYSICAL_DAMAGE, 16));

		assertThat(theCharacter(), PoeMatchers.hasPassives(passiveWithId(31628)));
	}

	@Test
	public void impossibleMarauderSkills()
	{
		createCharacter(CharacterClass.MARAUDER, new Integer[] { 31628, 38148 });

		assertThat(theCharacter(), hasStats2()
				.withStatValue(Stat.MAXIMUM_LIFE, 16)
				.withStatValue(Stat.MELEE_PHYSICAL_DAMAGE, 16));

		assertThat(theCharacter(), PoeMatchers.hasPassives(passiveWithId(31628)));
	}

	@Test
	public void dontAllowAnotherRoot() throws IOException
	{
		given(passiveSkills("" +
				"name          | id | startPoint | outputs\n" +
				"SIX           | 1  | SHADOW     | 2\n" +
				"some skill    | 2  |            | 3\n" +
				"DUELIST       | 3  | DUELIST    | 4\n" +
				"cant get this | 4  |            |"));

		createCharacter(CharacterClass.SHADOW, new Integer[] { 2, 3, 4 });

		assertThat(theCharacter(), PoeMatchers.hasPassives(passiveWithId(2)));
	}

	@Test
	public void statsShouldAdd() throws IOException
	{
		given(passiveSkills("" +
				"name          | id | startPoint | outputs | stats\n" +
				"SIX           | 1  | SHADOW     | 2       |\n" +
				"strong        | 2  |            | 3       | +10 to Strength \n" +
				"very strong   | 3  |            | 4       | +20 to Strength\n" +
				"super strong  | 4  |            |         | +100 to Strength"));

		final PassiveSkillBuilder passive2 = PassiveSkillBuilder.passiveSkill()
				.withId(2)
				.withName("strong")
				.withOutputs(3)
				.withStats(StatBuilder.stat().withStat(Stat.STRENGTH).withValue(10));

		final PassiveSkillBuilder passive3 = PassiveSkillBuilder.passiveSkill()
				.withId(3)
				.withName("very strong")
				.withOutputs(4)
				.withStats(StatBuilder.stat().withStat(Stat.STRENGTH).withValue(20));

		final PassiveSkillBuilder passive4 = PassiveSkillBuilder.passiveSkill()
				.withId(4)
				.withName("super strong")
				.withOutputs(5)
				.withStats(StatBuilder.stat().withStat(Stat.STRENGTH).withValue(100));

		createCharacter(CharacterClass.SHADOW, new Integer[] { 2, 3, 4 });

		assertThat(theCharacter(), PoeMatchers.hasPassives(
				ImmutablePassiveSkillBuilder.passiveSkill().from(passive2).build(),
				ImmutablePassiveSkillBuilder.passiveSkill().from(passive3).build(),
				ImmutablePassiveSkillBuilder.passiveSkill().from(passive4).build()));
		assertThat(theCharacter().getStatValues(), Matchers.contains(new StatValue(Stat.STRENGTH, 130)));
	}

	@Test
	public void statsShouldntChange() throws IOException
	{
		given(passiveSkills("" +
				"name          | id | startPoint | outputs | stats\n" +
				"SIX           | 1  | SHADOW     | 2       |\n" +
				"strong        | 2  |            | 3       | +10 to Strength \n" +
				"very strong   | 3  |            | 4       | +20 to Strength\n" +
				"super strong  | 4  |            |         | +100 to Strength"));

		final PassiveSkillBuilder passive2 = PassiveSkillBuilder.passiveSkill()
				.withId(2)
				.withName("strong")
				.withOutputs(3)
				.withStats(StatBuilder.stat().withStat(Stat.STRENGTH).withValue(10));

		createCharacter("" +
				"characterClass: shadow\n" +
				"passiveSkills: [2, 3, 4]");
		createCharacter("" +
				"characterClass: shadow\n" +
				"passiveSkills: [2, 3, 4]");

		assertThat(find(2), passiveSkillEqualTo(passive2));
	}

	@Test
	public void withAnItem() throws IOException
	{
		given(passiveSkills("" +
				"name          | id | startPoint | outputs | stats\n" +
				"SIX           | 1  | Seven      | 2       |\n" +
				"strong        | 2  |            | 3       | +10 to Strength \n" +
				"very strong   | 3  |            | 4       | +20 to Strength\n" +
				"super strong  | 4  |            |         | +100 to Strength"));

		createCharacter(
				"characterClass: scion",
				"level: 1",
				"passiveSkills: [2, 3, 4]",
				"items:",
				"  - stats:",
				"    - stat: +10 to Dexterity",
				"  - stats:",
				"    - stat: +30 to Dexterity",
				"    - stat: +5 to Strength");

		assertThat(theCharacter().getLevel(), equalTo(1));
		assertThat(theCharacter().getAdjustedStat(Stat.DEXTERITY), equalTo(60f));
		assertThat(theCharacter().getAdjustedStat(Stat.STRENGTH), equalTo(155f));
	}

	private PassiveSkill find(final int i)
	{
		for (final PassiveSkill passive : jsonSkillRepo.all())
		{
			if (passive.getId() == i) { return passive; }
		}

		return null;
	}

	@Test
	public void marauderUrl()
	{
		createCharacter(CharacterClass.MARAUDER, new Integer[] { 31628 });

		assertThat(result, PoeMatchers.hasUrl(equalTo("https://www.pathofexile.com/passive-skill-tree/AAAABAEAAHuM")));
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

	private void createCharacter(final String yaml)
	{
		final CreateCharacter command = new CreateCharacter(jsonSkillRepo);
		result = new CreateCharacterResultImplementation();
		command.setRequest(new YamlCreateCharacterRequest(yaml));
		command.setResult(result);
		command.execute();
	}

	private void createCharacter(final String... yaml)
	{
		final String joinedYaml = Arrays.stream(yaml).collect(Collectors.joining("\n"));
		createCharacter(joinedYaml);
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

			@Override
			public int getLevel()
			{
				return 1;
			}

			@Override
			public List<ItemDescription> getItems()
			{
				return new ArrayList<>();
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

	private ImmutablePassiveSkill passiveWithId(final int passiveSkillId)
	{
		return ImmutablePassiveSkillBuilder.passiveSkill().from(passiveSkillTree.find(passiveSkillId)).build();
	}

	private static class YamlCreateCharacterRequest implements CreateCharacterRequest
	{
		private final YamlRequest r;

		public YamlCreateCharacterRequest(final String yaml)
		{
			r = new Yaml().loadAs(yaml, YamlRequest.class);
		}

		@Override
		public CharacterClass getCharacterClass()
		{
			return CharacterClass.find(r.characterClass);
		}

		@Override
		public List<Integer> getPassiveSkillIds()
		{
			return r.passiveSkills;
		}

		@Override
		public int getLevel()
		{
			return r.level;
		}

		@Override
		public List<ItemDescription> getItems()
		{
			if (r.items == null) { return new ArrayList<>(); }

			return r.items.stream()
					.map(item -> {
						final ItemDescription i = new ItemDescription();
						item.stats.forEach(stat -> i.addSkillDescription(stat.stat));
						return i;
					})
					.collect(Collectors.toList());
		}

		static class YamlRequest
		{
			public String characterClass;

			public int level;

			public List<Integer> passiveSkills;

			public List<YamlItem> items;

			static class YamlItem
			{
				public List<YamlStat> stats;

				static class YamlStat
				{
					public String stat;
				}
			}
		}
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

	private List<PassiveSkillBuilder> passiveSkills(final String markDown) throws IOException
	{
		final List<PassiveSkillBuilder> passiveSkillBuilders = MarkdownStream
				.stream(markDown)
				.skip(1)
				.map(md -> PassiveSkillBuilder
						.passiveSkill()
						.withName(md.trimmed(0))
						.withId(md.intValue(1))
						.withClassStartingPoint(CharacterClass.find(md.trimmed(2)))
						.withOutputs(md.trimmedOptional(3))
						.withStats(theStats(md.trimmedOptional(4))))
				.collect(Collectors.toList());

		return passiveSkillBuilders;
	}

	private StatBuilder[] theStats(final Optional<String> markDown)
	{
		if (!markDown.isPresent()) { return new StatBuilder[0]; }

		return Arrays
				.stream(markDown.get().split("\\,"))
				.map(md -> stat(md))
				.collect(Collectors.toList())
				.toArray(new StatBuilder[0]);
	}

	private StatBuilder stat(final String skillDescription)
	{
		for (final Stat stat : Stat.values())
		{
			final java.util.regex.Matcher m = stat.matcher(skillDescription);
			if (!m.find())
			{
				continue;
			}

			final java.util.regex.Matcher matcher = m;
			try
			{
				final String group = matcher.group(1);
				final float val = Float.parseFloat(group);
				final StatValue attribute = new StatValue(stat, val);
				return StatBuilder.stat(attribute.getStat(), attribute.getValue());
			}
			catch (final IndexOutOfBoundsException e2)
			{
			}
		}
		return null;
	}

	private void given(final List<PassiveSkillBuilder> passiveSkillBuilders)
	{
		final InMemoryPassiveSkillRepository memRepo = new InMemoryPassiveSkillRepository();
		jsonSkillRepo = memRepo;
		passiveSkillBuilders.forEach(builder -> ((InMemoryPassiveSkillRepository)jsonSkillRepo).create(builder));
		passiveSkillTree = new PassiveSkillTree(jsonSkillRepo.all());
	}
}
