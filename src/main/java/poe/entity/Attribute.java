package poe.entity;

public class Attribute
{
	private final AttributeType attributeType;

	private float value;

	public Attribute(final AttributeType attributeType)
	{
		this.attributeType = attributeType;
	}

	@Override
	public String toString()
	{
		return getAttributeType().toString() + " " + value;
	}

	public AttributeType getAttributeType()
	{
		return attributeType;
	}

	public float getValue()
	{
		return value;
	}

	public void setValue(final float value)
	{
		this.value = value;
	}
}
