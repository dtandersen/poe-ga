package poe.command;

import java.util.List;
import poe.command.PureImmutableCharacter.ImmutableCharacterBuilder;
import poe.command.RandomBuild.RandomBuildRequest;
import poe.command.RandomBuild.RandomBuildResult;
import poe.entity.CharacterClass;
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
		final List<PassiveSkill> skills = passiveSkillRepository.all();
		final PassiveSkillTree skillTree = new PassiveSkillTree(skills);

		PassiveSkill curSkill = skillTree.findByName("MARAUDER");
		PassiveSkill prevSkill = curSkill;

		final PoeCharacter character = new PoeCharacter();

		do
		{
			if (character.hasAllPassiveSkills(skills))
			{
				break;
			}

			if (!character.hasPassiveSkill(curSkill))
			{
				System.out.println("added " + curSkill.getName());
				if (prevSkill == curSkill)
				{
					character.addSkill(curSkill);
				}
				else
				{
					character.addSkill(curSkill);
				}
			}
			else
			{
				System.out.println("already have " + curSkill.getName());
			}
			prevSkill = curSkill;
			final List<Integer> neighbors = skillTree.neighbors(curSkill.getId());
			System.out.println("neighbors of " + curSkill.getName() + " are " + neighbors);
			final int nextIndex = randomizer.nextInt(neighbors.size());
			curSkill = skillTree.find(neighbors.get(nextIndex));
			System.out.println("rolled " + nextIndex + " and got " + curSkill.getName());
		}
		while (character.passiveSkillCount() < request.getSize());

		result.setCharacter(ImmutableCharacterBuilder.character()
				.withPassiveSkillIds(character.getPassiveSkillIds())
				.build());

		result.setUrl(new PoeComUrlBuilder()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withPassiveSkillIds(character.getPassiveSkillIds())
				.toUrl());
	}

	public interface RandomBuildRequest
	{
		int getSize();
	}

	public interface RandomBuildResult
	{

		void setCharacter(ImmutableCharacter build);

		void setUrl(String url);
	}
}
