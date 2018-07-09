package us.davidandersen.poeapi.square;

import java.util.List;

class SearchExchangeRequest
{
	SearchExchangeRequest.ExchangeJson exchange;

	static class ExchangeJson
	{
		ExchangeJson.Status status;

		List<String> have;

		List<String> want;

		static class Status
		{
			String option;
		}
	}
}
