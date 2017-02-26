package poe.app.evolve;

import java.util.function.Consumer;
import java.util.function.Function;
import org.jenetics.Genotype;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkillTree;

final class FitnessFunction implements Function<Genotype<SkillGene>, Integer>
{
	private final class ConsumerImplementation implements Consumer<SkillGene>
	{
		private final int id;

		private int count;

		public ConsumerImplementation(final int id)
		{
			this.id = id;
		}

		@Override
		public void accept(final SkillGene t)
		{
			if (t.passiveSkillId == id)
			{
				count++;
			}
		}
	}

	PassiveSkillTree pst;

	// private static Integer eval(final Genotype<SkillGene> gt)
	// {
	// return gt.getChromosome()
	// .as(SkillChromosome.class)
	// .bitCount();
	// }

	public FitnessFunction(final PassiveSkillTree pst)
	{
		this.pst = pst;
	}

	@Override
	public Integer apply(final Genotype<SkillGene> t)
	{
		final PassiveSkill root = pst.findByName("MARAUDER");
		final SkillChromosome c = t.getChromosome().as(SkillChromosome.class);
		final ConsumerImplementation x = new ConsumerImplementation(root.getId());
		c.forEach(x);
		return x.count;
	}
}