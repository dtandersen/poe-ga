package poe.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poe.repo.JsonSkillRepo;
import poe.repo.SkillRepo;

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
