package poe.repo;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import java.util.List;
import org.junit.Test;
import poe.entity.PassiveSkill;
import poe.entity.PoeMatchers;
import poe.entity.Stat;
import poe.repo.json.JsonSkillRepo;

public class JsonSkillRepoTest
{
	@Test
	public void test()
	{
		final JsonSkillRepo repo = new JsonSkillRepo();
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
}
