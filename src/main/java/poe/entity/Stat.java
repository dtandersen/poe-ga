package poe.entity;

import static poe.entity.Regex.FLOAT;
import static poe.entity.Regex.INT;
import static poe.entity.Regex.PLUS;
import static poe.entity.Regex.PLUS_INT;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Stat
{
	UNKNOWN("Unknown"),

	// life
	LIFE_MAX(INT + "% increased maximum Life"),
	LIFE_1("Maximum Life becomes 1, Immune to Chaos Damage"),
	MAX_LIFE_PLUS(PLUS + INT + " to maximum Life"),
	LIFE_ON_KILL(PLUS_INT + " Life gained on Kill"),
	LIFE_ON_HIT(PLUS_INT + " Life gained for each Enemy hit by your Attacks"),

	// flask
	FLASK_LIFE(INT + "% increased Life Recovery from Flasks"),
	FLASK_RECOVERY(INT + "% increased Flask Recovery Speed"),
	FLASK_EXTRA(INT + "% reduced Flask Charges used"),
	FLASK_DMG(INT + "% increased Damage while using a Flask"),
	FLASK_DURATION(INT + "% increased Flask effect duration"),
	FLASK_INCREASED_EFFECT(INT + "% increased effect of Flasks"),

	// stats
	STRENGTH(PLUS + INT + " to Strength"),
	DEXTERITY(PLUS + INT + " to Dexterity"),
	INTELLIGENCE(PLUS_INT + " to Intelligence"),
	STR_INT(PLUS_INT + " to Strength and Intelligence"),
	DEX_INT(PLUS_INT + " to Dexterity and Intelligence"),
	STR_DEX(PLUS_INT + " to Strength and Dexterity"),

	// mana
	MANA_BONUS(PLUS_INT + " to maximum Mana"),
	MANA(INT + "% increased maximum Mana"),
	REDUCE_MANA(INT + "% reduced Mana Cost of Skills"),
	MANA_REGEN(INT + "% increased Mana Regeneration Rate"),
	MANA_RESV(INT + "% reduced Mana Reserved"),
	MANA_MOVE_REGEN(INT + "% increased Mana Regeneration if you've used a Movement Skill Recently"),
	MANA_RESERVE_REDUCE(INT + "% less Mana Reserved"),
	MANA_FLASK(INT + "% increased Mana Recovery from Flasks"),
	MANA_LEECH_PHYSICAL(FLOAT + "% of Physical Attack Damage Leeched as Mana"),
	MANA_ON_KILL(PLUS_INT + " Mana gained on Kill"),
	MANA_ON_HIT(PLUS_INT + " Mana gained for each Enemy hit by your Attacks"),
	MANA_TO_LIFE("Removes all mana. Spend Life instead of Mana for Skills"),
	MANA_GAIN_ON_HIT(INT + "% of Damage taken gained as Mana when Hit"),

	// critical
	CSM(PLUS_INT + "% to Critical Strike Multiplier"),
	CSM_NEVER("No Critical Strike Multiplier"),

	// flask
	FLASK_GAIN(INT + "% increased Flask Charges gained"),
	AVOID_FREEZE(INT + "% chance to avoid Freeze, Shock, Ignite and Bleed while using a Flask"),
	ELEM_FLASK(INT + "% reduced Elemental Damage taken while using a Flask"),

	ATTACK_SPEED(INT + "% increased Attack Speed"),
	STUN_THRESH(INT + "% reduced Enemy Stun Threshold"),

	// extra damage
	DAMAGE_ATTACK(INT + "% increased Attack Damage"),
	DAMAGE_FROZEN_SHOCK_IGNITED(INT + "% increased Damage against Frozen, Shocked or Ignited Enemies"),
	DAMAGE_ON_ENEMY_LOW_LIFE("Hits deal " + INT + "% increased Damage against Enemies that are on Low Life"),

	PHYSICAL_DAMAGE(INT + "% increased Physical Damage"),
	PHYSICAL_DAMAGE_2(INT + "% increased Physical Damage with Attacks"),
	PHYSICAL_DOT(INT + "% increased Physical Damage over Time"),

	CHAOS_DAMAGE(INT + "% increased Chaos Damage"),
	CHAOS_DAMAGE_ON_PHYSICAL("Gain " + INT + "% of Physical Damage as Extra Chaos Damage"),

	ELEM_DAMAGE(INT + "% increased Elemental Damage"),
	ELEMENTAL_STATUS_AILMENTS(INT + "% increased Duration of Elemental Status Ailments on Enemies"),
	ELEMENTAL_DAMAGE_ON_CRIT(INT + "% more Elemental Damage if you've Crit in the past 8 seconds"),
	CSC_ELEM_STATUS(INT + "% increased Critical Strike Chance against Enemies affected"), // by
																							// Elemental
																							// Status
																							// Ailments

	FIRE_DAMAGE(INT + "% increased Fire Damage"),
	FIRE_DAMAGE_CONVERT(INT + "% of Physical, Cold and Lightning Damage Converted to Fire Damage"),
	BURN_DAMAGE(INT + "% increased Burning Damage"),
	PHYSICAL_DAMAGE_AS_FIRE_DAMAGE("Gain " + INT + "% of Physical Damage as Extra Fire Damage"),
	FIRE_DAMAGE_ONLY("Deal no Non-Fire Damage"),

	LIGHTNING_DAMAGE(INT + "% increased Lightning Damage"),

	DOT_DAMAGE(INT + "% increased Damage over Time"),

	// spell damage
	SPELL_DAMAGE_ON_LOW_LIFE(INT + "% more Spell Damage when on Low Life"),
	SPELL_DAMAGE_PER_POWER_CHARGE(INT + "% increased Spell Damage per Power Charge"),

	// traps
	TRAP_DAMAGE(INT + "% increased Trap Damage"),
	TRAP_RECOVERY(INT + "% increased Cooldown Recovery Speed for throwing Traps"),
	TRAP_CSM(PLUS_INT + "% to Critical Strike Multiplier with Traps"),
	TRAP_CSC(INT + "% increased Critical Strike Chance with Traps"),
	TRAP_POWER_CHARGE(INT + "% chance to gain a Power Charge when your Trap is triggered by an Enemy"),
	TRAP_RADIUS(INT + "% increased Trap Trigger Radius"),
	TRAP_SPEED(INT + "% increased Trap Throwing Speed"),
	TRAP_INVULN("Traps cannot be Damaged for 5 seconds after being Thrown"),
	TRAP_ELEM_PEN("Trap Damage Penetrates " + INT + "% Elemental Resistances"),
	TRAP_ADDITIONAL("Can have up to " + INT + " additional Trap[s]? placed at a time"),
	TRAP_FRENZY_CHARGE(INT + "% chance to gain a Frenzy Charge when your Trap is triggered by an Enemy"),

	// mine
	MINE_CSM(PLUS_INT + "% to Critical Strike Multiplier with Mines"),
	MINE_CSC(INT + "% increased Critical Strike Chance with Mines"),
	MINE_POWER_CHARGE(INT + "% chance to gain a Power Charge when your Mine is Detonated targeting an Enemy"),
	MINE_DAMAGE(INT + "% increased Mine Damage"),
	MINE_SPEED(INT + "% increased Mine Laying Speed"),
	MINE_DURATION(INT + "% increased Mine Duration"),
	MINE_ADDITIONAL("Can have up to 1 additional Remote Mine placed at a time"),
	MINE_INSTANT("Detonating Mines is Instant"),
	MINE_INVULN("Mines cannot be Damaged for 5 seconds after being Placed"),
	MINE_RADIUS(INT + "% increased Mine Detonation Radius"),
	MINE_ELEM_PEN("Mine Damage Penetrates " + INT + "% Elemental Resistances"),

	// chill
	CHILL_ON_UNFREEZE("Enemies Become Chilled as they Unfreeze"),
	FREEZE_ON_CHILL(INT + "% chance to Freeze Enemies which are Chilled"),

	// shock
	SHOCK_DURATION(INT + "% increased Shock Duration on Enemies"),
	SHOCK_CHANCE(INT + "% chance to Shock"),

	// skills
	SKILL_DUR(INT + "% increased Skill Effect Duration"),

	// totem
	TOTEM_DAMAGE(INT + "% increased Totem Damage"),
	TOTEM_PLACE(INT + "% increased Totem Placement speed"),
	TOTEM_LIFE(INT + "% increased Totem Life"),
	TOTEM_HELM("Skills in your Helm can have up to 1 additional Totem Summoned at a time"),
	TOTEM_RES("Totems have " + INT + "% additional Physical Damage Reduction"),
	TOTEM_CHARGE(INT + "% chance to gain a Power Charge when you place a Totem"),
	TOTEM_DURATION(INT + "% increased Totem Duration"),
	TOTEM_CAST_SPEED("Spells Cast by Totems have " + INT + "% increased Cast Speed"),
	TOTEM_ATTACK_SPEED("Attacks used by Totems have " + INT + "% increased Attack Speed"),
	TOTEM_CSC(INT + "% increased Critical Strike Chance with Totem Skills"),
	TOTEM_CSM(PLUS_INT + "% to Critical Strike Multiplier with Totem Skills"),
	TOTAL_GLOBAL_CSC(INT + "% increased Global Critical Strike Chance if you've Summoned a Totem Recently"),

	// projectiles
	PROJ_DAMAGE(INT + "% increased Projectile Damage"),
	PROJ_SPEED(INT + "% increased Projectile Speed"),
	PROJ_FAR("Projectile Attacks gain damage as they travel farther, dealing up to 30% more Damage to targets"),
	PROJ_SKILL("Skills fire an additional Projectile"),
	PROJ_PIERCE(INT + "% chance of Projectiles Piercing"),
	PROJ_POINT_BLANK("Projectile Attacks deal up to " + INT + "% more Damage to targets at the start of their movement, dealing less Damage to targets as the projectile travels farther"),
	PROJ_STR("The increase to Physical Damage from Strength applies to Projectile Attacks as well as Melee Attacks"),

	// arrows
	ARROW_SPEED(INT + "% increased Arrow Speed"),

	// weapons
	WEAPON_ELEMENTAL_DAMAGE(INT + "% increased Elemental Damage with Weapons"),
	WEAPON_FIRE_DAMAGE(INT + "% increased Fire Damage with Weapons"),
	WEAPON_COLD_DAMAGE(INT + "% increased Cold Damage with Weapons"),
	WEAPON_LIGHTNING_DAMAGE(INT + "% increased Lightning Damage with Weapons"),
	WEAPON_PEN_COLD("Damage with Weapons Penetrates " + INT + "% Cold Resistance"),
	WEAPON_PEN_LIGHTNING("Damage with Weapons Penetrates " + INT + "% Lightning Resistance"),
	WEAPON_PEN_ELEM("Damage with Weapons Penetrates " + INT + "% Elemental Resistance"),

	// melee
	MELEE_DMG(INT + "% increased Melee Damage"),
	MELEE_PHYSICAL_DAMAGE(INT + "% increased Melee Physical Damage"),
	MELEE_ATTACK_SPEED(INT + "% increased Melee Attack Speed"),
	MELEE_CSC(INT + "% increased Melee Critical Strike Chance"),
	MELEE_CSM(PLUS_INT + "% to Melee Critical Strike Multiplier"),
	MELEE_RANGE(PLUS_INT + " to Melee Weapon and Unarmed range"),

	// one-handed
	ONE_HANDED_PHYSICAL_DAMAGE(INT + "% increased Physical Damage with One Handed Melee Weapons"),
	ONE_HANDED_ATTACK_SPEED(INT + "% increased Attack Speed with One Handed Melee Weapons"),
	ONE_HANDED_ACCURACY(INT + "% increased Accuracy Rating with One Handed Melee Weapons"),
	ONE_HANDED_CSC(INT + "% increased Critical Strike Chance with One Handed Melee Weapons"),

	// two-handed
	TWO_HANDED_PHYSICAL_DAMAGE(INT + "% increased Physical Damage with Two Handed Melee Weapons"),
	TWO_HANDED_ACCURACY(INT + "% increased Accuracy Rating with Two Handed Melee Weapons"),
	TWO_HANDED_ATTACK_SPEED(INT + "% increased Attack Speed with Two Handed Melee Weapons"),
	TWO_HANDED_STUN_DURATION(INT + "% increased Stun Duration with Two Handed Melee Weapons on Enemies"),
	TWO_HANDED_DAMAGE(INT + "% increased Damage with Two Handed Weapons"),

	// staves
	STAVE_PHYSICAL_DAMAGE(INT + "% increased Physical Damage with Staves"),
	STAVE_ACCURACY(INT + "% increased Accuracy Rating with Staves"),
	STAVE_ATTACK_SPEED(INT + "% increased Attack Speed with Staves"),
	STAVE_CSC(INT + "% increased Critical Strike Chance with Staves"),
	STAVE_GLOBAL_CSC(INT + "% increased Global Critical Strike Chance while wielding a Staff"),
	STAVE_GLOBAL_CSM(PLUS_INT + "% to Global Critical Strike Multiplier while wielding a Staff"),
	STAVE_KNOCKBACK_ON_CRIT("Knocks Back Enemies if you get a Critical Strike with a Staff"),
	STAVE_BLOCK(INT + "% additional Block Chance with Staves"),

	// wand
	WAND_DAMAGE(INT + "% increased Damage with Wands"),
	WAND_PHYSICAL_DAMAGE(INT + "% increased Physical Damage with Wands"),
	WAND_CSC(INT + "% increased Critical Strike Chance with Wands"),
	WAND_CSM(PLUS_INT + "% to Critical Strike Multiplier with Wands"),
	WAND_ACCURACT(INT + "% increased Accuracy Rating with Wands"),
	WAND_ATTACK_SPEED(INT + "% increased Attack Speed with Wands"),
	WAND_ELEM_DAMAGE(INT + "% increased Elemental Damage with Wands"),
	WAND_PHYSICAL_TO_LIGHTNING(INT + "% of Wand Physical Damage Added as Lightning Damage"),
	WAND_PHYSICAL_TO_COLD(INT + "% of Wand Physical Damage Added as Cold Damage"),
	WAND_PHYSICAL_TO_FIRE(INT + "% of Wand Physical Damage Added as Fire Damage"),
	WAND_DAMAGE_PER_POWER_CHARGE(INT + "% increased Wand Damage per Power Charge"),

	// dagger
	DAGGER_PHYSICAL_DAMAGE(INT + "% increased Physical Damage with Daggers"),
	DAGGER_ACCURACY(INT + "% increased Accuracy Rating with Daggers"),
	DAGGER_ATTACK_SPEED(INT + "% increased Attack Speed with Daggers"),
	DAGGER_CSC(INT + "% increased Critical Strike Chance with Daggers"),
	DAGGER_POISON_ON_CRIT("Critical Strikes with Daggers have a 30% chance to Poison the Enemy"),
	DAGGER_CSM(PLUS_INT + "% to Critical Strike Multiplier with Daggers"),

	// axe
	AXE_PHYSICAL_DAMAGE(INT + "% increased Physical Damage with Axes"),
	AXE_ATTACK_SPEED(INT + "% increased Attack Speed with Axes"),
	AXE_ACCURACY(INT + "% increased Accuracy Rating with Axes"),

	// sword
	SWORD_PHYSICAL_DAMAGE(INT + "% increased Physical Damage with Swords"),
	SWORD_ACCURACY(INT + "% increased Accuracy Rating with Swords"),
	SWORD_ATTACK_SPEED(INT + "% increased Attack Speed with Swords"),
	SWORD_CSC(INT + "% increased Critical Strike Chance with Swords"),
	SWORD_CSM(PLUS_INT + "% to Critical Strike Multiplier with Swords"),

	// claw
	CLAW_PHYSICAL_DAMAGE(INT + "% increased Physical Damage with Claws"),
	CLAW_ATTACK_SPEED(INT + "% increased Attack Speed with Claws"),
	CLAW_CSC(INT + "% increased Critical Strike Chance with Claws"),
	CLAW_CSM(PLUS_INT + "% to Critical Strike Multiplier with Claws"),
	CLAW_ACCURACY(INT + "% increased Accuracy Rating with Claws"),
	CLAW_STEAL_CHARGE(INT + "% chance to Steal Power, Frenzy, and Endurance Charges on Hit with Claws"),
	CLAW_LIFE_LEECH(FLOAT + "% of Physical Damage Dealt with Claws Leeched as Life"),
	CLAW_MANA_LEECH(FLOAT + "% of Physical Damage Dealt with Claws Leeched as Mana"),

	// mace
	MACE_ATTACK_SPEED(INT + "% increased Attack Speed with Maces"),
	MACE_ACCURACY(INT + "% increased Accuracy Rating with Maces"),
	MACE_CSM(PLUS_INT + "% to Critical Strike Multiplier with Maces"),
	MACE_PHYSICAL_DAMAGE(INT + "% increased Physical Damage with Maces"),
	MACE_STUN_THRESHOLD(INT + "% reduced Enemy Stun Threshold with Maces"),
	MACE_ELEMENTAL_DAMAGE(INT + "% increased Elemental Damage with Maces"),
	MACE_CSC(INT + "% increased Critical Strike Chance with Maces"),

	// bow
	BOW_KB("Knocks Back Enemies if you get a Critical Strike with a Bow"),
	BOW_CSC(INT + "% increased Critical Strike Chance with Bows"),
	BOW_CSM(PLUS_INT + "% to Critical Strike Multiplier with Bows"),
	BOW_PHYSICAL(INT + "% increased Physical Damage with Bows"),
	BOW_ACCURACY(INT + "% increased Accuracy Rating with Bows"),
	BOW_ATTACK_SPEED(INT + "% increased Attack Speed with Bows"),
	BOW_STUN_THRESHHOLD(INT + "% reduced Enemy Stun Threshold with Bows"),
	BOW_STUN_DURATION(INT + "% increased Stun Duration with Bows on Enemies"),

	// dual
	DUAL_PHYS(INT + "% increased Physical Weapon Damage while Dual Wielding"),
	DUAL_CSC(INT + "% increased Weapon Critical Strike Chance while Dual Wielding"),
	DUAL_BLOCK(INT + "% additional Block Chance while Dual Wielding"),
	DUAL_ACCURACY(INT + "% increased Accuracy Rating while Dual Wielding"),
	DUAL_ATTACK_SPEED(INT + "% increased Attack Speed while Dual Wielding"),
	DUAL_MAIN_DAMAGE(INT + "% increased Attack Damage with Main Hand"),
	DUAL_OFF_HAND_DAMAGE(INT + "% increased Attack Speed with Off Hand"),
	DUAL_CAST_SPEED(INT + "% increased Cast Speed while Dual Wielding"),

	// shield
	SHIELD_ATTACK_SPEED(INT + "% increased Attack Speed while holding a Shield"),
	SHIELD_PHYSICAL_DAMAGE(INT + "% increased Physical Attack Damage while holding a Shield"),
	SHIELD_MELEE_PHYSICAL_DAMAGE(INT + "% increased Melee Physical Damage while holding a Shield"),

	// accuracy
	ACCURACY(INT + "% increased Accuracy Rating"),
	ACC_PLUS(PLUS_INT + " to Accuracy Rating"),

	// evasion
	EVASION(INT + "% increased Evasion Rating"),
	EVASION_ARMOR(INT + "% increased Evasion Rating and Armour"),
	EVA_RAT(PLUS_INT + " to Evasion Rating"),
	EVADE_PROJECTILE(INT + "% more chance to Evade Projectile Attacks"),
	EVADE_MELEE(INT + "% less chance to Evade Melee Attacks"),
	EVADE_NEVER("Cannot Evade enemy Attacks"),

	// armor
	ARMOUR(INT + "% increased Armour"),
	ARMOR_FLAT(PLUS_INT + " to Armour"),
	ARMOR_EVADE("Converts all Evasion Rating to Armour. Dexterity provides no bonus to Evasion Rating"),

	// movement
	MOVEMENT_SPEED(INT + "% increased Movement Speed"),
	MOVEMENT_ENERGY_SHIELD(INT + "% increased Movement Speed while on Full Energy Shield"),
	MOVEMENT_IGNORE_ARMOR("Ignore all Movement Penalties from Armour"),
	REGEN(FLOAT + "% of Life Regenerated per second"),

	// global defence
	BLOCK_CHANCE(INT + "% additional Block Chance"),

	// shield defence
	SHIELD_DEFENCE(INT + "% increased Defences from equipped Shield"),
	SHIELD_BLOCK_CHANCE(INT + "% additional Chance to Block with Shields"),
	SHIELD_ELEMENTAL_RESIST(PLUS + INT + "% Elemental Resistances while holding a Shield"),
	SHIELD_DUAL_BLOCK(INT + "% additional Block Chance while Dual Wielding or holding a Shield"),

	// resistances
	LIGHTNING_RESIST(PLUS_INT + "% to Lightning Resistance"),
	LIGHTNING_RESIST_MAX(PLUS_INT + "% to maximum Lightning Resistance"),
	@Deprecated
	MAX_LIGHT(INT + "% to maximum Lightning Resistance"),

	ELEMENTAL_RESIST(INT + "% to all Elemental Resistances"),

	FIRE_RESIST(PLUS_INT + "% to Fire Resistance"),
	FIRE_RESIST_MAX(PLUS_INT + "% to maximum Fire Resistance"),

	COLD_RESIST(PLUS_INT + "% to Cold Resistance"),
	COLD_RESIST_CAP(PLUS_INT + "% to maximum Cold Resistance"),

	CHAOS_RESIST(PLUS_INT + "% to Chaos Resistance"),

	RESIST_ALL(PLUS_INT + "% to all Elemental Resistances"),

	FREEZE(INT + "% chance to Freeze"),
	COLD(INT + "% increased Cold Damage"),
	COLD_PEN("Damage Penetrates " + INT + "% Cold Resistance"),

	// damage reduction
	PHYSICAL_DAMAGE_REDUCTION(INT + "% additional Physical Damage Reduction"),
	ELEMENTAL_DAMAGE_REDUCTION_ON_CONSECRATE("Take " + INT + "% reduced Elemental Damage while on Consecrated Ground"),
	CRITICAL_REDUCE("You take " + INT + "% reduced Extra Damage from Critical Strikes"),

	// status
	CHILL_AVOID(INT + "% chance to Avoid being Chilled"),
	FROZEN_AVOID(INT + "% chance to Avoid being Frozen"),
	IGNITE_AVOID(INT + "% chance to Avoid being Ignited"),
	SHOCK_AVOID(INT + "% chance to Avoid being Shocked"),
	AVOID_STUN_ON_CAST(INT + "% chance to Avoid interruption from Stuns while Casting"),

	CHILL_DURATION(INT + "% increased Chill Duration on Enemies"),
	FREEZE_DURATION(INT + "% increased Freeze Duration on Enemies"),
	BLOCK_RECOVERY(INT + "% increased Block Recovery"),
	MANA_ON_BLOCK(PLUS + INT + " Mana gained when you Block"),
	CAST_SPEED(INT + "% increased Cast Speed"),
	CAST_SPEED_CHAOS(INT + "% increased Cast Speed with Chaos Skills"),

	// spells
	SPELL_DAMAGE(INT + "% increased Spell Damage"),
	SPELL_CSM(PLUS_INT + "% to Critical Strike Multiplier for Spells"),
	SPELL_CSC(INT + "% increased Critical Strike Chance for Spells"),

	// pen
	PEN_FIRE("Damage Penetrates " + INT + "% Fire Resistance"),
	WEAP_PEN_FIRE("Damage with Weapons Penetrates " + INT + "% Fire Resistance"),
	LIGHT_PEN("Damage Penetrates " + INT + "% Lightning Resistance"),
	ELEM_PEN("Damage Penetrates " + INT + "% Elemental Resistances"),

	// ignite
	IG_DUR(INT + "% increased Ignite Duration on Enemies"),
	IG_CHANCE(INT + "% chance to Ignite"),

	// life leech
	LIFE_LEECH(FLOAT + "% of Attack Damage Leeched as Life"),
	LIFE_LEECH_INSTANT("Life Leech applies instantly. Life Regeneration has no effect."),
	LIFE_LEECH_PHYSICAL(FLOAT + "% of Physical Attack Damage Leeched as Life"),
	LEECH_RATE(PLUS + INT + "% of maximum Life per second to maximum Life Leech rate"),
	LEECH_PER_SEC(INT + "% increased Life Leeched per second"),
	LEECH_STUN("Cannot be Stunned while Leeching"),
	LEECH_DAMAGE(INT + "% increased Damage while Leeching"),
	PHYS_LIFE(INT + "% of Physical Attack Damage Leeched as Life"),

	IMMUNE_BLEED("You are Immune to Bleeding while Leeching"),
	KILL_WEAK("Kill Enemies that have 20% or lower Life when Hit by your Skills"),
	OVERKILL("20% of Overkill Damage is Leeched as Life"),
	FILL_LEECH("Life Leech effects are not removed at Full Life"),

	// onslaught
	ONSLAUGHT_ON_RARE_OR_UNIQUE("Gain Onslaught for 20 seconds when you Kill a Rare or Unique Enemy"),
	ONSLAUGHT_ON_KILL_KILL(INT + "% chance to gain Onslaught for 3 seconds on Kill"),
	ONSLAUGHT_ON_FULL_FRENZY("You have Onslaught while on full Frenzy Charges"),
	EVASION_ON_ONSLAUGHT(INT + "% increased Evasion Rating while you have Onslaught"),

	// aoe
	AOE_RADIUS(INT + "% increased Radius of Area Skills"),
	AOE_DAMAGE(INT + "% increased Area Damage"),

	// stun
	STUN_DURATION(INT + "% increased Stun Duration on Enemies"),
	STUN_ON_ENEMY_FULL_LIFE("Your Damaging Hits always Stun Enemies that are on Full Life"),
	STUN_DURATION_ON_ENEMY_FULL_LIFE("100% increased Stun Duration against Enemies that are on Full Life"),
	STUN_DURATION_ON_ENEMY_LOW_LIFE("100% increased Stun Duration against Enemies that are on Low Life"),
	STUN_DURATION_DOUBLE("20% chance to double Stun Duration"),

	// damage avoidance
	STUN_AVOID(INT + "% chance to Avoid being Stunned"),
	STUN_NEVER("Cannot be Stunned"),
	STUN_AND_BLOCK_RECOVERY(INT + "% increased Stun and Block Recovery"),
	STUN_THRESHHOLD(INT + "% increased Stun Threshold"),

	//
	REFLECT_REDUCE(INT + "% reduced Reflected Physical Damage taken"),
	RECENT_KILL_DAMAGE(INT + "% more Damage if you've Killed Recently"),

	SINGLE_SPLASH("Single-target Melee attacks deal Splash Damage to surrounding targets"),
	SPLASH_LESS("50% less Damage to surrounding targets"),
	BLOCK_SPELLS(INT + "% of Block Chance applied to Spells"),
	BLOCK_SPELL_SHIELD(INT + "% additional Chance to Block Spells with Shields"),

	// mana leech
	MANA_LEECH(INT + "% increased Mana Leeched per second"),
	AD_MANA(FLOAT + "% of Attack Damage Leeched as Mana"),
	MANA_LEECH_RATE(PLUS_INT + "% of maximum Mana per second to maximum Mana Leech rate"),

	// minion
	MINION_INSTABILITY("Minions explode when reduced to Low Life, dealing 33% of their maximum Life as Fire Damage to surrounding Enemies"),
	MINION_ATTACK_SPEED("Minions have " + INT + "% increased Attack Speed"),
	MINION_CAST_SPEED("Minions have " + INT + "% increased Cast Speed"),
	MINION_SHIELD("All bonuses from an equipped Shield apply to your Minions instead of you"),
	MINION_BLOCK("Minions have " + INT + "% Chance to Block"),
	MINION_BLOCK_HEAL("Minions Recover " + INT + "% of their Maximum Life when they Block"),
	MINION_DAMAGE("Minions deal " + INT + "% increased Damage"),
	MINION_LL("Minions Leech " + FLOAT + "% of Damage as Life"),
	MINION_REGEN("Minions Regenerate " + FLOAT + "% Life per second"),
	MINION_ELEM_RESIST("Minions have +" + INT + "% to all Elemental Resistances"),
	MINION_CHAOS_RESIST("Minions have " + PLUS_INT + "% to Chaos Resistance"),
	MINION_MAX_LIFE("Minions have " + INT + "% increased maximum Life"),
	ZOMBIES(PLUS + " to Maximum number of Zombies"),
	ZOMBIE_MAX(PLUS_INT + " to Maximum number of Zombies"),
	SKEL(PLUS + " to Maximum number of Skeletons"),
	SKEL_MAX(PLUS_INT + " to Maximum number of Skeletons"),
	SPEC_MAX(PLUS_INT + " to Maximum number of Spectres"),
	ANC_BOND("You can't deal Damage with Skills yourself"),
	TOTEM_ADD("Can have up to " + INT + " additional Totem summoned at a time"),
	TOTEM_RESIST_ELEMENTAL("Totems gain " + PLUS_INT + "% to all Elemental Resistances"),

	// knockback
	KNOCKBACK_ON_HIT(INT + "% chance to Knock Enemies Back on hit"),
	KNOCKBACK_DISTANCE(INT + "% increased Knockback Distance"),

	// bleed
	BLEED("Attacks have " + INT + "% chance to cause Bleeding"),
	BLEED_DAMAGE_ON_KILL(INT + "% increased Damage if you've killed a Bleeding Enemy Recently"),
	BLEED_DMG(INT + "% increased Attack Damage against Bleeding Enemies"),
	BLEED_MELEE("Melee Attacks have " + INT + "% chance to cause Bleeding"),
	BLEED_POISON(INT + "% chance to Poison on Hit against Bleeding Enemies"),
	BLEED_CSM(PLUS_INT + "% to Critical Strike Multiplier against Bleeding Enemies"),
	BLEED_CSC(INT + "% increased Critical Strike Chance against Bleeding Enemies"),

	// poison
	POISON_DAMAGE(INT + "% increased Damage with Poison"),
	POISON_ON_HIT(INT + "% chance to Poison on Hit"),

	// energy shield
	ENERGY_SHIELD_MAX(PLUS + INT + " to maximum Energy Shield"),
	ENERGY_SHIELD_PCT(INT + "% increased maximum Energy Shield"),
	ENERGY_SHIELD_PCT_2(INT + "% more maximum Energy Shield"),
	ENERGY_SHIELD_MANA(INT + "% of Maximum Mana as Extra Maximum Energy Shield"),
	ENERGY_SHIELD_MANA_2("Gain " + INT + "% of Maximum Mana as Extra Maximum Energy Shield"),
	ENERGY_SHIELD_EVADE(INT + "% more chance to Evade while on full Energy Shield"),
	ENERGY_SHIELD_MORE_DAMAGE(INT + "% increased Damage while not on full Energy Shield"),
	ENERGY_SHIELD_RECHARG(INT + "% increased Energy Shield Recharge Rate"),
	ENERGY_SHIELD_SHIELD(INT + "% increased Energy Shield from equipped Shield"),
	ENERGY_SHIELD_LIFE_REGEN("Life Regeneration is applied to Energy Shield instead"),
	ENERGY_SHIELD_LIFE_LEECH("Life Leech is applied to Energy Shield instead"),
	ENERGY_SHIELD_BEFORE_MANA("Spend Energy Shield before Mana for Skill Costs"),
	ENERGY_SHIELD_PROTECT_MANA("Energy Shield protects Mana instead of Life"),

	//
	CHAIN("Skills Chain \\+1 times"),
	NO_BLEED("Moving while Bleeding doesn't cause you to take extra Damage"),
	CSC(INT + "% increased Critical Strike Chance"),
	ES_FASTER(INT + "% faster start of Energy Shield Recharge"),

	// dodge
	DODGE_ATTACK(INT + "% chance to Dodge Attacks"),
	DODGE_ATTACK2(INT + "% additional chance to Dodge Attacks"),
	DODGE_SPELL(INT + "% chance to Dodge Spell Damage"),
	DODGE_ACROBATICS("30% Chance to Dodge Attacks. 50% less Armour and Energy Shield, 30% less Chance to Block Spells and Attacks"),

	// aura
	AURA_RAD(INT + "% increased Radius of Aura Skills"),
	NON_CURSE_AURA(INT + "% increased effect of Non-Curse Auras you Cast"),
	CURSE_EFFECT(INT + "% increased Effect of your Curses"),
	CURSE_NUM("Enemies can have " + INT + " additional Curse"),
	CURSE_DUR(INT + "% increased Curse Duration"),
	CURSE_CAST_SPEED(INT + "% increased Cast Speed for Curses"),
	CURSE_RADIUS(INT + "% increased Radius of Curse Skills"),

	// charges
	FRENZ_CHARG(PLUS_INT + " to Maximum Frenzy Charges"),
	FR_CH_DUR(INT + "% increased Frenzy Charge Duration"),
	FRENZY_EVASION(INT + "% increased Evasion Rating per Frenzy Charge"),
	FRENZ_KILL(INT + "% chance to gain a Frenzy Charge on Kill"),
	END_DUR(INT + "% increased Endurance Charge Duration"),
	ENDURANCE_CHARGE_LIFE_REGEN(FLOAT + "% of maximum Life Regenerated per second per Endurance Charge"),
	MAX_END(INT + " to Maximum Endurance Charges"),
	CHARGE_ON_KILL(INT + "% chance to gain a Power, Frenzy or Endurance Charge on Kill"),
	POWER_CHARGE_DURATION(INT + "% increased Power Charge Duration"),
	POWER_CHARGE_ADDITIONAL(PLUS_INT + " to Maximum Power Charges"),
	CHARGE_SHARE("Share Endurance, Frenzy and Power Charges with nearby party members"),
	ENDURANCE_CHARGE_MAX(PLUS_INT + " to Maximum Endurance Charges"),
	ENDURANCE_CHARGE_ON_MELEE_CRITICAL(INT + "% chance to gain an Endurance Charge on Melee Critical Strike"),
	POWER_CHARGE_ON_BLOCK(INT + "% chance to gain a Power Charge when you Block"),

	// trap
	TRAP_RED(INT + "% reduced Damage taken from Traps and Mines"),

	// fortify
	MELEE_FORT(INT + "% increased Physical Melee Damage while you have Fortify"),
	INC_FORT(INT + "% increased effect of Fortify on You"),
	FORT_DUR(INT + "% increased Fortify duration"),
	FORT_MELEE(INT + "% chance to Fortify on Melee hit"),
	FORTIFY_ARMOUR(INT + "% increased Armour while you have Fortify"),
	FORT_ALLY("You and nearby Allies have " + INT + "% increased damage while you have Fortify"),

	// taunt
	TAUNT_HIT(INT + "% chance to Taunt on Hit"),
	TAUNT_REDUCE(INT + "% reduced Damage taken from Taunted Enemies"),

	// light
	LIGHT_RADIUS_ENERGY_SHIELD("Light Radius is based on Energy Shield instead of Life"),
	LIGHT_RADIUS(INT + "% increased Light Radius"),

	// misc
	STUN_CULL("Hits that Stun Enemies have Culling Strike"),
	ATTACK_CAST_SPEED(INT + "% increased Attack and Cast Speed if Energy"),
	ATK_CAST(INT + "% increased Attack and Cast Speed"),
	ATCK_CAST_MOVE(INT + "% increased Attack and Cast Speed if you've used a Movement Skill Recently"),
	LIFE_MANA_REGEN(INT + "% increased Recovery Rate of Life, Mana and Energy Shield if you've Killed an Enemy affected by your Damage Over Time Recently"),
	AVOID_ELEM(INT + "% chance to Avoid Elemental Status Ailments"),
	FREEZE_SHOCK_IGNITE(INT + "% chance to Freeze, Shock and Ignite"),
	NEVER_EVADED("Your hits can't be Evaded"),
	NEVER_CRIT("Never deal Critical Strikes"),
	ENEMY_NEVER_LEECH("Enemies Cannot Leech Life From You"),
	MANA_BEFORE_LIFE("When Hit, " + INT + "% of Damage is taken from Mana before Life"),
	ENEMY_ELEM_RESIST("Enemies you hit with Elemental Damage temporarily get +25% Resistance to those Elements and -50% Resistance to other Elements");

	private Pattern pattern;

	Stat(final String pattern)
	{
		this.pattern = pattern(pattern);
	}

	Stat(final String pattern, final Stat passiveSkillAttributeType)
	{
		this.pattern = pattern(pattern);
	}

	public Matcher matcher(final String skillDescription)
	{
		final Matcher matcher = pattern.matcher(skillDescription.toLowerCase());

		return matcher;
	}

	private Pattern pattern(final String pattern)
	{
		String newPattern = pattern;
		newPattern = newPattern.toLowerCase();
		newPattern = newPattern.replace("+", "\\+");
		newPattern = newPattern.replace(Regex.INT, "(\\d+)");
		newPattern = newPattern.replace(Regex.FLOAT, "(\\d*\\.?\\d*)");
		final Pattern compile = Pattern.compile("^" + newPattern + "$");
		return compile;
	}
}
