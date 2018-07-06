package poe.jenetics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import io.jenetics.Genotype;
import io.jenetics.util.Factory;
import poe.entity.CharacterClass;
import poe.entity.CharacterInstance.PoeCharacterEditor;
import poe.repository.JavaRandomizer;
import poe.repository.PassiveSkillTree;
import poe.repository.RandomCharacterGenerator;

public class RandomWalkSkillGeneFactory implements Factory<Genotype<SkillGene>>
{
	private final PassiveSkillTree pst;

	private final CharacterClass characterClass;

	private final int length;

	private final Set<Integer> allowedSkills;

	public RandomWalkSkillGeneFactory(final PassiveSkillTree pst, final CharacterClass characterClass, final int length, final List<Integer> ids)
	{
		this.pst = pst;
		this.characterClass = characterClass;
		this.length = length;
		this.allowedSkills = new HashSet<>(ids);
	}

	@Override
	public Genotype<SkillGene> newInstance()
	{
		final PoeCharacterEditor character = new RandomCharacterGenerator(pst, new JavaRandomizer())
				.withCharacterClass(characterClass)
				.generate(length);

		return Genotype.of(SkillChromosome.seq(allowedSkills, character.getPassiveSkillIds()));
	}
}
