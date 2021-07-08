//package assignment2;    //interfered with eustis running.

import java.io.*;
import java.util.*;

public class Dijkstra {

	public static LinkedList<Node> shortestDistance(LinkedList<Node> list, Node root) {
		Set<Node> visited = new HashSet<>();
		Set<Node> unVisited = new HashSet<>();
		Node currentNode = null;
		
		root.setDistance(0);
		unVisited.add(root);

		while(unVisited.size() != 0) {
			currentNode = adjMinDistanceNode(unVisited);
			unVisited.remove(currentNode);
			
			for(Map.Entry<Node, Integer> adjList : currentNode.getAdjacentNodes().entrySet()) {
				Node adjNode = adjList.getKey();
				Integer weight = adjList.getValue();
				
				if(!visited.contains(adjNode)) {
					findMinDistance(adjNode, weight, currentNode);
					unVisited.add(adjNode);
				}
			}
			visited.add(currentNode);
		}
		return list;		
	}
	
	public static Node adjMinDistanceNode(Set<Node> nodeSet) {
		Node lowestDisNode = null;
		int lowestDistance = Integer.MAX_VALUE;
		int currentNodeDistance = 0;
		
		for(Node node : nodeSet) {
			currentNodeDistance = node.getDistance();
			
			if(currentNodeDistance < lowestDistance) {
				lowestDistance = currentNodeDistance;
				lowestDisNode  = node;
			}
		}
		return lowestDisNode;
	}
	
	public static void findMinDistance(Node tempNode, Integer weight, Node parentNode){
		Integer parentNodeDistance = parentNode.getDistance();
		LinkedList<Node> shortestPath = new LinkedList<>();
		
		if(parentNodeDistance + weight < tempNode.getDistance()) {
			tempNode.setDistance(parentNodeDistance + weight);
			shortestPath = new LinkedList<>(parentNode.getShortestPath());
			shortestPath.add(parentNode);
			tempNode.setShortestPath(shortestPath);
		}
	}	
	
	static class Node{
		private int data;
		private int distance = Integer.MAX_VALUE;
		private List<Node> shortestPath = new LinkedList<>();
		private Map<Node, Integer> adjacentNodes = new HashMap<>();
		
		Node(int data){
			this.data = data;
		}
		
		public void addAdjacentNodes(Node node, int dist) {
			adjacentNodes.put(node, dist);
		}
		
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public int getDistance() {
			return distance;
		}
		public void setDistance(int distance) {
			this.distance = distance;
		}
		public List<Node> getShortestPath() {
			return shortestPath;
		}
		public void setShortestPath(List<Node> shortestPath) {
			this.shortestPath = shortestPath;
		}
		public Map<Node, Integer> getAdjacentNodes() {
			return adjacentNodes;
		}
		public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
			this.adjacentNodes = adjacentNodes;
		}
	}

	public static void main(String[] args) {
		Scanner scanner;
		PrintWriter pw;
		int vertices = 0, edges = 0, distance = 0, rootNodeData = 0;
		LinkedList<Node> nodeArray = new LinkedList<>();
		Node rootNode = null;
		int first = 0, second = 0, rootDistance = -1;
		
		try {
			scanner = new Scanner(new File("cop3503-asn2-input.txt"));
			pw = new PrintWriter("cop3503-asn2-output-Wiegman-Ryan.txt");
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		int counter = 0;
		while(scanner.hasNext()) {
			if(scanner.hasNextInt()) {
				if(counter == 0) {
					vertices = scanner.nextInt();
					
					for(int index = 1; index <= vertices; index++)
						nodeArray.add(new Node(index));
				}
				
				if(counter == 1) {
					rootNodeData = scanner.nextInt();
				}
				
				if(counter == 2)
					edges = scanner.nextInt();
				
				if(counter > 2) {
					first = scanner.nextInt();
					second = scanner.nextInt();
					distance = scanner.nextInt();
					nodeArray.get(first - 1).addAdjacentNodes(nodeArray.get(second - 1), distance);
					nodeArray.get(second - 1).addAdjacentNodes(nodeArray.get(first - 1), distance); 
					rootNode = nodeArray.get(rootNodeData - 1);					
				}
				counter++;
			}
			else
				scanner.next();	
		}	
		
		nodeArray = Dijkstra.shortestDistance(nodeArray, rootNode);
		pw.println(vertices);
		System.out.println(vertices);
		int size = 0;
		for(Node x : nodeArray) {
			size = x.getShortestPath().size();
			
			if(size == 0) {
				System.out.println(x.getData() + " " + rootDistance + " " + rootDistance);	
				pw.println(x.getData() + " " + rootDistance + " " + rootDistance);
			}
							
			else {
				System.out.println(x.getData() + " " + x.getDistance() + " " + x.getShortestPath().get(size - 1).getData());
				pw.println(x.getData() + " " + x.getDistance() + " " + x.getShortestPath().get(size - 1).getData());
			}
				
		}
		
		scanner.close();
		pw.close();

	}

}




