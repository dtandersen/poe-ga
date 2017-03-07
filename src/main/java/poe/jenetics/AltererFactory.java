package poe.jenetics;

import org.jenetics.Alterer;
import org.jenetics.Mutator;
import org.jenetics.SinglePointCrossover;
import org.jenetics.SwapMutator;
import poe.repository.PassiveSkillTree;

public class AltererFactory
{
	private final PassiveSkillTree passiveSkillTree;

	public AltererFactory(final PassiveSkillTree passiveSkillTree)
	{
		this.passiveSkillTree = passiveSkillTree;
	}

	public Alterer<SkillGene, Integer> createMutator(final String altererName, final float probability)
	{
		switch (altererName.toLowerCase())
		{
		case "null":
			return new NullMutator();
		case "crossover":
			return new SinglePointCrossover<>(probability);
		case "neighbor":
			return new NeighboringSkillMutator(probability, passiveSkillTree);
		case "mutator":
			return new Mutator<>(probability);
		case "swap":
			return new SwapMutator<>(probability);
		}

		throw new RuntimeException();
	}
}
