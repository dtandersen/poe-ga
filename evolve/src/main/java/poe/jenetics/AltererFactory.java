package poe.jenetics;

import io.jenetics.Alterer;
import io.jenetics.Mutator;
import io.jenetics.SwapMutator;
import io.jenetics.UniformCrossover;
import poe.repository.PassiveSkillTree;

public class AltererFactory
{
	private final PassiveSkillTree passiveSkillTree;

	public AltererFactory(final PassiveSkillTree passiveSkillTree)
	{
		this.passiveSkillTree = passiveSkillTree;
	}

	public Alterer<SkillGene, Float> createMutator(final String altererName, final float probability)
	{
		switch (altererName.toLowerCase())
		{
		case "null":
			return new NullMutator();
		case "crossover":
			// return new MultiPointCrossover<>(probability, 20);
			return new UniformCrossover<>(probability);
		case "singleneighbor":
			return new NeighboringSkillMutator5(probability, passiveSkillTree);
		case "multineighbor":
			return new NeighboringSkillMutator6(probability, passiveSkillTree);
		case "mutator":
			return new Mutator<>(probability);
		case "swap":
			return new SwapMutator<>(probability);
		}

		throw new RuntimeException();
	}
}
