package us.davidandersen.poeapi.model;

import java.util.List;

public class FetchItemResult
{
	public List<Result> result;

	public static class Result
	{
		String id;

		public Listing listing;

		public Item item;

		boolean gone;

		public static class Listing
		{
			public Price price;

			public static class Price
			{
				public float amount;

				public String currency;
			}
		}

		public static class Item
		{
			boolean verified;

			public String typeLine;

			boolean identified;

			Extended extended;

			static class Extended
			{
				String text;
			}
		}
	}
}
