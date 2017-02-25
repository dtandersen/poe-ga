package poe.entity;

import java.util.List;

public class PoeCharacter
{
	private final PassiveSkillGraph skillGraph;

	public PoeCharacter()
	{
		this.skillGraph = new PassiveSkillGraph();
	}

	public PassiveSkillGraph getSkillGraph()
	{
		return skillGraph;
	}

	public int passiveSkillCount()
	{
		return skillGraph.size();
	}

	public boolean hasPassiveSkill(final PassiveSkill passiveSkill)
	{
		return skillGraph.contains(passiveSkill.getId());
	}

	public void addSkill(final PassiveSkill passiveSkill)
	{
		skillGraph.add(passiveSkill.getId());
	}

	public void addSkill(final PassiveSkill passiveSkill1, final PassiveSkill passiveSkill2)
	{
		skillGraph.add(passiveSkill2.getId(), passiveSkill1.getId());
	}

	public List<Integer> getPassiveSkillIds()
	{
		return skillGraph.skills();
	}

	public boolean hasAllPassiveSkills(final List<PassiveSkill> skills)
	{
		return passiveSkillCount() == skills.size();
	}
}
