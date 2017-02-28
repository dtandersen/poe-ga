package poe.repository;

import java.util.List;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PoeCharacter;

public class MockEvolver implements Evolver
{
	public PoeCharacter best;

	public List<PassiveSkill> passives;

	public CharacterClass characterClass;

	@Override
	public PoeCharacter getBest(
			final List<PassiveSkill> passives,
			final CharacterClass characterClass)
	{
		this.passives = passives;
		this.characterClass = characterClass;
		best.addPassiveSkills(passives);
		return best;
	}
}