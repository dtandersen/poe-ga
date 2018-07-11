package us.davidandersen.poe.currency.entity;

import java.util.Objects;

public enum Currency
{
	CHAOS("chaos", "Chaos Orb"),
	FUSING("fuse", "Orb of Fusing"),
	SCOURING("scour", "Orb of Scouring"),
	ALTERATION("alt", "Orb of Alteration"),
	ALCHEMY("alch", "Orb of Alchemy"),
	GEMCUTTER("gcp", "Gemcutter's Prism"),
	EXALT("exa", "Exalted Orb"),
	CHROMATIC("chrom", "Chromatic Orb"),
	JEWELLER("jew", "Jeweller's Orb"),
	ENGINEER("engineers-orb", "Engineer's Orb"),
	CHANCE("chance", "Orb of Chance"),
	CHISEL("chisel", "Cartographer's Chisel"),
	BLESSED("blessed", "Blessed Orb"),
	REGRET("regret", "Orb of Regret"),
	REGAL("regal", "Regal Orb"),
	DIVINE("divine", "Divine Orb"),
	VAAL("vaal", "Vaal Orb"),
	ANNULMENT("orb-of-annulment", "Orb of Annulment"),
	BINDING("orb-of-binding", "Orb of Binding"),
	ANCIENT("ancient-orb", "Ancient Orb"),
	HORIZONS("orb-of-horizons", "Orb of Horizons"),
	HARBINGERS("harbingers-orb", "Harbinger's Orb"),
	WISDOM("wis", "Scroll of Wisdom"),
	PORTAL("port", "Portal Scroll"),
	ARMOURERS("scr", "Armourer's Scrap"),
	WHETSTONE("whe", "Blacksmith's Whetstone"),
	BAUBLE("ba", "Glassblower's Bauble"),
	TRANSMUTATION("tra", "Orb of Transmutation"),
	AUGMENTATION("aug", "Orb of Augmentation"),
	MIRROR("mir", "Mirror of Kalandra"),
	ETERNAL("?", "Eternal Orb"),
	PERANDUS("p", "Perandus Coin"),
	SILVER("silver", "Silver Coin"),
	ANNULMENT_SHARD("?", "Annulment Shard"),
	MIRROR_SHARD("mirror-shard", "Mirror Shard"),
	EXALTED_SHARD("exalted-shard", "Exalted Shard"),
	BINDING_SHARD("binding-shard", "Binding Shard"),
	HORIZON_SHARD("horizon-shard", "Horizon Shard"),
	HARBINGERS_SHARD("?", "Harbinger's Shard"),
	ENGINEERS_SHARD("engineers-shard", "Engineer's Shard"),
	ANCIENT_SHARD("ancient-shard", "Ancient Shard"),
	CHAOS_SHARD("chaos-shard", "Chaos Shard"),
	REGAL_SHARD("regal-shard", "Regal Shard"),
	APPRENTICE("apprentice-sextany", "Apprentice Cartographer's Sextant"),
	JOURNEYMAN("journeyman-sextant", "Journeyman Cartographer's Sextant"),
	MASTER("master-sextant", "Master Cartographer's Sextant"),
	XOPH("blessing-xoph", "Blessing of Xoph"),
	TUL("blessing-tul", "Blessing of Tul"),
	ESH("blessing-esh", "Blessing of Esh"),
	UUL_NETOL("blessing-uul-netol", "Blessing of Uul-Netol"),
	CHAYULA("blessing-chayula", "Blessing of Chayula"),
	SPLINTER_XOPH("splinter-xoph", "Splinter of Xoph"),
	SPLINTER_TUL("splinter-tul", "Splinter of Tul"),
	SPLINTER_ESH("splinter-esh", "Splinter of Esh"),
	SPLINTER_UUL_NETOL("splinter-uul", "Splinter of Uul-Netol"),
	SPLINTER_CHAYULA("splinter-chayula", "Splinter of Chayula");

	private final String shortName;

	private final String longName;

	Currency(final String shortName, final String longName)
	{
		this.shortName = shortName;
		this.longName = longName;
	}

	public static Currency longNameToCurrency(final String longName)
	{
		for (final Currency c : values())
		{
			if (Objects.equals(c.longName, longName)) { return c; }
		}

		return null;
	}

	public static Currency shortNameToCurrency(final String shortName)
	{
		for (final Currency c : values())
		{
			if (Objects.equals(c.shortName, shortName)) { return c; }
		}

		return null;
	}

	public String getShortName()
	{
		return shortName;
	}
}
