package us.davidandersen.poe.currency;

import us.davidandersen.poe.currency.entity.Currency;

public class Listing
{
	private final Currency have;

	private final Currency want;

	private final double price;

	public Listing(final ListingBuilder listingBuilder)
	{
		have = listingBuilder.have;
		want = listingBuilder.want;
		price = listingBuilder.price;
	}

	public boolean hasHave(final Currency have)
	{
		return this.have == have;
	}

	public boolean hasWant(final Currency want)
	{
		return this.want == want;
	}

	public double getPrice()
	{
		return price;
	}

	public static ListingBuilder Builder()
	{
		return new ListingBuilder();
	}

	public static class ListingBuilder
	{
		private Currency have;

		private Currency want;

		private double price;

		public ListingBuilder have(final Currency have)
		{
			this.have = have;
			return this;
		}

		public ListingBuilder want(final Currency want)
		{
			this.want = want;
			return this;
		}

		public ListingBuilder price(final double price)
		{
			this.price = price;
			return this;
		}

		public Listing build()
		{
			return new Listing(this);
		}
	}
}
