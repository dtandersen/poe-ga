package poe.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import poe.command.PureImmutablePassiveSkill.ImmutablePassiveSkillBuilder;
import poe.entity.AttributeValue;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill;
import poe.entity.PoeCharacter;
import poe.entity.StatValue;

public class PureImmutableCharacter implements ImmutableCharacter
{
	private final Collection<StatValue> statValues;

	private final Collection<AttributeValue> stats;

	private final List<ImmutablePassiveSkill> passiveSkills;

	private final CharacterClass characterClass;

	private final String url;

	public PureImmutableCharacter(final ImmutableCharacterBuilder pureImmutableCharacterBuilder)
	{
		this.statValues = pureImmutableCharacterBuilder.statValues;
		this.stats = pureImmutableCharacterBuilder.stats;
		this.passiveSkills = pureImmutableCharacterBuilder.passiveSkills;
		this.characterClass = pureImmutableCharacterBuilder.characterClass;
		this.url = pureImmutableCharacterBuilder.url;
	}

	@Override
	public Collection<AttributeValue> getStats()
	{
		return stats;
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
		public CharacterClass characterClass;

		private Collection<StatValue> statValues;

		private Collection<AttributeValue> stats;

		private final List<ImmutablePassiveSkill> passiveSkills;

		private String url;

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
			withPassiveSkills(character.getPassiveSkills());
			withStatValues(character.getStatValues());
			withStats(character.getAttributes());
			withUrl(new PoeComUrlBuilder()
					.withCharacterClass(character.getCharacterClass())
					.withPassiveSkillIds(character.getPassiveSkillIds())
					.toUrl());

			return this;
		}

		public PureImmutableCharacter build()
		{
			return new PureImmutableCharacter(this);
		}

		public static ImmutableCharacterBuilder character()
		{
			return new ImmutableCharacterBuilder();
		}
	}
}