package poe.command.model;

import java.util.function.Consumer;
import poe.evaluator.EvaluationResult;
import poe.evaluator.EvaluationResult.EvaluationLineItem;

public class EvolutionStatus
{
	private final ImmutableCharacter character;

	private long generation;

	private EvaluationResult evaluationResult;

	public ImmutableCharacter getCharacter()
	{
		return character;
	}

	public long getGeneration()
	{
		return generation;
	}

	public EvolutionStatus(final ImmutableCharacter character)
	{
		this.character = character;
	}

	public EvolutionStatus(final EvolutionStatusBuilder evolutionStatusBuilder)
	{
		this.character = evolutionStatusBuilder.character;
		this.generation = evolutionStatusBuilder.generation;
		this.evaluationResult = evolutionStatusBuilder.evaluationResult;
	}

	public void forEachLineItem(final Consumer<EvaluationLineItem> consumer)
	{
		evaluationResult.forEachLineItem(consumer);
	}

	public static class EvolutionStatusBuilder
	{
		private ImmutableCharacter character;

		private long generation;

		private EvaluationResult evaluationResult;

		public EvolutionStatusBuilder withCharacter(final ImmutableCharacter character)
		{
			this.character = character;
			return this;
		}

		public EvolutionStatusBuilder withGeneration(final long generation)
		{
			this.generation = generation;
			return this;
		}

		public EvolutionStatus build()
		{
			return new EvolutionStatus(this);
		}

		public EvolutionStatusBuilder withEvaluation(final EvaluationResult evaluationResult)
		{
			this.evaluationResult = evaluationResult;
			return this;
		}
	}
}
