package poe.app;

import poe.command.ListSkills;
import poe.repo.SkillRepo;

public class CommandFactory
{
	private final SkillRepo repo;

	public CommandFactory(final SkillRepo repo)
	{
		this.repo = repo;
	}

	public ListSkills list()
	{
		return new ListSkills(repo);
	}
}
