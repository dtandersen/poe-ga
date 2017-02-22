package poe.entity;

public class UnknownAttribute extends Attribute
{
	private final String text;

	public UnknownAttribute(final String text)
	{
		super(AttributeType.UNKNOWN);
		this.text = text;
	}

	@Override
	public String toString()
	{
		return "Unknown: " + text;
	}
}
