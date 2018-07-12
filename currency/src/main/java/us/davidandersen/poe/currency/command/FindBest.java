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
				result.success(List.of(Trade.Builder()
						.withMode("sell")
						.withIn(request.getQuantity())
						.withSell(request.getHave())
						.withOut(request.getQuantity())
						.withReceive(request.getWant())
						.withSellPrice(1)
						.build()));
			}
			else if (maxTrades == 1)
			{
				final Trade trade = doTrade(request.getWant(), request.getHave(), request.getQuantity());

				result.success(List.of(trade));
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
				tryTrade(start, maxTrades, tradeStack, 0, watcher, request.getQuantity(), end);

				result.success(watcher.best());
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
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
		// void setTrade(Trade trade);

		void success(List<Trade> best);
	}

	/**
	 * Perform a depth first search of all possible trades.
	 *
	 * @param have
	 * @param remainingTrades
	 * @param tradeStack
	 * @param depth
	 * @param watcher
	 * @param quantityIn
	 * @throws IOException
	 */
	private void tryTrade(final Currency have, final int remainingTrades, final ArrayList<Trade> tradeStack, final int depth, final TradeWatcher watcher, final float quantityIn, final Currency end) throws IOException
	{
		if (remainingTrades == 0)
		{
			// reached the end, is this the best?
			watcher.tradeComplete(tradeStack);
			return;
		}

		Currency[] values;
		if (remainingTrades == 1)
		{
			values = new Currency[] { end };
		}
		else
		{
			values = Currency.values();

		}
		for (final Currency want : values)
		{
			if (want == have)
			{
				// no use trading same for same
				continue;
			}

			final Trade doTrade = doTrade(want.symbol(), have.symbol(), quantityIn);

			if (doTrade == null)
			{
				// no trade available
				continue;
			}

			tradeStack.set(depth, doTrade);

			final float quantityOut = doTrade.getOut();

			// search deeper
			tryTrade(want, remainingTrades - 1, tradeStack, depth + 1, watcher, quantityOut, end);
		}
	}

	private boolean isInputEqualToOutput(final String want, final String have)
	{
		return Objects.equals(want, have);
	}

	private Trade doTrade(final String want2, final String have2, final float quantity2) throws IOException
	{
		final Currency want = Currency.ofSymbol(want2);
		final Currency have = Currency.ofSymbol(have2);
		final float quantity = quantity2;

		final Ratio price = priceRepository.get(want, have);
		if (price == null) { return null; }
		final float qout = quantity / price.getPrice();

		final Trade trade = Trade.Builder()
				.withMode("sell")
				.withIn(quantity)
				.withSell(have.symbol())
				.withOut(qout)
				.withReceive(want.symbol())
				.withSellPrice(price.getPrice())
				.build();
		return trade;
	}

	private static class TradeWatcher
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
}
