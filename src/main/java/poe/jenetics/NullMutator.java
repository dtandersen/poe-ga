package poe.jenetics;

import org.jenetics.Mutator;
import org.jenetics.util.MSeq;

public class NullMutator extends Mutator<SkillGene, Integer>
{
	public NullMutator()
	{
		super(0);
	}

	@Override
	protected int mutate(final MSeq<SkillGene> genes, final double p)
	{
		return 0;
	}
}
