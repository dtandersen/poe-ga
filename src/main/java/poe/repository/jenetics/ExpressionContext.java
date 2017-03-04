package poe.repository.jenetics;

public interface ExpressionContext
{
	int getPassiveSkillCount();

	float getIncreasedMaximumLife();

	float getLife1();

	float getMaximumLife();

	float getLifeOnKill();

	float getLifeOnHit();

	float getFlaskLife();

	float getFlaskRecovery();

	float getFlaskExtra();

	float getFlaskDmg();

	float getFlaskDuration();

	float getFlaskIncreasedEffect();

	float getStrength();

	float getDexterity();

	float getIntelligence();

	float getManaBonus();

	float getMana();

	float getReducedManaCost();

	float getManaRegenerationRate();

	float getManaReserve();

	float getManaMoveRegen();

	float getManaReserveReduce();

	float getManaFlask();

	float getManaLeechPhysical();

	float getManaOnKill();

	float getManaOnHit();

	float getManaToLife();

	float getManaGainOnHit();

	float getCsm();

	float getCsmNever();

	float getFlaskGain();

	float getAvoidFreeze();

	float getElemFlask();

	float getAttackSpeed();

	float getStunThresh();

	float getDamageAttack();

	float getDamageFrozenShockIgnited();

	float getDamageOnEnemyLowLife();

	float getPhysicalDamage();

	float getPhysicalDamage2();

	float getPhysicalDot();

	float getChaosDamage();

	float getChaosDamageOnPhysical();

	float getElemDamage();

	float getElementalStatusAilments();

	float getElementalDamageOnCrit();

	float getCscElemStatus();

	float getFireDamage();

	float getFireDamageConvert();

	float getBurnDamage();

	float getPhysicalDamageAsFireDamage();

	float getFireDamageOnly();

	float getLightningDamage();

	float getDotDamage();

	float getSpellDamageOnLowLife();

	float getSpellDamagePerPowerCharge();

	float getTrapDamage();

	float getTrapRecovery();

	float getTrapCsm();

	float getTrapCsc();

	float getTrapPowerCharge();

	float getTrapRadius();

	float getTrapSpeed();

	float getTrapInvuln();

	float getTrapElemPen();

	float getTrapAdditional();

	float getTrapFrenzyCharge();

	float getMineCsm();

	float getMineCsc();

	float getMinePowerCharge();

	float getMineDamage();

	float getMineSpeed();

	float getMineDuration();

	float getMineAdditional();

	float getMineInstant();

	float getMineInvuln();

	float getMineRadius();

	float getMineElemPen();

	float getChillOnUnfreeze();

	float getFreezeOnChill();

	float getShockDuration();

	float getShockChance();

	float getSkillDur();

	float getTotemDamage();

	float getTotemPlacementSpeed();

	float getTotemLife();

	float getTotemHelm();

	float getTotemRes();

	float getTotemCharge();

	float getTotemDuration();

	float getTotemCastSpeed();

	float getTotemAttackSpeed();

	float getTotemCsc();

	float getTotemCsm();

	float getTotalGlobalCsc();

	float getProjDamage();

	float getProjSpeed();

	float getProjFar();

	float getProjSkill();

	float getProjPierce();

	float getProjPointBlank();

	float getProjStr();

	float getArrowSpeed();

	float getWeaponElementalDamage();

	float getWeaponFireDamage();

	float getWeaponColdDamage();

	float getWeaponLightningDamage();

	float getWeaponPenCold();

	float getWeaponPenLightning();

	float getWeaponPenElem();

	float getMeleeDmg();

	float getMeleePhysicalDamage();

	float getMeleeAttackSpeed();

	float getMeleeCsc();

	float getMeleeCsm();

	float getMeleeRange();

	float getOneHandedPhysicalDamage();

	float getOneHandedAttackSpeed();

	float getOneHandedAccuracy();

	float getOneHandedCsc();

	float getTwoHandedPhysicalDamage();

	float getTwoHandedAccuracy();

	float getTwoHandedAttackSpeed();

