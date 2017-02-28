package poe.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PoeCharacter
{
	private final Map<Attribute, AttributeValue> attributes;

	private final StatValues stats;

	private final CharacterState character;

	public PoeCharacter(final CharacterClass characterClass)
	{
		attributes = new HashMap<>();
		stats = new StatValues();
		character = new CharacterState();

		calculateBaseAttributes(1, characterClass);
	}

	public float getAttributeValue(final Attribute attribute)
	{
		return attributes.get(attribute).getValue();
	}

	public Collection<AttributeValue> getAttributes()
	{
		return attributes.values();
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

	public boolean addPassiveSkill(final PassiveSkill passiveSkill)
	{
		if (passiveSkill == null)
		{
			return false;
		}
		if (hasPassiveSkill(passiveSkill.getId()))
		{
			return false;
		}
		if (character.size() > 0 && passiveSkill.isClassStartingNode())
		{
			return false;
		}
		if (!hasNeighbor(passiveSkill))
		{
			return false;
		}

		character.addPassiveSkill(passiveSkill);

		if (passiveSkill.getStatValues() == null)
		{
			return true;
		}

		for (final StatValue statValue : passiveSkill.getStatValues())
		{
			stats.increment(statValue);
		}

		return true;
	}

	public void addPassiveSkills(final List<PassiveSkill> passiveSkills)
	{
		for (final PassiveSkill passiveSkill : passiveSkills)
		{
			addPassiveSkill(passiveSkill);
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

	private void calculateBaseAttributes(final int level, final CharacterClass characterClass)
	{
		final Attribute attribute = Attribute.STRENGTH;
		attributes.put(attribute, new AttributeValue(attribute, characterClass.getStrength()));
		final Attribute attribute1 = Attribute.DEXTERITY;
		attributes.put(attribute1, new AttributeValue(attribute1, characterClass.getDexterity()));
		final Attribute attribute2 = Attribute.INTELLIGENCE;
		attributes.put(attribute2, new AttributeValue(attribute2, characterClass.getIntelligence()));
		final Attribute attribute3 = Attribute.LIFE;
		attributes.put(attribute3, new AttributeValue(attribute3, 38 + level * 12 + getAttributeValue(Attribute.STRENGTH) / 2));
		final Attribute attribute4 = Attribute.MANA;
		attributes.put(attribute4, new AttributeValue(attribute4, (40 - 6) + level * 6 + getAttributeValue(Attribute.INTELLIGENCE) / 2));
		final float attributeValue = getAttributeValue(Attribute.DEXTERITY);
		final int dexdiv5 = PoeCharacter.div5(attributeValue);
		final Attribute attribute5 = Attribute.EVASION_RATING;
		attributes.put(attribute5, new AttributeValue(attribute5, 53 + level * 3 + dexdiv5));
		final Attribute attribute6 = Attribute.ACCURACY;
		attributes.put(attribute6, new AttributeValue(attribute6, getAttributeValue(Attribute.DEXTERITY) * 2));
	}

	private boolean hasNeighbor(final PassiveSkill passiveSkill)
	{
		if (this.character.size() == 0)
		{
			return true;
		}
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
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final PoeCharacter other = (PoeCharacter)obj;
		if (getPassiveAttributes() == null)
		{
			if (other.getPassiveAttributes() != null)
			{
				return false;
			}
		} else if (!getPassiveAttributes().equals(other.getPassiveAttributes()))
		{
			return false;
		}
		if (attributes == null)
		{
			if (other.attributes != null)
			{
				return false;
			}
		} else if (!attributes.equals(other.attributes))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "ImmutableCharacterProxy[stats=" + attributes + ", passiveAttributes=" + getPassiveAttributes() + "]";
	}

	public static int div5(final float attributeValue)
	{
		final float gg = attributeValue % 5;
		if (gg == 0)
		{
			return (int)(attributeValue / 5);
		}

		final int g = (int)(attributeValue - gg);
		final int f = g / 5;
		return f;
	}
}