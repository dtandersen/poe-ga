package us.davidandersen.poeapi.model;

import java.util.List;
import us.davidandersen.poeapi.model.FetchItemResult.FetchItemResultBuilder.ItemBuilder;
import us.davidandersen.poeapi.model.FetchItemResult.FetchItemResultBuilder.ListingBuilder;
import us.davidandersen.poeapi.model.FetchItemResult.FetchItemResultBuilder.ListingBuilder.AccountBuilder;
import us.davidandersen.poeapi.model.FetchItemResult.FetchItemResultBuilder.ListingBuilder.PriceBuilder;
import us.davidandersen.poeapi.model.FetchItemResult.FetchItemResultBuilder.ResultBuilder;
import us.davidandersen.poeapi.model.FetchItemResult.Result.Item;
import us.davidandersen.poeapi.model.FetchItemResult.Result.Listing;
import us.davidandersen.poeapi.model.FetchItemResult.Result.Listing.Account;
import us.davidandersen.poeapi.model.FetchItemResult.Result.Listing.Price;

public class FetchItemResult
{
	public List<Result> result;

	public FetchItemResult(final FetchItemResultBuilder fetchItemResultBuilder)
	{
		result = fetchItemResultBuilder.results;
	}

	@Override
	public String toString()
	{
		return "FetchItemResult[" +
				"result=" + result +
				"]";
	}

	public static class Result
	{
		public String id;

		public Listing listing;

		public Item item;

		boolean gone;

		public Result(final ResultBuilder resultBuilder)
		{
			this.id = resultBuilder.id;
			this.listing = resultBuilder.listingBuilder.build();
			this.item = resultBuilder.itemBuilder.build();
		}

		public static ResultBuilder Builder()
		{
			return new ResultBuilder();
		}

		@Override
		public String toString()
		{
			return "Result[" +
					"id=" + id +
					", listing=" + listing +
					", item=" + item +
					"]";
		}

		public static class Listing
		{
			public Account account;

			public Price price;

			public Listing(final ListingBuilder listingBuilder)
			{
				this.account = listingBuilder.accountBuilder.build();
				this.price = listingBuilder.priceBuilder.build();
			}

			@Override
			public String toString()
			{
				return "Listing[" +
						"account=" + account +
						", price=" + price +
						"]";
			}

			public static ListingBuilder Builder()
			{
				return new ListingBuilder();
			}

			public static class Account
			{
				public String name;

				public Account(final AccountBuilder accountBuilder)
				{
					name = accountBuilder.name;
				}

				@Override
				public String toString()
				{
					return "Account[" +
							"name=" + name +
							"]";
				}

				public static AccountBuilder Builder()
				{
					return new AccountBuilder();
				}

			}

			public static class Price
			{
				public float amount;

				public String currency;

				public Price(final PriceBuilder priceBuilder)
				{
					amount = priceBuilder.amount;
					currency = priceBuilder.currency;
				}

				@Override
				public String toString()
				{
					return "Price[" +
							"amount=" + amount +
							", currency=" + currency +
							"]";
				}

				public static PriceBuilder Builder()
				{
					return new PriceBuilder();
				}
			}
		}

		public static class Item
		{
			static boolean verified;

			public String typeLine;

			boolean identified;

			public String note;

			Extended extended;

			public Item(final ItemBuilder itemBuilder)
			{
				typeLine = itemBuilder.typeLine;
				note = itemBuilder.note;
			}

			@Override
			public String toString()
			{
				return "Item[" +
						"verified=" + verified +
						", typeLine=" + typeLine +
						", idenified=" + identified +
						", note=" + note +
						", extended=" + extended +
						"]";
			}

			static class Extended
			{
				String text;

				@Override
				public String toString()
				{
					return "Extended[" +
							"text=" + text +
							"]";
				}
			}

			public static ItemBuilder Builder()
			{
				return new ItemBuilder();
			}
		}
	}

	public static FetchItemResultBuilder Builder()
	{
		return new FetchItemResultBuilder();
	}

	public static class FetchItemResultBuilder
	{
		private List<Result> results;

		public FetchItemResultBuilder withResults(final List<Result> results)
		{
			this.results = results;
			return this;
		}

		public FetchItemResult build()
		{
			return new FetchItemResult(this);
		}

		public static class ResultBuilder
		{
			private String id;

			public ListingBuilder listingBuilder;

			private ItemBuilder itemBuilder;

			public ResultBuilder withItem(final ItemBuilder itemBuilder)
			{
				this.itemBuilder = itemBuilder;
				return this;
			}

			public ResultBuilder withId(final String id)
			{
				this.id = id;
				return this;
			}

			public ResultBuilder withListing(final ListingBuilder listingBuilder)
			{
				this.listingBuilder = listingBuilder;
				return this;
			}

			public Result build()
			{
				return new Result(this);
			}
		}

		public static class ListingBuilder
		{
			public AccountBuilder accountBuilder;

			private PriceBuilder priceBuilder;

			public Listing build()
			{
				return new Listing(this);
			}

			public ListingBuilder withAccount(final String name)
			{
				accountBuilder = Account.Builder()
						.withName(name);
				return this;
			}

			public ListingBuilder withPrice(final float amount, final String currency)
			{
				this.priceBuilder = Price.Builder()
						.withAmount(amount)
						.withCurrency(currency);
				return this;
			}

			public static class AccountBuilder
			{
				private String name;

				public AccountBuilder withName(final String name)
				{
					this.name = name;
					return this;
				}

				public Account build()
				{
					return new Account(this);
				}
			}

			public static class PriceBuilder
			{
				private float amount;

				private String currency;

				public PriceBuilder withAmount(final float amount)
				{
					this.amount = amount;
					return this;
				}

				public Price build()
				{
					return new Price(this);
				}

				public PriceBuilder withCurrency(final String currency)
				{
					this.currency = currency;
					return this;
				}
			}
		}

		public static class ItemBuilder
		{
			private String typeLine;

			private boolean identified;

			private String note;

			private boolean verified;

			public ItemBuilder unverified()
			{
				this.verified = false;
				return this;
			}

			public Item build()
			{
				return new Item(this);
			}

			public ItemBuilder typeLine(final String typeLine)
			{
				this.typeLine = typeLine;
				return this;
			}

			public ItemBuilder identified()
			{
				this.identified = true;
				return this;
			}

			public ItemBuilder note(final String note)
			{
				this.note = note;
				return this;
			}
		}
	}
}
