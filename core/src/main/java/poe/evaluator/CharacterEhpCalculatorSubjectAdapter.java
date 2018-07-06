package poe.evaluator;

import poe.entity.CharacterInstance;
import poe.entity.CharacterInstance.PoeCharacterEditor;
import poe.entity.Stat;

public class CharacterEhpCalculatorSubjectAdapter implements EhpSubject
{
	private final PoeCharacterEditor character;

	private final CharacterInstance charInstance;

	public CharacterEhpCalculatorSubjectAdapter(final PoeCharacterEditor character, final CharacterInstance charInstance)
	{
		this.character = character;
		this.charInstance = charInstance;
	}

	@Override
	public float getLife()
	{
		if (character.getStat(Stat.CHAOS_INNOCULATION) > 0)
		{
			return 1;
		}

		final float stat = character.getStat(Stat.ADDED_LIFE) + 70 * 12 + (character.getStat(Stat.STRENGTH) / 10 * 5);
		final float life = stat * (1 + (character.getStat(Stat.INCREASED_LIFE) / 100f));
		return life;
	}

	@Override
	public float getArmour()
	{
		return character.getStat(Stat.ARMOUR_FLAT) * (1 + character.getStat(Stat.ARMOUR));
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
		return (character.getStat(Stat.ADDED_ENERGY_SHIELD)) *
				(1 * (character.getStat(Stat.INCREASED_ENERGY_SHIELD) + (character.getStat(Stat.INTELLIGENCE) / 10 * 2)));
	}

	@Override
	public float getMana()
	{
		return character.getStat(Stat.ADDED_MANA) * (1 + character.getStat(Stat.INCREASED_MANA));
	}

	@Override
	public boolean hasMindOverMatter()
	{
		return character.getStat(Stat.MIND_OVER_MATTER) > 0;
	}
}
