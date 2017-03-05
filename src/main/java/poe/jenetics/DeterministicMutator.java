package poe.jenetics;

import java.util.List;
import java.util.stream.IntStream;
import org.jenetics.Mutator;
import org.jenetics.util.MSeq;

public class DeterministicMutator extends Mutator<SkillGene, Integer>
{
	private final List<List<Integer>> testingGenes;

	int count;

	public DeterministicMutator(final List<List<Integer>> testingGenes)
	{
		super(1);
		this.testingGenes = testingGenes;
	}

	@Override
	protected int mutate(final MSeq<SkillGene> genes, final double p)
	{
		final List<Integer> testGenes = testingGenes.get(count++);
		final int length = genes.length();
		System.out.println("gene length=" + length);
		System.out.println("gene before=" + genes);
		final int x = (int)IntStream.range(0, length)
				.peek(i -> genes.set(i, genes.get(i).newInstance(testGenes.get(i))))
				.count();

		System.out.println("gene after=" + genes);

		return x;

	}

	@Override
	public String toString()
	{
		return "DeterministicMutator " + testingGenes.toString();
	}
}
