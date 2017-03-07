package poe.jenetics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jenetics.Alterer;
import org.jenetics.Chromosome;
import org.jenetics.Genotype;
import org.jenetics.PublicCompositeAlterer;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionResult;
import org.jenetics.util.Factory;
import poe.entity.AltererConfig;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PoeCharacter;
import poe.repository.Evolver;
import poe.repository.PassiveSkillTree;

public class JeneticsEvolver implements Evolver
{
	private final PassiveSkillTree passiveSkillTree;

	private final AltererFactory altererFactory;

	public JeneticsEvolver(
			final PassiveSkillTree passiveSkillTree,
			final AltererFactory altererFactory)
	{
		this.passiveSkillTree = passiveSkillTree;
		this.altererFactory = altererFactory;
	}

	@Override
	public void evolve(
			final PoeEvolutionContext evolutionContext,
			final PoeEvolutionResult poeEvolutionResult)
	{
		final List<PassiveSkill> passives = passiveSkillTree.passiveSkills();
		final CharacterClass characterClass = evolutionContext.getCharacterClass();
		final int pop = evolutionContext.getPopulation();
		final int length = evolutionContext.getSkills();
		final int threads = evolutionContext.getThreads();// Runtime.getRuntime().availableProcessors();
		// final float mutRate = (1f / length);

		final List<Integer> ids = passives.stream().map(new Function<PassiveSkill, Integer>() {
			@Override
			public Integer apply(final PassiveSkill t)
			{
				return t.getId();
			}
		}).collect(Collectors.toList());
		final Factory<Genotype<SkillGene>> gtf = new FactoryImplementation(ids, length);
		// final Factory<Genotype<SkillGene>> gtf = new RandomWalkSkillGeneFactory(passiveSkillTree, characterClass, length, ids);
		System.out.println("global length=" + length);

		final ExecutorService exec = Executors.newFixedThreadPool(threads);

		final FitnessFunction fitnessFunction = new FitnessFunction(passiveSkillTree, characterClass, evolutionContext.getCharacterEvaluator());

		final List<Alterer<SkillGene, Integer>> alterers2 = new ArrayList<>();
		final List<AltererConfig> altererConfig2 = evolutionContext.getAltererConfig();
		for (final AltererConfig altererConfig : altererConfig2)
		{
			alterers2.add(altererFactory.createMutator(altererConfig.getAltererTypeName().toLowerCase(), altererConfig.getProbability()));
		}

		final Alterer<SkillGene, Integer>[] altererArray = alterers2.toArray(new Alterer[0]);
		final Engine<SkillGene, Integer> engine = Engine
				.builder(fitnessFunction, gtf)
				.populationSize(pop)
				.alterers(PublicCompositeAlterer.of(altererArray))
				.build();

		final EvolutionResult<SkillGene, Integer> result = engine.stream()
				.limit(evolutionContext.getGenerationLimit())
				.peek(new EvolutionWatcher(new CharacterUpdateCallback(poeEvolutionResult), passiveSkillTree, evolutionContext.getCharacterClass(), evolutionContext.getCharacterEvaluator()))
				.collect(EvolutionResult.toBestEvolutionResult());

		final Chromosome<SkillGene> chromosome = result.getBestPhenotype().getGenotype().getChromosome(0);

		final PoeCharacter character = new PoeCharacter(characterClass);
		character.addPassiveSkill(passiveSkillTree.findByName(characterClass.getRootPassiveSkillName()));
		final List<PassiveSkill> myPassives = new ArrayList<>();
		for (final SkillGene g : chromosome)
		{
			final PassiveSkill passiveSkill = passiveSkillTree.find(g.getPassiveSkillId());
			myPassives.add(passiveSkill);
		}
		character.sneakyAdd(myPassives);

		poeEvolutionResult.setCharacter(character);
		poeEvolutionResult.setGenerations(result.getTotalGenerations());
		poeEvolutionResult.setFitness(result.getBestFitness());
	}
}
