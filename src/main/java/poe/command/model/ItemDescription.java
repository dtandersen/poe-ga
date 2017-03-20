package poe.command.model;

import java.util.ArrayList;
import java.util.List;

public class ItemDescription
{
	private final List<String> skillDescriptions = new ArrayList<>();

	public ItemDescription()
	{
	}

	public void addSkillDescription(final String stat)
	{
		skillDescriptions.add(stat);
	}

	public List<String> getSkillDescriptions()
	{
		return skillDescriptions;
	}
}
