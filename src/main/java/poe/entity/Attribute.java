package poe.entity;

public enum Attribute
{
	STRENGTH(Stat.STRENGTH),
	DEXTERITY(Stat.DEXTERITY),
	INTELLIGENCE(Stat.INTELLIGENCE),
	LIFE(Stat.MAXIMUM_LIFE),
	MANA(Stat.MANA_BONUS),
	EVASION_RATING(Stat.EVASION_RATING),
	ACCURACY(Stat.ACC_PLUS);

	private final Stat stat;

	Attribute(final Stat stat)
	{
		this.stat = stat;
	}

	public Stat getStat()
	{
		return stat;
	}
}
