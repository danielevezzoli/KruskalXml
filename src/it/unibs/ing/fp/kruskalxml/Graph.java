package it.unibs.ing.fp.kruskalxml;

import java.util.PriorityQueue;
import java.util.Vector;

public class Graph {

	private Vector<Node> nodes = new Vector<>();
	private PriorityQueue<Edge> edges = new PriorityQueue<>();
	private Vector<Group_String> gruppi = new Vector<>();
	private Vector<Edge> path = new Vector<>();
	private Vector<String> pathD = new Vector<>();

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
	
	public Node getNodeById(String id) {
		Node n = null;
		for (Node node : nodes) {
			if(node.getId().equals(id))
				n = node;
		}
		return n;
	}
	
	public void printGraph(){
		for(Node n: nodes){
			System.out.print(n);
		}
		System.out.print("\n\n\n\n");
		for(Edge e: edges){
			System.out.println(e + " ");
		}
	}
	
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
	
	public void kruskal_String()
	{
		
		aggiornaGruppi();
		PriorityQueue<Edge> tmp = edges;

		while (edges.size()>0)
		{
			int indice1=-1; int indice2=-1;
			boolean presente=false;
			
			Edge currentEdge = tmp.poll();
			
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
				System.out.println("Gia presente quindi non inserisco");
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
			}
	
		}		
		System.out.println("path ottimale:");
		for (int i=0; i<path.size(); i++)
		{
			System.out.println(path.get(i));
			
		}

		
	}
	
	private Vector<Node> settledNodes = new Vector<>();
	private Vector<Node> unSettledNodes = new Vector<>();
	private Node endNodo = new Node();
	private void updateArray()
	{
		for (Node n: nodes)
		{
			if (n.getStart())
			{
				n.setDistance(0);
				settledNodes.add(n);
				
			}
			
			else
			{
				if(n.getEnd()) endNodo = n;
				n.setDistance(99);
				unSettledNodes.add(n);
			}
		}
		
		for (Node n: settledNodes)
			System.out.println("settleNodes:"+n);
		
		for (Node n: unSettledNodes)
			System.out.println("unsettleNodes:"+n);
	}
	private void updateNearNodes()
	{
		for (Edge e: edges)
		{
			if(e.getStartNode().equals(settledNodes.get(settledNodes.size()-1)))
				updateNode(e.getEndNode(),settledNodes.get(settledNodes.size()-1),e.getWeight());
			if(e.getEndNode().equals(settledNodes.get(settledNodes.size()-1)))
				updateNode(e.getStartNode(),settledNodes.get(settledNodes.size()-1),e.getWeight());
		}
	}
	
	public Vector<Node> getNodes() {
		return nodes;
	}


	private void updateNode(Node e, Node a, int weight)
	{
		if(unSettledNodes.contains(e))
			System.out.println("nodo di partenza: "+a.getLabel()+ " nodo arrivo: "+e.getLabel()+" peso: "+weight + " distanza " + e.getDistance());
			if(e.getDistance() > (a.getDistance() + weight)){
				e.setDistance((a.getDistance() + weight));
				e.setPreviousNode(a);
				System.out.println("previous node: "+ e.getPreviousNode());
				System.out.println("nuova distanza: "+ e.getDistance());
				
			}
	}
	private Node takeNextNode()
	{
		Node nextNode= null;
		for (Node n: unSettledNodes)
		{
			
			if (nextNode == null || nextNode.getDistance()>n.getDistance())
				nextNode=n;
		}
		System.out.println("nodo con distanza minima: " + nextNode.getLabel());
		return nextNode;
	}
	private void createPath(){
		Node endnodo = endNodo;
		while(!endnodo.getStart()){
			pathD.add(endnodo.getLabel());
			endnodo = endnodo.getPreviousNode();
		}
		pathD.add(endnodo.getLabel());
	}
	private void printPath(){
		for(int i=(pathD.size()-1); i>=0 ; i--){
			System.out.println(pathD.get(i));
		}
	}
	public void dijkstra()
	{
		updateArray();
		while (unSettledNodes.size()>0)
		{
			updateNearNodes();
			settledNodes.add(takeNextNode());
			for(Node n: settledNodes) System.out.println("SettledNodes " + n.getLabel() + "\n");
			unSettledNodes.remove(takeNextNode());
			for(Node n: unSettledNodes) System.out.println("unSettledNodes " + n.getLabel() + "\n");
			
		}
		createPath();
		printPath();
		
	}
	
	public String toString(){
		return "";
	}
}

