package us.davidandersen.poe.currency.findbest;

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
import us.davidandersen.poe.currency.repository.PriceRepository;

@SpringBootApplication
@ComponentScan(basePackages = "us.davidandersen.poe.currency.config")
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
		final Currency have = Currency.GEMCUTTER;
		final Currency want = Currency.CHAOS;
		final float quantity = 20;
		final int maxTrades = 4;

		final FindBest command = new FindBest(ratioRepository);
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
}
