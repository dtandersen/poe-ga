package poe.entity;

import java.util.List;
import org.hamcrest.Matcher;

public class PoeMatchers
{
	public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(final List<T> values)
	{
		return org.hamcrest.Matchers.containsInAnyOrder(MatcherHelper.listToArray(values));
	}

	public static PassiveMatcher passiveMatcher()
	{
		return new PassiveMatcher();
	}

	public static PassiveSkillMatcher skill()
	{
		return new PassiveSkillMatcher();
	}

	public static Matcher<StatValue> attribute(final Stat attributeType, final float value)
	{
		return new StatValueMatcher(attributeType, value);
	}
}
