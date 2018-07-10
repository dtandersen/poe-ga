package us.davidandersen.poeapi.example;

import java.io.IOException;
import java.util.List;
import us.davidandersen.poeapi.model.FetchItemResult;
import us.davidandersen.poeapi.model.FetchItemResult.Result;
import us.davidandersen.poeapi.model.FetchItemResult.Result.Item;
import us.davidandersen.poeapi.model.FetchItemResult.Result.Listing.Price;
import us.davidandersen.poeapi.model.SearchExchangeResult;
import us.davidandersen.poeapi.model.ServerError;
import us.davidandersen.poeapi.square.SquarePoeApiClient;

public class PoeApiExample
{
	public static void main(final String[] args) throws ServerError, IOException
	{
		final SquarePoeApiClient poe = new SquarePoeApiClient();

		final SearchExchangeResult items = poe.searchExchange("fuse", "chaos");
		final List<String> first10Results = items.result.subList(0, 10);
		final FetchItemResult listings = poe.fetchListings(first10Results, items.id);

		for (final Result item : listings.result)
		{
			printListing(item);
		}
	}

	private static void printListing(final Result result)
	{
		final Price price = result.listing.price;
		final Item item = result.item;

		System.out.println("want: " + item.typeLine + ", have: " + price.amount + " " + price.currency);
	}
}
