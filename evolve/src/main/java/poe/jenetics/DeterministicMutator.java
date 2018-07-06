package poe.jenetics;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import io.jenetics.Chromosome;
import io.jenetics.Mutator;
import io.jenetics.MutatorResult;
import io.jenetics.util.MSeq;

public class DeterministicMutator extends Mutator<SkillGene, Float>
{
	private final List<List<Integer>> testingGenes;

	private int count;

	public DeterministicMutator(final List<List<Integer>> testingGenes)
	{
		super(1);
		this.testingGenes = testingGenes;
	}

	@Override
	protected MutatorResult<Chromosome<SkillGene>> mutate(final Chromosome<SkillGene> chromosome, final double p, final Random random)
	{
		final MSeq<SkillGene> genes = chromosome.toSeq().copy();
		final List<Integer> testGenes = testingGenes.get(count++);
		System.out.println("gene before=" + genes);

		final int count = (int)IntStream.range(0, genes.length())
				.peek(i -> genes.set(i, genes.get(i).newInstance(testGenes.get(i))))
				.count();

		System.out.println("gene after=" + genes);

		return MutatorResult.of(
				chromosome.newInstance(genes.toISeq()),
				count);
	}

	@Override
	public String toString()
	{
		return "DeterministicMutator " + testingGenes.toString();
	}
}
