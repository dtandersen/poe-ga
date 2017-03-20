package poe.repository;

import java.util.Random;

public class JavaRandomizer implements Randomizer
{
	private final Random random = new Random();

	@Override
	public int nextInt(final int range)
	{
		return random.nextInt(range);
	}
}
