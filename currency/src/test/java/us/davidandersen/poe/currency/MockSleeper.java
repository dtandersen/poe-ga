package us.davidandersen.poe.currency;

public class MockSleeper implements Sleeper
{
	private int sleep;

	@Override
	public void sleep()
	{
		sleep++;
	}

	public int count()
	{
		return sleep;
	}
}
