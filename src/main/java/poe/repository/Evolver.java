package poe.repository;

import java.util.List;
import poe.command.model.AltererConfig;
import poe.command.model.EvolutionStatus;
import poe.entity.CharacterClass;
import poe.entity.PoeCharacter;
import poe.evaluator.CharacterEvaluator;

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

		List<AltererConfig> getAltererConfig();
	}

	public interface PoeEvolutionResult
	{
		void setCharacter(PoeCharacter character);

		void setGenerations(long totalGenerations);

		void setFitness(float bestFitness);

		void newBest(EvolutionStatus evolutionStatus);
	}
}
