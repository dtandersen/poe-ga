package poe.repo;

import java.util.Objects;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import poe.entity.Attribute;
import poe.entity.AttributeDescription;

final class AttributeMatcher extends TypeSafeDiagnosingMatcher<Attribute>
{
	private final AttributeDescription expectedAttributeType;

	private final float expectedValue;

	public AttributeMatcher(final AttributeDescription expectedAttributeType, final float expectedValue)
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
	protected boolean matchesSafely(final Attribute item, final Description mismatchDescription)
	{
		if (!Objects.equals(item.getAttributeType(), expectedAttributeType))
		{
			mismatchDescription.appendText("attribute " + item.getAttributeType().toString());
			return false;
		}

		if (!Objects.equals(item.getValue(), expectedValue))
		{
			mismatchDescription.appendText("value " + item.getValue());
			return false;
		}

		return true;
	}
}