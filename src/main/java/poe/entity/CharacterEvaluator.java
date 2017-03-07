package poe.entity;

import java.util.ArrayList;
import java.util.List;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class CharacterEvaluator
{
	private final List<Evaluation> evaluations = new ArrayList<>();

	public CharacterEvaluator(final CharacterEvaluatorBuilder characterEvaluatorBuilder)
	{
		characterEvaluatorBuilder.fitnessConfig.getExpressions().stream().forEach(e -> evaluations.add(new Evaluation(e.getExpression())));
	}

	public int evaluate(final ExpressionContext context)
	{
		try
		{
			final StandardEvaluationContext teslaContext = new StandardEvaluationContext(context);
			final ExpressionParser parser = new SpelExpressionParser();
			int total = 0;
			for (final Evaluation ev : evaluations)
			{
				final String expression = ev.getExpression();
				final Expression parseExpression = parser.parseExpression(expression);
				total += parseExpression.getValue(teslaContext, Integer.class);
			}
			return total;
		}
		catch (final Exception ex)
		{
			ex.printStackTrace();
			return 0;
		}
	}

	public static class CharacterEvaluatorBuilder
	{
		private FitnessConfig fitnessConfig;

		public CharacterEvaluatorBuilder from(final FitnessConfig fitnessConfig)
		{
			this.fitnessConfig = fitnessConfig;
			return this;
		}

		public CharacterEvaluator build()
		{
			return new CharacterEvaluator(this);
		}
	}

	static class Evaluation
	{
		private final String expression;

		public Evaluation(final String expression)
		{
			this.expression = expression;
		}

		public String getExpression()
		{
			return expression;
		}
	}
}
