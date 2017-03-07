package poe.command;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static poe.command.SimpleEvolveCharacterRequest.SimpleEvolveCharacterRequestBuilder.request;
import static poe.entity.FitnessConfig.FitnessConfigBuilder.config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jenetics.Alterer;
import org.junit.Before;
import org.junit.Test;
import poe.command.EvolveCharacter.EvolveCharacterResult;
import poe.command.PureImmutablePassiveSkill.ImmutablePassiveSkillBuilder;
import poe.command.SimpleEvolveCharacterRequest.SimpleEvolveCharacterRequestBuilder;
import poe.entity.AltererConfig;
import poe.entity.AltererType;
import poe.entity.CharacterClass;
import poe.entity.FitnessConfig.ElementConfig.ElementConfigBuilder;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill.PassiveSkillBuilder;
import poe.entity.PoeMatchers;
import poe.entity.Stat;
import poe.entity.StatValue.StatBuilder;
import poe.jenetics.AltererFactory;
import poe.jenetics.DeterministicMutator;
import poe.jenetics.JeneticsEvolver;
import poe.jenetics.SkillGene;
import poe.repository.EvolutionStatus;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;
import poe.repository.RepoBuilder;

public class EvolveCharacterTest
{
	private static final int SLEEP_TIME = 1;

	private EvolveCharacterResultImplementation result;

	private PassiveSkillTree passiveSkillTree;

	private PassiveSkillRepository repo;

	private JeneticsEvolver evolver;

	private List<AltererConfig> altererConfig;

	@Before
	public void setUp()
	{
		result = null;
		passiveSkillTree = null;
		repo = null;
		evolver = null;
		altererConfig = new ArrayList<>();
		altererConfig.add(new AltererConfig(AltererType.DETERMINISTIC.name().toLowerCase(), 1));
	}

	@Test
	public void test() throws InterruptedException
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

		final AltererFactory altererFactory = givenAlterer(new Integer[][] { { 1, 2 } });
		evolver = new JeneticsEvolver(passiveSkillTree, altererFactory);

		whenBuildEvolved(request()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withPopulation(1)
				.withGenerations(1)
				.withAlterers(altererConfig)
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

		sleep();
	}

	private AltererFactory givenAlterer(final Integer[][] data1)
	{
		final List<List<Integer>> testingGenes = new ArrayList<>();

		for (final Integer[] data : data1)
		{
			final List<Integer> genes1 = Arrays.asList(data);
			testingGenes.add(genes1);
		}

		final DeterministicMutator alterer = new DeterministicMutator(testingGenes);
		return new AltererFactory(passiveSkillTree) {
			@Override
			public Alterer<SkillGene, Integer> createMutator(final String altererName, final float probability)
			{
				return alterer;
			}
		};
	}

	@Test
	public void duelist() throws InterruptedException
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

		final AltererFactory altererFactory = givenAlterer(new Integer[][] {
				{ 3, 3 },
				{ 3, 4 } });

		evolver = new JeneticsEvolver(passiveSkillTree, altererFactory);

		whenBuildEvolved(request()
				.withCharacterClass(CharacterClass.DUELIST)
				.withPopulation(1)
				.withGenerations(2)
				.withSkillCount(2)
				.withAlterers(altererConfig)
				.withFitnessConfig(config()
						.withElement(ElementConfigBuilder.element()
								.withExpression("passiveSkillCount"))
						.withElement(ElementConfigBuilder.element()
								.withExpression("dexterity * 11"))));

		assertThat(result.finalCharacter, PoeMatchers.hasPassives(
				ImmutablePassiveSkillBuilder.passiveSkill().from(passive2).build()));
		assertThat(result.finalCharacter.getCharacterClass(), equalTo(CharacterClass.DUELIST));
		assertThat(result.getGenerations(), equalTo(2L));
		assertThat(result.getFitness(), equalTo(111));
		assertThat(genCharacter(1), PoeMatchers.hasPassives());
		assertThat(genCharacter(2), PoeMatchers.hasPassives(
				ImmutablePassiveSkillBuilder.passiveSkill().from(passive2).build()));

		sleep();
	}

	private void sleep() throws InterruptedException
	{
		Thread.yield();
		Thread.sleep(SLEEP_TIME);
	}

	private ImmutableCharacter genCharacter(final long generation)
	{
		return result.characterUpdates.get(generation);
	}

	private void whenBuildEvolved(final SimpleEvolveCharacterRequestBuilder evolutionContextBuilder)
	{
		final EvolveCharacter command = new EvolveCharacter(evolver);
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
