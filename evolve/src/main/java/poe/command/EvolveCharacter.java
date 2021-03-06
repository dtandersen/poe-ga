package poe.command;

import java.util.List;
import java.util.stream.Collectors;
import poe.command.EvolveCharacter.EvolveCharacterRequest;
import poe.command.EvolveCharacter.EvolveCharacterResult;
import poe.command.model.AltererConfig;
import poe.command.model.EvolutionStatus;
import poe.command.model.FitnessConfig;
import poe.command.model.ImmutableCharacter;
import poe.command.model.ItemDescription;
import poe.command.model.PureImmutableCharacter.ImmutableCharacterBuilder;
import poe.entity.CharacterInstance.PoeCharacterEditor;
import poe.entity.CharacterClass;
import poe.entity.CharacterItem;
import poe.entity.CharacterItem.CharacterItemBuilder;
import poe.entity.StatParser;
import poe.evaluator.CharacterEvaluator;
import poe.evaluator.spring.SpringCharacterEvaluator.SpringCharacterEvaluatorBuilder;
import poe.repository.Evolver;
import poe.repository.Evolver.PoeEvolutionContext;
import poe.repository.Evolver.PoeEvolutionResult;

public class EvolveCharacter extends BaseCommand<EvolveCharacterRequest, EvolveCharacterResult>
{
	private final Evolver evolver;

	public EvolveCharacter(final Evolver evolver)
	{
		this.evolver = evolver;
	}

	@Override
	public void execute()
	{
		evolver.evolve(
				new EvolutionContextAdapter(request),
				new EvolutionResultAdapter(result));
	}

	public interface EvolveCharacterRequest
	{
		String getCharacterClass();

		List<AltererConfig> getAlterers();

		long getGenerationLimit();

		long getPopulation();

		FitnessConfig getFitnessConfig();

		int getSkillPoints();

		int getThreads();

		int getLevel();

		List<ItemDescription> getItems();

		int getGenes();

		int getChromosomes();
	}

	public interface EvolveCharacterResult
	{
		void setCharacter(ImmutableCharacter character);

		void setGenerations(long generations);

		void setFitness(float fitness);

		void newBest(EvolutionStatus evolutionStatus);
	}

	private final class EvolutionContextAdapter implements PoeEvolutionContext
	{
		private final EvolveCharacterRequest request;

		public EvolutionContextAdapter(final EvolveCharacterRequest request)
		{
			this.request = request;
		}

		@Override
		public long getGenerationLimit()
		{
			return request.getGenerationLimit();
		}

		@Override
		public CharacterClass getCharacterClass()
		{
			return CharacterClass.find(request.getCharacterClass());
		}

		@Override
		public CharacterEvaluator getCharacterEvaluator()
		{
			final SpringCharacterEvaluatorBuilder builder = new SpringCharacterEvaluatorBuilder()
					.from(request.getFitnessConfig());
			return builder.build();
		}

		@Override
		public int getPopulation()
		{
			return (int)request.getPopulation();
		}

		@Override
		public int getSkillPoints()
		{
			return request.getSkillPoints();
		}

		@Override
		public int getThreads()
		{
			return request.getThreads();
		}

		@Override
		public List<AltererConfig> getAltererConfig()
		{
			return request.getAlterers();
		}

		@Override
		public int getLevel()
		{
			return request.getLevel();
		}

		@Override
		public List<CharacterItem> getItems()
		{
			return request.getItems().stream()
					.map(item -> {
						final CharacterItemBuilder builder = CharacterItemBuilder.item();
						item.getSkillDescriptions().forEach(description -> builder.withStatValue(new StatParser().parseItem(description)));
						return builder.build();
					})
					.collect(Collectors.toList());
		}

		@Override
		public int getGenes()
		{
			return request.getGenes();
		}

		@Override
		public int getChromosomes()
		{
			return request.getChromosomes();
		}
	}

	private final class EvolutionResultAdapter implements PoeEvolutionResult
	{
		private final EvolveCharacterResult result;

		public EvolutionResultAdapter(final EvolveCharacterResult result)
		{
			this.result = result;
		}

		@Override
		public void setCharacter(final PoeCharacterEditor character)
		{
			result.setCharacter(ImmutableCharacterBuilder.character()
					.withPassiveSkills(character.getPassiveSkillsWithoutRoot())
					.withCharacterClass(character.getCharacterClass())
					.build());
		}

		@Override
		public void setGenerations(final long totalGenerations)
		{
			result.setGenerations(totalGenerations);
		}

		@Override
		public void setFitness(final float fitness)
		{
			result.setFitness(fitness);
		}

		@Override
		public void newBest(final EvolutionStatus evolutionStatus)
		{
			result.newBest(evolutionStatus);
		}
	}
}
