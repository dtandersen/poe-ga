package poe.repository;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;
import java.util.List;
import org.hamcrest.Matcher;
import org.junit.Test;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkillTree;
import poe.entity.PoeMatchers;
import poe.entity.Stat;
import poe.matcher.ComposableMatcher;
import poe.repository.json.JsonPassiveSkillRepository;

public class JsonPassiveSkillRepositoryTest
{
	private final JsonPassiveSkillRepository repo = new JsonPassiveSkillRepository();

	@Test
	public void test()
	{
		final List<PassiveSkill> skills = repo.all();

		assertThat(skills, hasItem(PoeMatchers.skill()
				.withName("Herbalism")
				.withId(19858)
				.withAttributes(
						PoeMatchers.attribute(Stat.LIFE_MAX, 10),
						PoeMatchers.attribute(Stat.FLASK_LIFE, 20),
						PoeMatchers.attribute(Stat.FLASK_RECOVERY, 20))));

		assertThat(skills, hasItem(PoeMatchers.skill()
				.withName("Armour Mastery")
				.withId(10542)
				.withAttributes(
						PoeMatchers.attribute(Stat.ARMOUR, 24),
						PoeMatchers.attribute(Stat.MOVEMENT_SPEED, 3),
						PoeMatchers.attribute(Stat.REGEN, 0.5f))));

		assertThat(skills, hasItem(PoeMatchers.skill()
				.withName("Minion Instability")
				.withId(18663)
				.withAttributes(
						PoeMatchers.attribute(Stat.MINION_INSTABILITY, 0))));

		assertThat(skills, hasItem(PoeMatchers.skill()
				.withName("Phase Acrobatics")
				.withId(14914)
				.withAttributes(
						PoeMatchers.attribute(Stat.DODGE_SPELL, 30))));

		assertThat(skills, hasItem(PoeMatchers.skill()
				.withName("Phase Acrobatics")
				.withId(14914)
				.withAttributes(
						PoeMatchers.attribute(Stat.DODGE_SPELL, 30))));
	}

	@Test
	public void skillTree()
	{
		final PassiveSkillTree skillTree = repo.skillTree();

		assertThat(skillTree, hasSkills(
				skill2()
						.withName("RANGER")
						.withNodes(39821,
								45035,
								58427,
								64111,
								56856)));
	}

	private PassiveSkillMatcherChain skill2()
	{
		return new PassiveSkillMatcherChain();
	}

	// private Matcher<PassiveSkillTree> hasSkills2(final Matcher<PassiveSkill>
	// matcher)
	// {
	// return new ComposableMatcher<PassiveSkillTree, Object>(null) {
	// @Override
	// protected Object getValue(final PassiveSkillTree item)
	// {
	// return item.passiveSkills();
	// }
	// };
	// }

	@SafeVarargs
	private final Matcher<PassiveSkillTree> hasSkills(final Matcher<PassiveSkill>... skills)
	{
		final Matcher<Iterable<PassiveSkill>> matcher = hasItems(skills);
		return new ComposableMatcher<PassiveSkillTree, List<PassiveSkill>>(matcher) {
			@Override
			protected List<PassiveSkill> getValue(final PassiveSkillTree item)
			{
				return item.passiveSkills();
			}
		};
	}
}
