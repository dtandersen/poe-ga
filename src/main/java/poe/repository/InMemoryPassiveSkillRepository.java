package poe.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkill.PassiveSkillBuilder;
import poe.entity.PassiveSkillTree;

public class InMemoryPassiveSkillRepository implements PassiveSkillRepository
{
	Map<Integer, PassiveSkill> passiveSkills = new HashMap<>();

	@Override
	public List<PassiveSkill> all()
	{
		return new ArrayList<>(passiveSkills.values());
	}

	@Override
	public PassiveSkillTree skillTree()
	{
		return null;
	}

	public void create(final PassiveSkillBuilder passiveSkillBuilder)
	{
		final PassiveSkill passiveSkill = passiveSkillBuilder.build();
		passiveSkills.put(passiveSkill.getId(), passiveSkill);
	}

	public PassiveSkill findByName(final String name)
	{
		for (final PassiveSkill passiveSkill : passiveSkills.values())
		{
			if (Objects.equals(passiveSkill.getName(), name))
			{
				return passiveSkill;
			}
		}

		return null;
	}
}
