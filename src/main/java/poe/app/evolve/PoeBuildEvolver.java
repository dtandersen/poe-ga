package poe.app.evolve;

import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import poe.app.evolve.EvolveConfig.MyEvaluator;
import poe.command.AltererConfig;
import poe.command.CommandFactory;
import poe.command.EvolveCharacter;
import poe.command.EvolveCharacter.EvolveCharacterRequest;
import poe.command.EvolveCharacter.EvolveCharacterResult;
import poe.command.FitnessConfig;
import poe.command.FitnessConfig.ElementConfig.ElementConfigBuilder;
import poe.command.FitnessConfig.FitnessConfigBuilder;
import poe.command.PoeComUrlBuilder;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.ImmutableCharacter.ImmutablePassiveSkill;
import poe.repository.PassiveSkillRepository;

@SpringBootApplication
@ComponentScan(basePackages = { "poe.app.config" }, excludeFilters = {})
public class PoeBuildEvolver implements CommandLineRunner
{
	private final class EvolveCharacterRequestImplementation implements EvolveCharacterRequest
	{
		private final EvolveConfig evolveConfig;

		public EvolveCharacterRequestImplementation() throws FileNotFoundException
		{
			evolveConfig = ConfigParser.read();
		}

		@Override
		public CharacterClass getCharacterClass()
		{
			return CharacterClass.find(evolveConfig.getCharacterClass());
		}

		@Override
		public List<AltererConfig> getAlterers()
		{
			return null;
		}

		@Override
		public long getGenerationLimit()
		{
			return evolveConfig.getGenerations();
		}

		@Override
		public long getPopulation()
		{
			return evolveConfig.getPopulation();
		}

		@Override
		public int getSkills()
		{
			return evolveConfig.getSkills();
		}

		@Override
		public int getThreads()
		{
			return Runtime.getRuntime().availableProcessors();
		}

		@Override
		public FitnessConfig getFitnessConfig()
		{
			final FitnessConfigBuilder fitnessConfigBuilder = new FitnessConfigBuilder();
			for (final MyEvaluator expr : evolveConfig.getEvaluators())
			{
				fitnessConfigBuilder.withElement(new ElementConfigBuilder().withExpression(expr.getExpression()));
			}

			return fitnessConfigBuilder.build();

		}
	}

	@Autowired
	private CommandFactory commandFactory;

	@Autowired
	PassiveSkillRepository passiveSkillRepository;

	@Override
	public void run(final String... args) throws FileNotFoundException
	{
		final EvolveCharacter command = commandFactory.evolveCharacter();
		command.setRequest(new EvolveCharacterRequestImplementation());
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

			@Override
			public void setGenerations(final long generations)
			{
			}

			@Override
			public void setFitness(final int fitness)
			{
			}
		});
		command.execute();
	}

	public static void main(final String[] args) throws Exception
	{
		SpringApplication.run(PoeBuildEvolver.class, args);
	}
}
