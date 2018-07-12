package us.davidandersen.poe.currency.findbest;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import us.davidandersen.poe.currency.command.FindBest;
import us.davidandersen.poe.currency.command.FindBest.FindBestRequest;
import us.davidandersen.poe.currency.command.FindBest.FindBestResult;
import us.davidandersen.poe.currency.command.Trade;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Ratio;
import us.davidandersen.poe.currency.repository.PriceRepository;

@SpringBootApplication
@ComponentScan(basePackages = "us.davidandersen.poe.currency.app")
public class FindBestApp implements CommandLineRunner
{
	@Autowired
	private PriceRepository ratioRepository;

	public static void main(final String[] args)
	{
		final SpringApplication app = new SpringApplication(FindBestApp.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(final String... args) throws Exception
	{
		final Currency want = Currency.CHAOS;
		final Currency have = Currency.CHAOS;
		final FindBest command = new FindBest(ratioRepository);
		final int quantity = 10;
		final int maxTrades = 2;

		command.setRequest(new FindBestRequest() {
			@Override
			public String getWant()
			{
				return want.symbol();
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

			@Override
			public String getHave()
			{
				return have.symbol();
			}
		});
		command.setResult(new FindBestResult() {
			@Override
			public void success(final List<Trade> best)
			{
				System.out.println("have => want");
				for (final Trade trade : best)
				{
					System.out.println(trade.getMode() + " " + trade.getIn() + " " + trade.getSell() + " @ " + trade.getSellPrice() + " ea => " + trade.getOut() + " " + trade.getReceive());
				}
			}
		});
		command.execute();
	}

	private void old() throws IOException
	{
		final List<Currency> currencies = List.of(
				Currency.WISDOM,
				Currency.PORTAL,
				Currency.WHETSTONE,
				Currency.ARMOURERS,
				Currency.BAUBLE,
				Currency.GEMCUTTER,
				Currency.CHISEL,
				Currency.TRANSMUTATION,
				Currency.ALTERATION,
				Currency.CHANCE,
				Currency.REGAL,
				Currency.ALCHEMY,
				Currency.CHAOS,
				Currency.BLESSED,
				Currency.AUGMENTATION,
				Currency.JEWELLER,
				Currency.FUSING,
				Currency.CHROMATIC,
				Currency.SILVER,
				Currency.SCOURING,
				Currency.REGRET,
				Currency.VAAL);

		final Currency start = Currency.CHAOS;
		final Currency end = Currency.CHAOS;
		final float q1 = 100;
		float best = 0;
		Currency bestcur = null;
		for (final Currency middle : currencies)
		{
			if (start == middle || end == middle)
			{
				continue;
			}
			Currency have = start;
			Currency want = middle;
			final Ratio ratioStartMiddle = ratioRepository.get(want, have);
			if (ratioStartMiddle == null)
			{
				continue;
			}
			final float q2 = q1 / ratioStartMiddle.getPrice();
			///////////////
			want = end;
			have = middle;
			final Ratio ratioMiddleEnd = ratioRepository.get(want, have);
			if (ratioMiddleEnd == null)
			{
				continue;
			}
			final float q3 = q2 / ratioMiddleEnd.getPrice();
			System.out.println("current = " + start + ", " + middle + ", " + end + ", " + q3);
			if (q3 > best)
			{
				best = q3;
				bestcur = middle;
				System.out.println("best = " + start + ", " + bestcur + ", " + end + ", " + best);
			}
		}

		System.out.println("original = " + start + ", " + end + ", " + (q1 / ratioRepository.get(end, start).getPrice()));
		System.out.println("best = " + start + ", " + bestcur + ", " + end + ", " + best);
	}
}
