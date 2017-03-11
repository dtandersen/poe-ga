package poe.command;

import poe.command.model.ImmutableCharacter.ImmutablePassiveSkill;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkill.PassiveSkillBuilder;

public class PureImmutablePassiveSkill implements ImmutablePassiveSkill
{
	private final int passiveSkillId;

	private final String name;

	public PureImmutablePassiveSkill(final ImmutablePassiveSkillBuilder immutablePassiveSkillBuilder)
	{
		this.passiveSkillId = immutablePassiveSkillBuilder.passiveSkillId;
		this.name = immutablePassiveSkillBuilder.name;
	}

	@Override
	public int getPassiveSkillId()
	{
		return passiveSkillId;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "[" +
				"passiveSkillId=" + passiveSkillId +
				", name=" + name + "]";
	}

	public static class ImmutablePassiveSkillBuilder
	{
		private int passiveSkillId;

		private String name;

		public ImmutablePassiveSkillBuilder withPassiveSkillId(final int passiveSkillId)
		{
			this.passiveSkillId = passiveSkillId;
			return this;
		}

		public ImmutablePassiveSkillBuilder withName(final String name)
		{
			this.name = name;
			return this;
		}

		public ImmutablePassiveSkillBuilder from(final PassiveSkill passiveSkill)
		{
			this.passiveSkillId = passiveSkill.getId();
			this.name = passiveSkill.getName();
			return this;
		}

		public ImmutablePassiveSkillBuilder from(final PassiveSkillBuilder builder)
		{
			from(builder.build());
			return this;
		}

		public ImmutablePassiveSkill build()
		{
			return new PureImmutablePassiveSkill(this);
		}

		public static ImmutablePassiveSkillBuilder passiveSkill()
		{
			return new ImmutablePassiveSkillBuilder();
		}
	}
}
