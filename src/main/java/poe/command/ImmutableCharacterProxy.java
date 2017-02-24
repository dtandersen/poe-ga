package poe.command;

import java.util.Collection;
import java.util.HashMap;
import poe.entity.Attribute;
import poe.entity.AttributeValue;
import poe.entity.ImmutableCharacter;
import poe.entity.StatValueBag;
import poe.entity.PassiveSkill;
import poe.entity.StatValue;

final class ImmutableCharacterProxy implements ImmutableCharacter
{
	private final HashMap<Attribute, AttributeValue> stats;

	private final StatValueBag passiveAttributes = new StatValueBag();

	public ImmutableCharacterProxy()
	{
		stats = new HashMap<>();
	}

	public float stat(final Attribute dexterity)
	{
		return stats.get(dexterity).getValue();
	}

	@Override
	public Collection<AttributeValue> getStats()
	{
		return stats.values();
	}

	public void stat(final Attribute stat, final float value)
	{
		stats.put(stat, new AttributeValue(stat, value));
	}

	@Override
	public Collection<StatValue> getStateValues()
	{
		return passiveAttributes.list();
	}

	public void apply(final PassiveSkill passive)
	{
		for (final StatValue attribute : passive.getAttributes())
		{
			apply(attribute);
		}
	}

	private void apply(final StatValue attribute)
	{
		passiveAttributes.increment(attribute);
	}
}