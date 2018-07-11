package us.davidandersen.poe.currency.command;

public class Trade
{
	private final String mode;

	private final float in;

	private final String sell;

	private final float out;

	private final String receive;

	public Trade(final TradeBuilder tradeBuilder)
	{
		mode = tradeBuilder.mode;
		in = tradeBuilder.in;
		sell = tradeBuilder.sell;
		out = tradeBuilder.out;
		receive = tradeBuilder.receive;
	}

	public String getMode()
	{
		return mode;
	}

	public float getIn()
	{
		return in;
	}

	public String getSell()
	{
		return sell;
	}

	public float getOut()
	{
		return out;
	}

	public String getReceive()
	{
		return receive;
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
				"]";
	}

	public static class TradeBuilder
	{
		private String mode;

		private float in;

		private String sell;

		private float out;

		private String receive;

		public Trade build()
		{
			return new Trade(this);
		}

		public TradeBuilder withMode(final String mode)
		{
			this.mode = mode;
			return this;
		}

		public TradeBuilder withIn(final float in)
		{
			this.in = in;
			return this;
		}

		public TradeBuilder withSell(final String sell)
		{
			this.sell = sell;
			return this;
		}

		public TradeBuilder withOut(final float out)
		{
			this.out = out;
			return this;
		}

		public TradeBuilder withReceive(final String receive)
		{
			this.receive = receive;
			return this;
		}
	}
}
