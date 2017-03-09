package poe.ehp;

public class SimpleEhpSubject implements EhpSubject
{
	private float life;

	private float armour;

	private float physicalDamageReduction;

	private float lightningResist;

	private float fireResist;

	private float coldResist;

	private float elementalResist;

	private float chaosResist;

	private float energyShield;

	private boolean mindOverMatter;

	private float mana;

	@Override
	public float getLife()
	{
		return life;
	}

	public void setLife(final float life)
	{
		this.life = life;
	}

	@Override
	public float getArmour()
	{
		return armour;
	}

	public void setArmour(final float armour)
	{
		this.armour = armour;
	}

	@Override
	public float getPhysicalDamageReduction()
	{
		return physicalDamageReduction;
	}

	public void setPhysicalDamageReduction(final float physicalDamageReduction)
	{
		this.physicalDamageReduction = physicalDamageReduction;
	}

	@Override
	public float getLightningResist()
	{
		return lightningResist;
	}

	public void setLightningResist(final float lightningResist)
	{
		this.lightningResist = lightningResist;
	}

	@Override
	public float getFireResist()
	{
		return fireResist;
	}

	public void setFireResist(final float fireResist)
	{
		this.fireResist = fireResist;
	}

	@Override
	public float getColdResist()
	{
		return coldResist;
	}

	public void setColdResist(final float coldResist)
	{
		this.coldResist = coldResist;
	}

	@Override
	public float getElementalResist()
	{
		return elementalResist;
	}

	public void setElementalResist(final float elementalResist)
	{
		this.elementalResist = elementalResist;
	}

	@Override
	public float getChaosResist()
	{
		return chaosResist;
	}

	public void setChaosResist(final float chaosResist)
	{
		this.chaosResist = chaosResist;
	}

	@Override
	public float getEnergyShield()
	{
		return energyShield;
	}

	public void setEnergyShield(final float energyShield)
	{
		this.energyShield = energyShield;
	}

	@Override
	public boolean hasMindOverMatter()
	{
		return mindOverMatter;
	}

	public void setMindOverMatter(final boolean mindOverMatter)
	{
		this.mindOverMatter = mindOverMatter;
	}

	@Override
	public float getMana()
	{
		return mana;
	}

	public void setMana(final float mana)
	{
		this.mana = mana;
	}
}
