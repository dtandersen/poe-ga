package poe.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import poe.command.MarkdownStream;
import poe.command.MarkdownStream.Row;
import poe.entity.CharacterInstance.PoeCharacterEditor;
import poe.entity.CharacterItem.CharacterItemBuilder;
import poe.entity.PassiveSkill.PassiveSkillBuilder;
import poe.entity.StatValue.StatBuilder;
import us.davidandersen.poe.test.ComposeBuilder;

public class CharacterInstanceTest
{
	@Test
	public void checkWitchLevel1Attributes()
	{
		final CharacterInstance editor = new PoeCharacterEditor()
				.withCharacterClass(CharacterClass.WITCH)
				.withLevel(1)
				.build();

		assertThat(editor, matches("" +
				"dexterity | intelligence | strength | life | mana | evasion | accuracy | energy shield | melee physical damage",
				"14        | 32           | 14       | 57   | 56   | 55      | 28       | 6             | 2"));
	}

	@Test
	public void checkMarauderLevel100Attributes()
	{
		final CharacterInstance editor = new PoeCharacterEditor()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withLevel(100)
				.build();

		assertThat(editor, matches("" +
				"dexterity | intelligence | strength | life | mana | evasion | accuracy | energy shield | melee physical damage",
				"14        | 14           | 32       | 1254 | 641  | 352     | 226      | 2             | 6"));
	}

	@Test
	public void increasedAttributes()
	{
		final PassiveSkill superman = PassiveSkillBuilder.passiveSkill()
				.withName("Superman")
				.withStats(
						StatBuilder.stat(Stat.STRENGTH, 10),
						StatBuilder.stat(Stat.DEXTERITY, 10),
						StatBuilder.stat(Stat.INTELLIGENCE, 10),
						StatBuilder.stat(Stat.STR_INT, 5),
						StatBuilder.stat(Stat.DEX_INT, 10),
						StatBuilder.stat(Stat.STR_DEX, 15),
						StatBuilder.stat(Stat.ALL_ATTRIBUTES, 15))
				.build();
		final CharacterInstance editor = new PoeCharacterEditor()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withLevel(1)
				.withPassiveSkills(superman)
				.build();

		assertThat(editor, matches("" +
				"dexterity | intelligence | strength | life | mana | evasion | accuracy | energy shield | melee physical damage",
				"64        | 54           | 77       | 88   | 67   | 65      | 128      | 10            | 15"));
	}

	@Test
	public void lifeCalculation()
	{
		final PassiveSkill extraLife = PassiveSkillBuilder.passiveSkill()
				.withName("Lots of Life")
				.withStats(
						StatBuilder.stat(Stat.INCREASED_LIFE, 10),
						StatBuilder.stat(Stat.ADDED_LIFE, 10))
				.build();
		final CharacterInstance editor = new PoeCharacterEditor()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withLevel(1)
				.withPassiveSkills(extraLife)
				.build();

		assertThat(editor.getLife(), equalTo(83));
	}

	@Test
	public void manaCalculation()
	{
		final PassiveSkill lotsOfMana = PassiveSkillBuilder.passiveSkill()
				.withName("Lots of Mana")
				.withStats(
						StatBuilder.stat(Stat.ADDED_MANA, 10),
						StatBuilder.stat(Stat.INCREASED_MANA, 10))
				.build();

		final CharacterInstance editor = new PoeCharacterEditor()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withLevel(1)
				.withPassiveSkills(lotsOfMana)
				.build();

		assertThat(editor.getMana(), equalTo(62));
	}

	@Test
	public void energyShieldCalculation()
	{
		final PassiveSkill energyShielded = PassiveSkillBuilder.passiveSkill()
				.withName("Lots of Mana")
				.withStats(
						StatBuilder.stat(Stat.ADDED_ENERGY_SHIELD, 10),
						StatBuilder.stat(Stat.INCREASED_ENERGY_SHIELD, 10))
				.build();

		final CharacterInstance editor = new PoeCharacterEditor()
				.withCharacterClass(CharacterClass.WITCH)
				.withLevel(1)
				.withPassiveSkills(energyShielded)
				.build();

		assertThat(editor.getEnergyShield(), equalTo(18));
	}

	@Test
	public void shouldCombineStatsOfPassiveSkillsAndItems()
	{
		final PassiveSkill energyShielded = PassiveSkillBuilder.passiveSkill()
				.withName("Lots of Mana")
				.withStats(
						StatBuilder.stat(Stat.ADDED_ENERGY_SHIELD, 10))
				.build();

		final CharacterInstance character = new PoeCharacterEditor()
				.withCharacterClass(CharacterClass.WITCH)
				.withLevel(1)
				.withPassiveSkills(energyShielded)
				.withItems(CharacterItemBuilder.item()
						.withStatValue(Stat.ADDED_ENERGY_SHIELD, 10))
				.build();

		assertThat(character.getEnergyShield(), equalTo(26));
		assertThat(character.getStatValue(Stat.ADDED_ENERGY_SHIELD), equalTo(20f));
	}

	private Matcher<CharacterInstance> matches(final String... markdown)
	{
		final Row row = MarkdownStream.stream(markdown).findFirst().get();
		return ComposeBuilder.of(CharacterInstance.class)
				.withDescription("a character with")
				.withFeature("dexterity", CharacterInstance::getDexterity, row.intValue("dexterity"))
				.withFeature("intelligence", CharacterInstance::getIntelligence, row.intValue("intelligence"))
				.withFeature("strength", CharacterInstance::getStrength, row.intValue("strength"))
				.withFeature("life", CharacterInstance::getLife, row.intValue("life"))
				.withFeature("mana", CharacterInstance::getMana, row.intValue("mana"))
				.withFeature("evasion", CharacterInstance::getEvasion, row.intValue("evasion"))
				.withFeature("accuracy", CharacterInstance::getAccuracy, row.intValue("accuracy"))
				.withFeature("energyShield", CharacterInstance::getEnergyShield, row.intValue("energy shield"))
				.withFeature("meleePhysicalDamage", CharacterInstance::getMeleePhysicalDamage, row.intValue("melee physical damage"))
				.build();
	}
}
