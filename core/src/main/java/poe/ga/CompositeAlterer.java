package poe.ga;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeAlterer implements Alterer
{
	List<Alterer> alterers;

	public CompositeAlterer(final List<Alterer> alterers)
	{
		this.alterers = alterers;
	}

	public static Alterer of(final Alterer... mutator)
	{
		return new CompositeAlterer(Arrays.stream(mutator).collect(Collectors.toList()));
	}

}
