package it.unibs.ing.fp.kruskalxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XmlParser {

	File filename;
	Vector<Node> nodes = new Vector<>();

	/**
	 * Metodo per l'acquisizione di un grafo da XML
	 * 
	 * @param filename
	 * @return il grafo
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public Graph parseXml(String filename) throws FileNotFoundException, XMLStreamException {
		
		nodes = parseXmlNodes(filename);

		Graph graph = new Graph();
		graph.setNodes(nodes);

		// Apre il file e controlla se esiste nella directory
		try {
			this.filename = new File(filename);
		} catch (Exception e) {
			System.out.println("Il file " + filename + " non è disponibile o non è presente nella directory");
			return null;
		}

		// Inizializzo le variabili
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(this.filename));
		Node tmp = null;
		String label = "", weight = "";

		// Ciclo di lettura file (finchè c'è da leggere)
		while (reader.hasNext()) {
			switch (reader.next()) {
			case XMLStreamConstants.START_DOCUMENT:
				System.out.println("Inizio a leggere il documento");
				break;

			case XMLStreamConstants.START_ELEMENT:
				switch (reader.getLocalName()) {
				case "tree":
					// Se trovo il tag <tree> creo il grafo
					System.out.println("Inizio a leggere l'albero");
					break;
				case "node":
					// Se trovo il tag <node>, creo il nodo e imposto i
					// parametri start e end
					tmp = new Node();
					// Se nell'attributo start/end trovo il valore "true" allora
					// i parametri diventano true altrimenti false.
					boolean start = Boolean.parseBoolean(reader.getAttributeValue(null, "start"));
					boolean end = Boolean.parseBoolean(reader.getAttributeValue(null, "end"));
					tmp.setStart(start);
					tmp.setEnd(end);

					break;
				case "edges":
					break;
				case "edge":
					// leggo il peso
					if ("weight".equals(reader.getAttributeName(0).toString())) {
						weight = reader.getAttributeValue(0).toString().trim();
					}
					break;
				}
				break;

			// Leggo i valori tra i tag di apertura e chiusura
			case XMLStreamConstants.CHARACTERS:
				if (reader.getTextLength() > 0) {
					label = reader.getText().trim();
				}
				break;

			// I tag di chiusura
			case XMLStreamConstants.END_ELEMENT:
				switch (reader.getLocalName()) {
				case "tree":
					System.out.println("Ho finito di leggere il documento");
					break;
				case "node":
					// Aggiungo il nodo al grafo
					graph.addNode(tmp);
					tmp = null;
					break;
				case "label":
					tmp.setId(label);
					tmp.setLabel(label);
					break;
				case "edge":
					// Aggiungo un edge al grafo e al nodo collegato
					Edge e = new Edge(tmp, graph.getNodeById(label), Integer.parseInt(weight));
					tmp.addEdge(e);
					graph.addEdge(e);
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

	public Vector<Node> parseXmlNodes(String filename) throws FileNotFoundException, XMLStreamException {

		// Apre il file e controlla se esiste nella directory
		try {
			this.filename = new File(filename);
		} catch (Exception e) {
			System.out.println("Il file " + filename + " non è disponibile o non è presente nella directory");
			return null;
		}

		// Inizializzo le variabili
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(this.filename));
		Node tmp = null;
		String label = "", weight = "";
		

		// Ciclo di lettura file (finchè c'è da leggere)
		while (reader.hasNext()) {
			switch (reader.next()) {
			case XMLStreamConstants.START_DOCUMENT:
				System.out.println("Inizio a leggere il documento");
				break;

			case XMLStreamConstants.START_ELEMENT:
				switch (reader.getLocalName()) {
				case "node":
					// Se trovo il tag <node>, creo il nodo e imposto i
					// parametri start e end
					tmp = new Node();
					// Se nell'attributo start/end trovo il valore "true" allora
					// i parametri diventano true altrimenti false.
					boolean start = Boolean.parseBoolean(reader.getAttributeValue(null, "start"));
					boolean end = Boolean.parseBoolean(reader.getAttributeValue(null, "end"));
					tmp.setStart(start);
					tmp.setEnd(end);

				}
				break;

			// Leggo i valori tra i tag di apertura e chiusura
			case XMLStreamConstants.CHARACTERS:
				if (reader.getTextLength() > 0) {
					label = reader.getText().trim();
				}
				break;

			// I tag di chiusura
			case XMLStreamConstants.END_ELEMENT:
				switch (reader.getLocalName()) {
				case "node":
					// Aggiungo il nodo al grafo
					nodes.add(tmp);
					tmp = null;
					break;
				case "label":
					tmp.setId(label);
					tmp.setLabel(label);
					break;
				}
			case XMLStreamConstants.END_DOCUMENT:
				System.out.println("Ho finito di leggere il documento");
				break;

			}
		}
		
		return nodes;
	}
}