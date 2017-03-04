package poe.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poe.command.CommandFactory;
import poe.repository.Evolver;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;
import poe.repository.jenetics.JeneticsEvolver;
import poe.repository.json.JsonPassiveSkillRepository;

@Configuration
public class PoeConfig
{
	@Bean
	CommandFactory commandFactory(
			final PassiveSkillRepository repo,
			final Evolver evolver,
			final PassiveSkillTree passiveSkillTree)
	{
		return new CommandFactory(repo, evolver, passiveSkillTree);
	}

	@Bean
	PassiveSkillRepository passiveSkillRepository()
	{
		return new JsonPassiveSkillRepository();
	}

	@Bean
	Evolver evolver(final PassiveSkillTree passiveSkillTree)
	{
		return new JeneticsEvolver(passiveSkillTree);
	}

	@Bean
	PassiveSkillTree passiveSkillTree(final PassiveSkillRepository passiveSkillRepository)
	{
		return new PassiveSkillTree(passiveSkillRepository.all());
	}
}
