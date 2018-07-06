package poe.jenetics;

import java.util.Random;
import io.jenetics.Chromosome;
import io.jenetics.Mutator;
import io.jenetics.MutatorResult;

public class NullMutator extends Mutator<SkillGene, Float>
{
	public NullMutator()
	{
		super(0);
	}

	@Override
	protected MutatorResult<Chromosome<SkillGene>> mutate(final Chromosome<SkillGene> chromosome, final double p, final Random random)
	{
		return MutatorResult.of(chromosome);
	}
}
