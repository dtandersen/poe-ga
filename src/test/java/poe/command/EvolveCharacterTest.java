package poe.command;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import poe.command.EvolveCharacter.EvolveCharacterRequest;
import poe.command.EvolveCharacter.EvolveCharacterResult;
import poe.command.PureImmutablePassiveSkill.ImmutablePassiveSkillBuilder;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill.PassiveSkillBuilder;
import poe.entity.PoeCharacter.CharacterBuilder;
import poe.entity.PoeMatchers;
import poe.repository.MockEvolver;
import poe.repository.PassiveSkillRepository;
import poe.repository.RepoBuilder;

public class EvolveCharacterTest
{
	private EvolveCharacterResultImplementation result;

	@Test
	public void test()
	{
		final PassiveSkillBuilder passive1 = PassiveSkillBuilder.passiveSkill()
				.withName(CharacterClass.MARAUDER.getRootPassiveSkillName())
				.withId(1)
				.withOutputs(2)
				.withClassStartingPoint(CharacterClass.MARAUDER);
		final PassiveSkillBuilder passive2 = PassiveSkillBuilder.passiveSkill()
				.withName("Melee Damage")
				.withId(2);
		final PassiveSkillRepository repo = new RepoBuilder()
				.withPassiveSkills(passive1, passive2)
				.build();
		final MockEvolver evolver = new MockEvolver();
		evolver.best = new CharacterBuilder()
				.withCharacterClass(CharacterClass.MARAUDER)
				.build();
		final EvolveCharacter command = new EvolveCharacter(evolver, repo);
		command.setRequest(new EvolveCharacterRequest() {
			@Override
			public CharacterClass getCharacterClass()
			{
				return CharacterClass.MARAUDER;
			}
		});
		result = new EvolveCharacterResultImplementation();
		command.setResult(result);
		command.execute();

		assertThat(evolver.passives, equalTo(repo.all()));
		assertThat(evolver.characterClass, equalTo(CharacterClass.MARAUDER));
		assertThat(result.character, PoeMatchers.hasPassives(
				ImmutablePassiveSkillBuilder.passiveSkill().from(passive2).build()));
	}

	private final class EvolveCharacterResultImplementation implements EvolveCharacterResult
	{
		public ImmutableCharacter character;

		@Override
		public void setCharacter(final ImmutableCharacter character)
		{
			this.character = character;
		}
	}
}
