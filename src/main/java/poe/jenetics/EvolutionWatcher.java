package poe.jenetics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.jenetics.Genotype;
import org.jenetics.Phenotype;
import org.jenetics.engine.EvolutionResult;
import org.jenetics.util.ISeq;
import poe.command.PureImmutableCharacter.ImmutableCharacterBuilder;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill;
import poe.entity.PoeCharacter;
import poe.repository.EvolutionStatus.EvolutionStatusBuilder;
import poe.repository.PassiveSkillTree;

class EvolutionWatcher implements Consumer<EvolutionResult<SkillGene, Integer>>
{
	Phenotype<SkillGene, Integer> best;

	private final CharacterUpdateCallback callback;

	private final PassiveSkillTree passiveSkillTree;

	private final CharacterClass characterClass;

	public EvolutionWatcher(
			final CharacterUpdateCallback callback,
			final PassiveSkillTree passiveSkillTree,
			final CharacterClass characterClass)
	{
		best = null;
		this.callback = callback;
		this.passiveSkillTree = passiveSkillTree;
		this.characterClass = characterClass;
	}

	@Override
	public void accept(final EvolutionResult<SkillGene, Integer> evolutionResult)
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

			// System.out.println("gen=" + evolutionResult.getGeneration() + "," + retchar.getPassiveSkills());

			callback.accept(new EvolutionStatusBuilder()
					.withCharacter(retchar)
					.withGeneration(evolutionResult.getTotalGenerations())
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
			final PassiveSkill find = passiveSkillTree.find(gene.getPassiveSkillId());
			passives.add(find);
		}
		character.sneakyAdd(passives);

		return character;
	}
}
