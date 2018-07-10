package us.davidandersen.poe.currency.app;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import us.davidandersen.poe.currency.Ratio;
import us.davidandersen.poe.currency.Ratio.RatioBuilder;
import us.davidandersen.poe.currency.app.YamlRatioRepository.Ratios.RatioYaml;
import us.davidandersen.poe.currency.repository.RatioRepository;

public class YamlRatioRepository implements RatioRepository
{
	@Override
	public void insert(final RatioBuilder ratioBuilder) throws IOException
	{
		final Yaml yaml = new Yaml();
		Ratios ratios = yaml.loadAs(new FileInputStream("prices.yaml"), Ratios.class);
		if (ratios == null)
		{
			ratios = new Ratios();
		}
		final Ratio ratio = ratioBuilder.build();
		final RatioYaml r = ratio2Yaml(ratio);
		ratios.ratios.put(ratio.getHave() + "-" + ratio.getWant(), r);
		yaml.dump(ratios, new FileWriter("prices.yaml"));
	}

	private RatioYaml ratio2Yaml(final Ratio ratio)
	{
		final RatioYaml yaml = new RatioYaml();
		yaml.have = ratio.getHave().getShortName();
		yaml.want = ratio.getWant().getShortName();
		yaml.price = ratio.getPrice();
		return yaml;
	}

	static class Ratios
	{
		public Map<String, RatioYaml> ratios = new HashMap<>();

		static class RatioYaml
		{
			public String have;

			public String want;

			public float price;
		}
	}
}
