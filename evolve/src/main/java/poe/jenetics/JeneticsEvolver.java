package poe.jenetics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import io.jenetics.Alterer;
import io.jenetics.BoltzmannSelector;
import io.jenetics.Chromosome;
import io.jenetics.EliteSelector;
import io.jenetics.Genotype;
import io.jenetics.PublicCompositeAlterer;
import io.jenetics.StochasticUniversalSelector;
import io.jenetics.UniformCrossover;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.EvolutionStreamable;
import io.jenetics.engine.Limits;
import io.jenetics.ext.engine.CyclicEngine;
import io.jenetics.util.Factory;
import poe.command.model.AltererConfig;
import poe.entity.CharacterClass;
import poe.entity.CharacterInstance.PoeCharacterEditor;
import poe.entity.PassiveSkill;
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
		final int threads = evolutionContext.getThreads();

		final List<Integer> ids = passives.stream().map(new Function<PassiveSkill, Integer>() {
			@Override
			public Integer apply(final PassiveSkill t)
			{
				return t.getId();
			}
		}).collect(Collectors.toList());
		final Factory<Genotype<SkillGene>> gtf = new FactoryImplementation(
				ids,
				evolutionContext.getGenes(),
				evolutionContext.getChromosomes());
		// final Factory<Genotype<SkillGene>> gtf = new
		// RandomWalkSkillGeneFactory(passiveSkillTree, characterClass, length,
		// ids);

		final ExecutorService exec = Executors.newFixedThreadPool(threads);

		final FitnessFunction fitnessFunction = new FitnessFunction(
				passiveSkillTree,
				characterClass,
				evolutionContext.getCharacterEvaluator(),
				evolutionContext.getItems(),
				evolutionContext.getLevel(),
				evolutionContext.getSkillPoints());

		final List<Alterer<SkillGene, Float>> alterers2 = new ArrayList<>();
		final List<AltererConfig> altererConfig2 = evolutionContext.getAltererConfig();
		for (final AltererConfig altererConfig : altererConfig2)
		{
			alterers2.add(altererFactory.createMutator(altererConfig.getAltererTypeName().toLowerCase(), altererConfig.getProbability()));
		}

		final Alterer<SkillGene, Float>[] altererArray = alterers2.toArray(new Alterer[0]);
		// final EvolutionStreamable<SkillGene, Float> engine = engine2(pop,
		// gtf, fitnessFunction, altererArray);
		final EvolutionStreamable<SkillGene, Float> engine = engine3(pop, gtf, fitnessFunction, altererArray, passiveSkillTree);
		// final Engine<SkillGene, Float> engine = engine1(pop, gtf,
		// fitnessFunction, altererArray);

		final EvolutionResult<SkillGene, Float> result = engine.stream()
				.limit(evolutionContext.getGenerationLimit())
				.peek(new EvolutionWatcher(new CharacterUpdateCallback(poeEvolutionResult), passiveSkillTree, evolutionContext.getCharacterClass(), evolutionContext.getCharacterEvaluator(), evolutionContext.getLevel(), evolutionContext.getItems()))
				.collect(EvolutionResult.toBestEvolutionResult());

		final Chromosome<SkillGene> chromosome = result.getBestPhenotype().getGenotype().getChromosome(0);

		final PoeCharacterEditor character = new PoeCharacterEditor(characterClass, evolutionContext.getLevel());
		character.addPassiveSkill(passiveSkillTree.findByName(characterClass.getRootPassiveSkillName()));
		final List<PassiveSkill> myPassives = new ArrayList<>();
		for (final SkillGene g : chromosome)
		{
			final PassiveSkill passiveSkill = passiveSkillTree.find(g.getAllele());
			myPassives.add(passiveSkill);
		}
		character.sneakyAdd(myPassives);

		poeEvolutionResult.setCharacter(character);
		poeEvolutionResult.setGenerations(result.getTotalGenerations());
		poeEvolutionResult.setFitness(result.getBestFitness());
	}

	private Engine<SkillGene, Float> engine1(final int pop, final Factory<Genotype<SkillGene>> gtf, final FitnessFunction fitnessFunction, final Alterer<SkillGene, Float>[] altererArray)
	{
		final Engine<SkillGene, Float> engine = Engine
				.builder(fitnessFunction, gtf)
				.populationSize(pop)
				.alterers(PublicCompositeAlterer.of(altererArray))
				// .selector(new TournamentSelector<SkillGene, Float>())
				// .selector(new StochasticUniversalSelector<SkillGene,
				// Float>())
				.selector(new EliteSelector<>((5), new StochasticUniversalSelector<SkillGene, Float>()))
				// .selector(new EliteSelector<>((int)(pop * .01), new
				// BoltzmannSelector<SkillGene, Float>(1)))
				// .maximalPhenotypeAge(50)
				// .fitnessScaler(f -> f * .9f + 10000f)
				// .executor(exec)
				.build();
		return engine;
	}

	private EvolutionStreamable<SkillGene, Float> engine2(final int pop, final Factory<Genotype<SkillGene>> gtf, final FitnessFunction fitnessFunction, final Alterer<SkillGene, Float>[] altererArray)
	{
		final Engine<SkillGene, Float> engine1 = Engine
				.builder(fitnessFunction, gtf)
				.populationSize(pop)
				.alterers(PublicCompositeAlterer.of(altererArray))
				// .selector(new TournamentSelector<SkillGene, Float>())
				// .selector(new StochasticUniversalSelector<SkillGene,
				// Float>())
				.selector(new EliteSelector<>((5), new StochasticUniversalSelector<SkillGene, Float>()))
				// .selector(new EliteSelector<>((int)(pop * .01), new
				// BoltzmannSelector<SkillGene, Float>(1)))
				// .maximalPhenotypeAge(50)
				// .fitnessScaler(f -> f * .9f + 10000f)
				// .executor(exec)
				.build();
		final Engine<SkillGene, Float> engine2 = Engine
				.builder(fitnessFunction, gtf)
				.populationSize(pop)
				.alterers(PublicCompositeAlterer.of(altererArray))
				// .selector(new TournamentSelector<SkillGene, Float>())
				// .selector(new StochasticUniversalSelector<SkillGene,
				// Float>())
				.selector(new BoltzmannSelector<SkillGene, Float>(2))
				// .selector(new EliteSelector<>((3), new
				// BoltzmannSelector<SkillGene, Float>(2)))
				// .selector(new EliteSelector<>((int)(pop * .01), new
				// BoltzmannSelector<SkillGene, Float>(1)))
				// .maximalPhenotypeAge(50)
				// .fitnessScaler(f -> f * .9f + 10000f)
				// .executor(exec)
				.build();
		return CyclicEngine.of(
				engine2.limit(() -> Limits.bySteadyFitness(30)));
		// return CyclicEngine.of(
		// engine1.limit(50),
		// engine2.limit(() -> Limits.bySteadyFitness(30)));
	}

	private EvolutionStreamable<SkillGene, Float> engine3(final int pop, final Factory<Genotype<SkillGene>> gtf, final FitnessFunction fitnessFunction, final Alterer<SkillGene, Float>[] altererArray, final PassiveSkillTree pst)
	{
		final Engine<SkillGene, Float> engine1 = Engine
				.builder(fitnessFunction, gtf)
				.populationSize(pop)
				.alterers(new NeighboringSkillMutator6(.20f, pst))
				// .selector(new TournamentSelector<SkillGene, Float>())
				// .selector(new StochasticUniversalSelector<SkillGene,
				// Float>())
				// .selector(new EliteSelector<>((5), new
				// StochasticUniversalSelector<SkillGene, Float>()))
				.selector(new EliteSelector<>((3), new StochasticUniversalSelector<SkillGene, Float>()))
				// .selector(new EliteSelector<>((int)(pop * .01), new
				// BoltzmannSelector<SkillGene, Float>(1)))
				// .maximalPhenotypeAge(50)
				// .fitnessScaler(f -> f * .9f + 10000f)
				// .executor(exec)
				.build();
		final Engine<SkillGene, Float> engine2 = Engine
				.builder(fitnessFunction, gtf)
				.populationSize(pop)
				.alterers(
						new UniformCrossover<SkillGene, Float>(.005f),
						new NeighboringSkillMutator5(.01f, pst))
				// .alterers(PublicCompositeAlterer.of(altererArray))
				// .selector(new TournamentSelector<SkillGene, Float>())
				// .selector(new StochasticUniversalSelector<SkillGene,
				// Float>())
				.selector(new EliteSelector<>((3), new BoltzmannSelector<SkillGene, Float>(2)))
				// .selector(new EliteSelector<>((3), new
				// BoltzmannSelector<SkillGene, Float>(2)))
				// .selector(new EliteSelector<>((int)(pop * .01), new
				// BoltzmannSelector<SkillGene, Float>(1)))
				// .maximalPhenotypeAge(50)
				// .fitnessScaler(f -> f * .9f + 10000f)
				// .executor(exec)
				.build();
		return CyclicEngine.of(
				engine1.limit(() -> Limits.bySteadyFitness(10)),
				engine2.limit(() -> Limits.bySteadyFitness(30)));
		// return CyclicEngine.of(
		// engine1.limit(50),
		// engine2.limit(() -> Limits.bySteadyFitness(30)));
	}
}
