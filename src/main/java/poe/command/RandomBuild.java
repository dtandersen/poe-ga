package poe.command;

import java.util.ArrayList;
import java.util.List;
import poe.command.ImmutableCharacterProxy.ImmutableCharacterBuilder;
import poe.command.RandomBuild.RandomBuildRequest;
import poe.command.RandomBuild.RandomBuildResult;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkillTree;
import poe.repository.PassiveSkillRepository;
import poe.repository.Randomizer;

public class RandomBuild extends BaseCommand<RandomBuildRequest, RandomBuildResult>
{
	private final PassiveSkillRepository passiveSkillRepository;

	private final Randomizer randomizer;

	public RandomBuild(
			final PassiveSkillRepository passiveSkillRepository,
			final Randomizer randomizer)
	{
		this.passiveSkillRepository = passiveSkillRepository;
		this.randomizer = randomizer;
	}

	@Override
	public void execute()
	{
		final List<PassiveSkill> all = passiveSkillRepository.all();
		final PassiveSkillTree skillTree = new PassiveSkillTree(all);
		PassiveSkill passiveSkill = skillTree.findByName("MARAUDER");
		final List<Integer> passives = new ArrayList<>();
		int count = 0;
		do
		{
			if (passives.size() == all.size())
			{
				break;
			}
			if (!passives.contains(passiveSkill.getId()))
			{
				System.out.println("added " + passiveSkill.getName());
				passives.add(passiveSkill.getId());
				count++;
			} else
			{
				System.out.println("already have " + passiveSkill.getName());
			}
			final List<Integer> neighbors = skillTree.neighbors(passiveSkill.getId());
			System.out.println("neighbors of " + passiveSkill.getName() + " are " + neighbors);
			final int nextIndex = randomizer.nextInt(neighbors.size());
			passiveSkill = skillTree.find(neighbors.get(nextIndex));
			System.out.println("rolled " + nextIndex + " and got " + passiveSkill.getName());
		} while (count < request.getSize());
		final ImmutableCharacterBuilder builder = new ImmutableCharacterBuilder();
		builder.withPassives(passives);
		final ImmutableCharacter character = builder
				.build();
		result.setCharacter(character);
	}

	public interface RandomBuildRequest
	{
		int getSize();
	}

	public interface RandomBuildResult
	{

		void setCharacter(ImmutableCharacter build);
	}
}
