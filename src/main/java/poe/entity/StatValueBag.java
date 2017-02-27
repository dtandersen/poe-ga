package poe.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StatValueBag
{
	private final Map<Stat, StatValue> passives = new HashMap<>();

	public Collection<StatValue> list()
	{
		return passives.values();
	}

	public void increment(final StatValue statValue)
	{
		final StatValue existing = find(statValue.getStat());
		if (existing == null)
		{
			passives.put(statValue.getStat(), statValue);
		}
	}

	private StatValue find(final Stat attributeType)
	{
		return passives.get(attributeType);
	}

	@Override
	public String toString()
	{
		return "StatValueBag[passives=" + passives + "]";
	}
}
