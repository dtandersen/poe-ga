package us.davidandersen.poe.currency.app.monitor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import us.davidandersen.poe.currency.command.GetListings;
import us.davidandersen.poe.currency.command.GetListings.GetListingsRequest;
import us.davidandersen.poe.currency.command.GetListings.GetListingsResult;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Listing;
import us.davidandersen.poe.gateway.PoeApiGateway;

@SpringBootApplication
@ComponentScan(basePackages = "us.davidandersen.poe.currency.app")
public class MonitorApp implements CommandLineRunner
{
	private final class GetListingsResultImplementation implements GetListingsResult
	{
		private List<Listing> selling;

		private List<Listing> buying;

		@Override
		public void setSelling(final List<Listing> selling)
		{
			this.selling = selling;
		}

		@Override
		public void setBuying(final List<Listing> buying)
		{
			this.buying = buying;
		}
	}

	@Autowired
	private PoeApiGateway api;

	public static void main(final String[] args)
	{
		final SpringApplication app = new SpringApplication(MonitorApp.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(final String... args) throws Exception
	{
		while (true)
		{
			go();
			Thread.sleep(60000);
		}
	}

	private void go()
	{
		final GetListings command = new GetListings(api);
		command.setRequest(new GetListingsRequest() {
			@Override
			public String getBase()
			{
				return Currency.CHAOS.symbol();
			}

			@Override
			public String getOther()
			{
				return Currency.FUSING.symbol();
			}
		});
		final GetListingsResultImplementation result = new GetListingsResultImplementation();
		command.setResult(result);
		command.execute();

		display(result);
	}

	private void display(final GetListingsResultImplementation result)
	{
		System.out.println("Sellers");

		for (final Listing l : result.selling)
		{
			print(l);
		}
		System.out.println("Buyers");
		for (final Listing l : result.buying)
		{
			print(l);
		}
	}

	private void print(final Listing listing)
	{
		System.out.println(listing.getHave() + " " + trunc(listing.getPrice()) + " <=> " + trunc(1 / listing.getPrice()) + " " + listing.getWant());
	}

	private String trunc(final double d)
	{
		return String.format("%.2f", d);
	}
}
