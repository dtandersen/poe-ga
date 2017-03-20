package poe.jenetics;

import java.util.List;
import org.jenetics.Genotype;
import org.jenetics.util.Factory;

final class FactoryImplementation implements Factory<Genotype<SkillGene>>
{
	private final List<Integer> allowedSkills;

	private final int genes;

	private final int chromosomes;

	FactoryImplementation(final List<Integer> ids, final int genes, final int chromosomes)
	{
		this.allowedSkills = ids;
		this.genes = genes;
		this.chromosomes = chromosomes;
	}

	@Override
	public Genotype<SkillGene> newInstance()
	{
		return Genotype.of(SkillChromosome.seq(allowedSkills, genes, chromosomes));
	}
}
