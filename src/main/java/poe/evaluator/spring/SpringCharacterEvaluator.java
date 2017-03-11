package poe.evaluator.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import poe.command.model.FitnessConfig;
import poe.command.model.FitnessConfig.ElementConfig;
import poe.evaluator.CharacterEvaluator;
import poe.evaluator.EvaluationResult;
import poe.evaluator.ExpressionContext;
import poe.evaluator.EvaluationResult.EvaluationLineItem;

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
			// final AtomicFloat fitness = new AtomicInteger();
			float fitness = 0;
			// forEachExpression(ev -> {
			for (final EvaluationCriteria ev : evaluations)
			{
				final String expression = ev.getExpression();
				final Expression parseExpression = parser.parseExpression(expression);
				final float value = parseExpression.getValue(context, Float.class);
				// fitness.getAndAccumulate(value, (a, b) -> a + b);
				fitness += value;
				items.add(new EvaluationLineItem(value, expression));
			}

			return new EvaluationResult(fitness, items);
		}
		catch (final EvaluationException ex)
		{
			throw new RuntimeException(ex);
		}
	}

	// private void forEachExpression(final Consumer<EvaluationCriteria> consumer)
	// {
	// evaluations.stream().forEach(consumer);
	// }

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

	private static class EvaluationCriteria
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