	float getTwoHandedStunDuration();

	float getTwoHandedDamage();

	float getStavePhysicalDamage();

	float getStaveAccuracy();

	float getStaveAttackSpeed();

	float getStaveCsc();

	float getStaveGlobalCsc();

	float getStaveGlobalCsm();

	float getStaveKnockbackOnCrit();

	float getStaveBlock();

	float getWandDamage();

	float getWandPhysicalDamage();

	float getWandCsc();

	float getWandCsm();

	float getWandAccuract();

	float getWandAttackSpeed();

	float getWandElemDamage();

	float getWandPhysicalToLightning();

	float getWandPhysicalToCold();

	float getWandPhysicalToFire();

	float getWandDamagePerPowerCharge();

	float getDaggerPhysicalDamage();

	float getDaggerAccuracy();

	float getDaggerAttackSpeed();

	float getDaggerCsc();

	float getDaggerPoisonOnCrit();

	float getDaggerCsm();

	float getAxePhysicalDamage();

	float getAxeAttackSpeed();

	float getAxeAccuracy();

	float getSwordPhysicalDamage();

	float getSwordAccuracy();

	float getSwordAttackSpeed();

	float getSwordCsc();

	float getSwordCsm();

	float getClawPhysicalDamage();

	float getClawAttackSpeed();

	float getClawCsc();

	float getClawCsm();

	float getClawAccuracy();

	float getClawStealCharge();

	float getClawLifeLeech();

	float getClawManaLeech();

	float getMaceAttackSpeed();

	float getMaceAccuracy();

	float getMaceCsm();

	float getMacePhysicalDamage();

	float getMaceStunThreshold();

	float getMaceElementalDamage();

	float getMaceCsc();

	float getBowKb();

	float getBowCsc();

	float getBowCsm();

	float getBowPhysical();

	float getBowAccuracy();

	float getBowAttackSpeed();

	float getBowStunThreshhold();

	float getBowStunDuration();

	float getDualPhys();

	float getDualCsc();

	float getDualBlock();

	float getDualAccuracy();

	float getDualAttackSpeed();

	float getDualMainDamage();

	float getDualOffHandDamage();

	float getDualCastSpeed();

	float getShieldAttackSpeed();

	float getShieldPhysicalDamage();

	float getShieldMeleePhysicalDamage();

	float getAccuracy();

	float getAccPlus();

	float getEvasion();

	float getEvasionArmor();

	float getEvaRat();

	float getEvadeProjectile();

	float getEvadeMelee();

	float getEvadeNever();

	float getArmour();

	float getArmorFlat();

	float getArmorEvade();

	float getMovementSpeed();

	float getMovementEnergyShield();

	float getMovementIgnoreArmor();

	float getRegen();

	float getBlockChance();

	float getShieldDefence();

	float getShieldBlockChance();

	float getShieldElementalResist();

	float getShieldDualBlock();

	float getLightningResist();

	float getLightningResistMax();

	float getMaxLight();

	float getElementalResist();

	float getFireResist();

	float getFireResistMax();

	float getColdResist();

	float getColdResistCap();

	float getChaosResist();

	float getResistAll();

	float getFreeze();

	float getCold();

	float getColdPen();

	float getPhysicalDamageReduction();

	float getElementalDamageReductionOnConsecrate();

	float getCriticalReduce();

	float getChillAvoid();

	float getFrozenAvoid();

	float getIgniteAvoid();

	float getShockAvoid();

	float getAvoidStunOnCast();

	float getChillDuration();

	float getFreezeDuration();

	float getBlockRecovery();

	float getManaOnBlock();

	float getCastSpeed();

	float getCastSpeedChaos();

	float getSpellDamage();

	float getSpellCsm();

	float getSpellCsc();

	float getPenFire();

	float getWeapPenFire();

	float getLightPen();

	float getElemPen();

	float getIgDur();

	float getIgChance();

	float getLifeLeech();

	float getLifeLeechInstant();

	float getLifeLeechPhysical();

	float getLeechRate();

