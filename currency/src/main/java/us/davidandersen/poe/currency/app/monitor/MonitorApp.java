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
		final Currency base = Currency.CHAOS;
		final Currency other = Currency.CHROMATIC;

		final GetListings command = new GetListings(api);
		command.setRequest(new GetListingsRequest() {
			@Override
			public String getBase()
			{
				return base.symbol();
			}

			@Override
			public String getOther()
			{
				return other.symbol();
			}

			@Override
			public int getLimit()
			{
				return 10;
			}
		});
		final GetListingsResultImplementation result = new GetListingsResultImplementation();
		command.setResult(result);
		command.execute();

		display(result, base, other);
	}

	private void display(final GetListingsResultImplementation result, final Currency buying, final Currency selling)
	{
		System.out.println("Buying " + buying.symbol() + " for " + selling.symbol());
		for (final Listing l : result.buying)
		{
			print(l);
		}

		System.out.println("Buying " + selling.symbol() + " for " + buying.symbol());
		for (final Listing l : result.selling)
		{
			print(l);
		}
	}

	private void print(final Listing listing)
	{
		System.out.println(listing.getSelling() + " " + trunc(listing.getBuyPrice()) + " <=> " + trunc(1 / listing.getBuyPrice()) + " " + listing.getBuying() + " " + listing.getNote() + " " + listing.getAccountName());
	}

	private String trunc(final double d)
	{
		return String.format("%.2f", d);
	}
}
