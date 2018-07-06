package io.jenetics;

import io.jenetics.Alterer;
import io.jenetics.Gene;

public class PublicCompositeAlterer
{
	@SafeVarargs
	public static <G extends Gene<?, G>, C extends Comparable<? super C>> CompositeAlterer<G, C> of(final Alterer<G, C>... alterers)
	{
		return CompositeAlterer.of(alterers);
	}
}
