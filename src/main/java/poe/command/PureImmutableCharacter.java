package poe.command;

import java.util.Collection;
import java.util.List;
import poe.entity.AttributeValue;
import poe.entity.ImmutableCharacter;
import poe.entity.StatValue;

final class PureImmutableCharacter implements ImmutableCharacter
{
	private final List<Integer> passiveSkillIds;

	private final Collection<StatValue> statValues;

	private final Collection<AttributeValue> stats;

	public PureImmutableCharacter(final PureImmutableCharacterBuilder pureImmutableCharacterBuilder)
	{
		this.passiveSkillIds = pureImmutableCharacterBuilder.passiveSkillIds;
		this.statValues = pureImmutableCharacterBuilder.statValues;
		this.stats = pureImmutableCharacterBuilder.stats;
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

	public static class PureImmutableCharacterBuilder
	{
		private List<Integer> passiveSkillIds;

		private Collection<StatValue> statValues;

		private Collection<AttributeValue> stats;

		public PureImmutableCharacterBuilder withPassiveSkillIds(final List<Integer> passiveSkillIds)
		{
			this.passiveSkillIds = passiveSkillIds;
			return this;
		}

		public PureImmutableCharacterBuilder withStatValues(final Collection<StatValue> statValues)
		{
			this.statValues = statValues;
			return this;
		}

		public PureImmutableCharacterBuilder withStats(final Collection<AttributeValue> stats)
		{
			this.stats = stats;
			return this;
		}

		public PureImmutableCharacter build()
		{
			return new PureImmutableCharacter(this);
		}

		public static PureImmutableCharacterBuilder character()
		{
			return new PureImmutableCharacterBuilder();
		}
	}
}