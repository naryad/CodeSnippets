package yn.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GraphUtils {

	public static <T> List<T> findSomePath(DirectedGraph<T> graph, T start, T end, List<T> path) {
		
		path = new ArrayList<T>(path);
		path.add(start);
		
		if (start.equals(end)) {
			return path;
		}
		
		if (graph.edgesFrom(start) == null) {
			return null;
		}
		
		for (T t : graph.edgesFrom(start)) {
			if (!path.contains(t)) {
				List<T> newPath = findSomePath(graph, t, end, path);
				
				if (newPath != null) {
					return newPath;
				}
			}
		}
		return null;
	}
	
	public static <T> List<T> findShortestPath(DirectedGraph<T> graph, T start, T end, List<T> path) {
		
		path = new ArrayList<T>(path);
		path.add(start);
		
		if (start.equals(end)) {
			return path;
		}
		if (graph.edgesFrom(start) == null) {
			return null;
		}
		
		List<T> minimalPath = null;		
		for (T t : graph.edgesFrom(start)) {
			if (!path.contains(t)) {
				List<T> newPath = findShortestPath(graph, t, end, path);
				
				if (newPath != null) {
					if (minimalPath == null || newPath.size() < minimalPath.size()) {
						minimalPath = newPath;
					}
				}
			}
		}
		return minimalPath;
	}

	/**
	 * Recursively performs a DFS from the specified node, marking all nodes
	 * encountered by the search.
	 * 
	 * @param sourceNode The node to begin the search from.
	 * @param g The graph in which to perform the search.
	 * @param visited A set of nodes that have already been visited.
	 * @param expanded A set of nodes that have been fully expanded.
	 */
	private static <T> void dfs(T sourceNode, DirectedGraph<T> g,
			Set<T> visited, Set<T> expanded) {
		System.out.print(sourceNode + " ");
		
		/*
		 * Check whether we've been here before. If so, we should stop the
		 * search.
		 */
		if (visited.contains(sourceNode)) {
			/*
			 * There are two cases to consider. First, if this node has already
			 * been expanded, then it's already been assigned a position in the
			 * final topological sort and we don't need to explore it again.
			 * However, if it hasn't been expanded, it means that we've just
			 * found a node that is currently being explored, and therefore is
			 * part of a cycle. In that case, we should report an error.
			 */
			if (expanded.contains(sourceNode))
				return;
			throw new IllegalArgumentException("Graph contains a cycle.");
		}

		/* Mark that we've been here */
		visited.add(sourceNode);

		/* Recursively explore all of the node's predecessors. */
		for (T predecessor : g.edgesFrom(sourceNode))
			dfs(predecessor, g, visited, expanded);

		/* Similarly, mark that this node is done being expanded. */
		expanded.add(sourceNode);
	}
	
	
	/**
	 * Recursively performs a BFS from the specified node, marking all nodes
	 * encountered by the search.
	 * 
	 * @param sourceNode The node to begin the search from.
	 * @param g The graph in which to perform the search.
	 * @param visited A set of nodes that have already been visited.
	 */
	private static <T> void bfs(T sourceNode, DirectedGraph<T> g) {
		Queue<T> queue = new LinkedList<T>();
		Set<T> visited = new HashSet<T>();
		
		queue.add(sourceNode);
		visited.add(sourceNode);
		T s = null;
		while ((s = queue.poll()) != null) {
			System.out.println(s);
			for (T u : g.edgesFrom(s)) {
				if (!visited.contains(u)) {
					queue.add(u);
					visited.add(s);					
				}
			}
		}
	}
	
	public static void main(String[] args) {
		DirectedGraph<String> graph = new DirectedGraph<String>();
		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("D");
		graph.addNode("C");
		
		graph.addEdge("A", "B");
		graph.addEdge("B", "D");
		graph.addEdge("D", "C");
		graph.addEdge("A", "C");
		
		
		printGraph(graph);
		List<String> path = new ArrayList<String>();
		System.out.println("Some path in the graph between A and C : " + findSomePath(graph, "A", "C", path));
		System.out.println("Shortest path in the graph  between A and C : " + findShortestPath(graph, "A", "C", path));
		
		Set<String> visited = new HashSet<String>();
		Set<String> expanded = new HashSet<String>();
		System.out.print("Order of nodes visited while doing a dfs on the graph : ");
		dfs("A",graph,visited,expanded);
		visited.clear();
		System.out.println("\nOrder of nodes visited while doing a bfs on the graph : ");
		bfs("A",graph);
	}

	private static <T> void printGraph(DirectedGraph<T> graph) {
		for (T t : graph) {
			System.out.println(t + " ==> " + graph.edgesFrom(t));
		}
	}
}
