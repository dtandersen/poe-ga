package poe.entity;

import java.util.List;

public class MatcherHelper
{
	public static <T> T[] listToArray(final List<T> values)
	{
		@SuppressWarnings("unchecked")
		final T[] valueArray = (T[])values.toArray();

		return valueArray;
	}
}
