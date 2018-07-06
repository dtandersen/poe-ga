package poe.evaluator;

import poe.entity.CharacterInstance;
import poe.entity.CharacterInstance.PoeCharacterEditor;
import poe.entity.Stat;

public class CharacterEvaluatorContextAdapter implements CharacterEvaluatorContext
{
	private final PoeCharacterEditor characterSheet;

	private final EhpCalculator ehpCalculator;

	private final CharacterInstance characterInstance;

	public CharacterEvaluatorContextAdapter(final PoeCharacterEditor character, final CharacterInstance charInstance)
	{
		this.characterSheet = character;
		characterInstance = character.build();
		ehpCalculator = new EhpCalculator(new CharacterEhpCalculatorSubjectAdapter(character, charInstance));
	}

	@Override
	public EhpCalculator getEhp()
	{
		return ehpCalculator;
	}

	@Override
	public int getPassiveSkillCount()
	{
		return characterSheet.passiveSkillCount() - 1;
	}

	@Override
	public float getMaximumLife()
	{
		return characterSheet.getAdjustedStat(Stat.MAXIMUM_LIFE);
	}

	@Override
	public float getIncreasedMaximumLife()
	{
		return characterSheet.getAdjustedStat(Stat.INCRESED_MAXIMUM_LIFE);
	}

	@Override
	public float getLife1()
	{
		return characterSheet.getAdjustedStat(Stat.CHAOS_INNOCULATION);
	}

	@Override
	public float getLifeOnKill()
	{
		return characterSheet.getAdjustedStat(Stat.LIFE_ON_KILL);
	}

	@Override
	public float getLifeOnHit()
	{
		return characterSheet.getAdjustedStat(Stat.LIFE_ON_HIT);
	}

	@Override
	public float getFlaskLife()
	{
		return characterSheet.getAdjustedStat(Stat.FLASK_LIFE);
	}

	@Override
	public float getFlaskRecovery()
	{
		return characterSheet.getAdjustedStat(Stat.FLASK_RECOVERY);
	}

	@Override
	public float getFlaskExtra()
	{
		return characterSheet.getAdjustedStat(Stat.FLASK_EXTRA);
	}

	@Override
	public float getFlaskDmg()
	{
		return characterSheet.getAdjustedStat(Stat.FLASK_DMG);
	}

	@Override
	public float getFlaskDuration()
	{
		return characterSheet.getAdjustedStat(Stat.FLASK_DURATION);
	}

	@Override
	public float getFlaskIncreasedEffect()
	{
		return characterSheet.getAdjustedStat(Stat.FLASK_INCREASED_EFFECT);
	}

	@Override
	public float getStrength()
	{
		return characterSheet.getAdjustedStat(Stat.STRENGTH);
	}

	@Override
	public float getDexterity()
	{
		return characterSheet.getAdjustedStat(Stat.DEXTERITY);
	}

	@Override
	public float getIntelligence()
	{
		return characterSheet.getAdjustedStat(Stat.INTELLIGENCE);
	}

