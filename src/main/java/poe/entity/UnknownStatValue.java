package poe.entity;

public class UnknownStatValue extends StatValue
{
	private final String text;

	public UnknownStatValue(final String text)
	{
		super(Stat.UNKNOWN, 0);
		this.text = text;
	}

	@Override
	public String toString()
	{
		return "Unknown: " + text;
	}
}
