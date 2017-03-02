package poe.command;

import java.util.Random;
import poe.repository.Evolver;
import poe.repository.PassiveSkillRepository;
import poe.repository.Randomizer;

public class CommandFactory
{
	public static class RandomizerImplementation implements Randomizer
	{
		Random random = new Random();

		@Override
		public int nextInt(final int range)
		{
			return random.nextInt(range);
		}
	}

	private final PassiveSkillRepository passiveSkillRepository;

	private final Evolver evolver;

	public CommandFactory(
			final PassiveSkillRepository passiveSkillRepository,
			final Evolver evolver)
	{
		this.passiveSkillRepository = passiveSkillRepository;
		this.evolver = evolver;
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
		return new RandomBuild(passiveSkillRepository, new RandomizerImplementation());
	}

	public EvolveCharacter evolveCharacter()
	{
		return new EvolveCharacter(evolver, passiveSkillRepository);
	}
}
