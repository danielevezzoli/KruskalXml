package it.unibs.ing.fp.kruskalxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XmlParser {

	File filename;

	public Graph parseXml(String filename) throws FileNotFoundException, XMLStreamException {

		Graph graph = null;

		try {
			this.filename = new File(filename);
		} catch (Exception e) {
			System.out.println("Il file " + filename + " non è disponibile o non è presente nella directory");
			return null;
		}

		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(this.filename));
		Node tmp = null;
		String data = "", weight = "";

		while (reader.hasNext()) {
			switch (reader.next()) {
			case XMLStreamConstants.START_DOCUMENT:
				System.out.println("Inizio a leggere il documento");
				break;

			case XMLStreamConstants.START_ELEMENT:
				switch (reader.getLocalName()) {
				case "tree":
					graph = new Graph();
					System.out.println("Inizio a leggere l'albero");
					break;
				case "node":
					tmp = new Node();
					boolean start = Boolean.parseBoolean(reader.getAttributeValue(null, "start"));
					boolean end = Boolean.parseBoolean(reader.getAttributeValue(null, "end"));
					tmp.setStart(start);
					tmp.setEnd(end);

					break;
				case "edges":
					break;
				case "edge":
					if ("weight".equals(reader.getAttributeName(0).toString())) {
						weight = reader.getAttributeValue(0).toString().trim();
					}
				}
				break;

			case XMLStreamConstants.CHARACTERS:
				if (reader.getTextLength() > 0) {
					data = reader.getText().trim();
				}
				break;

			case XMLStreamConstants.END_ELEMENT:
				switch (reader.getLocalName()) {
				case "tree":
					System.out.println("Ho finito di leggere il documento");
					break;
				case "node":
					graph.addNode(tmp);
					tmp = null;
					break;
				case "label":
					tmp.setId(data);
					tmp.setLabel(data);
					break;
				case "edge":
					// tmp.addLinkedNode(getNodeByID(data), weight);
					graph.addEdge(new Edge(tmp, graph.getNodeById(data), Integer.parseInt(weight)));
					break;
				}
				break;

			case XMLStreamConstants.END_DOCUMENT:
				System.out.println("Ho finito di leggere il documento");
				break;

			}

		}
		return graph;
	}

}
