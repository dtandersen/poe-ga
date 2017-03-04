package poe.repository.jenetics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jenetics.Chromosome;
import org.jenetics.Genotype;
import org.jenetics.Mutator;
import org.jenetics.SinglePointCrossover;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionResult;
import org.jenetics.util.Factory;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PoeCharacter;
import poe.repository.Evolver;
import poe.repository.PassiveSkillTree;

public class JeneticsEvolver implements Evolver
{
	PassiveSkillTree passiveSkillTree;

	public JeneticsEvolver(final PassiveSkillTree passiveSkillTree)
	{
		this.passiveSkillTree = passiveSkillTree;
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
		final float mutRate = (1f / length);

		final List<Integer> ids = passives.stream().map(new Function<PassiveSkill, Integer>() {
			@Override
			public Integer apply(final PassiveSkill t)
			{
				return t.getId();
			}
		}).collect(Collectors.toList());
		final Factory<Genotype<SkillGene>> gtf = new FactoryImplementation(ids, length);

		final ExecutorService exec = Executors.newFixedThreadPool(threads);

		final Engine<SkillGene, Integer> engine = Engine
				.builder(new FitnessFunction(passiveSkillTree, characterClass, evolutionContext.getCharacterEvaluator()), gtf)
				.populationSize(pop)
				.alterers(
						new Mutator<>(mutRate / 2),
						new NeighboringSkillMutator(mutRate * 2, passiveSkillTree),
						new SinglePointCrossover<>(.2))
				.executor(exec)
				.build();

		final EvolutionResult<SkillGene, Integer> result = engine.stream()
				.limit(evolutionContext.getGenerationLimit())
				.peek(new EvolutionStatistics())
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
