package poe.entity;

public enum CharacterClass
{
	MARAUDER(1, 32, 14, 14),
	RANGER(2, 14, 32, 14),
	WITCH(3, 14, 14, 32),
	DUELIST(4, 23, 23, 14),
	TEMPLAR(5, 23, 14, 23),
	SHADOW(6, 14, 23, 23),
	SCION(0, 20, 20, 20);

	private final int strength;

	private final int dexterity;

	private final int intelligence;

	private final int id;

	CharacterClass(final int id, final int strength, final int dexterity, final int intelligence)
	{
		this.id = id;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;

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
}
