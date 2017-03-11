package poe.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poe.app.evolve.CharacterView;
import poe.app.evolve.ConsoleCharacterView;
import poe.app.evolve.MultiCharacterView;
import poe.app.evolve.selenium.SeleniumCharacterView;
import poe.command.CommandFactory;
import poe.repository.Evolver;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;
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
		return new CommandFactory(repo, evolver);
	}

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

	@Bean
	CharacterView viewer()
	{
		return new MultiCharacterView(
				new SeleniumCharacterView(),
				new ConsoleCharacterView());
	}
}
