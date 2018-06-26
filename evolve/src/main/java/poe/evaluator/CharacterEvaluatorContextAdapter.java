package poe.evaluator;

import poe.entity.PoeCharacter;
import poe.entity.Stat;

public class CharacterEvaluatorContextAdapter implements CharacterEvaluatorContext
{
	private final PoeCharacter character;

	private final EhpCalculator ehpCalculator;

	public CharacterEvaluatorContextAdapter(final PoeCharacter character)
	{
		this.character = character;
		ehpCalculator = new EhpCalculator(new CharacterEhpCalculatorSubjectAdapter(character));
	}

	@Override
	public EhpCalculator getEhp()
	{
		return ehpCalculator;
	}

	@Override
	public int getPassiveSkillCount()
	{
		return character.passiveSkillCount() - 1;
	}

	@Override
	public float getMaximumLife()
	{
		return character.getAdjustedStat(Stat.MAXIMUM_LIFE);
	}

	@Override
	public float getIncreasedMaximumLife()
	{
		return character.getAdjustedStat(Stat.INCRESED_MAXIMUM_LIFE);
	}

	@Override
	public float getLife1()
	{
		return character.getAdjustedStat(Stat.CHAOS_INNOCULATION);
	}

	@Override
	public float getLifeOnKill()
	{
		return character.getAdjustedStat(Stat.LIFE_ON_KILL);
	}

	@Override
	public float getLifeOnHit()
	{
		return character.getAdjustedStat(Stat.LIFE_ON_HIT);
	}

	@Override
	public float getFlaskLife()
	{
		return character.getAdjustedStat(Stat.FLASK_LIFE);
	}

	@Override
	public float getFlaskRecovery()
	{
		return character.getAdjustedStat(Stat.FLASK_RECOVERY);
	}

	@Override
	public float getFlaskExtra()
	{
		return character.getAdjustedStat(Stat.FLASK_EXTRA);
	}

	@Override
	public float getFlaskDmg()
	{
		return character.getAdjustedStat(Stat.FLASK_DMG);
	}

	@Override
	public float getFlaskDuration()
	{
		return character.getAdjustedStat(Stat.FLASK_DURATION);
	}

	@Override
	public float getFlaskIncreasedEffect()
	{
		return character.getAdjustedStat(Stat.FLASK_INCREASED_EFFECT);
	}

	@Override
	public float getStrength()
	{
		return character.getAdjustedStat(Stat.STRENGTH);
	}

	@Override
	public float getDexterity()
	{
		return character.getAdjustedStat(Stat.DEXTERITY);
	}

	@Override
	public float getIntelligence()
	{
		return character.getAdjustedStat(Stat.INTELLIGENCE);
	}

	@Override
	public float getMaxMana()
	{
		return character.getAdjustedStat(Stat.MANA_BONUS);
	}

	@Override
	public float getIncreasedMaxMana()
	{
		return character.getAdjustedStat(Stat.MANA);
	}

	@Override
	public float getReducedManaCost()
	{
		return character.getAdjustedStat(Stat.MANA_COST_REDUCED);
	}

	@Override
	public float getManaRegenerationRate()
	{
		return character.getAdjustedStat(Stat.MANA_REGEN);
	}

	@Override
	public float getManaReserved()
	{
		return character.getAdjustedStat(Stat.MANA_RESERVED);
	}

	@Override
	public float getManaMoveRegen()
	{
		return character.getAdjustedStat(Stat.MANA_MOVE_REGEN);
	}

	@Override
	public float getManaReserveReduce()
	{
		return character.getAdjustedStat(Stat.MANA_RESERVE_REDUCE);
	}

	@Override
	public float getManaFlask()
	{
		return character.getAdjustedStat(Stat.MANA_FLASK);
	}

	@Override
	public float getManaLeechPhysical()
	{
		return character.getAdjustedStat(Stat.MANA_LEECH_PHYSICAL);
	}

	@Override
	public float getManaOnKill()
	{
		return character.getAdjustedStat(Stat.MANA_ON_KILL);
	}

	@Override
	public float getManaOnHit()
	{
		return character.getAdjustedStat(Stat.MANA_ON_HIT);
	}

	@Override
	public float getManaToLife()
	{
		return character.getAdjustedStat(Stat.MANA_TO_LIFE);
	}

	@Override
	public float getManaGainOnHit()
	{
		return character.getAdjustedStat(Stat.MANA_GAIN_ON_HIT);
	}

	@Override
	public float getCsm()
	{
		return character.getAdjustedStat(Stat.CSM);
	}

	@Override
	public float getCsmNever()
	{
		return character.getAdjustedStat(Stat.CSM_NEVER);
	}

	@Override
	public float getFlaskGain()
	{
		return character.getAdjustedStat(Stat.FLASK_GAIN);
	}

	@Override
	public float getAvoidFreeze()
	{
		return character.getAdjustedStat(Stat.AVOID_FREEZE);
	}

	@Override
	public float getElemFlask()
	{
		return character.getAdjustedStat(Stat.ELEM_FLASK);
	}

	@Override
	public float getAttackSpeed()
	{
		return character.getAdjustedStat(Stat.ATTACK_SPEED);
	}

	@Override
	public float getStunThresh()
	{
		return character.getAdjustedStat(Stat.STUN_THRESH);
	}

	@Override
	public float getDamageAttack()
	{
		return character.getAdjustedStat(Stat.DAMAGE_ATTACK);
	}

	@Override
	public float getDamageFrozenShockIgnited()
	{
		return character.getAdjustedStat(Stat.DAMAGE_FROZEN_SHOCK_IGNITED);
	}

	@Override
	public float getDamageOnEnemyLowLife()
	{
		return character.getAdjustedStat(Stat.DAMAGE_ON_ENEMY_LOW_LIFE);
	}

	@Override
	public float getPhysicalDamage()
	{
		return character.getAdjustedStat(Stat.PHYSICAL_DAMAGE);
	}

	@Override
	public float getPhysicalDamage2()
	{
		return character.getAdjustedStat(Stat.PHYSICAL_DAMAGE_2);
	}

	@Override
	public float getPhysicalDot()
	{
		return character.getAdjustedStat(Stat.PHYSICAL_DOT);
	}

	@Override
	public float getChaosDamage()
	{
		return character.getAdjustedStat(Stat.CHAOS_DAMAGE);
	}

	@Override
	public float getChaosDamageOnPhysical()
	{
		return character.getAdjustedStat(Stat.CHAOS_DAMAGE_ON_PHYSICAL);
	}

	@Override
	public float getElementalDamage()
	{
		return character.getAdjustedStat(Stat.ELEM_DAMAGE);
	}

