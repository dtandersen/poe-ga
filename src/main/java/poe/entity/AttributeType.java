package poe.entity;

import static poe.entity.Regex.FLOAT;
import static poe.entity.Regex.INT;
import static poe.entity.Regex.PLUS;
import static poe.entity.Regex.PLUS_INT;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum AttributeType
{
	UNKNOWN("Unknown"),
	// life
	MAX_LIFE(INT + "% increased maximum Life"),
	MAX_LIFE_PLUS(PLUS + INT + " to maximum Life"),
	FLASK_LIFE(INT + "% increased Life Recovery from Flasks"),
	FLASK_RECOVERY(INT + "% increased Flask Recovery Speed"),
	STRENGTH(PLUS + INT + " to Strength"),
	DEXTERITY(PLUS + INT + " to Dexterity"),
	INTELLIGENCE(PLUS_INT + " to Intelligence"),

	// mana
	MANA(INT + "% increased maximum Mana"),
	REDUCE_MANA(INT + "% reduced Mana Cost of Skills"),
	MANA_REGEN(INT + "% increased Mana Regeneration Rate"),
	MANA_RESV(INT + "% reduced Mana Reserved"),

	// flask
	FLASK_GAIN(INT + "% increased Flask Charges gained"),
	AVOID_FREEZE(INT + "% chance to avoid Freeze, Shock, Ignite and Bleed while using a Flask"),
	ELEM_FLASK(INT + "% reduced Elemental Damage taken while using a Flask"),

	ATTACK_SPEED(INT + "% increased Attack Speed"),
	STUN_THRESH(INT + "% reduced Enemy Stun Threshold"),

	PHYSICAL_2H(INT + "% increased Physical Damage with Two Handed Melee Weapons"),
	ACCURACY_2H(INT + "% increased Accuracy Rating with Two Handed Melee Weapons"),
	ATTACK_SPEED_2H(INT + "% increased Attack Speed with Two Handed Melee Weapons"),
	STUN_DURATION_2H(INT + "% increased Stun Duration with Two Handed Melee Weapons on Enemies"),
	DAMAGE_2H(INT + "% increased Damage with Two Handed Weapons"),

	// extra damage
	CHAOS_DAMAGE(INT + "% increased Chaos Damage"),
	PHYSICAL_DAMAGE(INT + "% increased Physical Damage"),
	ELEM_DAMAGE(INT + "% increased Elemental Damage"),
	FIRE_DAMAGE(INT + "% increased Fire Damage"),
	PROJ_DAMAGE(INT + "% increased Projectile Damage"),
	BURN_DAMAGE(INT + "% increased Burning Damage"),
	LIGHT_DAMAGE(INT + "% increased Lightning Damage"),
	TRAP_DAMAGE(INT + "% increased Trap Damage"),
	DOT_DAMAGE(INT + "% increased Damage over Time"),
	FROZ_DMG(INT + "% increased Damage against Frozen, Shocked or Ignited Enemies"),
	FLASK_DMG(INT + "% increased Damage while using a Flask"),
	PHYS_FIRE("Gain " + INT + "% of Physical Damage as Extra Fire Damage"),

	// skills
	SKILL_DUR(INT + "% increased Skill Effect Duration"),

	// totem
	TOTEM_DAMAGE(INT + "% increased Totem Damage"),
	TOTEM_PLACE(INT + "% increased Totem Placement speed"),
	TOTEM_LIFE(INT + "% increased Totem Life"),
	TOTEM_HELM("Skills in your Helm can have up to 1 additional Totem Summoned at a time"),
	TOTEM_RES("Totems have " + INT + "% additional Physical Damage Reduction"),

	// projectiles
	PROJ_SPEED(INT + "% increased Projectile Speed"),
	PROJ_FAR("Projectile Attacks gain damage as they travel farther, dealing up to 30% more Damage to targets"),
	PROJ_SKILL("Skills fire an additional Projectile"),

	// melee
	MELEE_DMG(INT + "% increased Melee Damage"),
	PHYS_MELEE(INT + "% increased Melee Physical Damage"),
	AS_MELEE(INT + "% increased Melee Attack Speed"),

	// axe
	PHYSICAL_AXE(INT + "% increased Physical Damage with Axes"),
	ATTACK_SPEED_AXE(INT + "% increased Attack Speed with Axes"),

	// sword
	PHYSICAL_SWORD(INT + "% increased Physical Damage with Swords"),
	ATTACK_SPEED_SWORD(INT + "% increased Attack Speed with Swords"),

	// claw
	PHYSICAL_CLAW(INT + "% increased Physical Damage with Claws"),

	// shield
	ATTACK_SPEED_SHIELD(INT + "% increased Attack Speed while holding a Shield"),

	// mace
	CSM_MACE(PLUS_INT + "% to Critical Strike Multiplier with Maces"),

	// dual
	DUAL_PHYS(INT + "% increased Physical Weapon Damage while Dual Wielding"),

	BLOCK(INT + "% additional Block Chance"),
	ACCURACY(INT + "% increased Accuracy Rating"),
	ACC_PLUS(PLUS_INT + " to Accuracy Rating"),
	EVASION(INT + "% increased Evasion Rating"),

	WEAPON_ELEMENTAL_DAMAGE(INT + "% increased Elemental Damage with Weapons"),
	ARMOUR(INT + "% increased Armour"),
	ARMOR_FLAT(PLUS_INT + " to Armour"),
	MOVEMENT_SPEED(INT + "% increased Movement Speed"),
	REGEN(FLOAT + "% of Life Regenerated per second"),

	SHIELD(INT + "% increased Defences from equipped Shield"),
	BLOCK_SHIELD(INT + "% additional Chance to Block with Shields"),
	ELEM_SHIELD(PLUS + INT + "% Elemental Resistances while holding a Shield"),

	// RESIST
	LIGHTNING(INT + "% to Lightning Resistance"),
	ELEMENTAL_RESIST(INT + "% to all Elemental Resistances"),
	FIRE_RESIST(INT + "% to Fire Resistance"),
	COLD_RESIST(INT + "% to Cold Resistance"),
	FREEZE(INT + "% chance to Freeze"),
	COLD(INT + "% increased Cold Damage"),
	COLD_PEN("Damage Penetrates " + INT + "% Cold Resistance"),
	MAX_LIGHT(INT + "% to maximum Lightning Resistance"),

	MELEE_PHYSICAL_SHIELD(INT + "% increased Melee Physical Damage while holding a Shield"),
	CHILL_DURATION(INT + "% increased Chill Duration on Enemies"),
	FREEZE_DURATION(INT + "% increased Freeze Duration on Enemies"),
	BLOCK_RECOVERY(INT + "% increased Block Recovery"),
	MANA_ON_BLOCK(PLUS + INT + " Mana gained when you Block"),
	CAST_SPEED(INT + "% increased Cast Speed"),
	SPELL_DAMAGE(INT + "% increased Spell Damage"),

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
	LEECH_RATE(PLUS + INT + "% of maximum Life per second to maximum Life Leech rate"),
	LEECH_PER_SEC(INT + "% increased Life Leeched per second"),
	LEECH_STUN("Cannot be Stunned while Leeching"),
	PHYS_LIFE(INT + "% of Physical Attack Damage Leeched as Life"),

	IMMUNE_BLEED("You are Immune to Bleeding while Leeching"),
	ONSLAUGHT("Gain Onslaught for 20 seconds when you Kill a Rare or Unique Enemy"),
	ONSLAUGHT_KILL("% chance to gain Onslaught for 3 seconds on Kill"),
	KILL_WEAK("Kill Enemies that have 20% or lower Life when Hit by your Skills"),
	OVERKILL("20% of Overkill Damage is Leeched as Life"),
	FILL_LEECH("Life Leech effects are not removed at Full Life"),
	AOE_RADIUS(INT + "% increased Radius of Area Skills"),

	// stun
	STUN("Your Damaging Hits always Stun Enemies that are on Full Life"),
	FULL_LIFE_STUN("100% increased Stun Duration against Enemies that are on Full Life"),
	LOW_LIFE_STUN("100% increased Stun Duration against Enemies that are on Low Life"),
	DOUBLE_STUN("20% chance to double Stun Duration"),
	AVOID_STUN(INT + "% chance to Avoid being Stunned"),
	STUN_RECOV(INT + "% increased Stun and Block Recovery"),
	STUN_DUR(INT + "% increased Stun Duration on Enemies"),

	// low life
	HIT_LL("Hits deal " + INT + "% increased Damage against Enemies that are on Low Life"),

	REFLECT_REDUCE(INT + "% reduced Reflected Physical Damage taken"),
	RECENT_KILL_DAMAGE(INT + "% more Damage if you've Killed Recently"),

	SINGLE_SPLASH("Single-target Melee attacks deal Splash Damage to surrounding targets"),
	SPLASH_LESS("50% less Damage to surrounding targets"),
	BLOCK_SPELLS(INT + "% of Block Chance applied to Spells"),

	// mana leech
	MANA_LEECH(INT + "% increased Mana Leeched per second"),
	AD_MANA(FLOAT + "% of Attack Damage Leeched as Mana"),

	// minion
	MINION_INSTABILITY("Minions explode when reduced to Low Life, dealing 33% of their maximum Life as Fire Damage to surrounding Enemies"),
	MINION_BLOCK("Minions have " + INT + "% Chance to Block"),
	MINION_BLOCK_HEAL("Minions Recover " + INT + "% of their Maximum Life when they Block"),
	MINION_DAMAGE("Minions deal " + INT + "% increased Damage"),
	MINION_LL("Minions Leech " + FLOAT + "% of Damage as Life"),
	ZOMBIES(PLUS + " to Maximum number of Zombies"),
	SKEL(PLUS + " to Maximum number of Skeletons"),
	ZOMBIE_MAX(PLUS_INT + " to Maximum number of Zombies"),
	SKEL_MAX(PLUS_INT + " to Maximum number of Skeletons"),
	MIN_REGEN("Minions Regenerate " + INT + "% Life per second"),
	SPEC_MAX(PLUS_INT + " to Maximum number of Spectres"),
	ANC_BOND("You can't deal Damage with Skills yourself"),
	TOTEM_ADD("Can have up to " + INT + " additional Totem summoned at a time"),

	// bleed
	BLEED("Attacks have " + INT + "% chance to cause Bleeding"),
	KILL_BLEED(INT + "% increased Damage if you've killed a Bleeding Enemy Recently"),
	BLEED_DMG(INT + "% increased Attack Damage against Bleeding Enemies"),

	MAX_ENERGY_SHIELD(PLUS + INT + " to maximum Energy Shield"),
	ENERGY_SHIELD_PCT(INT + "% increased maximum Energy Shield"),
	ES_RECHARG(INT + "% increased Energy Shield Recharge Rate"),
	CHAIN("Skills Chain \\+1 times"),
	CSM_BLEED(PLUS_INT + "% to Critical Strike Multiplier against Bleeding Enemies"),
	CSC_BLEED(INT + "% increased Critical Strike Chance against Bleeding Enemies"),
	NO_BLEED("Moving while Bleeding doesn't cause you to take extra Damage"),
	CSC(INT + "% increased Critical Strike Chance"),
	ES_FASTER(INT + "% faster start of Energy Shield Recharge"),

	// dodge
	DOGE(INT + "% chance to Dodge Attacks"),
	DOGE_sPELL(INT + "% chance to Dodge Spell Damage"),

	// aura
	AURA_RAD(INT + "% increased Radius of Aura Skills"),
	NON_CURSE_AURA(INT + "% increased effect of Non-Curse Auras you Cast"),
	CURSE_EFFECT(INT + "% increased Effect of your Curses"),
	CURSE_NUM("Enemies can have " + INT + " additional Curse"),
	CURSE_DUR(INT + "% increased Curse Duration"),

	// charges
	FRENZ_CHARG(PLUS_INT + " to Maximum Frenzy Charges"),
	FR_CH_DUR(INT + "% increased Frenzy Charge Duration"),
	END_DUR(INT + "% increased Endurance Charge Duration"),
	MAX_END(INT + " to Maximum Endurance Charges"),

	// trap
	TRAP_RED(INT + "% reduced Damage taken from Traps and Mines"),

	// fortify
	MELEE_FORT(INT + "% increased Physical Melee Damage while you have Fortify"),
	INC_FORT(INT + "% increased effect of Fortify on You"),
	FORT_DUR(INT + "% increased Fortify duration"),

	// misc
	STUN_CULL("Hits that Stun Enemies have Culling Strike"),
	LIGHT_RAD("Light Radius is based on Energy Shield instead of Life"),
	BOW_KB("Knocks Back Enemies if you get a Critical Strike with a Bow");

	private Pattern pattern;

	AttributeType(final String pattern)
	{
		this.pattern = Pattern.compile(pattern);
	}

	public Matcher matcher(final String skillDescription)
	{
		final Matcher matcher = pattern.matcher(skillDescription);

		return matcher;
	}
}
