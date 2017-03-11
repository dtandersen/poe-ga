package poe.jenetics;

import java.util.List;
import org.jenetics.Genotype;
import org.jenetics.util.Factory;

final class FactoryImplementation implements Factory<Genotype<SkillGene>>
{
	private final List<Integer> allowedSkills;

	private final int length;

	FactoryImplementation(final List<Integer> ids, final int length)
	{
		this.allowedSkills = ids;
		this.length = length;
	}

	@Override
	public Genotype<SkillGene> newInstance()
	{
		return Genotype.of(SkillChromosome.seq(allowedSkills, length));
	}
}