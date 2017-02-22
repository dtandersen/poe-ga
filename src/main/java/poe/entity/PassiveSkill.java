package poe.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PassiveSkill
{
	private final String name;

	private final List<Attribute> attributes = new ArrayList<>();

	public PassiveSkill(final String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void addAttribute(final Attribute attribute)
	{
		attributes.add(attribute);
	}

	@Override
	public String toString()
	{
		return name + ": " + attributes;
	}

	public boolean hasAttribute(final AttributeType expectedAttributeType)
	{
		for (final Attribute a : attributes)
		{
			if (Objects.equals(a.getAttributeType(), expectedAttributeType)) { return true; }
		}

		return false;
	}

	public List<Attribute> getAttributes()
	{
		return attributes;
	}
}
