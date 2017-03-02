package poe.entity;

import java.util.List;
import java.util.Objects;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class PassiveSkillTree
{
	private final List<PassiveSkill> passiveSkills;

	private final UndirectedGraph<Integer, DefaultEdge> graph;

	public PassiveSkillTree(final List<PassiveSkill> passiveSkills)
	{
		this.passiveSkills = passiveSkills;
		graph = new SimpleGraph<>(DefaultEdge.class);
		for (final PassiveSkill passiveSkill : passiveSkills)
		{
			final int id = passiveSkill.getId();
			graph.addVertex(id);
		}
		for (final PassiveSkill passiveSkill : passiveSkills)
		{
			final Integer v1 = passiveSkill.getId();
			final List<Integer> outputs = passiveSkill.getOutputs();
			for (final Integer neighborId : outputs)
			{
				final Integer v2 = neighborId;
				if (graph.containsVertex(v2))
				{
					graph.addEdge(v1, v2);
				}
			}
		}
	}

	public List<PassiveSkill> passiveSkills()
	{
		return passiveSkills;
	}

	public PassiveSkill findByName(final String string)
	{
		for (final PassiveSkill ps : passiveSkills)
		{
			if (Objects.equals(string, ps.getName())) { return ps; }
		}
		return null;
	}

	public PassiveSkill find(final int id)
	{
		for (final PassiveSkill ps : passiveSkills)
		{
			if (id == ps.getId()) { return ps; }
		}
		return null;
	}

	public List<Integer> neighbors(final int passiveSkillId)
	{
		return Graphs.neighborListOf(graph, passiveSkillId);
	}

	public int count()
	{
		return passiveSkills.size();
	}
}
