package us.davidandersen.poe.currency.app;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import us.davidandersen.poe.currency.app.YamlRatioRepository.Ratios.RatioYaml;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Ratio;
import us.davidandersen.poe.currency.entity.Ratio.RatioBuilder;
import us.davidandersen.poe.currency.repository.PriceRepository;

public class YamlRatioRepository implements PriceRepository
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

	@Override
	public Ratio get(final Currency want, final Currency have) throws IOException
	{
		final Yaml yaml = new Yaml();
		Ratios ratios = yaml.loadAs(new FileInputStream("prices.yaml"), Ratios.class);
		if (ratios == null)
		{
			ratios = new Ratios();
		}
		final RatioYaml ratioYaml = ratios.ratios.get(have + "-" + want);
		if (ratioYaml == null) { return null; }
		return toRatio(ratioYaml);
	}

	private RatioYaml ratio2Yaml(final Ratio ratio)
	{
		final RatioYaml yaml = new RatioYaml();
		yaml.have = ratio.getHave().symbol();
		yaml.want = ratio.getWant().symbol();
		yaml.price = ratio.getPrice();
		return yaml;
	}

	private Ratio toRatio(final RatioYaml ratioYaml)
	{
		return Ratio.Builder()
				.want(Currency.ofSymbol(ratioYaml.want))
				.have(Currency.ofSymbol(ratioYaml.have))
				.price(ratioYaml.price)
				.build();
	}
}
