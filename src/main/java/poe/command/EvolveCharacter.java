package poe.command;

import java.util.List;
import poe.command.EvolveCharacter.EvolveCharacterRequest;
import poe.command.EvolveCharacter.EvolveCharacterResult;
import poe.command.PureImmutableCharacter.ImmutableCharacterBuilder;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.PoeCharacter;
import poe.jenetics.CharacterEvaluator;
import poe.jenetics.CharacterEvaluator.CharacterEvaluatorBuilder;
import poe.repository.Evolver;
import poe.repository.Evolver.PoeEvolutionContext;
import poe.repository.Evolver.PoeEvolutionResult;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;

public class EvolveCharacter extends BaseCommand<EvolveCharacterRequest, EvolveCharacterResult>
{
	private final Evolver evolver;

	public EvolveCharacter(
			final Evolver evolver,
			final PassiveSkillRepository repox,
			final PassiveSkillTree passiveSkillTree)
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

		void newBest(ImmutableCharacter character);
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
			final CharacterEvaluatorBuilder builder = new CharacterEvaluatorBuilder().from(request.getFitnessConfig());
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
		public void newBest(final ImmutableCharacter character)
		{
			result.newBest(character);
		}
	}
}
