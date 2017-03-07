package poe.entity;

import java.util.ArrayList;
import java.util.List;
import poe.entity.FitnessConfig.ElementConfig.ElementConfigBuilder;

public class FitnessConfig
{
	private final List<ElementConfig> elements;

	public FitnessConfig(final FitnessConfigBuilder fitnessConfigBuilder)
	{
		this.elements = fitnessConfigBuilder.elements;
	}

	public List<ElementConfig> getExpressions()
	{
		return elements;
	}

	public static class FitnessConfigBuilder
	{
		private final List<ElementConfig> elements = new ArrayList<>();

		public FitnessConfigBuilder withElement(final ElementConfigBuilder elementConfigBuilder)
		{
			elements.add(elementConfigBuilder.build());
			return this;
		}

		public FitnessConfig build()
		{
			return new FitnessConfig(this);
		}

		public static FitnessConfigBuilder config()
		{
			return new FitnessConfigBuilder();
		}
	}

	public static class ElementConfig
	{
		private final String expression;

		public String getExpression()
		{
			return expression;
		}

		public ElementConfig(final ElementConfigBuilder elementConfigBuilder)
		{
			this.expression = elementConfigBuilder.expression;
		}

		public static class ElementConfigBuilder
		{
			private String expression;

			public ElementConfigBuilder withExpression(final String expression)
			{
				this.expression = expression;
				return this;
			}

			public ElementConfig build()
			{
				return new ElementConfig(this);
			}

			public static ElementConfigBuilder element()
			{
				return new ElementConfigBuilder();
			}
		}

	}
}
