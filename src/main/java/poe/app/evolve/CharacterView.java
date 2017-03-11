package poe.app.evolve;

import poe.command.model.EvolutionStatus;

public interface CharacterView
{
	void start();

	void update(EvolutionStatus evolutionStatus);

	void stop();
}
