package us.davidandersen.poe.currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Ratio;
import us.davidandersen.poe.currency.entity.Ratio.RatioBuilder;
import us.davidandersen.poe.currency.repository.PriceRepository;

public class MockRatioRepository implements PriceRepository
{
	private final List<Ratio> ratios = new ArrayList<>();

	@Override
	public Ratio get(final Currency want, final Currency have)
	{
		final Optional<Ratio> findFirst = ratios.stream()
				.filter(r -> r.hasHave(have) && r.hasWant(want))
				.findFirst();

		if (!findFirst.isPresent()) { return null; }

		return findFirst.get();
	}

	@Override
	public void insert(final RatioBuilder ratio)
	{
		ratios.add(ratio.build());
	}

	@Override
	public String toString()
	{
		return ratios.toString();
	}

	@Override
	public void clear()
	{
		ratios.clear();
	}
}
