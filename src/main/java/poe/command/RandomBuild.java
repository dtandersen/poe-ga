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
		final PassiveSkill root = passiveSkill;
		final List<Integer> passives = new ArrayList<>();
		passives.add(passiveSkill.getId());
		int i = 0;
		while (i < request.getSize())
		{
			if (passives.size() == all.size())
			{
				break;
			}
			final List<Integer> neighbors = skillTree.neighbors(passiveSkill.getId());
			System.out.println("neighbors of " + passiveSkill.getName() + " are " + neighbors);
			if (neighbors.isEmpty())
			{
				passiveSkill = root;
				continue;
			}
			final int nextIndex = randomizer.nextInt(neighbors.size());
			passiveSkill = skillTree.find(neighbors.get(nextIndex));
			final int nextId = passiveSkill.getId();
			if (passives.contains(nextId))
			{
				// passiveSkill = root;
				continue;
			}
			passives.add(nextId);
			i++;
		}
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
