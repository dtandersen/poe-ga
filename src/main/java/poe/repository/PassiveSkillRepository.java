package poe.repository;

import java.util.List;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkillTree;

public interface PassiveSkillRepository
{
	List<PassiveSkill> all();

	PassiveSkillTree skillTree();
}