	float getLeechPerSec();

	float getLeechStun();

	float getLeechDamage();

	float getPhysLife();

	float getImmuneBleed();

	float getKillWeak();

	float getOverkill();

	float getFillLeech();

	float getOnslaughtOnRareOrUnique();

	float getOnslaughtOnKillKill();

	float getOnslaughtOnFullFrenzy();

	float getEvasionOnOnslaught();

	float getAoeRadius();

	float getAoeDamage();

	float getStunDuration();

	float getStunOnEnemyFullLife();

	float getStunDurationOnEnemyFullLife();

	float getStunDurationOnEnemyLowLife();

	float getStunDurationDouble();

	float getStunAvoid();

	float getStunNever();

	float getStunAndBlockRecovery();

	float getStunThreshhold();

	float getReflectReduce();

	float getRecentKillDamage();

	float getSingleSplash();

	float getSplashLess();

	float getBlockSpells();

	float getBlockSpellShield();

	float getManaLeech();

	float getAdMana();

	float getManaLeechRate();

	float getMinionInstability();

	float getMinionAttackSpeed();

	float getMinionCastSpeed();

	float getMinionShield();

	float getMinionBlock();

	float getMinionBlockHeal();

	float getMinionDamage();

	float getMinionLl();

	float getMinionRegen();

	float getMinionElemResist();

	float getMinionChaosResist();

	float getMinionMaxLife();

	float getZombies();

	float getZombieMax();

	float getSkel();

	float getSkelMax();

	float getSpecMax();

	float getAncBond();

	float getTotemAdd();

	float getTotemResistElemental();

	float getKnockbackOnHit();

	float getKnockbackDistance();

	float getBleed();

	float getBleedDamageOnKill();

	float getBleedDmg();

	float getBleedMelee();

	float getBleedPoison();

	float getBleedCsm();

	float getBleedCsc();

	float getPoisonDamage();

	float getPoisonOnHit();

	float getEnergyShieldMax();

	float getEnergyShieldPct();

	float getEnergyShieldPct2();

	float getEnergyShieldMana();

	float getEnergyShieldMana2();

	float getEnergyShieldEvade();

	float getEnergyShieldMoreDamage();

	float getEnergyShieldRecharg();

	float getEnergyShieldShield();

	float getEnergyShieldLifeRegen();

	float getEnergyShieldLifeLeech();

	float getEnergyShieldBeforeMana();

	float getEnergyShieldProtectMana();

	float getChain();

	float getNoBleed();

	float getCsc();

	float getEsFaster();

	float getDodgeAttack();

	float getDodgeAttack2();

	float getDodgeSpell();

	float getDodgeAcrobatics();

	float getAuraRad();

	float getNonCurseAura();

	float getCurseEffect();

	float getCurseNum();

	float getCurseDur();

	float getCurseCastSpeed();

	float getCurseRadius();

	float getFrenzCharg();

	float getFrChDur();

	float getFrenzyEvasion();

	float getFrenzKill();

	float getEndDur();

	float getEnduranceChargeLifeRegen();

	float getMaxEnd();

	float getChargeOnKill();

	float getPowerChargeDuration();

	float getPowerChargeAdditional();

	float getChargeShare();

	float getEnduranceChargeMax();

	float getEnduranceChargeOnMeleeCritical();

	float getPowerChargeOnBlock();

	float getTrapRed();

	float getMeleeFort();

	float getIncFort();

	float getFortDur();

	float getFortMelee();

	float getFortifyArmour();

	float getFortAlly();

	float getTauntHit();

	float getTauntReduce();

	float getLightRadiusEnergyShield();

	float getLightRadius();

	float getStunCull();

	float getAttackCastSpeed();

	float getAtkCast();

	float getAtckCastMove();

	float getLifeManaRegen();

	float getAvoidElem();

	float getFreezeShockIgnite();

	float getNeverEvaded();

	float getNeverCrit();

	float getEnemyNeverLeech();

	float getManaBeforeLife();

	float getEnemyElemResist();
}