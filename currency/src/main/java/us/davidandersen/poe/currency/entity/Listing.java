package us.davidandersen.poe.currency.entity;

public class Listing
{
	private final String id;

	private final String accountName;

	private final Currency selling;

	private final Currency buying;

	private final float price;

	private final String note;

	public Listing(final ListingBuilder listingBuilder)
	{
		id = listingBuilder.id;
		accountName = listingBuilder.accountName;
		selling = listingBuilder.selling;
		buying = listingBuilder.buying;
		price = listingBuilder.buyPrice;
		note = listingBuilder.note;
	}

	public String getId()
	{
		return id;
	}

	public String getAccountName()
	{
		return accountName;
	}

	public boolean isSelling(final Currency selling)
	{
		return this.selling == selling;
	}

	public boolean isBuying(final Currency buying)
	{
		return this.buying == buying;
	}

	public float getBuyPrice()
	{
		return price;
	}

	public float getSellPrice()
	{
		return 1 / price;
	}

	public Currency getBuying()
	{
		return buying;
	}

	public Currency getSelling()
	{
		return selling;
	}

	public String getNote()
	{
		return note;
	}

	public static ListingBuilder Builder()
	{
		return new ListingBuilder();
	}

	public static class ListingBuilder
	{
		public String note;

		public String accountName;

		public String id;

		private Currency selling;

		private Currency buying;

		private float buyPrice;

		public ListingBuilder withId(final String id)
		{
			this.id = id;
			return this;
		}

		public ListingBuilder withAccountName(final String accountName)
		{
			this.accountName = accountName;
			return this;
		}

		public ListingBuilder selling(final Currency selling)
		{
			this.selling = selling;
			return this;
		}

		public ListingBuilder buying(final Currency buying)
		{
			this.buying = buying;
			return this;
		}

		public ListingBuilder withBuyPrice(final float buyPrice)
		{
			this.buyPrice = buyPrice;
			return this;
		}

		public ListingBuilder buying(final String want)
		{
			this.buying = Currency.ofSymbol(want);
			return this;
		}

		public ListingBuilder selling(final String have)
		{
			this.selling = Currency.ofSymbol(have);
			return this;
		}

		public ListingBuilder withNote(final String note)
		{
			this.note = note;
			return this;
		}

		public Listing build()
		{
			return new Listing(this);
		}
	}
}
