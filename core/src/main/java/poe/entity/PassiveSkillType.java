package poe.entity;

public enum PassiveSkillType
{
	STARTING_POINT(5);

	private final int type;

	PassiveSkillType(final int type)
	{
		this.type = type;
	}

	public int getType()
	{
		return type;
	}
}
