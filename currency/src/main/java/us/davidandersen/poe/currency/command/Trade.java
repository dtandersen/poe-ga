package us.davidandersen.poe.currency.command;

public class Trade
{
	private final String mode;

	private final double in;

	private final String sell;

	private final double out;

	private final String receive;

	private final double sellPrice;

	public Trade(final TradeBuilder tradeBuilder)
	{
		mode = tradeBuilder.mode;
		in = tradeBuilder.in;
		sell = tradeBuilder.sell;
		out = tradeBuilder.out;
		receive = tradeBuilder.receive;
		sellPrice = tradeBuilder.sellPrice;
	}

	public String getMode()
	{
		return mode;
	}

	public double getIn()
	{
		return in;
	}

	public String getSell()
	{
		return sell;
	}

	public double getOut()
	{
		return out;
	}

	public String getReceive()
	{
		return receive;
	}

	public double getSellPrice()
	{
		return sellPrice;
	}

	public static TradeBuilder Builder()
	{
		return new Trade.TradeBuilder();
	}

	@Override
	public String toString()
	{
		return "Trade[" +
				"mode=" + mode +
				", in=" + in +
				", sell=" + sell +
				", out=" + out +
				", receieve=" + receive +
				", sellPrice=" + sellPrice +
				"]";
	}

	public static class TradeBuilder
	{
		private String mode;

		private double in;

		private String sell;

		private double out;

		private String receive;

		private float sellPrice;

		public Trade build()
		{
			return new Trade(this);
		}

		public TradeBuilder withMode(final String mode)
		{
			this.mode = mode;
			return this;
		}

		public TradeBuilder withIn(final double in)
		{
			this.in = in;
			return this;
		}

		public TradeBuilder withSell(final String sell)
		{
			this.sell = sell;
			return this;
		}

		public TradeBuilder withOut(final double out)
		{
			this.out = out;
			return this;
		}

		public TradeBuilder withReceive(final String receive)
		{
			this.receive = receive;
			return this;
		}

		public TradeBuilder withSellPrice(final float sellPrice)
		{
			this.sellPrice = sellPrice;
			return this;
		}
	}
}
