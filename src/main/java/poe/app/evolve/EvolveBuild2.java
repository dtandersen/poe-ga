package poe.app.evolve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import poe.command.CommandFactory;
import poe.command.EvolveCharacter;
import poe.command.EvolveCharacter.EvolveCharacterRequest;
import poe.command.EvolveCharacter.EvolveCharacterResult;
import poe.command.PoeComUrlBuilder;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.ImmutableCharacter.ImmutablePassiveSkill;
import poe.repository.PassiveSkillRepository;

@SpringBootApplication
@ComponentScan(basePackages = { "poe.app.config" }, excludeFilters = {})
public class EvolveBuild2 implements CommandLineRunner
{
	@Autowired
	private CommandFactory commandFactory;

	@Autowired
	PassiveSkillRepository passiveSkillRepository;

	@Override
	public void run(final String... args)
	{
		final EvolveCharacter command = commandFactory.evolveCharacter();
		command.setRequest(new EvolveCharacterRequest() {
			@Override
			public CharacterClass getCharacterClass()
			{
				return CharacterClass.MARAUDER;
			}
		});
		command.setResult(new EvolveCharacterResult() {
			@Override
			public void setCharacter(final ImmutableCharacter character)
			{
				for (final ImmutablePassiveSkill passiveSkill : character.getPassiveSkills())
				{
					System.out.println(passiveSkill.getName());
				}

				System.out.println(new PoeComUrlBuilder()
						.withCharacterClass(CharacterClass.MARAUDER)
						.withPassiveSkillIds(character.getPassiveSkillIds())
						.toUrl());
			}
		});
		command.execute();
	}

	public static void main(final String[] args) throws Exception
	{
		SpringApplication.run(EvolveBuild2.class, args);
	}
}
