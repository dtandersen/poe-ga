package poe.matcher;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import poe.util.TypeUtil;

public abstract class MatcherChain<T> extends TypeSafeDiagnosingMatcher<T>
{
	private final List<Chain<T>> chains = new ArrayList<>();

	protected void addChain(final Matcher<?> matcher, final Grabber<T> grabber)
	{
		chains.add(new Chain<>(matcher, grabber));
	}

	@Override
	public void describeTo(final Description description)
	{
		description.appendText("an " + typeArgumentName() + " with ");
		for (final Chain<T> chain : chains)
		{
			final Matcher<?> matcher = chain.getMatcher();
			matcher.describeTo(description);
		}
	}

	@Override
	protected boolean matchesSafely(final T item, final Description mismatchDescription)
	{
		boolean failed = false;
		for (final Chain<T> chain : chains)
		{
			final Matcher<?> matcher = chain.getMatcher();
			final Object item2 = chain.grabber.grab(item);
			if (!matcher.matches(item2))
			{
				matcher.describeMismatch(item2, mismatchDescription);
				failed = true;
			}
		}

		return !failed;
	}

	private String typeArgumentName()
	{
		final Class<?> typeArgument = TypeUtil.resolveTypeArgument(getClass(), MatcherChain.class);
		return typeArgument.getSimpleName();
	}

	public interface Grabber<T>
	{
		public abstract Object grab(T item);
	}

	public static final class Chain<T>
	{
		private final Matcher<?> matcher;

		private final Grabber<T> grabber;

		public Chain(final Matcher<?> matcher, final Grabber<T> grabber)
		{
			this.matcher = matcher;
			this.grabber = grabber;
		}

		public Matcher<?> getMatcher()
		{
			return matcher;
		}

		public Grabber<T> getGrabber()
		{
			return grabber;
		}
	}
}