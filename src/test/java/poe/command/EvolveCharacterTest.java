package poe.command;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static poe.command.FitnessConfig.FitnessConfigBuilder.config;
import static poe.command.SimpleEvolveCharacterRequest.SimpleEvolveCharacterRequestBuilder.request;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import poe.jenetics.DeterministicMutator;
import poe.jenetics.JeneticsEvolver;
import poe.repository.EvolutionStatus;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;
import poe.repository.RepoBuilder;

public class EvolveCharacterTest
{
	private EvolveCharacterResultImplementation result;

	private List<AltererConfig> alterers;

	// private final List<AltererType> mutators = new ArrayList<>();

	private PassiveSkillTree passiveSkillTree;

	private PassiveSkillRepository repo;

	private JeneticsEvolver evolver;

	@Before
	public void setUp()
	{
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

		final List<List<Integer>> testingGenes = new ArrayList<>();
		final List<Integer> genes1 = Arrays.asList(new Integer[] { 1, 2 });
		// final List<Integer> genes2 = Arrays.asList(new Integer[] { 1, 2 });
		testingGenes.add(genes1);
		// testingGenes.add(genes2);

		givenAlterers(new AltererConfig(AltererType.NULL));

		whenBuildEvolved(request()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withPopulation(1)
				.withGenerations(1)
				.withAlterers(new DeterministicMutator(testingGenes))
				.withSkillCount(2)
				.withFitnessConfig(config()
						.withElement(ElementConfigBuilder.element()
								.withExpression("passiveSkillCount"))
						.withElement(ElementConfigBuilder.element()
								.withExpression("strength * 10"))));

		assertThat(result.finalCharacter, PoeMatchers.hasPassives(
				ImmutablePassiveSkillBuilder.passiveSkill().from(passive2).build()));
		assertThat(result.getGenerations(), equalTo(1L));
		assertThat(result.getFitness(), equalTo(101));
		assertThat(result.finalCharacter.getCharacterClass(), equalTo(CharacterClass.MARAUDER));
		// assertThat(result.characterUpdates, Matchers.instanceOf(ImmutableCharacter.class));
	}

	// @Test
	public void duelist()
	{
		final PassiveSkillBuilder passive1 = PassiveSkillBuilder.passiveSkill()
				.withName(CharacterClass.DUELIST.getRootPassiveSkillName())
				.withId(3)
				.withOutputs(4)
				.withClassStartingPoint(CharacterClass.DUELIST);
		final PassiveSkillBuilder passive2 = PassiveSkillBuilder.passiveSkill()
				.withName("Dexterity")
				.withId(4)
				.withStats(StatBuilder.stat()
						.withStat(Stat.DEXTERITY)
						.withValue(10));
		repo = new RepoBuilder()
				.withPassiveSkills(passive1, passive2)
				.build();
		passiveSkillTree = new PassiveSkillTree(repo.all());
		evolver = new JeneticsEvolver(passiveSkillTree);

		// givenAlterers(new AltererConfig(AltererType.NULL));
		final List<List<Integer>> testingGenes = new ArrayList<>();
		final List<Integer> genes1 = Arrays.asList(new Integer[] { 3, 3 });
		final List<Integer> genes2 = Arrays.asList(new Integer[] { 3, 4 });
		testingGenes.add(genes1);
		testingGenes.add(genes2);
		// givenAlterers(new DeterministicMutator(testingGenes));

		whenBuildEvolved(request()
				.withCharacterClass(CharacterClass.DUELIST)
				.withPopulation(1)
				.withGenerations(2)
				.withSkillCount(2)
				.withAlterers(new DeterministicMutator(testingGenes))
				// .withAlterers("deterministic")
				.withFitnessConfig(config()
						.withElement(ElementConfigBuilder.element()
								.withExpression("passiveSkillCount"))
						.withElement(ElementConfigBuilder.element()
								.withExpression("dexterity * 10"))));

		assertThat(result.finalCharacter, PoeMatchers.hasPassives(
				ImmutablePassiveSkillBuilder.passiveSkill().from(passive2).build()));
		assertThat(result.finalCharacter.getCharacterClass(), equalTo(CharacterClass.DUELIST));
		assertThat(result.getGenerations(), equalTo(2L));
		assertThat(result.getFitness(), equalTo(101));
		assertThat(genCharacter(1), PoeMatchers.hasPassives());
		assertThat(genCharacter(2), PoeMatchers.hasPassives(
				ImmutablePassiveSkillBuilder.passiveSkill().from(passive2).build()));
	}

	private ImmutableCharacter genCharacter(final long generation)
	{
		return result.characterUpdates.get(generation);
	}

	private void givenAlterers(final AltererConfig... mutatorTypes)
	{
		alterers = new ArrayList<>();
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
		public Map<Long, ImmutableCharacter> characterUpdates = new HashMap<>();

		public ImmutableCharacter finalCharacter;

		private long generations;

		private int fitness;

		@Override
		public void setCharacter(final ImmutableCharacter character)
		{
			this.finalCharacter = character;
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
		public void newBest(final EvolutionStatus evolutionStatus)
		{
			final ImmutableCharacter character = evolutionStatus.getCharacter();
			final long generation = evolutionStatus.getGeneration();
			System.out.println("gen=" + generation + "," + character.getPassiveSkills());
			characterUpdates.put(generation, character);
		}
	}
}
