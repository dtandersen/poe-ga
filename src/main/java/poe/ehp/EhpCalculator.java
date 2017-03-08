package poe.ehp;

import poe.entity.PoeCharacter;
import poe.entity.Stat;
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
		return getEffectiveHitPoints() / (1 - Math.min(subject.getLightningResist() + subject.getElementalResist(), 75) / 100);
	}

	public float getMaxFireHit()
	{
		return getEffectiveHitPoints() / (1 - Math.min(subject.getFireResist() + subject.getElementalResist(), MAX_ELEMENTAL_RESIST) / 100);
	}

	public float getMaxColdHit()
	{
		return getEffectiveHitPoints() / (1 - Math.min(subject.getColdResist() + subject.getElementalResist(), MAX_ELEMENTAL_RESIST) / 100);
	}

	public float getMaxChaosHit()
	{
		return subject.getLife() / (1 - Math.min(subject.getChaosResist(), MAX_ELEMENTAL_RESIST) / 100);
	}

	private float getEffectiveHitPoints()
	{
		return subject.getLife() + subject.getEnergyShield();
	}

	private float physicalDamageTaken(final float rawDamage)
	{
		final float physRed = subject.getPhysicalDamageReduction() / 100;
		final float armourRed = armourDamageReduction(rawDamage);
		final float totalRed = (float)Math.min(physRed + armourRed, MAX_PHYSICAL_DAMAGE_REDUCTION);

		return EhpUtils.damageTaken(rawDamage, totalRed);
	}

	private float armourDamageReduction(final float rawDamage)
	{
		final float armour = subject.getArmour();

		if (armour == 0) { return 0; }

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

		public EhpCalculatorBuilder from(final PoeCharacter character)
		{
			withChaosResist(character.getStat(Stat.CHAOS_RESIST));
			withColdResist(character.getStat(Stat.COLD_RESIST));
			withFireResist(character.getStat(Stat.FIRE_RESIST));
			withLightningResist(character.getStat(Stat.LIGHTNING_RESIST));
			withPhysicalDamageReduction(0);
			withElementalResist(character.getStat(Stat.ELEMENTAL_RESIST));
			withLife(0 +
					character.getStat(Stat.MAXIMUM_LIFE) * (1 + character.getStat(Stat.INCRESED_MAXIMUM_LIFE) + character.getStat(Stat.STRENGTH) / 10 * 5) +
					character.getStat(Stat.MAX_ENERGY_SHIELD) * (1 + character.getStat(Stat.INCREASED_ENERGY_SHIELD) + character.getStat(Stat.INTELLIGENCE) / 10 * 2));

			return this;
		}

		public EhpCalculator build()
		{
			return new EhpCalculator(this);
		}
	}
}
