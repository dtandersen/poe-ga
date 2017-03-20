package poe.util;

import org.springframework.core.GenericTypeResolver;

public class TypeUtil
{
	public static Class<?> resolveTypeArgument(final Class<?> getSuperType, final Class<?> genericIfc)
	{
		return GenericTypeResolver.resolveTypeArguments(getSuperType, genericIfc)[0];
	}
}