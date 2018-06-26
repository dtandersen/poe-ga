package verifier;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ComponentScan(basePackages = { "poe.app.config" }, excludeFilters = {})
public class SkillVerifier implements CommandLineRunner
{
	@Override
	public void run(final String... arg0) throws Exception
	{
	}

	public static void main(final String[] args) throws Exception
	{
		SpringApplication.run(SkillVerifier.class, args);
	}
}
