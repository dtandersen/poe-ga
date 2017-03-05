package poe.repository;

import org.jenetics.Alterer;
import poe.entity.CharacterClass;
import poe.entity.PoeCharacter;
import poe.jenetics.CharacterEvaluator;
import poe.jenetics.SkillGene;

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

		Alterer<SkillGene, Integer>[] getAlterers();
	}

	public interface PoeEvolutionResult
	{
		void setCharacter(PoeCharacter character);

		void setGenerations(long totalGenerations);

		void setFitness(int bestFitness);

		void newBest(EvolutionStatus evolutionStatus);
	}
}
