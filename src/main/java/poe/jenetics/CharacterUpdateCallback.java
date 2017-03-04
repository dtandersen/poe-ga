package poe.jenetics;

import java.util.function.Consumer;
import poe.entity.ImmutableCharacter;
import poe.repository.Evolver.PoeEvolutionResult;

public class CharacterUpdateCallback implements Consumer<ImmutableCharacter>
{
	private final PoeEvolutionResult result;

	public CharacterUpdateCallback(final PoeEvolutionResult result)
	{
		this.result = result;
	}

	@Override
	public void accept(final ImmutableCharacter character)
	{
		result.newBest(character);
	}
}
