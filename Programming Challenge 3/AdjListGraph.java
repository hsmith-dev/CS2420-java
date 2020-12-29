package lab12;

import components.list.List;
import components.list.ListOnArrays;
import components.list.ListOnJavaArrayList;
import components.map.Map;
import components.map.MapOnHashTable;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * Adjacency List representation of the Graph.
 * 
 * @author Harrison Smith
 *
 */
public class AdjListGraph implements Graph {
	public static class Edge {
		int dest;
		int cost;

		public Edge(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

	}

	/*
	 * Private members
	 */
	private List<List<Edge>> adjList;
	private Map<String, Integer> vertexMap;
	private Map<Integer, String> intVertexMap;
	private int nodeId;

	/**
	 * Resets the matrix to the given number of vertices and resets the other fields
	 * to their initial values.
	 * 
	 * @param numVertices number of vertices for the new graph
	 * @modifies this
	 */
	private void resize(int numVertices) {
		adjList = new ListOnJavaArrayList<List<Edge>>();
		for (int i = 0; i < numVertices; i++)
			for (int j = 0; j < numVertices; j++)
				adjList.add(new ListOnJavaArrayList<Edge>());
		vertexMap = new MapOnHashTable<String, Integer>();
		intVertexMap = new MapOnHashTable<Integer, String>();
		nodeId = 0;
	}

	/**
	 * No argument constructor.
	 * 
	 * @param numVertices number of vertices
	 */
	public AdjListGraph(int numVertices) {
		resize(numVertices);
	}

	/**
	 * Constructor from a file.
	 * 
	 * @param file path of a file in the specified format
	 * @requires the file is in this format: first line is an integer, indicating
	 *           number of vertices (n), followed by n lines, each containing an
	 *           edge in this comma-separated format: (src,dst,cost)
	 */
	public AdjListGraph(String file) {
		SimpleReader in = new SimpleReader1L(file);
		int numVertices = Integer.parseInt(in.nextLine());
		resize(numVertices);
		while (!in.atEOS()) {
			String line = in.nextLine();
			String[] edgeParts = line.split(",");
			String src = edgeParts[0];
			if (!vertexMap.hasKey(src))
				addVertex(src);
			String dst = edgeParts[1];
			if (!vertexMap.hasKey(dst))
				addVertex(dst);
			int cost = Integer.parseInt(edgeParts[2]);
			addEdge(src, dst, cost);
		}
		in.close();
	}

	@Override
	public void addVertex(String label) {
		vertexMap.add(label, nodeId);
		intVertexMap.add(nodeId, label);
		nodeId++;
	}

	@Override
	public void addEdge(String src, String dst, int cost) {
		assert cost >= 0 : "Violation of: edge cost is non negative";
		assert vertexMap.hasKey(src) : "Violation of: src is a vertex already in the graph";
		assert vertexMap.hasKey(dst) : "Violation of: dst is a vertex already in the graph";

		int srcIndex = vertexMap.value(src);
		int dstIndex = vertexMap.value(dst);

		Edge edge = new Edge(dstIndex, cost);
		adjList.get(srcIndex).add(edge);
	}

	@Override
	public int pathCost(List<String> path) {
		int totalCost = 0;
		int edgeCost = 0;
		int i = 0;
		while (edgeCost < INFINITY && i < path.size() - 1) {
			int sourceInd = vertexMap.value(path.get(i));
			int dstInd = vertexMap.value(path.get(i + 1));
			List<Edge> source = adjList.get(sourceInd);

			for (Edge e : source) {
				if (e.dest == dstInd)
					edgeCost = e.cost;
			}
			totalCost += edgeCost;
			i++;
		}
		// if there was no path avaliable, it will be infinite cost
		if(totalCost == 0)
			totalCost = INFINITY;
		return edgeCost < INFINITY ? totalCost : INFINITY;
	}

	/*
	 * Methods from Object
	 */
	@Override
	public String toString() {
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("digraph g {\n");
		for (int i = 0; i < adjList.size(); i++)
			for (Edge e : adjList.get(i)) {
				sbuilder.append(String.format("%s->%s [label=%d];\n", intVertexMap.value(i), intVertexMap.value(e.dest),
						e.cost));
			}
		sbuilder.append("}\n");
		return sbuilder.toString();
	}

	/**
	 * Visits every list and all it's children
	 */
	@Override
	public List<String> dfs(String start) {
		List<Integer> indxResult = new ListOnJavaArrayList<Integer>();
		dfs(vertexMap.value(start), indxResult);
		List<String> result = new ListOnJavaArrayList<String>();
		for (Integer indx : indxResult)
			result.add(intVertexMap.value(indx));
		return result;
	}

	/**
	 * Recursively computes the depth first traversal from the given node by adding
	 * the next node in DFS to the given list.
	 * 
	 * @param srcIdx       index of the starting node
	 * @param visitedNodes indexes of the nodes visited in DFS so far
	 */
	private void dfs(int srcIdx, List<Integer> visitedNodes) {
		visitedNodes.add(srcIdx);
		for (int dstIdx = 0; dstIdx < adjList.get(srcIdx).size(); dstIdx++) {
			// used to tell if it has been visited
			boolean check = true;
			// if there is an edge and the dstIdx node has not been visited yet
			Edge edge = adjList.get(srcIdx).get(dstIdx);
			for (int nodeIndx : visitedNodes)
				if(edge.dest == nodeIndx)
					check = false;
			if(check == true)
				dfs(edge.dest, visitedNodes);
		}
	}

}
