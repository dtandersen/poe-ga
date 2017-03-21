package poe.jenetics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jenetics.Genotype;
import org.jenetics.util.Factory;

final class FactoryImplementation implements Factory<Genotype<SkillGene>>
{
	private final Set<Integer> allowedSkills;

	private final int genes;

	private final int chromosomes;

	FactoryImplementation(final List<Integer> ids, final int genes, final int chromosomes)
	{
		this.allowedSkills = new HashSet<>(ids);
		this.genes = genes;
		this.chromosomes = chromosomes;
	}

	@Override
	public Genotype<SkillGene> newInstance()
	{
		return Genotype.of(SkillChromosome.seq(allowedSkills, genes, chromosomes));
	}
}
