package poe.jenetics;

import java.util.function.Consumer;
import poe.repository.EvolutionStatus;
import poe.repository.Evolver.PoeEvolutionResult;

public class CharacterUpdateCallback implements Consumer<EvolutionStatus>
{
	private final PoeEvolutionResult result;

	public CharacterUpdateCallback(final PoeEvolutionResult result)
	{
		this.result = result;
	}

	@Override
	public void accept(final EvolutionStatus evolutionStatus)
	{
		result.newBest(evolutionStatus);
	}
}
