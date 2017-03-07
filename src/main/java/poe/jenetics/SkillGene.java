package poe.jenetics;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jenetics.Gene;
import org.jenetics.util.ISeq;
import org.jenetics.util.MSeq;
import org.jenetics.util.RandomRegistry;

public class SkillGene implements Gene<Integer, SkillGene>,
		Comparable<SkillGene>
{
	private final List<Integer> allowedSkills;

	private final int passiveSkillId;

	public SkillGene(final List<Integer> allowedSkills)
	{
		this(
				allowedSkills,
				allowedSkills.get(RandomRegistry.getRandom().nextInt(allowedSkills.size())));
	}

	public SkillGene(final List<Integer> allowedSkills, final Integer passiveSkillId)
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
		return new SkillGene(getAllowedSkills());
	}

	@Override
	public SkillGene newInstance(final Integer value)
	{
		return new SkillGene(getAllowedSkills(), value);
	}

	public static ISeq<? extends SkillGene> seq(final List<Integer> passiveSkillIds, final int length)
	{
		return MSeq.<SkillGene> ofLength(length)
				.fill(() -> new SkillGene(passiveSkillIds))
				.toISeq();
	}

	public static ISeq<? extends SkillGene> seq(final List<Integer> allowedSkills, final List<Integer> actualSkills)
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

	public int getPassiveSkillId()
	{
		return passiveSkillId;
	}

	@Override
	public String toString()
	{
		return "" + getPassiveSkillId();
	}

	public List<Integer> getAllowedSkills()
	{
		return allowedSkills;
	}
}