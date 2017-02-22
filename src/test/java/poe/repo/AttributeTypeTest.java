package poe.repo;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import java.util.List;
import org.hamcrest.Matcher;
import org.junit.Test;
import poe.entity.Attribute;
import poe.entity.AttributeType;
import poe.entity.PassiveSkill;

public class AttributeTypeTest
{
	@Test
	public void test()
	{
		final JsonSkillRepo repo = new JsonSkillRepo();
		final List<PassiveSkill> skills = repo.all();

		assertThat(skills, hasItem(skill()
				.withName("Herbalism")
				.withAttributes(
						attribute(AttributeType.LIFE_MAX, 10),
						attribute(AttributeType.FLASK_LIFE, 20),
						attribute(AttributeType.FLASK_RECOVERY, 20))));

		assertThat(skills, hasItem(skill()
				.withName("Armour Mastery")
				.withAttributes(
						attribute(AttributeType.ARMOUR, 24),
						attribute(AttributeType.MOVEMENT_SPEED, 3),
						attribute(AttributeType.REGEN, 0.5f))));

		assertThat(skills, hasItem(skill()
				.withName("Minion Instability")
				.withAttributes(
						attribute(AttributeType.MINION_INSTABILITY, 0))));

		assertThat(skills, hasItem(skill()
				.withName("Phase Acrobatics")
				.withAttributes(
						attribute(AttributeType.DODGE_SPELL, 30))));

		// assertThat(skills, hasItem(skill()
		// .withName("Ghost Dance")
		// .withAttributes(
		// attribute(AttributeType.ES_FASTER, 20),
		// attribute(AttributeType.DODGE_ATTACK, 5),
		// attribute(AttributeType.DODGE_SPELL, 5),
		// attribute(AttributeType.MOVEMENT_ENERGY_SHIELD, 10))));

		assertThat(skills, hasItem(skill()
				.withName("Phase Acrobatics")
				.withAttributes(
						attribute(AttributeType.DODGE_SPELL, 30))));
	}

	private Matcher<Attribute> attribute(final AttributeType attributeType, final float value)
	{
		return new AttributeMatcher(attributeType, value);
	}

	private PassiveSkillMatcher skill()
	{
		return new PassiveSkillMatcher();
	}
}
