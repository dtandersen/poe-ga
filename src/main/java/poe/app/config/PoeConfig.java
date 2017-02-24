package poe.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poe.command.CommandFactory;
import poe.repo.SkillRepo;
import poe.repo.json.JsonSkillRepo;

@Configuration
public class PoeConfig
{
	@Bean
	CommandFactory commandFactory(final SkillRepo repo)
	{
		return new CommandFactory(repo);
	}

	@Bean
	SkillRepo skillRepo()
	{
		return new JsonSkillRepo();
	}
}
