package it.unibs.ing.fp.kruskalxml;

import java.util.Vector;

public class Node {
	private int distance;
	private Node previousNode;
	private String id;
	private String label;
	private boolean start = false, end = false;
	private Vector<Edge> links = new Vector<>();

	public boolean getStart() {
		return start;
	}

	public boolean getEnd() {
		return end;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Node getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(Node previousNode) {
		this.previousNode = previousNode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public void addEdge(Edge newEdge) {
		links.add(newEdge);
	}

	public Vector<Edge> getEdges() {
		return links;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("\nNodo: " + " " + label + " " + distance);
		for (Edge e : links) {
			str.append("\n" + e);
		}
		return str.toString();
	}

	public boolean equals(Node node) {
		boolean flag = false;
		if (this.label.equalsIgnoreCase(node.getLabel())) {
			flag = true;
		}
		return flag;
	}
}
