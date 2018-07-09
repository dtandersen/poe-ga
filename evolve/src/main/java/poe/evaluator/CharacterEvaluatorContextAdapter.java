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
		return getStat(Stat.ADDED_LIFE);
	}

	@Override
	public float getIncreasedMaximumLife()
	{
		return getStat(Stat.INCREASED_LIFE);
	}

	@Override
	public float getLife1()
	{
		return getStat(Stat.CHAOS_INNOCULATION);
	}

	@Override
	public float getLifeOnKill()
	{
		return getStat(Stat.LIFE_ON_KILL);
	}

	@Override
	public float getLifeOnHit()
	{
		return getStat(Stat.LIFE_ON_HIT);
	}

	@Override
	public float getFlaskLife()
	{
		return getStat(Stat.FLASK_LIFE);
	}

	@Override
	public float getFlaskRecovery()
	{
		return getStat(Stat.FLASK_RECOVERY);
	}

	@Override
	public float getFlaskExtra()
	{
		return getStat(Stat.FLASK_EXTRA);
	}

	@Override
	public float getFlaskDmg()
	{
		return getStat(Stat.FLASK_DMG);
	}

	@Override
	public float getFlaskDuration()
	{
		return getStat(Stat.FLASK_DURATION);
	}

	@Override
	public float getFlaskIncreasedEffect()
	{
		return getStat(Stat.FLASK_INCREASED_EFFECT);
	}

	@Override
	public float getStrength()
	{
		return getStat(Stat.STRENGTH);
	}

	@Override
	public float getDexterity()
	{
		return getStat(Stat.DEXTERITY);
	}

	@Override
	public float getIntelligence()
	{
		return getStat(Stat.INTELLIGENCE);
	}

	@Override
	public float getMaxMana()
	{
		return getStat(Stat.ADDED_MANA);
	}

	@Override
	public float getIncreasedMaxMana()
	{
		return getStat(Stat.INCREASED_MANA);
	}

	@Override
	public float getReducedManaCost()
	{
		return getStat(Stat.MANA_COST_REDUCED);
	}

	@Override
	public float getManaRegenerationRate()
	{
		return getStat(Stat.MANA_REGEN);
	}

	@Override
	public float getManaReserved()
	{
		return getStat(Stat.MANA_RESERVED);
	}

	@Override
	public float getManaMoveRegen()
	{
		return getStat(Stat.MANA_MOVE_REGEN);
	}

	@Override
	public float getManaReserveReduce()
	{
		return getStat(Stat.MANA_RESERVE_REDUCE);
	}

	@Override
	public float getManaFlask()
	{
		return getStat(Stat.MANA_FLASK);
	}

	@Override
	public float getManaLeechPhysical()
	{
		return getStat(Stat.MANA_LEECH_PHYSICAL);
	}

	@Override
	public float getManaOnKill()
	{
		return getStat(Stat.MANA_ON_KILL);
	}

	@Override
	public float getManaOnHit()
	{
		return getStat(Stat.MANA_ON_HIT);
	}

	@Override
	public float getManaToLife()
	{
		return getStat(Stat.MANA_TO_LIFE);
	}

	@Override
	public float getManaGainOnHit()
	{
		return getStat(Stat.MANA_GAIN_ON_HIT);
	}

	@Override
	public float getCsm()
	{
		return getStat(Stat.CSM);
	}

	@Override
	public float getCsmNever()
	{
		return getStat(Stat.CSM_NEVER);
	}

	@Override
	public float getFlaskGain()
	{
		return getStat(Stat.FLASK_GAIN);
	}

	@Override
	public float getAvoidFreeze()
	{
		return getStat(Stat.AVOID_FREEZE);
	}

	@Override
	public float getElemFlask()
	{
		return getStat(Stat.ELEM_FLASK);
	}

	@Override
	public float getAttackSpeed()
	{
		return getStat(Stat.ATTACK_SPEED);
	}

	@Override
	public float getStunThresh()
	{
		return getStat(Stat.STUN_THRESH);
	}

	@Override
	public float getDamageAttack()
	{
		return getStat(Stat.DAMAGE_ATTACK);
	}

	@Override
	public float getDamageFrozenShockIgnited()
	{
		return getStat(Stat.DAMAGE_FROZEN_SHOCK_IGNITED);
	}

	@Override
	public float getDamageOnEnemyLowLife()
	{
		return getStat(Stat.DAMAGE_ON_ENEMY_LOW_LIFE);
	}

	@Override
	public float getPhysicalDamage()
	{
		return getStat(Stat.PHYSICAL_DAMAGE);
	}

	@Override
	public float getPhysicalDamage2()
	{
		return getStat(Stat.PHYSICAL_DAMAGE_2);
	}

	@Override
	public float getPhysicalDot()
	{
		return getStat(Stat.PHYSICAL_DOT);
	}

	@Override
	public float getChaosDamage()
	{
		return getStat(Stat.CHAOS_DAMAGE);
	}

	@Override
	public float getChaosDamageOnPhysical()
	{
		return getStat(Stat.CHAOS_DAMAGE_ON_PHYSICAL);
	}

	@Override
	public float getElementalDamage()
	{
		return getStat(Stat.ELEM_DAMAGE);
	}

	@Override
	public float getElementalStatusAilments()
	{
		return getStat(Stat.ELEMENTAL_STATUS_AILMENTS);
	}

	@Override
	public float getElementalDamageOnCrit()
	{
		return getStat(Stat.ELEMENTAL_DAMAGE_ON_CRIT);
	}

	@Override
	public float getCscElemStatus()
	{
		return getStat(Stat.CSC_ELEM_STATUS);
	}

	@Override
	public float getFireDamage()
	{
		return getStat(Stat.FIRE_DAMAGE);
	}

	@Override
	public float getFireDamageConvert()
	{
		return getStat(Stat.FIRE_DAMAGE_CONVERT);
	}

	@Override
	public float getBurnDamage()
	{
		return getStat(Stat.BURN_DAMAGE);
	}

	@Override
	public float getPhysicalDamageAsFireDamage()
	{
		return getStat(Stat.PHYSICAL_DAMAGE_AS_FIRE_DAMAGE);
	}

	@Override
	public float getFireDamageOnly()
	{
		return getStat(Stat.FIRE_DAMAGE_ONLY);
	}

	@Override
	public float getLightningDamage()
	{
		return getStat(Stat.LIGHTNING_DAMAGE);
	}

	@Override
	public float getDotDamage()
	{
		return getStat(Stat.DOT_DAMAGE);
	}

	@Override
	public float getSpellDamageOnLowLife()
	{
		return getStat(Stat.SPELL_DAMAGE_ON_LOW_LIFE);
	}

	@Override
	public float getSpellDamagePerPowerCharge()
	{
		return getStat(Stat.SPELL_DAMAGE_PER_POWER_CHARGE);
	}

	@Override
	public float getTrapDamage()
	{
		return getStat(Stat.TRAP_DAMAGE);
	}

	@Override
	public float getTrapRecovery()
	{
		return getStat(Stat.TRAP_RECOVERY);
	}

	@Override
	public float getTrapCsm()
	{
		return getStat(Stat.TRAP_CSM);
	}

	@Override
	public float getTrapCsc()
	{
		return getStat(Stat.TRAP_CSC);
	}

	@Override
	public float getTrapPowerCharge()
	{
		return getStat(Stat.TRAP_POWER_CHARGE);
	}

	@Override
	public float getTrapRadius()
	{
		return getStat(Stat.TRAP_RADIUS);
	}

	@Override
	public float getTrapSpeed()
	{
		return getStat(Stat.TRAP_SPEED);
	}

	@Override
	public float getTrapInvuln()
	{
		return getStat(Stat.TRAP_INVULN);
	}

	@Override
	public float getTrapElemPen()
	{
		return getStat(Stat.TRAP_ELEM_PEN);
	}

	@Override
	public float getTrapAdditional()
	{
		return getStat(Stat.TRAP_ADDITIONAL);
	}

	@Override
	public float getTrapFrenzyCharge()
	{
		return getStat(Stat.TRAP_FRENZY_CHARGE);
	}

	@Override
	public float getMineCsm()
	{
		return getStat(Stat.MINE_CSM);
	}

	@Override
	public float getMineCsc()
	{
		return getStat(Stat.MINE_CSC);
	}

	@Override
	public float getMinePowerCharge()
	{
		return getStat(Stat.MINE_POWER_CHARGE);
	}

	@Override
	public float getMineDamage()
	{
		return getStat(Stat.MINE_DAMAGE);
	}

	@Override
	public float getMineSpeed()
	{
		return getStat(Stat.MINE_SPEED);
	}

	@Override
	public float getMineDuration()
	{
		return getStat(Stat.MINE_DURATION);
	}

	@Override
	public float getMineAdditional()
	{
		return getStat(Stat.MINE_ADDITIONAL);
	}

	@Override
	public float getMineInstant()
	{
		return getStat(Stat.MINE_INSTANT);
	}

	@Override
	public float getMineInvuln()
	{
		return getStat(Stat.MINE_INVULN);
	}

	@Override
	public float getMineRadius()
	{
		return getStat(Stat.MINE_RADIUS);
	}

	@Override
	public float getMineElemPen()
	{
		return getStat(Stat.MINE_ELEM_PEN);
	}

	@Override
	public float getChillOnUnfreeze()
	{
		return getStat(Stat.CHILL_ON_UNFREEZE);
	}

	@Override
	public float getFreezeOnChill()
	{
		return getStat(Stat.FREEZE_ON_CHILL);
	}

	@Override
	public float getShockDuration()
	{
		return getStat(Stat.SHOCK_DURATION);
	}

	@Override
	public float getShockChance()
	{
		return getStat(Stat.SHOCK_CHANCE);
	}

	@Override
	public float getSkillDur()
	{
		return getStat(Stat.SKILL_EFFECT_DURATION);
	}

	@Override
	public float getTotemDamage()
	{
		return getStat(Stat.TOTEM_DAMAGE);
	}

	@Override
	public float getTotemPlacementSpeed()
	{
		return getStat(Stat.TOTEM_PLACEMENT_SPEED);
	}

	@Override
	public float getTotemLife()
	{
		return getStat(Stat.TOTEM_LIFE);
	}

	@Override
	public float getTotemHelm()
	{
		return getStat(Stat.TOTEM_HELM);
	}

	@Override
	public float getTotemRes()
	{
		return getStat(Stat.TOTEM_RES);
	}

	@Override
	public float getTotemCharge()
	{
		return getStat(Stat.TOTEM_CHARGE);
	}

	@Override
	public float getTotemDuration()
	{
		return getStat(Stat.TOTEM_DURATION);
	}

	@Override
	public float getTotemCastSpeed()
	{
		return getStat(Stat.TOTEM_CAST_SPEED);
	}

	@Override
	public float getTotemAttackSpeed()
	{
		return getStat(Stat.TOTEM_ATTACK_SPEED);
	}

	@Override
	public float getTotemCsc()
	{
		return getStat(Stat.TOTEM_CSC);
	}

	@Override
	public float getTotemCsm()
	{
		return getStat(Stat.TOTEM_CSM);
	}

	@Override
	public float getTotalGlobalCsc()
	{
		return getStat(Stat.TOTAL_GLOBAL_CSC);
	}

	@Override
	public float getProjDamage()
	{
		return getStat(Stat.PROJ_DAMAGE);
	}

	@Override
	public float getProjSpeed()
	{
		return getStat(Stat.PROJ_SPEED);
	}

	@Override
	public float getProjFar()
	{
		return getStat(Stat.PROJ_FAR);
	}

	@Override
	public float getProjSkill()
	{
		return getStat(Stat.PROJ_SKILL);
	}

	@Override
	public float getProjPierce()
	{
		return getStat(Stat.PROJ_PIERCE);
	}

	@Override
	public float getProjPointBlank()
	{
		return getStat(Stat.PROJ_POINT_BLANK);
	}

	@Override
	public float getProjStr()
	{
		return getStat(Stat.PROJ_STR);
	}

	@Override
	public float getArrowSpeed()
	{
		return getStat(Stat.ARROW_SPEED);
	}

	@Override
	public float getWeaponElementalDamage()
	{
		return getStat(Stat.WEAPON_ELEMENTAL_DAMAGE);
	}

	@Override
	public float getWeaponFireDamage()
	{
		return getStat(Stat.WEAPON_FIRE_DAMAGE);
	}

	@Override
	public float getWeaponColdDamage()
	{
		return getStat(Stat.WEAPON_COLD_DAMAGE);
	}

	@Override
	public float getWeaponLightningDamage()
	{
		return getStat(Stat.WEAPON_LIGHTNING_DAMAGE);
	}

	@Override
	public float getWeaponPenCold()
	{
		return getStat(Stat.WEAPON_PEN_COLD);
	}

	@Override
	public float getWeaponPenLightning()
	{
		return getStat(Stat.WEAPON_PEN_LIGHTNING);
	}

	@Override
	public float getWeaponPenElem()
	{
		return getStat(Stat.WEAPON_PEN_ELEM);
	}

	@Override
	public float getMeleeDmg()
	{
		return getStat(Stat.MELEE_DMG);
	}

	@Override
	public float getMeleePhysicalDamage()
	{
		return getStat(Stat.MELEE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getMeleeAttackSpeed()
	{
		return getStat(Stat.MELEE_ATTACK_SPEED);
	}

	@Override
	public float getMeleeCsc()
	{
		return getStat(Stat.MELEE_CSC);
	}

	@Override
	public float getMeleeCsm()
	{
		return getStat(Stat.MELEE_CSM);
	}

	@Override
	public float getMeleeRange()
	{
		return getStat(Stat.MELEE_RANGE);
	}

	@Override
	public float getOneHandedPhysicalDamage()
	{
		return getStat(Stat.ONE_HANDED_PHYSICAL_DAMAGE);
	}

	@Override
	public float getOneHandedAttackSpeed()
	{
		return getStat(Stat.ONE_HANDED_ATTACK_SPEED);
	}

	@Override
	public float getOneHandedAccuracy()
	{
		return getStat(Stat.ONE_HANDED_ACCURACY);
	}

	@Override
	public float getOneHandedCsc()
	{
		return getStat(Stat.ONE_HANDED_CSC);
	}

	@Override
	public float getTwoHandedPhysicalDamage()
	{
		return getStat(Stat.TWO_HANDED_PHYSICAL_DAMAGE);
	}

	@Override
	public float getTwoHandedAccuracy()
	{
		return getStat(Stat.TWO_HANDED_ACCURACY);
	}

	@Override
	public float getTwoHandedAttackSpeed()
	{
		return getStat(Stat.TWO_HANDED_ATTACK_SPEED);
	}

	@Override
	public float getTwoHandedStunDuration()
	{
		return getStat(Stat.TWO_HANDED_STUN_DURATION);
	}

	@Override
	public float getTwoHandedDamage()
	{
		return getStat(Stat.TWO_HANDED_DAMAGE);
	}

	@Override
	public float getStavePhysicalDamage()
	{
		return getStat(Stat.STAVE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getStaveAccuracy()
	{
		return getStat(Stat.STAVE_ACCURACY);
	}

	@Override
	public float getStaveAttackSpeed()
	{
		return getStat(Stat.STAVE_ATTACK_SPEED);
	}

	@Override
	public float getStaveCsc()
	{
		return getStat(Stat.STAVE_CSC);
	}

	@Override
	public float getStaveGlobalCsc()
	{
		return getStat(Stat.STAVE_GLOBAL_CSC);
	}

	@Override
	public float getStaveGlobalCsm()
	{
		return getStat(Stat.STAVE_GLOBAL_CSM);
	}

	@Override
	public float getStaveKnockbackOnCrit()
	{
		return getStat(Stat.STAVE_KNOCKBACK_ON_CRIT);
	}

	@Override
	public float getStaveBlock()
	{
		return getStat(Stat.STAVE_BLOCK);
	}

	@Override
	public float getWandDamage()
	{
		return getStat(Stat.WAND_DAMAGE);
	}

	@Override
	public float getWandPhysicalDamage()
	{
		return getStat(Stat.WAND_PHYSICAL_DAMAGE);
	}

	@Override
	public float getWandCsc()
	{
		return getStat(Stat.WAND_CSC);
	}

	@Override
	public float getWandCsm()
	{
		return getStat(Stat.WAND_CSM);
	}

	@Override
	public float getWandAccuract()
	{
		return getStat(Stat.WAND_ACCURACT);
	}

	@Override
	public float getWandAttackSpeed()
	{
		return getStat(Stat.WAND_ATTACK_SPEED);
	}

	@Override
	public float getWandElemDamage()
	{
		return getStat(Stat.WAND_ELEM_DAMAGE);
	}

	@Override
	public float getWandPhysicalToLightning()
	{
		return getStat(Stat.WAND_PHYSICAL_TO_LIGHTNING);
	}

	@Override
	public float getWandPhysicalToCold()
	{
		return getStat(Stat.WAND_PHYSICAL_TO_COLD);
	}

	@Override
	public float getWandPhysicalToFire()
	{
		return getStat(Stat.WAND_PHYSICAL_TO_FIRE);
	}

	@Override
	public float getWandDamagePerPowerCharge()
	{
		return getStat(Stat.WAND_DAMAGE_PER_POWER_CHARGE);
	}

	@Override
	public float getDaggerPhysicalDamage()
	{
		return getStat(Stat.DAGGER_PHYSICAL_DAMAGE);
	}

	@Override
	public float getDaggerAccuracy()
	{
		return getStat(Stat.DAGGER_ACCURACY);
	}

	@Override
	public float getDaggerAttackSpeed()
	{
		return getStat(Stat.DAGGER_ATTACK_SPEED);
	}

	@Override
	public float getDaggerCsc()
	{
		return getStat(Stat.DAGGER_CSC);
	}

	@Override
	public float getDaggerPoisonOnCrit()
	{
		return getStat(Stat.DAGGER_POISON_ON_CRIT);
	}

	@Override
	public float getDaggerCsm()
	{
		return getStat(Stat.DAGGER_CSM);
	}

	@Override
	public float getAxePhysicalDamage()
	{
		return getStat(Stat.AXE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getAxeAttackSpeed()
	{
		return getStat(Stat.AXE_ATTACK_SPEED);
	}

	@Override
	public float getAxeAccuracy()
	{
		return getStat(Stat.AXE_ACCURACY);
	}

	@Override
	public float getSwordPhysicalDamage()
	{
		return getStat(Stat.SWORD_PHYSICAL_DAMAGE);
	}

	@Override
	public float getSwordAccuracy()
	{
		return getStat(Stat.SWORD_ACCURACY);
	}

	@Override
	public float getSwordAttackSpeed()
	{
		return getStat(Stat.SWORD_ATTACK_SPEED);
	}

	@Override
	public float getSwordCsc()
	{
		return getStat(Stat.SWORD_CSC);
	}

	@Override
	public float getSwordCsm()
	{
		return getStat(Stat.SWORD_CSM);
	}

	@Override
	public float getClawPhysicalDamage()
	{
		return getStat(Stat.CLAW_PHYSICAL_DAMAGE);
	}

	@Override
	public float getClawAttackSpeed()
	{
		return getStat(Stat.CLAW_ATTACK_SPEED);
	}

	@Override
	public float getClawCsc()
	{
		return getStat(Stat.CLAW_CSC);
	}

	@Override
	public float getClawCsm()
	{
		return getStat(Stat.CLAW_CSM);
	}

	@Override
	public float getClawAccuracy()
	{
		return getStat(Stat.CLAW_ACCURACY);
	}

	@Override
	public float getClawStealCharge()
	{
		return getStat(Stat.CLAW_STEAL_CHARGE);
	}

	@Override
	public float getClawLifeLeech()
	{
		return getStat(Stat.CLAW_LIFE_LEECH);
	}

	@Override
	public float getClawManaLeech()
	{
		return getStat(Stat.CLAW_MANA_LEECH);
	}

	@Override
	public float getMaceAttackSpeed()
	{
		return getStat(Stat.MACE_ATTACK_SPEED);
	}

	@Override
	public float getMaceAccuracy()
	{
		return getStat(Stat.MACE_ACCURACY);
	}

	@Override
	public float getMaceCsm()
	{
		return getStat(Stat.MACE_CSM);
	}

	@Override
	public float getMacePhysicalDamage()
	{
		return getStat(Stat.MACE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getMaceStunThreshold()
	{
		return getStat(Stat.MACE_STUN_THRESHOLD);
	}

	@Override
	public float getMaceElementalDamage()
	{
		return getStat(Stat.MACE_ELEMENTAL_DAMAGE);
	}

	@Override
	public float getMaceCsc()
	{
		return getStat(Stat.MACE_CSC);
	}

	@Override
	public float getBowKb()
	{
		return getStat(Stat.BOW_KB);
	}

	@Override
	public float getBowCsc()
	{
		return getStat(Stat.BOW_CSC);
	}

	@Override
	public float getBowCsm()
	{
		return getStat(Stat.BOW_CSM);
	}

	@Override
	public float getBowPhysical()
	{
		return getStat(Stat.BOW_PHYSICAL);
	}

	@Override
	public float getBowAccuracy()
	{
		return getStat(Stat.BOW_ACCURACY);
	}

	@Override
	public float getBowAttackSpeed()
	{
		return getStat(Stat.BOW_ATTACK_SPEED);
	}

	@Override
	public float getBowStunThreshhold()
	{
		return getStat(Stat.BOW_STUN_THRESHHOLD);
	}

	@Override
	public float getBowStunDuration()
	{
		return getStat(Stat.BOW_STUN_DURATION);
	}

	@Override
	public float getDualPhys()
	{
		return getStat(Stat.DUAL_PHYS);
	}

	@Override
	public float getDualCsc()
	{
		return getStat(Stat.DUAL_CSC);
	}

	@Override
	public float getDualBlock()
	{
		return getStat(Stat.DUAL_BLOCK);
	}

	@Override
	public float getDualAccuracy()
	{
		return getStat(Stat.DUAL_ACCURACY);
	}

	@Override
	public float getDualAttackSpeed()
	{
		return getStat(Stat.DUAL_ATTACK_SPEED);
	}

	@Override
	public float getDualMainDamage()
	{
		return getStat(Stat.DUAL_MAIN_DAMAGE);
	}

	@Override
	public float getDualOffHandDamage()
	{
		return getStat(Stat.DUAL_OFF_HAND_DAMAGE);
	}

	@Override
	public float getDualCastSpeed()
	{
		return getStat(Stat.DUAL_CAST_SPEED);
	}

	@Override
	public float getShieldAttackSpeed()
	{
		return getStat(Stat.SHIELD_ATTACK_SPEED);
	}

	@Override
	public float getShieldPhysicalDamage()
	{
		return getStat(Stat.SHIELD_PHYSICAL_DAMAGE);
	}

	@Override
	public float getShieldMeleePhysicalDamage()
	{
		return getStat(Stat.SHIELD_MELEE_PHYSICAL_DAMAGE);
	}

	@Override
	public float getAccuracy()
	{
		return getStat(Stat.ACCURACY);
	}

	@Override
	public float getAccPlus()
	{
		return getStat(Stat.ACC_PLUS);
	}

	@Override
	public float getEvasion()
	{
		return getStat(Stat.EVASION);
	}

	@Override
	public float getEvasionArmor()
	{
		return getStat(Stat.EVASION_ARMOR);
	}

	@Override
	public float getEvaRat()
	{
		return getStat(Stat.EVA_RAT);
	}

	@Override
	public float getEvadeProjectile()
	{
		return getStat(Stat.EVADE_PROJECTILE);
	}

	@Override
	public float getEvadeMelee()
	{
		return getStat(Stat.EVADE_MELEE);
	}

	@Override
	public float getEvadeNever()
	{
		return getStat(Stat.EVADE_NEVER);
	}

	@Override
	public float getArmour()
	{
		return getStat(Stat.ARMOUR);
	}

	@Override
	public float getArmorFlat()
	{
		return getStat(Stat.ARMOUR_FLAT);
	}

	@Override
	public float getArmorEvade()
	{
		return getStat(Stat.ARMOR_EVADE);
	}

	@Override
	public float getMovementSpeed()
	{
		return getStat(Stat.MOVEMENT_SPEED);
	}

	@Override
	public float getMovementEnergyShield()
	{
		return getStat(Stat.MOVEMENT_ENERGY_SHIELD);
	}

	@Override
	public float getMovementIgnoreArmor()
	{
		return getStat(Stat.MOVEMENT_IGNORE_ARMOR);
	}

	@Override
	public float getRegen()
	{
		return getStat(Stat.REGEN);
	}

	@Override
	public float getBlockChance()
	{
		return getStat(Stat.BLOCK_CHANCE);
	}

	@Override
	public float getShieldDefence()
	{
		return getStat(Stat.SHIELD_DEFENCE);
	}

	@Override
	public float getShieldBlockChance()
	{
		return getStat(Stat.SHIELD_BLOCK_CHANCE);
	}

	@Override
	public float getShieldElementalResist()
	{
		return getStat(Stat.SHIELD_ELEMENTAL_RESIST);
	}

	@Override
	public float getShieldDualBlock()
	{
		return getStat(Stat.SHIELD_DUAL_BLOCK);
	}

	@Override
	public float getLightningResist()
	{
		return getStat(Stat.LIGHTNING_RESIST);
	}

	@Override
	public float getLightningResistMax()
	{
		return getStat(Stat.LIGHTNING_RESIST_MAX);
	}

	@Override
	public float getMaxLight()
	{
		return getStat(Stat.MAX_LIGHT);
	}

	@Override
	public float getElementalResist()
	{
		return getStat(Stat.ELEMENTAL_RESIST);
	}

	@Override
	public float getFireResist()
	{
		return getStat(Stat.FIRE_RESIST);
	}

	@Override
	public float getFireResistMax()
	{
		return getStat(Stat.FIRE_RESIST_MAX);
	}

	@Override
	public float getColdResist()
	{
		return getStat(Stat.COLD_RESIST);
	}

	@Override
	public float getColdResistCap()
	{
		return getStat(Stat.COLD_RESIST_CAP);
	}

	@Override
	public float getChaosResist()
	{
		return getStat(Stat.CHAOS_RESIST);
	}

	@Override
	public float getResistAll()
	{
		return getStat(Stat.RESIST_ALL);
	}

	@Override
	public float getFreeze()
	{
		return getStat(Stat.FREEZE_CHANCE);
	}

	@Override
	public float getCold()
	{
		return getStat(Stat.COLD);
	}

	@Override
	public float getColdPen()
	{
		return getStat(Stat.COLD_PEN);
	}

	@Override
	public float getPhysicalDamageReduction()
	{
		return getStat(Stat.PHYSICAL_DAMAGE_REDUCTION);
	}

	@Override
	public float getElementalDamageReductionOnConsecrate()
	{
		return getStat(Stat.ELEMENTAL_DAMAGE_REDUCTION_ON_CONSECRATE);
	}

	@Override
	public float getCriticalReduce()
	{
		return getStat(Stat.CRITICAL_REDUCE);
	}

	@Override
	public float getChillAvoid()
	{
		return getStat(Stat.CHILL_AVOID);
	}

	@Override
	public float getFrozenAvoid()
	{
		return getStat(Stat.FROZEN_AVOID);
	}

	@Override
	public float getIgniteAvoid()
	{
		return getStat(Stat.IGNITE_AVOID);
	}

	@Override
	public float getShockAvoid()
	{
		return getStat(Stat.SHOCK_AVOID);
	}

	@Override
	public float getAvoidStunOnCast()
	{
		return getStat(Stat.AVOID_STUN_ON_CAST);
	}

	@Override
	public float getChillDuration()
	{
		return getStat(Stat.CHILL_DURATION);
	}

	@Override
	public float getFreezeDuration()
	{
		return getStat(Stat.FREEZE_DURATION);
	}

	@Override
	public float getBlockRecovery()
	{
		return getStat(Stat.BLOCK_RECOVERY);
	}

	@Override
	public float getManaOnBlock()
	{
		return getStat(Stat.MANA_ON_BLOCK);
	}

	@Override
	public float getCastSpeed()
	{
		return getStat(Stat.CAST_SPEED);
	}

	@Override
	public float getCastSpeedChaos()
	{
		return getStat(Stat.CAST_SPEED_CHAOS);
	}

	@Override
	public float getSpellDamage()
	{
		return getStat(Stat.SPELL_DAMAGE);
	}

	@Override
	public float getSpellCsm()
	{
		return getStat(Stat.SPELL_CSM);
	}

	@Override
	public float getSpellCsc()
	{
		return getStat(Stat.SPELL_CSC);
	}

	@Override
	public float getPenFire()
	{
		return getStat(Stat.PEN_FIRE);
	}

	@Override
	public float getWeapPenFire()
	{
		return getStat(Stat.WEAP_PEN_FIRE);
	}

	@Override
	public float getLightPen()
	{
		return getStat(Stat.LIGHT_PEN);
	}

	@Override
	public float getElemPen()
	{
		return getStat(Stat.ELEM_PEN);
	}

	@Override
	public float getIgDur()
	{
		return getStat(Stat.IG_DUR);
	}

	@Override
	public float getIgChance()
	{
		return getStat(Stat.IG_CHANCE);
	}

	@Override
	public float getLifeLeech()
	{
		return getStat(Stat.LIFE_LEECH);
	}

	@Override
	public float getLifeLeechInstant()
	{
		return getStat(Stat.LIFE_LEECH_INSTANT);
	}

	@Override
	public float getLifeLeechPhysical()
	{
		return getStat(Stat.LIFE_LEECH_PHYSICAL);
	}

	@Override
	public float getLeechRate()
	{
		return getStat(Stat.LEECH_RATE);
	}

	@Override
	public float getLeechPerSec()
	{
		return getStat(Stat.LEECH_PER_SEC);
	}

	@Override
	public float getLeechStun()
	{
		return getStat(Stat.LEECH_STUN);
	}

	@Override
	public float getLeechDamage()
	{
		return getStat(Stat.LEECH_DAMAGE);
	}

	@Override
	public float getPhysLife()
	{
		return getStat(Stat.PHYS_LIFE);
	}

	@Override
	public float getImmuneBleed()
	{
		return getStat(Stat.IMMUNE_BLEED);
	}

	@Override
	public float getKillWeak()
	{
		return getStat(Stat.KILL_WEAK);
	}

	@Override
	public float getOverkill()
	{
		return getStat(Stat.OVERKILL);
	}

	@Override
	public float getFillLeech()
	{
		return getStat(Stat.FILL_LEECH);
	}

	@Override
	public float getOnslaughtOnRareOrUnique()
	{
		return getStat(Stat.ONSLAUGHT_ON_RARE_OR_UNIQUE);
	}

	@Override
	public float getOnslaughtOnKillKill()
	{
		return getStat(Stat.ONSLAUGHT_ON_KILL_KILL);
	}

	@Override
	public float getOnslaughtOnFullFrenzy()
	{
		return getStat(Stat.ONSLAUGHT_ON_FULL_FRENZY);
	}

	@Override
	public float getEvasionOnOnslaught()
	{
		return getStat(Stat.EVASION_ON_ONSLAUGHT);
	}

	@Override
	public float getAoeRadius()
	{
		return getStat(Stat.AOE_RADIUS);
	}

	@Override
	public float getAoeDamage()
	{
		return getStat(Stat.AREA_DAMAGE);
	}

	@Override
	public float getStunDuration()
	{
		return getStat(Stat.STUN_DURATION);
	}

	@Override
	public float getStunOnEnemyFullLife()
	{
		return getStat(Stat.STUN_ON_ENEMY_FULL_LIFE);
	}

	@Override
	public float getStunDurationOnEnemyFullLife()
	{
		return getStat(Stat.STUN_DURATION_ON_ENEMY_FULL_LIFE);
	}

	@Override
	public float getStunDurationOnEnemyLowLife()
	{
		return getStat(Stat.STUN_DURATION_ON_ENEMY_LOW_LIFE);
	}

	@Override
	public float getStunDurationDouble()
	{
		return getStat(Stat.STUN_DURATION_DOUBLE);
	}

	@Override
	public float getStunAvoid()
	{
		return getStat(Stat.STUN_AVOID);
	}

	@Override
	public float getStunNever()
	{
		return getStat(Stat.STUN_NEVER);
	}

	@Override
	public float getStunAndBlockRecovery()
	{
		return getStat(Stat.STUN_AND_BLOCK_RECOVERY);
	}

	@Override
	public float getStunThreshhold()
	{
		return getStat(Stat.STUN_THRESHHOLD);
	}

	@Override
	public float getReflectReduce()
	{
		return getStat(Stat.REFLECT_REDUCE);
	}

	@Override
	public float getRecentKillDamage()
	{
		return getStat(Stat.RECENT_KILL_DAMAGE);
	}

	@Override
	public float getSingleSplash()
	{
		return getStat(Stat.SINGLE_SPLASH);
	}

	@Override
	public float getSplashLess()
	{
		return getStat(Stat.SPLASH_LESS);
	}

	@Override
	public float getBlockSpells()
	{
		return getStat(Stat.BLOCK_SPELLS);
	}

	@Override
	public float getBlockSpellShield()
	{
		return getStat(Stat.BLOCK_SPELL_SHIELD);
	}

	@Override
	public float getManaLeech()
	{
		return getStat(Stat.MANA_LEECH);
	}

	@Override
	public float getAdMana()
	{
		return getStat(Stat.AD_MANA);
	}

	@Override
	public float getManaLeechRate()
	{
		return getStat(Stat.MANA_LEECH_RATE);
	}

	@Override
	public float getMinionInstability()
	{
		return getStat(Stat.MINION_INSTABILITY);
	}

	@Override
	public float getMinionAttackSpeed()
	{
		return getStat(Stat.MINION_ATTACK_SPEED);
	}

	@Override
	public float getMinionCastSpeed()
	{
		return getStat(Stat.MINION_CAST_SPEED);
	}

	@Override
	public float getMinionShield()
	{
		return getStat(Stat.MINION_SHIELD);
	}

	@Override
	public float getMinionBlock()
	{
		return getStat(Stat.MINION_BLOCK);
	}

	@Override
	public float getMinionBlockHeal()
	{
		return getStat(Stat.MINION_BLOCK_HEAL);
	}

	@Override
	public float getMinionDamage()
	{
		return getStat(Stat.MINION_DAMAGE);
	}

	@Override
	public float getMinionLifeLeech()
	{
		return getStat(Stat.MINION_LL);
	}

	@Override
	public float getMinionLifeRegeneration()
	{
		return getStat(Stat.MINION_REGEN);
	}

	@Override
	public float getMinionElementalResist()
	{
		return getStat(Stat.MINION_ELEM_RESIST);
	}

	@Override
	public float getMinionChaosResist()
	{
		return getStat(Stat.MINION_CHAOS_RESIST);
	}

	@Override
	public float getIncreasedMinionLife()
	{
		return getStat(Stat.MINION_MAXIMUM_LIFE);
	}

	@Override
	public float getZombies()
	{
		return getStat(Stat.ZOMBIES);
	}

	@Override
	public float getSkel()
	{
		return getStat(Stat.SKEL);
	}

	@Override
	public float getSkelMax()
	{
		return getStat(Stat.SKEL_MAX);
	}

	@Override
	public float getSpecMax()
	{
		return getStat(Stat.SPEC_MAX);
	}

	@Override
	public int getTotemAdd()
	{
		return (int)getStat(Stat.TOTEM_ADD);
	}

	@Override
	public int getTotemCount()
	{
		return 1 + (int)getStat(Stat.TOTEM_ADD) + (int)getStat(Stat.ANCESTRAL_BOND);
	}

	@Override
	public float getTotemResistElemental()
	{
		return getStat(Stat.TOTEM_RESIST_ELEMENTAL);
	}

	@Override
	public float getKnockbackOnHit()
	{
		return getStat(Stat.KNOCKBACK_ON_HIT);
	}

	@Override
	public float getKnockbackDistance()
	{
		return getStat(Stat.KNOCKBACK_DISTANCE);
	}

	@Override
	public float getBleed()
	{
		return getStat(Stat.BLEED);
	}

	@Override
	public float getBleedDamageOnKill()
	{
		return getStat(Stat.BLEED_DAMAGE_ON_KILL);
	}

	@Override
	public float getBleedDmg()
	{
		return getStat(Stat.BLEED_DMG);
	}

	@Override
	public float getBleedMelee()
	{
		return getStat(Stat.BLEED_MELEE);
	}

	@Override
	public float getBleedPoison()
	{
		return getStat(Stat.BLEED_POISON);
	}

	@Override
	public float getBleedCsm()
	{
		return getStat(Stat.BLEED_CSM);
	}

	@Override
	public float getBleedCsc()
	{
		return getStat(Stat.BLEED_CSC);
	}

	@Override
	public float getPoisonDamage()
	{
		return getStat(Stat.POISON_DAMAGE);
	}

	@Override
	public float getPoisonOnHit()
	{
		return getStat(Stat.POISON_ON_HIT);
	}

	@Override
	public float getMaxEnergyShield()
	{
		return getStat(Stat.ADDED_ENERGY_SHIELD);
	}

	@Override
	public float getIncreasedEnergyShield()
	{
		return getStat(Stat.INCREASED_ENERGY_SHIELD);
	}

	@Override
	public float getEnergyShieldPct2()
	{
		return getStat(Stat.ENERGY_SHIELD_PCT_2);
	}

	@Override
	public float getEnergyShieldMana()
	{
		return getStat(Stat.ENERGY_SHIELD_MANA);
	}

	@Override
	public float getEnergyShieldMana2()
	{
		return getStat(Stat.ENERGY_SHIELD_MANA_2);
	}

	@Override
	public float getEnergyShieldEvade()
	{
		return getStat(Stat.ENERGY_SHIELD_EVADE);
	}

	@Override
	public float getEnergyShieldMoreDamage()
	{
		return getStat(Stat.ENERGY_SHIELD_MORE_DAMAGE);
	}

	@Override
	public float getEnergyShieldRecharg()
	{
		return getStat(Stat.ENERGY_SHIELD_RECHARG);
	}

	@Override
	public float getEnergyShieldShield()
	{
		return getStat(Stat.ENERGY_SHIELD_SHIELD);
	}

	@Override
	public float getEnergyShieldLifeRegen()
	{
		return getStat(Stat.ENERGY_SHIELD_LIFE_REGEN);
	}

	@Override
	public float getEnergyShieldLifeLeech()
	{
		return getStat(Stat.ENERGY_SHIELD_LIFE_LEECH);
	}

	@Override
	public float getEnergyShieldBeforeMana()
	{
		return getStat(Stat.ENERGY_SHIELD_BEFORE_MANA);
	}

	@Override
	public float getEnergyShieldProtectMana()
	{
		return getStat(Stat.ENERGY_SHIELD_BEFORE_MANA);
	}

	@Override
	public float getChain()
	{
		return getStat(Stat.CHAIN);
	}

	@Override
	public float getNoBleed()
	{
		return getStat(Stat.NO_BLEED);
	}

	@Override
	public float getCsc()
	{
		return getStat(Stat.CSC);
	}

	@Override
	public float getEsFaster()
	{
		return getStat(Stat.ES_FASTER);
	}

	@Override
	public float getDodgeAttack()
	{
		return getStat(Stat.DODGE_ATTACK);
	}

	@Override
	public float getDodgeAttack2()
	{
		return getStat(Stat.DODGE_ATTACK2);
	}

	@Override
	public float getDodgeSpell()
	{
		return getStat(Stat.DODGE_SPELL);
	}

	@Override
	public float getDodgeAcrobatics()
	{
		return getStat(Stat.DODGE_ACROBATICS);
	}

	@Override
	public float getAuraRad()
	{
		return getStat(Stat.AURA_RAD);
	}

	@Override
	public float getNonCurseAuraEffect()
	{
		return getStat(Stat.NON_CURSE_AURA_EFFECT);
	}

	@Override
	public float getCurseEffect()
	{
		return getStat(Stat.CURSE_EFFECT);
	}

	@Override
	public float getAdditionalCurse()
	{
		return getStat(Stat.CURSE_NUM);
	}

	@Override
	public float getCurseDuration()
	{
		return getStat(Stat.CURSE_DUR);
	}

	@Override
	public float getCurseCastSpeed()
	{
		return getStat(Stat.CURSE_CAST_SPEED);
	}

	@Override
	public float getCurseRadius()
	{
		return getStat(Stat.CURSE_RADIUS);
	}

	@Override
	public float getFrenzCharg()
	{
		return getStat(Stat.FRENZ_CHARG);
	}

	@Override
	public float getFrChDur()
	{
		return getStat(Stat.FR_CH_DUR);
	}

	@Override
	public float getFrenzyEvasion()
	{
		return getStat(Stat.FRENZY_EVASION);
	}

	@Override
	public float getFrenzKill()
	{
		return getStat(Stat.FRENZ_KILL);
	}

	@Override
	public float getEndDur()
	{
		return getStat(Stat.END_DUR);
	}

	@Override
	public float getEnduranceChargeLifeRegen()
	{
		return getStat(Stat.ENDURANCE_CHARGE_LIFE_REGEN);
	}

	@Override
	public float getMaxEnd()
	{
		return getStat(Stat.MAX_END);
	}

	@Override
	public float getChargeOnKill()
	{
		return getStat(Stat.CHARGE_ON_KILL);
	}

	@Override
	public float getPowerChargeDuration()
	{
		return getStat(Stat.POWER_CHARGE_DURATION);
	}

	@Override
	public float getPowerChargeAdditional()
	{
		return getStat(Stat.POWER_CHARGE_ADDITIONAL);
	}

	@Override
	public float getChargeShare()
	{
		return getStat(Stat.CHARGE_SHARE);
	}

	@Override
	public float getEnduranceChargeMax()
	{
		return getStat(Stat.ENDURANCE_CHARGE_MAX);
	}

	@Override
	public float getEnduranceChargeOnMeleeCritical()
	{
		return getStat(Stat.ENDURANCE_CHARGE_ON_MELEE_CRITICAL);
	}

	@Override
	public float getPowerChargeOnBlock()
	{
		return getStat(Stat.POWER_CHARGE_ON_BLOCK);
	}

	@Override
	public float getTrapRed()
	{
		return getStat(Stat.TRAP_RED);
	}

	@Override
	public float getMeleeFort()
	{
		return getStat(Stat.MELEE_FORT);
	}

	@Override
	public float getIncFort()
	{
		return getStat(Stat.INC_FORT);
	}

	@Override
	public float getFortDur()
	{
		return getStat(Stat.FORT_DUR);
	}

	@Override
	public float getFortMelee()
	{
		return getStat(Stat.FORT_MELEE);
	}

	@Override
	public float getFortifyArmour()
	{
		return getStat(Stat.FORTIFY_ARMOUR);
	}

	@Override
	public float getFortAlly()
	{
		return getStat(Stat.FORT_ALLY);
	}

	@Override
	public float getTauntHit()
	{
		return getStat(Stat.TAUNT_HIT);
	}

	@Override
	public float getTauntReduce()
	{
		return getStat(Stat.TAUNT_REDUCE);
	}

	@Override
	public float getLightRadiusEnergyShield()
	{
		return getStat(Stat.LIGHT_RADIUS_ENERGY_SHIELD);
	}

	@Override
	public float getLightRadius()
	{
		return getStat(Stat.LIGHT_RADIUS);
	}

	@Override
	public float getStunCull()
	{
		return getStat(Stat.STUN_CULL);
	}

	@Override
	public float getAttackCastSpeed()
	{
		return getStat(Stat.ATTACK_CAST_SPEED);
	}

	@Override
	public float getAtkCast()
	{
		return getStat(Stat.ATK_CAST);
	}

	@Override
	public float getAtckCastMove()
	{
		return getStat(Stat.ATCK_CAST_MOVE);
	}

	@Override
	public float getLifeManaRegen()
	{
		return getStat(Stat.LIFE_MANA_REGEN);
	}

	@Override
	public float getAvoidElem()
	{
		return getStat(Stat.AVOID_ELEM);
	}

	@Override
	public float getFreezeShockIgnite()
	{
		return getStat(Stat.FREEZE_SHOCK_IGNITE);
	}

	@Override
	public float getNeverEvaded()
	{
		return getStat(Stat.NEVER_EVADED);
	}

	@Override
	public float getNeverCrit()
	{
		return getStat(Stat.NEVER_CRIT);
	}

	@Override
	public float getEnemyNeverLeech()
	{
		return getStat(Stat.ENEMY_NEVER_LEECH);
	}

	@Override
	public float getManaBeforeLife()
	{
		return getStat(Stat.MIND_OVER_MATTER);
	}

	@Override
	public float getEnemyElemResist()
	{
		return getStat(Stat.ENEMY_ELEM_RESIST);
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
		return getStat(Stat.AREA_DAMAGE) / 100f;
	}

	@Override
	public int getLife()
	{
		return characterInstance.getLife();
	}

	@Override
	public int getEnergyShield()
	{
		return characterInstance.getEnergyShield();
	}

	@Override
	public float getSkillEffectDuration()
	{
		return getStat(Stat.SKILL_EFFECT_DURATION);
	}

	private float getStat(final Stat stat)
	{
		return characterInstance.getStatValue(stat);
	}
}
