package poe.entity;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import poe.command.CreateCharacterTest;
import poe.command.CreateCharacterTest.CreateCharacterResultImplementation;
import poe.matcher.ComposableMatcher;

public class PoeMatchers
{
	public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(final List<T> values)
	{
		return org.hamcrest.Matchers.containsInAnyOrder(MatcherHelper.listToArray(values));
	}

	public static PassiveSkillMatcher skill()
	{
		return PassiveSkillMatcher.skill();
	}

	public static StatValueMatcher attribute(final Stat stat, final float value)
	{
		return StatValueMatcher.attribute(stat, value);
	}

	public static CharacterStatMatcher hasStats2()
	{
		return CharacterStatMatcher.passiveMatcher();
	}

	public static StatValuesMatcher hasStats()
	{
		return StatValuesMatcher.hasStats();
	}

	public static Matcher<ImmutableCharacter> hasPassiveSkills(final Integer... expectedPassiveSkillIds)
	{
		final List<Integer> asList = Arrays.asList(expectedPassiveSkillIds);
		final Matcher<Iterable<? extends Integer>> matcher = containsInAnyOrder(asList);
		return new TypeSafeDiagnosingMatcher<ImmutableCharacter>() {
			@Override
			public void describeTo(final Description description)
			{
				description.appendText("a character with passives ");
				description.appendValueList("", ",", "", expectedPassiveSkillIds);
			}
	
			@Override
			protected boolean matchesSafely(final ImmutableCharacter item, final Description mismatchDescription)
			{
				final List<Integer> actualIds = item.getPassiveSkillIds();
				if (!matcher.matches(actualIds))
				{
					matcher.describeMismatch(actualIds, mismatchDescription);
					return false;
				}
				return true;
			}
		};
	}

	public static Matcher<CreateCharacterResultImplementation> hasUrl(final Matcher<String> matcher)
	{
		return new ComposableMatcher<CreateCharacterTest.CreateCharacterResultImplementation, String>(matcher) {
			@Override
			protected String getValue(final CreateCharacterResultImplementation item)
			{
				return item.getUrl();
			}
		};
	}
}
