package poe.app.evolve;

import java.util.ArrayList;
import java.util.List;
import org.jenetics.Gene;
import org.jenetics.util.ISeq;
import org.jenetics.util.MSeq;
import org.jenetics.util.RandomRegistry;

public class SkillGene implements Gene<Integer, SkillGene>,
		Comparable<SkillGene>
{
	List<Integer> skills = new ArrayList<>();

	final int passiveSkillId;

	public SkillGene(final List<Integer> passiveSkillIds)
	{
		this.skills = passiveSkillIds;
		final int randomIndex = RandomRegistry.getRandom().nextInt(passiveSkillIds.size());
		this.passiveSkillId = passiveSkillIds.get(randomIndex);
	}

	public SkillGene(final List<Integer> skills, final Integer value)
	{
		this.skills = skills;
		this.passiveSkillId = value;
	}

	@Override
	public boolean isValid()
	{
		return skills.contains(passiveSkillId);
	}

	@Override
	public int compareTo(final SkillGene o)
	{
		return o.passiveSkillId - passiveSkillId;
	}

	@Override
	public Integer getAllele()
	{
		return passiveSkillId;
	}

	@Override
	public SkillGene newInstance()
	{
		return new SkillGene(skills);
	}

	@Override
	public SkillGene newInstance(final Integer value)
	{
		return new SkillGene(skills, value);
	}

	public static ISeq<? extends SkillGene> seq(final List<Integer> passiveSkillIds, final int length)
	{
		return MSeq.<SkillGene> ofLength(length)
				.fill(() -> new SkillGene(passiveSkillIds))
				.toISeq();
	}

	@Override
	public String toString()
	{
		return "" + passiveSkillId;
	}
}