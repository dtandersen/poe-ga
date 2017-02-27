package poe.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import poe.entity.Attribute;
import poe.entity.AttributeValue;
import poe.entity.CharacterClass;
import poe.entity.CharacterState;
import poe.entity.PassiveSkill;
import poe.entity.StatValue;
import poe.entity.StatValues;

public final class PoeCharacter
{
	private final Map<Attribute, AttributeValue> attributes;

	private final StatValues stats;

	private final CharacterState character;

	public PoeCharacter()
	{
		attributes = new HashMap<>();
		stats = new StatValues();
		character = new CharacterState();
	}

	public float getAttributeValue(final Attribute attribute)
	{
		return attributes.get(attribute).getValue();
	}

	public Collection<AttributeValue> getAttributes()
	{
		return attributes.values();
	}

	public void setAttributeValue(final Attribute attribute, final float value)
	{
		attributes.put(attribute, new AttributeValue(attribute, value));
	}

	public Collection<StatValue> getStatValues()
	{
		return getPassiveAttributes().list();
	}

	public List<Integer> getPassiveSkillIds()
	{
		return character.getPassiveSkillIds();
	}

	public StatValues getPassiveAttributes()
	{
		return stats;
	}

	public void apply(final StatValue attribute)
	{
		stats.increment(attribute);
	}

	public boolean addPassiveSkill(final PassiveSkill passiveSkill)
	{
		if (passiveSkill == null) { return false; }
		if (hasPassiveSkill(passiveSkill.getId())) { return false; }
		if (!hasNeighbor(passiveSkill)) { return false; }

		character.addPassiveSkill(passiveSkill);
		apply(passiveSkill);

		return true;
	}

	public void addPassiveSkills(final List<PassiveSkill> passiveSkills)
	{
		for (final PassiveSkill passiveSkill : passiveSkills)
		{
			addPassiveSkill(passiveSkill);
		}
	}

	private void apply(final PassiveSkill passive)
	{
		if (passive.getAttributes() == null) { return; }
		for (final StatValue attribute : passive.getAttributes())
		{
			apply(attribute);
		}
	}

	public int passiveSkillCount()
	{
		return character.size();
	}

	public boolean hasPassiveSkill(final PassiveSkill passiveSkill)
	{
		return hasPassiveSkill(passiveSkill.getId());
	}

	public boolean hasAllPassiveSkills(final List<PassiveSkill> skills)
	{
		return passiveSkillCount() == skills.size();
	}

	public boolean hasPassiveSkill(final int passiveSkillId)
	{
		return character.contains(passiveSkillId);
	}

	public Collection<PassiveSkill> getPassiveSkills()
	{
		return character.getPassiveSkills();
	}

	private boolean hasNeighbor(final PassiveSkill passiveSkill)
	{
		if (this.character.size() == 0) { return true; }
		return passiveSkill.isNeighbor(character.getPassiveSkills());
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPassiveAttributes() == null) ? 0 : getPassiveAttributes().hashCode());
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		final PoeCharacter other = (PoeCharacter)obj;
		if (getPassiveAttributes() == null)
		{
			if (other.getPassiveAttributes() != null) { return false; }
		}
		else if (!getPassiveAttributes().equals(other.getPassiveAttributes())) { return false; }
		if (attributes == null)
		{
			if (other.attributes != null) { return false; }
		}
		else if (!attributes.equals(other.attributes)) { return false; }
		return true;
	}

	@Override
	public String toString()
	{
		return "ImmutableCharacterProxy[stats=" + attributes + ", passiveAttributes=" + getPassiveAttributes() + "]";
	}

	public void baseAttributes(final int level, final CharacterClass characterClass)
	{
		setAttributeValue(Attribute.STRENGTH, characterClass.getStrength());
		setAttributeValue(Attribute.DEXTERITY, characterClass.getDexterity());
		setAttributeValue(Attribute.INTELLIGENCE, characterClass.getIntelligence());
		setAttributeValue(Attribute.LIFE, 38 + level * 12 + getAttributeValue(Attribute.STRENGTH) / 2);
		setAttributeValue(Attribute.MANA, (40 - 6) + level * 6 + getAttributeValue(Attribute.INTELLIGENCE) / 2);
		final float attributeValue = getAttributeValue(Attribute.DEXTERITY);
		final int dexdiv5 = CreateCharacter.div5(attributeValue);
		setAttributeValue(Attribute.EVASION_RATING, 53 + level * 3 + dexdiv5);
		setAttributeValue(Attribute.ACCURACY, getAttributeValue(Attribute.DEXTERITY) * 2);
	}

	public Collection<PassiveSkill> getPassiveSkillsWithoutRoot()
	{
		return character.getPassiveSkillMap().values().stream().filter(new Predicate<PassiveSkill>() {
			@Override
			public boolean test(final PassiveSkill t)
			{
				return !t.isClassStartingNode();
			}
		}).collect(Collectors.toList());
	}
}