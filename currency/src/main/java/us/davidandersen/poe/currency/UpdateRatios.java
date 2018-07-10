package us.davidandersen.poe.currency;

import java.io.IOException;
import java.util.List;
import poe.command.BaseCommand;
import poe.command.BaseCommand.VoidRequest;
import poe.command.BaseCommand.VoidResult;
import us.davidandersen.poe.currency.entity.Currency;
import us.davidandersen.poe.currency.repository.RatioRepository;
import us.davidandersen.poe.gateway.PoeApiGateway;

public class UpdateRatios extends BaseCommand<VoidRequest, VoidResult>
{
	private final RatioRepository ratioRepository;

	private final PoeApiGateway poeApi;

	private final Sleeper sleeper;

	public UpdateRatios(final RatioRepository ratioRepository, final PoeApiGateway poeApi, final Sleeper sleeper)
	{
		this.ratioRepository = ratioRepository;
		this.poeApi = poeApi;
		this.sleeper = sleeper;
	}

	@Override
	public void execute()
	{
		for (final Currency have : Currency.values())
		{
			for (final Currency want : Currency.values())
			{
				if (have != want)
				{
					sleeper.sleep();
					try
					{
						update(have, want);
					}
					catch (final IOException e)
					{
						e.printStackTrace();
						break;
					}
				}
			}
		}
	}

	private void update(final Currency have, final Currency want) throws IOException
	{
		final List<Listing> listings = poeApi.find(have, want);
		if (listings.isEmpty()) { return; }
		final Listing listing = listings.get(0);

		ratioRepository.insert(Ratio.Builder()
				.withHave(have)
				.want(want)
				.price(listing.getPrice()));
	}
}
