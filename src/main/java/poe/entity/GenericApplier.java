package poe.entity;

public class GenericApplier implements Applier
{
	private final PassiveSkillAttributeType passiveSkillAttributeType;

	public GenericApplier(final PassiveSkillAttributeType passiveSkillAttributeType)
	{
		this.passiveSkillAttributeType = passiveSkillAttributeType;
	}

	@Override
	public void apply(final CharacterSheet characterSheet)
	{
	}
}
