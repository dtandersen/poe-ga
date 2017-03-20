package poe.ga;

import java.util.stream.Stream;

public class EvolutionEngine
{
	private final Population population;

	private final Alterer alterer;

	public EvolutionEngine(final Population population, final Alterer alterer)
	{
		this.population = population;
		this.alterer = alterer;
	}

	public void start()
	{
	}

	public EvolutionResult process()
	{
		return new EvolutionResult();
	}

	public Stream<EvolutionResult> stream()
	{
		// return new EvolutionSteam();
		return Stream.generate(this::process);
	}

}
