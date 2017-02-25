package poe.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import poe.entity.Attribute;
import poe.entity.AttributeValue;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill;
import poe.entity.StatValue;
import poe.entity.StatValueBag;

final class ImmutableCharacterProxy implements ImmutableCharacter
{
	private Map<Attribute, AttributeValue> stats = new HashMap<>();

	private final StatValueBag passiveAttributes = new StatValueBag();

	private List<Integer> passiveSkillIds = new ArrayList<>();

	public ImmutableCharacterProxy(final ImmutableCharacterBuilder immutableCharacterBuilder)
	{
		passiveSkillIds = immutableCharacterBuilder.passiveSkillIds;
		stats = new HashMap<>();
	}

	public ImmutableCharacterProxy()
	{
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
		if (passive == null)
		{
			return;
		}
		if (passive.getAttributes() == null)
		{
			return;
		}
		for (final StatValue attribute : passive.getAttributes())
		{
			apply(attribute);
		}
	}

	private void apply(final StatValue attribute)
	{
		passiveAttributes.increment(attribute);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((passiveAttributes == null) ? 0 : passiveAttributes.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final ImmutableCharacterProxy other = (ImmutableCharacterProxy)obj;
		if (passiveAttributes == null)
		{
			if (other.passiveAttributes != null)
			{
				return false;
			}
		} else if (!passiveAttributes.equals(other.passiveAttributes))
		{
			return false;
		}
		if (stats == null)
		{
			if (other.stats != null)
			{
				return false;
			}
		} else if (!stats.equals(other.stats))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "ImmutableCharacterProxy[stats=" + stats + ", passiveAttributes=" + passiveAttributes + "]";
	}

	public static class ImmutableCharacterBuilder
	{
		private List<Integer> passiveSkillIds = new ArrayList<>();

		public ImmutableCharacter build()
		{
			return new ImmutableCharacterProxy(this);
		}

		public ImmutableCharacterBuilder withPassive(final int passiveSkillId)
		{
			this.passiveSkillIds.add(passiveSkillId);
			return this;
		}

		public ImmutableCharacterBuilder withPassives(final List<Integer> passives)
		{
			this.passiveSkillIds = passives;
			return this;
		}

		static ImmutableCharacterBuilder character()
		{
			return new ImmutableCharacterBuilder();
		}
	}

	@Override
	public List<Integer> getPassiveSkillIds()
	{
		return passiveSkillIds;
	}
}