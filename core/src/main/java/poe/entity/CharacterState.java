package poe.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterState
{
	private final Map<Integer, PassiveSkill> passiveSkills;

	private int level;

	private List<CharacterItem> items;

	public CharacterState()
	{
		passiveSkills = new HashMap<>();
		items = new ArrayList<>();
	}

	public Map<Integer, PassiveSkill> getPassiveSkillMap()
	{
		return passiveSkills;
	}

	public Collection<PassiveSkill> getPassiveSkills()
	{
		return passiveSkills.values();
	}

	public List<Integer> getPassiveSkillIds()
	{
		return new ArrayList<>(passiveSkills.keySet());
	}

	public int size()
	{
		return passiveSkills.size();
	}

	public boolean contains(final int passiveSkillId)
	{
		return passiveSkills.containsKey(passiveSkillId);
	}

	public void addPassiveSkill(final PassiveSkill passiveSkill)
	{
		getPassiveSkillMap().put(passiveSkill.getId(), passiveSkill);
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(final int level)
	{
		this.level = level;
	}

	public void addItem(final CharacterItem item)
	{
		items.add(item);
	}

	public List<CharacterItem> getItems()
	{
		return items;
	}

	public void setItems(final List<CharacterItem> items)
	{
		this.items = items;
	}
}
