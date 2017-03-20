package poe.repository;

import java.util.ArrayList;
import java.util.List;
import poe.entity.PassiveSkill.PassiveSkillBuilder;

public class RepoBuilder
{
	private final List<PassiveSkillBuilder> passiveSkills = new ArrayList<>();

	public RepoBuilder withPassiveSkills(final PassiveSkillBuilder... builders)
	{
		for (final PassiveSkillBuilder passiveSkill : builders)
		{
			withPassiveSkill(passiveSkill);
		}

		return this;
	}

	public RepoBuilder withPassiveSkill(final PassiveSkillBuilder passiveSkillBuilder)
	{
		passiveSkills.add(passiveSkillBuilder);
		return this;
	}

	public PassiveSkillRepository build()
	{
		final InMemoryPassiveSkillRepository inMemoryPassiveSkillRepository = new InMemoryPassiveSkillRepository();
		for (final PassiveSkillBuilder passiveSkill : passiveSkills)
		{
			inMemoryPassiveSkillRepository.create(passiveSkill);
		}

		return inMemoryPassiveSkillRepository;
	}
}
