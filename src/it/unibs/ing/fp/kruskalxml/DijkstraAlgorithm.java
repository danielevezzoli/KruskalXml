package it.unibs.ing.fp.kruskalxml;

import java.util.PriorityQueue;
import java.util.Vector;

/**
 * Classe statica per l'esecuzione dell'algoritmo di Dijsktra
 * 
 * @author Stefano Poma
 * @author Matteo Zanolla
 * @author Daniele Vezzoli
 *
 */
public class DijkstraAlgorithm {

	private static Vector<Node> settledNodes = new Vector<>();
	private static Vector<Node> unSettledNodes = new Vector<>();
	private static Node endNode = new Node();
	private static Vector<String> path = new Vector<>();

	/**
	 * Metodo per inizializzare i nodi. All'inizio pongo il nodo iniziale a
	 * distanza = 0 Tutti gli altri nodi li pongo a distanza "infinito"
	 *
	 * @author Stefano Poma
	 * @author Matteo Zanolla
	 * 
	 * @param nodes
	 *            Il Vector di Node da inizializzare
	 */
	private static void initArray(Vector<Node> nodes) {
		for (Node n : nodes) {
			if (n.getStart()) {
				n.setDistance(0);
				settledNodes.add(n);
			}

			else {
				if (n.getEnd())
					endNode = n;
				n.setDistance(Integer.MAX_VALUE);
				unSettledNodes.add(n);
			}
		}
	}

	/**
	 * Metodo per aggiornare i nodi
	 * 
	 * @author Stefano Poma
	 * @author Matteo Zanolla
	 * @param edges
	 *            Il vector di edges da scorrere
	 */
	private static void updateNearNodes(PriorityQueue<Edge> edges) {
		for (Edge e : edges) {
			if (e.getStartNode().equals(settledNodes.lastElement()))
				updateNode(e.getEndNode(), settledNodes.lastElement(), e.getWeight());
			if (e.getEndNode().equals(settledNodes.lastElement()))
				updateNode(e.getStartNode(), settledNodes.lastElement(), e.getWeight());
		}
	}

	/**
	 * Metodo per aggiornare la distanza di un nodo
	 * 
	 * @author Stefano Poma
	 * @author Matteo Zanolla
	 * @param arriveNode
	 *            Il nodo di arrivo
	 * @param actualNode
	 *            il nodo di partenza
	 * @param weight
	 *            Il peso dell'edge
	 */
	private static void updateNode(Node arriveNode, Node actualNode, int weight) {
		if (unSettledNodes.contains(arriveNode)) {
			if (arriveNode.getDistance() > (actualNode.getDistance() + weight)) {
				arriveNode.setDistance((actualNode.getDistance() + weight));
				arriveNode.setPreviousNode(actualNode);
			}
		}
	}

	/**
	 * Metodo per decidere il nodo da considerare successivamente
	 * 
	 * @author Stefano Poma
	 * @author Matteo Zanolla
	 * @return Il nodo scelto
	 */
	private static Node takeNextNode() {
		Node nextNode = null;
		for (Node n : unSettledNodes) {

			if (nextNode == null || nextNode.getDistance() > n.getDistance())
				nextNode = n;
		}
		return nextNode;
	}

	/**
	 * Metodo che crea il path finale
	 * 
	 * @author Stefano Poma
	 * @author Matteo Zanolla
	 */
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

	/**
	 * Metodo che stampa il path finale
	 * 
	 * @author Stefano Poma
	 * @author Matteo Zanolla
	 */
	private static void printPath() {
		System.out.println("Il path ottimale è:\n");
		for (int i = (path.size() - 1); i >= 0; i--) {
			System.out.println(path.get(i));
		}

		System.out.println("\n");
	}

	/**
	 * Metodo che esegue l'algoritmo di Dijkstra
	 * 
	 * @param graph
	 *            Il grafo su cui eseguire l'algoritmo
	 * @return Un vector contenente le label del path finale
	 */

	public static Vector<String> startAlgorithm(Graph graph) {
		initArray(graph.getNodes());
		boolean fine = false;
		while ((unSettledNodes.size() > 0) && (!fine)) {
			updateNearNodes(graph.getEdges());
			settledNodes.add(takeNextNode());
			unSettledNodes.remove(takeNextNode());
			if (endNode.equals(settledNodes.lastElement())) {
				fine = true;
			}
		}
		createPath();
		printPath();
		return path;

	}
}
