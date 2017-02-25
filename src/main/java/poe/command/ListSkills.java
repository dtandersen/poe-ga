package poe.command;

import java.util.List;
import poe.command.BaseCommand.VoidRequest;
import poe.command.ListSkills.ListSkillsResult;
import poe.entity.PassiveSkill;
import poe.repository.PassiveSkillRepository;

public class ListSkills extends BaseCommand<VoidRequest, ListSkillsResult>
{
	private final PassiveSkillRepository repo;

	public ListSkills(final PassiveSkillRepository repo)
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
