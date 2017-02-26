package poe.command;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Before;
import org.junit.Test;
import poe.command.RandomBuild.RandomBuildRequest;
import poe.command.RandomBuild.RandomBuildResult;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkill.PassiveSkillBuilder;
import poe.matcher.ComposableMatcher;
import poe.repository.InMemoryPassiveSkillRepository;
import poe.repository.Randomizer;
import poe.repository.RandomizerStub;

public class RandomBuildTest
{
	private InMemoryPassiveSkillRepository passiveRepo;

	private RandomBuildResultImplementation result;

	private Randomizer randomizer;

	@Before
	public void setUp()
	{
		passiveRepo = new InMemoryPassiveSkillRepository();
	}

	private void givenMarauderData()
	{
		// 47175: MARAUDER -> [31628, 50904, 17765, 24704, 29294]
		// 31628: Melee Damage and Life -> [9511]
		// 9511: +10 to Strength
		// 33988: Melee Damage -> [31628]
		passiveRepo.create(passiveSkill()
				.withName("MARAUDER")
				.withId(47175)
				.withOutputs(31628, 50904, 17765, 24704, 29294));
		passiveRepo.create(passiveSkill()
				.withName("Melee Damage and Life")
				.withId(31628)
				.withOutputs(9511));
		passiveRepo.create(passiveSkill()
				.withName("Melee Damage")
				.withId(33988)
				.withOutputs(31628));
	}

	private void givenWitchData()
	{
		// 47175: MARAUDER -> [31628, 50904, 17765, 24704, 29294]
		// 31628: Melee Damage and Life -> [9511]
		// 9511: +10 to Strength
		// 33988: Melee Damage -> [31628]
		passiveRepo.create(passiveSkill()
				.withName("MARAUDER")
				.withId(1)
				.withOutputs(2, 3));
		passiveRepo.create(passiveSkill()
				.withName("Melee Damage and Life")
				.withId(2)
				.withOutputs());
		passiveRepo.create(passiveSkill()
				.withName("Melee Damage")
				.withId(3)
				.withOutputs());
	}

	@Test
	public void test()
	{
		givenMarauderData();
		final Randomizer randomizer = new RandomizerStub();
		final RandomBuild command = new RandomBuild(passiveRepo, randomizer);
		command.setRequest(new RandomBuildRequest() {
			@Override
			public int getSize()
			{
				return 3;
			}
		});
		result = new RandomBuildResultImplementation();
		command.setResult(result);
		command.execute();

		assertThat(theCharacter(), hasPassives(
				"MARAUDER",
				"Melee Damage and Life",
				"Melee Damage"));
	}

	@Test
	public void nodupes()
	{
		givenMarauderData();
		final Randomizer randomizer = new RandomizerStub();
		final RandomBuild command = new RandomBuild(passiveRepo, randomizer);
		command.setRequest(new RandomBuildRequest() {
			@Override
			public int getSize()
			{
				return 100;
			}
		});
		result = new RandomBuildResultImplementation();
		command.setResult(result);
		command.execute();

		assertThat(theCharacter(), hasPassives(
				"MARAUDER",
				"Melee Damage and Life",
				"Melee Damage"));
	}

	@Test
	public void nodupes2()
	{
		givenWitchData();
		givenRandomData(0, 0, 0, 0, 1, 0);
		final RandomBuild command = new RandomBuild(passiveRepo, randomizer);
		command.setRequest(new RandomBuildRequest() {
			@Override
			public int getSize()
			{
				return 100;
			}
		});
		result = new RandomBuildResultImplementation();
		command.setResult(result);
		command.execute();

		assertThat(theCharacter(), hasPassives(
				"MARAUDER",
				"Melee Damage and Life",
				"Melee Damage"));
	}

	@Test
	public void returnTheUrl()
	{
		givenWitchData();
		givenRandomData(0, 0, 0, 0, 1, 0);
		final RandomBuild command = new RandomBuild(passiveRepo, randomizer);
		command.setRequest(new RandomBuildRequest() {
			@Override
			public int getSize()
			{
				return 100;
			}
		});
		result = new RandomBuildResultImplementation();
		command.setResult(result);
		command.execute();

		assertThat(theUrl(), equalTo("https://www.pathofexile.com/passive-skill-tree/AAAABAEAAAABAAIAAw=="));
	}

	private String theUrl()
	{
		return result.getUrl();
	}

	private void givenRandomData(final int... series)
	{
		randomizer = new SeriesRandomizer(series);
	}

	private PassiveSkillBuilder passiveSkill()
	{
		return new PassiveSkillBuilder();
	}

	private Matcher<ImmutableCharacter> hasPassives(final int i)
	{
		final List<Integer> asList = Arrays.asList(new Integer[] { i });
		return new ComposableMatcher<ImmutableCharacter, Object>(equalTo(asList)) {
			@Override
			public void describeTo(final Description description)
			{
				description.appendText("an ImmutableCharacter with ");
				description.appendText("the passive skills " + i);
			}

			@Override
			protected Object getValue(final ImmutableCharacter item)
			{
				return item.getPassiveSkillIds();
			}
		};
	}

	private Matcher<ImmutableCharacter> hasPassives(final String... names)
	{
		final List<Integer> asList = new ArrayList<>();
		for (final String name : names)
		{
			final PassiveSkill passiveSkill = passiveRepo.findByName(name);
			asList.add(passiveSkill.getId());
		}
		return new ComposableMatcher<ImmutableCharacter, Object>(equalTo(asList)) {
			@Override
			public void describeTo(final Description description)
			{
				description.appendText("an ImmutableCharacter with ");
				description.appendText("the passive skills " + asList);
			}

			@Override
			protected Object getValue(final ImmutableCharacter item)
			{
				return item.getPassiveSkillIds();
			}
		};
	}

	private ImmutableCharacter theCharacter()
	{
		return result.getCharacter();
	}

	private static final class SeriesRandomizer implements Randomizer
	{
		private final int[] series;

		int i = 0;

		private SeriesRandomizer(final int[] series)
		{
			this.series = series;
		}

		@Override
		public int nextInt(final int range)
		{
			return series[i++];
		}
	}

	private final class TypeSafeDiagnosingMatcherExtension extends TypeSafeDiagnosingMatcher<PoeCharacter>
	{
		private final ImmutableCharacter expectedCharacter;

		public TypeSafeDiagnosingMatcherExtension(final ImmutableCharacter expectedCharacter)
		{
			this.expectedCharacter = expectedCharacter;
		}

		@Override
		public void describeTo(final Description description)
		{
			description.appendText("a ");
			description.appendValue(expectedCharacter);
		}

		@Override
		protected boolean matchesSafely(final PoeCharacter item, final Description mismatchDescription)
		{
			if (!Objects.equals(item, expectedCharacter))
			{
				mismatchDescription.appendValue(item);
				return false;
			}

			return true;
		}
	}

	private final class RandomBuildResultImplementation implements RandomBuildResult
	{
		private ImmutableCharacter character;

		private String url;

		public ImmutableCharacter getCharacter()
		{
			return character;
		}

		@Override
		public void setCharacter(final ImmutableCharacter immutableCharacter)
		{
			character = immutableCharacter;
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
