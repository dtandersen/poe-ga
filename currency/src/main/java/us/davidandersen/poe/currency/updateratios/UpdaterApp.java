package us.davidandersen.poe.currency.updateratios;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import us.davidandersen.poe.currency.command.UpdateRatios;
import us.davidandersen.poe.currency.command.UpdateRatios.UpdateRatioRequest;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.repository.PriceRepository;
import us.davidandersen.poe.gateway.PoeApiGateway;

@SpringBootApplication
@ComponentScan(basePackages = "us.davidandersen.poe.currency.config")
public class UpdaterApp implements CommandLineRunner
{
	@Autowired
	private PriceRepository ratioRepository;

	@Autowired
	private PoeApiGateway poeApi;

	public static void main(final String[] args)
	{
		final SpringApplication app = new SpringApplication(UpdaterApp.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(final String... args) throws Exception
	{
		final List<String> currencies = list(
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

		final UpdateRatios command = new UpdateRatios(ratioRepository, poeApi);
		command.setRequest(new UpdateRatioRequest() {
			@Override
			public List<String> getCurrencies()
			{
				return currencies;
			}
		});
		command.execute();
	}

	private List<String> list(final Currency... currencies)
	{
		return Arrays.stream(currencies)
				.map(c -> c.symbol())
				.collect(Collectors.toList());
	}
}
