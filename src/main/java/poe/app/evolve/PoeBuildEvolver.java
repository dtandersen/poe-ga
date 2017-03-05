package poe.app.evolve;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.jenetics.Alterer;
import org.jenetics.Mutator;
import org.jenetics.SinglePointCrossover;
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
import poe.jenetics.NeighboringSkillMutator;
import poe.jenetics.SkillGene;
import poe.repository.CharacterView;
import poe.repository.EvolutionStatus;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;

@SpringBootApplication
@ComponentScan(basePackages = { "poe.app.config" }, excludeFilters = {})
public class PoeBuildEvolver implements CommandLineRunner
{
	private static final String CONFIG = "necromancer.yaml";

	@Autowired
	private CommandFactory commandFactory;

	@Autowired
	PassiveSkillRepository passiveSkillRepository;

	@Autowired
	PassiveSkillTree passiveSkillTree;

	@Autowired
	CharacterView characterView;

	@Override
	public void run(final String... args) throws FileNotFoundException
	{
		characterView.start();

		try
		{
			final EvolveCharacter command = commandFactory.evolveCharacter();
			final EvolveCharacterRequestImplementation request = new EvolveCharacterRequestImplementation(CONFIG, passiveSkillTree);
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

		private final List<Alterer<SkillGene, Integer>> alterers = new ArrayList<>();

		private final PassiveSkillTree passiveSkillTree;

		public EvolveCharacterRequestImplementation(final String filename, final PassiveSkillTree passiveSkillTree) throws FileNotFoundException
		{
			this.passiveSkillTree = passiveSkillTree;
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

		@Override
		public List<Alterer<SkillGene, Integer>> getAlterers2()
		{
			alterers.add(new Mutator<>(.05));
			alterers.add(new NeighboringSkillMutator(.05f, passiveSkillTree));
			alterers.add(new SinglePointCrossover<>(.2));

			return alterers;
		}
	}
}
