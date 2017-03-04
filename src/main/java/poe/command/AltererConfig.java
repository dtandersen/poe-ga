package poe.command;

import poe.repository.jenetics.AltererType;

public class AltererConfig
{
	final AltererType altererType;

	final float probability;

	public AltererConfig(final AltererType altererType)
	{
		this.altererType = altererType;
		this.probability = 0;
	}
}
