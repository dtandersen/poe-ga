package poe.entity;

public class PoeCalc
{
	static int calcMana(final int level, final int intelligence)
	{
		return 40 + (level - 1) * 6 + intelligence / 2;
	}

	public static int calcLife(final int level, final int strength)
	{
		return 38 + (level) * 12 + strength / 2;
	}
}
