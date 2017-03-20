package poe.solver;

import java.util.function.Function;

public interface Solver<IN extends Number, OUT extends Number>
{
	OUT solve(Function<IN, OUT> function, IN a, IN b, IN epsilon);
}
