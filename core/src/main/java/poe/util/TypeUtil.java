package poe.util;

import java.lang.reflect.ParameterizedType;

public class TypeUtil
{
	public static Class<?> resolveTypeArgument(final Class<?> getSuperType, final Class<?> genericIfc)
	{
		// return GenericTypeResolver.resolveTypeArguments(getSuperType,
		// genericIfc)[0];
		final Class<?> cls = (Class<?>)((ParameterizedType)getSuperType.getDeclaredConstructors()[0]
				.getGenericParameterTypes()[0]) // first constructor, first
												 // parameter
						.getActualTypeArguments()[0]; // first type argument

		return cls;
	}
}
