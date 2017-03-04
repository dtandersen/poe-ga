package poe.repository;

import poe.entity.PoeCharacter;

public class CharacterEvolutionResult
{
	private final PoeCharacter character;

	private final long generations;

	public CharacterEvolutionResult(final PoeCharacter character, final long generations)
	{
		this.character = character;
		this.generations = generations;
	}

	public PoeCharacter getCharacter()
	{
		return character;
	}

	public long getGenerations()
	{
		return generations;
	}
}
