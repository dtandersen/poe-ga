package poe.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poe.command.CommandFactory;
import poe.repository.Evolver;
import poe.repository.PassiveSkillRepository;
import poe.repository.jenetics.JeneticsEvolver;
import poe.repository.json.JsonPassiveSkillRepository;

@Configuration
public class PoeConfig
{
	@Bean
	CommandFactory commandFactory(
			final PassiveSkillRepository repo,
			final Evolver evolver)
	{
		return new CommandFactory(repo, evolver);
	}

	@Bean
	PassiveSkillRepository skillRepo()
	{
		return new JsonPassiveSkillRepository();
	}

	@Bean
	Evolver evolver()
	{
		return new JeneticsEvolver();
	}
}
