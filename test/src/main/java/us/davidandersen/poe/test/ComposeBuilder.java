package us.davidandersen.poe.test;

import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.hamcrest.Matcher;
import org.hobsoft.hamcrest.compose.ComposeMatchers;

public class ComposeBuilder<T>
{
	private String description;

	private final List<Matcher<? super T>> features;

	public ComposeBuilder()
	{
		features = new ArrayList<>();
	}

	public ComposeBuilder<T> withDescription(final String description)
	{
		this.description = description;
		return this;
	}

	public <U> ComposeBuilder<T> withFeature(final String featureName, final Function<T, U> featureFunction, final Matcher<U> featureMatcher)
	{
		features.add(ComposeMatchers.hasFeature(featureName, featureFunction, featureMatcher));
		return this;
	}

	public <U> ComposeBuilder<T> withFeature(final String featureName, final Function<T, U> featureFunction, final Object featureValue)
	{
		features.add(ComposeMatchers.hasFeature(featureName, featureFunction, equalTo(featureValue)));
		return this;
	}

	public Matcher<T> build()
	{
		return ComposeMatchers.compose(description, features);
	}

	public static <T> ComposeBuilder<T> of(final Class<T> clazz)
	{
		return new ComposeBuilder<>();
	}
}
