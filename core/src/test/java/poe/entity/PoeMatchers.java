package poe.entity;

import static org.hamcrest.Matchers.equalTo;
import static org.hobsoft.hamcrest.compose.ComposeMatchers.compose;
import static org.hobsoft.hamcrest.compose.ComposeMatchers.hasFeature;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import poe.command.model.ImmutableCharacter;
import poe.command.model.ImmutableCharacter.ImmutablePassiveSkill;
import poe.entity.PassiveSkill.PassiveSkillBuilder;

public class PoeMatchers
{
	public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(final List<T> values)
	{
		return org.hamcrest.Matchers.containsInAnyOrder(ListUtil.listToArray(values));
	}

	public static Matcher<PassiveSkill> passiveSkillEqualTo(final PassiveSkillBuilder expected)
	{
		final PassiveSkill passiveSkill = expected.build();
		return compose("a passive skill with",
				hasFeature("name", PassiveSkill::getName, equalTo(passiveSkill.getName())))
						.and(hasFeature("id", PassiveSkill::getId, equalTo(passiveSkill.getId())))
						.and(hasFeature("outputs", PassiveSkill::getOutputs, equalTo(passiveSkill.getOutputs())))
						.and(hasFeature("stats", PassiveSkill::getStats, containsInAnyOrder(passiveSkill.getStats())))
						.and(hasFeature("classStartingPoint", PassiveSkill::getClassStartingPoint, equalTo(passiveSkill.getClassStartingPoint())))
						.and(hasFeature("jewels", PassiveSkill::getJewels, equalTo(passiveSkill.getJewels())));
	}

	public static CharacterStatMatcher hasStats2()
	{
		return CharacterStatMatcher.passiveMatcher();
	}

	public static StatValuesMatcher hasStats()
	{
		return StatValuesMatcher.hasStats();
	}

	public static Matcher<ImmutableCharacter> hasPassives(final Matcher<Iterable<? extends ImmutablePassiveSkill>> matcher)
	{
		return new TypeSafeDiagnosingMatcher<ImmutableCharacter>() {
			@Override
			public void describeTo(final Description description)
			{
				description.appendText("a character with ");
				matcher.describeTo(description);
			}

			@Override
			protected boolean matchesSafely(final ImmutableCharacter item, final Description mismatchDescription)
			{
				if (!matcher.matches(item.getPassiveSkills()))
				{
					matcher.describeMismatch(item.getPassiveSkills(), mismatchDescription);
					return false;
				}

				return true;
			}
		};
	}

	public static Matcher<ImmutablePassiveSkill> passiveEqualTo(final ImmutablePassiveSkill expected)
	{
		return compose("a passive skill with",
				hasFeature("name", ImmutablePassiveSkill::getName, equalTo(expected.getName())));
	}

	public static Matcher<ImmutableCharacter> hasPassives(final ImmutablePassiveSkill... classPassive)
	{
		final Collection<Matcher<? super ImmutablePassiveSkill>> x = new ArrayList<>();
		for (final ImmutablePassiveSkill ps : classPassive)
		{
			x.add(passiveEqualTo(ps));
		}
		final Matcher<Iterable<? extends ImmutablePassiveSkill>> containsInAnyOrder = Matchers.containsInAnyOrder(x);

		return hasPassives(containsInAnyOrder);
	}
}
