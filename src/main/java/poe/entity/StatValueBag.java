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

	public void increment(final StatValue attribute)
	{
		final StatValue existing = find(attribute.getStat());
		if (existing == null)
		{
			passives.put(attribute.getStat(), attribute);
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
