package poe.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class PassiveSkillGraph
{
	private final UndirectedGraph<Integer, DefaultEdge> graph;

	private final List<PassiveSkill> passiveSkills;

	private final Set<Integer> neighbors;

	private PassiveSkill root;

	public PassiveSkillGraph()
	{
		graph = new SimpleGraph<>(DefaultEdge.class);
		passiveSkills = new ArrayList<>();
		neighbors = new HashSet<>();
	}

	public boolean addPassiveSkill(final PassiveSkill passiveSkill)
	{
		if (passiveSkill == null) { return false; }
		if (contains(passiveSkill.getId())) { return false; }
		// if (!neighbors.contains(passiveSkill.getId()) || passiveSkill.hasNeighbor(neighbors)) { return false; }
		if (!(passiveSkill.isNeighbor(passiveSkills) || passiveSkill.isNeighbor(root))) { return false; }

		addNeighbors(passiveSkill);
		passiveSkills.add(passiveSkill);
		graph.addVertex(passiveSkill.getId());

		return true;
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

	public void addRoot(final PassiveSkill root)
	{
		this.root = root;
		addNeighbors(root);
	}

	private void addNeighbors(final PassiveSkill passiveSkill)
	{
		for (final int passiveSkillId : passiveSkill.getOutputs())
		{
			neighbors.add(passiveSkillId);
		}
	}
}
