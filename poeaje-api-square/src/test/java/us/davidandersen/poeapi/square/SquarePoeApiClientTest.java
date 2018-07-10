package us.davidandersen.poeapi.square;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okhttp3.mockwebserver.SocketPolicy;
import us.davidandersen.poeapi.PoeApiClient;
import us.davidandersen.poeapi.model.FetchItemResult;
import us.davidandersen.poeapi.model.FetchItemResult.Result;
import us.davidandersen.poeapi.model.SearchExchangeResult;
import us.davidandersen.poeapi.model.ServerError;
import us.davidandersen.poeapi.util.ResourceUtil;

public class SquarePoeApiClientTest
{
	private MockWebServer server;

	@BeforeEach
	private void start() throws IOException
	{
		server = new MockWebServer();
		server.start();
	}

	@AfterEach
	private void stop() throws IOException
	{
		server.shutdown();
	}

	@Test
	void test() throws Exception
	{
		server.enqueue(new MockResponse().setBody(ResourceUtil.getFile("trade-exchange-response.json")));

		final PoeApiClient poe = new SquarePoeApiClient(server.url("").toString());
		final SearchExchangeResult stuff = poe.searchExchange("fuse", "chaos");

		final RecordedRequest request1 = server.takeRequest(5, TimeUnit.SECONDS);
		assertEquals("/trade/exchange/Incursion", request1.getPath());
		final String body = requestBody(request1);

		final String xyz = "{\"exchange\":{\"status\":{\"option\":\"online\"},\"have\":[\"fuse\"],\"want\":[\"chaos\"]}}";
		JSONAssert.assertEquals(body, xyz, JSONCompareMode.NON_EXTENSIBLE);

		assertThat(stuff.result, contains("a0e3b1fb99e7e64815db0611d27c9fddf0ea348e17322072feb78bd2e42a9f0d", "69af0e807d4309b3c1ddfa1101fe3c91562456d0c5536472c7f275edf903047d"));
		assertThat(stuff.id, equalTo("jW58VLUX"));
	}

	@Test
	void showThrowIOExceptionWhenOnTimeout() throws Exception
	{
		server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
		final PoeApiClient poe = new SquarePoeApiClient(server.url("").toString());

		try
		{
			poe.searchExchange("chaos", "scour");
		}
		catch (final IOException e)
		{
			return;
		}

		fail("should throw IOException");
	}

	@Test
	void showThrowIOExceptionWhenOnServerError() throws Exception
	{
		server.enqueue(new MockResponse().setResponseCode(500).setBody(ResourceUtil.getFile("trade-exchange-response.json")));
		final PoeApiClient poe = new SquarePoeApiClient(server.url("").toString());

		try
		{
			poe.searchExchange("scour", "fuse");
		}
		catch (final ServerError e)
		{
			return;
		}

		fail("should throw IOException");
	}

	@Test
	void queryExchange() throws Exception
	{
		server.enqueue(new MockResponse().setBody(ResourceUtil.getFile("fetch-multiple-result.json")));
		final PoeApiClient poe = new SquarePoeApiClient(server.url("").toString());

		final List<String> ids = List.of("df4a68db48e5a1d185016f3cce2a11360c4fdee700260fb8f545c27e29922406", "a0e3b1fb99e7e64815db0611d27c9fddf0ea348e17322072feb78bd2e42a9f0d");
		final FetchItemResult result = poe.fetchListings(ids, "jW58VLUX");

		final RecordedRequest request1 = server.takeRequest(2, TimeUnit.SECONDS);
		assertEquals("/trade/fetch/df4a68db48e5a1d185016f3cce2a11360c4fdee700260fb8f545c27e29922406,a0e3b1fb99e7e64815db0611d27c9fddf0ea348e17322072feb78bd2e42a9f0d?query=jW58VLUX", request1.getPath());

		final Result result1 = result.result.get(0);
		assertThat(result1.item.typeLine, equalTo("Chaos Orb"));
		assertThat(result1.listing.price.currency, equalTo("fuse"));
	}

	private static String requestBody(final RecordedRequest request)
	{
		return request.getBody().readString(Charset.forName("UTF-8"));
	}
}
