package poe.app.evolve;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import poe.app.evolve.YamlConfig.YamlAlterer;
import poe.app.evolve.YamlConfig.YamlEvaluator;
import poe.command.CommandFactory;
import poe.command.EvolveCharacter;
import poe.command.EvolveCharacter.EvolveCharacterRequest;
import poe.command.EvolveCharacter.EvolveCharacterResult;
import poe.command.model.AltererConfig;
import poe.command.model.EvolutionStatus;
import poe.command.model.FitnessConfig;
import poe.command.model.FitnessConfig.ElementConfig.ElementConfigBuilder;
import poe.command.model.FitnessConfig.FitnessConfigBuilder;
import poe.command.model.ImmutableCharacter;
import poe.command.model.ImmutableCharacter.ImmutablePassiveSkill;
import poe.command.model.ItemDescription;
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
			final YamlEvolveCharacterRequest request = new YamlEvolveCharacterRequest(CONFIG);
			command.setRequest(request);
			command.setResult(new EvolveCharacterResult() {
				@Override
				public void setCharacter(final ImmutableCharacter character)
				{
					for (final ImmutablePassiveSkill passiveSkill : character.getPassiveSkills())
					{
						System.out.println(passiveSkill.getName());
					}

					System.out.println(character.getUrl());
				}

				@Override
				public void setGenerations(final long generations)
				{
				}

				@Override
				public void setFitness(final float fitness)
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

	public static class YamlEvolveCharacterRequest implements EvolveCharacterRequest
	{
		private final YamlConfig evolveConfig;

		public YamlEvolveCharacterRequest(final String filename) throws FileNotFoundException
		{
			evolveConfig = ConfigParser.read(filename);
		}

		@Override
		public String getCharacterClass()
		{
			return evolveConfig.getCharacterClass();
		}

		@Override
		public List<AltererConfig> getAlterers()
		{
			return StreamUtils.mapList(evolveConfig.getAlterers(), (final YamlAlterer t) -> new AltererConfig(t.getType(), t.getProbability()));
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

			for (final YamlEvaluator expr : evolveConfig.getEvaluators())
			{
				fitnessConfigBuilder.withElement(new ElementConfigBuilder().withExpression(expr.getExpression()));
			}

			return fitnessConfigBuilder.build();
		}

		@Override
		public int getLevel()
		{
			return evolveConfig.getLevel();
		}

		@Override
		public List<ItemDescription> getItems()
		{
			return evolveConfig.getItems().stream()
					.map(item -> {
						final ItemDescription i = new ItemDescription();
						item.getStats().forEach(stat -> i.addSkillDescription(stat.stat));
						return i;
					})
					.collect(Collectors.toList());
		}
	}
}
