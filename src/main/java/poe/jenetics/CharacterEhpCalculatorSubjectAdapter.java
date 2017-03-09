package poe.jenetics;

import poe.ehp.EhpSubject;
import poe.entity.PoeCharacter;
import poe.entity.Stat;

public class CharacterEhpCalculatorSubjectAdapter implements EhpSubject
{
	private final PoeCharacter character;

	public CharacterEhpCalculatorSubjectAdapter(final PoeCharacter character)
	{
		this.character = character;
	}

	@Override
	public float getLife()
	{
		if (character.getStat(Stat.CHAOS_INNOCULATION) > 0)
		{
			return 1;
		}

		return character.getStat(Stat.MAXIMUM_LIFE) * (1 + character.getStat(Stat.INCRESED_MAXIMUM_LIFE) + character.getStat(Stat.STRENGTH) / 10 * 5);
	}

	@Override
	public float getArmour()
	{
		return character.getStat(Stat.ARMOUR_FLAT) + (1 + character.getStat(Stat.ARMOUR));
	}

	@Override
	public float getPhysicalDamageReduction()
	{
		return character.getStat(Stat.PHYSICAL_DAMAGE_REDUCTION);
	}

	@Override
	public float getLightningResist()
	{
		return character.getStat(Stat.LIGHTNING_RESIST);
	}

	@Override
	public float getFireResist()
	{
		return character.getStat(Stat.FIRE_RESIST);
	}

	@Override
	public float getColdResist()
	{
		return character.getStat(Stat.COLD_RESIST);
	}

	@Override
	public float getChaosResist()
	{
		return character.getStat(Stat.CHAOS_RESIST);
	}

	@Override
	public float getElementalResist()
	{
		return character.getStat(Stat.ELEMENTAL_RESIST);
	}

	@Override
	public float getEnergyShield()
	{
		return character.getStat(Stat.MAX_ENERGY_SHIELD) + (1 * character.getStat(Stat.INCREASED_ENERGY_SHIELD));
	}

	@Override
	public float getMana()
	{
		return character.getStat(Stat.MANA_BONUS) + (1 * character.getStat(Stat.MANA));
	}

	@Override
	public boolean hasMindOverMatter()
	{
		return character.getStat(Stat.MIND_OVER_MATTER) > 0;
	}
}
