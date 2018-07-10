package us.davidandersen.poe.currency.app;

import us.davidandersen.poe.currency.Sleeper;

public class MySleeper implements Sleeper
{
	@Override
	public void sleep()
	{
		try
		{
			Thread.sleep(1000);
		}
		catch (final InterruptedException e)
		{
		}
	}
}
