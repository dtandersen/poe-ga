package us.davidandersen.poe.currency.repository;

import java.io.IOException;
import us.davidandersen.poe.currency.Ratio.RatioBuilder;

public interface RatioRepository
{
	void insert(RatioBuilder ratio) throws IOException;
}
