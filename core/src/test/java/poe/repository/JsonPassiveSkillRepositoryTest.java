package poe.repository;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static poe.entity.PassiveSkill.PassiveSkillBuilder.passiveSkill;
import static poe.entity.StatValue.StatBuilder.stat;
import java.util.List;
import java.util.Objects;
import org.hamcrest.Matcher;
import org.junit.Test;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PoeMatchers;
import poe.entity.Stat;
import poe.matcher.ComposableMatcher;
import poe.repository.json.JsonPassiveSkillRepository;

public class JsonPassiveSkillRepositoryTest
{
	private final JsonPassiveSkillRepository repo = new JsonPassiveSkillRepository();

	private List<PassiveSkill> skills;

	@Test
	public void test()
	{
		skills = repo.all();

		assertThat(passiveNamed("Herbalism"), PoeMatchers.passiveSkillEqualTo(
				passiveSkill()
						.withName("Herbalism")
						.withId(19858)
						.withOutputs(9206)
						.withStats(
								stat(Stat.INCRESED_MAXIMUM_LIFE, 10),
								stat(Stat.FLASK_LIFE, 20),
								stat(Stat.FLASK_RECOVERY, 20))));

		assertThat(passiveNamed("Armour Mastery"), PoeMatchers.passiveSkillEqualTo(
				passiveSkill()
						.withName("Armour Mastery")
						.withId(10542)
						.withOutputs()
						.withStats(
								stat(Stat.ARMOUR, 24),
								stat(Stat.MOVEMENT_SPEED, 3),
								stat(Stat.REGEN, 0.5f))));

		assertThat(passiveNamed("Minion Instability"), PoeMatchers.passiveSkillEqualTo(
				passiveSkill()
						.withName("Minion Instability")
						.withId(18663)
						.withOutputs()
						.withStats(stat(Stat.MINION_INSTABILITY, 0))));

		assertThat(passiveNamed("Phase Acrobatics"), PoeMatchers.passiveSkillEqualTo(
				passiveSkill()
						.withName("Phase Acrobatics")
						.withId(14914)
						.withOutputs()
						.withStats(stat(Stat.DODGE_SPELL, 30))));

		assertThat(passiveNamed("MARAUDER"), PoeMatchers.passiveSkillEqualTo(
				passiveSkill()
						.withName("MARAUDER")
						.withId(47175)
						.withOutputs(31628, 50904, 17765, 24704, 29294)
						.withStats()
						.withClassStartingPoint(CharacterClass.MARAUDER)));

		assertThat("Don't include ascendency skills", passiveNamed("Fast and Deadly"), nullValue());
	}

	private PassiveSkill passiveNamed(final String name)
	{
		for (final PassiveSkill passiveSkill : skills)
		{
			if (Objects.equals(name, passiveSkill.getName())) { return passiveSkill; }
		}

		return null;
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
