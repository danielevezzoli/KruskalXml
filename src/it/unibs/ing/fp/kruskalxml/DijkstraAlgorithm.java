package it.unibs.ing.fp.kruskalxml;

import java.util.PriorityQueue;
import java.util.Vector;

/**
 * Classe statica per l'esecuzione dell'algoritmo di Dijsktra
 * 
 * @author Stefano Poma
 * @author Matteo Zanolla
 *
 */
public class DijkstraAlgorithm {

	private static Vector<Node> settledNodes = new Vector<>();
	private static Vector<Node> unSettledNodes = new Vector<>();
	private static Node endNode = new Node();
	private static Vector<String> path = new Vector<>();

	private static void initArray(Vector<Node> nodes) {
		for (Node n : nodes) {
			if (n.getStart()) {
				n.setDistance(0);
				settledNodes.add(n);
			}

			else {
				if (n.getEnd())
					endNode = n;
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
			if (e.getStartNode().equals(settledNodes.lastElement()))
				updateNode(e.getEndNode(), settledNodes.lastElement(), e.getWeight());
			if (e.getEndNode().equals(settledNodes.lastElement()))
				updateNode(e.getStartNode(), settledNodes.lastElement(), e.getWeight());
		}
	}

	private static void updateNode(Node arriveNode, Node actualNode, int weight) {
		if (unSettledNodes.contains(arriveNode)) {
			System.out.println("nodo di partenza: " + actualNode.getLabel() + " nodo arrivo: " + arriveNode.getLabel()
					+ " peso: " + weight + " distanza " + arriveNode.getDistance());
			if (arriveNode.getDistance() > (actualNode.getDistance() + weight)) {
				arriveNode.setDistance((actualNode.getDistance() + weight));
				arriveNode.setPreviousNode(actualNode);
				System.out.println("previous node: " + arriveNode.getPreviousNode());
				System.out.println("nuova distanza: " + arriveNode.getDistance());

			}
		}
	}

	// private static void updateNearNodes(Node actualNode){
	// for(Edge e: actualNode.getEdges()){
	// for(Node nearNode: actualNode.getLinkedNodes()){
	// System.out.println("nodo di partenza: " + actualNode.getLabel() + " nodo
	// arrivo: " + nearNode.getLabel()
	// + " peso: " + e.getWeight() + " distanza " + nearNode.getDistance());
	// if(e.getStartNode().equals(nearNode) || e.getEndNode().equals(nearNode)){
	// if(unSettledNodes.contains(nearNode)){
	// if((actualNode.getDistance() + e.getWeight()) < nearNode.getDistance()){
	// nearNode.setDistance((actualNode.getDistance() + e.getWeight()));
	// nearNode.setPreviousNode(actualNode);
	// System.out.println("previous node: " + nearNode.getPreviousNode());
	// System.out.println("nuova distanza: " + nearNode.getDistance());
	// }
	// }
	// }
	// }
	// }
	// }

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
		Node pathNode = endNode;
		while (!pathNode.getStart()) {
			path.add(pathNode.getLabel());
			pathNode = pathNode.getPreviousNode();
		}
		path.add(pathNode.getLabel());

		Vector<String> tmp = new Vector<>();

		for (int i = (path.size() - 1); i >= 0; i--) {
			tmp.add(path.get(i));
		}

		path = tmp;
	}

	private static void printPath() {
		System.out.println("Il path ottimale �:\n");
		for (int i = (path.size() - 1); i >= 0; i--) {
			System.out.println(path.get(i));
		}

		System.out.println("\n");
	}

	public static Vector<String> startAlgorithm(Graph graph) {
		initArray(graph.getNodes());
		boolean fine = false;
		while ((unSettledNodes.size() > 0) && (!fine)) {
			updateNearNodes(graph.getEdges());
			settledNodes.add(takeNextNode());
			for (Node n : settledNodes)
				System.out.println("SettledNodes " + n.getLabel() + "\n");
			unSettledNodes.remove(takeNextNode());
			for (Node n : unSettledNodes)
				System.out.println("unSettledNodes " + n.getLabel() + "\n");
			if (endNode.equals(settledNodes.lastElement())) {
				fine = true;
			}
		}
		createPath();
		printPath();
		return path;

	}
}
