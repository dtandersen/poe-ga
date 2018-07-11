package us.davidandersen.poe.currency;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import poe.command.MarkdownStream;
import poe.command.MarkdownStream.Row;
import us.davidandersen.poe.currency.Listing.ListingBuilder;
import us.davidandersen.poe.currency.UpdateRatios.UpdateRatioRequest;
import us.davidandersen.poe.currency.entity.Currency;

class UpdateRatiosTest
{
	private final MockRatioRepository ratioRepository = new MockRatioRepository();

	private final MockPoeGateway poeApi = new MockPoeGateway();

	private final MockSleeper sleeper = new MockSleeper();

	@Test
	void shouldUseTheLowestPrice()
	{
		givenListings(
				"want  | have  | pay",
				"chaos | fuse  | 1.9",
				"chaos | fuse  | 1.95");

		go(Currency.CHAOS, Currency.FUSING);

		assertThat(ratioRepository.get(Currency.CHAOS, Currency.FUSING).getPrice(), equalTo(1.9f));
		assertThat(sleeper.count(), equalTo(2));
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

		assertThat(sleeper.count(), equalTo(6));
	}

	private void go(final Currency... currencies)
	{
		final UpdateRatios command = new UpdateRatios(ratioRepository, poeApi, sleeper);
		command.setRequest(new UpdateRatioRequest() {
			@Override
			public List<String> getCurrencies()
			{
				return Arrays.stream(currencies)
						.map(c -> c.getShortName())
						.collect(Collectors.toList());
			}
		});

		command.execute();
	}
}
