package poe.app.config;

import poe.repository.CharacterView;
import poe.repository.EvolutionStatus;

public class ConsoleCharacterView implements CharacterView
{
	public ConsoleCharacterView()
	{
	}

	@Override
	public void start()
	{
	}

	@Override
	public void update(final EvolutionStatus evolutionStatus)
	{
		// character=evolutionStatus.getCharacter();
	}

	@Override
	public void stop()
	{
	}
}
