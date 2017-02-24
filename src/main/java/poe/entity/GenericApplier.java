package poe.entity;

public class GenericApplier implements Applier
{
	private final Stat passiveSkillAttributeType;

	public GenericApplier(final Stat passiveSkillAttributeType)
	{
		this.passiveSkillAttributeType = passiveSkillAttributeType;
	}

	@Override
	public void apply(final CharacterSheet characterSheet)
	{
	}
}
