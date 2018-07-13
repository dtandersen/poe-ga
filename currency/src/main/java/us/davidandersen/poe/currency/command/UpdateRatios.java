package us.davidandersen.poe.currency.command;

import java.io.IOException;
import java.util.List;
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
		System.out.println(want + "<=" + have);
		final List<Listing> listings = poeApi.find(have, want);
		if (listings.isEmpty())
		{
			return;
		}
		final Listing listing = listings.get(0);

		ratioRepository.insert(Ratio.Builder()
				.have(have)
				.want(want)
				.price(listing.getBuyPrice()));
	}

	public interface UpdateRatioRequest
	{

		List<String> getCurrencies();
	}
}
