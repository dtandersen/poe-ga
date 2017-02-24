package poe.entity;

import java.util.List;
import org.hamcrest.Matcher;

public class PoeMatchers
{
	public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(final List<T> values)
	{
		return org.hamcrest.Matchers.containsInAnyOrder(MatcherHelper.listToArray(values));
	}

	public static PassiveSkillMatcher skill()
	{
		return PassiveSkillMatcher.skill();
	}

	public static StatValueMatcher attribute(final Stat stat, final float value)
	{
		return StatValueMatcher.attribute(stat, value);
	}

	public static CharacterStatMatcher hasCharacter()
	{
		return CharacterStatMatcher.passiveMatcher();
	}

	public static StatValuesMatcher hasStats()
	{
		return StatValuesMatcher.hasStats();
	}
}
