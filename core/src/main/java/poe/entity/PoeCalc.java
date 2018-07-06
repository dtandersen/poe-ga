package poe.entity;

public class PoeCalc
{
	static int calcMana(final int level, final int intelligence, final float maximumMana, final float increasedMana)
	{
		final float baseMana = 40 + (level - 1) * 6 + intelligence / 2;

		return (int)((baseMana + maximumMana) * (1 + increasedMana / 100));
	}

	public static int calcLife(final int level, final int strength, final float maximumLife, final float increasedMaximumLife)
	{
		final int baseLife = 38 + (level) * 12 + strength / 2;

		return (int)((baseLife + maximumLife) * (1 + increasedMaximumLife / 100));
	}

	public static int calcEvasion(final int level, final int dexterity)
	{
		return ((53 + (level - 1) * 3 + dexterity / 5));
	}

	public static int calcAccuracy(final int level, final int dexterity)
	{
		return (level - 1) * 2 + dexterity * 2;
	}

	public static int calcEnergyShield(final int intelligence)
	{
		return (int)(intelligence / 5f);
	}

	public static int calcMeleePhysicalDamage(final int strength)
	{
		return strength / 5;
	}
}
