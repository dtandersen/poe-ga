package poe.entity;

public class StatValue
{
	private final Stat stat;

	private final float value;

	public Stat getStat()
	{
		return stat;
	}

	public float getValue()
	{
		return value;
	}

	public StatValue(final Stat stat, final float value)
	{
		this.stat = stat;
		this.value = value;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getStat() == null) ? 0 : getStat().hashCode());
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
		if (getStat() != other.getStat()) { return false; }
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value)) { return false; }
		return true;
	}

	@Override
	public String toString()
	{
		return getStat() + "=" + value;
	}
}
