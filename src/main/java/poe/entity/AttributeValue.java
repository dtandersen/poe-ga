package poe.entity;

public class AttributeValue
{
	private Attribute attribute;

	private float value;

	public Attribute getAttribute()
	{
		return attribute;
	}

	public void setAttribute(final Attribute attribute)
	{
		this.attribute = attribute;
	}

	public float getValue()
	{
		return value;
	}

	public void setValue(final float value)
	{
		this.value = value;
	}

	public AttributeValue(final Attribute attribute, final float value)
	{
		this.attribute = attribute;
		this.value = value;
	}

	@Override
	public String toString()
	{
		return attribute + "=" + value;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attribute == null) ? 0 : attribute.hashCode());
		result = prime * result + Float.floatToIntBits(value);
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		final AttributeValue other = (AttributeValue)obj;
		if (attribute != other.attribute) { return false; }
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value)) { return false; }
		return true;
	}
}
