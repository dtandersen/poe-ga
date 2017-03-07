package poe.command;

import poe.repository.Evolver;
import poe.repository.JavaRandomizer;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;

public class CommandFactory
{
	private final PassiveSkillRepository passiveSkillRepository;

	private final Evolver evolver;

	private final PassiveSkillTree passiveSkillTree;

	public CommandFactory(
			final PassiveSkillRepository passiveSkillRepository,
			final Evolver evolver,
			final PassiveSkillTree passiveSkillTree)
	{
		this.passiveSkillRepository = passiveSkillRepository;
		this.evolver = evolver;
		this.passiveSkillTree = passiveSkillTree;
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

	public EvolveCharacter evolveCharacter()
	{
		return new EvolveCharacter(evolver);
	}
}
