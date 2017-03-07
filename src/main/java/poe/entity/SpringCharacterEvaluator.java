package poe.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import poe.entity.FitnessConfig.ElementConfig;
import poe.entity.SpringCharacterEvaluator.EvaluationResult.EvaluationLineItem;

public class SpringCharacterEvaluator implements CharacterEvaluator
{
	private final List<EvaluationCriteria> evaluations = new ArrayList<>();

	private final ExpressionParser parser;

	public SpringCharacterEvaluator(final SpringCharacterEvaluatorBuilder characterEvaluatorBuilder)
	{
		characterEvaluatorBuilder.forEachExpression(e -> evaluations.add(new EvaluationCriteria(e.getExpression())));
		parser = new SpelExpressionParser();
	}

	@Override
	public EvaluationResult evaluate(final ExpressionContext rootObject)
	{
		try
		{
			final List<EvaluationLineItem> items = new ArrayList<>();
			final StandardEvaluationContext context = new StandardEvaluationContext(rootObject);
			final AtomicInteger fitness = new AtomicInteger();
			forEachExpression(ev -> {
				final String expression = ev.getExpression();
				final Expression parseExpression = parser.parseExpression(expression);
				final int value = parseExpression.getValue(context, Integer.class);
				fitness.getAndAccumulate(value, (a, b) -> a + b);
				items.add(new EvaluationLineItem(value, parseExpression));
			});

			return new EvaluationResult(fitness.get(), items);
		}
		catch (final EvaluationException ex)
		{
			throw new RuntimeException(ex);
		}
	}

	private void forEachExpression(final Consumer<EvaluationCriteria> consumer)
	{
		evaluations.stream().forEach(consumer);
	}

	public static class EvaluationResult
	{
		private final int fitness;

		private final List<EvaluationLineItem> items;

		public int getFitness()
		{
			return fitness;
		}

		public EvaluationResult(final int fitness, final List<EvaluationLineItem> items)
		{
			this.fitness = fitness;
			this.items = items;
		}

		public void forEachLineItem(final Consumer<EvaluationLineItem> consumer)
		{
			items.stream().forEach(consumer);
		}

		public static class EvaluationLineItem
		{
			private final int value;

			private final Expression parseExpression;

			public EvaluationLineItem(final int value, final Expression parseExpression)
			{
				this.value = value;
				this.parseExpression = parseExpression;
			}

			public String getExpression()
			{
				return parseExpression.getExpressionString();
			}

			public int getFitness()
			{
				return value;
			}
		}
	}

	public static class SpringCharacterEvaluatorBuilder
	{
		private FitnessConfig fitnessConfig;

		public SpringCharacterEvaluatorBuilder from(final FitnessConfig fitnessConfig)
		{
			this.fitnessConfig = fitnessConfig;
			return this;
		}

		public SpringCharacterEvaluator build()
		{
			return new SpringCharacterEvaluator(this);
		}

		private void forEachExpression(final Consumer<ElementConfig> consumer)
		{
			fitnessConfig.getExpressions().stream().forEach(consumer);
		}
	}

	static class EvaluationCriteria
	{
		private final String expression;

		public EvaluationCriteria(final String expression)
		{
			this.expression = expression;
		}

		public String getExpression()
		{
			return expression;
		}
	}
}
