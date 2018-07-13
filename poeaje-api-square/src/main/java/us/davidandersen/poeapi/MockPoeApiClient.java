package us.davidandersen.poeapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import us.davidandersen.poeapi.model.FetchItemResult;
import us.davidandersen.poeapi.model.FetchItemResult.Result;
import us.davidandersen.poeapi.model.SearchExchangeResult;

public class MockPoeApiClient implements PoeApiClient
{
	private final List<Result> results = new ArrayList<>();

	@Override
	public SearchExchangeResult searchExchange(final String have, final String want) throws IOException
	{
		final List<String> ids = results.stream()
				.map(result -> toResult(result))
				.collect(Collectors.toList());
		final SearchExchangeResult searchExchangeResult = new SearchExchangeResult();
		searchExchangeResult.result = ids;
		return searchExchangeResult;
	}

	private String toResult(final FetchItemResult.Result result)
	{
		return result.id;
	}

	@Override
	public FetchItemResult fetchListings(final List<String> ids, final String query) throws IOException
	{
		if (ids.size() > 10)
		{
			throw new RuntimeException("too many id");
		}

		final FetchItemResult results2 = FetchItemResult.Builder()
				.withResults(results.stream()
						.filter(r -> hasId(r, ids))
						.collect(Collectors.toList()))
				.build();
		return results2;
	}

	private boolean hasId(final Result r, final List<String> ids)
	{
		final boolean match = ids.contains(r.id);
		return match;
	}

	public void add(final FetchItemResult.Result result)
	{
		results.add(result);
	}
}
