package poe.jenetics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.jenetics.Genotype;
import org.jenetics.util.ISeq;
import poe.entity.CharacterClass;
import poe.entity.CharacterItem;
import poe.entity.PassiveSkill;
import poe.entity.PoeCharacter;
import poe.evaluator.CharacterEvaluator;
import poe.evaluator.CharacterEvaluatorContextAdapter;
import poe.repository.PassiveSkillTree;

public class FitnessFunction implements Function<Genotype<SkillGene>, Float>
{
	private final PassiveSkillTree passiveSkillTree;

	private final CharacterClass characterClass;

	private final CharacterEvaluator characterEvaluator;

	private final List<CharacterItem> items;

	private final int level;

	public FitnessFunction(
			final PassiveSkillTree passiveSkillTree,
			final CharacterClass characterClass,
			final CharacterEvaluator characterEvaluator,
			final List<CharacterItem> items,
			final int level)
	{
		this.passiveSkillTree = passiveSkillTree;
		this.characterClass = characterClass;
		this.characterEvaluator = characterEvaluator;
		this.items = items;
		this.level = level;
	}

	@Override
	public Float apply(final Genotype<SkillGene> genotype)
	{
		final PassiveSkill root = passiveSkillTree.findByName(characterClass.getRootPassiveSkillName());
		final PoeCharacter character = new PoeCharacter(characterClass);
		character.setLevel(level);
		character.setItems(items);
		character.addPassiveSkill(root);
		for (int i = 0; i < genotype.length(); i++)
		{
			final SkillChromosome c = genotype.getChromosome(i).as(SkillChromosome.class);
			// final SkillChromosome c = genotype.getChromosome().as(SkillChromosome.class);
			final ISeq<SkillGene> seq = c.toSeq();
			final List<PassiveSkill> passives = new ArrayList<>();
			for (final SkillGene gene : seq)
			{
				final PassiveSkill find = passiveSkillTree.find(gene.getAllele());
				passives.add(find);
			}
			character.sneakyAdd(passives);
		}
		final CharacterEvaluatorContextAdapter context = new CharacterEvaluatorContextAdapter(character);
		final float fitness = characterEvaluator.evaluate(context).getFitness();

		return fitness;
	}
}