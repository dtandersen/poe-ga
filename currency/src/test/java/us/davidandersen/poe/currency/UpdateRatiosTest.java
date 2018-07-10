package us.davidandersen.poe.currency;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;
import us.davidandersen.poe.currency.entity.Currency;

class UpdateRatiosTest
{
	private final MockRatioRepository ratioRepository = new MockRatioRepository();

	private final MockPoeGateway poeApi = new MockPoeGateway();

	private final MockSleeper sleeper = new MockSleeper();

	@Test
	void shouldUseTheLowestPrice()
	{
		poeApi.addListing(Listing.Builder()
				.have(Currency.CHAOS)
				.want(Currency.FUSING)
				.price(1.9));
		poeApi.addListing(Listing.Builder()
				.have(Currency.CHAOS)
				.want(Currency.FUSING)
				.price(1.95));
		final UpdateRatios command = new UpdateRatios(ratioRepository, poeApi, sleeper);

		command.execute();

		assertThat(ratioRepository.get(Currency.CHAOS, Currency.FUSING).getPrice(), equalTo(1.9f));
		assertThat(sleeper.count(), equalTo(6));
	}

	@Test
	void shouldCheckAllCombinations()
	{
		poeApi.addListing(Listing.Builder()
				.have(Currency.CHAOS)
				.want(Currency.FUSING)
				.price(.5));
		poeApi.addListing(Listing.Builder()
				.have(Currency.CHAOS)
				.want(Currency.SCOURING)
				.price(.4167));

		poeApi.addListing(Listing.Builder()
				.have(Currency.FUSING)
				.want(Currency.CHAOS)
				.price(1.9));
		poeApi.addListing(Listing.Builder()
				.have(Currency.FUSING)
				.want(Currency.SCOURING)
				.price(.8));

		poeApi.addListing(Listing.Builder()
				.have(Currency.SCOURING)
				.want(Currency.CHAOS)
				.price(2.4));
		poeApi.addListing(Listing.Builder()
				.have(Currency.SCOURING)
				.want(Currency.FUSING)
				.price(1.8));

		final UpdateRatios command = new UpdateRatios(ratioRepository, poeApi, sleeper);

		command.execute();

		assertThat(ratioRepository.get(Currency.CHAOS, Currency.FUSING).getPrice(), equalTo(0.5f));
		assertThat(ratioRepository.get(Currency.CHAOS, Currency.SCOURING).getPrice(), equalTo(.4167f));
		assertThat(ratioRepository.get(Currency.FUSING, Currency.CHAOS).getPrice(), equalTo(1.9f));
		assertThat(ratioRepository.get(Currency.FUSING, Currency.SCOURING).getPrice(), equalTo(.8f));
		assertThat(ratioRepository.get(Currency.SCOURING, Currency.CHAOS).getPrice(), equalTo(2.4f));
		assertThat(ratioRepository.get(Currency.SCOURING, Currency.FUSING).getPrice(), equalTo(1.8f));
		assertThat(sleeper.count(), equalTo(6));
	}
}
