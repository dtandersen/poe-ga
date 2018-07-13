package us.davidandersen.poe.currency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Listing;
import us.davidandersen.poe.currency.entity.Listing.ListingBuilder;
import us.davidandersen.poe.gateway.PoeApiGateway;

public class MockPoeGateway implements PoeApiGateway
{
	private final List<Listing> listings = new ArrayList<>();

	public void addListing(final ListingBuilder listingBuilder)
	{
		listings.add(listingBuilder.build());
	}

	@Override
	public List<Listing> find(final Currency have, final Currency want)
	{
		return listings.stream()
				.filter(l -> l.isSelling(have) && l.isBuying(want))
				.collect(Collectors.toList());
	}

	@Override
	public List<Listing> find(final Currency have, final Currency want, final int limit) throws IOException
	{
		if (limit == 0)
		{
			return find(have, want);
		}

		return listings.stream()
				.filter(l -> l.isSelling(have) && l.isBuying(want))
				.limit(limit)
				.collect(Collectors.toList());
	}
}
