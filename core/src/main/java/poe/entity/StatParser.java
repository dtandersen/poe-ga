package poe.entity;

import java.util.regex.Matcher;

public class StatParser
{
	public StatValue parseItem(final String skillDescription)
	{
		for (final Stat stat : Stat.values())
		{
			final Matcher matcher = stat.matcher(skillDescription);
			if (!matcher.find())
			{
				continue;
			}

			try
			{
				final String group = matcher.group(1);
				final float value = Float.parseFloat(group);
				final StatValue statValue = new StatValue(stat, value);

				return statValue;
			}
			catch (final IndexOutOfBoundsException e)
			{
			}
		}

		return null;
	}
}
