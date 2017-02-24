package poe.repo;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import java.util.List;
import org.hamcrest.Matcher;
import org.junit.Test;
import poe.entity.Attribute;
import poe.entity.AttributeDescription;
import poe.entity.PassiveSkill;

public class JsonSkillRepoTest
{
	@Test
	public void test()
	{
		final JsonSkillRepo repo = new JsonSkillRepo();
		final List<PassiveSkill> skills = repo.all();

		assertThat(skills, hasItem(skill()
				.withName("Herbalism")
				.withId(19858)
				.withAttributes(
						attribute(AttributeDescription.LIFE_MAX, 10),
						attribute(AttributeDescription.FLASK_LIFE, 20),
						attribute(AttributeDescription.FLASK_RECOVERY, 20))));

		assertThat(skills, hasItem(skill()
				.withName("Armour Mastery")
				.withId(10542)
				.withAttributes(
						attribute(AttributeDescription.ARMOUR, 24),
						attribute(AttributeDescription.MOVEMENT_SPEED, 3),
						attribute(AttributeDescription.REGEN, 0.5f))));

		assertThat(skills, hasItem(skill()
				.withName("Minion Instability")
				.withId(18663)
				.withAttributes(
						attribute(AttributeDescription.MINION_INSTABILITY, 0))));

		assertThat(skills, hasItem(skill()
				.withName("Phase Acrobatics")
				.withId(14914)
				.withAttributes(
						attribute(AttributeDescription.DODGE_SPELL, 30))));

		// assertThat(skills, hasItem(skill()
		// .withName("Ghost Dance")
		// .withAttributes(
		// attribute(AttributeType.ES_FASTER, 20),
		// attribute(AttributeType.DODGE_ATTACK, 5),
		// attribute(AttributeType.DODGE_SPELL, 5),
		// attribute(AttributeType.MOVEMENT_ENERGY_SHIELD, 10))));

		assertThat(skills, hasItem(skill()
				.withName("Phase Acrobatics")
				.withId(14914)
				.withAttributes(
						attribute(AttributeDescription.DODGE_SPELL, 30))));
	}

	private Matcher<Attribute> attribute(final AttributeDescription attributeType, final float value)
	{
		return new AttributeMatcher(attributeType, value);
	}

	private PassiveSkillMatcher skill()
	{
		return new PassiveSkillMatcher();
	}
}
