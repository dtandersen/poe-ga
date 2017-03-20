package poe.ga;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class IndividualCollector implements Collector<EvolutionResult, EvBuilder, Individual>
{
	@Override
	public Supplier<EvBuilder> supplier()
	{
		return EvBuilder::of;
	}

	@Override
	public BiConsumer<EvBuilder, EvolutionResult> accumulator()
	{
		return (builder, t) -> builder.add(t);
	}

	@Override
	public BinaryOperator<EvBuilder> combiner()
	{
		return (left, right) -> {
			left.addAll(right.build());
			return left;
		};
	}

	@Override
	public Function<EvBuilder, Individual> finisher()
	{
		return null;
	}

	@Override
	public Set<Characteristics> characteristics()
	{
		return null;
	}
}
