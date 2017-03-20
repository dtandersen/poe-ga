package poe.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import poe.command.PureImmutablePassiveSkill.ImmutablePassiveSkillBuilder;
import poe.command.model.ImmutableCharacter;
import poe.entity.AttributeValue;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PoeCharacter;
import poe.entity.Stat;
import poe.entity.StatValue;

public class PureImmutableCharacter implements ImmutableCharacter
{
	private final Collection<StatValue> statValues;

	private final Map<Stat, StatValue> adjustedStats;

	private final Collection<AttributeValue> stats;

	private final List<ImmutablePassiveSkill> passiveSkills;

	private final CharacterClass characterClass;

	private final String url;

	private final int level;

	public PureImmutableCharacter(final ImmutableCharacterBuilder pureImmutableCharacterBuilder)
	{
		this.statValues = pureImmutableCharacterBuilder.statValues;
		this.stats = pureImmutableCharacterBuilder.stats;
		this.passiveSkills = pureImmutableCharacterBuilder.passiveSkills;
		this.characterClass = pureImmutableCharacterBuilder.characterClass;
		this.url = pureImmutableCharacterBuilder.url;
		this.level = pureImmutableCharacterBuilder.level;
		this.adjustedStats = pureImmutableCharacterBuilder.adjustedStats;
	}

	@Override
	public Collection<AttributeValue> getStats()
	{
		return stats;
	}

	@Override
	public float getAdjustedStat(final Stat stat)
	{
		final StatValue value = adjustedStats.get(stat);
		if (value == null) { return 0; }

		return value.getValue();
	}

	@Override
	public Collection<StatValue> getStatValues()
	{
		return statValues;
	}

	@Override
	public List<Integer> getPassiveSkillIds()
	{
		return passiveSkills.stream()
				.map(new Function<ImmutablePassiveSkill, Integer>() {
					@Override
					public Integer apply(final ImmutablePassiveSkill passiveSkill)
					{
						return passiveSkill.getPassiveSkillId();
					}
				})
				.collect(Collectors.toList());
	}

	@Override
	public CharacterClass getCharacterClass()
	{
		return characterClass;
	}

	@Override
	public int getLevel()
	{
		return level;
	}

	@Override
	public List<ImmutablePassiveSkill> getPassiveSkills()
	{
		return passiveSkills;
	}

	@Override
	public String getUrl()
	{
		return url;
	}

	public static class ImmutableCharacterBuilder
	{
		public Map<Stat, StatValue> adjustedStats = new HashMap<>();

		public CharacterClass characterClass;

		private Collection<StatValue> statValues;

		private Collection<AttributeValue> stats;

		private final List<ImmutablePassiveSkill> passiveSkills;

		private String url;

		private int level;

		public ImmutableCharacterBuilder()
		{
			passiveSkills = new ArrayList<>();
		}

		public ImmutableCharacterBuilder withStatValues(final Collection<StatValue> statValues)
		{
			this.statValues = statValues;
			return this;
		}

		public ImmutableCharacterBuilder withStats(final Collection<AttributeValue> stats)
		{
			this.stats = stats;
			return this;
		}

		public ImmutableCharacterBuilder withPassiveSkills(final Collection<PassiveSkill> passiveSkills)
		{
			for (final PassiveSkill passiveSkill : passiveSkills)
			{
				final ImmutablePassiveSkill immutablePassiveSkill = ImmutablePassiveSkillBuilder.passiveSkill()
						.withPassiveSkillId(passiveSkill.getId())
						.withName(passiveSkill.getName())
						.build();
				this.passiveSkills.add(immutablePassiveSkill);
			}
			return this;
		}

		public ImmutableCharacterBuilder withCharacterClass(final CharacterClass characterClass)
		{
			this.characterClass = characterClass;
			return this;
		}

		private void withUrl(final String url)
		{
			this.url = url;
		}

		public ImmutableCharacterBuilder from(final PoeCharacter character)
		{
			withCharacterClass(character.getCharacterClass());
			withPassiveSkills(character.getPassiveSkillsWithoutRoot());
			withStatValues(character.getStatValues());
			withStats(character.getAttributes());
			withUrl(new PoeComUrlBuilder()
					.withCharacterClass(character.getCharacterClass())
					.withPassiveSkillIds(character.getPassiveSkillIds())
					.toUrl());

			return this;
		}

		public ImmutableCharacter build()
		{
			return new PureImmutableCharacter(this);
		}

		public static ImmutableCharacterBuilder character()
		{
			return new ImmutableCharacterBuilder();
		}

		public ImmutableCharacterBuilder withLevel(final int level)
		{
			this.level = level;
			return this;
		}

		public ImmutableCharacterBuilder withAdjustedStats(final List<StatValue> adjustedStats)
		{
			adjustedStats.forEach(stat -> this.adjustedStats.put(stat.getStat(), stat));
			return this;
		}
	}

	@Override
	public List<StatValue> getAdjustedStats()
	{
		return new ArrayList<>(adjustedStats.values());
	}
}
