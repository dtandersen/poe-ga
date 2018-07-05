package poe.ehp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import poe.ehp.DpsCalculator.DpsContext;
import poe.entity.CharacterInstance;
import poe.entity.CharacterInstance.PoeCharacterEditor;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.repository.PassiveSkillTree;
import poe.repository.json.JsonPassiveSkillRepository;

public class DpsCalculatorTest
{
	private PassiveSkillTree passiveSkillTree;

	private DpsCalculator dpsCalc;

	@BeforeEach
	public void skillTree()
	{
		final JsonPassiveSkillRepository jsonSkillRepo = new JsonPassiveSkillRepository();
		passiveSkillTree = new PassiveSkillTree(jsonSkillRepo.all());
	}

	@Test
	public void meleeAttack()
	{
		final CharacterInstance character = given(character()
				.withPassiveSkills(passiveSkills("Melee Damage and Life")));

		dpsCalc = new DpsCalculator();

		assertThat(dpsOf(), equalTo(1f));
	}

	private float dpsOf()
	{
		return dpsCalc.ford(new DpsContext());
	}

	private CharacterInstance given(final PoeCharacterEditor poeCharacter)
	{
		return poeCharacter.build();
	}

	private List<PassiveSkill> passiveSkills(final String... passiveSkillDescriptions)
	{
		final List<PassiveSkill> passiveSkills = new ArrayList<>();

		for (final String passiveSkillDescription : passiveSkillDescriptions)
		{
			passiveSkills.add(passiveSkillTree.findByName(passiveSkillDescription));
		}

		return passiveSkills;
	}

	private PoeCharacterEditor character()
	{
		return new PoeCharacterEditor(CharacterClass.MARAUDER, 10);
	}
}
