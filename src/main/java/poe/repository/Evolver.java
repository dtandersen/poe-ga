package poe.repository;

import java.util.List;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkillTree;
import poe.entity.PoeCharacter;

public interface Evolver
{
	PoeCharacter getBest(
			List<PassiveSkill> passives,
			CharacterClass characterClass,
			PassiveSkillTree passiveSkillTree);
}
