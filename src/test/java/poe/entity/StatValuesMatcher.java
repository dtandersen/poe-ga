package poe.entity;

import java.util.HashSet;
import java.util.Set;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class StatValuesMatcher extends TypeSafeDiagnosingMatcher<ImmutableCharacter>
{
	Set<AttributeValue> stats = new HashSet<>();

	@Override
	public void describeTo(final Description description)
	{
		description.appendValueList("[", ",", "]", stats);
	}

	@Override
	protected boolean matchesSafely(final ImmutableCharacter item, final Description mismatchDescription)
	{
		final Matcher<Iterable<? extends AttributeValue>> m = Matchers.containsInAnyOrder(stats.toArray(new AttributeValue[0]));
		if (!m.matches(item.getStats()))
		{
			m.describeMismatch(item.getStats(), mismatchDescription);
			return false;

		}

		return true;
	}

	public StatValuesMatcher withStat(final Attribute stat, final int value)
	{
		stats.add(new AttributeValue(stat, value));
		return this;
	}
}