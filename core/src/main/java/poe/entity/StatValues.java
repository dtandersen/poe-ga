package poe.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StatValues
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
			passives.put(statValue.getStat(), new StatValue(statValue.getStat(), statValue.getValue()));
		}
		else
		{
			passives.put(statValue.getStat(), new StatValue(statValue.getStat(), existing.getValue() + statValue.getValue()));
		}
	}

	public StatValue find(final Stat attributeType)
	{
		return passives.get(attributeType);
	}

	@Override
	public String toString()
	{
		return "StatValueBag[passives=" + passives + "]";
	}

	public float valueOf(final Stat increasedMana)
	{
		final StatValue sv = find(increasedMana);
		if (sv == null)
		{
			return 0;
		}
		return sv.getValue();
	}
}
