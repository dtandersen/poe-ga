package us.davidandersen.poe.currency.command;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import poe.command.MarkdownStream;
import poe.command.MarkdownStream.Row;
import us.davidandersen.poe.currency.MockPoeGateway;
import us.davidandersen.poe.currency.MockRatioRepository;
import us.davidandersen.poe.currency.command.UpdateRatios.UpdateRatioRequest;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Listing;
import us.davidandersen.poe.currency.entity.Listing.ListingBuilder;

class UpdateRatiosTest
{
	private final MockRatioRepository ratioRepository = new MockRatioRepository();

	private final MockPoeGateway poeApi = new MockPoeGateway();

	@Test
	void shouldUseTheLowestPrice()
	{
		givenListings(
				"want  | have  | pay",
				"chaos | fuse  | 1.9",
				"chaos | fuse  | 1.95");

		go(Currency.CHAOS, Currency.FUSING);

		assertThat(ratioRepository.get(Currency.CHAOS, Currency.FUSING).getPrice(), equalTo(1.9f));
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
				.buying(row.trimmed("want"))
				.selling(row.trimmed("have"))
				.withBuyPrice(row.floatValue("pay"));
	}

	@Test
	void shouldCheckAllCombinations()
	{
		givenListings(
				"want  | have  | pay",
				"fuse  | chaos | .5",
				"scour | chaos | .4167",
				"chaos | fuse  | 1.9",
				"scour | fuse  | .8",
				"chaos | scour | 2.4",
				"fuse  | scour | 1.8");

		go(Currency.CHAOS, Currency.FUSING, Currency.SCOURING);

		assertThat(ratioRepository.get(Currency.FUSING, Currency.CHAOS).getPrice(), equalTo(0.5f));
		assertThat(ratioRepository.get(Currency.SCOURING, Currency.CHAOS).getPrice(), equalTo(.4167f));
		assertThat(ratioRepository.get(Currency.CHAOS, Currency.FUSING).getPrice(), equalTo(1.9f));
		assertThat(ratioRepository.get(Currency.SCOURING, Currency.FUSING).getPrice(), equalTo(.8f));
		assertThat(ratioRepository.get(Currency.CHAOS, Currency.SCOURING).getPrice(), equalTo(2.4f));
		assertThat(ratioRepository.get(Currency.FUSING, Currency.SCOURING).getPrice(), equalTo(1.8f));
	}

	private void go(final Currency... currencies)
	{
		final UpdateRatios command = new UpdateRatios(ratioRepository, poeApi);
		command.setRequest(new UpdateRatioRequest() {
			@Override
			public List<String> getCurrencies()
			{
				return Arrays.stream(currencies)
						.map(c -> c.symbol())
						.collect(Collectors.toList());
			}
		});

		command.execute();
	}
}
