package poe.repository;

import java.util.List;
import poe.entity.CharInstance.PoeCharacter;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;

public class RandomCharacterGenerator
{
	private CharacterClass characterClass;

	private final PassiveSkillTree skillTree;

	private final Randomizer randomizer;

	public RandomCharacterGenerator(final PassiveSkillTree skillTree, final Randomizer randomizer)
	{
		this.skillTree = skillTree;
		this.randomizer = randomizer;
	}

	public RandomCharacterGenerator withCharacterClass(final CharacterClass characterClass)
	{
		this.characterClass = characterClass;
		return this;
	}

	public PoeCharacter generate(final int skillCount)
	{
		PassiveSkill curSkill = skillTree.findByName(characterClass.getRootPassiveSkillName());
		PassiveSkill prevSkill = curSkill;

		final PoeCharacter character = new PoeCharacter(characterClass, 1);
		final PassiveSkill root = curSkill;
		character.addPassiveSkill(root);

		do
		{
			if (character.hasAmountOfSkills(skillTree.count()))
			{
				break;
			}

			if (!(character.hasPassiveSkill(curSkill) || curSkill.isRootSkill()))
			{
				// System.out.println("added " + curSkill.getName());
				if (prevSkill == curSkill)
				{
					final PassiveSkill passiveSkill = curSkill;
					character.addPassiveSkill(passiveSkill);
				}
				else
				{
					final PassiveSkill passiveSkill = curSkill;
					character.addPassiveSkill(passiveSkill);
				}
			}
			else
			{
				// System.out.println("already have " + curSkill.getName());
			}
			prevSkill = curSkill;
			final List<Integer> neighbors = skillTree.neighbors(curSkill.getId());
			// System.out.println("neighbors of " + curSkill.getName() + " are "
			// + neighbors);
			final int nextIndex = randomizer.nextInt(neighbors.size());
			curSkill = skillTree.find(neighbors.get(nextIndex));
			// System.out.println("rolled " + nextIndex + " and got " +
			// curSkill.getName());
		}
		while ((character.passiveSkillCount()) - 1 < skillCount);
		return character;
	}
}
