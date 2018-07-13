package us.davidandersen.poe.currency.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.davidandersen.poe.currency.repository.PriceRepository;
import us.davidandersen.poe.gateway.PoeApiGateway;
import us.davidandersen.poeapi.PoeApiClient;
import us.davidandersen.poeapi.square.SquarePoeApiClient;
import us.davidandersen.poeapi.square.ThrottledPoeApiClient;

@Configuration
public class PoeAppConfig
{
	@Bean
	PriceRepository ratioRepository()
	{
		return new YamlRatioRepository();
	}

	@Bean
	PoeApiGateway poeApiGateway(final PoeApiClient client)
	{
		return new DefaultPoeApiGateway(client);
	}

	@Bean
	PoeApiClient poeApiClient()
	{
		return new ThrottledPoeApiClient(new SquarePoeApiClient());
	}
}
