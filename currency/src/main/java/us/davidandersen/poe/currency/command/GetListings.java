package us.davidandersen.poe.currency.command;

import java.io.IOException;
import java.util.List;
import poe.command.BaseCommand;
import us.davidandersen.poe.currency.command.GetListings.GetListingsRequest;
import us.davidandersen.poe.currency.command.GetListings.GetListingsResult;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.entity.Listing;
import us.davidandersen.poe.gateway.PoeApiGateway;

public class GetListings extends BaseCommand<GetListingsRequest, GetListingsResult>
{
	private final PoeApiGateway poeApi;

	public GetListings(final PoeApiGateway poeApi)
	{
		this.poeApi = poeApi;
	}

	@Override
	public void execute()
	{
		try
		{
			final Currency base = Currency.ofSymbol(request.getBase());
			final Currency other = Currency.ofSymbol(request.getOther());

			final List<Listing> selling = poeApi.find(other, base);
			final List<Listing> buying = poeApi.find(base, other);

			result.setBuying(buying);
			result.setSelling(selling);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	public interface GetListingsRequest
	{
		String getBase();

		String getOther();
	}

	public interface GetListingsResult
	{
		void setBuying(List<Listing> buying);

		void setSelling(List<Listing> selling);
	}
}
