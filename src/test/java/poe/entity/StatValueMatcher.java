package poe.entity;

import java.util.Objects;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class StatValueMatcher extends TypeSafeDiagnosingMatcher<StatValue>
{
	private final Stat expectedAttributeType;

	private final float expectedValue;

	public StatValueMatcher(final Stat expectedAttributeType, final float expectedValue)
	{
		this.expectedAttributeType = expectedAttributeType;
		this.expectedValue = expectedValue;
	}

	@Override
	public void describeTo(final Description description)
	{
		description.appendText(expectedAttributeType + " " + expectedValue);
	}

	@Override
	protected boolean matchesSafely(final StatValue item, final Description mismatchDescription)
	{
		if (!Objects.equals(item.getStat(), expectedAttributeType))
		{
			mismatchDescription.appendText("attribute " + item.getStat().toString());
			return false;
		}

		if (!Objects.equals(item.getValue(), expectedValue))
		{
			mismatchDescription.appendText("value " + item.getValue());
			return false;
		}

		return true;
	}

	static StatValueMatcher attribute(final Stat attributeType, final float value)
	{
		return new StatValueMatcher(attributeType, value);
	}
}