package us.davidandersen.poe.currency.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.davidandersen.poe.currency.Sleeper;
import us.davidandersen.poe.currency.repository.PriceRepository;
import us.davidandersen.poe.gateway.PoeApiGateway;

@Configuration
public class MyConfig
{
	@Bean
	PriceRepository ratioRepository()
	{
		return new YamlRatioRepository();
	}

	@Bean
	PoeApiGateway poeApiGateway()
	{
		return new MyPoeApiGateway();
	}

	@Bean
	Sleeper sleeper()
	{
		return new MySleeper();
	}
}
