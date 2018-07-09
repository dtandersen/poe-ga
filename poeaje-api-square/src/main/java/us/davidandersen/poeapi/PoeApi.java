package us.davidandersen.poeapi;

import java.io.IOException;
import java.util.List;
import us.davidandersen.poeapi.model.FetchItemResult;
import us.davidandersen.poeapi.model.SearchExchangeResult;

public interface PoeApi
{
	SearchExchangeResult searchExchange() throws IOException;

	FetchItemResult fetchListings(List<String> ids, String query) throws IOException;
}
