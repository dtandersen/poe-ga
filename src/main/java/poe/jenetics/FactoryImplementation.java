package poe.jenetics;

import java.util.List;
import org.jenetics.Genotype;
import org.jenetics.util.Factory;

final class FactoryImplementation implements Factory<Genotype<SkillGene>>
{
	private final List<Integer> ids;

	private final int length;

	FactoryImplementation(List<Integer> ids, int length)
	{
		this.ids = ids;
		this.length = length;
	}

	@Override
	public Genotype<SkillGene> newInstance()
	{
		return Genotype.of(SkillChromosome.seq(ids, length));
	}
}