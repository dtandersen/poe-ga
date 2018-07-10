package us.davidandersen.poe.currency.entity;

import java.util.Objects;

public enum Currency
{
	CHAOS("chaos", "Chaos Orb"),
	FUSING("fuse", "Orb of Fusing"),
	SCOURING("scour", "Orb of Scouring");

	private final String shortName;

	private final String longName;

	Currency(final String shortName, final String longName)
	{
		this.shortName = shortName;
		this.longName = longName;
	}

	public static Currency longNameToCurrency(final String longName)
	{
		for (final Currency c : values())
		{
			if (Objects.equals(c.longName, longName)) { return c; }
		}

		return null;
	}

	public static Currency shortNameToCurrency(final String shortName)
	{
		for (final Currency c : values())
		{
			if (Objects.equals(c.shortName, shortName)) { return c; }
		}

		return null;
	}

	public String getShortName()
	{
		return shortName;
	}
}
