import java.util.LinkedList;
import java.util.Random;
import java.util.*;

public class Graph {

	private int[][] edges; // adjacency matrix
	private LinkedList<Integer>[] adjlist; // adjacency list
	private Object[] labels; // vertex label, i.e, vertex 0, its label = "A", vertex 1, its label = "B"

	public Graph(int n) {
		// n: size of nodes
		// weighted graph
		edges = new int[n][n];
		// edges[i][j] saves the weight of edge i->j, assume weight > 0
		// for unweighted graph
		// set edges[i][j] to 1 if there exists an edge i->j
		// set edges[i][j] to 0 otherwise
		adjlist = new LinkedList[n];
		// adjlist saves the adjacency list of the graph
		// adjlist[i] saves a list of neighboring vertices of vertex i

		for (int i = 0; i < n; i++) {
			adjlist[i] = new LinkedList<>();
		}//for

		labels = new Object[n];
	}//Graph

	public void setLabel(int vertex, Object label) {
		// vertex: vertex index, label: vertex name
		labels[vertex] = label;

	}//setLabel

	public Object getLabel(int vertex) {
		return labels[vertex];
	}//getLabel

	public int size() {
		return edges.length;
	}//size

	public void addEdge(int source, int target, int w) {
		// add an edge from vertex source to vertex target with w as weight
		edges[source][target] = w;

		// edges[target][source] = w;
		// In an undirected graph, set the symmetry element in the matrix with the same
		// weight

	}//addEdge

	public boolean isEdge(int source, int target) {
		// if edges[i][j] > 0, there exists an edge from vertex i to vertex j
		return edges[source][target] > 0;

	}//isEdge

	public void removeEdge(int source, int target) {
		edges[source][target] = 0;
		// edges[target][source] = 0;
		// In an undirected graph, set the symmetry element in the matrix to 0

	}//removeEdge

	public int getWeight(int source, int target) {
		return edges[source][target];

	}//getWeight

	public int[] neighbors(int vertex) {
		// find neighbors of a given vertex

		int count = 0;

		for (int i = 0; i < edges[vertex].length; i++) {
			if (edges[vertex][i] > 0)
			{
				count++;
			}//if
		}//for

		final int[] answer = new int[count];
		count = 0;

		for (int i = 0; i < edges[vertex].length; i++) {
			if (edges[vertex][i] > 0)
				answer[count++] = i;
		}//for

		return answer;
	}//neighbors

	public int getUnvisitedNeighbor(int vertex, boolean[] visited) {
		// find an unvisited neighbor of a given vertex
		// if there exist multiple unvisited neighbors, return the first one found
		// if all neighbors are visited, return -1

		for (int i = 0; i < edges[vertex].length; i++) {
			if (edges[vertex][i] > 0 && visited[i] == false)
				return i;
		}//for

		return -1;
	}//getUnvisitedNeighbor

	public void getAdjList() {
		// Complete the method to create an adjacency list for the graph
		// Feel free to change the return type

		int len = labels.length;
		for (int i = 0; i < len; i++) {
			int[] neighbors = neighbors(i);

			int count = 0;
			while (neighbors.length > count) {
				adjlist[i].add(neighbors[count]);
				count++;
			} // while
		} // for

	}// getAdjList

	public void print() {
		// Print adjacency list
		int n = edges.length;

		for (int i = 0; i < n; i++) {
			System.out.println("Vertex " + i + ":" + adjlist[i].toString());
		}//for

	}//print

	public void bfs() {// BFS
		// Complete this method to traverse a graph using BFS
		// Start BFS from a randomly selected node in the graph
		// Print nodes in order as visited by BFS
		// Note: Follow the pseudocode of BFS() in slides.

		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] vis = new boolean[labels.length];

		for (int i = 0; i < labels.length; i++) {
			vis[i] = false;
		} // for

		Random r = new Random();
		int p = r.nextInt(labels.length);
		vis[p] = true;

		q.add(p);

		while (q.size() > 0) {
			p = q.remove();
			System.out.print(labels[p] + " ");
			Iterator<Integer> i = adjlist[p].listIterator();
			while (i.hasNext()) {
				int head = i.next();
				if (!vis[head]) {
					vis[head] = true;
					q.add(head);
				} // if
			} // while
		} // while

	}// bfs

	public static void main(String args[]) {

		// An example to create a graph using the Graph class

		final Graph t = new Graph(6);
		t.setLabel(0, "A");
		t.setLabel(1, "B");
		t.setLabel(2, "C");
		t.setLabel(3, "D");
		t.setLabel(4, "E");
		t.setLabel(5, "F");
		t.addEdge(0, 1, 1);
		t.addEdge(0, 5, 1);
		t.addEdge(1, 2, 1);
		t.addEdge(1, 3, 1);
		t.addEdge(1, 5, 1);
		t.addEdge(2, 3, 1);
		t.addEdge(4, 3, 1);
		t.addEdge(4, 2, 1);
		t.addEdge(5, 4, 1);
		// Test adjacency list
		t.getAdjList();
		System.out.println("Adjacency list: ");
		t.print();
		// Test BFS
		System.out.println("\nBreadth First Search: ");
		t.bfs();

	}//main

}//Graph
