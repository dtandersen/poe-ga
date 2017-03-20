package poe.ga;

import org.junit.Test;

public class GaTest
{
	@Test
	public void evolve()
	{
		final Population population = new Population(new IndividualFactory() {
			@Override
			public Individual newInstance()
			{
				return new Individual();
			}
		});

		final Alterer alterer = CompositeAlterer.of(new Mutator());
		final EvolutionEngine engine = new EvolutionEngine(population, alterer);
		// engine.stream().limit(1).collect(EvolutionResult.toBest());
	}
}
