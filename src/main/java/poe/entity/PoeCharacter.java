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
		final int id = passiveSkill.getId();
		addSkill(id);
	}

	public void addSkill(final int id)
	{
		skillGraph.add(id);
	}

	public void addSkill(final PassiveSkill passiveSkill1, final PassiveSkill passiveSkill2)
	{
		final int id = passiveSkill2.getId();
		final int id2 = passiveSkill1.getId();
		addSkill(id, id2);
	}

	public void addSkill(final int id, final int id2)
	{
		skillGraph.add(id, id2);
	}

	public List<Integer> getPassiveSkillIds()
	{
		return skillGraph.skills();
	}

	public boolean hasAllPassiveSkills(final List<PassiveSkill> skills)
	{
		return passiveSkillCount() == skills.size();
	}

	public boolean hasPassiveSkill(final int passiveSkillId)
	{
		return skillGraph.contains(passiveSkillId);
	}
}
