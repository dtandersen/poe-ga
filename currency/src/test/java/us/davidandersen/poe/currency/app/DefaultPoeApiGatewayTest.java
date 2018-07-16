package us.davidandersen.poe.currency.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import us.davidandersen.poe.currency.config.DefaultPoeApiGateway;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Listing;
import us.davidandersen.poeapi.MockPoeApiClient;
import us.davidandersen.poeapi.model.FetchItemResult;

class DefaultPoeApiGatewayTest
{
	private final MockPoeApiClient client = new MockPoeApiClient();;

	@Test
	void fieldsShouldBeFilled() throws IOException
	{
		client.add(FetchItemResult.Result.Builder()
				.withId("df4a68db48e5a1d185016f3cce2a11360c4fdee700260fb8f545c27e29922406")
				.withListing(FetchItemResult.Result.Listing.Builder()
						.withAccount("haddock97")
						.withPrice(1.9f, "fuse"))
				.withItem(FetchItemResult.Result.Item.Builder()
						.unverified()
						.typeLine("Chaos Orb")
						.identified()
						.note("~price 19/10 fuse"))
				.build());

		final DefaultPoeApiGateway gateway = new DefaultPoeApiGateway(client);

		final List<Listing> listings = gateway.find(Currency.FUSING, Currency.CHAOS);
		final Listing listing1 = listings.get(0);

		assertThat(listing1.getId(), equalTo("df4a68db48e5a1d185016f3cce2a11360c4fdee700260fb8f545c27e29922406"));
		assertThat(listing1.getAccountName(), equalTo("haddock97"));
		assertThat(listing1.getBuying(), equalTo(Currency.FUSING));
		assertThat((double)listing1.getBuyPrice(), closeTo(1.9f, .001));
		assertThat((double)listing1.getSellPrice(), closeTo(1 / 1.9f, .001));
		assertThat(listing1.getSelling(), equalTo(Currency.CHAOS));
		assertThat(listing1.getNote(), equalTo("~price 19/10 fuse"));
	}

	@Test
	void shouldReturnMoreThan10Results() throws IOException
	{
		final List<String> ids = new ArrayList<>();
		for (int i = 0; i < 11; i++)
		{
			final String id = "" + new Random().nextInt();
			ids.add(id);
			client.add(FetchItemResult.Result.Builder()
					.withId(id)
					.withListing(FetchItemResult.Result.Listing.Builder()
							.withAccount("haddock97")
							.withPrice(1.9f, "fuse"))
					.withItem(FetchItemResult.Result.Item.Builder()
							.unverified()
							.typeLine("Chaos Orb")
							.identified()
							.note("~price 19/10 fuse"))
					.build());
		}

		final DefaultPoeApiGateway gateway = new DefaultPoeApiGateway(client);

		final List<Listing> listings = gateway.find(Currency.FUSING, Currency.CHAOS);
		assertThat(listings.size(), equalTo(11));
		for (int i = 0; i < 11; i++)
		{
			final Listing listing = listings.get(i);
			assertThat(listing.getId(), equalTo(ids.get(i)));
		}
	}

	@Test
	void limitTo10Results() throws IOException
	{
		final List<String> ids = new ArrayList<>();
		for (int i = 0; i < 11; i++)
		{
			final String id = "" + new Random().nextInt();
			ids.add(id);
			client.add(FetchItemResult.Result.Builder()
					.withId(id)
					.withListing(FetchItemResult.Result.Listing.Builder()
							.withAccount("haddock97")
							.withPrice(1.9f, "fuse"))
					.withItem(FetchItemResult.Result.Item.Builder()
							.unverified()
							.typeLine("Chaos Orb")
							.identified()
							.note("~price 19/10 fuse"))
					.build());
		}

		final DefaultPoeApiGateway gateway = new DefaultPoeApiGateway(client);

		final List<Listing> listings = gateway.find(Currency.FUSING, Currency.CHAOS, 10);
		assertThat(listings.size(), equalTo(10));
		for (int i = 0; i < 10; i++)
		{
			final Listing listing = listings.get(i);
			assertThat(listing.getId(), equalTo(ids.get(i)));
		}
	}
}
