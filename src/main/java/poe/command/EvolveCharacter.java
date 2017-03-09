package poe.command;

import java.util.List;
import poe.command.EvolveCharacter.EvolveCharacterRequest;
import poe.command.EvolveCharacter.EvolveCharacterResult;
import poe.command.PureImmutableCharacter.ImmutableCharacterBuilder;
import poe.entity.AltererConfig;
import poe.entity.CharacterClass;
import poe.entity.FitnessConfig;
import poe.evaluator.CharacterEvaluator;
import poe.evaluator.spring.SpringCharacterEvaluator.SpringCharacterEvaluatorBuilder;
import poe.entity.ImmutableCharacter;
import poe.entity.PoeCharacter;
import poe.repository.EvolutionStatus;
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
		CharacterClass getCharacterClass();

		List<AltererConfig> getAlterers();

		long getGenerationLimit();

		long getPopulation();

		FitnessConfig getFitnessConfig();

		int getSkills();

		int getThreads();
	}

	public interface EvolveCharacterResult
	{
		void setCharacter(ImmutableCharacter character);

		void setGenerations(long generations);

		void setFitness(int fitness);

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
			return request.getCharacterClass();
		}

		@Override
		public CharacterEvaluator getCharacterEvaluator()
		{
			final SpringCharacterEvaluatorBuilder builder = new SpringCharacterEvaluatorBuilder().from(request.getFitnessConfig());
			return builder.build();
		}

		@Override
		public int getPopulation()
		{
			return (int)request.getPopulation();
		}

		@Override
		public int getSkills()
		{
			return request.getSkills();
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
	}

	private final class EvolutionResultAdapter implements PoeEvolutionResult
	{
		private final EvolveCharacterResult result;

		public EvolutionResultAdapter(final EvolveCharacterResult result)
		{
			this.result = result;
		}

		@Override
		public void setCharacter(final PoeCharacter character)
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
		public void setFitness(final int fitness)
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
