package poe.command;

import poe.repository.PassiveSkillRepository;

public class CommandFactory
{
	private final PassiveSkillRepository repo;

	public CommandFactory(final PassiveSkillRepository repo)
	{
		this.repo = repo;
	}

	public ListSkills list()
	{
		return new ListSkills(repo);
	}
}
