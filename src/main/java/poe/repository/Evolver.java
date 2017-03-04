package poe.repository;

import poe.entity.CharacterClass;
import poe.entity.PoeCharacter;
import poe.repository.jenetics.CharacterEvaluator;

public interface Evolver
{
	void evolve(PoeEvolutionContext evolutionContext, PoeEvolutionResult evolutionResult);

	public interface PoeEvolutionContext
	{
		long getGenerationLimit();

		CharacterClass getCharacterClass();

		CharacterEvaluator getCharacterEvaluator();

		int getPopulation();

		int getSkills();

		int getThreads();
	}

	public interface PoeEvolutionResult
	{
		void setCharacter(PoeCharacter character);

		void setGenerations(long totalGenerations);

		void setFitness(int bestFitness);
	}
}
