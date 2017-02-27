package poe.command;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import poe.command.PureImmutablePassiveSkill.ImmutablePassiveSkillBuilder;
import poe.command.RandomBuild.RandomBuildRequest;
import poe.command.RandomBuild.RandomBuildResult;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.ImmutableCharacter.ImmutablePassiveSkill;
import poe.entity.PassiveSkill.PassiveSkillBuilder;
import poe.entity.PoeMatchers;
import poe.repository.InMemoryPassiveSkillRepository;
import poe.repository.Randomizer;

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
				.withOutputs(31628, 50904, 17765, 24704, 29294)
				.withType(5));
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
				.withName("WITCH")
				.withId(1)
				.withOutputs(2, 3)
				.withType(5));
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
		givenRandomData(0, 0, 0, 0, 0, 0);
		final RandomBuild command = new RandomBuild(passiveRepo, randomizer);
		command.setRequest(new RandomBuildRequest() {
			@Override
			public int getSize()
			{
				return 2;
			}

			@Override
			public CharacterClass getCharacterClass()
			{
				return CharacterClass.MARAUDER;
			}
		});
		result = new RandomBuildResultImplementation();
		command.setResult(result);
		command.execute();

		assertThat(theCharacter(), PoeMatchers.hasPassives(
				passive("Melee Damage and Life"),
				passive("Melee Damage")));
	}

	@Test
	public void nodupes()
	{
		givenMarauderData();
		givenRandomData(0, 0, 0, 0, 0, 0);
		final RandomBuild command = new RandomBuild(passiveRepo, randomizer);
		command.setRequest(new RandomBuildRequest() {
			@Override
			public int getSize()
			{
				return 100;
			}

			@Override
			public CharacterClass getCharacterClass()
			{
				return CharacterClass.MARAUDER;
			}
		});
		result = new RandomBuildResultImplementation();
		command.setResult(result);
		command.execute();

		assertThat(theCharacter(), PoeMatchers.hasPassives(
				passive("Melee Damage and Life"),
				passive("Melee Damage")));
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

			@Override
			public CharacterClass getCharacterClass()
			{
				return CharacterClass.WITCH;
			}
		});
		result = new RandomBuildResultImplementation();
		command.setResult(result);
		command.execute();

		assertThat(theCharacter(), PoeMatchers.hasPassives(
				passive("Melee Damage and Life"),
				passive("Melee Damage")));
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

			@Override
			public CharacterClass getCharacterClass()
			{
				return CharacterClass.WITCH;
			}

		});
		result = new RandomBuildResultImplementation();
		command.setResult(result);
		command.execute();

		assertThat(theUrl(), equalTo("https://www.pathofexile.com/passive-skill-tree/AAAABAEAAAACAAM="));
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

	private ImmutablePassiveSkill passive(final String string)
	{
		return ImmutablePassiveSkillBuilder.passiveSkill().from(passiveRepo.findByName(string)).build();
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
