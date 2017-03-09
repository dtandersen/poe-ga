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
		evolutionStatus.forEachLineItem(item -> System.out.print("[" + item.getValue() + "] => " + item.getExpression() + "\n"));
	}

	@Override
	public void stop()
	{
	}
}
