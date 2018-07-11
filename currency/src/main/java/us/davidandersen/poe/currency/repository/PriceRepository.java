package us.davidandersen.poe.currency.repository;

import java.io.IOException;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Ratio;
import us.davidandersen.poe.currency.entity.Ratio.RatioBuilder;

public interface PriceRepository
{
	void insert(RatioBuilder ratio) throws IOException;

	Ratio get(Currency want, Currency have) throws IOException;
}
