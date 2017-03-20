package poe.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CharacterItem
{
	private final List<StatValue> stats;

	public CharacterItem(final CharacterItemBuilder characterItemBuilder)
	{
		stats = characterItemBuilder.stats;
	}

	public List<StatValue> getStats()
	{
		return stats;
	}

	public void forEachStatValue(final Consumer<StatValue> action)
	{
		stats.forEach(action);
	}

	public static class CharacterItemBuilder
	{
		private final List<StatValue> stats = new ArrayList<>();

		public CharacterItemBuilder withStatValue(final Stat stat, final float value)
		{
			withStatValue(new StatValue(stat, value));
			return this;
		}

		public CharacterItemBuilder withStatValue(final StatValue statValue)
		{
			stats.add(statValue);
			return this;
		}

		public CharacterItem build()
		{
			return new CharacterItem(this);
		}

		public static CharacterItemBuilder item()
		{
			return new CharacterItemBuilder();
		}
	}
}
