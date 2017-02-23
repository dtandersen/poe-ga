package poe.command;

import java.util.Collection;
import poe.entity.StatValue;

public interface ImmutableCharacter
{
	Collection<StatValue> getStats();
}
