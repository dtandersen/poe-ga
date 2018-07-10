package us.davidandersen.poe.gateway;

import java.io.IOException;
import java.util.List;
import us.davidandersen.poe.currency.Listing;
import us.davidandersen.poe.currency.entity.Currency;

public interface PoeApiGateway
{
	List<Listing> find(Currency have, Currency want) throws IOException;
}
