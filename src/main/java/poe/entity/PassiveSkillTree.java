package poe.entity;

import java.util.List;

public class PassiveSkillTree
{
	private final List<PassiveSkill> passiveSkills;

	public PassiveSkillTree(final List<PassiveSkill> passiveSkills)
	{
		this.passiveSkills = passiveSkills;
	}

	public List<PassiveSkill> passiveSkills()
	{
		return passiveSkills;
	}
}
