package us.davidandersen.poe.currency.repository;

import java.io.IOException;
import us.davidandersen.poe.currency.Ratio;
import us.davidandersen.poe.currency.Ratio.RatioBuilder;
import us.davidandersen.poe.currency.entity.Currency;

public interface RatioRepository
{
	void insert(RatioBuilder ratio) throws IOException;

	Ratio get(Currency want, Currency have) throws IOException;
}
