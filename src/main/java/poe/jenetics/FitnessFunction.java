package poe.jenetics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.jenetics.Genotype;
import org.jenetics.util.ISeq;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PoeCharacter;
import poe.evaluator.CharacterEvaluator;
import poe.evaluator.ExpressionContextAdapter;
import poe.repository.PassiveSkillTree;

public class FitnessFunction implements Function<Genotype<SkillGene>, Integer>
{
	private final PassiveSkillTree passiveSkillTree;

	private final CharacterClass characterClass;

	private final CharacterEvaluator characterEvaluator;

	public FitnessFunction(
			final PassiveSkillTree passiveSkillTree,
			final CharacterClass characterClass,
			final CharacterEvaluator characterEvaluator)
	{
		this.passiveSkillTree = passiveSkillTree;
		this.characterClass = characterClass;
		this.characterEvaluator = characterEvaluator;
	}

	@Override
	public Integer apply(final Genotype<SkillGene> genotype)
	{
		final PassiveSkill root = passiveSkillTree.findByName(characterClass.getRootPassiveSkillName());
		final PoeCharacter character = new PoeCharacter(characterClass);
		character.addPassiveSkill(root);
		final SkillChromosome c = genotype.getChromosome().as(SkillChromosome.class);
		final ISeq<SkillGene> seq = c.toSeq();
		final List<PassiveSkill> passives = new ArrayList<>();
		for (final SkillGene gene : seq)
		{
			final PassiveSkill find = passiveSkillTree.find(gene.getPassiveSkillId());
			passives.add(find);
		}
		character.sneakyAdd(passives);

		final ExpressionContextAdapter context = new ExpressionContextAdapter(character);
		final int fitness = characterEvaluator.evaluate(context).getFitness();

		return fitness;
	}
}