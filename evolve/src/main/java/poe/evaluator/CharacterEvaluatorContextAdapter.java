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
		return character.getStat(Stat.MAXIMUM_LIFE);
	}

	@Override
	public float getIncreasedMaximumLife()
	{
		return character.getStat(Stat.INCRESED_MAXIMUM_LIFE);
	}

	@Override
	public float getLife1()
	{
		return character.getStat(Stat.CHAOS_INNOCULATION);
	}

	@Override
	public float getLifeOnKill()
	{
		return character.getStat(Stat.LIFE_ON_KILL);
	}

	@Override
	public float getLifeOnHit()
	{
		return character.getStat(Stat.LIFE_ON_HIT);
	}

	@Override
	public float getFlaskLife()
	{
		return character.getStat(Stat.FLASK_LIFE);
	}

	@Override
	public float getFlaskRecovery()
	{
		return character.getStat(Stat.FLASK_RECOVERY);
	}

	@Override
	public float getFlaskExtra()
	{
		return character.getStat(Stat.FLASK_EXTRA);
	}

	@Override
	public float getFlaskDmg()
	{
		return character.getStat(Stat.FLASK_DMG);
	}

	@Override
	public float getFlaskDuration()
	{
		return character.getStat(Stat.FLASK_DURATION);
	}

	@Override
	public float getFlaskIncreasedEffect()
	{
		return character.getStat(Stat.FLASK_INCREASED_EFFECT);
	}

	@Override
	public float getStrength()
	{
		return character.getStat(Stat.STRENGTH);
	}

	@Override
	public float getDexterity()
	{
		return character.getStat(Stat.DEXTERITY);
	}

	@Override
	public float getIntelligence()
	{
		return character.getStat(Stat.INTELLIGENCE);
	}

	@Override
	public float getMaxMana()
	{
		return character.getStat(Stat.MANA_BONUS);
	}

	@Override
	public float getIncreasedMaxMana()
	{
		return character.getStat(Stat.MANA);
	}

	@Override
	public float getReducedManaCost()
	{
		return character.getStat(Stat.MANA_COST_REDUCED);
	}

	@Override
	public float getManaRegenerationRate()
	{
		return character.getStat(Stat.MANA_REGEN);
	}

	@Override
	public float getManaReserved()
	{
		return character.getStat(Stat.MANA_RESERVED);
	}

	@Override
	public float getManaMoveRegen()
	{
		return character.getStat(Stat.MANA_MOVE_REGEN);
	}

	@Override
	public float getManaReserveReduce()
	{
		return character.getStat(Stat.MANA_RESERVE_REDUCE);
	}

	@Override
	public float getManaFlask()
	{
		return character.getStat(Stat.MANA_FLASK);
	}

	@Override
	public float getManaLeechPhysical()
	{
		return character.getStat(Stat.MANA_LEECH_PHYSICAL);
	}

	@Override
	public float getManaOnKill()
	{
		return character.getStat(Stat.MANA_ON_KILL);
	}

	@Override
	public float getManaOnHit()
	{
		return character.getStat(Stat.MANA_ON_HIT);
	}

	@Override
	public float getManaToLife()
	{
		return character.getStat(Stat.MANA_TO_LIFE);
	}

	@Override
	public float getManaGainOnHit()
	{
		return character.getStat(Stat.MANA_GAIN_ON_HIT);
	}

	@Override
	public float getCsm()
	{
		return character.getStat(Stat.CSM);
	}

	@Override
	public float getCsmNever()
	{
		return character.getStat(Stat.CSM_NEVER);
	}

	@Override
	public float getFlaskGain()
	{
		return character.getStat(Stat.FLASK_GAIN);
	}

	@Override
	public float getAvoidFreeze()
	{
		return character.getStat(Stat.AVOID_FREEZE);
	}

	@Override
	public float getElemFlask()
	{
		return character.getStat(Stat.ELEM_FLASK);
	}

	@Override
	public float getAttackSpeed()
	{
		return character.getStat(Stat.ATTACK_SPEED);
	}

	@Override
	public float getStunThresh()
	{
		return character.getStat(Stat.STUN_THRESH);
	}

	@Override
	public float getDamageAttack()
	{
		return character.getStat(Stat.DAMAGE_ATTACK);
	}

	@Override
	public float getDamageFrozenShockIgnited()
	{
		return character.getStat(Stat.DAMAGE_FROZEN_SHOCK_IGNITED);
	}

	@Override
	public float getDamageOnEnemyLowLife()
	{
		return character.getStat(Stat.DAMAGE_ON_ENEMY_LOW_LIFE);
	}

	@Override
	public float getPhysicalDamage()
	{
		return character.getStat(Stat.PHYSICAL_DAMAGE);
	}

	@Override
	public float getPhysicalDamage2()
	{
		return character.getStat(Stat.PHYSICAL_DAMAGE_2);
	}

	@Override
	public float getPhysicalDot()
	{
		return character.getStat(Stat.PHYSICAL_DOT);
	}

	@Override
	public float getChaosDamage()
	{
		return character.getStat(Stat.CHAOS_DAMAGE);
	}

	@Override
	public float getChaosDamageOnPhysical()
	{
		return character.getStat(Stat.CHAOS_DAMAGE_ON_PHYSICAL);
	}

	@Override
	public float getElementalDamage()
	{
		return character.getStat(Stat.ELEM_DAMAGE);
	}

	@Override
	public float getElementalStatusAilments()
	{
		return character.getStat(Stat.ELEMENTAL_STATUS_AILMENTS);
	}

	@Override
	public float getElementalDamageOnCrit()
	{
		return character.getStat(Stat.ELEMENTAL_DAMAGE_ON_CRIT);
	}

	@Override
	public float getCscElemStatus()
	{
		return character.getStat(Stat.CSC_ELEM_STATUS);
	}

	@Override
	public float getFireDamage()
	{
		return character.getStat(Stat.FIRE_DAMAGE);
	}

	@Override
	public float getFireDamageConvert()
	{
		return character.getStat(Stat.FIRE_DAMAGE_CONVERT);
	}

	@Override
	public float getBurnDamage()
	{
		return character.getStat(Stat.BURN_DAMAGE);
	}

	@Override
	public float getPhysicalDamageAsFireDamage()
	{
		return character.getStat(Stat.PHYSICAL_DAMAGE_AS_FIRE_DAMAGE);
	}

	@Override
	public float getFireDamageOnly()
	{
		return character.getStat(Stat.FIRE_DAMAGE_ONLY);
	}

	@Override
	public float getLightningDamage()
	{
		return character.getStat(Stat.LIGHTNING_DAMAGE);
	}

	@Override
	public float getDotDamage()
	{
		return character.getStat(Stat.DOT_DAMAGE);
	}

	@Override
	public float getSpellDamageOnLowLife()
	{
		return character.getStat(Stat.SPELL_DAMAGE_ON_LOW_LIFE);
	}

	@Override
	public float getSpellDamagePerPowerCharge()
	{
		return character.getStat(Stat.SPELL_DAMAGE_PER_POWER_CHARGE);
	}

	@Override
	public float getTrapDamage()
	{
		return character.getStat(Stat.TRAP_DAMAGE);
	}

	@Override
	public float getTrapRecovery()
	{
		return character.getStat(Stat.TRAP_RECOVERY);
	}

	@Override
	public float getTrapCsm()
	{
		return character.getStat(Stat.TRAP_CSM);
	}

	@Override
	public float getTrapCsc()
	{
		return character.getStat(Stat.TRAP_CSC);
	}

	@Override
	public float getTrapPowerCharge()
	{
		return character.getStat(Stat.TRAP_POWER_CHARGE);
	}

	@Override
	public float getTrapRadius()
	{
		return character.getStat(Stat.TRAP_RADIUS);
	}

	@Override
	public float getTrapSpeed()
	{
		return character.getStat(Stat.TRAP_SPEED);
	}

	@Override
	public float getTrapInvuln()
	{
		return character.getStat(Stat.TRAP_INVULN);
	}

	@Override
	public float getTrapElemPen()
	{
		return character.getStat(Stat.TRAP_ELEM_PEN);
	}

	@Override
	public float getTrapAdditional()
	{
		return character.getStat(Stat.TRAP_ADDITIONAL);
	}

	@Override
	public float getTrapFrenzyCharge()
	{
		return character.getStat(Stat.TRAP_FRENZY_CHARGE);
	}

	@Override
	public float getMineCsm()
	{
		return character.getStat(Stat.MINE_CSM);
	}

	@Override
	public float getMineCsc()
	{
		return character.getStat(Stat.MINE_CSC);
	}

	@Override
	public float getMinePowerCharge()
	{
		return character.getStat(Stat.MINE_POWER_CHARGE);
	}

	@Override
	public float getMineDamage()
	{
		return character.getStat(Stat.MINE_DAMAGE);
	}

	@Override
	public float getMineSpeed()
	{
		return character.getStat(Stat.MINE_SPEED);
	}

	@Override
	public float getMineDuration()
	{
		return character.getStat(Stat.MINE_DURATION);
	}

	@Override
	public float getMineAdditional()
	{
		return character.getStat(Stat.MINE_ADDITIONAL);
	}

	@Override
	public float getMineInstant()
	{
		return character.getStat(Stat.MINE_INSTANT);
	}

	@Override
	public float getMineInvuln()
	{
		return character.getStat(Stat.MINE_INVULN);
	}

	@Override
	public float getMineRadius()
	{
		return character.getStat(Stat.MINE_RADIUS);
	}

	@Override
	public float getMineElemPen()
	{
		return character.getStat(Stat.MINE_ELEM_PEN);
	}

	@Override
	public float getChillOnUnfreeze()
	{
		return character.getStat(Stat.CHILL_ON_UNFREEZE);
	}

	@Override
	public float getFreezeOnChill()
	{
		return character.getStat(Stat.FREEZE_ON_CHILL);
	}

	@Override
	public float getShockDuration()
	{
		return character.getStat(Stat.SHOCK_DURATION);
	}

	@Override
	public float getShockChance()
	{
		return character.getStat(Stat.SHOCK_CHANCE);
	}

	@Override
	public float getSkillDur()
	{
		return character.getStat(Stat.SKILL_DUR);
	}

	@Override
	public float getTotemDamage()
	{
		return character.getStat(Stat.TOTEM_DAMAGE);
	}

	@Override
	public float getTotemPlacementSpeed()
	{
		return character.getStat(Stat.TOTEM_PLACEMENT_SPEED);
	}

	@Override
	public float getTotemLife()
	{
		return character.getStat(Stat.TOTEM_LIFE);
	}

	@Override
	public float getTotemHelm()
	{
		return character.getStat(Stat.TOTEM_HELM);
	}

	@Override
	public float getTotemRes()
	{
		return character.getStat(Stat.TOTEM_RES);
	}

	@Override
	public float getTotemCharge()
	{
		return character.getStat(Stat.TOTEM_CHARGE);
	}

	@Override
	public float getTotemDuration()
	{
		return character.getStat(Stat.TOTEM_DURATION);
	}

	@Override
	public float getTotemCastSpeed()
	{
		return character.getStat(Stat.TOTEM_CAST_SPEED);
	}

	@Override
	public float getTotemAttackSpeed()
	{
		return character.getStat(Stat.TOTEM_ATTACK_SPEED);
	}

	@Override
	public float getTotemCsc()
	{
		return character.getStat(Stat.TOTEM_CSC);
	}

	@Override
	public float getTotemCsm()
	{
		return character.getStat(Stat.TOTEM_CSM);
	}

	@Override
	public float getTotalGlobalCsc()
	{
		return character.getStat(Stat.TOTAL_GLOBAL_CSC);
	}

	@Override
	public float getProjDamage()
	{
		return character.getStat(Stat.PROJ_DAMAGE);
	}

	@Override
	public float getProjSpeed()
	{
		return character.getStat(Stat.PROJ_SPEED);
	}

	@Override
	public float getProjFar()
	{
		return character.getStat(Stat.PROJ_FAR);
	}

	@Override
	public float getProjSkill()
	{
		return character.getStat(Stat.PROJ_SKILL);
	}

	@Override
	public float getProjPierce()
	{
		return character.getStat(Stat.PROJ_PIERCE);
	}

	@Override
	public float getProjPointBlank()
	{
		return character.getStat(Stat.PROJ_POINT_BLANK);
	}

	@Override
	public float getProjStr()
	{
		return character.getStat(Stat.PROJ_STR);
	}

	@Override
	public float getArrowSpeed()
	{
		return character.getStat(Stat.ARROW_SPEED);
	}

	@Override
	public float getWeaponElementalDamage()
	{
		return character.getStat(Stat.WEAPON_ELEMENTAL_DAMAGE);
	}

	@Override
	public float getWeaponFireDamage()
	{
		return character.getStat(Stat.WEAPON_FIRE_DAMAGE);
	}

	@Override
	public float getWeaponColdDamage()
	{
		return character.getStat(Stat.WEAPON_COLD_DAMAGE);
	}

	@Override
	public float getWeaponLightningDamage()
	{
		return character.getStat(Stat.WEAPON_LIGHTNING_DAMAGE);
	}

	@Override
	public float getWeaponPenCold()
	{
		return character.getStat(Stat.WEAPON_PEN_COLD);
	}

	@Override
	public float getWeaponPenLightning()
	{
		return character.getStat(Stat.WEAPON_PEN_LIGHTNING);
	}

	@Override
	public float getWeaponPenElem()
	{
		return character.getStat(Stat.WEAPON_PEN_ELEM);
	}

	@Override
	public float getMeleeDmg()
	{
		return character.getStat(Stat.MELEE_DMG);
	}

	@Override
	public float getMeleePhysicalDamage()
	{
		return character.getStat(Stat.MELEE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getMeleeAttackSpeed()
	{
		return character.getStat(Stat.MELEE_ATTACK_SPEED);
	}

	@Override
	public float getMeleeCsc()
	{
		return character.getStat(Stat.MELEE_CSC);
	}

	@Override
	public float getMeleeCsm()
	{
		return character.getStat(Stat.MELEE_CSM);
	}

	@Override
	public float getMeleeRange()
	{
		return character.getStat(Stat.MELEE_RANGE);
	}

	@Override
	public float getOneHandedPhysicalDamage()
	{
		return character.getStat(Stat.ONE_HANDED_PHYSICAL_DAMAGE);
	}

	@Override
	public float getOneHandedAttackSpeed()
	{
		return character.getStat(Stat.ONE_HANDED_ATTACK_SPEED);
	}

	@Override
	public float getOneHandedAccuracy()
	{
		return character.getStat(Stat.ONE_HANDED_ACCURACY);
	}

	@Override
	public float getOneHandedCsc()
	{
		return character.getStat(Stat.ONE_HANDED_CSC);
	}

	@Override
	public float getTwoHandedPhysicalDamage()
	{
		return character.getStat(Stat.TWO_HANDED_PHYSICAL_DAMAGE);
	}

	@Override
	public float getTwoHandedAccuracy()
	{
		return character.getStat(Stat.TWO_HANDED_ACCURACY);
	}

	@Override
	public float getTwoHandedAttackSpeed()
	{
		return character.getStat(Stat.TWO_HANDED_ATTACK_SPEED);
	}

	@Override
	public float getTwoHandedStunDuration()
	{
		return character.getStat(Stat.TWO_HANDED_STUN_DURATION);
	}

	@Override
	public float getTwoHandedDamage()
	{
		return character.getStat(Stat.TWO_HANDED_DAMAGE);
	}

	@Override
	public float getStavePhysicalDamage()
	{
		return character.getStat(Stat.STAVE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getStaveAccuracy()
	{
		return character.getStat(Stat.STAVE_ACCURACY);
	}

	@Override
	public float getStaveAttackSpeed()
	{
		return character.getStat(Stat.STAVE_ATTACK_SPEED);
	}

	@Override
	public float getStaveCsc()
	{
		return character.getStat(Stat.STAVE_CSC);
	}

	@Override
	public float getStaveGlobalCsc()
	{
		return character.getStat(Stat.STAVE_GLOBAL_CSC);
	}

	@Override
	public float getStaveGlobalCsm()
	{
		return character.getStat(Stat.STAVE_GLOBAL_CSM);
	}

	@Override
	public float getStaveKnockbackOnCrit()
	{
		return character.getStat(Stat.STAVE_KNOCKBACK_ON_CRIT);
	}

	@Override
	public float getStaveBlock()
	{
		return character.getStat(Stat.STAVE_BLOCK);
	}

	@Override
	public float getWandDamage()
	{
		return character.getStat(Stat.WAND_DAMAGE);
	}

	@Override
	public float getWandPhysicalDamage()
	{
		return character.getStat(Stat.WAND_PHYSICAL_DAMAGE);
	}

	@Override
	public float getWandCsc()
	{
		return character.getStat(Stat.WAND_CSC);
	}

	@Override
	public float getWandCsm()
	{
		return character.getStat(Stat.WAND_CSM);
	}

	@Override
	public float getWandAccuract()
	{
		return character.getStat(Stat.WAND_ACCURACT);
	}

	@Override
	public float getWandAttackSpeed()
	{
		return character.getStat(Stat.WAND_ATTACK_SPEED);
	}

	@Override
	public float getWandElemDamage()
	{
		return character.getStat(Stat.WAND_ELEM_DAMAGE);
	}

	@Override
	public float getWandPhysicalToLightning()
	{
		return character.getStat(Stat.WAND_PHYSICAL_TO_LIGHTNING);
	}

	@Override
	public float getWandPhysicalToCold()
	{
		return character.getStat(Stat.WAND_PHYSICAL_TO_COLD);
	}

	@Override
	public float getWandPhysicalToFire()
	{
		return character.getStat(Stat.WAND_PHYSICAL_TO_FIRE);
	}

	@Override
	public float getWandDamagePerPowerCharge()
	{
		return character.getStat(Stat.WAND_DAMAGE_PER_POWER_CHARGE);
	}

	@Override
	public float getDaggerPhysicalDamage()
	{
		return character.getStat(Stat.DAGGER_PHYSICAL_DAMAGE);
	}

	@Override
	public float getDaggerAccuracy()
	{
		return character.getStat(Stat.DAGGER_ACCURACY);
	}

	@Override
	public float getDaggerAttackSpeed()
	{
		return character.getStat(Stat.DAGGER_ATTACK_SPEED);
	}

	@Override
	public float getDaggerCsc()
	{
		return character.getStat(Stat.DAGGER_CSC);
	}

	@Override
	public float getDaggerPoisonOnCrit()
	{
		return character.getStat(Stat.DAGGER_POISON_ON_CRIT);
	}

	@Override
	public float getDaggerCsm()
	{
		return character.getStat(Stat.DAGGER_CSM);
	}

	@Override
	public float getAxePhysicalDamage()
	{
		return character.getStat(Stat.AXE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getAxeAttackSpeed()
	{
		return character.getStat(Stat.AXE_ATTACK_SPEED);
	}

	@Override
	public float getAxeAccuracy()
	{
		return character.getStat(Stat.AXE_ACCURACY);
	}

	@Override
	public float getSwordPhysicalDamage()
	{
		return character.getStat(Stat.SWORD_PHYSICAL_DAMAGE);
	}

	@Override
	public float getSwordAccuracy()
	{
		return character.getStat(Stat.SWORD_ACCURACY);
	}

	@Override
	public float getSwordAttackSpeed()
	{
		return character.getStat(Stat.SWORD_ATTACK_SPEED);
	}

	@Override
	public float getSwordCsc()
	{
		return character.getStat(Stat.SWORD_CSC);
	}

	@Override
	public float getSwordCsm()
	{
		return character.getStat(Stat.SWORD_CSM);
	}

	@Override
	public float getClawPhysicalDamage()
	{
		return character.getStat(Stat.CLAW_PHYSICAL_DAMAGE);
	}

	@Override
	public float getClawAttackSpeed()
	{
		return character.getStat(Stat.CLAW_ATTACK_SPEED);
	}

	@Override
	public float getClawCsc()
	{
		return character.getStat(Stat.CLAW_CSC);
	}

	@Override
	public float getClawCsm()
	{
		return character.getStat(Stat.CLAW_CSM);
	}

	@Override
	public float getClawAccuracy()
	{
		return character.getStat(Stat.CLAW_ACCURACY);
	}

	@Override
	public float getClawStealCharge()
	{
		return character.getStat(Stat.CLAW_STEAL_CHARGE);
	}

	@Override
	public float getClawLifeLeech()
	{
		return character.getStat(Stat.CLAW_LIFE_LEECH);
	}

	@Override
	public float getClawManaLeech()
	{
		return character.getStat(Stat.CLAW_MANA_LEECH);
	}

	@Override
	public float getMaceAttackSpeed()
	{
		return character.getStat(Stat.MACE_ATTACK_SPEED);
	}

	@Override
	public float getMaceAccuracy()
	{
		return character.getStat(Stat.MACE_ACCURACY);
	}

	@Override
	public float getMaceCsm()
	{
		return character.getStat(Stat.MACE_CSM);
	}

	@Override
	public float getMacePhysicalDamage()
	{
		return character.getStat(Stat.MACE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getMaceStunThreshold()
	{
		return character.getStat(Stat.MACE_STUN_THRESHOLD);
	}

	@Override
	public float getMaceElementalDamage()
	{
		return character.getStat(Stat.MACE_ELEMENTAL_DAMAGE);
	}

	@Override
	public float getMaceCsc()
	{
		return character.getStat(Stat.MACE_CSC);
	}

	@Override
	public float getBowKb()
	{
		return character.getStat(Stat.BOW_KB);
	}

	@Override
	public float getBowCsc()
	{
		return character.getStat(Stat.BOW_CSC);
	}

	@Override
	public float getBowCsm()
	{
		return character.getStat(Stat.BOW_CSM);
	}

	@Override
	public float getBowPhysical()
	{
		return character.getStat(Stat.BOW_PHYSICAL);
	}

	@Override
	public float getBowAccuracy()
	{
		return character.getStat(Stat.BOW_ACCURACY);
	}

	@Override
	public float getBowAttackSpeed()
	{
		return character.getStat(Stat.BOW_ATTACK_SPEED);
	}

	@Override
	public float getBowStunThreshhold()
	{
		return character.getStat(Stat.BOW_STUN_THRESHHOLD);
	}

	@Override
	public float getBowStunDuration()
	{
		return character.getStat(Stat.BOW_STUN_DURATION);
	}

	@Override
	public float getDualPhys()
	{
		return character.getStat(Stat.DUAL_PHYS);
	}

	@Override
	public float getDualCsc()
	{
		return character.getStat(Stat.DUAL_CSC);
	}

	@Override
	public float getDualBlock()
	{
		return character.getStat(Stat.DUAL_BLOCK);
	}

	@Override
	public float getDualAccuracy()
	{
		return character.getStat(Stat.DUAL_ACCURACY);
	}

	@Override
	public float getDualAttackSpeed()
	{
		return character.getStat(Stat.DUAL_ATTACK_SPEED);
	}

	@Override
	public float getDualMainDamage()
	{
		return character.getStat(Stat.DUAL_MAIN_DAMAGE);
	}

	@Override
	public float getDualOffHandDamage()
	{
		return character.getStat(Stat.DUAL_OFF_HAND_DAMAGE);
	}

	@Override
	public float getDualCastSpeed()
	{
		return character.getStat(Stat.DUAL_CAST_SPEED);
	}

	@Override
	public float getShieldAttackSpeed()
	{
		return character.getStat(Stat.SHIELD_ATTACK_SPEED);
	}

	@Override
	public float getShieldPhysicalDamage()
	{
		return character.getStat(Stat.SHIELD_PHYSICAL_DAMAGE);
	}

	@Override
	public float getShieldMeleePhysicalDamage()
	{
		return character.getStat(Stat.SHIELD_MELEE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getAccuracy()
	{
		return character.getStat(Stat.ACCURACY);
	}

	@Override
	public float getAccPlus()
	{
		return character.getStat(Stat.ACC_PLUS);
	}

	@Override
	public float getEvasion()
	{
		return character.getStat(Stat.EVASION);
	}

	@Override
	public float getEvasionArmor()
	{
		return character.getStat(Stat.EVASION_ARMOR);
	}

	@Override
	public float getEvaRat()
	{
		return character.getStat(Stat.EVA_RAT);
	}

	@Override
	public float getEvadeProjectile()
	{
		return character.getStat(Stat.EVADE_PROJECTILE);
	}

	@Override
	public float getEvadeMelee()
	{
		return character.getStat(Stat.EVADE_MELEE);
	}

	@Override
	public float getEvadeNever()
	{
		return character.getStat(Stat.EVADE_NEVER);
	}

	@Override
	public float getArmour()
	{
		return character.getStat(Stat.ARMOUR);
	}

	@Override
	public float getArmorFlat()
	{
		return character.getStat(Stat.ARMOUR_FLAT);
	}

	@Override
	public float getArmorEvade()
	{
		return character.getStat(Stat.ARMOR_EVADE);
	}

	@Override
	public float getMovementSpeed()
	{
		return character.getStat(Stat.MOVEMENT_SPEED);
	}

	@Override
	public float getMovementEnergyShield()
	{
		return character.getStat(Stat.MOVEMENT_ENERGY_SHIELD);
	}

	@Override
	public float getMovementIgnoreArmor()
	{
		return character.getStat(Stat.MOVEMENT_IGNORE_ARMOR);
	}

	@Override
	public float getRegen()
	{
		return character.getStat(Stat.REGEN);
	}

	@Override
	public float getBlockChance()
	{
		return character.getStat(Stat.BLOCK_CHANCE);
	}

	@Override
	public float getShieldDefence()
	{
		return character.getStat(Stat.SHIELD_DEFENCE);
	}

	@Override
	public float getShieldBlockChance()
	{
		return character.getStat(Stat.SHIELD_BLOCK_CHANCE);
	}

	@Override
	public float getShieldElementalResist()
	{
		return character.getStat(Stat.SHIELD_ELEMENTAL_RESIST);
	}

	@Override
	public float getShieldDualBlock()
	{
		return character.getStat(Stat.SHIELD_DUAL_BLOCK);
	}

	@Override
	public float getLightningResist()
	{
		return character.getStat(Stat.LIGHTNING_RESIST);
	}

	@Override
	public float getLightningResistMax()
	{
		return character.getStat(Stat.LIGHTNING_RESIST_MAX);
	}

	@Override
	public float getMaxLight()
	{
		return character.getStat(Stat.MAX_LIGHT);
	}

	@Override
	public float getElementalResist()
	{
		return character.getStat(Stat.ELEMENTAL_RESIST);
	}

	@Override
	public float getFireResist()
	{
		return character.getStat(Stat.FIRE_RESIST);
	}

	@Override
	public float getFireResistMax()
	{
		return character.getStat(Stat.FIRE_RESIST_MAX);
	}

	@Override
	public float getColdResist()
	{
		return character.getStat(Stat.COLD_RESIST);
	}

	@Override
	public float getColdResistCap()
	{
		return character.getStat(Stat.COLD_RESIST_CAP);
	}

	@Override
	public float getChaosResist()
	{
		return character.getStat(Stat.CHAOS_RESIST);
	}

	@Override
	public float getResistAll()
	{
		return character.getStat(Stat.RESIST_ALL);
	}

	@Override
	public float getFreeze()
	{
		return character.getStat(Stat.FREEZE);
	}

	@Override
	public float getCold()
	{
		return character.getStat(Stat.COLD);
	}

	@Override
	public float getColdPen()
	{
		return character.getStat(Stat.COLD_PEN);
	}

	@Override
	public float getPhysicalDamageReduction()
	{
		return character.getStat(Stat.PHYSICAL_DAMAGE_REDUCTION);
	}

	@Override
	public float getElementalDamageReductionOnConsecrate()
	{
		return character.getStat(Stat.ELEMENTAL_DAMAGE_REDUCTION_ON_CONSECRATE);
	}

	@Override
	public float getCriticalReduce()
	{
		return character.getStat(Stat.CRITICAL_REDUCE);
	}

	@Override
	public float getChillAvoid()
	{
		return character.getStat(Stat.CHILL_AVOID);
	}

	@Override
	public float getFrozenAvoid()
	{
		return character.getStat(Stat.FROZEN_AVOID);
	}

	@Override
	public float getIgniteAvoid()
	{
		return character.getStat(Stat.IGNITE_AVOID);
	}

	@Override
	public float getShockAvoid()
	{
		return character.getStat(Stat.SHOCK_AVOID);
	}

	@Override
	public float getAvoidStunOnCast()
	{
		return character.getStat(Stat.AVOID_STUN_ON_CAST);
	}

	@Override
	public float getChillDuration()
	{
		return character.getStat(Stat.CHILL_DURATION);
	}

	@Override
	public float getFreezeDuration()
	{
		return character.getStat(Stat.FREEZE_DURATION);
	}

	@Override
	public float getBlockRecovery()
	{
		return character.getStat(Stat.BLOCK_RECOVERY);
	}

	@Override
	public float getManaOnBlock()
	{
		return character.getStat(Stat.MANA_ON_BLOCK);
	}

	@Override
	public float getCastSpeed()
	{
		return character.getStat(Stat.CAST_SPEED);
	}

	@Override
	public float getCastSpeedChaos()
	{
		return character.getStat(Stat.CAST_SPEED_CHAOS);
	}

	@Override
	public float getSpellDamage()
	{
		return character.getStat(Stat.SPELL_DAMAGE);
	}

	@Override
	public float getSpellCsm()
	{
		return character.getStat(Stat.SPELL_CSM);
	}

	@Override
	public float getSpellCsc()
	{
		return character.getStat(Stat.SPELL_CSC);
	}

	@Override
	public float getPenFire()
	{
		return character.getStat(Stat.PEN_FIRE);
	}

	@Override
	public float getWeapPenFire()
	{
		return character.getStat(Stat.WEAP_PEN_FIRE);
	}

	@Override
	public float getLightPen()
	{
		return character.getStat(Stat.LIGHT_PEN);
	}

	@Override
	public float getElemPen()
	{
		return character.getStat(Stat.ELEM_PEN);
	}

	@Override
	public float getIgDur()
	{
		return character.getStat(Stat.IG_DUR);
	}

	@Override
	public float getIgChance()
	{
		return character.getStat(Stat.IG_CHANCE);
	}

	@Override
	public float getLifeLeech()
	{
		return character.getStat(Stat.LIFE_LEECH);
	}

	@Override
	public float getLifeLeechInstant()
	{
		return character.getStat(Stat.LIFE_LEECH_INSTANT);
	}

	@Override
	public float getLifeLeechPhysical()
	{
		return character.getStat(Stat.LIFE_LEECH_PHYSICAL);
	}

	@Override
	public float getLeechRate()
	{
		return character.getStat(Stat.LEECH_RATE);
	}

	@Override
	public float getLeechPerSec()
	{
		return character.getStat(Stat.LEECH_PER_SEC);
	}

	@Override
	public float getLeechStun()
	{
		return character.getStat(Stat.LEECH_STUN);
	}

	@Override
	public float getLeechDamage()
	{
		return character.getStat(Stat.LEECH_DAMAGE);
	}

	@Override
	public float getPhysLife()
	{
		return character.getStat(Stat.PHYS_LIFE);
	}

	@Override
	public float getImmuneBleed()
	{
		return character.getStat(Stat.IMMUNE_BLEED);
	}

	@Override
	public float getKillWeak()
	{
		return character.getStat(Stat.KILL_WEAK);
	}

	@Override
	public float getOverkill()
	{
		return character.getStat(Stat.OVERKILL);
	}

	@Override
	public float getFillLeech()
	{
		return character.getStat(Stat.FILL_LEECH);
	}

	@Override
	public float getOnslaughtOnRareOrUnique()
	{
		return character.getStat(Stat.ONSLAUGHT_ON_RARE_OR_UNIQUE);
	}

	@Override
	public float getOnslaughtOnKillKill()
	{
		return character.getStat(Stat.ONSLAUGHT_ON_KILL_KILL);
	}

	@Override
	public float getOnslaughtOnFullFrenzy()
	{
		return character.getStat(Stat.ONSLAUGHT_ON_FULL_FRENZY);
	}

	@Override
	public float getEvasionOnOnslaught()
	{
		return character.getStat(Stat.EVASION_ON_ONSLAUGHT);
	}

	@Override
	public float getAoeRadius()
	{
		return character.getStat(Stat.AOE_RADIUS);
	}

	@Override
	public float getAoeDamage()
	{
		return character.getStat(Stat.AOE_DAMAGE);
	}

	@Override
	public float getStunDuration()
	{
		return character.getStat(Stat.STUN_DURATION);
	}

	@Override
	public float getStunOnEnemyFullLife()
	{
		return character.getStat(Stat.STUN_ON_ENEMY_FULL_LIFE);
	}

	@Override
	public float getStunDurationOnEnemyFullLife()
	{
		return character.getStat(Stat.STUN_DURATION_ON_ENEMY_FULL_LIFE);
	}

	@Override
	public float getStunDurationOnEnemyLowLife()
	{
		return character.getStat(Stat.STUN_DURATION_ON_ENEMY_LOW_LIFE);
	}

	@Override
	public float getStunDurationDouble()
	{
		return character.getStat(Stat.STUN_DURATION_DOUBLE);
	}

	@Override
	public float getStunAvoid()
	{
		return character.getStat(Stat.STUN_AVOID);
	}

	@Override
	public float getStunNever()
	{
		return character.getStat(Stat.STUN_NEVER);
	}

	@Override
	public float getStunAndBlockRecovery()
	{
		return character.getStat(Stat.STUN_AND_BLOCK_RECOVERY);
	}

	@Override
	public float getStunThreshhold()
	{
		return character.getStat(Stat.STUN_THRESHHOLD);
	}

	@Override
	public float getReflectReduce()
	{
		return character.getStat(Stat.REFLECT_REDUCE);
	}

	@Override
	public float getRecentKillDamage()
	{
		return character.getStat(Stat.RECENT_KILL_DAMAGE);
	}

	@Override
	public float getSingleSplash()
	{
		return character.getStat(Stat.SINGLE_SPLASH);
	}

	@Override
	public float getSplashLess()
	{
		return character.getStat(Stat.SPLASH_LESS);
	}

	@Override
	public float getBlockSpells()
	{
		return character.getStat(Stat.BLOCK_SPELLS);
	}

	@Override
	public float getBlockSpellShield()
	{
		return character.getStat(Stat.BLOCK_SPELL_SHIELD);
	}

	@Override
	public float getManaLeech()
	{
		return character.getStat(Stat.MANA_LEECH);
	}

	@Override
	public float getAdMana()
	{
		return character.getStat(Stat.AD_MANA);
	}

	@Override
	public float getManaLeechRate()
	{
		return character.getStat(Stat.MANA_LEECH_RATE);
	}

	@Override
	public float getMinionInstability()
	{
		return character.getStat(Stat.MINION_INSTABILITY);
	}

	@Override
	public float getMinionAttackSpeed()
	{
		return character.getStat(Stat.MINION_ATTACK_SPEED);
	}

	@Override
	public float getMinionCastSpeed()
	{
		return character.getStat(Stat.MINION_CAST_SPEED);
	}

	@Override
	public float getMinionShield()
	{
		return character.getStat(Stat.MINION_SHIELD);
	}

	@Override
	public float getMinionBlock()
	{
		return character.getStat(Stat.MINION_BLOCK);
	}

	@Override
	public float getMinionBlockHeal()
	{
		return character.getStat(Stat.MINION_BLOCK_HEAL);
	}

	@Override
	public float getMinionDamage()
	{
		return character.getStat(Stat.MINION_DAMAGE);
	}

	@Override
	public float getMinionLifeLeech()
	{
		return character.getStat(Stat.MINION_LL);
	}

	@Override
	public float getMinionLifeRegeneration()
	{
		return character.getStat(Stat.MINION_REGEN);
	}

	@Override
	public float getMinionElementalResist()
	{
		return character.getStat(Stat.MINION_ELEM_RESIST);
	}

	@Override
	public float getMinionChaosResist()
	{
		return character.getStat(Stat.MINION_CHAOS_RESIST);
	}

	@Override
	public float getIncreasedMinionLife()
	{
		return character.getStat(Stat.MINION_MAXIMUM_LIFE);
	}

	@Override
	public float getZombies()
	{
		return character.getStat(Stat.ZOMBIES);
	}

	@Override
	public float getSkel()
	{
		return character.getStat(Stat.SKEL);
	}

	@Override
	public float getSkelMax()
	{
		return character.getStat(Stat.SKEL_MAX);
	}

	@Override
	public float getSpecMax()
	{
		return character.getStat(Stat.SPEC_MAX);
	}

	@Override
	public float getAncBond()
	{
		return character.getStat(Stat.ANC_BOND);
	}

	@Override
	public float getTotemAdd()
	{
		return character.getStat(Stat.TOTEM_ADD);
	}

	@Override
	public float getTotemResistElemental()
	{
		return character.getStat(Stat.TOTEM_RESIST_ELEMENTAL);
	}

	@Override
	public float getKnockbackOnHit()
	{
		return character.getStat(Stat.KNOCKBACK_ON_HIT);
	}

	@Override
	public float getKnockbackDistance()
	{
		return character.getStat(Stat.KNOCKBACK_DISTANCE);
	}

	@Override
	public float getBleed()
	{
		return character.getStat(Stat.BLEED);
	}

	@Override
	public float getBleedDamageOnKill()
	{
		return character.getStat(Stat.BLEED_DAMAGE_ON_KILL);
	}

	@Override
	public float getBleedDmg()
	{
		return character.getStat(Stat.BLEED_DMG);
	}

	@Override
	public float getBleedMelee()
	{
		return character.getStat(Stat.BLEED_MELEE);
	}

	@Override
	public float getBleedPoison()
	{
		return character.getStat(Stat.BLEED_POISON);
	}

	@Override
	public float getBleedCsm()
	{
		return character.getStat(Stat.BLEED_CSM);
	}

	@Override
	public float getBleedCsc()
	{
		return character.getStat(Stat.BLEED_CSC);
	}

	@Override
	public float getPoisonDamage()
	{
		return character.getStat(Stat.POISON_DAMAGE);
	}

	@Override
	public float getPoisonOnHit()
	{
		return character.getStat(Stat.POISON_ON_HIT);
	}

	@Override
	public float getMaxEnergyShield()
	{
		return character.getStat(Stat.MAX_ENERGY_SHIELD);
	}

	@Override
	public float getIncreasedEnergyShield()
	{
		return character.getStat(Stat.INCREASED_ENERGY_SHIELD);
	}

	@Override
	public float getEnergyShieldPct2()
	{
		return character.getStat(Stat.ENERGY_SHIELD_PCT_2);
	}

	@Override
	public float getEnergyShieldMana()
	{
		return character.getStat(Stat.ENERGY_SHIELD_MANA);
	}

	@Override
	public float getEnergyShieldMana2()
	{
		return character.getStat(Stat.ENERGY_SHIELD_MANA_2);
	}

	@Override
	public float getEnergyShieldEvade()
	{
		return character.getStat(Stat.ENERGY_SHIELD_EVADE);
	}

	@Override
	public float getEnergyShieldMoreDamage()
	{
		return character.getStat(Stat.ENERGY_SHIELD_MORE_DAMAGE);
	}

	@Override
	public float getEnergyShieldRecharg()
	{
		return character.getStat(Stat.ENERGY_SHIELD_RECHARG);
	}

	@Override
	public float getEnergyShieldShield()
	{
		return character.getStat(Stat.ENERGY_SHIELD_SHIELD);
	}

	@Override
	public float getEnergyShieldLifeRegen()
	{
		return character.getStat(Stat.ENERGY_SHIELD_LIFE_REGEN);
	}

	@Override
	public float getEnergyShieldLifeLeech()
	{
		return character.getStat(Stat.ENERGY_SHIELD_LIFE_LEECH);
	}

	@Override
	public float getEnergyShieldBeforeMana()
	{
		return character.getStat(Stat.ENERGY_SHIELD_BEFORE_MANA);
	}

	@Override
	public float getEnergyShieldProtectMana()
	{
		return character.getStat(Stat.ENERGY_SHIELD_PROTECT_MANA);
	}

	@Override
	public float getChain()
	{
		return character.getStat(Stat.CHAIN);
	}

	@Override
	public float getNoBleed()
	{
		return character.getStat(Stat.NO_BLEED);
	}

	@Override
	public float getCsc()
	{
		return character.getStat(Stat.CSC);
	}

	@Override
	public float getEsFaster()
	{
		return character.getStat(Stat.ES_FASTER);
	}

	@Override
	public float getDodgeAttack()
	{
		return character.getStat(Stat.DODGE_ATTACK);
	}

	@Override
	public float getDodgeAttack2()
	{
		return character.getStat(Stat.DODGE_ATTACK2);
	}

	@Override
	public float getDodgeSpell()
	{
		return character.getStat(Stat.DODGE_SPELL);
	}

	@Override
	public float getDodgeAcrobatics()
	{
		return character.getStat(Stat.DODGE_ACROBATICS);
	}

	@Override
	public float getAuraRad()
	{
		return character.getStat(Stat.AURA_RAD);
	}

	@Override
	public float getNonCurseAuraEffect()
	{
		return character.getStat(Stat.NON_CURSE_AURA_EFFECT);
	}

	@Override
	public float getCurseEffect()
	{
		return character.getStat(Stat.CURSE_EFFECT);
	}

	@Override
	public float getAdditionalCurse()
	{
		return character.getStat(Stat.CURSE_NUM);
	}

	@Override
	public float getCurseDuration()
	{
		return character.getStat(Stat.CURSE_DUR);
	}

	@Override
	public float getCurseCastSpeed()
	{
		return character.getStat(Stat.CURSE_CAST_SPEED);
	}

	@Override
	public float getCurseRadius()
	{
		return character.getStat(Stat.CURSE_RADIUS);
	}

	@Override
	public float getFrenzCharg()
	{
		return character.getStat(Stat.FRENZ_CHARG);
	}

	@Override
	public float getFrChDur()
	{
		return character.getStat(Stat.FR_CH_DUR);
	}

	@Override
	public float getFrenzyEvasion()
	{
		return character.getStat(Stat.FRENZY_EVASION);
	}

	@Override
	public float getFrenzKill()
	{
		return character.getStat(Stat.FRENZ_KILL);
	}

	@Override
	public float getEndDur()
	{
		return character.getStat(Stat.END_DUR);
	}

	@Override
	public float getEnduranceChargeLifeRegen()
	{
		return character.getStat(Stat.ENDURANCE_CHARGE_LIFE_REGEN);
	}

	@Override
	public float getMaxEnd()
	{
		return character.getStat(Stat.MAX_END);
	}

	@Override
	public float getChargeOnKill()
	{
		return character.getStat(Stat.CHARGE_ON_KILL);
	}

	@Override
	public float getPowerChargeDuration()
	{
		return character.getStat(Stat.POWER_CHARGE_DURATION);
	}

	@Override
	public float getPowerChargeAdditional()
	{
		return character.getStat(Stat.POWER_CHARGE_ADDITIONAL);
	}

	@Override
	public float getChargeShare()
	{
		return character.getStat(Stat.CHARGE_SHARE);
	}

	@Override
	public float getEnduranceChargeMax()
	{
		return character.getStat(Stat.ENDURANCE_CHARGE_MAX);
	}

	@Override
	public float getEnduranceChargeOnMeleeCritical()
	{
		return character.getStat(Stat.ENDURANCE_CHARGE_ON_MELEE_CRITICAL);
	}

	@Override
	public float getPowerChargeOnBlock()
	{
		return character.getStat(Stat.POWER_CHARGE_ON_BLOCK);
	}

	@Override
	public float getTrapRed()
	{
		return character.getStat(Stat.TRAP_RED);
	}

	@Override
	public float getMeleeFort()
	{
		return character.getStat(Stat.MELEE_FORT);
	}

	@Override
	public float getIncFort()
	{
		return character.getStat(Stat.INC_FORT);
	}

	@Override
	public float getFortDur()
	{
		return character.getStat(Stat.FORT_DUR);
	}

	@Override
	public float getFortMelee()
	{
		return character.getStat(Stat.FORT_MELEE);
	}

	@Override
	public float getFortifyArmour()
	{
		return character.getStat(Stat.FORTIFY_ARMOUR);
	}

	@Override
	public float getFortAlly()
	{
		return character.getStat(Stat.FORT_ALLY);
	}

	@Override
	public float getTauntHit()
	{
		return character.getStat(Stat.TAUNT_HIT);
	}

	@Override
	public float getTauntReduce()
	{
		return character.getStat(Stat.TAUNT_REDUCE);
	}

	@Override
	public float getLightRadiusEnergyShield()
	{
		return character.getStat(Stat.LIGHT_RADIUS_ENERGY_SHIELD);
	}

	@Override
	public float getLightRadius()
	{
		return character.getStat(Stat.LIGHT_RADIUS);
	}

	@Override
	public float getStunCull()
	{
		return character.getStat(Stat.STUN_CULL);
	}

	@Override
	public float getAttackCastSpeed()
	{
		return character.getStat(Stat.ATTACK_CAST_SPEED);
	}

	@Override
	public float getAtkCast()
	{
		return character.getStat(Stat.ATK_CAST);
	}

	@Override
	public float getAtckCastMove()
	{
		return character.getStat(Stat.ATCK_CAST_MOVE);
	}

	@Override
	public float getLifeManaRegen()
	{
		return character.getStat(Stat.LIFE_MANA_REGEN);
	}

	@Override
	public float getAvoidElem()
	{
		return character.getStat(Stat.AVOID_ELEM);
	}

	@Override
	public float getFreezeShockIgnite()
	{
		return character.getStat(Stat.FREEZE_SHOCK_IGNITE);
	}

	@Override
	public float getNeverEvaded()
	{
		return character.getStat(Stat.NEVER_EVADED);
	}

	@Override
	public float getNeverCrit()
	{
		return character.getStat(Stat.NEVER_CRIT);
	}

	@Override
	public float getEnemyNeverLeech()
	{
		return character.getStat(Stat.ENEMY_NEVER_LEECH);
	}

	@Override
	public float getManaBeforeLife()
	{
		return character.getStat(Stat.MIND_OVER_MATTER);
	}

	@Override
	public float getEnemyElemResist()
	{
		return character.getStat(Stat.ENEMY_ELEM_RESIST);
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
}
