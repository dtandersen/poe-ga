package us.davidandersen.poe.currency.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Listing;
import us.davidandersen.poe.gateway.PoeApiGateway;
import us.davidandersen.poeapi.PoeApiClient;
import us.davidandersen.poeapi.model.FetchItemResult;
import us.davidandersen.poeapi.model.FetchItemResult.Result;
import us.davidandersen.poeapi.model.SearchExchangeResult;

public class DefaultPoeApiGateway implements PoeApiGateway
{
	private final PoeApiClient client;

	public DefaultPoeApiGateway(final PoeApiClient client)
	{
		this.client = client;
	}

	@Override
	public List<Listing> find(final Currency have, final Currency want, final int limit) throws IOException
	{
		final SearchExchangeResult x = client.searchExchange(have.symbol(), want.symbol());
		final List<String> ids = x.result;
		if (ids.isEmpty())
		{
			return new ArrayList<>();
		}
		final List<List<String>> lists = split(ids, limit);
		final List<Result> rr = new ArrayList<>();
		for (final List<String> cl : lists)
		{
			final FetchItemResult temp = client.fetchListings(cl, x.id);
			rr.addAll(temp.result);
		}

		final List<Listing> all = rr.stream()
				.map(l -> toListing(l))
				.collect(Collectors.toList());

		return all;
	}

	@Override
	public List<Listing> find(final Currency have, final Currency want) throws IOException
	{
		return find(have, want, 0);
	}

	private List<List<String>> split(final List<String> ids, final int limit)
	{
		final List<List<String>> lists = new ArrayList<>();

		List<String> clist = null;
		int count = 0;
		for (final String id : ids)
		{
			if (clist == null || clist.size() >= 10)
			{
				clist = new ArrayList<>();
				lists.add(clist);
			}
			clist.add(id);
			count++;
			if (limit > 0 && count >= limit)
			{
				break;
			}
		}
		return lists;
	}

	private static Listing toListing(final Result result)
	{
		final Listing listing = Listing.Builder()
				.withId(result.id)
				.withAccountName(result.listing.account.name)
				.buying(Currency.ofSymbol(result.listing.price.currency))
				.selling(Currency.longNameToCurrency(result.item.typeLine))
				.withBuyPrice(result.listing.price.amount)
				.withNote(result.item.note)
				.build();

		return listing;
	}
}
