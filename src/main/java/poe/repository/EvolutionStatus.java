package poe.repository;

import poe.entity.ImmutableCharacter;

public class EvolutionStatus
{
	private final ImmutableCharacter character;

	private long generation;

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
	}

	public static class EvolutionStatusBuilder
	{
		private ImmutableCharacter character;

		private long generation;

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
	}
}
