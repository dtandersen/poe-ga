package poe.entity;

public class Attribute
{
	private final AttributeDescription attributeType;

	private float value;

	public Attribute(final AttributeDescription attributeType)
	{
		this.attributeType = attributeType;
	}

	@Override
	public String toString()
	{
		return getAttributeType().toString() + " " + value;
	}

	public AttributeDescription getAttributeType()
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
