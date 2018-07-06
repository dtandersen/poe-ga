package poe.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Container for stats. When a new stat is added it increments the old stat.
 *
 * @author David Andersen
 * @deprecated Use {@link StatValues}.
 */
@Deprecated
public class StatBucket
{
	private final Map<Stat, StatValue> stats = new HashMap<>();

	public void add(final StatValue stat)
	{
		final StatValue existing = stats.get(stat.getStat());

		if (existing == null)
		{
			stats.put(stat.getStat(), stat);
		}
		else
		{
			stats.put(stat.getStat(), existing.increment(stat.getValue()));
		}
	}

	public List<StatValue> getStatValues()
	{
		return Collections.unmodifiableList(new ArrayList<>(stats.values()));
	}
}
