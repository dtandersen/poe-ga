package poe.entity;

public class StatValue
{
	private Stat stat;

	private float value;

	public Stat getStat()
	{
		return stat;
	}

	public void setStat(final Stat stat)
	{
		this.stat = stat;
	}

	public float getValue()
	{
		return value;
	}

	public void setValue(final float value)
	{
		this.value = value;
	}

	public StatValue(final Stat stat, final float value)
	{
		this.stat = stat;
		this.value = value;
	}

	@Override
	public String toString()
	{
		return stat + "=" + value;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stat == null) ? 0 : stat.hashCode());
		result = prime * result + Float.floatToIntBits(value);
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		final StatValue other = (StatValue)obj;
		if (stat != other.stat) { return false; }
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value)) { return false; }
		return true;
	}
}
