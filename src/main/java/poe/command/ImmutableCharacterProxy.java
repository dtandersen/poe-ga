package poe.command;

import java.util.Collection;
import java.util.HashMap;
import poe.entity.ImmutableCharacter;
import poe.entity.Stat;
import poe.entity.StatValue;

final class ImmutableCharacterProxy implements ImmutableCharacter
{
	private final HashMap<Stat, StatValue> stats;

	public ImmutableCharacterProxy()
	{
		stats = new HashMap<>();
	}

	public float stat(final Stat dexterity)
	{
		return stats.get(dexterity).getValue();
	}

	@Override
	public Collection<StatValue> getStats()
	{
		return stats.values();
	}

	public void stat(final Stat stat, final float value)
	{
		stats.put(stat, new StatValue(stat, value));
	}
}