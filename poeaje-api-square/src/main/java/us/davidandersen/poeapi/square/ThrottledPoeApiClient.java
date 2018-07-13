package us.davidandersen.poeapi.square;

import java.io.IOException;
import java.util.List;
import us.davidandersen.poeapi.PoeApiClient;
import us.davidandersen.poeapi.model.FetchItemResult;
import us.davidandersen.poeapi.model.SearchExchangeResult;

public class ThrottledPoeApiClient implements PoeApiClient
{
	private static final int WAIT_TIME_MILLIS = 300;

	private final PoeApiClient client;

	private long lastRequestTime;

	public ThrottledPoeApiClient(final PoeApiClient client)
	{
		this.client = client;
	}

	@Override
	public SearchExchangeResult searchExchange(final String have, final String want) throws IOException
	{
		block();
		return client.searchExchange(have, want);
	}

	@Override
	public FetchItemResult fetchListings(final List<String> ids, final String query) throws IOException
	{
		block();
		return client.fetchListings(ids, query);
	}

	private void block()
	{
		final long nextTime = lastRequestTime + WAIT_TIME_MILLIS;

		long ct = currentTime();
		while (ct <= nextTime)
		{
			sleep(nextTime - ct);
			ct = currentTime();
		}

		lastRequestTime = currentTime();
	}

	private long currentTime()
	{
		return System.currentTimeMillis();
	}

	private void sleep(final long time)
	{
		try
		{
			Thread.sleep(time);
		}
		catch (final InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
