package poe.jenetics;

import java.util.Random;
import io.jenetics.Chromosome;
import io.jenetics.Mutator;
import io.jenetics.MutatorResult;
import io.jenetics.internal.math.probability;
import io.jenetics.util.ISeq;
import poe.repository.PassiveSkillTree;

public class NeighboringSkillMutator6 extends Mutator<SkillGene, Float>
{
	private final PassiveSkillTree pst;

	public NeighboringSkillMutator6(final float probability, final PassiveSkillTree pst)
	{
		super(probability);
		this.pst = pst;
	}

	@Override
	protected MutatorResult<Chromosome<SkillGene>> mutate(
			final Chromosome<SkillGene> chromosome,
			final double p,
			final Random random)
	{
		// if (p > random.nextFloat()) { return MutatorResult.of(chromosome); }

		final int P = probability.toInt(p);
		final Neighborizer neighborizer = new Neighborizer(pst, chromosome.getGene(random.nextInt(chromosome.length())).getAllele(), random);
		final ISeq<MutatorResult<SkillGene>> result = chromosome.toSeq()
				.map(gene -> random.nextInt() < P
						? MutatorResult.of(replaceWithNeighbor(gene, random, neighborizer.next(gene)), 1)
						: MutatorResult.of(gene));

		return MutatorResult.of(
				chromosome.newInstance(result.map(MutatorResult::getResult)),
				result.stream().mapToInt(MutatorResult::getMutations).sum());
	}

	private SkillGene replaceWithNeighbor(final SkillGene gene, final Random random, final SkillGene neighbor)
	{
		return gene.newInstance(neighbor.getAllele());
	}

	static class Neighborizer
	{
		private int currentSkillId;

		private final PassiveSkillTree pst;

		private final Random random;

		public Neighborizer(final PassiveSkillTree pst, final int passiveSkillId, final Random random)
		{
			this.pst = pst;
			this.currentSkillId = passiveSkillId;
			this.random = random;
		}

		public SkillGene next(final SkillGene gene)
		{
			currentSkillId = pst.randomNeighbor(currentSkillId, random);
			return gene.newInstance(currentSkillId);
		}
	}
	// @Override
	// public AltererResult<SkillGene, Float> alter(final
	// Seq<Phenotype<SkillGene, Float>> population, final long generation)
	// {
	// final IntRef alterations = new IntRef(0);
	//
	// for (int i = 0; i < population.size(); ++i)
	// {
	// final Phenotype<SkillGene, Float> pt = population.get(i);
	//
	// final Genotype<SkillGene> gt = pt.getGenotype();
	// final Genotype<SkillGene> mgt = mutate(gt, alterations);
	// final Phenotype<SkillGene, Float> mpt = pt.newInstance(mgt, generation);
	// // population.set(i, mpt);
	// }
	//
	// // return alterations.value;
	// return AltererResult.of(population.asISeq(), alterations.value);
	// }
	// @Override
	// public int alter(final Population<SkillGene, Float> population, final
	// long generation)
	// {
	// final IntRef alterations = new IntRef(0);
	//
	// for (int i = 0; i < population.size(); ++i)
	// {
	// final Phenotype<SkillGene, Float> pt = population.get(i);
	//
	// final Genotype<SkillGene> gt = pt.getGenotype();
	// final Genotype<SkillGene> mgt = mutate(gt, alterations);
	// final Phenotype<SkillGene, Float> mpt = pt.newInstance(mgt, generation);
	// population.set(i, mpt);
	// }
	//
	// return alterations.value;
	// }
	//
	// protected Genotype<SkillGene> mutate(
	// final Genotype<SkillGene> genotype,
	// final IntRef alterations)
	// {
	// final MSeq<Chromosome<SkillGene>> chromosomes = genotype.toSeq().copy();
	//
	// alterations.value += IntStream.range(0, chromosomes.size())
	// .map(i -> mutate(chromosomes, i))
	// .sum();
	//
	// return Genotype.of(chromosomes);
	// }
	//
	// protected int mutate(final MSeq<Chromosome<SkillGene>> chromosomes, final
	// int chromosomeIndex)
	// {
	// final Chromosome<SkillGene> chromosome =
	// chromosomes.get(chromosomeIndex);
	// final MSeq<SkillGene> genes = chromosome.toSeq().copy();
	//
	// final AtomicInteger atom = new
	// AtomicInteger(randomPassiveSkillId(chromosomes));
	// final int mutations = mutate(genes, _probability, chromosomes, atom);
	// if (mutations > 0)
	// {
	// chromosomes.set(chromosomeIndex, chromosome.newInstance(genes.toISeq()));
	// }
	//
	// return mutations;
	// }
	//
	// protected int mutate(final MSeq<SkillGene> genes, final double p, final
	// MSeq<Chromosome<SkillGene>> chromosomes, final AtomicInteger
	// passiveSkillId)
	// {
	// final Random random = RandomRegistry.getRandom();
	// // final AtomicInteger atom;
	// // atom = new AtomicInteger(randomPassiveSkillId(chromosomes));
	//
	// // final float p2 = random.nextFloat();
	// // final float p2 = (random.nextFloat() + random.nextFloat()) / 2f;
	// return (int)indexes(RandomRegistry.getRandom(), genes.length(), p)
	// .peek(i -> {
	// final int randomNeighbor = passiveSkillId.get();
	// final Integer newNeighbor = pst.randomNeighbor(randomNeighbor, random);
	// genes.set(i, genes.get(i).newInstance(newNeighbor));
	// passiveSkillId.set(newNeighbor);
	// })
	// .count();
	// }

	// private int randomPassiveSkillId(final MSeq<Chromosome<SkillGene>>
	// chromosomes)
	// {
	// final int chromosomeIndex =
	// RandomRegistry.getRandom().nextInt(chromosomes.length());
	// final Chromosome<SkillGene> chromosome =
	// chromosomes.get(chromosomeIndex);
	// final int geneIndex =
	// RandomRegistry.getRandom().nextInt(chromosome.length());
	// final SkillGene gene = chromosome.getGene(geneIndex);
	//
	// return gene.getAllele();
	// }
}
