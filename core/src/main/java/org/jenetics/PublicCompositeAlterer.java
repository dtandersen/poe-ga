package org.jenetics;

public class PublicCompositeAlterer
{
	@SafeVarargs
	public static <G extends Gene<?, G>, C extends Comparable<? super C>> CompositeAlterer<G, C> of(final Alterer<G, C>... alterers)
	{
		return CompositeAlterer.of(alterers);
	}
}
