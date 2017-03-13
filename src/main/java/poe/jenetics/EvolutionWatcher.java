package poe.jenetics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.jenetics.Genotype;
import org.jenetics.Phenotype;
import org.jenetics.engine.EvolutionResult;
import org.jenetics.util.ISeq;
import poe.command.PureImmutableCharacter.ImmutableCharacterBuilder;
import poe.command.model.EvolutionStatus.EvolutionStatusBuilder;
import poe.command.model.ImmutableCharacter;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PoeCharacter;
import poe.evaluator.CharacterEvaluator;
import poe.evaluator.CharacterExpressionContextAdapter;
import poe.repository.PassiveSkillTree;

class EvolutionWatcher implements Consumer<EvolutionResult<SkillGene, Float>>
{
	Phenotype<SkillGene, Float> best;

	private final CharacterUpdateCallback callback;

	private final PassiveSkillTree passiveSkillTree;

	private final CharacterClass characterClass;

	private final CharacterEvaluator characterEvaluator;

	public EvolutionWatcher(
			final CharacterUpdateCallback callback,
			final PassiveSkillTree passiveSkillTree,
			final CharacterClass characterClass,
			final CharacterEvaluator characterEvaluator)
	{
		this.characterEvaluator = characterEvaluator;
		best = null;
		this.callback = callback;
		this.passiveSkillTree = passiveSkillTree;
		this.characterClass = characterClass;
	}

	@Override
	public void accept(final EvolutionResult<SkillGene, Float> evolutionResult)
	{
		if (best == null || best.compareTo(evolutionResult.getBestPhenotype()) < 0)
		{
			best = evolutionResult.getBestPhenotype();
			final long oldest = evolutionResult.getWorstPhenotype().getAge(evolutionResult.getGeneration());
			System.out.println("Generation: " + evolutionResult.getGeneration() + ", Fitness: " + evolutionResult.getBestFitness() + ", Age: " + oldest);
			final Genotype<SkillGene> genotype = best.getGenotype();
			final PoeCharacter character = make(genotype);

			final ImmutableCharacter retchar = ImmutableCharacterBuilder.character()
					.from(character)
					.build();

			callback.accept(new EvolutionStatusBuilder()
					.withCharacter(retchar)
					.withGeneration(evolutionResult.getTotalGenerations())
					.withEvaluation(characterEvaluator.evaluate(new CharacterExpressionContextAdapter(character)))
					.build());
		}
	}

	private PoeCharacter make(final Genotype<SkillGene> genotype)
	{
		final PassiveSkill root = passiveSkillTree.findByName(characterClass.getRootPassiveSkillName());
		final PoeCharacter character = new PoeCharacter(characterClass);
		character.addPassiveSkill(root);
		final SkillChromosome c = genotype.getChromosome().as(SkillChromosome.class);
		final ISeq<SkillGene> seq = c.toSeq();
		final List<PassiveSkill> passives = new ArrayList<>();
		for (final SkillGene gene : seq)
		{
			final PassiveSkill find = passiveSkillTree.find(gene.getAllele());
			passives.add(find);
		}
		character.sneakyAdd(passives);

		return character;
	}
}
