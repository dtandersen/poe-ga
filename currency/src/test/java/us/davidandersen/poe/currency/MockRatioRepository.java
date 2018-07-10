package us.davidandersen.poe.currency;

import java.util.ArrayList;
import java.util.List;
import us.davidandersen.poe.currency.Ratio.RatioBuilder;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.repository.RatioRepository;

public class MockRatioRepository implements RatioRepository
{
	private final List<Ratio> ratios = new ArrayList<>();

	public Ratio get(final Currency have, final Currency want)
	{
		return ratios.stream()
				.filter(r -> r.hasHave(have) && r.hasWant(want))
				.findFirst()
				.get();
	}

	@Override
	public void insert(final RatioBuilder ratio)
	{
		ratios.add(ratio.build());
	}
}
