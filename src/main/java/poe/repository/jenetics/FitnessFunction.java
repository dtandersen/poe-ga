package poe.repository.jenetics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.jenetics.Genotype;
import org.jenetics.util.ISeq;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkillTree;
import poe.entity.PoeCharacter;
import poe.entity.Stat;

public class FitnessFunction implements Function<Genotype<SkillGene>, Integer>
{
	private final PassiveSkillTree passiveSkillTree;

	private final CharacterClass characterClass;

	public FitnessFunction(
			final PassiveSkillTree passiveSkillTree,
			final CharacterClass characterClass)
	{
		this.passiveSkillTree = passiveSkillTree;
		this.characterClass = characterClass;
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
		int value = (int)character.getStat3(Stat.STRENGTH) * 10;
		value += (int)character.getStat3(Stat.INTELLIGENCE) * 1;
		value += (int)character.getStat3(Stat.DEXTERITY) * 1;
		value += (int)character.getStat3(Stat.TOTEM_CAST_SPEED) * 100;
		value += (int)character.getStat3(Stat.TOTEM_ATTACK_SPEED) * 100;
		value += (int)character.getStat3(Stat.TOTEM_PLACEMENT_SPEED) * 100;
		value += (int)character.getStat3(Stat.TOTEM_DAMAGE) * 100;
		value += (int)(character.getStat3(Stat.MELEE_CSC) * (1 + character.getStat3(Stat.MELEE_CSM))) * 10;
		value += (int)(character.getStat3(Stat.LIFE_MAX) * (1 + character.getStat3(Stat.MAX_LIFE_PLUS)));
		// System.out.println("strength=" + value);

		int fitness = value + character.passiveSkillCount() * 10;
		// if (character.hasPassiveNamed("Slaughter"))
		// {
		// fitness += 100000;
		// }
		// if (character.hasPassiveNamed("Ancestral Bond"))
		// {
		// fitness += 100000;
		// }
		if (character.hasPassiveNamed("Constitution"))
		{
			fitness += 100000;
		}
		// System.out.println(fitness);
		return fitness;
	}
}