package us.davidandersen.poe.currency.app;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import us.davidandersen.poe.currency.Listing;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.gateway.PoeApiGateway;
import us.davidandersen.poeapi.PoeApiClient;
import us.davidandersen.poeapi.model.FetchItemResult;
import us.davidandersen.poeapi.model.SearchExchangeResult;
import us.davidandersen.poeapi.square.SquarePoeApiClient;

public class MyPoeApiGateway implements PoeApiGateway
{
	@Override
	public List<Listing> find(final Currency have, final Currency want) throws IOException
	{
		final PoeApiClient api = new SquarePoeApiClient();
		final SearchExchangeResult x = api.searchExchange(have.getShortName(), want.getShortName());
		List<String> ids = x.result;
		if (x.result.size() > 10)
		{
			ids = x.result.subList(0, 10);
		}
		final FetchItemResult listings = api.fetchListings(ids, x.id);

		return listings.result.stream()
				.map(l -> Listing.Builder()
						.have(Currency.shortNameToCurrency(l.listing.price.currency))
						.want(Currency.longNameToCurrency(l.item.typeLine))
						.price(l.listing.price.amount)
						.build())
				.collect(Collectors.toList());
	}
}
