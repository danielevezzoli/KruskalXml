package it.unibs.ing.fp.kruskalxml;

import java.util.PriorityQueue;
import java.util.Vector;

public class Graph {

	private Vector<Node> nodes = new Vector<>();
	private PriorityQueue<Edge> edges = new PriorityQueue<>();

	/**
	 * Aggiunge un nodo al Vector nodes se il nodo non è già presente
	 * 
	 * @param node
	 * @return
	 */
	public boolean addNode(Node node) {
		boolean flag = true;
		// if(edges.contains(edge));
		for (Node n : nodes) {
			if (n.equals(node)) {
				flag = false;
			}
		}
		if (flag) {
			nodes.add(node);
		}
		return flag;
	}

	public Vector<Node> getNodes() {
		return nodes;
	}

	public PriorityQueue<Edge> getEdges() {
		return edges;
	}

	public boolean addEdge(Edge edge) {
		boolean flag = false;
		if (!edges.contains(edge)) {
			edge.getStartNode().setLinkedNodes(edge.getEndNode());
			edge.getEndNode().setLinkedNodes(edge.getStartNode());
			edge.getStartNode().addEdge(edge);
			edge.getEndNode().addEdge(edge);
			edges.add(edge);
			flag = true;
		}
		return flag;
	}

	public void setNodes(Vector<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * Restituisce il nodo contrassegnato da id
	 * 
	 * @param id
	 * @return il nodo trovato
	 */
	public Node getNodeById(String id) {
		Node n = null;
		for (Node node : nodes) {
			if (node.getId().equals(id))
				n = node;
		}
		return n;
	}

	/**
	 * Stampa il grafo
	 */
	public void printGraph() {
		for (Node n : nodes) {
			System.out.println(n);
		}
		System.out.print("\n\n\n\n");
		for (Edge e : edges) {
			System.out.println(e + " ");
		}
	}

}
