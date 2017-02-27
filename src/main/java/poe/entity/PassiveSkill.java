package poe.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class PassiveSkill
{
	private final String name;

	private final List<StatValue> attributes = new ArrayList<>();

	private int id;

	private List<Integer> outputs;

	public PassiveSkill(final String name)
	{
		this.name = name;
	}

	public PassiveSkill(final PassiveSkillBuilder passiveSkillBuilder)
	{
		this.id = passiveSkillBuilder.id;
		this.name = passiveSkillBuilder.name;
		this.outputs = passiveSkillBuilder.outputs;
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
		return name + ": " + attributes + ", outputs=" + outputs;
	}

	public boolean hasAttribute(final Stat expectedAttributeType)
	{
		for (final StatValue a : attributes)
		{
			if (Objects.equals(a.getStat(), expectedAttributeType)) { return true; }
		}

		return false;
	}

	public List<StatValue> getAttributes()
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

	public static class PassiveSkillBuilder
	{
		private int id;

		private String name;

		private List<Integer> outputs;

		public PassiveSkill build()
		{
			return new PassiveSkill(this);
		}

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
}
