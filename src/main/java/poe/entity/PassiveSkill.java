package poe.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PassiveSkill
{
	private final String name;

	private final List<StatValue> attributes = new ArrayList<>();

	private int id;

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
		return name + ": " + attributes;
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
}
