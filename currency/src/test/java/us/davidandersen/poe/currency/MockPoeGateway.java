package us.davidandersen.poe.currency;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import us.davidandersen.poe.currency.Listing.ListingBuilder;
import us.davidandersen.poe.currency.entity.Currency;
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
				.filter(l -> l.hasHave(have) && l.hasWant(want))
				.collect(Collectors.toList());
	}
}
