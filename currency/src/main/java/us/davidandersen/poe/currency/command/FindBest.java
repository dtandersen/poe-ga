package us.davidandersen.poe.currency.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import poe.command.BaseCommand;
import us.davidandersen.poe.currency.command.FindBest.FindBestRequest;
import us.davidandersen.poe.currency.command.FindBest.FindBestResult;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Ratio;
import us.davidandersen.poe.currency.repository.PriceRepository;

public class FindBest extends BaseCommand<FindBestRequest, FindBestResult>
{
	private final PriceRepository priceRepository;

	public FindBest(final PriceRepository priceRepository)
	{
		this.priceRepository = priceRepository;
	}

	@Override
	public void execute()
	{
		try
		{
			final int maxTrades = request.getMaxTrades();
			if (maxTrades == 1)
			{
				final Trade trade = doTrade(request.getWant(), request.getHave(), request.getQuantity());

				result.setTrade(trade);
			}
			else
			{
				final Currency start = Currency.ofSymbol(request.getHave());
				final Currency end = Currency.ofSymbol(request.getWant());
				final ArrayList<Trade> tradeStack = new ArrayList<>(maxTrades);
				for (int i = 0; i < maxTrades; i++)
				{
					tradeStack.add(null);
				}
				final TradeContext tc = new TradeContext();
				tryTrade(start, end, maxTrades, tradeStack, 0, tc, request.getQuantity());

				result.setTrades(tc.best());
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	private void tryTrade(final Currency have, final Currency end, final int maxTrades, final ArrayList<Trade> tradeStack, final int i, final TradeContext tc, final float qty) throws IOException
	{
		if (maxTrades == 0)
		{
			tc.check(tradeStack);
			return;
		}

		for (final Currency c : Currency.values())
		{
			final Currency want = c;
			final Trade doTrade = doTrade(want.symbol(), have.symbol(), qty);
			if (doTrade == null)
			{
				continue;
			}
			tradeStack.set(i, doTrade);

			tryTrade(want, end, maxTrades - 1, tradeStack, i + 1, tc, doTrade.getOut());
		}
	}

	static class TradeContext
	{
		float q;

		private List<Trade> best;

		public List<Trade> best()
		{
			return best;
		}

		public void check(final List<Trade> tradeStack)
		{
			if (best == null)
			{
				best = new ArrayList<>(tradeStack);
				return;
			}

			final float out = tradeStack.get(tradeStack.size() - 1).getOut();
			if (out > q)
			{
				best = new ArrayList<>(tradeStack);
				q = out;
			}
		}
	}

	private Trade doTrade(final String want2, final String have2, final float quantity2) throws IOException
	{
		final Currency want = Currency.ofSymbol(want2);
		final Currency have = Currency.ofSymbol(have2);
		final float quantity = quantity2;

		final Ratio price = priceRepository.get(want, have);
		if (price == null)
		{
			return null;
		}
		final float qout = quantity / price.getPrice();

		final Trade trade = Trade.Builder()
				.withMode("sell")
				.withIn(quantity)
				.withSell(have.symbol())
				.withOut(qout)
				.withReceive(want.symbol())
				.build();
		return trade;
	}

	public interface FindBestRequest
	{
		String getWant();

		String getHave();

		float getQuantity();

		int getMaxTrades();
	}

	public interface FindBestResult
	{
		void setTrade(Trade trade);

		void setTrades(List<Trade> best);
	}
}
