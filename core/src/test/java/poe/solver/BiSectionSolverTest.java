package poe.solver;

import static org.junit.Assert.assertThat;
import org.hamcrest.Matchers;
import org.junit.Test;

public class BiSectionSolverTest
{
	@Test
	public void test()
	{
		final double result = new BiSectionSolver().solve(x -> x + 5, -10d, 1d, .5);

		assertThat(result, Matchers.closeTo(-5d, .5));
	}

	@Test
	public void tes2t()
	{
		final double result = new BiSectionSolver().solve(x -> -x + 3, 1d, 5d, .5);

		assertThat(result, Matchers.closeTo(3, .5));
	}

	@Test
	public void tes3t()
	{
		final double result = new BiSectionSolver().solve(x -> -x + 3, 2.35d, 7.2d, .5);

		assertThat(result, Matchers.closeTo(3, .5));
	}
}
