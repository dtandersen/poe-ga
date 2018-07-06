package poe.jenetics;

import static io.jenetics.internal.math.random.indexes;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import io.jenetics.Chromosome;
import io.jenetics.Mutator;
import io.jenetics.MutatorResult;
import io.jenetics.util.MSeq;
import io.jenetics.util.RandomRegistry;
import poe.repository.PassiveSkillTree;

public class NeighboringSkillMutator2 extends Mutator<SkillGene, Float>
{
	private final PassiveSkillTree pst;

	public NeighboringSkillMutator2(final float mutationRate, final PassiveSkillTree pst)
	{
		super(mutationRate);
		this.pst = pst;
	}

	@Override
	protected MutatorResult<Chromosome<SkillGene>> mutate(final Chromosome<SkillGene> chromosome, final double p, final Random random)
	{
		final MSeq<SkillGene> genes = chromosome.toSeq().copy();
		// final Random random = RandomRegistry.getRandom();
		// final List<Integer> original =
		// genes.asList().stream().map(SkillGene::getAllele).collect(Collectors.toList());
		// final Set<Integer> neighbors = pst.neighbors(original);
		// neighbors.removeAll(original);

		// final Integer startPoint =
		// original.get(random.nextInt(original.size()));
		// final Set<Integer> walk = pst.randomWalk(startPoint, 10, random);

		final AtomicInteger atom = new AtomicInteger(-1);
		final int count = (int)indexes(RandomRegistry.getRandom(), genes.length(), p)
				.peek(i -> {
					if (atom.get() == -1)
					{
						// final Random random2 = RandomRegistry.getRandom();
						// if (random2.nextFloat() < .5)
						// {
						// atom.set(genes.get(random2.nextInt(genes.length())).getAllele());
						// }
						// else
						// {
						atom.set(genes.get(i).getAllele());
						// }
					}
					final int randomNeighbor = atom.get();
					final Integer newNeighbor = pst.randomNeighbor(randomNeighbor, random);
					genes.set(i, genes.get(i).newInstance(newNeighbor));
					atom.set(newNeighbor);
				})
				.count();

		final MutatorResult<Chromosome<SkillGene>> result = MutatorResult.of(
				chromosome.newInstance(genes.toISeq()),
				count);
		return result;
		// return count;

	}
	//
	// private int randomNeighbor(final Set<Integer> neighbors)
	// {
	// final int randomIndex =
	// RandomRegistry.getRandom().nextInt(neighbors.size());
	// final int val = randomSetElement(neighbors, randomIndex);
	// return val;
	// }
	//
	// private int randomSetElement(final Set<Integer> neighbors, final int x)
	// {
	// int i = 0;
	// for (final int obj : neighbors)
	// {
	// if (i == x) { return obj; }
	// i++;
	// }
	//
	// throw new RuntimeException();
	// }

	//
	// @Override
	// public int alter(final Population<SkillGene, Integer> population, final
	// long generation)
	// {
	// final Consumer<? super Phenotype<SkillGene, Integer>> action = new
	// PhenotypeConsumer(mutationRate, pst);
	// // for (final Object x : population.stream())
	// // {
	// // return 0;
	// // }
	// population.forEach(action);
	//
	// return 0;
	// }
	//
	// private final class PhenotypeConsumer implements
	// Consumer<Phenotype<SkillGene, Integer>>
	// {
	// private final float mutationRate;
	//
	// private final PassiveSkillTree pst;
	//
	// public PhenotypeConsumer(final float mutationRate, final PassiveSkillTree
	// pst)
	// {
	// this.mutationRate = mutationRate;
	// this.pst = pst;
	// }
	//
	// @Override
	// public void accept(final Phenotype<SkillGene, Integer> t)
	// {
	// t.getGenotype().forEach(new ChromeosomeConsumer(mutationRate, pst));
	// }
	// }
	//
	// private final class ChromeosomeConsumer implements
	// Consumer<Chromosome<SkillGene>>
	// {
	// private final float mutationRate;
	//
	// private final PassiveSkillTree pst;
	//
	// public ChromeosomeConsumer(final float mutationRate, final
	// PassiveSkillTree pst)
	// {
	// this.mutationRate = mutationRate;
	// this.pst = pst;
	// }
	//
	// @Override
	// public void accept(final Chromosome<SkillGene> chromosome)
	// {
	// final Random random = RandomRegistry.getRandom();
	// if (random.nextFloat() >= mutationRate) { return; }
	//
	// final Set<Integer> neighbors =
	// pst.neighbors(chromosome.stream().map(SkillGene::getPassiveSkillId).collect(Collectors.toList()));
	//
	// final int index = random.nextInt(chromosome.length());
	// final SkillGene gene = chromosome.getGene(index);
	// chromosome.se
	// }
	// }
}
