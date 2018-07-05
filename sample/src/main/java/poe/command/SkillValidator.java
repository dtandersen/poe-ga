package poe.command;

import poe.command.BaseCommand.VoidRequest;
import poe.command.SkillValidator.SkillValidatorResult;
import poe.repository.PassiveSkillRepository;
import poe.repository.StatRepository;

public class SkillValidator extends BaseCommand<VoidRequest, SkillValidatorResult>
{
	private final PassiveSkillRepository repo;

	private final StatRepository statRepo;

	public SkillValidator(final PassiveSkillRepository repo, final StatRepository statRepo)
	{
		this.repo = repo;
		this.statRepo = statRepo;
	}

	@Override
	public void execute()
	{
	}

	public interface SkillValidatorResult
	{
	}
}
