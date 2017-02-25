package poe.entity;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class PassiveSkillGraph
{
	private final UndirectedGraph<Integer, DefaultEdge> graph;

	public PassiveSkillGraph()
	{
		graph = new SimpleGraph<>(DefaultEdge.class);
	}

	public void add(final int passiveSkillId)
	{
		graph.addVertex(passiveSkillId);
	}

	public void add(final int passiveSkillId1, final int passiveSkillId2)
	{
		if (!graph.containsVertex(passiveSkillId1))
		{
			graph.addVertex(passiveSkillId1);
		}

		if (!graph.containsVertex(passiveSkillId2))
		{
			graph.addVertex(passiveSkillId2);
		}

		graph.addEdge(passiveSkillId1, passiveSkillId2);
	}

	public List<Integer> skills()
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
}
