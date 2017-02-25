package poe.entity;

import java.util.Collection;
import java.util.List;

public interface ImmutableCharacter
{
	Collection<AttributeValue> getStats();

	Collection<StatValue> getStateValues();

	List<Integer> getPassiveSkillIds();
}
