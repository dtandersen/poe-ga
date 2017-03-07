package poe.app.evolve;

import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import poe.app.evolve.EvolveConfig.MyAlterer;
import poe.app.evolve.EvolveConfig.MyEvaluator;
import poe.command.CommandFactory;
import poe.command.EvolveCharacter;
import poe.command.EvolveCharacter.EvolveCharacterRequest;
import poe.command.EvolveCharacter.EvolveCharacterResult;
import poe.command.PoeComUrlBuilder;
import poe.entity.AltererConfig;
import poe.entity.CharacterClass;
import poe.entity.FitnessConfig;
import poe.entity.FitnessConfig.ElementConfig.ElementConfigBuilder;
import poe.entity.FitnessConfig.FitnessConfigBuilder;
import poe.entity.ImmutableCharacter;
import poe.entity.ImmutableCharacter.ImmutablePassiveSkill;
import poe.repository.CharacterView;
import poe.repository.EvolutionStatus;
import poe.util.StreamUtils;

@SpringBootApplication
@ComponentScan(basePackages = { "poe.app.config" }, excludeFilters = {})
public class PoeBuildEvolver implements CommandLineRunner
{
	private static final String CONFIG = "necromancer.yaml";

	@Autowired
	private CommandFactory commandFactory;

	@Autowired
	private CharacterView characterView;

	@Override
	public void run(final String... args) throws FileNotFoundException
	{
		characterView.start();

		try
		{
			final EvolveCharacter command = commandFactory.evolveCharacter();
			final EvolveCharacterRequestImplementation request = new EvolveCharacterRequestImplementation(CONFIG);
			command.setRequest(request);
			command.setResult(new EvolveCharacterResult() {
				@Override
				public void setCharacter(final ImmutableCharacter character)
				{
					for (final ImmutablePassiveSkill passiveSkill : character.getPassiveSkills())
					{
						System.out.println(passiveSkill.getName());
					}

					System.out.println(new PoeComUrlBuilder()
							.withCharacterClass(character.getCharacterClass())
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

				@Override
				public void newBest(final EvolutionStatus evolutionStatus)
				{
					characterView.update(evolutionStatus);
				}
			});
			command.execute();
		}
		finally
		{
			characterView.stop();
		}
	}

	public static void main(final String[] args) throws Exception
	{
		SpringApplication.run(PoeBuildEvolver.class, args);
	}

	public static class EvolveCharacterRequestImplementation implements EvolveCharacterRequest
	{
		private final EvolveConfig evolveConfig;

		public EvolveCharacterRequestImplementation(final String filename) throws FileNotFoundException
		{
			evolveConfig = ConfigParser.read(filename);
		}

		@Override
		public CharacterClass getCharacterClass()
		{
			return CharacterClass.find(evolveConfig.getCharacterClass());
		}

		@Override
		public List<AltererConfig> getAlterers()
		{
			return StreamUtils.mapList(evolveConfig.getAlterers(), (final MyAlterer t) -> new AltererConfig(t.getType(), t.getProbability()));
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
}
