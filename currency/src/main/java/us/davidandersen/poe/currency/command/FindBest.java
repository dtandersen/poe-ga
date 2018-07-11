package us.davidandersen.poe.currency.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
			if (maxTrades == 1 && isInputEqualToOutput(request.getWant(), request.getHave()))
			{
				result.setTrade(Trade.Builder()
						.withMode("sell")
						.withIn(request.getQuantity())
						.withSell(request.getHave())
						.withOut(request.getQuantity())
						.withReceive(request.getWant())
						.build());
			}
			else if (maxTrades == 1)
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
				final TradeWatcher watcher = new TradeWatcher();
				tryTrade(start, maxTrades, tradeStack, 0, watcher, request.getQuantity());

				result.setTrades(watcher.best());
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	private void tryTrade(final Currency have, final int maxTrades, final ArrayList<Trade> tradeStack, final int depth, final TradeWatcher watcher, final float quantityIn) throws IOException
	{
		if (maxTrades == 0)
		{
			// reached the end, is this the best?
			watcher.tradeComplete(tradeStack);
			return;
		}

		for (final Currency want : Currency.values())
		{
			final Trade doTrade = doTrade(want.symbol(), have.symbol(), quantityIn);
			if (doTrade == null)
			{
				// no trade available
				continue;
			}

			tradeStack.set(depth, doTrade);

			final float quantityOut = doTrade.getOut();
			tryTrade(want, maxTrades - 1, tradeStack, depth + 1, watcher, quantityOut);
		}
	}

	private boolean isInputEqualToOutput(final String want, final String have)
	{
		return Objects.equals(want, have);
	}

	static class TradeWatcher
	{
		float q;

		private List<Trade> best;

		public List<Trade> best()
		{
			return best;
		}

		public void tradeComplete(final List<Trade> tradeStack)
		{
			if (best == null)
			{
				// best = tradeStack;
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
