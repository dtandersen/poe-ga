package us.davidandersen.poe.currency.command;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import java.util.List;
import java.util.stream.Collectors;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import poe.command.MarkdownStream;
import poe.command.MarkdownStream.Row;
import us.davidandersen.poe.currency.MockPoeGateway;
import us.davidandersen.poe.currency.command.GetListings.GetListingsRequest;
import us.davidandersen.poe.currency.command.GetListings.GetListingsResult;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Listing;
import us.davidandersen.poe.currency.entity.Listing.ListingBuilder;
import us.davidandersen.poe.test.ComposeBuilder;

public class GetListingsTest
{
	private final MockPoeGateway poeApi = new MockPoeGateway();

	private GetListingsResultImplementation result;

	@Test
	void test()
	{
		givenListings(
				"want  | have  | pay",
				"chaos | fuse  | 1.9",
				"chaos | fuse  | 1.95",
				"fuse  | chaos | .4",
				"fuse  | chaos | .5");

		run(Currency.CHAOS, Currency.FUSING);

		assertThat(selling(), hasListings(
				"want  | have  | pay",
				"chaos | fuse  | 1.9",
				"chaos | fuse  | 1.95"));

		assertThat(buying(), hasListings(
				"want  | have  | pay",
				"fuse  | chaos | .4",
				"fuse  | chaos | .5"));
	}

	private void run(final Currency base, final Currency other)
	{
		final GetListings command = new GetListings(poeApi);
		command.setRequest(new GetListingsRequest() {
			@Override
			public String getBase()
			{
				return base.symbol();
			}

			@Override
			public String getOther()
			{
				return other.symbol();
			}
		});
		result = new GetListingsResultImplementation();
		command.setResult(result);
		command.execute();
	}

	private void givenListings(final String... markdown)
	{
		MarkdownStream.stream(markdown)
				.map(row -> toListing(row))
				.forEach(listings -> poeApi.addListing(listings));
	}

	private ListingBuilder toListing(final Row row)
	{
		return Listing.Builder()
				.want(row.trimmed("want"))
				.have(row.trimmed("have"))
				.price(row.floatValue("pay"));
	}

	private List<Listing> buying()
	{
		return result.getBuying();
	}

	private List<Listing> selling()
	{
		return result.getSelling();
	}

	private Matcher<Iterable<? extends Listing>> hasListings(final String... markdown)
	{
		final List<Matcher<? super Listing>> listingMatchers = MarkdownStream.stream(markdown)
				.map(row -> toListing(row))
				.map(listing -> toMatcher(listing))
				.collect(Collectors.toList());

		return contains(listingMatchers);
	}

	private Matcher<Listing> toMatcher(final ListingBuilder listingBuilder)
	{
		final Listing listing = listingBuilder.build();
		return ComposeBuilder.of(Listing.class)
				.withDescription("a listing with")
				.withFeature("want", Listing::getWant, listing.getWant())
				.withFeature("have", Listing::getHave, listing.getHave())
				.withFeature("price", Listing::getPrice, listing.getPrice())
				.build();
	}

	private final class GetListingsResultImplementation implements GetListingsResult
	{
		private List<Listing> selling;

		private List<Listing> buying;

		public List<Listing> getSelling()
		{
			return selling;
		}

		public List<Listing> getBuying()
		{
			return buying;
		}

		@Override
		public void setBuying(final List<Listing> buying)
		{
			this.buying = buying;
		}

		@Override
		public void setSelling(final List<Listing> selling)
		{
			this.selling = selling;
		}
	}
}
