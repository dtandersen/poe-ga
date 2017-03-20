package poe.app.evolve;

import java.util.List;

public class YamlConfig
{
	private String characterClass;

	private Integer generations;

	private Integer skills;

	private Integer population;

	private List<YamlAlterer> alterers;

	private List<YamlEvaluator> evaluators;

	private List<YamlItem> items;

	private int level;

	public YamlConfig()
	{
	}

	public String getCharacterClass()
	{
		return characterClass;
	}

	public void setCharacterClass(final String characterClass)
	{
		this.characterClass = characterClass;
	}

	public int getGenerations()
	{
		return generations;
	}

	public void setGenerations(final int generations)
	{
		this.generations = generations;
	}

	public int getSkills()
	{
		return skills;
	}

	public void setSkills(final int skills)
	{
		this.skills = skills;
	}

	public int getPopulation()
	{
		return population;
	}

	public void setPopulation(final int population)
	{
		this.population = population;
	}

	public List<YamlAlterer> getAlterers()
	{
		return alterers;
	}

	public void setAlterers(final List<YamlAlterer> alterers)
	{
		this.alterers = alterers;
	}

	public List<YamlEvaluator> getEvaluators()
	{
		return evaluators;
	}

	public void setEvaluators(final List<YamlEvaluator> evaluators)
	{
		this.evaluators = evaluators;
	}

	public void setLevel(final int level)
	{
		this.level = level;
	}

	public int getLevel()
	{
		return level;
	}

	public List<YamlItem> getItems()
	{
		return items;
	}

	public void setItems(final List<YamlItem> items)
	{
		this.items = items;
	}

	public static class YamlAlterer
	{
		private String type;

		private float probability;

		public String getType()
		{
			return type;
		}

		public void setType(final String type)
		{
			this.type = type;
		}

		public float getProbability()
		{
			return probability;
		}

		public void setProbability(final float probability)
		{
			this.probability = probability;
		}
	}

	public static class YamlEvaluator
	{
		private String expression;

		private String scale;

		public String getExpression()
		{
			return expression;
		}

		public void setExpression(final String expression)
		{
			this.expression = expression;
		}

		public String getScale()
		{
			return scale;
		}

		public void setScale(final String scale)
		{
			this.scale = scale;
		}
	}

	public static class YamlItem
	{
		public List<YamlItemStat> stats;

		public static class YamlItemStat
		{
			public String stat;
		}

		public List<YamlItemStat> getStats()
		{
			return stats;
		}
	}
}
