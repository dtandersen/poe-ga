package poe.command;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import poe.command.RandomBuild.RandomBuildRequest;
import poe.command.RandomBuild.RandomBuildResult;
import poe.command.model.ImmutableCharacter;
import poe.command.model.PureImmutableCharacter.ImmutableCharacterBuilder;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PoeCharacter;
import poe.entity.PoeComUrlBuilder;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;
import poe.repository.RandomCharacterGenerator;
import poe.repository.Randomizer;

public class RandomBuild extends BaseCommand<RandomBuildRequest, RandomBuildResult>
{
	private final PassiveSkillRepository passiveSkillRepository;

	public final Randomizer randomizer;

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
		final PoeCharacter character = new RandomCharacterGenerator(skillTree, randomizer)
				.withCharacterClass(request.getCharacterClass())
				.generate(request.getSize());

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
