package us.davidandersen.poe.currency.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import poe.command.BaseCommand;
import poe.command.BaseCommand.VoidResult;
import us.davidandersen.poe.currency.command.UpdateRatios.UpdateRatioRequest;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Listing;
import us.davidandersen.poe.currency.entity.Ratio;
import us.davidandersen.poe.currency.repository.PriceRepository;
import us.davidandersen.poe.gateway.PoeApiGateway;

public class UpdateRatios extends BaseCommand<UpdateRatioRequest, VoidResult>
{
	private final PriceRepository ratioRepository;

	private final PoeApiGateway poeApi;

	public UpdateRatios(
			final PriceRepository ratioRepository,
			final PoeApiGateway poeApi)
	{
		this.ratioRepository = ratioRepository;
		this.poeApi = poeApi;
	}

	@Override
	public void execute()
	{
		try
		{
			ratioRepository.clear();
			final List<String> currencyStrings = request.getCurrencies();
			final List<Currency> currencies = currencyStrings.stream()
					.map(c -> Currency.ofSymbol(c))
					.collect(Collectors.toList());

			for (final Currency have : currencies)
			{
				for (final Currency want : currencies)
				{
					if (have != want)
					{
						update(have, want);
					}
				}
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	private void update(final Currency have, final Currency want) throws IOException
	{
		System.out.println(want + "<=" + have);
		final List<Listing> listings = poeApi.find(have, want, 30);
		if (listings.size() < 20) { return; }
		if (listings.isEmpty()) { return; }
		// final Listing listing = listings.get(0);
		// final float avg = (float)listings.stream().mapToDouble(l ->
		// l.getBuyPrice()).average().getAsDouble();
		// final Counter counter = new Counter();
		// for (final Listing l : listings)
		// {
		// counter.put(l.getBuyPrice());
		// }

		// final float price = counter.mostCommon();
		final float price = (float)listings.stream().limit(5).mapToDouble(l -> l.getBuyPrice()).average().getAsDouble();
		ratioRepository.insert(Ratio.Builder()
				.have(have)
				.want(want)
				.price(price));
	}

	static class Counter
	{
		Map<String, Integer> counts = new HashMap<>();

		public void put(final float buyPrice)
		{
			final String k = key(buyPrice);
			if (counts.containsKey(k))
			{
				int c = counts.get(k);
				c++;
				counts.put(k, c);
			}
			else
			{
				counts.put(k, 1);
			}
		}

		public float mostCommon()
		{
			float p = Float.MAX_VALUE;
			int c = 0;
			for (final Entry<String, Integer> e : counts.entrySet())
			{
				final Integer cc = e.getValue();
				final String kk = e.getKey();
				if (cc > c && Float.parseFloat(kk) < p)
				{
					p = Float.parseFloat(kk);
					c = cc;
				}
			}

			// final List<Entry<String, Integer>> entries = new ArrayList<>();
			// for (final Entry<String, Integer> e : counts.entrySet())
			// {
			// entries.add(e);
			// }
			// Collections.sort(entries, new Comparator<Entry<String,
			// Integer>>() {
			// @Override
			// public int compare(final Entry<String, Integer> e1, final
			// Entry<String, Integer> e2)
			// {
			// return 0;
			// }
			// });

			return p;
		}

		private String key(final float buyPrice)
		{
			return String.format("%.3f", buyPrice);
		}
	}

	public interface UpdateRatioRequest
	{

		List<String> getCurrencies();
	}
}
