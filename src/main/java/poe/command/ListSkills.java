package poe.command;

import java.util.List;
import poe.command.ListSkills.ListSkillsResult;
import poe.entity.PassiveSkill;
import poe.repo.SkillRepo;

public class ListSkills extends BaseCommand<VoidRequest, ListSkillsResult>
{
	private final SkillRepo repo;

	public ListSkills(final SkillRepo repo)
	{
		this.repo = repo;
	}

	@Override
	public void execute()
	{
		result.skills(repo.all());
	}

	public static interface ListSkillsResult
	{
		void skills(List<PassiveSkill> passiveSkills);
	}
}
