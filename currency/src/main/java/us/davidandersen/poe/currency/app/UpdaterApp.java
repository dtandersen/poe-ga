package us.davidandersen.poe.currency.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.davidandersen.poe.currency.Sleeper;
import us.davidandersen.poe.currency.UpdateRatios;
import us.davidandersen.poe.currency.repository.RatioRepository;
import us.davidandersen.poe.gateway.PoeApiGateway;

@SpringBootApplication
public class UpdaterApp implements CommandLineRunner
{
	@Autowired
	private RatioRepository ratioRepository;

	@Autowired
	private PoeApiGateway poeApi;

	@Autowired
	private Sleeper sleeper;

	public static void main(final String[] args)
	{
		final SpringApplication app = new SpringApplication(UpdaterApp.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(final String... args) throws Exception
	{
		final UpdateRatios command = new UpdateRatios(ratioRepository, poeApi, sleeper);
		command.execute();
	}
}
