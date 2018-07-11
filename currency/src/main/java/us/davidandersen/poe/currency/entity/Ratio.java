package us.davidandersen.poe.currency.entity;

public class Ratio
{
	private final Currency have;

	private final Currency want;

	float price;

	public Ratio(final RatioBuilder ratioBuilder)
	{
		have = ratioBuilder.have;
		want = ratioBuilder.want;
		price = (float)ratioBuilder.price;
	}

	public Currency getHave()
	{
		return have;
	}

	public Currency getWant()
	{
		return want;
	}

	public boolean hasHave(final Currency have)
	{
		return this.have == have;
	}

	public boolean hasWant(final Currency want)
	{
		return this.want == want;
	}

	public float getPrice()
	{
		return price;
	}

	public static RatioBuilder Builder()
	{
		return new Ratio.RatioBuilder();
	}

	@Override
	public String toString()
	{
		return "Ratio[" +
				"want=" + want +
				", have=" + have +
				", price=+" + price +
				"]";
	}

	public static class RatioBuilder
	{
		private Currency have;

		private Currency want;

		private double price;

		public RatioBuilder want(final Currency want)
		{
			this.want = want;
			return this;
		}

		public RatioBuilder want(final String want)
		{
			return want(Currency.ofSymbol(want));
		}

		public RatioBuilder have(final Currency have)
		{
			this.have = have;
			return this;
		}

		public RatioBuilder have(final String have)
		{
			return have(Currency.ofSymbol(have));
		}

		public RatioBuilder price(final double price)
		{
			this.price = price;
			return this;
		}

		public Ratio build()
		{
			return new Ratio(this);
		}
	}
}
