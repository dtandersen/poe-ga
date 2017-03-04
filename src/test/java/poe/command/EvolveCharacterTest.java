package poe.command;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static poe.command.FitnessConfig.FitnessConfigBuilder.config;
import static poe.command.SimpleEvolveCharacterRequest.SimpleEvolveCharacterRequestBuilder.request;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import poe.command.EvolveCharacter.EvolveCharacterResult;
import poe.command.FitnessConfig.ElementConfig.ElementConfigBuilder;
import poe.command.PureImmutablePassiveSkill.ImmutablePassiveSkillBuilder;
import poe.command.SimpleEvolveCharacterRequest.SimpleEvolveCharacterRequestBuilder;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill.PassiveSkillBuilder;
import poe.entity.PoeMatchers;
import poe.entity.Stat;
import poe.entity.StatValue.StatBuilder;
import poe.jenetics.AltererType;
import poe.jenetics.JeneticsEvolver;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;
import poe.repository.RepoBuilder;

public class EvolveCharacterTest
{
	private EvolveCharacterResultImplementation result;

	private final List<AltererConfig> alterers = new ArrayList<>();

	private final List<AltererType> mutators = new ArrayList<>();

	private PassiveSkillTree passiveSkillTree;

	private PassiveSkillRepository repo;

	private JeneticsEvolver evolver;

	@Before
	public void setUp()
	{
		// passiveSkillTree=pass
	}

	@Test
	public void test()
	{
		final PassiveSkillBuilder passive1 = PassiveSkillBuilder.passiveSkill()
				.withName(CharacterClass.MARAUDER.getRootPassiveSkillName())
				.withId(1)
				.withOutputs(2)
				.withClassStartingPoint(CharacterClass.MARAUDER);
		final PassiveSkillBuilder passive2 = PassiveSkillBuilder.passiveSkill()
				.withName("Strength")
				.withId(2)
				.withStats(StatBuilder.stat()
						.withStat(Stat.STRENGTH)
						.withValue(10));
		repo = new RepoBuilder()
				.withPassiveSkills(passive1, passive2)
				.build();
		passiveSkillTree = new PassiveSkillTree(repo.all());
		evolver = new JeneticsEvolver(passiveSkillTree);

		givenAlterers(new AltererConfig(AltererType.NULL));

		whenBuildEvolved(request()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withPopulation(1)
				.withGenerations(1)
				.withSkillCount(10)
				.withFitnessConfig(config()
						.withElement(ElementConfigBuilder.element()
								.withExpression("passiveSkillCount"))
						.withElement(ElementConfigBuilder.element()
								.withExpression("strength * 10"))));

		assertThat(result.character, PoeMatchers.hasPassives(
				ImmutablePassiveSkillBuilder.passiveSkill().from(passive2).build()));
		assertThat(result.getGenerations(), equalTo(1L));
		assertThat(result.getFitness(), equalTo(101));
		assertThat(result.character.getCharacterClass(), equalTo(CharacterClass.MARAUDER));
		assertThat(result.characterUpdates, Matchers.instanceOf(ImmutableCharacter.class));
	}

	@Test
	public void duelist()
	{
		final PassiveSkillBuilder passive1 = PassiveSkillBuilder.passiveSkill()
				.withName(CharacterClass.MARAUDER.getRootPassiveSkillName())
				.withId(1)
				.withOutputs(2)
				.withClassStartingPoint(CharacterClass.MARAUDER);
		final PassiveSkillBuilder passive2 = PassiveSkillBuilder.passiveSkill()
				.withName("Strength")
				.withId(2)
				.withStats(StatBuilder.stat()
						.withStat(Stat.STRENGTH)
						.withValue(10));
		repo = new RepoBuilder()
				.withPassiveSkills(passive1, passive2)
				.build();
		passiveSkillTree = new PassiveSkillTree(repo.all());
		evolver = new JeneticsEvolver(passiveSkillTree);

		givenAlterers(new AltererConfig(AltererType.NULL));

		whenBuildEvolved(request()
				.withCharacterClass(CharacterClass.DUELIST)
				.withPopulation(1)
				.withGenerations(1)
				.withSkillCount(10)
				.withFitnessConfig(config()
						.withElement(ElementConfigBuilder.element()
								.withExpression("passiveSkillCount"))
						.withElement(ElementConfigBuilder.element()
								.withExpression("strength * 10"))));

		assertThat(result.character, PoeMatchers.hasPassives(
				ImmutablePassiveSkillBuilder.passiveSkill().from(passive2).build()));
		assertThat(result.character.getCharacterClass(), equalTo(CharacterClass.DUELIST));
		assertThat(result.getGenerations(), equalTo(1L));
		assertThat(result.getFitness(), equalTo(101));
	}

	private void givenAlterers(final AltererConfig... mutatorTypes)
	{
		Arrays.asList(mutatorTypes).stream().forEach(altererConfig -> alterers.add(altererConfig));
	}

	private void whenBuildEvolved(final SimpleEvolveCharacterRequestBuilder evolutionContextBuilder)
	{
		final EvolveCharacter command = new EvolveCharacter(evolver, repo, passiveSkillTree);
		command.setRequest(evolutionContextBuilder.build());
		result = new EvolveCharacterResultImplementation();
		command.setResult(result);
		command.execute();
	}

	private final class EvolveCharacterResultImplementation implements EvolveCharacterResult
	{
		public ImmutableCharacter characterUpdates;

		public ImmutableCharacter character;

		private long generations;

		private int fitness;

		@Override
		public void setCharacter(final ImmutableCharacter character)
		{
			this.character = character;
		}

		public int getFitness()
		{
			return fitness;
		}

		public long getGenerations()
		{
			return generations;
		}

		@Override
		public void setGenerations(final long generations)
		{
			this.generations = generations;
		}

		@Override
		public void setFitness(final int fitness)
		{
			this.fitness = fitness;
		}

		@Override
		public void newBest(final ImmutableCharacter character)
		{
			characterUpdates = character;
		}
	}
}
