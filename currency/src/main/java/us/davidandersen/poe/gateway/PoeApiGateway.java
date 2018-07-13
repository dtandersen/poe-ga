package us.davidandersen.poe.gateway;

import java.io.IOException;
import java.util.List;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Listing;

public interface PoeApiGateway
{
	List<Listing> find(Currency have, Currency want) throws IOException;

	List<Listing> find(Currency have, Currency want, int limit) throws IOException;
}
