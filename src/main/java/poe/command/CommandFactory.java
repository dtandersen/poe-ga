package poe.command;

import java.util.Random;
import poe.repository.PassiveSkillRepository;
import poe.repository.Randomizer;

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
		return new RandomBuild(passiveSkillRepository, new Randomizer() {
			Random random = new Random();

			@Override
			public int nextInt(final int range)
			{
				return random.nextInt(range);
			}
		});
	}
}
