package poe.command;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
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

		PassiveSkill curSkill = skillTree.findByName(request.getCharacterClass().getRootPassiveSkillName());
		PassiveSkill prevSkill = curSkill;

		final PoeCharacter character = new PoeCharacter();
		final PassiveSkill root = curSkill;
		character.addPassiveSkill(root);

		do
		{
			if (character.hasAllPassiveSkills(skills))
			{
				break;
			}

			if (!(character.hasPassiveSkill(curSkill) || curSkill.isRootSkill()))
			{
				System.out.println("added " + curSkill.getName());
				if (prevSkill == curSkill)
				{
					final PassiveSkill passiveSkill = curSkill;
					character.addPassiveSkill(passiveSkill);
				}
				else
				{
					final PassiveSkill passiveSkill = curSkill;
					character.addPassiveSkill(passiveSkill);
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
		while ((character.passiveSkillCount()) - 1 < request.getSize());

		result.setCharacter(ImmutableCharacterBuilder.character()
				.withPassiveSkills(character.getPassiveSkillsWithoutRoot())
				.build());

		result.setUrl(new PoeComUrlBuilder()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withPassiveSkillIds(character.getPassiveSkillsWithoutRoot().stream().map(new Function<PassiveSkill, Integer>() {
					@Override
					public Integer apply(final PassiveSkill t)
					{
						return t.getId();
					}
				}).collect(Collectors.toList()))
				.toUrl());
	}

	public interface RandomBuildRequest
	{
		CharacterClass getCharacterClass();

		int getSize();
	}

	public interface RandomBuildResult
	{

		void setCharacter(ImmutableCharacter build);

		void setUrl(String url);
	}
}
