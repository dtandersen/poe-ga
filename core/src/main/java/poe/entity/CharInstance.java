package poe.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CharInstance
{
	public CharInstance(final PoeCharacter poeCharacter)
	{
	}

	/**
	 * Prototype of a character.
	 */
	public static class PoeCharacter
	{
		private final Map<Attribute, AttributeValue> attributes;

		private final StatValues stats;

		private final CharacterState character;

		private final CharacterClass characterClass;

		private int skillPoints = 123;

		public PoeCharacter(final CharacterClass characterClass, final int level)
		{
			this.characterClass = characterClass;
			attributes = new HashMap<>();
			stats = new StatValues();
			character = new CharacterState();

			character.setLevel(level);
			calculateBaseAttributes(level, characterClass);
		}

		public PoeCharacter(final CharacterBuilder characterBuilder)
		{
			this(characterBuilder.characterClass, characterBuilder.level);
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
			// todo: unit test passiveSkillCount() >= skillPoints
			if (passiveSkillCount() >= skillPoints)
			{
				return false;
			}
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
				if (!addPassiveSkill(passiveSkill) && passiveSkillCount() >= skillPoints)
				{
					return;
				}
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

		public boolean hasAmountOfSkills(final int size)
		{
			return passiveSkillCount() == size;
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
			}
			else if (!getPassiveAttributes().equals(other.getPassiveAttributes()))
			{
				return false;
			}
			if (attributes == null)
			{
				if (other.attributes != null)
				{
					return false;
				}
			}
			else if (!attributes.equals(other.attributes))
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

		public StatValue getStatValue(final Stat stat)
		{
			return stats.find(stat);
		}

		public float getStat(final Stat stat)
		{
			final StatValue statValue = getStatValue(stat);
			if (statValue == null)
			{
				return 0;
			}

			return statValue.getValue();
		}

		public void sneakyAdd(final List<PassiveSkill> passives)
		{
			// addPassiveSkills(passives);
			// return;
			List<PassiveSkill> tryToAdd = new ArrayList<>(passives);
			List<PassiveSkill> notAdded = new ArrayList<>();

			boolean keepGoing = true;
			while (keepGoing)
			{
				keepGoing = false;
				for (final PassiveSkill passive : tryToAdd)
				{
					if (addPassiveSkill(passive))
					{
						keepGoing = true;
					}
					else
					{
						notAdded.add(passive);
					}
				}
				tryToAdd = notAdded;
				notAdded = new ArrayList<>();
			}
		}

		public boolean hasPassiveNamed(final String string)
		{
			for (final PassiveSkill passive : character.getPassiveSkills())
			{
				if (Objects.equals(string, passive.getName()))
				{
					return true;
				}
			}

			return false;
		}

		public int passiveCount(final String string)
		{
			int count = 0;
			for (final PassiveSkill passive : character.getPassiveSkills())
			{
				if (Objects.equals(string, passive.getName()))
				{
					// return true;
					count++;
				}
			}

			return count;
		}

		public CharacterClass getCharacterClass()
		{
			return characterClass;
		}

		public int getLevel()
		{
			return character.getLevel();
		}

		// public void setLevel(final int level)
		// {
		// character.setLevel(level);
		// }

		public void addItem(final CharacterItem item)
		{
			character.addItem(item);
		}

		public List<StatValue> getAdjustedStats()
		{
			final StatBucket adjustedStatsCache = new StatBucket();

			attributes.values().forEach(attr -> adjustedStatsCache.add(StatValue.of(attr.getStat(), attr.getValue())));
			character.getPassiveSkills().forEach(skill -> skill.getStatValues().forEach(stat -> adjustedStatsCache.add(stat)));
			character.getItems().forEach(item -> item.forEachStatValue(stat -> adjustedStatsCache.add(stat)));

			return adjustedStatsCache.getStatValues();
		}

		public float getAdjustedStat(final Stat stat)
		{
			final Map<Stat, Float> stats = new HashMap<>();
			getAdjustedStats().stream().forEach(s -> stats.put(s.getStat(), s.getValue()));
			final Float statValue = stats.get(stat);
			if (statValue == null)
			{
				return 0;
			}

			return statValue;
		}

		public void setItems(final List<CharacterItem> items)
		{
			character.setItems(items);
		}

		public void setSkillPoints(final int skillPoints)
		{
			this.skillPoints = skillPoints;
		}

		public CharInstance build()
		{
			return new CharInstance(this);
		}

		public PoeCharacter withPassiveSkills(final List<PassiveSkill> passiveSkills)
		{
			addPassiveSkills(passiveSkills);
			return this;
		}
	}

	public static class CharacterBuilder
	{
		public int level = 1;

		private CharacterClass characterClass;

		public PoeCharacter build()
		{
			return new PoeCharacter(this);
		}

		public CharacterBuilder withCharacterClass(final CharacterClass marauder)
		{
			characterClass = marauder;
			return this;
		}
	}
}
