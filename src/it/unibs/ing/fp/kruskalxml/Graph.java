package it.unibs.ing.fp.kruskalxml;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Vector;

public class Graph {

	private Vector<Node> nodes = new Vector<>();
	private PriorityQueue<Edge> edges = new PriorityQueue<>();

	public boolean addNode(Node node){
		boolean flag = true;
		//if(edges.contains(edge));
		for(Node n: nodes){
			if(n.equals(node)){
				flag = false;
			}
		}
		if(flag){
			nodes.add(node);
		}
		return flag;
	}
	
	//prende gli edges dal nodo (TUTTI)
	public boolean addEdge(Edge edge){
		boolean flag = true;
		//if(edges.contains(edge));
		for(Edge e: edges){
			if(e.equals(edge)){
				flag = false;
			}
		}
		if(flag){
			edges.add(edge);
		}
		return flag;
	}
	
	public void printGraph(){
		for(Node n: nodes){
			System.out.print(n + " ");
		}
		System.out.print("\n\n\n\n");
		for(Edge e: edges){
			System.out.println(e + " ");
		}
	}
	
	private Vector<Group_String> gruppi=new Vector<>();
	private Vector<Edge> path=new Vector<>();
	
	public void aggiornaGruppi()
	{
		for (int i=0; i<nodes.size(); i++)
		{
			Group_String a = new Group_String();
			gruppi.add(a);
			gruppi.get(i).addSet(nodes.get(i).getLabel());
			
			System.out.println("indice : "+i);
			System.out.println(gruppi.get(i).getSet());
			
		}
	}
	
	public void Kruskal_String()
	{
		
		aggiornaGruppi();

		while (edges.size()>0)
		{
			int indice1=-1; int indice2=-1;
			boolean presente=false;
			
			Edge currentEdge = edges.poll();
			
			if(currentEdge.compareTo(currentEdge) == 0)
				System.out.println("OK");
			
			for (int i=0; i<gruppi.size();i++)
			{
				
				if (gruppi.get(i).contain2Set(currentEdge.getStartNode().getLabel(), currentEdge.getEndNode().getLabel() ))
					presente=true;
				if (gruppi.get(i).contain1Set(currentEdge.getStartNode().getLabel()))
					indice1=i;
				if (gruppi.get(i).contain1Set(currentEdge.getEndNode().getLabel()))
					indice2=i;
			}
			System.out.println("sto lavorando sul edge: " + currentEdge);
			if (presente)
			{
				System.out.println("� gi� presente quindi non lo inserisco");
//				edges.remove(0);
			}
			
			else
			{
				System.out.println("non presente quindi lo inserisco");
				path.add(currentEdge);
				gruppi.get(indice2).addSet(gruppi.get(indice1).getSet());
				gruppi.remove(indice1);
				for (int i=0; i<gruppi.size(); i++)
				{
					System.out.println("indice : "+i);
					System.out.println(gruppi.get(i).getSet());
					
				}
//				edges.remove(0);
			}
	
		}		
		System.out.println("path ottimale:");
		for (int i=0; i<path.size(); i++)
		{
			System.out.println(path.get(i));
			
		}

		
	}
	
	
	public String toString(){
		return "";
	}
}

