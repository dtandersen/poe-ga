package poe.entity;

import static poe.entity.PoeMatchers.containsInAnyOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class PassiveMatcher extends TypeSafeDiagnosingMatcher<ImmutableCharacter>
{
	private final List<PassiveAttribute> expectedPassives = new ArrayList<>();

	@Override
	public void describeTo(final Description description)
	{
		description.appendText("the passives ");
		description.appendValueList("[", ",", "]", expectedPassives);
	}

	@Override
	protected boolean matchesSafely(final ImmutableCharacter item, final Description mismatchDescription)
	{
		final Matcher<Iterable<? extends PassiveAttribute>> matcher = containsInAnyOrder(expectedPassives);
		final Collection<PassiveAttribute> actualPassives = item.getPassives();
		if (!matcher.matches(actualPassives))
		{
			matcher.describeMismatch(actualPassives, mismatchDescription);
			return false;
		}

		return true;
	}

	public PassiveMatcher withPassive(final PassiveSkillAttributeType passive, final float value)
	{
		expectedPassives.add(new PassiveAttribute(passive, value));
		return this;
	}
}
