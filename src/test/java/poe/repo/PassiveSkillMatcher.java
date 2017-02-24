package poe.repo;

import static org.hamcrest.Matchers.containsInAnyOrder;
import java.util.Objects;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import poe.entity.Attribute;
import poe.entity.PassiveSkill;

final class PassiveSkillMatcher extends TypeSafeDiagnosingMatcher<PassiveSkill>
{
	private String expectedName;

	private Matcher<Attribute>[] expectedAttributes;

	private int id;

	public PassiveSkillMatcher withName(final String name)
	{
		this.expectedName = name;
		return this;
	}

	public PassiveSkillMatcher withId(final int id)
	{
		this.id = id;
		return this;
	}

	public PassiveSkillMatcher withAttributes(final Matcher<Attribute>... expectedAttributes)
	{
		this.expectedAttributes = expectedAttributes;
		return this;
	}

	@Override
	public void describeTo(final Description description)
	{
		description.appendText("the skill " + expectedName);
		description.appendText(" with id " + id);
		if (expectedAttributes != null)
		{
			description.appendText(" with the attributes ");
			for (final Matcher<?> x : expectedAttributes)
			{
				x.describeTo(description);
			}
		} else
		{
			description.appendText(" with no attributes ");
		}
	}

	@Override
	protected boolean matchesSafely(final PassiveSkill actualSkill, final Description mismatchDescription)
	{
		if (!Objects.equals(actualSkill.getName(), expectedName))
		{
			mismatchDescription.appendText(actualSkill.toString());
			return false;
		}

		if (id != actualSkill.getId())
		{
			mismatchDescription.appendText(" was id " + actualSkill.getId());
			return false;
		}

		if (expectedAttributes != null)
		{
			final Matcher<Iterable<? extends Attribute>> x = containsInAnyOrder(expectedAttributes);
			if (!x.matches(actualSkill.getAttributes()))
			{
				// x.describeMismatch(actualSkill.getAttributes(),
				// mismatchDescription);
				mismatchDescription.appendText(actualSkill.toString());
				return false;
			}
		} else
		{
			if (!actualSkill.getAttributes().isEmpty())
			{
				// mismatchDescription.appendValueList("", ",", "",
				// actualSkill.getAttributes());
				mismatchDescription.appendText(actualSkill.toString());
				return false;
			}
		}

		return true;
	}
}
