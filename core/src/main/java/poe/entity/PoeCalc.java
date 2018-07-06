package poe.entity;

public class PoeCalc
{
	static int calcMana(final int level, final int intelligence, final float addedMana, final float increasedMana)
	{
		final float baseMana = 40 + (level - 1) * 6 + intelligence / 2;

		return (int)calculateStat(baseMana, addedMana, increasedMana / 100);
	}

	public static int calcLife(final int level, final int strength, final float addedLife, final float increasedLife)
	{
		final int baseLife = 38 + (level) * 12 + strength / 2;

		return (int)((baseLife + addedLife) * (1 + increasedLife / 100));
	}

	public static int calcEvasion(final int level, final int dexterity)
	{
		return ((53 + (level - 1) * 3 + dexterity / 5));
	}

	public static int calcAccuracy(final int level, final int dexterity)
	{
		return (level - 1) * 2 + dexterity * 2;
	}

	public static int calcEnergyShield(final int intelligence, final float addedEnergyShield, final float increasedEnergyShield)
	{
		final float baseEnergyShield = intelligence / 5f;
		return (int)calculateStat(baseEnergyShield, addedEnergyShield, increasedEnergyShield / 100);
	}

	public static int calcMeleePhysicalDamage(final int strength)
	{
		return strength / 5;
	}

	/**
	 * Common standard calculation
	 */
	public static float calculateStat(final float base, final float added, final float increased)
	{
		return (base + added) * (1 + increased);
	}
}
