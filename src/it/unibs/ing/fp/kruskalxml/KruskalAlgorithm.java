package it.unibs.ing.fp.kruskalxml;

import java.util.PriorityQueue;
import java.util.Vector;

public class KruskalAlgorithm {

	private static void aggiornaGruppi(Vector<Node> nodes, Vector<GroupString> gruppi) {
		for (int i = 0; i < nodes.size(); i++) {
			GroupString a = new GroupString();
			gruppi.add(a);
			gruppi.get(i).addSet(nodes.get(i).getLabel());

			System.out.println("indice : " + i);
			System.out.println(gruppi.get(i).getSet());

		}
	}

	public static Vector<Edge> startAlgorithm(Graph graph) {

		Vector<Node> nodes = new Vector<>(graph.getNodes());
		PriorityQueue<Edge> edges = new PriorityQueue<>(graph.getEdges());
		Vector<GroupString> gruppi = new Vector<>();

		Vector<Edge> path = new Vector<>();

		aggiornaGruppi(nodes, gruppi);
		PriorityQueue<Edge> tmp = edges;

		while (edges.size() > 0) {
			int indice1 = -1;
			int indice2 = -1;
			boolean presente = false;

			Edge currentEdge = tmp.poll();

			for (int i = 0; i < gruppi.size(); i++) {

				if (gruppi.get(i).contain2Set(currentEdge.getStartNode().getLabel(),
						currentEdge.getEndNode().getLabel()))
					presente = true;
				if (gruppi.get(i).contain1Set(currentEdge.getStartNode().getLabel()))
					indice1 = i;
				if (gruppi.get(i).contain1Set(currentEdge.getEndNode().getLabel()))
					indice2 = i;
			}
			System.out.println("sto lavorando sul edge: " + currentEdge);
			if (presente) {
				System.out.println("Gia presente quindi non inserisco");
			}

			else {
				System.out.println("non presente quindi lo inserisco");
				path.add(currentEdge);
				gruppi.get(indice2).addSet(gruppi.get(indice1).getSet());
				gruppi.remove(indice1);
				for (int i = 0; i < gruppi.size(); i++) {
					System.out.println("indice : " + i);
					System.out.println(gruppi.get(i).getSet());

				}
			}

		}
		System.out.println("path ottimale:");
		for (int i = 0; i < path.size(); i++) {
			System.out.println(path.get(i));

		}

		return path;

	}
}
