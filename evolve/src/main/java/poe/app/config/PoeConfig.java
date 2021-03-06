package poe.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;
import poe.repository.json.JsonPassiveSkillRepository;

@Configuration
public class PoeConfig
{
	@Bean
	PassiveSkillRepository passiveSkillRepository()
	{
		return new JsonPassiveSkillRepository();
	}

	@Bean
	PassiveSkillTree passiveSkillTree(final PassiveSkillRepository passiveSkillRepository)
	{
		return new PassiveSkillTree(passiveSkillRepository.all());
	}
}
