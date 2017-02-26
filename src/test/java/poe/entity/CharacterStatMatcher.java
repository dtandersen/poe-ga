package poe.entity;

import static poe.entity.PoeMatchers.containsInAnyOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class CharacterStatMatcher extends TypeSafeDiagnosingMatcher<ImmutableCharacter>
{
	private final List<StatValue> expectedStatValues;

	public CharacterStatMatcher()
	{
		expectedStatValues = new ArrayList<>();
	}

	public CharacterStatMatcher withStatValue(final Stat stat, final float value)
	{
		expectedStatValues.add(new StatValue(stat, value));
		return this;
	}

	@Override
	public void describeTo(final Description description)
	{
		description.appendText("the passives ");
		description.appendValueList("[", ",", "]", expectedStatValues);
	}

	@Override
	protected boolean matchesSafely(final ImmutableCharacter character, final Description mismatchDescription)
	{
		final Matcher<Iterable<? extends StatValue>> matcher = containsInAnyOrder(expectedStatValues);
		final Collection<StatValue> actualPassives = character.getStatValues();
		if (!matcher.matches(actualPassives))
		{
			matcher.describeMismatch(actualPassives, mismatchDescription);
			return false;
		}

		return true;
	}

	public static CharacterStatMatcher passiveMatcher()
	{
		return new CharacterStatMatcher();
	}
}
