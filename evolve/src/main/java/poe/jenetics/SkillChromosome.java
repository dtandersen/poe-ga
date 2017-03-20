package poe.jenetics;

import java.util.List;
import org.jenetics.AbstractChromosome;
import org.jenetics.Chromosome;
import org.jenetics.util.ISeq;
import org.jenetics.util.MSeq;

@SuppressWarnings("serial")
public class SkillChromosome extends AbstractChromosome<SkillGene>
{
	private final List<Integer> allowedSkills;

	protected SkillChromosome(final ISeq<? extends SkillGene> genes)
	{
		super(genes);
		allowedSkills = genes.get(0).getAllowedSkills();
	}

	public SkillChromosome(final List<Integer> allowedSkills, final int length)
	{
		this(SkillGene.seq(allowedSkills, length));
	}

	public SkillChromosome(final List<Integer> allowedSkills, final List<Integer> actualSkills)
	{
		this(SkillGene.seq(allowedSkills, actualSkills));
	}

	@Override
	public Chromosome<SkillGene> newInstance(final ISeq<SkillGene> genes)
	{
		return new SkillChromosome(genes);
	}

	@Override
	public Chromosome<SkillGene> newInstance()
	{
		return new SkillChromosome(this.allowedSkills, length());
	}

	public static Iterable<SkillChromosome> seq(final List<Integer> ids, final int length)
	{
		return MSeq.<SkillChromosome> ofLength(1)
				.fill(() -> new SkillChromosome(ids, length))
				.toISeq();
	}

	public static Iterable<SkillChromosome> seq(
			final List<Integer> ids,
			final int length,
			final int chromosomeCount)
	{
		return MSeq.<SkillChromosome> ofLength(chromosomeCount)
				.fill(() -> new SkillChromosome(ids, length))
				.toISeq();
	}

	public static Iterable<SkillChromosome> seq(final List<Integer> allowedSkills, final List<Integer> actualSkills)
	{
		return MSeq.<SkillChromosome> ofLength(1)
				.fill(() -> new SkillChromosome(allowedSkills, actualSkills))
				.toISeq();
	}
}