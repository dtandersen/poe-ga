package us.davidandersen.poeapi.square;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import us.davidandersen.poeapi.model.FetchItemResult;
import us.davidandersen.poeapi.model.SearchExchangeResult;

interface PoeService
{
	@POST("trade/exchange/Incursion")
	Call<SearchExchangeResult> searchExchange(@Body SearchExchangeRequest request);

	@GET("trade/fetch/{ids}")
	Call<FetchItemResult> fetchItems(@Path("ids") String ids, @Query("query") String query);
}