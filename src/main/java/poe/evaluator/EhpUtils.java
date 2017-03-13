package poe.evaluator;

public class EhpUtils
{
	/**
	 * physical damage reduction for armour.
	 */
	static float armourDamageReduction(final float rawDamage, final float armour)
	{
		return armour / (armour + 10 * rawDamage);
	}

	/**
	 * calculate amount of damage taken with given resistance.
	 */
	static float damageTaken(final float rawDamage, final float resistance)
	{
		return rawDamage * (1 - resistance);
	}
}
