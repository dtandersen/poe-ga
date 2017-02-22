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
						attribute(AttributeType.MAX_LIFE, 10),
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
