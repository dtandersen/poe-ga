package poe.entity;

import java.util.Collection;
import java.util.List;

public interface ImmutableCharacter
{
	Collection<AttributeValue> getStats();

	Collection<StatValue> getStatValues();

	List<Integer> getPassiveSkillIds();

	List<ImmutablePassiveSkill> getPassiveSkills();

	CharacterClass getCharacterClass();

	public interface ImmutablePassiveSkill
	{
		int getPassiveSkillId();

		String getName();
	}
}
