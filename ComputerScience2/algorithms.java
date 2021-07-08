//package assignment3;

import java.io.*;
import java.util.*;

public class algorithms {
	
	static class bfNode{
		int source, weight, dest;
		
		bfNode(){
			this.source = 0;
			this.weight = 0;
			this.dest = 0;
		}	
	}
	
	static class edgeNode{
		Integer parentNode, distance;
		
		edgeNode(){
			parentNode = 0;
			distance = Integer.MAX_VALUE;
		}
	}
	
	static class Graph{
		int vertices, edges;
		ArrayList<bfNode> nodeList;
		
		Graph(int v, int e){
			this.vertices = v;
			this.edges = e;
			nodeList = new ArrayList<>(e);
			
			for(int index = 0; index < e; ++index) 
				nodeList.add(new bfNode());	
		}
		
		ArrayList<edgeNode> bellmanFordAlg(Graph graph, int sourceNode) {
			ArrayList<edgeNode> distances = new ArrayList<>();
			int currentNode = 0, dest = 0, weight = 0;
			
			for(int index = 0; index < vertices; index++)
				distances.add(new edgeNode());
			distances.get(sourceNode - 1).distance = 0;
					
			for(int index = 0; index < vertices; index++) {
				for(int edgeInd = 0; edgeInd < edges; edgeInd++) {
					currentNode = graph.nodeList.get(edgeInd).source;
					dest = graph.nodeList.get(edgeInd).dest;
					weight = graph.nodeList.get(edgeInd).weight;					
					
					if(distances.get(currentNode - 1).distance != Integer.MAX_VALUE && distances.get(currentNode - 1).distance + weight < distances.get(dest - 1).distance) {
						distances.get(dest - 1).distance = distances.get(currentNode - 1).distance + weight;
						distances.get(dest - 1).parentNode = currentNode;
					}
				}
			}
			
			for(int index = 0; index < edges; index++) {
				currentNode = graph.nodeList.get(index).source;
				dest = graph.nodeList.get(index).dest;
				weight = graph.nodeList.get(index).weight;
				
				if(distances.get(currentNode - 1).distance != Integer.MAX_VALUE && distances.get(currentNode - 1).distance + weight < distances.get(dest - 1).distance) {
					System.out.println("Negative weight cycle found!");
					return null;
				}
			}
			
			for(int index = 1; index <= vertices; index++) {
				System.out.println(index + " " + distances.get(index - 1).distance + " " + distances.get(index - 1).parentNode);
			}
			return distances;
		}
		
		public int[][] floydWarshallAlg(int[][] graph, int vertexs){
			int[][] distances = graph;
			
			for(int index = 0; index < vertexs; index++) {
				for(int row = 0; row < vertexs; row++) {
					for(int column = 0; column < vertexs; column++) {
						if(distances[row][index] == Integer.MAX_VALUE || distances[index][column] == Integer.MAX_VALUE)
							continue;
						else if(distances[row][index] + distances[index][column] < distances[row][column])
							distances[row][column] = distances[row][index] + distances[index][column];
					}
				}
			}
			
			for(int row = 0; row < vertexs; row++) {
				for(int col = 0; col < vertexs; col++)
					System.out.printf("%d ", distances[row][col]);
				System.out.println();
			}
			return distances;
		}
	}

	public static void main(String[] args) {
		PrintWriter pw;
		Scanner scanner;
		int numOfVertexs = 0, rootNode = 0, numOfEdges = 0,first, second, third;
		Graph graph = null;
		ArrayList<edgeNode> distances;
		int fwGraph[][] = null;
		
		try {
			scanner = new Scanner(new File("cop3503-asn3-input.txt"));
			pw = new PrintWriter("cop3503-asn3-output-Wiegman-Ryan.txt");
		}
		catch(Exception e){
			e.printStackTrace();
			return;
		}
		int nodeListCtr = 0;
		int counter = 0;
		while(scanner.hasNext()) {
			if(scanner.hasNextInt()) {
				if(counter == 0) 
					numOfVertexs = scanner.nextInt();
				if(counter == 1)
					rootNode = scanner.nextInt();
				if(counter == 2) {
					numOfEdges = scanner.nextInt();
					graph = new Graph(numOfVertexs, (numOfEdges * 2));
					fwGraph = new int[numOfVertexs][numOfVertexs];
				}
				if(counter > 2) {
					first = scanner.nextInt();
					second = scanner.nextInt();
					third = scanner.nextInt();
					
					graph.nodeList.get(nodeListCtr).source = first;
					graph.nodeList.get(nodeListCtr).dest = second;
					graph.nodeList.get(nodeListCtr).weight = third;
					nodeListCtr++;
					graph.nodeList.get(nodeListCtr).source = second;
					graph.nodeList.get(nodeListCtr).dest = first;
					graph.nodeList.get(nodeListCtr).weight = third;
					nodeListCtr++;
					
					fwGraph[first - 1][second - 1] = third;
					fwGraph[second - 1][first - 1] = third;
				}
				counter++;
			}
			else
				scanner.next();
		}
		System.out.println("Bellman-Ford algorithm: \n" + numOfVertexs);
		pw.println("Bellman-Ford algorithm: \n" + numOfVertexs);
		distances = graph.bellmanFordAlg(graph, rootNode);	
		
		for(int index = 1; index <= numOfVertexs; index++) {
			pw.println(index + " " + distances.get(index - 1).distance + " " + distances.get(index - 1).parentNode);
		}
		
		System.out.println("Floyd-Warshall algorithm: \n" + numOfVertexs);
		pw.println("Floyd-Warshall algorithm: \n" + numOfVertexs);
		
		for(int i = 0; i < 6;i++) {
			for(int j = 0; j < 6; j++) {
				if(fwGraph[i][j] == 0)
					fwGraph[i][j] = Integer.MAX_VALUE;
				if(i == j)
					fwGraph[i][j] = 0;
			}
		}
		fwGraph = graph.floydWarshallAlg(fwGraph, numOfVertexs);
		
		for(int row = 0; row < numOfVertexs; row++) {
			for(int col = 0; col < numOfVertexs; col++)
				pw.printf("%d ", fwGraph[row][col]);
			pw.println();
		}
		
		scanner.close();
		pw.close();
	}
}
