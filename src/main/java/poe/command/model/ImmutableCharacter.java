package poe.command.model;

import java.util.Collection;
import java.util.List;
import poe.entity.AttributeValue;
import poe.entity.CharacterClass;
import poe.entity.StatValue;

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

	String getUrl();
}
