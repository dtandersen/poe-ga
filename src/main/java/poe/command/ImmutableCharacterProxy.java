package poe.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import poe.entity.Attribute;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveAttribute;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkillAttributeType;
import poe.entity.Stat;
import poe.entity.StatValue;

final class ImmutableCharacterProxy implements ImmutableCharacter
{
	private final HashMap<Stat, StatValue> stats;

	private final List<PassiveAttribute> passiveAttributes = new ArrayList<>();

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

	@Override
	public Collection<PassiveAttribute> getPassives()
	{
		return passiveAttributes;
	}

	public void apply(final PassiveSkill passive)
	{
		for (final Attribute attribute : passive.getAttributes())
		{
			apply(attribute);
		}
	}

	private void apply(final Attribute attribute)
	{
		final PassiveAttribute passiveAttribute = find(attribute.getAttributeType().getPassiveAttributeType());
	}

	private PassiveAttribute find(final PassiveSkillAttributeType passiveSkillAttributeType)
	{
		for (final PassiveAttribute passiveAttribute : passiveAttributes)
		{
			if (passiveAttribute.getPassive() == passiveSkillAttributeType)
			{
				return passiveAttribute;
			}
		}

		return null;
	}
}