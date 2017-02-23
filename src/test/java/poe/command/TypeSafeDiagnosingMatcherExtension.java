package poe.command;

import java.util.HashSet;
import java.util.Set;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import poe.entity.Stat;
import poe.entity.StatValue;

final class TypeSafeDiagnosingMatcherExtension extends TypeSafeDiagnosingMatcher<ImmutableCharacter>
{
	Set<StatValue> stats = new HashSet<>();

	@Override
	public void describeTo(final Description description)
	{
		description.appendValueList("[", ",", "]", stats);
	}

	@Override
	protected boolean matchesSafely(final ImmutableCharacter item, final Description mismatchDescription)
	{
		final Matcher<Iterable<? extends StatValue>> m = Matchers.containsInAnyOrder(stats.toArray(new StatValue[0]));
		if (!m.matches(item.getStats()))
		{
			m.describeMismatch(item.getStats(), mismatchDescription);
			return false;

		}

		return true;
	}

	public TypeSafeDiagnosingMatcherExtension withStat(final Stat stat, final int value)
	{
		stats.add(new StatValue(stat, value));
		return this;
	}
}