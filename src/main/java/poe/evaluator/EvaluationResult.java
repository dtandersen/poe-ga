package poe.evaluator;

import java.util.List;
import java.util.function.Consumer;

public class EvaluationResult
{
	private final int fitness;

	private final List<EvaluationResult.EvaluationLineItem> items;

	public int getFitness()
	{
		return fitness;
	}

	public EvaluationResult(final int fitness, final List<EvaluationResult.EvaluationLineItem> items)
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
		private final int value;

		private final String expression;

		public EvaluationLineItem(final int value, final String expression)
		{
			this.value = value;
			this.expression = expression;
		}

		public int getValue()
		{
			return value;
		}

		public String getExpression()
		{
			return expression;
		}
	}
}