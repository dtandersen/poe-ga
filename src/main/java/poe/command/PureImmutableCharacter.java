package poe.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import poe.command.PureImmutableSkill.ImmutablePassiveSkillBuilder;
import poe.entity.AttributeValue;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill;
import poe.entity.StatValue;

public class PureImmutableCharacter implements ImmutableCharacter
{
	private final List<Integer> passiveSkillIds;

	private final Collection<StatValue> statValues;

	private final Collection<AttributeValue> stats;

	private final List<ImmutablePassiveSkill> passiveSkills;

	public PureImmutableCharacter(final ImmutableCharacterBuilder pureImmutableCharacterBuilder)
	{
		this.passiveSkillIds = pureImmutableCharacterBuilder.passiveSkillIds;
		this.statValues = pureImmutableCharacterBuilder.statValues;
		this.stats = pureImmutableCharacterBuilder.stats;
		this.passiveSkills = pureImmutableCharacterBuilder.passiveSkills;
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
		return passiveSkillIds;
	}

	@Override
	public List<ImmutablePassiveSkill> getPassiveSkills()
	{
		return passiveSkills;
	}

	public static class ImmutableCharacterBuilder
	{
		private List<Integer> passiveSkillIds;

		private Collection<StatValue> statValues;

		private Collection<AttributeValue> stats;

		private final List<ImmutablePassiveSkill> passiveSkills;

		public ImmutableCharacterBuilder()
		{
			passiveSkills = new ArrayList<>();
		}

		public ImmutableCharacterBuilder withPassiveSkillIds(final List<Integer> passiveSkillIds)
		{
			this.passiveSkillIds = passiveSkillIds;
			return this;
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

		public ImmutableCharacterBuilder withPassiveSkill(final List<PassiveSkill> passiveSkills)
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