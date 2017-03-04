package poe.repository.jenetics;

import org.jenetics.Alterer;
import org.jenetics.Mutator;
import org.jenetics.SinglePointCrossover;
import poe.repository.PassiveSkillTree;

public class AltererFactory
{
	private final PassiveSkillTree passiveSkillTree;

	public AltererFactory(final PassiveSkillTree passiveSkillTree)
	{
		this.passiveSkillTree = passiveSkillTree;

	}

	public Alterer<SkillGene, Integer> createMutator(final AltererType mutatorType, final float probability)
	{
		switch (mutatorType)
		{
		case NULL:
			return new NullMutator();
		case CROSSOVER:
			return new SinglePointCrossover<>(probability);
		case NEIGHBOR:
			return new NeighboringSkillMutator(probability, passiveSkillTree);
		case MUTATOR:
			return new Mutator<>(probability);
		}

		throw new RuntimeException();
	}
}
