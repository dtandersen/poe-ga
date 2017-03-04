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
		allowedSkills = genes.get(0).skills;
	}

	public SkillChromosome(final List<Integer> passiveSkillIds, final int length)
	{
		this(SkillGene.seq(passiveSkillIds, length));
	}

	public SkillChromosome(final List<Integer> ids, final int length, final List<Integer> passiveSkillIds)
	{
		this(SkillGene.seq(ids, passiveSkillIds));
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

	public static Iterable<SkillChromosome> seq(final List<Integer> ids, final int length, final List<Integer> passiveSkillIds)
	{
		return MSeq.<SkillChromosome> ofLength(1)
				.fill(() -> new SkillChromosome(ids, length, passiveSkillIds))
				.toISeq();
		// final MSeq<SkillChromosome> seq = MSeq.<SkillChromosome> ofLength(length);
		//
		// return seq;
	}
}