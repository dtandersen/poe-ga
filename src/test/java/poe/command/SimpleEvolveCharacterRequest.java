package poe.command;

import java.util.List;
import poe.command.EvolveCharacter.EvolveCharacterRequest;
import poe.command.FitnessConfig.FitnessConfigBuilder;
import poe.entity.CharacterClass;

public class SimpleEvolveCharacterRequest implements EvolveCharacterRequest
{
	private final CharacterClass characterClass;

	private final int population;

	private final int generations;

	private final FitnessConfig fitnessConfig;

	private final int threads = 1;

	private final int skills;

	@Override
	public CharacterClass getCharacterClass()
	{
		return characterClass;
	}

	@Override
	public List<AltererConfig> getAlterers()
	{
		return null;
	}

	@Override
	public long getGenerationLimit()
	{
		return generations;
	}

	@Override
	public long getPopulation()
	{
		return population;
	}

	@Override
	public FitnessConfig getFitnessConfig()
	{
		return fitnessConfig;
	}

	@Override
	public int getSkills()
	{
		return skills;
	}

	@Override
	public int getThreads()
	{
		return threads;
	}

	public SimpleEvolveCharacterRequest(final SimpleEvolveCharacterRequestBuilder simpleEvolveCharacterRequestBuilder)
	{
		this.characterClass = simpleEvolveCharacterRequestBuilder.characterClass;
		this.population = simpleEvolveCharacterRequestBuilder.population;
		this.generations = simpleEvolveCharacterRequestBuilder.generations;
		this.fitnessConfig = simpleEvolveCharacterRequestBuilder.fitnessConfig;
		this.skills = simpleEvolveCharacterRequestBuilder.skillCount;
	}

	public static class SimpleEvolveCharacterRequestBuilder
	{
		private CharacterClass characterClass;

		private int population;

		private int generations;

		private FitnessConfig fitnessConfig;

		private int skillCount;

		public SimpleEvolveCharacterRequestBuilder withCharacterClass(final CharacterClass characterClass)
		{
			this.characterClass = characterClass;
			return this;
		}

		public SimpleEvolveCharacterRequestBuilder withPopulation(final int population)
		{
			this.population = population;
			return this;
		}

		public SimpleEvolveCharacterRequestBuilder withGenerations(final int generations)
		{
			this.generations = generations;
			return this;
		}

		public SimpleEvolveCharacterRequestBuilder withFitnessConfig(final FitnessConfigBuilder fitnessConfigBuilder)
		{
			this.fitnessConfig = fitnessConfigBuilder.build();
			return this;
		}

		public SimpleEvolveCharacterRequestBuilder withSkillCount(final int skillCount)
		{
			this.skillCount = skillCount;
			return this;
		}

		public SimpleEvolveCharacterRequest build()
		{
			return new SimpleEvolveCharacterRequest(this);
		}

		static SimpleEvolveCharacterRequestBuilder request()
		{
			return new SimpleEvolveCharacterRequestBuilder();
		}
	}
}
