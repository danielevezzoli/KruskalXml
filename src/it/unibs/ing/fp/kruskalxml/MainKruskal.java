package it.unibs.ing.fp.kruskalxml;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

public class MainKruskal {

	public static void main(String[] args) {

		XmlParser xp = new XmlParser();
		Graph graph = new Graph();

		try {
			graph = xp.parseXml("input.xml");
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}

		graph.printGraph();
		KruskalAlgorithm.startAlgorithm(graph);
		DijkstraAlgorithm.startAlgorithm(graph);
		graph.printGraph();

	}

}
