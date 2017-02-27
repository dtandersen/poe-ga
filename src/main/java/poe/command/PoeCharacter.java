package poe.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import poe.entity.Attribute;
import poe.entity.AttributeValue;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkillGraph;
import poe.entity.PassiveSkillTree;
import poe.entity.StatValue;
import poe.entity.StatValueBag;

public final class PoeCharacter
{
	private final Map<Attribute, AttributeValue> stats;

	private final StatValueBag passiveAttributes;

	private final PassiveSkillGraph skillGraph;

	public PoeCharacter()
	{
		stats = new HashMap<>();
		passiveAttributes = new StatValueBag();
		skillGraph = new PassiveSkillGraph();
	}

	public float stat(final Attribute attribute)
	{
		return stats.get(attribute).getValue();
	}

	public Collection<AttributeValue> getStats()
	{
		return stats.values();
	}

	public void stat(final Attribute stat, final float value)
	{
		stats.put(stat, new AttributeValue(stat, value));
	}

	public Collection<StatValue> getStatValues()
	{
		return getPassiveAttributes().list();
	}

	public List<Integer> getPassiveSkillIds()
	{
		return skillGraph.getPassiveSkillIds();
	}

	public StatValueBag getPassiveAttributes()
	{
		return passiveAttributes;
	}

	public void apply(final StatValue attribute)
	{
		passiveAttributes.increment(attribute);
	}

	public void apply(final PassiveSkill passive)
	{
		if (passive == null) { return; }
		if (skillGraph.contains(passive.getId())) { return; }

		skillGraph.addPassiveSkill(passive);
		if (passive.getAttributes() == null) { return; }
		for (final StatValue attribute : passive.getAttributes())
		{
			apply(attribute);
		}
	}

	public void applyPassives(
			final List<Integer> passiveSkillIds,
			final PassiveSkillTree pst)
	{
		for (final int passiveSkillId : passiveSkillIds)
		{
			final PassiveSkill passive = pst.find(passiveSkillId);
			apply(passive);
		}
	}

	public void addSkill(final PassiveSkill passiveSkill)
	{
		skillGraph.addPassiveSkill(passiveSkill);
	}

	public int passiveSkillCount()
	{
		return skillGraph.size();
	}

	public boolean hasPassiveSkill(final PassiveSkill passiveSkill)
	{
		return skillGraph.contains(passiveSkill.getId());
	}

	public boolean hasAllPassiveSkills(final List<PassiveSkill> skills)
	{
		return passiveSkillCount() == skills.size();
	}

	public boolean hasPassiveSkill(final int passiveSkillId)
	{
		return skillGraph.contains(passiveSkillId);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPassiveAttributes() == null) ? 0 : getPassiveAttributes().hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		final PoeCharacter other = (PoeCharacter)obj;
		if (getPassiveAttributes() == null)
		{
			if (other.getPassiveAttributes() != null) { return false; }
		}
		else if (!getPassiveAttributes().equals(other.getPassiveAttributes())) { return false; }
		if (stats == null)
		{
			if (other.stats != null) { return false; }
		}
		else if (!stats.equals(other.stats)) { return false; }
		return true;
	}

	@Override
	public String toString()
	{
		return "ImmutableCharacterProxy[stats=" + stats + ", passiveAttributes=" + getPassiveAttributes() + "]";
	}

	public List<PassiveSkill> getPassiveSkills()
	{
		return skillGraph.getPassiveSkills();
	}
}