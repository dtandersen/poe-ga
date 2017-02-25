package poe.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import poe.util.TypeUtil;

public abstract class ComposableMatcher<T, U> extends TypeSafeDiagnosingMatcher<T>
{
	private final Matcher<?> matcher;

	public ComposableMatcher(final Matcher<?> matcher)
	{
		this.matcher = matcher;
	}

	protected abstract U getValue(final T item);

	@Override
	public void describeTo(final Description description)
	{
		description.appendText("an " + typeArgumentName() + " with ");
		matcher.describeTo(description);
	}

	@Override
	protected boolean matchesSafely(final T item, final Description mismatchDescription)
	{
		final U value = getValue(item);
		if (!matcher.matches(value))
		{
			matcher.describeMismatch(value, mismatchDescription);
			return false;
		}

		return true;
	}

	private String typeArgumentName()
	{
		final Class<?> typeArgument = TypeUtil.resolveTypeArgument(getClass(), ComposableMatcher.class);
		return typeArgument.getSimpleName();
	}
}