	@Override
	public float getElementalStatusAilments()
	{
		return character.getAdjustedStat(Stat.ELEMENTAL_STATUS_AILMENTS);
	}

	@Override
	public float getElementalDamageOnCrit()
	{
		return character.getAdjustedStat(Stat.ELEMENTAL_DAMAGE_ON_CRIT);
	}

	@Override
	public float getCscElemStatus()
	{
		return character.getAdjustedStat(Stat.CSC_ELEM_STATUS);
	}

	@Override
	public float getFireDamage()
	{
		return character.getAdjustedStat(Stat.FIRE_DAMAGE);
	}

	@Override
	public float getFireDamageConvert()
	{
		return character.getAdjustedStat(Stat.FIRE_DAMAGE_CONVERT);
	}

	@Override
	public float getBurnDamage()
	{
		return character.getAdjustedStat(Stat.BURN_DAMAGE);
	}

	@Override
	public float getPhysicalDamageAsFireDamage()
	{
		return character.getAdjustedStat(Stat.PHYSICAL_DAMAGE_AS_FIRE_DAMAGE);
	}

	@Override
	public float getFireDamageOnly()
	{
		return character.getAdjustedStat(Stat.FIRE_DAMAGE_ONLY);
	}

	@Override
	public float getLightningDamage()
	{
		return character.getAdjustedStat(Stat.LIGHTNING_DAMAGE);
	}

	@Override
	public float getDotDamage()
	{
		return character.getAdjustedStat(Stat.DOT_DAMAGE);
	}

	@Override
	public float getSpellDamageOnLowLife()
	{
		return character.getAdjustedStat(Stat.SPELL_DAMAGE_ON_LOW_LIFE);
	}

	@Override
	public float getSpellDamagePerPowerCharge()
	{
		return character.getAdjustedStat(Stat.SPELL_DAMAGE_PER_POWER_CHARGE);
	}

	@Override
	public float getTrapDamage()
	{
		return character.getAdjustedStat(Stat.TRAP_DAMAGE);
	}

	@Override
	public float getTrapRecovery()
	{
		return character.getAdjustedStat(Stat.TRAP_RECOVERY);
	}

	@Override
	public float getTrapCsm()
	{
		return character.getAdjustedStat(Stat.TRAP_CSM);
	}

	@Override
	public float getTrapCsc()
	{
		return character.getAdjustedStat(Stat.TRAP_CSC);
	}

	@Override
	public float getTrapPowerCharge()
	{
		return character.getAdjustedStat(Stat.TRAP_POWER_CHARGE);
	}

	@Override
	public float getTrapRadius()
	{
		return character.getAdjustedStat(Stat.TRAP_RADIUS);
	}

	@Override
	public float getTrapSpeed()
	{
		return character.getAdjustedStat(Stat.TRAP_SPEED);
	}

	@Override
	public float getTrapInvuln()
	{
		return character.getAdjustedStat(Stat.TRAP_INVULN);
	}

	@Override
	public float getTrapElemPen()
	{
		return character.getAdjustedStat(Stat.TRAP_ELEM_PEN);
	}

	@Override
	public float getTrapAdditional()
	{
		return character.getAdjustedStat(Stat.TRAP_ADDITIONAL);
	}

	@Override
	public float getTrapFrenzyCharge()
	{
		return character.getAdjustedStat(Stat.TRAP_FRENZY_CHARGE);
	}

	@Override
	public float getMineCsm()
	{
		return character.getAdjustedStat(Stat.MINE_CSM);
	}

	@Override
	public float getMineCsc()
	{
		return character.getAdjustedStat(Stat.MINE_CSC);
	}

	@Override
	public float getMinePowerCharge()
	{
		return character.getAdjustedStat(Stat.MINE_POWER_CHARGE);
	}

	@Override
	public float getMineDamage()
	{
		return character.getAdjustedStat(Stat.MINE_DAMAGE);
	}

	@Override
	public float getMineSpeed()
	{
		return character.getAdjustedStat(Stat.MINE_SPEED);
	}

	@Override
	public float getMineDuration()
	{
		return character.getAdjustedStat(Stat.MINE_DURATION);
	}

	@Override
	public float getMineAdditional()
	{
		return character.getAdjustedStat(Stat.MINE_ADDITIONAL);
	}

	@Override
	public float getMineInstant()
	{
		return character.getAdjustedStat(Stat.MINE_INSTANT);
	}

	@Override
	public float getMineInvuln()
	{
		return character.getAdjustedStat(Stat.MINE_INVULN);
	}

	@Override
	public float getMineRadius()
	{
		return character.getAdjustedStat(Stat.MINE_RADIUS);
	}

	@Override
	public float getMineElemPen()
	{
		return character.getAdjustedStat(Stat.MINE_ELEM_PEN);
	}

	@Override
	public float getChillOnUnfreeze()
	{
		return character.getAdjustedStat(Stat.CHILL_ON_UNFREEZE);
	}

	@Override
	public float getFreezeOnChill()
	{
		return character.getAdjustedStat(Stat.FREEZE_ON_CHILL);
	}

	@Override
	public float getShockDuration()
	{
		return character.getAdjustedStat(Stat.SHOCK_DURATION);
	}

	@Override
	public float getShockChance()
	{
		return character.getAdjustedStat(Stat.SHOCK_CHANCE);
	}

	@Override
	public float getSkillDur()
	{
		return character.getAdjustedStat(Stat.SKILL_DUR);
	}

	@Override
	public float getTotemDamage()
	{
		return character.getAdjustedStat(Stat.TOTEM_DAMAGE);
	}

	@Override
	public float getTotemPlacementSpeed()
	{
		return character.getAdjustedStat(Stat.TOTEM_PLACEMENT_SPEED);
	}

	@Override
	public float getTotemLife()
	{
		return character.getAdjustedStat(Stat.TOTEM_LIFE);
	}

	@Override
	public float getTotemHelm()
	{
		return character.getAdjustedStat(Stat.TOTEM_HELM);
	}

	@Override
	public float getTotemRes()
	{
		return character.getAdjustedStat(Stat.TOTEM_RES);
	}

	@Override
	public float getTotemCharge()
	{
		return character.getAdjustedStat(Stat.TOTEM_CHARGE);
	}

	@Override
	public float getTotemDuration()
	{
		return character.getAdjustedStat(Stat.TOTEM_DURATION);
	}

	@Override
	public float getTotemCastSpeed()
	{
		return character.getAdjustedStat(Stat.TOTEM_CAST_SPEED);
	}

	@Override
	public float getTotemAttackSpeed()
	{
		return character.getAdjustedStat(Stat.TOTEM_ATTACK_SPEED);
	}

