package poe.jenetics;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jenetics.Gene;
import org.jenetics.util.ISeq;
import org.jenetics.util.MSeq;
import org.jenetics.util.RandomRegistry;

public class SkillGene implements Gene<Integer, SkillGene>,
		Comparable<SkillGene>
{
	private final Set<Integer> allowedSkills;

	private final int passiveSkillId;

	public SkillGene(final Set<Integer> allowedSkills)
	{
		this.allowedSkills = allowedSkills;
		this.passiveSkillId = randomSetElement(allowedSkills);
	}

	int randomSetElement(final Set<Integer> set)
	{
		final int index = RandomRegistry.getRandom().nextInt(set.size());
		return SetUtil.randomElement(set, index);
	}

	public SkillGene(final Set<Integer> allowedSkills, final Integer passiveSkillId)
	{
		this.allowedSkills = allowedSkills;
		this.passiveSkillId = passiveSkillId;
	}

	@Override
	public boolean isValid()
	{
		return allowedSkills.contains(passiveSkillId);
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
		return new SkillGene(allowedSkills);
	}

	@Override
	public SkillGene newInstance(final Integer value)
	{
		return new SkillGene(allowedSkills, value);
	}

	public static ISeq<? extends SkillGene> seq(final Set<Integer> passiveSkillIds, final int length)
	{
		return MSeq.<SkillGene> ofLength(length)
				.fill(() -> new SkillGene(passiveSkillIds))
				.toISeq();
	}

	public static ISeq<? extends SkillGene> seq(final Set<Integer> allowedSkills, final List<Integer> actualSkills)
	{
		final MSeq<SkillGene> ofLength = MSeq.<SkillGene> ofLength(actualSkills.size());
		ofLength.setAll(actualSkills.stream()
				.map(new Function<Integer, SkillGene>() {
					@Override
					public SkillGene apply(final Integer t)
					{
						return new SkillGene(allowedSkills, t);
					}
				})
				.collect(Collectors.toList()));
		return ofLength.toISeq();
	}

	@Override
	public String toString()
	{
		return "" + getAllele();
	}

	public Set<Integer> getAllowedSkills()
	{
		return allowedSkills;
	}
}