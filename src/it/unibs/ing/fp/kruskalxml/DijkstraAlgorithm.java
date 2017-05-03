package it.unibs.ing.fp.kruskalxml;

import java.util.PriorityQueue;
import java.util.Vector;

public class DijkstraAlgorithm {

	private static Vector<Node> settledNodes = new Vector<>();
	private static Vector<Node> unSettledNodes = new Vector<>();
	private static Node endNode = new Node();

	private static Vector<String> path = new Vector<>();

	private static void updateArray(Vector<Node> nodes) {
		for (Node n : nodes) {
			if (n.getStart()) {
				n.setDistance(0);
				settledNodes.add(n);

			}

			else {
				if (n.getEnd())
					endNode = n;
				// n.setDistance(99);
				n.setDistance(Integer.MAX_VALUE); // 2^32 > 99 lol
				unSettledNodes.add(n);
			}
		}

		for (Node n : settledNodes)
			System.out.println("settleNodes:" + n);

		for (Node n : unSettledNodes)
			System.out.println("unsettleNodes:" + n);
	}

	private static void updateNearNodes(PriorityQueue<Edge> edges) {
		for (Edge e : edges) {
			if (e.getStartNode().equals(settledNodes.get(settledNodes.size() - 1)))
				updateNode(e.getEndNode(), settledNodes.get(settledNodes.size() - 1), e.getWeight());
			if (e.getEndNode().equals(settledNodes.get(settledNodes.size() - 1)))
				updateNode(e.getStartNode(), settledNodes.get(settledNodes.size() - 1), e.getWeight());
		}
	}

	private static void updateNode(Node e, Node a, int weight) {
		if (unSettledNodes.contains(e))
			System.out.println("nodo di partenza: " + a.getLabel() + " nodo arrivo: " + e.getLabel() + " peso: "
					+ weight + " distanza " + e.getDistance());
		if (e.getDistance() > (a.getDistance() + weight)) {
			e.setDistance((a.getDistance() + weight));
			e.setPreviousNode(a);
			System.out.println("previous node: " + e.getPreviousNode());
			System.out.println("nuova distanza: " + e.getDistance());

		}
	}

	private static Node takeNextNode() {
		Node nextNode = null;
		for (Node n : unSettledNodes) {

			if (nextNode == null || nextNode.getDistance() > n.getDistance())
				nextNode = n;
		}
		System.out.println("nodo con distanza minima: " + nextNode.getLabel());
		return nextNode;
	}

	private static void createPath() {
		Node endnodo = endNode;
		while (!endnodo.getStart()) {
			path.add(endnodo.getLabel());
			endnodo = endnodo.getPreviousNode();
		}
		path.add(endnodo.getLabel());
	}

	private static void printPath() {
		for (int i = (path.size() - 1); i >= 0; i--) {
			System.out.println(path.get(i));
		}
	}

	public static void startAlgorithm(Graph graph) {
		updateArray(graph.getNodes());
		while (unSettledNodes.size() > 0) {
			updateNearNodes(graph.getEdges());
			settledNodes.add(takeNextNode());
			for (Node n : settledNodes)
				System.out.println("SettledNodes " + n.getLabel() + "\n");
			unSettledNodes.remove(takeNextNode());
			for (Node n : unSettledNodes)
				System.out.println("unSettledNodes " + n.getLabel() + "\n");

		}
		createPath();
		printPath();

	}
}
