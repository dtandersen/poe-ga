package poe.entity;

import java.util.Collection;

public interface ImmutableCharacter
{
	Collection<AttributeValue> getStats();

	Collection<StatValue> getPassives();
}