	@Override
	public float getTotemCsc()
	{
		return character.getAdjustedStat(Stat.TOTEM_CSC);
	}

	@Override
	public float getTotemCsm()
	{
		return character.getAdjustedStat(Stat.TOTEM_CSM);
	}

	@Override
	public float getTotalGlobalCsc()
	{
		return character.getAdjustedStat(Stat.TOTAL_GLOBAL_CSC);
	}

	@Override
	public float getProjDamage()
	{
		return character.getAdjustedStat(Stat.PROJ_DAMAGE);
	}

	@Override
	public float getProjSpeed()
	{
		return character.getAdjustedStat(Stat.PROJ_SPEED);
	}

	@Override
	public float getProjFar()
	{
		return character.getAdjustedStat(Stat.PROJ_FAR);
	}

	@Override
	public float getProjSkill()
	{
		return character.getAdjustedStat(Stat.PROJ_SKILL);
	}

	@Override
	public float getProjPierce()
	{
		return character.getAdjustedStat(Stat.PROJ_PIERCE);
	}

	@Override
	public float getProjPointBlank()
	{
		return character.getAdjustedStat(Stat.PROJ_POINT_BLANK);
	}

	@Override
	public float getProjStr()
	{
		return character.getAdjustedStat(Stat.PROJ_STR);
	}

	@Override
	public float getArrowSpeed()
	{
		return character.getAdjustedStat(Stat.ARROW_SPEED);
	}

	@Override
	public float getWeaponElementalDamage()
	{
		return character.getAdjustedStat(Stat.WEAPON_ELEMENTAL_DAMAGE);
	}

	@Override
	public float getWeaponFireDamage()
	{
		return character.getAdjustedStat(Stat.WEAPON_FIRE_DAMAGE);
	}

	@Override
	public float getWeaponColdDamage()
	{
		return character.getAdjustedStat(Stat.WEAPON_COLD_DAMAGE);
	}

	@Override
	public float getWeaponLightningDamage()
	{
		return character.getAdjustedStat(Stat.WEAPON_LIGHTNING_DAMAGE);
	}

	@Override
	public float getWeaponPenCold()
	{
		return character.getAdjustedStat(Stat.WEAPON_PEN_COLD);
	}

	@Override
	public float getWeaponPenLightning()
	{
		return character.getAdjustedStat(Stat.WEAPON_PEN_LIGHTNING);
	}

	@Override
	public float getWeaponPenElem()
	{
		return character.getAdjustedStat(Stat.WEAPON_PEN_ELEM);
	}

	@Override
	public float getMeleeDmg()
	{
		return character.getAdjustedStat(Stat.MELEE_DMG);
	}

