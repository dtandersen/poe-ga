package poe.command;

public enum CharacterClass
{
	MARAUDER(32, 14, 14),
	RANGER(14, 32, 14),
	WITCH(14, 14, 32),
	DUELIST(23, 23, 14),
	TEMPLAR(23, 14, 23),
	SHADOW(14, 23, 23),
	SCION(20, 20, 20);

	private final int strength;

	private final int dexterity;

	private final int intelligence;

	CharacterClass(final int strength, final int dexterity, final int intelligence)
	{
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
}
