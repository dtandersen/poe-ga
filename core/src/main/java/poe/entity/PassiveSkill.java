package poe.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import poe.entity.StatValue.StatBuilder;

public class PassiveSkill
{
	private final String name;

	private List<StatValue> attributes = new ArrayList<>();

	private int id;

	private List<Integer> outputs;

	private CharacterClass classStartingPoint;

	private int jewels;

	public PassiveSkill(final PassiveSkillBuilder passiveSkillBuilder)
	{
		this.id = passiveSkillBuilder.id;
		this.name = passiveSkillBuilder.name;
		this.outputs = passiveSkillBuilder.outputs;
		this.attributes = passiveSkillBuilder.stats;
		this.classStartingPoint = passiveSkillBuilder.classStartingPoint;
		this.jewels = passiveSkillBuilder.jewels;
	}

	public List<StatValue> getStats()
	{
		return attributes;
	}

	public CharacterClass getClassStartingPoint()
	{
		return classStartingPoint;
	}

	public void setClassStartingPoint(final CharacterClass classStartingPoint)
	{
		this.classStartingPoint = classStartingPoint;
	}

	public boolean isClassStartingNode()
	{
		return classStartingPoint != null;
	}

	public PassiveSkill(final String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void addAttribute(final StatValue attribute)
	{
		attributes.add(attribute);
	}

	@Override
	public String toString()
	{
		return name + " (" + id + "): " + attributes + ", outputs=" + outputs;
	}

	public boolean hasAttribute(final Stat expectedAttributeType)
	{
		for (final StatValue a : attributes)
		{
			if (Objects.equals(a.getStat(), expectedAttributeType)) { return true; }
		}

		return false;
	}

	public List<StatValue> getStatValues()
	{
		return attributes;
	}

	public int getId()
	{
		return id;
	}

	public void setId(final int id)
	{
		this.id = id;
	}

	public List<Integer> getOutputs()
	{
		return outputs;
	}

	public void setOutputs(final List<Integer> out)
	{
		outputs = out;
	}

	public boolean isNeighbor(final Collection<PassiveSkill> neighbors)
	{
		for (final PassiveSkill neighbor : neighbors)
		{
			if (isNeighbor(neighbor)) { return true; }
		}

		return false;
	}

	public boolean isNeighbor(final PassiveSkill neighbor)
	{
		return isNeighbor(neighbor.getId()) || neighbor.isNeighbor(this.id);
	}

	private boolean isNeighbor(final int otherId)
	{
		return outputs.contains(otherId);
	}

	public boolean isRootSkill()
	{
		return CharacterClass.isRootSkill(name);
	}

	public int getJewels()
	{
		return jewels;
	}

	public void setJewels(final int jewels)
	{
		this.jewels = jewels;
	}

	public static class PassiveSkillBuilder
	{
		private int id;

		private String name;

		private List<Integer> outputs = new ArrayList<>();

		private List<StatValue> stats = new ArrayList<>();

		private CharacterClass classStartingPoint;

		private int jewels;

		public PassiveSkillBuilder withId(final int id)
		{
			this.id = id;
			return this;
		}

		public PassiveSkillBuilder withName(final String name)
		{
			this.name = name;
			return this;
		}

		public PassiveSkillBuilder withOutputs(final Integer... outputs)
		{
			this.outputs = Arrays.asList(outputs);
			return this;
		}

		public PassiveSkillBuilder withOutputs(final Optional<String> outputs)
		{
			if (!outputs.isPresent()) { return this; }

			return withOutputs(Arrays
					.stream(outputs.get().split(","))
					.map(o -> o.trim())
					.filter(o -> !o.isEmpty())
					.map(o -> Integer.parseInt(o))
					.collect(Collectors.toList())
					.toArray(new Integer[0]));
		}

		public PassiveSkillBuilder withStats(final StatBuilder... statBuilders)
		{
			stats = new ArrayList<>();
			for (final StatBuilder statBuilder : statBuilders)
			{
				stats.add(statBuilder.build());
			}

			return this;
		}

		public PassiveSkill build()
		{
			return new PassiveSkill(this);
		}

		public static PassiveSkillBuilder passiveSkill()
		{
			return new PassiveSkillBuilder();
		}

		public PassiveSkillBuilder withClassStartingPoint(final CharacterClass characterClass)
		{
			this.classStartingPoint = characterClass;
			return this;
		}

		public PassiveSkillBuilder withJewels(final int jewels)
		{
			this.jewels = jewels;
			return this;
		}
	}
}
