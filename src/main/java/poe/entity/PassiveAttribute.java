package poe.entity;

public class PassiveAttribute
{
	private final PassiveSkillAttributeType passive;

	private final float value;

	public PassiveAttribute(final PassiveSkillAttributeType passive, final float value)
	{
		this.passive = passive;
		this.value = value;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPassive() == null) ? 0 : getPassive().hashCode());
		result = prime * result + Float.floatToIntBits(value);
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
		final PassiveAttribute other = (PassiveAttribute)obj;
		if (getPassive() != other.getPassive())
		{
			return false;
		}
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return getPassive() + "=" + value;
	}

	public PassiveSkillAttributeType getPassive()
	{
		return passive;
	}
}
