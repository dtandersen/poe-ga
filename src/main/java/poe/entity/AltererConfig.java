package poe.entity;

public class AltererConfig
{
	private final String altererType;

	private final float probability;

	public AltererConfig(final String altererType, final float probability)
	{
		this.altererType = altererType;
		this.probability = probability;
	}

	public String getAltererTypeName()
	{
		return altererType;
	}

	public float getProbability()
	{
		return probability;
	}
}
