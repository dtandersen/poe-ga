package poe.util.graph;

import java.util.List;
import java.util.Set;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class IntGraph
{
	public UndirectedGraph<Integer, DefaultEdge> graph;

	public IntGraph(final UndirectedGraph<Integer, DefaultEdge> graph)
	{
		this.graph = graph;
	}

	public IntGraph()
	{
		this(new SimpleGraph<>(DefaultEdge.class));
	}

	public void addVertex(final int vertex)
	{
		graph.addVertex(vertex);
	}

	public boolean containsVertex(final Integer vertex)
	{
		return graph.containsVertex(vertex);
	}

	public void addEdge(final Integer v1, final Integer v2)
	{
		graph.addEdge(v1, v2);
	}

	public List<Integer> neighbors(final int vertex)
	{
		return Graphs.neighborListOf(graph, vertex);
	}

	public Set<Integer> all()
	{
		return graph.vertexSet();
	}

	public void remove(final Integer v)
	{
		if (graph.containsVertex(v))
		{
			graph.removeVertex(v);
		}
	}
}
