package it.unibs.ing.fp.kruskalxml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XmlWriter {
	public boolean saveKruskal(String filename, Vector<Edge> mst) throws XMLStreamException {
		System.out.println("Scrittura su file");

		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try {
			writer = output.createXMLStreamWriter(new FileWriter(filename));

			writer.writeComment("data saved");
			writer.writeStartDocument("utf-8", "1.0");
			writer.writeStartElement("path");
			for (Edge i : mst) {
				writer.writeStartElement("edge");
				writer.writeCharacters(i.getStartNode().getLabel() + i.getEndNode().getLabel());
				writer.writeEndElement();
			}
			writer.writeEndElement(); // End Path
			writer.writeEndDocument(); // End Document
			writer.flush();
			writer.close();
			System.out.println("End!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Vecchio, problema!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean saveDijkstra(String filename, Vector<String> path) throws XMLStreamException {
		System.out.println("Scrittura su file");

		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try {
			writer = output.createXMLStreamWriter(new FileWriter(filename));

			writer.writeComment("data saved");
			writer.writeStartDocument("utf-8", "1.0");
			writer.writeStartElement("path");
			for (String i : path) {
				writer.writeStartElement("node");
				writer.writeCharacters(i);
				writer.writeEndElement();
			}
			writer.writeEndElement(); // End Path
			writer.writeEndDocument(); // End Document
			writer.flush();
			writer.close();
			System.out.println("End!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Vecchio, problema!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
