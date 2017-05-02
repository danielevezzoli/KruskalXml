package it.unibs.ing.fp.kruskalxml;

import java.util.Collections;

public class MainKruskal {

	public static void main(String[] args) {
		
		Graph g = new Graph();
		
		Node a = new Node();
		Node b = new Node();
		Node c = new Node();
		Node d = new Node();
		Node e = new Node();
		
		a.setLabel("a");
		b.setLabel("b");
		b.setStart(true);
		c.setLabel("c");
		d.setLabel("d");
		e.setLabel("e");
		e.setEnd(true);
		e.addEdge(new Edge(e,a,1));
		d.addEdge(new Edge(d,c,2));
		b.addEdge(new Edge(b,a,3));
		e.addEdge(new Edge(e,b,4));
		c.addEdge(new Edge(c,b,5));
		e.addEdge(new Edge(e,c,6));
		e.addEdge(new Edge(e,d,7));

		g.addNode(a);
		g.addNode(b);
		g.addNode(c);
		g.addNode(d);
		g.addNode(e);


		g.addEdge(new Edge(e,a,1));
		g.addEdge(new Edge(d,c,2));
		g.addEdge(new Edge(b,a,3));
		g.addEdge(new Edge(e,b,4));
		g.addEdge(new Edge(c,b,5));
		g.addEdge(new Edge(e,c,6));
		g.addEdge(new Edge(e,d,7));

		g.printGraph();
			
//			StringBuffer set= new StringBuffer("afebdgdffe");
//			String ritorno=set.toString();
//			String contien="e";
//			if (ritorno.contains(contien))
//				System.out.println("contine");
//				
		
	g.Kruskal_String();

	}

}