	@Override
	public float getMeleePhysicalDamage()
	{
		return character.getAdjustedStat(Stat.MELEE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getMeleeAttackSpeed()
	{
		return character.getAdjustedStat(Stat.MELEE_ATTACK_SPEED);
	}

	@Override
	public float getMeleeCsc()
	{
		return character.getAdjustedStat(Stat.MELEE_CSC);
	}

	@Override
	public float getMeleeCsm()
	{
		return character.getAdjustedStat(Stat.MELEE_CSM);
	}

	@Override
	public float getMeleeRange()
	{
		return character.getAdjustedStat(Stat.MELEE_RANGE);
	}

	@Override
	public float getOneHandedPhysicalDamage()
	{
		return character.getAdjustedStat(Stat.ONE_HANDED_PHYSICAL_DAMAGE);
	}

	@Override
	public float getOneHandedAttackSpeed()
	{
		return character.getAdjustedStat(Stat.ONE_HANDED_ATTACK_SPEED);
	}

	@Override
	public float getOneHandedAccuracy()
	{
		return character.getAdjustedStat(Stat.ONE_HANDED_ACCURACY);
	}

	@Override
	public float getOneHandedCsc()
	{
		return character.getAdjustedStat(Stat.ONE_HANDED_CSC);
	}

	@Override
	public float getTwoHandedPhysicalDamage()
	{
		return character.getAdjustedStat(Stat.TWO_HANDED_PHYSICAL_DAMAGE);
	}

	@Override
	public float getTwoHandedAccuracy()
	{
		return character.getAdjustedStat(Stat.TWO_HANDED_ACCURACY);
	}

	@Override
	public float getTwoHandedAttackSpeed()
	{
		return character.getAdjustedStat(Stat.TWO_HANDED_ATTACK_SPEED);
	}

	@Override
	public float getTwoHandedStunDuration()
	{
		return character.getAdjustedStat(Stat.TWO_HANDED_STUN_DURATION);
	}

	@Override
	public float getTwoHandedDamage()
	{
		return character.getAdjustedStat(Stat.TWO_HANDED_DAMAGE);
	}

	@Override
	public float getStavePhysicalDamage()
	{
		return character.getAdjustedStat(Stat.STAVE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getStaveAccuracy()
	{
		return character.getAdjustedStat(Stat.STAVE_ACCURACY);
	}

	@Override
	public float getStaveAttackSpeed()
	{
		return character.getAdjustedStat(Stat.STAVE_ATTACK_SPEED);
	}

	@Override
	public float getStaveCsc()
	{
		return character.getAdjustedStat(Stat.STAVE_CSC);
	}

	@Override
	public float getStaveGlobalCsc()
	{
		return character.getAdjustedStat(Stat.STAVE_GLOBAL_CSC);
	}

	@Override
	public float getStaveGlobalCsm()
	{
		return character.getAdjustedStat(Stat.STAVE_GLOBAL_CSM);
	}

	@Override
	public float getStaveKnockbackOnCrit()
	{
		return character.getAdjustedStat(Stat.STAVE_KNOCKBACK_ON_CRIT);
	}

	@Override
	public float getStaveBlock()
	{
		return character.getAdjustedStat(Stat.STAVE_BLOCK);
	}

	@Override
	public float getWandDamage()
	{
		return character.getAdjustedStat(Stat.WAND_DAMAGE);
	}

	@Override
	public float getWandPhysicalDamage()
	{
		return character.getAdjustedStat(Stat.WAND_PHYSICAL_DAMAGE);
	}

	@Override
	public float getWandCsc()
	{
		return character.getAdjustedStat(Stat.WAND_CSC);
	}

	@Override
	public float getWandCsm()
	{
		return character.getAdjustedStat(Stat.WAND_CSM);
	}

	@Override
	public float getWandAccuract()
	{
		return character.getAdjustedStat(Stat.WAND_ACCURACT);
	}

	@Override
	public float getWandAttackSpeed()
	{
		return character.getAdjustedStat(Stat.WAND_ATTACK_SPEED);
	}

	@Override
	public float getWandElemDamage()
	{
		return character.getAdjustedStat(Stat.WAND_ELEM_DAMAGE);
	}

	@Override
	public float getWandPhysicalToLightning()
	{
		return character.getAdjustedStat(Stat.WAND_PHYSICAL_TO_LIGHTNING);
	}

	@Override
	public float getWandPhysicalToCold()
	{
		return character.getAdjustedStat(Stat.WAND_PHYSICAL_TO_COLD);
	}

	@Override
	public float getWandPhysicalToFire()
	{
		return character.getAdjustedStat(Stat.WAND_PHYSICAL_TO_FIRE);
	}

	@Override
	public float getWandDamagePerPowerCharge()
	{
		return character.getAdjustedStat(Stat.WAND_DAMAGE_PER_POWER_CHARGE);
	}

	@Override
	public float getDaggerPhysicalDamage()
	{
		return character.getAdjustedStat(Stat.DAGGER_PHYSICAL_DAMAGE);
	}

	@Override
	public float getDaggerAccuracy()
	{
		return character.getAdjustedStat(Stat.DAGGER_ACCURACY);
	}

	@Override
	public float getDaggerAttackSpeed()
	{
		return character.getAdjustedStat(Stat.DAGGER_ATTACK_SPEED);
	}

	@Override
	public float getDaggerCsc()
	{
		return character.getAdjustedStat(Stat.DAGGER_CSC);
	}

	@Override
	public float getDaggerPoisonOnCrit()
	{
		return character.getAdjustedStat(Stat.DAGGER_POISON_ON_CRIT);
	}

	@Override
	public float getDaggerCsm()
	{
		return character.getAdjustedStat(Stat.DAGGER_CSM);
	}

	@Override
	public float getAxePhysicalDamage()
	{
		return character.getAdjustedStat(Stat.AXE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getAxeAttackSpeed()
	{
		return character.getAdjustedStat(Stat.AXE_ATTACK_SPEED);
	}

	@Override
	public float getAxeAccuracy()
	{
		return character.getAdjustedStat(Stat.AXE_ACCURACY);
	}

	@Override
	public float getSwordPhysicalDamage()
	{
		return character.getAdjustedStat(Stat.SWORD_PHYSICAL_DAMAGE);
	}

	@Override
	public float getSwordAccuracy()
	{
		return character.getAdjustedStat(Stat.SWORD_ACCURACY);
	}

	@Override
	public float getSwordAttackSpeed()
	{
		return character.getAdjustedStat(Stat.SWORD_ATTACK_SPEED);
	}

	@Override
	public float getSwordCsc()
	{
		return character.getAdjustedStat(Stat.SWORD_CSC);
	}

	@Override
	public float getSwordCsm()
	{
		return character.getAdjustedStat(Stat.SWORD_CSM);
	}

	@Override
	public float getClawPhysicalDamage()
	{
		return character.getAdjustedStat(Stat.CLAW_PHYSICAL_DAMAGE);
	}

	@Override
	public float getClawAttackSpeed()
	{
		return character.getAdjustedStat(Stat.CLAW_ATTACK_SPEED);
	}

	@Override
	public float getClawCsc()
	{
		return character.getAdjustedStat(Stat.CLAW_CSC);
	}

	@Override
	public float getClawCsm()
	{
		return character.getAdjustedStat(Stat.CLAW_CSM);
	}

	@Override
	public float getClawAccuracy()
	{
		return character.getAdjustedStat(Stat.CLAW_ACCURACY);
	}

	@Override
	public float getClawStealCharge()
	{
		return character.getAdjustedStat(Stat.CLAW_STEAL_CHARGE);
	}

	@Override
	public float getClawLifeLeech()
	{
		return character.getAdjustedStat(Stat.CLAW_LIFE_LEECH);
	}

	@Override
	public float getClawManaLeech()
	{
		return character.getAdjustedStat(Stat.CLAW_MANA_LEECH);
	}

	@Override
	public float getMaceAttackSpeed()
	{
		return character.getAdjustedStat(Stat.MACE_ATTACK_SPEED);
	}

	@Override
	public float getMaceAccuracy()
	{
		return character.getAdjustedStat(Stat.MACE_ACCURACY);
	}

	@Override
	public float getMaceCsm()
	{
		return character.getAdjustedStat(Stat.MACE_CSM);
	}

	@Override
	public float getMacePhysicalDamage()
	{
		return character.getAdjustedStat(Stat.MACE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getMaceStunThreshold()
	{
		return character.getAdjustedStat(Stat.MACE_STUN_THRESHOLD);
	}

	@Override
	public float getMaceElementalDamage()
	{
		return character.getAdjustedStat(Stat.MACE_ELEMENTAL_DAMAGE);
	}

	@Override
	public float getMaceCsc()
	{
		return character.getAdjustedStat(Stat.MACE_CSC);
	}

	@Override
	public float getBowKb()
	{
		return character.getAdjustedStat(Stat.BOW_KB);
	}

	@Override
	public float getBowCsc()
	{
		return character.getAdjustedStat(Stat.BOW_CSC);
	}

	@Override
	public float getBowCsm()
	{
		return character.getAdjustedStat(Stat.BOW_CSM);
	}

	@Override
	public float getBowPhysical()
	{
		return character.getAdjustedStat(Stat.BOW_PHYSICAL);
	}

	@Override
	public float getBowAccuracy()
	{
		return character.getAdjustedStat(Stat.BOW_ACCURACY);
	}

	@Override
	public float getBowAttackSpeed()
	{
		return character.getAdjustedStat(Stat.BOW_ATTACK_SPEED);
	}

	@Override
	public float getBowStunThreshhold()
	{
		return character.getAdjustedStat(Stat.BOW_STUN_THRESHHOLD);
	}

	@Override
	public float getBowStunDuration()
	{
		return character.getAdjustedStat(Stat.BOW_STUN_DURATION);
	}

	@Override
	public float getDualPhys()
	{
		return character.getAdjustedStat(Stat.DUAL_PHYS);
	}

	@Override
	public float getDualCsc()
	{
		return character.getAdjustedStat(Stat.DUAL_CSC);
	}

	@Override
	public float getDualBlock()
	{
		return character.getAdjustedStat(Stat.DUAL_BLOCK);
	}

	@Override
	public float getDualAccuracy()
	{
		return character.getAdjustedStat(Stat.DUAL_ACCURACY);
	}

	@Override
	public float getDualAttackSpeed()
	{
		return character.getAdjustedStat(Stat.DUAL_ATTACK_SPEED);
	}

	@Override
	public float getDualMainDamage()
	{
		return character.getAdjustedStat(Stat.DUAL_MAIN_DAMAGE);
	}

	@Override
	public float getDualOffHandDamage()
	{
		return character.getAdjustedStat(Stat.DUAL_OFF_HAND_DAMAGE);
	}

	@Override
	public float getDualCastSpeed()
	{
		return character.getAdjustedStat(Stat.DUAL_CAST_SPEED);
	}

	@Override
	public float getShieldAttackSpeed()
	{
		return character.getAdjustedStat(Stat.SHIELD_ATTACK_SPEED);
	}

	@Override
	public float getShieldPhysicalDamage()
	{
		return character.getAdjustedStat(Stat.SHIELD_PHYSICAL_DAMAGE);
	}

	@Override
	public float getShieldMeleePhysicalDamage()
	{
		return character.getAdjustedStat(Stat.SHIELD_MELEE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getAccuracy()
	{
		return character.getAdjustedStat(Stat.ACCURACY);
	}

	@Override
	public float getAccPlus()
	{
		return character.getAdjustedStat(Stat.ACC_PLUS);
	}

	@Override
	public float getEvasion()
	{
		return character.getAdjustedStat(Stat.EVASION);
	}

	@Override
	public float getEvasionArmor()
	{
		return character.getAdjustedStat(Stat.EVASION_ARMOR);
	}

	@Override
	public float getEvaRat()
	{
		return character.getAdjustedStat(Stat.EVA_RAT);
	}

	@Override
	public float getEvadeProjectile()
	{
		return character.getAdjustedStat(Stat.EVADE_PROJECTILE);
	}

	@Override
	public float getEvadeMelee()
	{
		return character.getAdjustedStat(Stat.EVADE_MELEE);
	}

	@Override
	public float getEvadeNever()
	{
		return character.getAdjustedStat(Stat.EVADE_NEVER);
	}

	@Override
	public float getArmour()
	{
		return character.getAdjustedStat(Stat.ARMOUR);
	}

	@Override
	public float getArmorFlat()
	{
		return character.getAdjustedStat(Stat.ARMOUR_FLAT);
	}

	@Override
	public float getArmorEvade()
	{
		return character.getAdjustedStat(Stat.ARMOR_EVADE);
	}

	@Override
	public float getMovementSpeed()
	{
		return character.getAdjustedStat(Stat.MOVEMENT_SPEED);
	}

	@Override
	public float getMovementEnergyShield()
	{
		return character.getAdjustedStat(Stat.MOVEMENT_ENERGY_SHIELD);
	}

	@Override
	public float getMovementIgnoreArmor()
	{
		return character.getAdjustedStat(Stat.MOVEMENT_IGNORE_ARMOR);
	}

	@Override
	public float getRegen()
	{
		return character.getAdjustedStat(Stat.REGEN);
	}

	@Override
	public float getBlockChance()
	{
		return character.getAdjustedStat(Stat.BLOCK_CHANCE);
	}

	@Override
	public float getShieldDefence()
	{
		return character.getAdjustedStat(Stat.SHIELD_DEFENCE);
	}

	@Override
	public float getShieldBlockChance()
	{
		return character.getAdjustedStat(Stat.SHIELD_BLOCK_CHANCE);
	}

	@Override
	public float getShieldElementalResist()
	{
		return character.getAdjustedStat(Stat.SHIELD_ELEMENTAL_RESIST);
	}

	@Override
	public float getShieldDualBlock()
	{
		return character.getAdjustedStat(Stat.SHIELD_DUAL_BLOCK);
	}

	@Override
	public float getLightningResist()
	{
		return character.getAdjustedStat(Stat.LIGHTNING_RESIST);
	}

	@Override
	public float getLightningResistMax()
	{
		return character.getAdjustedStat(Stat.LIGHTNING_RESIST_MAX);
	}

	@Override
	public float getMaxLight()
	{
		return character.getAdjustedStat(Stat.MAX_LIGHT);
	}

	@Override
	public float getElementalResist()
	{
		return character.getAdjustedStat(Stat.ELEMENTAL_RESIST);
	}

	@Override
	public float getFireResist()
	{
		return character.getAdjustedStat(Stat.FIRE_RESIST);
	}

	@Override
	public float getFireResistMax()
	{
		return character.getAdjustedStat(Stat.FIRE_RESIST_MAX);
	}

	@Override
	public float getColdResist()
	{
		return character.getAdjustedStat(Stat.COLD_RESIST);
	}

	@Override
	public float getColdResistCap()
	{
		return character.getAdjustedStat(Stat.COLD_RESIST_CAP);
	}

	@Override
	public float getChaosResist()
	{
		return character.getAdjustedStat(Stat.CHAOS_RESIST);
	}

	@Override
	public float getResistAll()
	{
		return character.getAdjustedStat(Stat.RESIST_ALL);
	}

	@Override
	public float getFreeze()
	{
		return character.getAdjustedStat(Stat.FREEZE_CHANCE);
	}

	@Override
	public float getCold()
	{
		return character.getAdjustedStat(Stat.COLD);
	}

	@Override
	public float getColdPen()
	{
		return character.getAdjustedStat(Stat.COLD_PEN);
	}

	@Override
	public float getPhysicalDamageReduction()
	{
		return character.getAdjustedStat(Stat.PHYSICAL_DAMAGE_REDUCTION);
	}

	@Override
	public float getElementalDamageReductionOnConsecrate()
	{
		return character.getAdjustedStat(Stat.ELEMENTAL_DAMAGE_REDUCTION_ON_CONSECRATE);
	}

	@Override
	public float getCriticalReduce()
	{
		return character.getAdjustedStat(Stat.CRITICAL_REDUCE);
	}

	@Override
	public float getChillAvoid()
	{
		return character.getAdjustedStat(Stat.CHILL_AVOID);
	}

	@Override
	public float getFrozenAvoid()
	{
		return character.getAdjustedStat(Stat.FROZEN_AVOID);
	}

	@Override
	public float getIgniteAvoid()
	{
		return character.getAdjustedStat(Stat.IGNITE_AVOID);
	}

	@Override
	public float getShockAvoid()
	{
		return character.getAdjustedStat(Stat.SHOCK_AVOID);
	}

	@Override
	public float getAvoidStunOnCast()
	{
		return character.getAdjustedStat(Stat.AVOID_STUN_ON_CAST);
	}

	@Override
	public float getChillDuration()
	{
		return character.getAdjustedStat(Stat.CHILL_DURATION);
	}

	@Override
	public float getFreezeDuration()
	{
		return character.getAdjustedStat(Stat.FREEZE_DURATION);
	}

	@Override
	public float getBlockRecovery()
	{
		return character.getAdjustedStat(Stat.BLOCK_RECOVERY);
	}

	@Override
	public float getManaOnBlock()
	{
		return character.getAdjustedStat(Stat.MANA_ON_BLOCK);
	}

	@Override
	public float getCastSpeed()
	{
		return character.getAdjustedStat(Stat.CAST_SPEED);
	}

	@Override
	public float getCastSpeedChaos()
	{
		return character.getAdjustedStat(Stat.CAST_SPEED_CHAOS);
	}

	@Override
	public float getSpellDamage()
	{
		return character.getAdjustedStat(Stat.SPELL_DAMAGE);
	}

	@Override
	public float getSpellCsm()
	{
		return character.getAdjustedStat(Stat.SPELL_CSM);
	}

	@Override
	public float getSpellCsc()
	{
		return character.getAdjustedStat(Stat.SPELL_CSC);
	}

	@Override
	public float getPenFire()
	{
		return character.getAdjustedStat(Stat.PEN_FIRE);
	}

	@Override
	public float getWeapPenFire()
	{
		return character.getAdjustedStat(Stat.WEAP_PEN_FIRE);
	}

	@Override
	public float getLightPen()
	{
		return character.getAdjustedStat(Stat.LIGHT_PEN);
	}

	@Override
	public float getElemPen()
	{
		return character.getAdjustedStat(Stat.ELEM_PEN);
	}

	@Override
	public float getIgDur()
	{
		return character.getAdjustedStat(Stat.IG_DUR);
	}

	@Override
	public float getIgChance()
	{
		return character.getAdjustedStat(Stat.IG_CHANCE);
	}

	@Override
	public float getLifeLeech()
	{
		return character.getAdjustedStat(Stat.LIFE_LEECH);
	}

	@Override
	public float getLifeLeechInstant()
	{
		return character.getAdjustedStat(Stat.LIFE_LEECH_INSTANT);
	}

	@Override
	public float getLifeLeechPhysical()
	{
		return character.getAdjustedStat(Stat.LIFE_LEECH_PHYSICAL);
	}

	@Override
	public float getLeechRate()
	{
		return character.getAdjustedStat(Stat.LEECH_RATE);
	}

	@Override
	public float getLeechPerSec()
	{
		return character.getAdjustedStat(Stat.LEECH_PER_SEC);
	}

	@Override
	public float getLeechStun()
	{
		return character.getAdjustedStat(Stat.LEECH_STUN);
	}

	@Override
	public float getLeechDamage()
	{
		return character.getAdjustedStat(Stat.LEECH_DAMAGE);
	}

	@Override
	public float getPhysLife()
	{
		return character.getAdjustedStat(Stat.PHYS_LIFE);
	}

	@Override
	public float getImmuneBleed()
	{
		return character.getAdjustedStat(Stat.IMMUNE_BLEED);
	}

	@Override
	public float getKillWeak()
	{
		return character.getAdjustedStat(Stat.KILL_WEAK);
	}

	@Override
	public float getOverkill()
	{
		return character.getAdjustedStat(Stat.OVERKILL);
	}

	@Override
	public float getFillLeech()
	{
		return character.getAdjustedStat(Stat.FILL_LEECH);
	}

	@Override
	public float getOnslaughtOnRareOrUnique()
	{
		return character.getAdjustedStat(Stat.ONSLAUGHT_ON_RARE_OR_UNIQUE);
	}

	@Override
	public float getOnslaughtOnKillKill()
	{
		return character.getAdjustedStat(Stat.ONSLAUGHT_ON_KILL_KILL);
	}

	@Override
	public float getOnslaughtOnFullFrenzy()
	{
		return character.getAdjustedStat(Stat.ONSLAUGHT_ON_FULL_FRENZY);
	}

	@Override
	public float getEvasionOnOnslaught()
	{
		return character.getAdjustedStat(Stat.EVASION_ON_ONSLAUGHT);
	}

	@Override
	public float getAoeRadius()
	{
		return character.getAdjustedStat(Stat.AOE_RADIUS);
	}

	@Override
	public float getAoeDamage()
	{
		return character.getAdjustedStat(Stat.AOE_DAMAGE);
	}

	@Override
	public float getStunDuration()
	{
		return character.getAdjustedStat(Stat.STUN_DURATION);
	}

	@Override
	public float getStunOnEnemyFullLife()
	{
		return character.getAdjustedStat(Stat.STUN_ON_ENEMY_FULL_LIFE);
	}

	@Override
	public float getStunDurationOnEnemyFullLife()
	{
		return character.getAdjustedStat(Stat.STUN_DURATION_ON_ENEMY_FULL_LIFE);
	}

	@Override
	public float getStunDurationOnEnemyLowLife()
	{
		return character.getAdjustedStat(Stat.STUN_DURATION_ON_ENEMY_LOW_LIFE);
	}

	@Override
	public float getStunDurationDouble()
	{
		return character.getAdjustedStat(Stat.STUN_DURATION_DOUBLE);
	}

	@Override
	public float getStunAvoid()
	{
		return character.getAdjustedStat(Stat.STUN_AVOID);
	}

	@Override
	public float getStunNever()
	{
		return character.getAdjustedStat(Stat.STUN_NEVER);
	}

	@Override
	public float getStunAndBlockRecovery()
	{
		return character.getAdjustedStat(Stat.STUN_AND_BLOCK_RECOVERY);
	}

	@Override
	public float getStunThreshhold()
	{
		return character.getAdjustedStat(Stat.STUN_THRESHHOLD);
	}

	@Override
	public float getReflectReduce()
	{
		return character.getAdjustedStat(Stat.REFLECT_REDUCE);
	}

	@Override
	public float getRecentKillDamage()
	{
		return character.getAdjustedStat(Stat.RECENT_KILL_DAMAGE);
	}

	@Override
	public float getSingleSplash()
	{
		return character.getAdjustedStat(Stat.SINGLE_SPLASH);
	}

	@Override
	public float getSplashLess()
	{
		return character.getAdjustedStat(Stat.SPLASH_LESS);
	}

	@Override
	public float getBlockSpells()
	{
		return character.getAdjustedStat(Stat.BLOCK_SPELLS);
	}

	@Override
	public float getBlockSpellShield()
	{
		return character.getAdjustedStat(Stat.BLOCK_SPELL_SHIELD);
	}

	@Override
	public float getManaLeech()
	{
		return character.getAdjustedStat(Stat.MANA_LEECH);
	}

	@Override
	public float getAdMana()
	{
		return character.getAdjustedStat(Stat.AD_MANA);
	}

	@Override
	public float getManaLeechRate()
	{
		return character.getAdjustedStat(Stat.MANA_LEECH_RATE);
	}

	@Override
	public float getMinionInstability()
	{
		return character.getAdjustedStat(Stat.MINION_INSTABILITY);
	}

	@Override
	public float getMinionAttackSpeed()
	{
		return character.getAdjustedStat(Stat.MINION_ATTACK_SPEED);
	}

	@Override
	public float getMinionCastSpeed()
	{
		return character.getAdjustedStat(Stat.MINION_CAST_SPEED);
	}

	@Override
	public float getMinionShield()
	{
		return character.getAdjustedStat(Stat.MINION_SHIELD);
	}

	@Override
	public float getMinionBlock()
	{
		return character.getAdjustedStat(Stat.MINION_BLOCK);
	}

	@Override
	public float getMinionBlockHeal()
	{
		return character.getAdjustedStat(Stat.MINION_BLOCK_HEAL);
	}

	@Override
	public float getMinionDamage()
	{
		return character.getAdjustedStat(Stat.MINION_DAMAGE);
	}

	@Override
	public float getMinionLifeLeech()
	{
		return character.getAdjustedStat(Stat.MINION_LL);
	}

	@Override
	public float getMinionLifeRegeneration()
	{
		return character.getAdjustedStat(Stat.MINION_REGEN);
	}

	@Override
	public float getMinionElementalResist()
	{
		return character.getAdjustedStat(Stat.MINION_ELEM_RESIST);
	}

	@Override
	public float getMinionChaosResist()
	{
		return character.getAdjustedStat(Stat.MINION_CHAOS_RESIST);
	}

	@Override
	public float getIncreasedMinionLife()
	{
		return character.getAdjustedStat(Stat.MINION_MAXIMUM_LIFE);
	}

	@Override
	public float getZombies()
	{
		return character.getAdjustedStat(Stat.ZOMBIES);
	}

	@Override
	public float getSkel()
	{
		return character.getAdjustedStat(Stat.SKEL);
	}

	@Override
	public float getSkelMax()
	{
		return character.getAdjustedStat(Stat.SKEL_MAX);
	}

	@Override
	public float getSpecMax()
	{
		return character.getAdjustedStat(Stat.SPEC_MAX);
	}

	@Override
	public float getAncBond()
	{
		return character.getAdjustedStat(Stat.ANC_BOND);
	}

	@Override
	public float getTotemAdd()
	{
		return character.getAdjustedStat(Stat.TOTEM_ADD);
	}

	@Override
	public float getTotemResistElemental()
	{
		return character.getAdjustedStat(Stat.TOTEM_RESIST_ELEMENTAL);
	}

	@Override
	public float getKnockbackOnHit()
	{
		return character.getAdjustedStat(Stat.KNOCKBACK_ON_HIT);
	}

	@Override
	public float getKnockbackDistance()
	{
		return character.getAdjustedStat(Stat.KNOCKBACK_DISTANCE);
	}

	@Override
	public float getBleed()
	{
		return character.getAdjustedStat(Stat.BLEED);
	}

	@Override
	public float getBleedDamageOnKill()
	{
		return character.getAdjustedStat(Stat.BLEED_DAMAGE_ON_KILL);
	}

	@Override
	public float getBleedDmg()
	{
		return character.getAdjustedStat(Stat.BLEED_DMG);
	}

	@Override
	public float getBleedMelee()
	{
		return character.getAdjustedStat(Stat.BLEED_MELEE);
	}

	@Override
	public float getBleedPoison()
	{
		return character.getAdjustedStat(Stat.BLEED_POISON);
	}

	@Override
	public float getBleedCsm()
	{
		return character.getAdjustedStat(Stat.BLEED_CSM);
	}

	@Override
	public float getBleedCsc()
	{
		return character.getAdjustedStat(Stat.BLEED_CSC);
	}

	@Override
	public float getPoisonDamage()
	{
		return character.getAdjustedStat(Stat.POISON_DAMAGE);
	}

	@Override
	public float getPoisonOnHit()
	{
		return character.getAdjustedStat(Stat.POISON_ON_HIT);
	}

	@Override
	public float getMaxEnergyShield()
	{
		return character.getAdjustedStat(Stat.MAX_ENERGY_SHIELD);
	}

	@Override
	public float getIncreasedEnergyShield()
	{
		return character.getAdjustedStat(Stat.INCREASED_ENERGY_SHIELD);
	}

	@Override
	public float getEnergyShieldPct2()
	{
		return character.getAdjustedStat(Stat.ENERGY_SHIELD_PCT_2);
	}

	@Override
	public float getEnergyShieldMana()
	{
		return character.getAdjustedStat(Stat.ENERGY_SHIELD_MANA);
	}

	@Override
	public float getEnergyShieldMana2()
	{
		return character.getAdjustedStat(Stat.ENERGY_SHIELD_MANA_2);
	}

	@Override
	public float getEnergyShieldEvade()
	{
		return character.getAdjustedStat(Stat.ENERGY_SHIELD_EVADE);
	}

	@Override
	public float getEnergyShieldMoreDamage()
	{
		return character.getAdjustedStat(Stat.ENERGY_SHIELD_MORE_DAMAGE);
	}

	@Override
	public float getEnergyShieldRecharg()
	{
		return character.getAdjustedStat(Stat.ENERGY_SHIELD_RECHARG);
	}

	@Override
	public float getEnergyShieldShield()
	{
		return character.getAdjustedStat(Stat.ENERGY_SHIELD_SHIELD);
	}

	@Override
	public float getEnergyShieldLifeRegen()
	{
		return character.getAdjustedStat(Stat.ENERGY_SHIELD_LIFE_REGEN);
	}

	@Override
	public float getEnergyShieldLifeLeech()
	{
		return character.getAdjustedStat(Stat.ENERGY_SHIELD_LIFE_LEECH);
	}

	@Override
	public float getEnergyShieldBeforeMana()
	{
		return character.getAdjustedStat(Stat.ENERGY_SHIELD_BEFORE_MANA);
	}

	@Override
	public float getEnergyShieldProtectMana()
	{
		return character.getAdjustedStat(Stat.ENERGY_SHIELD_BEFORE_MANA);
	}

	@Override
	public float getChain()
	{
		return character.getAdjustedStat(Stat.CHAIN);
	}

	@Override
	public float getNoBleed()
	{
		return character.getAdjustedStat(Stat.NO_BLEED);
	}

	@Override
	public float getCsc()
	{
		return character.getAdjustedStat(Stat.CSC);
	}

	@Override
	public float getEsFaster()
	{
		return character.getAdjustedStat(Stat.ES_FASTER);
	}

	@Override
	public float getDodgeAttack()
	{
		return character.getAdjustedStat(Stat.DODGE_ATTACK);
	}

	@Override
	public float getDodgeAttack2()
	{
		return character.getAdjustedStat(Stat.DODGE_ATTACK2);
	}

	@Override
	public float getDodgeSpell()
	{
		return character.getAdjustedStat(Stat.DODGE_SPELL);
	}

	@Override
	public float getDodgeAcrobatics()
	{
		return character.getAdjustedStat(Stat.DODGE_ACROBATICS);
	}

	@Override
	public float getAuraRad()
	{
		return character.getAdjustedStat(Stat.AURA_RAD);
	}

	@Override
	public float getNonCurseAuraEffect()
	{
		return character.getAdjustedStat(Stat.NON_CURSE_AURA_EFFECT);
	}

	@Override
	public float getCurseEffect()
	{
		return character.getAdjustedStat(Stat.CURSE_EFFECT);
	}

	@Override
	public float getAdditionalCurse()
	{
		return character.getAdjustedStat(Stat.CURSE_NUM);
	}

	@Override
	public float getCurseDuration()
	{
		return character.getAdjustedStat(Stat.CURSE_DUR);
	}

	@Override
	public float getCurseCastSpeed()
	{
		return character.getAdjustedStat(Stat.CURSE_CAST_SPEED);
	}

	@Override
	public float getCurseRadius()
	{
		return character.getAdjustedStat(Stat.CURSE_RADIUS);
	}

	@Override
	public float getFrenzCharg()
	{
		return character.getAdjustedStat(Stat.FRENZ_CHARG);
	}

	@Override
	public float getFrChDur()
	{
		return character.getAdjustedStat(Stat.FR_CH_DUR);
	}

	@Override
	public float getFrenzyEvasion()
	{
		return character.getAdjustedStat(Stat.FRENZY_EVASION);
	}

	@Override
	public float getFrenzKill()
	{
		return character.getAdjustedStat(Stat.FRENZ_KILL);
	}

	@Override
	public float getEndDur()
	{
		return character.getAdjustedStat(Stat.END_DUR);
	}

	@Override
	public float getEnduranceChargeLifeRegen()
	{
		return character.getAdjustedStat(Stat.ENDURANCE_CHARGE_LIFE_REGEN);
	}

	@Override
	public float getMaxEnd()
	{
		return character.getAdjustedStat(Stat.MAX_END);
	}

	@Override
	public float getChargeOnKill()
	{
		return character.getAdjustedStat(Stat.CHARGE_ON_KILL);
	}

	@Override
	public float getPowerChargeDuration()
	{
		return character.getAdjustedStat(Stat.POWER_CHARGE_DURATION);
	}

	@Override
	public float getPowerChargeAdditional()
	{
		return character.getAdjustedStat(Stat.POWER_CHARGE_ADDITIONAL);
	}

	@Override
	public float getChargeShare()
	{
		return character.getAdjustedStat(Stat.CHARGE_SHARE);
	}

	@Override
	public float getEnduranceChargeMax()
	{
		return character.getAdjustedStat(Stat.ENDURANCE_CHARGE_MAX);
	}

	@Override
	public float getEnduranceChargeOnMeleeCritical()
	{
		return character.getAdjustedStat(Stat.ENDURANCE_CHARGE_ON_MELEE_CRITICAL);
	}

	@Override
	public float getPowerChargeOnBlock()
	{
		return character.getAdjustedStat(Stat.POWER_CHARGE_ON_BLOCK);
	}

	@Override
	public float getTrapRed()
	{
		return character.getAdjustedStat(Stat.TRAP_RED);
	}

	@Override
	public float getMeleeFort()
	{
		return character.getAdjustedStat(Stat.MELEE_FORT);
	}

	@Override
	public float getIncFort()
	{
		return character.getAdjustedStat(Stat.INC_FORT);
	}

	@Override
	public float getFortDur()
	{
		return character.getAdjustedStat(Stat.FORT_DUR);
	}

	@Override
	public float getFortMelee()
	{
		return character.getAdjustedStat(Stat.FORT_MELEE);
	}

	@Override
	public float getFortifyArmour()
	{
		return character.getAdjustedStat(Stat.FORTIFY_ARMOUR);
	}

	@Override
	public float getFortAlly()
	{
		return character.getAdjustedStat(Stat.FORT_ALLY);
	}

	@Override
	public float getTauntHit()
	{
		return character.getAdjustedStat(Stat.TAUNT_HIT);
	}

	@Override
	public float getTauntReduce()
	{
		return character.getAdjustedStat(Stat.TAUNT_REDUCE);
	}

	@Override
	public float getLightRadiusEnergyShield()
	{
		return character.getAdjustedStat(Stat.LIGHT_RADIUS_ENERGY_SHIELD);
	}

	@Override
	public float getLightRadius()
	{
		return character.getAdjustedStat(Stat.LIGHT_RADIUS);
	}

	@Override
	public float getStunCull()
	{
		return character.getAdjustedStat(Stat.STUN_CULL);
	}

	@Override
	public float getAttackCastSpeed()
	{
		return character.getAdjustedStat(Stat.ATTACK_CAST_SPEED);
	}

	@Override
	public float getAtkCast()
	{
		return character.getAdjustedStat(Stat.ATK_CAST);
	}

	@Override
	public float getAtckCastMove()
	{
		return character.getAdjustedStat(Stat.ATCK_CAST_MOVE);
	}

	@Override
	public float getLifeManaRegen()
	{
		return character.getAdjustedStat(Stat.LIFE_MANA_REGEN);
	}

	@Override
	public float getAvoidElem()
	{
		return character.getAdjustedStat(Stat.AVOID_ELEM);
	}

	@Override
	public float getFreezeShockIgnite()
	{
		return character.getAdjustedStat(Stat.FREEZE_SHOCK_IGNITE);
	}

	@Override
	public float getNeverEvaded()
	{
		return character.getAdjustedStat(Stat.NEVER_EVADED);
	}

	@Override
	public float getNeverCrit()
	{
		return character.getAdjustedStat(Stat.NEVER_CRIT);
	}

	@Override
	public float getEnemyNeverLeech()
	{
		return character.getAdjustedStat(Stat.ENEMY_NEVER_LEECH);
	}

	@Override
	public float getManaBeforeLife()
	{
		return character.getAdjustedStat(Stat.MIND_OVER_MATTER);
	}

	@Override
	public float getEnemyElemResist()
	{
		return character.getAdjustedStat(Stat.ENEMY_ELEM_RESIST);
	}

	@Override
	public double ln(final double x)
	{
		return Math.log(x + 1);
	}

	@Override
	public int hasPassive(final String passiveSkillName)
	{
		return character.hasPassiveNamed(passiveSkillName) ? 1 : 0;
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
		return (int)character.getPassiveSkills().stream().filter(skill -> skill.getJewels() > 0).count();
	}

	@Override
	public float rising(final float x)
	{
		return 1 - (1 / (x + 1));
	}
}
