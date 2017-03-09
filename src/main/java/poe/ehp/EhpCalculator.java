package poe.ehp;

import poe.solver.BiSectionSolver;

public class EhpCalculator
{
	private static final int MAX_ELEMENTAL_RESIST = 75;

	private static final double MAX_PHYSICAL_DAMAGE_REDUCTION = .9;

	private final EhpSubject subject;

	public EhpCalculator(final EhpCalculatorBuilder ehpCalculatorBuilder)
	{
		this.subject = ehpCalculatorBuilder.subject;
	}

	public EhpCalculator(final EhpSubject subject)
	{
		this.subject = subject;
	}

	public float getMaxPhysicalHit()
	{
		final BiSectionSolver solver = new BiSectionSolver();

		final double rawDmg = solver.solve((final Double maxDmg) -> {
			return (double)getEffectiveHitPoints() - physicalDamageTaken(maxDmg.floatValue());
		}, 0d, (double)getEffectiveHitPoints() * 100, 1d);

		return (float)rawDmg;
	}

	public float getMaxLightningHit()
	{
		return calcDmg(
				getEffectiveHitPoints(),
				(subject.getLightningResist() + subject.getElementalResist()) / 100f,
				MAX_ELEMENTAL_RESIST / 100f);
	}

	private float calcDmg(final double life, final float totalresist, final float capresist)
	{
		final BiSectionSolver solver = new BiSectionSolver();
		final double rawDmg = solver.solve((final Double maxDmg) -> {
			return life -
					maxDmg.floatValue() *
							(1 - Math.min(totalresist, capresist));
		}, 0d, (double)getEffectiveHitPoints() * 100, 1d);
		return (float)rawDmg;
	}

	public float getMaxFireHit()
	{
		return calcDmg(
				getEffectiveHitPoints(),
				(subject.getFireResist() + subject.getElementalResist()) / 100f,
				MAX_ELEMENTAL_RESIST / 100f);
	}

	public float getMaxColdHit()
	{
		return calcDmg(
				getEffectiveHitPoints(),
				(subject.getColdResist() + subject.getElementalResist()) / 100f,
				MAX_ELEMENTAL_RESIST / 100f);
	}

	public float getMaxChaosHit()
	{
		return calcDmg(
				subject.getLife(),
				subject.getChaosResist() / 100f,
				MAX_ELEMENTAL_RESIST / 100f);
	}

	private float getEffectiveHitPoints()
	{
		return subject.getLife() + subject.getEnergyShield();
	}

	private float physicalDamageTaken(float rawDamage)
	{
		if (subject.hasMindOverMatter())
		{
			rawDamage = rawDamage - (float)Math.min(rawDamage * .3, subject.getMana());
		}
		final float physRed = subject.getPhysicalDamageReduction() / 100;
		final float armourRed = armourDamageReduction(rawDamage);
		final float totalRed = (float)Math.min(physRed + armourRed, MAX_PHYSICAL_DAMAGE_REDUCTION);

		return EhpUtils.damageTaken(rawDamage, totalRed);
	}

	private float armourDamageReduction(final float rawDamage)
	{
		final float armour = subject.getArmour();

		if (armour == 0)
		{
			return 0;
		}

		return EhpUtils.armourDamageReduction(rawDamage, armour);
	}

	public static class EhpCalculatorBuilder
	{
		private final SimpleEhpSubject subject = new SimpleEhpSubject();

		public EhpCalculatorBuilder withLife(final float life)
		{
			subject.setLife(life);
			return this;
		}

		public EhpCalculatorBuilder withPhysicalDamageReduction(final float physicalDamageReduction)
		{
			subject.setPhysicalDamageReduction(physicalDamageReduction);
			return this;
		}

		public EhpCalculatorBuilder withLightningResist(final float lightningResist)
		{
			subject.setLightningResist(lightningResist);
			return this;
		}

		public EhpCalculatorBuilder withFireResist(final float fireResist)
		{
			subject.setFireResist(fireResist);
			return this;
		}

		public EhpCalculatorBuilder withColdResist(final float coldResist)
		{
			subject.setColdResist(coldResist);
			return this;
		}

		public EhpCalculatorBuilder withChaosResist(final float chaosResist)
		{
			subject.setChaosResist(chaosResist);
			return this;
		}

		public EhpCalculatorBuilder withElementalResist(final float elementalResist)
		{
			subject.setElementalResist(elementalResist);
			return this;
		}

		public EhpCalculatorBuilder withEnergyShield(final float energyShield)
		{
			subject.setEnergyShield(energyShield);
			return this;
		}

		public EhpCalculatorBuilder withArmour(final float armour)
		{
			subject.setArmour(armour);
			return this;
		}

		public EhpCalculator build()
		{
			return new EhpCalculator(this);
		}

		public EhpCalculatorBuilder withMindOverMatter()
		{
			subject.setMindOverMatter(true);
			return this;
		}

		public EhpCalculatorBuilder withMana(final float mana)
		{
			subject.setMana(mana);
			return this;
		}
	}
}
