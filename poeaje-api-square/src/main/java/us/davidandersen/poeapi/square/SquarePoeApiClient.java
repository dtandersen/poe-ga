package us.davidandersen.poeapi.square;

import java.io.IOException;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import us.davidandersen.poeapi.PoeApiClient;
import us.davidandersen.poeapi.model.FetchItemResult;
import us.davidandersen.poeapi.model.SearchExchangeResult;
import us.davidandersen.poeapi.model.ServerError;
import us.davidandersen.poeapi.square.SearchExchangeRequest.ExchangeJson;
import us.davidandersen.poeapi.square.SearchExchangeRequest.ExchangeJson.Status;

public class SquarePoeApiClient implements PoeApiClient
{
	private static final String DEFAULT_ENDPOINT = "https://www.pathofexile.com/api/";

	private final String endpoint;

	public SquarePoeApiClient(final String endpoint)
	{
		this.endpoint = endpoint;
	}

	public SquarePoeApiClient()
	{
		endpoint = DEFAULT_ENDPOINT;
	}

	@Override
	public SearchExchangeResult searchExchange(final String haveIn, final String wantIn) throws IOException, ServerError
	{
		final Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(endpoint)
				.addConverterFactory(MoshiConverterFactory.create())
				.build();

		final PoeService service = retrofit.create(PoeService.class);

		final SearchExchangeRequest want = new SearchExchangeRequest();
		want.exchange = new ExchangeJson();
		want.exchange.status = new Status();
		want.exchange.status.option = "online";
		want.exchange.have = List.of(haveIn);
		want.exchange.want = List.of(wantIn);
		final Call<SearchExchangeResult> stuff = service.searchExchange(want);
		SearchExchangeResult r1 = null;
		final Response<SearchExchangeResult> xx = stuff.execute();
		if (!xx.isSuccessful())
		{
			throw new ServerError(xx.code());
		}
		r1 = xx.body();

		return r1;
	}

	@Override
	public FetchItemResult fetchListings(final List<String> results, final String query) throws IOException
	{
		final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
		final Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(endpoint)
				.client(client)
				.addConverterFactory(MoshiConverterFactory.create())
				.build();

		final PoeService service = retrofit.create(PoeService.class);

		final Call<FetchItemResult> call = service.fetchItems(String.join(",", results), query);
		final Response<FetchItemResult> resx = call.execute();

		System.out.println(resx.raw().toString());

		final FetchItemResult body = resx.body();

		return body;
	}
}
