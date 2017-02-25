package poe.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poe.command.CommandFactory;
import poe.repository.PassiveSkillRepository;
import poe.repository.json.JsonPassiveSkillRepository;

@Configuration
public class PoeConfig
{
	@Bean
	CommandFactory commandFactory(final PassiveSkillRepository repo)
	{
		return new CommandFactory(repo);
	}

	@Bean
	PassiveSkillRepository skillRepo()
	{
		return new JsonPassiveSkillRepository();
	}
}
