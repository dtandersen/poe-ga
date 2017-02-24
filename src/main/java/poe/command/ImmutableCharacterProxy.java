package poe.command;

import java.util.Collection;
import java.util.HashMap;
import poe.entity.ImmutableCharacter;
import poe.entity.StatValue;
import poe.entity.PassiveAttributeBag;
import poe.entity.PassiveSkill;
import poe.entity.Attribute;
import poe.entity.AttributeValue;

final class ImmutableCharacterProxy implements ImmutableCharacter
{
	private final HashMap<Attribute, AttributeValue> stats;

	// private final List<PassiveAttribute> passiveAttributes = new ArrayList<>();
	private final PassiveAttributeBag passiveAttributes = new PassiveAttributeBag();

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
	public Collection<StatValue> getPassives()
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