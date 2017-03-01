package poe.repository.jenetics;

import java.util.List;
import org.jenetics.AbstractChromosome;
import org.jenetics.Chromosome;
import org.jenetics.util.ISeq;
import org.jenetics.util.MSeq;

@SuppressWarnings("serial")
public class SkillChromosome extends AbstractChromosome<SkillGene>
{
	private final List<Integer> skills;

	protected SkillChromosome(final ISeq<? extends SkillGene> genes)
	{
		super(genes);
		skills = genes.get(0).skills;
	}

	public SkillChromosome(final List<Integer> passiveSkillIds, final int length)
	{
		this(SkillGene.seq(passiveSkillIds, length));
	}

	@Override
	public Chromosome<SkillGene> newInstance(final ISeq<SkillGene> genes)
	{
		return new SkillChromosome(genes);
	}

	@Override
	public Chromosome<SkillGene> newInstance()
	{
		return new SkillChromosome(this.skills, length());
		// return of();
	}

	public static Iterable<SkillChromosome> seq(final List<Integer> ids, final int length)
	{
		return MSeq.<SkillChromosome> ofLength(length)
				.fill(() -> new SkillChromosome(ids, length))
				.toISeq();
		// return null;
	}

	// public static SkillChromosome of()
	// {
	// return new SkillChromosome(SkillGene.seq());
	// }

	// public static SkillChromosome of(final List<Integer> ids, final int i)
	// {
	// return new SkillChromosome(ids, i);
	// }
}