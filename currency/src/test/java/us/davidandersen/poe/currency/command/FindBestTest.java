package us.davidandersen.poe.currency.command;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
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
	void singleTrade() throws Exception
	{
		givenListings(
				"want  | have  | price",
				"chaos | chrom | 8");

		// convert chaos to chromatic orbs
		run(Currency.CHAOS, Currency.CHROMATIC, 20, 1);

		assertThat(bestTrade(), isTrades(
				"mode | in | sell  | price | out | receive",
				"sell | 20 | chaos | 8     | 160 | chrom"));
	}

	/**
	 * Same to same is always the input quantity.
	 */
	@Test
	void singleTradeSameCurrency() throws Exception
	{
		run(Currency.CHAOS, Currency.CHAOS, 20, 1);

		assertThat(bestTrade(), empty());
	}

	@Test
	void doubleTrade() throws Exception
	{
		givenListings(
				"want  | have  | price",
				"alt   | fuse  | .3",
				"fuse  | chaos | .2");

		run(Currency.ALTERATION, Currency.CHAOS, 120, 2);

		assertThat(bestTrade(), isTrades(
				"mode | in  | sell  | price | out | receive",
				"sell | 120 | alt   | .3    | 36  | fuse",
				"sell | 36  | fuse  | .2    | 7.2 | chaos"));
	}

	@Test
	void takeBetterPath() throws Exception
	{
		givenListings(
				"want  | have   | price",
				"alt   | fuse   | .3",
				"alt   | chrom  | .5",
				"fuse  | chaos  | .2",
				"chrom | chaos  | .5");

		run(Currency.ALTERATION, Currency.CHAOS, 120, 2);

		assertThat(bestTrade(), isTrades(
				"mode | in  | sell  | price | out | receive",
				"sell | 120 | alt   | .5    | 60  | chrom",
				"sell | 60  | chrom | .5    | 30  | chaos"));
	}

	@Test
	void lastNodeMustBeEnd() throws Exception
	{
		givenListings(
				"want  | have   | price",
				"alt   | fuse   | .3",
				"alt   | chrom  | .5",
				"fuse  | chaos  | .2",
				"chrom | chaos  | .5",
				"chrom | fuse   | 2");

		run(Currency.ALTERATION, Currency.CHAOS, 120, 2);

		assertThat(bestTrade(), isTrades(
				"mode | in  | sell  | price | out | receive",
				"sell | 120 | alt   | .5     | 60  | chrom",
				"sell | 60  | chrom | .5     | 30  | chaos"));
	}

	@Test
	void shorterPathBetter() throws Exception
	{
		givenListings(
				"want  | have   | price",
				"alt   | fuse   | .3",
				"alt   | chrom  | .5",
				"alt   | chaos  | .5",
				"fuse  | chaos  | .2",
				"chrom | chaos  | .5");

		run(Currency.ALTERATION, Currency.CHAOS, 120, 2);

		assertThat(bestTrade(), isTrades(
				"mode | in  | sell  | price | out | receive",
				"sell | 120 | alt   | .5    | 60  | chaos"));
	}

	private void givenListings(final String... markdown) throws IOException
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

	private void run(final Currency have, final Currency want, final float quantity, final int maxTrades)
	{
		final FindBest command = new FindBest(priceRepository);
		final FindBestRequest request = new FindBestRequest() {
			@Override
			public String getWant()
			{
				return want.symbol();
			}

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
			public int getMaxTrades()
			{
				return maxTrades;
			}
		};
		result = new FindBestResultBean();

		command.setRequest(request);
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
						.withSellPrice(row.floatValue("price"))
						.build())
				.collect(Collectors.toList());
	}

	private Matcher<? super Trade> toTradeMatcher(final Trade trade)
	{
		return ComposeBuilder.of(Trade.class)
				.withDescription("a trade with")
				.withFeature("mode", Trade::getMode, trade.getMode())
				.withFeature("in", Trade::getIn, closeTo(trade.getIn(), .001))
				.withFeature("sell", Trade::getSell, trade.getSell())
				.withFeature("out", Trade::getOut, closeTo(trade.getOut(), .001))
				.withFeature("receive", Trade::getReceive, trade.getReceive())
				.withFeature("price", Trade::getSellPrice, closeTo(trade.getSellPrice(), .001))
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
		public void success(final List<Trade> trades)
		{
			this.trades = trades;
		}
	}
}
