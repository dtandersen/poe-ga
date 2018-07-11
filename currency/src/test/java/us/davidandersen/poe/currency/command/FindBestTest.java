package us.davidandersen.poe.currency.command;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import poe.command.MarkdownStream;
import poe.command.MarkdownStream.Row;
import us.davidandersen.poe.currency.MockRatioRepository;
import us.davidandersen.poe.currency.command.FindBest.FindBestRequest;
import us.davidandersen.poe.currency.command.FindBest.FindBestResult;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Ratio;
import us.davidandersen.poe.currency.entity.Ratio.RatioBuilder;
import us.davidandersen.poe.currency.repository.PriceRepository;
import us.davidandersen.poe.test.ComposeBuilder;

public class FindBestTest
{
	private FindBestResultBean result;

	private final PriceRepository priceRepository = new MockRatioRepository();

	@Test
	void test() throws Exception
	{
		givenPrices(
				"want  | have  | price",
				"chaos | chrom | 2");

		run(Currency.CHAOS, Currency.CHROMATIC, 20, 1);

		assertThat(bestTrade(), isTrades(
				"mode | in | sell  | out | receive",
				"sell | 20 | chrom | 10  | chaos"));
	}

	@Test
	void test2() throws Exception
	{
		givenPrices(
				"want  | have  | price",
				"fuse  | alt   | 3",
				"chaos | fuse  | 5");

		run(Currency.CHAOS, Currency.ALTERATION, 120, 2);

		assertThat(bestTrade(), isTrades(
				"mode | in  | sell  | out | receive",
				"sell | 120 | alt   | 40  | fuse",
				"sell | 40  | fuse  | 8   | chaos"));
	}

	private void givenPrices(final String... markdown) throws IOException
	{
		for (final Row row : MarkdownStream.asList(markdown))
		{
			final RatioBuilder price = Ratio.Builder()
					.want(row.trimmed("want"))
					.have(row.trimmed("have"))
					.price(row.floatValue("price"));

			priceRepository.insert(price);
		}
	}

	private void run(final Currency want, final Currency have, final float quantity, final int maxTrades)
	{
		final FindBest command = new FindBest(priceRepository);
		command.setRequest(new FindBestRequest() {
			@Override
			public String getHave()
			{
				return have.symbol();
			}

			@Override
			public float getQuantity()
			{
				return quantity;
			}

			@Override
			public String getWant()
			{
				return want.symbol();
			}

			@Override
			public int getMaxTrades()
			{
				return maxTrades;
			}
		});
		result = new FindBestResultBean();
		command.setResult(result);
		command.execute();
	}

	private List<Trade> bestTrade()
	{
		return result.getTrades();
	}

	private Matcher<Iterable<? extends Trade>> isTrades(final String... markdown)
	{
		final List<Trade> trades = toTrades(markdown);

		final List<Matcher<? super Trade>> matchers = trades.stream()
				.map(trade -> toTradeMatcher(trade))
				.collect(Collectors.toList());

		return contains(matchers);
	}

	private List<Trade> toTrades(final String[] markdown)
	{
		return MarkdownStream.stream(markdown)
				.map(row -> Trade.Builder()
						.withMode(row.trimmed("mode"))
						.withIn(row.floatValue("in"))
						.withSell(row.trimmed("sell"))
						.withOut(row.floatValue("out"))
						.withReceive(row.trimmed("receive"))
						.build())
				.collect(Collectors.toList());
	}

	private Matcher<? super Trade> toTradeMatcher(final Trade trade)
	{
		return ComposeBuilder.of(Trade.class)
				.withDescription("a trade with")
				.withFeature("mode", Trade::getMode, trade.getMode())
				.withFeature("in", Trade::getIn, trade.getIn())
				.withFeature("sell", Trade::getSell, trade.getSell())
				.withFeature("out", Trade::getOut, trade.getOut())
				.withFeature("receive", Trade::getReceive, trade.getReceive())
				.build();
	}

	private final class FindBestResultBean implements FindBestResult
	{
		private List<Trade> trades = new ArrayList<>();

		public List<Trade> getTrades()
		{
			return trades;
		}

		@Override
		public void setTrade(final Trade trade)
		{
			trades.add(trade);
		}

		@Override
		public void setTrades(final List<Trade> trades)
		{
			this.trades = trades;
		}
	}
}