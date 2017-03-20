package poe.evaluator;

import java.util.List;
import java.util.function.Consumer;

public class EvaluationResult
{
	private final float fitness;

	private final List<EvaluationResult.EvaluationLineItem> items;

	public float getFitness()
	{
		return fitness;
	}

	public EvaluationResult(final float fitness, final List<EvaluationResult.EvaluationLineItem> items)
	{
		this.fitness = fitness;
		this.items = items;
	}

	public void forEachLineItem(final Consumer<EvaluationResult.EvaluationLineItem> consumer)
	{
		items.stream().forEach(consumer);
	}

	public static class EvaluationLineItem
	{
		private final float value;

		private final String expression;

		public EvaluationLineItem(final float value, final String expression)
		{
			this.value = value;
			this.expression = expression;
		}

		public float getValue()
		{
			return value;
		}

		public String getExpression()
		{
			return expression;
		}
	}
}