package poe.command;

import poe.repository.JavaRandomizer;
import poe.repository.PassiveSkillRepository;

public class CommandFactory
{
	private final PassiveSkillRepository passiveSkillRepository;

	public CommandFactory(
			final PassiveSkillRepository passiveSkillRepository)
	{
		this.passiveSkillRepository = passiveSkillRepository;
	}

	public ListSkills list()
	{
		return new ListSkills(passiveSkillRepository);
	}

	public CreateCharacter createCharacter()
	{
		return new CreateCharacter(passiveSkillRepository);
	}

	public RandomBuild randomCharacter()
	{
		return new RandomBuild(passiveSkillRepository, new JavaRandomizer());
	}
}
