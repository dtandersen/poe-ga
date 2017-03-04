package poe.app.evolve;

import java.util.List;

public class EvolveConfig
{
	private String characterClass;

	private Integer generations;

	private Integer skills;

	private Integer population;

	private List<MyAlterer> alterers;

	private List<MyEvaluator> evaluators;

	public EvolveConfig()
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

	public List<MyAlterer> getAlterers()
	{
		return alterers;
	}

	public void setAlterers(final List<MyAlterer> alterers)
	{
		this.alterers = alterers;
	}

	public List<MyEvaluator> getEvaluators()
	{
		return evaluators;
	}

	public void setEvaluators(final List<MyEvaluator> evaluators)
	{
		this.evaluators = evaluators;
	}

	public static class MyAlterer
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

	public static class MyEvaluator
	{
		private String expression;

		public String getExpression()
		{
			return expression;
		}

		public void setExpression(final String expression)
		{
			this.expression = expression;
		}
	}
}
