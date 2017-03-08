package poe.solver;

import java.util.function.Function;

public class BiSectionSolver implements Solver<Double, Double>
{
	final int maxTries = 100;

	/**
	 * Find root of f(x) between a and b.
	 * [a...m...b]
	 */
	@Override
	public Double solve(final Function<Double, Double> function, final Double ai, final Double bi, final Double epsilon)
	{
		double a = ai;
		double b = bi;

		int i = 0;
		double m;
		while ((b - a) > epsilon)
		{

			final double y_a = function.apply(a);
			final double y_b = function.apply(b);

			if (y_a * y_b > 0) { throw new RuntimeException("no root"); }

			m = (a + b) / 2;

			final double y_m = function.apply(m);

			System.out.println("f(" + m + ")=" + y_m);

			if (y_m == 0)
			{
				a = m;
				b = m;
			}
			else if (y_m * y_a < 0)
			{
				b = m;
			}
			else
			{
				a = m;
			}

			// System.out.println("New interval: [" + a + " .. " + b + "]");

			if (i++ > maxTries) { throw new RuntimeException("too many try"); }
		}

		return (a + b) / 2;
	}
}
