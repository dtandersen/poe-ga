package us.davidandersen.poe.command;

import org.junit.jupiter.api.Test;
import poe.command.SkillValidator;
import poe.entity.PassiveSkill.PassiveSkillBuilder;
import poe.entity.StatValue.StatBuilder;
import poe.repository.InMemoryPassiveSkillRepository;
import poe.repository.InMemoryStatRepository;

class SkillValidatorTest
{
	@Test
	void test()
	{
		final InMemoryStatRepository statRepo = new InMemoryStatRepository();
		final InMemoryPassiveSkillRepository passiveSkillRepo = new InMemoryPassiveSkillRepository();

		passiveSkillRepo.create(PassiveSkillBuilder.passiveSkill()
				.withName("a")
				.withStats(
						StatBuilder.stat()
								.withDescription("a1"),
						StatBuilder.stat()
								.withDescription("a2")));

		final SkillValidator command = new SkillValidator(passiveSkillRepo, statRepo);
		command.execute();
	}
}
