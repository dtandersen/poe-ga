package poe.entity;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class PassiveSkillGraph
{
	private final UndirectedGraph<Integer, DefaultEdge> graph;

	private final List<PassiveSkill> passiveSkills;

	public PassiveSkillGraph()
	{
		graph = new SimpleGraph<>(DefaultEdge.class);
		passiveSkills = new ArrayList<>();
	}

	public void addPassiveSkill(final PassiveSkill passiveSkill)
	{
		passiveSkills.add(passiveSkill);
		graph.addVertex(passiveSkill.getId());
	}

	public List<Integer> getPassiveSkillIds()
	{
		return new ArrayList<>(graph.vertexSet());
	}

	public int size()
	{
		return graph.vertexSet().size();
	}

	public boolean contains(final int id)
	{
		return graph.containsVertex(id);
	}

	public List<PassiveSkill> getPassiveSkills()
	{
		return passiveSkills;
	}
}
