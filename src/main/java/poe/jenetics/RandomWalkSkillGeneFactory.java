package poe.jenetics;

import java.util.List;
import org.jenetics.Genotype;
import org.jenetics.util.Factory;
import poe.entity.CharacterClass;
import poe.entity.PoeCharacter;
import poe.entity.RandomCharacterGenerator;
import poe.repository.JavaRandomizer;
import poe.repository.PassiveSkillTree;

public class RandomWalkSkillGeneFactory implements Factory<Genotype<SkillGene>>
{
	private final PassiveSkillTree pst;

	private final CharacterClass characterClass;

	private final int length;

	private final List<Integer> allowedSkills;

	public RandomWalkSkillGeneFactory(final PassiveSkillTree pst, final CharacterClass characterClass, final int length, final List<Integer> ids)
	{
		this.pst = pst;
		this.characterClass = characterClass;
		this.length = length;
		this.allowedSkills = ids;
	}

	@Override
	public Genotype<SkillGene> newInstance()
	{
		final PoeCharacter character = new RandomCharacterGenerator(pst, new JavaRandomizer())
				.withCharacterClass(characterClass)
				.generate(length);

		return Genotype.of(SkillChromosome.seq(allowedSkills, character.getPassiveSkillIds()));
	}
}