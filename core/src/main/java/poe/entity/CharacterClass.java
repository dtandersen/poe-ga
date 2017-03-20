package poe.entity;

import java.util.Objects;

public enum CharacterClass
{
	MARAUDER(1, 32, 14, 14, "MARAUDER"),
	RANGER(2, 14, 32, 14, "RANGER"),
	WITCH(3, 14, 14, 32, "WITCH"),
	DUELIST(4, 23, 23, 14, "DUELIST"),
	TEMPLAR(5, 23, 14, 23, "TEMPLAR"),
	SHADOW(6, 14, 23, 23, "SIX"),
	SCION(0, 20, 20, 20, "Seven");

	private final int strength;

	private final int dexterity;

	private final int intelligence;

	private final int id;

	private final String rootPassiveSkillName;

	CharacterClass(final int id, final int strength, final int dexterity, final int intelligence, final String rootPassiveSkillName)
	{
		this.id = id;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.rootPassiveSkillName = rootPassiveSkillName;

	}

	public int getStrength()
	{
		return strength;
	}

	public int getIntelligence()
	{
		return intelligence;
	}

	public int getDexterity()
	{
		return dexterity;
	}

	public int getId()
	{
		return id;
	}

	public String getRootPassiveSkillName()
	{
		return rootPassiveSkillName;
	}

	public static boolean isRootSkill(final String name)
	{
		for (final CharacterClass c : values())
		{
			if (Objects.equals(c.getRootPassiveSkillName(), name)) { return true; }
		}

		return false;
	}

	public static CharacterClass byId(final int classId)
	{
		for (final CharacterClass c : values())
		{
			if (c.getId() == classId) { return c; }
		}

		return null;
	}

	public static CharacterClass find(final String characterClass)
	{
		for (final CharacterClass klazz : values())
		{
			if (klazz.name().equalsIgnoreCase(characterClass)) { return klazz; }
		}

		return null;
	}
}
