public class WeightedGraph {

	private int[][] edges; // adjacency matrix
	private Object[] labels;

	public WeightedGraph(int n) {
		// n: size of nodes
		edges = new int[n][n];
		labels = new Object[n];
	}//WeightedGraph

	public int size() {
		return labels.length;
	}//size

	public void setLabel(int vertex, Object label) {
		// vertex: vertex index, label: vertex name
		labels[vertex] = label;
	}//setLabel

	public Object getLabel(int vertex) {
		return labels[vertex];
	}//getLabel

	public void addEdge(int source, int target, int w) {
		// w for weight
		edges[source][target] = w;
		// edges[target][source] = w; // undirected graph
	}//addEdge

	public boolean isEdge(int source, int target) {
		// if edges[i][j] > 0, there exists an edge from vertex i to vertex j
		return edges[source][target] > 0;
	}//isEdge

	public void removeEdge(int source, int target) {
		edges[source][target] = 0;
		// edges[target][source] = 0; // undirected graph
	}//removeEdge

	public int getWeight(int source, int target) {
		return edges[source][target];
	}//getWeight

	public int[] neighbors(int vertex) {
		int count = 0;
		for (int i = 0; i < edges[vertex].length; i++) {
			if (edges[vertex][i] > 0)
				count++;
		}//for
		final int[] answer = new int[count];
		count = 0;
		for (int i = 0; i < edges[vertex].length; i++) {
			if (edges[vertex][i] > 0)
				answer[count++] = i;
		}//for
		return answer;
	}//neighbors

	public void print() {
		for (int j = 0; j < edges.length; j++) {
			System.out.print(labels[j] + ": ");
			for (int i = 0; i < edges[j].length; i++) {
				if (edges[j][i] > 0)
					System.out.print(labels[i] + ":" + edges[j][i] + " ");
			}//for
			System.out.println();
		}//for
	}//print

	public static void main(String args[]) {

		final WeightedGraph t = new WeightedGraph(6);
		// Create a graph t
		t.setLabel(0, "v0");
		t.setLabel(1, "v1");
		t.setLabel(2, "v2");
		t.setLabel(3, "v3");
		t.setLabel(4, "v4");
		t.setLabel(5, "v5");
		t.addEdge(0, 1, 2);
		t.addEdge(0, 5, 9);
		t.addEdge(1, 2, 8);
		t.addEdge(1, 3, 15);
		t.addEdge(1, 5, 6);
		t.addEdge(2, 3, 1);
		t.addEdge(4, 3, 3);
		t.addEdge(4, 2, 7);
		t.addEdge(5, 4, 3);

		// Test the Dijkstra's algorithm to find the shortest path from v0 to every
		// other node in graph t
		int[] pred = Dijkstra.dijkstra(t, 0);
		for (int n = 0; n < 6; n++) {
			Dijkstra.printPath(t, pred, 0, n);
		}//for
		// Expected output
		/*
		 * [v0]
		 * [v0, v1]
		 * [v0, v1, v2]
		 * [v0, v1, v2, v3]
		 * [v0, v1, v5, v4]
		 * [v0, v1, v5]
		 */

	}//main

}//WeightedGraph