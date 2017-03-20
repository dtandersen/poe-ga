package poe.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poe.app.evolve.CharacterView;
import poe.app.evolve.ConsoleCharacterView;
import poe.app.evolve.MultiCharacterView;
import poe.app.evolve.selenium.SeleniumCharacterView;
import poe.jenetics.AltererFactory;
import poe.jenetics.JeneticsEvolver;
import poe.repository.Evolver;
import poe.repository.PassiveSkillTree;

@Configuration
public class JeneticsConfig
{
	@Bean
	Evolver evolver(
			final PassiveSkillTree passiveSkillTree,
			final AltererFactory altererFactory)
	{
		return new JeneticsEvolver(passiveSkillTree, altererFactory);
	}

	@Bean
	AltererFactory altererFactory(final PassiveSkillTree passiveSkillTree)
	{
		return new AltererFactory(passiveSkillTree);
	}

	@Bean
	CharacterView viewer()
	{
		return new MultiCharacterView(
				new SeleniumCharacterView(),
				new ConsoleCharacterView());
	}
}
