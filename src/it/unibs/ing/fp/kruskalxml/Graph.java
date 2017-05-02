package it.unibs.ing.fp.kruskalxml;

import java.util.PriorityQueue;
import java.util.Vector;

public class Graph {

	private Vector<Node> nodes = new Vector<>();
private Vector<Edge> edges = new Vector<>(); //Priorityueue

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
	
	public String toString(){
		return "";
	}
}

