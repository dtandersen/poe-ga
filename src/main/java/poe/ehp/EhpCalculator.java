package poe.ehp;

public class EhpCalculator
{
	private static final int MAX_RESIST = 75;

	private final float life;

	private final float physicalResist;

	private final float lightningResist;

	private final float fireResist;

	private final float coldResist;

	private final float chaosResist;

	private final float elementalResist;

	public EhpCalculator(final EhpCalculatorBuilder ehpCalculatorBuilder)
	{
		this.life = ehpCalculatorBuilder.life;
		this.physicalResist = ehpCalculatorBuilder.physicalResist;
		this.lightningResist = ehpCalculatorBuilder.lightningResist;
		this.fireResist = ehpCalculatorBuilder.fireResist;
		this.coldResist = ehpCalculatorBuilder.coldResist;
		this.chaosResist = ehpCalculatorBuilder.chaosResist;
		this.elementalResist = ehpCalculatorBuilder.elementalResist;
	}

	public float getLargestPhysicalHit()
	{
		return life / (1 - Math.min(physicalResist, MAX_RESIST) / 100);
	}

	public float getLargestLightningHit()
	{
		return life / (1 - Math.min(lightningResist + elementalResist, 75) / 100);
	}

	public float getLargestFireHit()
	{
		return life / (1 - Math.min(fireResist + elementalResist, MAX_RESIST) / 100);
	}

	public float getLargestColdHit()
	{
		return life / (1 - Math.min(coldResist + elementalResist, MAX_RESIST) / 100);
	}

	public float getLargestChaosHit()
	{
		return life / (1 - Math.min(chaosResist, MAX_RESIST) / 100);
	}

	public static class EhpCalculatorBuilder
	{
		private float life;

		private float physicalResist;

		private float lightningResist;

		private float fireResist;

		private float coldResist;

		private float chaosResist;

		private float elementalResist;

		public EhpCalculatorBuilder withLife(final int life)
		{
			this.life = life;
			return this;
		}

		public EhpCalculatorBuilder withPhysicalResist(final float physicalResist)
		{
			this.physicalResist = physicalResist;
			return this;
		}

		public EhpCalculatorBuilder withLightningResist(final float lightningResist)
		{
			this.lightningResist = lightningResist;
			return this;
		}

		public EhpCalculatorBuilder withFireResist(final float fireResist)
		{
			this.fireResist = fireResist;
			return this;
		}

		public EhpCalculatorBuilder withColdResist(final float coldResist)
		{
			this.coldResist = coldResist;
			return this;
		}

		public EhpCalculatorBuilder withChaosResist(final float chaosResist)
		{
			this.chaosResist = chaosResist;
			return this;
		}

		public EhpCalculator build()
		{
			return new EhpCalculator(this);
		}

		public EhpCalculatorBuilder withElementalResist(final float elementalResist)
		{
			this.elementalResist = elementalResist;
			return this;
		}
	}
}