	@Override
	public float getMaxMana()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_BONUS);
	}

	@Override
	public float getIncreasedMaxMana()
	{
		return characterSheet.getAdjustedStat(Stat.MANA);
	}

	@Override
	public float getReducedManaCost()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_COST_REDUCED);
	}

	@Override
	public float getManaRegenerationRate()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_REGEN);
	}

	@Override
	public float getManaReserved()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_RESERVED);
	}

	@Override
	public float getManaMoveRegen()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_MOVE_REGEN);
	}

	@Override
	public float getManaReserveReduce()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_RESERVE_REDUCE);
	}

	@Override
	public float getManaFlask()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_FLASK);
	}

	@Override
	public float getManaLeechPhysical()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_LEECH_PHYSICAL);
	}

	@Override
	public float getManaOnKill()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_ON_KILL);
	}

	@Override
	public float getManaOnHit()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_ON_HIT);
	}

	@Override
	public float getManaToLife()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_TO_LIFE);
	}

	@Override
	public float getManaGainOnHit()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_GAIN_ON_HIT);
	}

	@Override
	public float getCsm()
	{
		return characterSheet.getAdjustedStat(Stat.CSM);
	}

	@Override
	public float getCsmNever()
	{
		return characterSheet.getAdjustedStat(Stat.CSM_NEVER);
	}

	@Override
	public float getFlaskGain()
	{
		return characterSheet.getAdjustedStat(Stat.FLASK_GAIN);
	}

	@Override
	public float getAvoidFreeze()
	{
		return characterSheet.getAdjustedStat(Stat.AVOID_FREEZE);
	}

	@Override
	public float getElemFlask()
	{
		return characterSheet.getAdjustedStat(Stat.ELEM_FLASK);
	}

	@Override
	public float getAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.ATTACK_SPEED);
	}

	@Override
	public float getStunThresh()
	{
		return characterSheet.getAdjustedStat(Stat.STUN_THRESH);
	}

	@Override
	public float getDamageAttack()
	{
		return characterSheet.getAdjustedStat(Stat.DAMAGE_ATTACK);
	}

	@Override
	public float getDamageFrozenShockIgnited()
	{
		return characterSheet.getAdjustedStat(Stat.DAMAGE_FROZEN_SHOCK_IGNITED);
	}

	@Override
	public float getDamageOnEnemyLowLife()
	{
		return characterSheet.getAdjustedStat(Stat.DAMAGE_ON_ENEMY_LOW_LIFE);
	}

	@Override
	public float getPhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.PHYSICAL_DAMAGE);
	}

	@Override
	public float getPhysicalDamage2()
	{
		return characterSheet.getAdjustedStat(Stat.PHYSICAL_DAMAGE_2);
	}

	@Override
	public float getPhysicalDot()
	{
		return characterSheet.getAdjustedStat(Stat.PHYSICAL_DOT);
	}

	@Override
	public float getChaosDamage()
	{
		return characterSheet.getAdjustedStat(Stat.CHAOS_DAMAGE);
	}

	@Override
	public float getChaosDamageOnPhysical()
	{
		return characterSheet.getAdjustedStat(Stat.CHAOS_DAMAGE_ON_PHYSICAL);
	}

	@Override
	public float getElementalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.ELEM_DAMAGE);
	}

	@Override
	public float getElementalStatusAilments()
	{
		return characterSheet.getAdjustedStat(Stat.ELEMENTAL_STATUS_AILMENTS);
	}

	@Override
	public float getElementalDamageOnCrit()
	{
		return characterSheet.getAdjustedStat(Stat.ELEMENTAL_DAMAGE_ON_CRIT);
	}

	@Override
	public float getCscElemStatus()
	{
		return characterSheet.getAdjustedStat(Stat.CSC_ELEM_STATUS);
	}

	@Override
	public float getFireDamage()
	{
		return characterSheet.getAdjustedStat(Stat.FIRE_DAMAGE);
	}

	@Override
	public float getFireDamageConvert()
	{
		return characterSheet.getAdjustedStat(Stat.FIRE_DAMAGE_CONVERT);
	}

	@Override
	public float getBurnDamage()
	{
		return characterSheet.getAdjustedStat(Stat.BURN_DAMAGE);
	}

	@Override
	public float getPhysicalDamageAsFireDamage()
	{
		return characterSheet.getAdjustedStat(Stat.PHYSICAL_DAMAGE_AS_FIRE_DAMAGE);
	}

	@Override
	public float getFireDamageOnly()
	{
		return characterSheet.getAdjustedStat(Stat.FIRE_DAMAGE_ONLY);
	}

	@Override
	public float getLightningDamage()
	{
		return characterSheet.getAdjustedStat(Stat.LIGHTNING_DAMAGE);
	}

	@Override
	public float getDotDamage()
	{
		return characterSheet.getAdjustedStat(Stat.DOT_DAMAGE);
	}

	@Override
	public float getSpellDamageOnLowLife()
	{
		return characterSheet.getAdjustedStat(Stat.SPELL_DAMAGE_ON_LOW_LIFE);
	}

	@Override
	public float getSpellDamagePerPowerCharge()
	{
		return characterSheet.getAdjustedStat(Stat.SPELL_DAMAGE_PER_POWER_CHARGE);
	}

	@Override
	public float getTrapDamage()
	{
		return characterSheet.getAdjustedStat(Stat.TRAP_DAMAGE);
	}

	@Override
	public float getTrapRecovery()
	{
		return characterSheet.getAdjustedStat(Stat.TRAP_RECOVERY);
	}

	@Override
	public float getTrapCsm()
	{
		return characterSheet.getAdjustedStat(Stat.TRAP_CSM);
	}

	@Override
	public float getTrapCsc()
	{
		return characterSheet.getAdjustedStat(Stat.TRAP_CSC);
	}

	@Override
	public float getTrapPowerCharge()
	{
		return characterSheet.getAdjustedStat(Stat.TRAP_POWER_CHARGE);
	}

	@Override
	public float getTrapRadius()
	{
		return characterSheet.getAdjustedStat(Stat.TRAP_RADIUS);
	}

	@Override
	public float getTrapSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.TRAP_SPEED);
	}

	@Override
	public float getTrapInvuln()
	{
		return characterSheet.getAdjustedStat(Stat.TRAP_INVULN);
	}

	@Override
	public float getTrapElemPen()
	{
		return characterSheet.getAdjustedStat(Stat.TRAP_ELEM_PEN);
	}

	@Override
	public float getTrapAdditional()
	{
		return characterSheet.getAdjustedStat(Stat.TRAP_ADDITIONAL);
	}

	@Override
	public float getTrapFrenzyCharge()
	{
		return characterSheet.getAdjustedStat(Stat.TRAP_FRENZY_CHARGE);
	}

	@Override
	public float getMineCsm()
	{
		return characterSheet.getAdjustedStat(Stat.MINE_CSM);
	}

	@Override
	public float getMineCsc()
	{
		return characterSheet.getAdjustedStat(Stat.MINE_CSC);
	}

	@Override
	public float getMinePowerCharge()
	{
		return characterSheet.getAdjustedStat(Stat.MINE_POWER_CHARGE);
	}

	@Override
	public float getMineDamage()
	{
		return characterSheet.getAdjustedStat(Stat.MINE_DAMAGE);
	}

	@Override
	public float getMineSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.MINE_SPEED);
	}

	@Override
	public float getMineDuration()
	{
		return characterSheet.getAdjustedStat(Stat.MINE_DURATION);
	}

	@Override
	public float getMineAdditional()
	{
		return characterSheet.getAdjustedStat(Stat.MINE_ADDITIONAL);
	}

	@Override
	public float getMineInstant()
	{
		return characterSheet.getAdjustedStat(Stat.MINE_INSTANT);
	}

	@Override
	public float getMineInvuln()
	{
		return characterSheet.getAdjustedStat(Stat.MINE_INVULN);
	}

	@Override
	public float getMineRadius()
	{
		return characterSheet.getAdjustedStat(Stat.MINE_RADIUS);
	}

	@Override
	public float getMineElemPen()
	{
		return characterSheet.getAdjustedStat(Stat.MINE_ELEM_PEN);
	}

	@Override
	public float getChillOnUnfreeze()
	{
		return characterSheet.getAdjustedStat(Stat.CHILL_ON_UNFREEZE);
	}

	@Override
	public float getFreezeOnChill()
	{
		return characterSheet.getAdjustedStat(Stat.FREEZE_ON_CHILL);
	}

	@Override
	public float getShockDuration()
	{
		return characterSheet.getAdjustedStat(Stat.SHOCK_DURATION);
	}

	@Override
	public float getShockChance()
	{
		return characterSheet.getAdjustedStat(Stat.SHOCK_CHANCE);
	}

	@Override
	public float getSkillDur()
	{
		return characterSheet.getAdjustedStat(Stat.SKILL_EFFECT_DURATION);
	}

	@Override
	public float getTotemDamage()
	{
		return characterSheet.getAdjustedStat(Stat.TOTEM_DAMAGE);
	}

	@Override
	public float getTotemPlacementSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.TOTEM_PLACEMENT_SPEED);
	}

	@Override
	public float getTotemLife()
	{
		return characterSheet.getAdjustedStat(Stat.TOTEM_LIFE);
	}

	@Override
	public float getTotemHelm()
	{
		return characterSheet.getAdjustedStat(Stat.TOTEM_HELM);
	}

	@Override
	public float getTotemRes()
	{
		return characterSheet.getAdjustedStat(Stat.TOTEM_RES);
	}

	@Override
	public float getTotemCharge()
	{
		return characterSheet.getAdjustedStat(Stat.TOTEM_CHARGE);
	}

	@Override
	public float getTotemDuration()
	{
		return characterSheet.getAdjustedStat(Stat.TOTEM_DURATION);
	}

	@Override
	public float getTotemCastSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.TOTEM_CAST_SPEED);
	}

	@Override
	public float getTotemAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.TOTEM_ATTACK_SPEED);
	}

	@Override
	public float getTotemCsc()
	{
		return characterSheet.getAdjustedStat(Stat.TOTEM_CSC);
	}

	@Override
	public float getTotemCsm()
	{
		return characterSheet.getAdjustedStat(Stat.TOTEM_CSM);
	}

	@Override
	public float getTotalGlobalCsc()
	{
		return characterSheet.getAdjustedStat(Stat.TOTAL_GLOBAL_CSC);
	}

	@Override
	public float getProjDamage()
	{
		return characterSheet.getAdjustedStat(Stat.PROJ_DAMAGE);
	}

	@Override
	public float getProjSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.PROJ_SPEED);
	}

	@Override
	public float getProjFar()
	{
		return characterSheet.getAdjustedStat(Stat.PROJ_FAR);
	}

	@Override
	public float getProjSkill()
	{
		return characterSheet.getAdjustedStat(Stat.PROJ_SKILL);
	}

	@Override
	public float getProjPierce()
	{
		return characterSheet.getAdjustedStat(Stat.PROJ_PIERCE);
	}

	@Override
	public float getProjPointBlank()
	{
		return characterSheet.getAdjustedStat(Stat.PROJ_POINT_BLANK);
	}

	@Override
	public float getProjStr()
	{
		return characterSheet.getAdjustedStat(Stat.PROJ_STR);
	}

	@Override
	public float getArrowSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.ARROW_SPEED);
	}

	@Override
	public float getWeaponElementalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.WEAPON_ELEMENTAL_DAMAGE);
	}

	@Override
	public float getWeaponFireDamage()
	{
		return characterSheet.getAdjustedStat(Stat.WEAPON_FIRE_DAMAGE);
	}

	@Override
	public float getWeaponColdDamage()
	{
		return characterSheet.getAdjustedStat(Stat.WEAPON_COLD_DAMAGE);
	}

	@Override
	public float getWeaponLightningDamage()
	{
		return characterSheet.getAdjustedStat(Stat.WEAPON_LIGHTNING_DAMAGE);
	}

	@Override
	public float getWeaponPenCold()
	{
		return characterSheet.getAdjustedStat(Stat.WEAPON_PEN_COLD);
	}

	@Override
	public float getWeaponPenLightning()
	{
		return characterSheet.getAdjustedStat(Stat.WEAPON_PEN_LIGHTNING);
	}

	@Override
	public float getWeaponPenElem()
	{
		return characterSheet.getAdjustedStat(Stat.WEAPON_PEN_ELEM);
	}

	@Override
	public float getMeleeDmg()
	{
		return characterSheet.getAdjustedStat(Stat.MELEE_DMG);
	}

	@Override
	public float getMeleePhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.MELEE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getMeleeAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.MELEE_ATTACK_SPEED);
	}

	@Override
	public float getMeleeCsc()
	{
		return characterSheet.getAdjustedStat(Stat.MELEE_CSC);
	}

	@Override
	public float getMeleeCsm()
	{
		return characterSheet.getAdjustedStat(Stat.MELEE_CSM);
	}

	@Override
	public float getMeleeRange()
	{
		return characterSheet.getAdjustedStat(Stat.MELEE_RANGE);
	}

	@Override
	public float getOneHandedPhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.ONE_HANDED_PHYSICAL_DAMAGE);
	}

	@Override
	public float getOneHandedAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.ONE_HANDED_ATTACK_SPEED);
	}

	@Override
	public float getOneHandedAccuracy()
	{
		return characterSheet.getAdjustedStat(Stat.ONE_HANDED_ACCURACY);
	}

	@Override
	public float getOneHandedCsc()
	{
		return characterSheet.getAdjustedStat(Stat.ONE_HANDED_CSC);
	}

	@Override
	public float getTwoHandedPhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.TWO_HANDED_PHYSICAL_DAMAGE);
	}

	@Override
	public float getTwoHandedAccuracy()
	{
		return characterSheet.getAdjustedStat(Stat.TWO_HANDED_ACCURACY);
	}

	@Override
	public float getTwoHandedAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.TWO_HANDED_ATTACK_SPEED);
	}

	@Override
	public float getTwoHandedStunDuration()
	{
		return characterSheet.getAdjustedStat(Stat.TWO_HANDED_STUN_DURATION);
	}

	@Override
	public float getTwoHandedDamage()
	{
		return characterSheet.getAdjustedStat(Stat.TWO_HANDED_DAMAGE);
	}

	@Override
	public float getStavePhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.STAVE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getStaveAccuracy()
	{
		return characterSheet.getAdjustedStat(Stat.STAVE_ACCURACY);
	}

	@Override
	public float getStaveAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.STAVE_ATTACK_SPEED);
	}

	@Override
	public float getStaveCsc()
	{
		return characterSheet.getAdjustedStat(Stat.STAVE_CSC);
	}

	@Override
	public float getStaveGlobalCsc()
	{
		return characterSheet.getAdjustedStat(Stat.STAVE_GLOBAL_CSC);
	}

	@Override
	public float getStaveGlobalCsm()
	{
		return characterSheet.getAdjustedStat(Stat.STAVE_GLOBAL_CSM);
	}

	@Override
	public float getStaveKnockbackOnCrit()
	{
		return characterSheet.getAdjustedStat(Stat.STAVE_KNOCKBACK_ON_CRIT);
	}

	@Override
	public float getStaveBlock()
	{
		return characterSheet.getAdjustedStat(Stat.STAVE_BLOCK);
	}

	@Override
	public float getWandDamage()
	{
		return characterSheet.getAdjustedStat(Stat.WAND_DAMAGE);
	}

	@Override
	public float getWandPhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.WAND_PHYSICAL_DAMAGE);
	}

	@Override
	public float getWandCsc()
	{
		return characterSheet.getAdjustedStat(Stat.WAND_CSC);
	}

	@Override
	public float getWandCsm()
	{
		return characterSheet.getAdjustedStat(Stat.WAND_CSM);
	}

	@Override
	public float getWandAccuract()
	{
		return characterSheet.getAdjustedStat(Stat.WAND_ACCURACT);
	}

	@Override
	public float getWandAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.WAND_ATTACK_SPEED);
	}

	@Override
	public float getWandElemDamage()
	{
		return characterSheet.getAdjustedStat(Stat.WAND_ELEM_DAMAGE);
	}

	@Override
	public float getWandPhysicalToLightning()
	{
		return characterSheet.getAdjustedStat(Stat.WAND_PHYSICAL_TO_LIGHTNING);
	}

	@Override
	public float getWandPhysicalToCold()
	{
		return characterSheet.getAdjustedStat(Stat.WAND_PHYSICAL_TO_COLD);
	}

	@Override
	public float getWandPhysicalToFire()
	{
		return characterSheet.getAdjustedStat(Stat.WAND_PHYSICAL_TO_FIRE);
	}

	@Override
	public float getWandDamagePerPowerCharge()
	{
		return characterSheet.getAdjustedStat(Stat.WAND_DAMAGE_PER_POWER_CHARGE);
	}

	@Override
	public float getDaggerPhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.DAGGER_PHYSICAL_DAMAGE);
	}

	@Override
	public float getDaggerAccuracy()
	{
		return characterSheet.getAdjustedStat(Stat.DAGGER_ACCURACY);
	}

	@Override
	public float getDaggerAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.DAGGER_ATTACK_SPEED);
	}

	@Override
	public float getDaggerCsc()
	{
		return characterSheet.getAdjustedStat(Stat.DAGGER_CSC);
	}

	@Override
	public float getDaggerPoisonOnCrit()
	{
		return characterSheet.getAdjustedStat(Stat.DAGGER_POISON_ON_CRIT);
	}

	@Override
	public float getDaggerCsm()
	{
		return characterSheet.getAdjustedStat(Stat.DAGGER_CSM);
	}

	@Override
	public float getAxePhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.AXE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getAxeAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.AXE_ATTACK_SPEED);
	}

	@Override
	public float getAxeAccuracy()
	{
		return characterSheet.getAdjustedStat(Stat.AXE_ACCURACY);
	}

	@Override
	public float getSwordPhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.SWORD_PHYSICAL_DAMAGE);
	}

	@Override
	public float getSwordAccuracy()
	{
		return characterSheet.getAdjustedStat(Stat.SWORD_ACCURACY);
	}

	@Override
	public float getSwordAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.SWORD_ATTACK_SPEED);
	}

	@Override
	public float getSwordCsc()
	{
		return characterSheet.getAdjustedStat(Stat.SWORD_CSC);
	}

	@Override
	public float getSwordCsm()
	{
		return characterSheet.getAdjustedStat(Stat.SWORD_CSM);
	}

	@Override
	public float getClawPhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.CLAW_PHYSICAL_DAMAGE);
	}

	@Override
	public float getClawAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.CLAW_ATTACK_SPEED);
	}

	@Override
	public float getClawCsc()
	{
		return characterSheet.getAdjustedStat(Stat.CLAW_CSC);
	}

	@Override
	public float getClawCsm()
	{
		return characterSheet.getAdjustedStat(Stat.CLAW_CSM);
	}

	@Override
	public float getClawAccuracy()
	{
		return characterSheet.getAdjustedStat(Stat.CLAW_ACCURACY);
	}

	@Override
	public float getClawStealCharge()
	{
		return characterSheet.getAdjustedStat(Stat.CLAW_STEAL_CHARGE);
	}

	@Override
	public float getClawLifeLeech()
	{
		return characterSheet.getAdjustedStat(Stat.CLAW_LIFE_LEECH);
	}

	@Override
	public float getClawManaLeech()
	{
		return characterSheet.getAdjustedStat(Stat.CLAW_MANA_LEECH);
	}

	@Override
	public float getMaceAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.MACE_ATTACK_SPEED);
	}

	@Override
	public float getMaceAccuracy()
	{
		return characterSheet.getAdjustedStat(Stat.MACE_ACCURACY);
	}

	@Override
	public float getMaceCsm()
	{
		return characterSheet.getAdjustedStat(Stat.MACE_CSM);
	}

	@Override
	public float getMacePhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.MACE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getMaceStunThreshold()
	{
		return characterSheet.getAdjustedStat(Stat.MACE_STUN_THRESHOLD);
	}

	@Override
	public float getMaceElementalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.MACE_ELEMENTAL_DAMAGE);
	}

	@Override
	public float getMaceCsc()
	{
		return characterSheet.getAdjustedStat(Stat.MACE_CSC);
	}

	@Override
	public float getBowKb()
	{
		return characterSheet.getAdjustedStat(Stat.BOW_KB);
	}

	@Override
	public float getBowCsc()
	{
		return characterSheet.getAdjustedStat(Stat.BOW_CSC);
	}

	@Override
	public float getBowCsm()
	{
		return characterSheet.getAdjustedStat(Stat.BOW_CSM);
	}

	@Override
	public float getBowPhysical()
	{
		return characterSheet.getAdjustedStat(Stat.BOW_PHYSICAL);
	}

	@Override
	public float getBowAccuracy()
	{
		return characterSheet.getAdjustedStat(Stat.BOW_ACCURACY);
	}

	@Override
	public float getBowAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.BOW_ATTACK_SPEED);
	}

	@Override
	public float getBowStunThreshhold()
	{
		return characterSheet.getAdjustedStat(Stat.BOW_STUN_THRESHHOLD);
	}

	@Override
	public float getBowStunDuration()
	{
		return characterSheet.getAdjustedStat(Stat.BOW_STUN_DURATION);
	}

	@Override
	public float getDualPhys()
	{
		return characterSheet.getAdjustedStat(Stat.DUAL_PHYS);
	}

	@Override
	public float getDualCsc()
	{
		return characterSheet.getAdjustedStat(Stat.DUAL_CSC);
	}

	@Override
	public float getDualBlock()
	{
		return characterSheet.getAdjustedStat(Stat.DUAL_BLOCK);
	}

	@Override
	public float getDualAccuracy()
	{
		return characterSheet.getAdjustedStat(Stat.DUAL_ACCURACY);
	}

	@Override
	public float getDualAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.DUAL_ATTACK_SPEED);
	}

	@Override
	public float getDualMainDamage()
	{
		return characterSheet.getAdjustedStat(Stat.DUAL_MAIN_DAMAGE);
	}

	@Override
	public float getDualOffHandDamage()
	{
		return characterSheet.getAdjustedStat(Stat.DUAL_OFF_HAND_DAMAGE);
	}

	@Override
	public float getDualCastSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.DUAL_CAST_SPEED);
	}

	@Override
	public float getShieldAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.SHIELD_ATTACK_SPEED);
	}

	@Override
	public float getShieldPhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.SHIELD_PHYSICAL_DAMAGE);
	}

	@Override
	public float getShieldMeleePhysicalDamage()
	{
		return characterSheet.getAdjustedStat(Stat.SHIELD_MELEE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getAccuracy()
	{
		return characterSheet.getAdjustedStat(Stat.ACCURACY);
	}

	@Override
	public float getAccPlus()
	{
		return characterSheet.getAdjustedStat(Stat.ACC_PLUS);
	}

	@Override
	public float getEvasion()
	{
		return characterSheet.getAdjustedStat(Stat.EVASION);
	}

	@Override
	public float getEvasionArmor()
	{
		return characterSheet.getAdjustedStat(Stat.EVASION_ARMOR);
	}

	@Override
	public float getEvaRat()
	{
		return characterSheet.getAdjustedStat(Stat.EVA_RAT);
	}

	@Override
	public float getEvadeProjectile()
	{
		return characterSheet.getAdjustedStat(Stat.EVADE_PROJECTILE);
	}

	@Override
	public float getEvadeMelee()
	{
		return characterSheet.getAdjustedStat(Stat.EVADE_MELEE);
	}

	@Override
	public float getEvadeNever()
	{
		return characterSheet.getAdjustedStat(Stat.EVADE_NEVER);
	}

	@Override
	public float getArmour()
	{
		return characterSheet.getAdjustedStat(Stat.ARMOUR);
	}

	@Override
	public float getArmorFlat()
	{
		return characterSheet.getAdjustedStat(Stat.ARMOUR_FLAT);
	}

	@Override
	public float getArmorEvade()
	{
		return characterSheet.getAdjustedStat(Stat.ARMOR_EVADE);
	}

	@Override
	public float getMovementSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.MOVEMENT_SPEED);
	}

	@Override
	public float getMovementEnergyShield()
	{
		return characterSheet.getAdjustedStat(Stat.MOVEMENT_ENERGY_SHIELD);
	}

	@Override
	public float getMovementIgnoreArmor()
	{
		return characterSheet.getAdjustedStat(Stat.MOVEMENT_IGNORE_ARMOR);
	}

	@Override
	public float getRegen()
	{
		return characterSheet.getAdjustedStat(Stat.REGEN);
	}

	@Override
	public float getBlockChance()
	{
		return characterSheet.getAdjustedStat(Stat.BLOCK_CHANCE);
	}

	@Override
	public float getShieldDefence()
	{
		return characterSheet.getAdjustedStat(Stat.SHIELD_DEFENCE);
	}

	@Override
	public float getShieldBlockChance()
	{
		return characterSheet.getAdjustedStat(Stat.SHIELD_BLOCK_CHANCE);
	}

	@Override
	public float getShieldElementalResist()
	{
		return characterSheet.getAdjustedStat(Stat.SHIELD_ELEMENTAL_RESIST);
	}

	@Override
	public float getShieldDualBlock()
	{
		return characterSheet.getAdjustedStat(Stat.SHIELD_DUAL_BLOCK);
	}

	@Override
	public float getLightningResist()
	{
		return characterSheet.getAdjustedStat(Stat.LIGHTNING_RESIST);
	}

	@Override
	public float getLightningResistMax()
	{
		return characterSheet.getAdjustedStat(Stat.LIGHTNING_RESIST_MAX);
	}

	@Override
	public float getMaxLight()
	{
		return characterSheet.getAdjustedStat(Stat.MAX_LIGHT);
	}

	@Override
	public float getElementalResist()
	{
		return characterSheet.getAdjustedStat(Stat.ELEMENTAL_RESIST);
	}

	@Override
	public float getFireResist()
	{
		return characterSheet.getAdjustedStat(Stat.FIRE_RESIST);
	}

	@Override
	public float getFireResistMax()
	{
		return characterSheet.getAdjustedStat(Stat.FIRE_RESIST_MAX);
	}

	@Override
	public float getColdResist()
	{
		return characterSheet.getAdjustedStat(Stat.COLD_RESIST);
	}

	@Override
	public float getColdResistCap()
	{
		return characterSheet.getAdjustedStat(Stat.COLD_RESIST_CAP);
	}

	@Override
	public float getChaosResist()
	{
		return characterSheet.getAdjustedStat(Stat.CHAOS_RESIST);
	}

	@Override
	public float getResistAll()
	{
		return characterSheet.getAdjustedStat(Stat.RESIST_ALL);
	}

	@Override
	public float getFreeze()
	{
		return characterSheet.getAdjustedStat(Stat.FREEZE_CHANCE);
	}

	@Override
	public float getCold()
	{
		return characterSheet.getAdjustedStat(Stat.COLD);
	}

	@Override
	public float getColdPen()
	{
		return characterSheet.getAdjustedStat(Stat.COLD_PEN);
	}

	@Override
	public float getPhysicalDamageReduction()
	{
		return characterSheet.getAdjustedStat(Stat.PHYSICAL_DAMAGE_REDUCTION);
	}

	@Override
	public float getElementalDamageReductionOnConsecrate()
	{
		return characterSheet.getAdjustedStat(Stat.ELEMENTAL_DAMAGE_REDUCTION_ON_CONSECRATE);
	}

	@Override
	public float getCriticalReduce()
	{
		return characterSheet.getAdjustedStat(Stat.CRITICAL_REDUCE);
	}

	@Override
	public float getChillAvoid()
	{
		return characterSheet.getAdjustedStat(Stat.CHILL_AVOID);
	}

	@Override
	public float getFrozenAvoid()
	{
		return characterSheet.getAdjustedStat(Stat.FROZEN_AVOID);
	}

	@Override
	public float getIgniteAvoid()
	{
		return characterSheet.getAdjustedStat(Stat.IGNITE_AVOID);
	}

	@Override
	public float getShockAvoid()
	{
		return characterSheet.getAdjustedStat(Stat.SHOCK_AVOID);
	}

	@Override
	public float getAvoidStunOnCast()
	{
		return characterSheet.getAdjustedStat(Stat.AVOID_STUN_ON_CAST);
	}

	@Override
	public float getChillDuration()
	{
		return characterSheet.getAdjustedStat(Stat.CHILL_DURATION);
	}

	@Override
	public float getFreezeDuration()
	{
		return characterSheet.getAdjustedStat(Stat.FREEZE_DURATION);
	}

	@Override
	public float getBlockRecovery()
	{
		return characterSheet.getAdjustedStat(Stat.BLOCK_RECOVERY);
	}

	@Override
	public float getManaOnBlock()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_ON_BLOCK);
	}

	@Override
	public float getCastSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.CAST_SPEED);
	}

	@Override
	public float getCastSpeedChaos()
	{
		return characterSheet.getAdjustedStat(Stat.CAST_SPEED_CHAOS);
	}

	@Override
	public float getSpellDamage()
	{
		return characterSheet.getAdjustedStat(Stat.SPELL_DAMAGE);
	}

	@Override
	public float getSpellCsm()
	{
		return characterSheet.getAdjustedStat(Stat.SPELL_CSM);
	}

	@Override
	public float getSpellCsc()
	{
		return characterSheet.getAdjustedStat(Stat.SPELL_CSC);
	}

	@Override
	public float getPenFire()
	{
		return characterSheet.getAdjustedStat(Stat.PEN_FIRE);
	}

	@Override
	public float getWeapPenFire()
	{
		return characterSheet.getAdjustedStat(Stat.WEAP_PEN_FIRE);
	}

	@Override
	public float getLightPen()
	{
		return characterSheet.getAdjustedStat(Stat.LIGHT_PEN);
	}

	@Override
	public float getElemPen()
	{
		return characterSheet.getAdjustedStat(Stat.ELEM_PEN);
	}

	@Override
	public float getIgDur()
	{
		return characterSheet.getAdjustedStat(Stat.IG_DUR);
	}

	@Override
	public float getIgChance()
	{
		return characterSheet.getAdjustedStat(Stat.IG_CHANCE);
	}

	@Override
	public float getLifeLeech()
	{
		return characterSheet.getAdjustedStat(Stat.LIFE_LEECH);
	}

	@Override
	public float getLifeLeechInstant()
	{
		return characterSheet.getAdjustedStat(Stat.LIFE_LEECH_INSTANT);
	}

	@Override
	public float getLifeLeechPhysical()
	{
		return characterSheet.getAdjustedStat(Stat.LIFE_LEECH_PHYSICAL);
	}

	@Override
	public float getLeechRate()
	{
		return characterSheet.getAdjustedStat(Stat.LEECH_RATE);
	}

	@Override
	public float getLeechPerSec()
	{
		return characterSheet.getAdjustedStat(Stat.LEECH_PER_SEC);
	}

	@Override
	public float getLeechStun()
	{
		return characterSheet.getAdjustedStat(Stat.LEECH_STUN);
	}

	@Override
	public float getLeechDamage()
	{
		return characterSheet.getAdjustedStat(Stat.LEECH_DAMAGE);
	}

	@Override
	public float getPhysLife()
	{
		return characterSheet.getAdjustedStat(Stat.PHYS_LIFE);
	}

	@Override
	public float getImmuneBleed()
	{
		return characterSheet.getAdjustedStat(Stat.IMMUNE_BLEED);
	}

	@Override
	public float getKillWeak()
	{
		return characterSheet.getAdjustedStat(Stat.KILL_WEAK);
	}

	@Override
	public float getOverkill()
	{
		return characterSheet.getAdjustedStat(Stat.OVERKILL);
	}

	@Override
	public float getFillLeech()
	{
		return characterSheet.getAdjustedStat(Stat.FILL_LEECH);
	}

	@Override
	public float getOnslaughtOnRareOrUnique()
	{
		return characterSheet.getAdjustedStat(Stat.ONSLAUGHT_ON_RARE_OR_UNIQUE);
	}

	@Override
	public float getOnslaughtOnKillKill()
	{
		return characterSheet.getAdjustedStat(Stat.ONSLAUGHT_ON_KILL_KILL);
	}

	@Override
	public float getOnslaughtOnFullFrenzy()
	{
		return characterSheet.getAdjustedStat(Stat.ONSLAUGHT_ON_FULL_FRENZY);
	}

	@Override
	public float getEvasionOnOnslaught()
	{
		return characterSheet.getAdjustedStat(Stat.EVASION_ON_ONSLAUGHT);
	}

	@Override
	public float getAoeRadius()
	{
		return characterSheet.getAdjustedStat(Stat.AOE_RADIUS);
	}

	@Override
	public float getAoeDamage()
	{
		return characterSheet.getAdjustedStat(Stat.AREA_DAMAGE);
	}

	@Override
	public float getStunDuration()
	{
		return characterSheet.getAdjustedStat(Stat.STUN_DURATION);
	}

	@Override
	public float getStunOnEnemyFullLife()
	{
		return characterSheet.getAdjustedStat(Stat.STUN_ON_ENEMY_FULL_LIFE);
	}

	@Override
	public float getStunDurationOnEnemyFullLife()
	{
		return characterSheet.getAdjustedStat(Stat.STUN_DURATION_ON_ENEMY_FULL_LIFE);
	}

	@Override
	public float getStunDurationOnEnemyLowLife()
	{
		return characterSheet.getAdjustedStat(Stat.STUN_DURATION_ON_ENEMY_LOW_LIFE);
	}

	@Override
	public float getStunDurationDouble()
	{
		return characterSheet.getAdjustedStat(Stat.STUN_DURATION_DOUBLE);
	}

	@Override
	public float getStunAvoid()
	{
		return characterSheet.getAdjustedStat(Stat.STUN_AVOID);
	}

	@Override
	public float getStunNever()
	{
		return characterSheet.getAdjustedStat(Stat.STUN_NEVER);
	}

	@Override
	public float getStunAndBlockRecovery()
	{
		return characterSheet.getAdjustedStat(Stat.STUN_AND_BLOCK_RECOVERY);
	}

	@Override
	public float getStunThreshhold()
	{
		return characterSheet.getAdjustedStat(Stat.STUN_THRESHHOLD);
	}

	@Override
	public float getReflectReduce()
	{
		return characterSheet.getAdjustedStat(Stat.REFLECT_REDUCE);
	}

	@Override
	public float getRecentKillDamage()
	{
		return characterSheet.getAdjustedStat(Stat.RECENT_KILL_DAMAGE);
	}

	@Override
	public float getSingleSplash()
	{
		return characterSheet.getAdjustedStat(Stat.SINGLE_SPLASH);
	}

	@Override
	public float getSplashLess()
	{
		return characterSheet.getAdjustedStat(Stat.SPLASH_LESS);
	}

	@Override
	public float getBlockSpells()
	{
		return characterSheet.getAdjustedStat(Stat.BLOCK_SPELLS);
	}

	@Override
	public float getBlockSpellShield()
	{
		return characterSheet.getAdjustedStat(Stat.BLOCK_SPELL_SHIELD);
	}

	@Override
	public float getManaLeech()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_LEECH);
	}

	@Override
	public float getAdMana()
	{
		return characterSheet.getAdjustedStat(Stat.AD_MANA);
	}

	@Override
	public float getManaLeechRate()
	{
		return characterSheet.getAdjustedStat(Stat.MANA_LEECH_RATE);
	}

	@Override
	public float getMinionInstability()
	{
		return characterSheet.getAdjustedStat(Stat.MINION_INSTABILITY);
	}

	@Override
	public float getMinionAttackSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.MINION_ATTACK_SPEED);
	}

	@Override
	public float getMinionCastSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.MINION_CAST_SPEED);
	}

	@Override
	public float getMinionShield()
	{
		return characterSheet.getAdjustedStat(Stat.MINION_SHIELD);
	}

	@Override
	public float getMinionBlock()
	{
		return characterSheet.getAdjustedStat(Stat.MINION_BLOCK);
	}

	@Override
	public float getMinionBlockHeal()
	{
		return characterSheet.getAdjustedStat(Stat.MINION_BLOCK_HEAL);
	}

	@Override
	public float getMinionDamage()
	{
		return characterSheet.getAdjustedStat(Stat.MINION_DAMAGE);
	}

	@Override
	public float getMinionLifeLeech()
	{
		return characterSheet.getAdjustedStat(Stat.MINION_LL);
	}

	@Override
	public float getMinionLifeRegeneration()
	{
		return characterSheet.getAdjustedStat(Stat.MINION_REGEN);
	}

	@Override
	public float getMinionElementalResist()
	{
		return characterSheet.getAdjustedStat(Stat.MINION_ELEM_RESIST);
	}

	@Override
	public float getMinionChaosResist()
	{
		return characterSheet.getAdjustedStat(Stat.MINION_CHAOS_RESIST);
	}

	@Override
	public float getIncreasedMinionLife()
	{
		return characterSheet.getAdjustedStat(Stat.MINION_MAXIMUM_LIFE);
	}

	@Override
	public float getZombies()
	{
		return characterSheet.getAdjustedStat(Stat.ZOMBIES);
	}

	@Override
	public float getSkel()
	{
		return characterSheet.getAdjustedStat(Stat.SKEL);
	}

	@Override
	public float getSkelMax()
	{
		return characterSheet.getAdjustedStat(Stat.SKEL_MAX);
	}

	@Override
	public float getSpecMax()
	{
		return characterSheet.getAdjustedStat(Stat.SPEC_MAX);
	}

	@Override
	public int getTotemAdd()
	{
		return (int)characterSheet.getAdjustedStat(Stat.TOTEM_ADD);
	}

	@Override
	public int getTotemCount()
	{
		return 1 + (int)characterSheet.getAdjustedStat(Stat.TOTEM_ADD) + (int)characterSheet.getAdjustedStat(Stat.ANCESTRAL_BOND);
	}

	@Override
	public float getTotemResistElemental()
	{
		return characterSheet.getAdjustedStat(Stat.TOTEM_RESIST_ELEMENTAL);
	}

	@Override
	public float getKnockbackOnHit()
	{
		return characterSheet.getAdjustedStat(Stat.KNOCKBACK_ON_HIT);
	}

	@Override
	public float getKnockbackDistance()
	{
		return characterSheet.getAdjustedStat(Stat.KNOCKBACK_DISTANCE);
	}

	@Override
	public float getBleed()
	{
		return characterSheet.getAdjustedStat(Stat.BLEED);
	}

	@Override
	public float getBleedDamageOnKill()
	{
		return characterSheet.getAdjustedStat(Stat.BLEED_DAMAGE_ON_KILL);
	}

	@Override
	public float getBleedDmg()
	{
		return characterSheet.getAdjustedStat(Stat.BLEED_DMG);
	}

	@Override
	public float getBleedMelee()
	{
		return characterSheet.getAdjustedStat(Stat.BLEED_MELEE);
	}

	@Override
	public float getBleedPoison()
	{
		return characterSheet.getAdjustedStat(Stat.BLEED_POISON);
	}

	@Override
	public float getBleedCsm()
	{
		return characterSheet.getAdjustedStat(Stat.BLEED_CSM);
	}

	@Override
	public float getBleedCsc()
	{
		return characterSheet.getAdjustedStat(Stat.BLEED_CSC);
	}

	@Override
	public float getPoisonDamage()
	{
		return characterSheet.getAdjustedStat(Stat.POISON_DAMAGE);
	}

	@Override
	public float getPoisonOnHit()
	{
		return characterSheet.getAdjustedStat(Stat.POISON_ON_HIT);
	}

	@Override
	public float getMaxEnergyShield()
	{
		return characterSheet.getAdjustedStat(Stat.MAX_ENERGY_SHIELD);
	}

	@Override
	public float getIncreasedEnergyShield()
	{
		return characterSheet.getAdjustedStat(Stat.INCREASED_ENERGY_SHIELD);
	}

	@Override
	public float getEnergyShieldPct2()
	{
		return characterSheet.getAdjustedStat(Stat.ENERGY_SHIELD_PCT_2);
	}

	@Override
	public float getEnergyShieldMana()
	{
		return characterSheet.getAdjustedStat(Stat.ENERGY_SHIELD_MANA);
	}

	@Override
	public float getEnergyShieldMana2()
	{
		return characterSheet.getAdjustedStat(Stat.ENERGY_SHIELD_MANA_2);
	}

	@Override
	public float getEnergyShieldEvade()
	{
		return characterSheet.getAdjustedStat(Stat.ENERGY_SHIELD_EVADE);
	}

	@Override
	public float getEnergyShieldMoreDamage()
	{
		return characterSheet.getAdjustedStat(Stat.ENERGY_SHIELD_MORE_DAMAGE);
	}

	@Override
	public float getEnergyShieldRecharg()
	{
		return characterSheet.getAdjustedStat(Stat.ENERGY_SHIELD_RECHARG);
	}

	@Override
	public float getEnergyShieldShield()
	{
		return characterSheet.getAdjustedStat(Stat.ENERGY_SHIELD_SHIELD);
	}

	@Override
	public float getEnergyShieldLifeRegen()
	{
		return characterSheet.getAdjustedStat(Stat.ENERGY_SHIELD_LIFE_REGEN);
	}

	@Override
	public float getEnergyShieldLifeLeech()
	{
		return characterSheet.getAdjustedStat(Stat.ENERGY_SHIELD_LIFE_LEECH);
	}

	@Override
	public float getEnergyShieldBeforeMana()
	{
		return characterSheet.getAdjustedStat(Stat.ENERGY_SHIELD_BEFORE_MANA);
	}

	@Override
	public float getEnergyShieldProtectMana()
	{
		return characterSheet.getAdjustedStat(Stat.ENERGY_SHIELD_BEFORE_MANA);
	}

	@Override
	public float getChain()
	{
		return characterSheet.getAdjustedStat(Stat.CHAIN);
	}

	@Override
	public float getNoBleed()
	{
		return characterSheet.getAdjustedStat(Stat.NO_BLEED);
	}

	@Override
	public float getCsc()
	{
		return characterSheet.getAdjustedStat(Stat.CSC);
	}

	@Override
	public float getEsFaster()
	{
		return characterSheet.getAdjustedStat(Stat.ES_FASTER);
	}

	@Override
	public float getDodgeAttack()
	{
		return characterSheet.getAdjustedStat(Stat.DODGE_ATTACK);
	}

	@Override
	public float getDodgeAttack2()
	{
		return characterSheet.getAdjustedStat(Stat.DODGE_ATTACK2);
	}

	@Override
	public float getDodgeSpell()
	{
		return characterSheet.getAdjustedStat(Stat.DODGE_SPELL);
	}

	@Override
	public float getDodgeAcrobatics()
	{
		return characterSheet.getAdjustedStat(Stat.DODGE_ACROBATICS);
	}

	@Override
	public float getAuraRad()
	{
		return characterSheet.getAdjustedStat(Stat.AURA_RAD);
	}

	@Override
	public float getNonCurseAuraEffect()
	{
		return characterSheet.getAdjustedStat(Stat.NON_CURSE_AURA_EFFECT);
	}

	@Override
	public float getCurseEffect()
	{
		return characterSheet.getAdjustedStat(Stat.CURSE_EFFECT);
	}

	@Override
	public float getAdditionalCurse()
	{
		return characterSheet.getAdjustedStat(Stat.CURSE_NUM);
	}

	@Override
	public float getCurseDuration()
	{
		return characterSheet.getAdjustedStat(Stat.CURSE_DUR);
	}

	@Override
	public float getCurseCastSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.CURSE_CAST_SPEED);
	}

	@Override
	public float getCurseRadius()
	{
		return characterSheet.getAdjustedStat(Stat.CURSE_RADIUS);
	}

	@Override
	public float getFrenzCharg()
	{
		return characterSheet.getAdjustedStat(Stat.FRENZ_CHARG);
	}

	@Override
	public float getFrChDur()
	{
		return characterSheet.getAdjustedStat(Stat.FR_CH_DUR);
	}

	@Override
	public float getFrenzyEvasion()
	{
		return characterSheet.getAdjustedStat(Stat.FRENZY_EVASION);
	}

	@Override
	public float getFrenzKill()
	{
		return characterSheet.getAdjustedStat(Stat.FRENZ_KILL);
	}

	@Override
	public float getEndDur()
	{
		return characterSheet.getAdjustedStat(Stat.END_DUR);
	}

	@Override
	public float getEnduranceChargeLifeRegen()
	{
		return characterSheet.getAdjustedStat(Stat.ENDURANCE_CHARGE_LIFE_REGEN);
	}

	@Override
	public float getMaxEnd()
	{
		return characterSheet.getAdjustedStat(Stat.MAX_END);
	}

	@Override
	public float getChargeOnKill()
	{
		return characterSheet.getAdjustedStat(Stat.CHARGE_ON_KILL);
	}

	@Override
	public float getPowerChargeDuration()
	{
		return characterSheet.getAdjustedStat(Stat.POWER_CHARGE_DURATION);
	}

	@Override
	public float getPowerChargeAdditional()
	{
		return characterSheet.getAdjustedStat(Stat.POWER_CHARGE_ADDITIONAL);
	}

	@Override
	public float getChargeShare()
	{
		return characterSheet.getAdjustedStat(Stat.CHARGE_SHARE);
	}

	@Override
	public float getEnduranceChargeMax()
	{
		return characterSheet.getAdjustedStat(Stat.ENDURANCE_CHARGE_MAX);
	}

	@Override
	public float getEnduranceChargeOnMeleeCritical()
	{
		return characterSheet.getAdjustedStat(Stat.ENDURANCE_CHARGE_ON_MELEE_CRITICAL);
	}

	@Override
	public float getPowerChargeOnBlock()
	{
		return characterSheet.getAdjustedStat(Stat.POWER_CHARGE_ON_BLOCK);
	}

	@Override
	public float getTrapRed()
	{
		return characterSheet.getAdjustedStat(Stat.TRAP_RED);
	}

	@Override
	public float getMeleeFort()
	{
		return characterSheet.getAdjustedStat(Stat.MELEE_FORT);
	}

	@Override
	public float getIncFort()
	{
		return characterSheet.getAdjustedStat(Stat.INC_FORT);
	}

	@Override
	public float getFortDur()
	{
		return characterSheet.getAdjustedStat(Stat.FORT_DUR);
	}

	@Override
	public float getFortMelee()
	{
		return characterSheet.getAdjustedStat(Stat.FORT_MELEE);
	}

	@Override
	public float getFortifyArmour()
	{
		return characterSheet.getAdjustedStat(Stat.FORTIFY_ARMOUR);
	}

	@Override
	public float getFortAlly()
	{
		return characterSheet.getAdjustedStat(Stat.FORT_ALLY);
	}

	@Override
	public float getTauntHit()
	{
		return characterSheet.getAdjustedStat(Stat.TAUNT_HIT);
	}

	@Override
	public float getTauntReduce()
	{
		return characterSheet.getAdjustedStat(Stat.TAUNT_REDUCE);
	}

	@Override
	public float getLightRadiusEnergyShield()
	{
		return characterSheet.getAdjustedStat(Stat.LIGHT_RADIUS_ENERGY_SHIELD);
	}

	@Override
	public float getLightRadius()
	{
		return characterSheet.getAdjustedStat(Stat.LIGHT_RADIUS);
	}

	@Override
	public float getStunCull()
	{
		return characterSheet.getAdjustedStat(Stat.STUN_CULL);
	}

	@Override
	public float getAttackCastSpeed()
	{
		return characterSheet.getAdjustedStat(Stat.ATTACK_CAST_SPEED);
	}

	@Override
	public float getAtkCast()
	{
		return characterSheet.getAdjustedStat(Stat.ATK_CAST);
	}

	@Override
	public float getAtckCastMove()
	{
		return characterSheet.getAdjustedStat(Stat.ATCK_CAST_MOVE);
	}

	@Override
	public float getLifeManaRegen()
	{
		return characterSheet.getAdjustedStat(Stat.LIFE_MANA_REGEN);
	}

	@Override
	public float getAvoidElem()
	{
		return characterSheet.getAdjustedStat(Stat.AVOID_ELEM);
	}

	@Override
	public float getFreezeShockIgnite()
	{
		return characterSheet.getAdjustedStat(Stat.FREEZE_SHOCK_IGNITE);
	}

	@Override
	public float getNeverEvaded()
	{
		return characterSheet.getAdjustedStat(Stat.NEVER_EVADED);
	}

	@Override
	public float getNeverCrit()
	{
		return characterSheet.getAdjustedStat(Stat.NEVER_CRIT);
	}

	@Override
	public float getEnemyNeverLeech()
	{
		return characterSheet.getAdjustedStat(Stat.ENEMY_NEVER_LEECH);
	}

	@Override
	public float getManaBeforeLife()
	{
		return characterSheet.getAdjustedStat(Stat.MIND_OVER_MATTER);
	}

	@Override
	public float getEnemyElemResist()
	{
		return characterSheet.getAdjustedStat(Stat.ENEMY_ELEM_RESIST);
	}

	@Override
	public double ln(final double x)
	{
		return Math.log(x + 1);
	}

	@Override
	public int hasPassive(final String passiveSkillName)
	{
		return characterSheet.passiveCount(passiveSkillName);
	}

	@Override
	public float min(final float... values)
	{
		float smallest = Float.MAX_VALUE;
		for (final float value : values)
		{
			if (value < smallest)
			{
				smallest = value;
			}
		}
		return smallest;
	}

	@Override
	public float max(final float... values)
	{
		float largest = Float.MIN_VALUE;
		for (final float value : values)
		{
			if (value > largest)
			{
				largest = value;
			}
		}
		return largest;
	}

	@Override
	public int getJewels()
	{
		return (int)characterSheet.getPassiveSkills().stream().filter(skill -> skill.getJewels() > 0).count();
	}

	@Override
	public float rising(final float x)
	{
		return 1 - (1 / (x + 1));
	}

	@Override
	public float getFinalTotemCsc()
	{
		return (5 + getCsc() + getSpellCsc() + getTotemCsc()) / 100f;
	}

	@Override
	public float getFinalTotemCsm()
	{
		return (50 + getCsm() + getSpellCsm() + getTotemCsm()) / 100f;
	}

	@Override
	public float getAreaDamage()
	{
		return characterSheet.getAdjustedStat(Stat.AREA_DAMAGE) / 100f;
	}

	@Override
	public int getLife()
	{
		final int base = 38;
		final float level = characterSheet.getLevel() * 12f;
		final float str = this.getStrength() / 2f;
		return (int)((base + level + str + this.getMaximumLife()) * (1 + this.getIncreasedMaximumLife() / 100f));
	}

	@Override
	public int getEnergyShield()
	{
		final float intel = (this.getIntelligence() / 10f) * 2f;
		return (int)((intel + this.getMaxEnergyShield()) * (1 +
				this.getIncreasedEnergyShield() / 100f));
	}

	@Override
	public float getSkillEffectDuration()
	{
		return characterSheet.getAdjustedStat(Stat.SKILL_EFFECT_DURATION);
	}
}
