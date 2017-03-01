package poe.repository.jenetics;

import java.util.function.Consumer;
import org.jenetics.Genotype;
import org.jenetics.Phenotype;
import org.jenetics.engine.EvolutionResult;

class EvolutionStatistics implements Consumer<EvolutionResult<SkillGene, Integer>>
{
	Phenotype<SkillGene, Integer> best = null;

	Genotype<SkillGene> best2 = null;

	@Override
	public void accept(final EvolutionResult<SkillGene, Integer> result)
	{
		if (best == null || best.compareTo(result.getBestPhenotype()) < 0)
		{
			best = result.getBestPhenotype();
			best2 = result.getBestPhenotype().getGenotype();
			final long oldest = result.getWorstPhenotype().getAge(result.getGeneration());
			System.out.println("Generation: " + result.getGeneration() + ", Fitness: " + result.getBestFitness() + ", Age: " + oldest);
		}
	}
}
