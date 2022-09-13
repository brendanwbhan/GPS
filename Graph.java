import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * @apiNote
 * CSE 274: Project 04 Final Project GPS
 * Professor Salman
 * 05/04/2022
 * 
 * @author Caleb Alexander
 * @author Brendan Han
 * @author Julia Loncala
 * 
 * Description: A Graph object contains a HashMap of its vertices 
 * and sets of edges. 
 */

public class Graph extends JPanel {

	// ----------------------------------------------------------------------------- Properties
	public static boolean useDistCost; 
	public static boolean returnAddress; 
	private Map<Vertex, TreeSet<Edge>> graphMap = new HashMap<Vertex, TreeSet<Edge>>(); 
	
	// ----------------------------------------------------------------------------- Constructors
	public Graph(String fileName, String fileNameXY) { 
		loadVertex(fileName); 
		loadEdge(fileName); 
		loadXY(fileNameXY);
		
		for(Vertex v : graphMap.keySet()) { 
			v.setEdges((TreeSet<Edge>)graphMap.get(v));
		}
	}
	
	// ----------------------------------------------------------------------------- Methods
	/*
	 * Grabs the symbols and addresses of each vertex from the specified 
	 * file. Stores each as a vertex object and puts them in the graph. 
	 */
	private void loadVertex(String fileName) { 
		try {
			Scanner in = new Scanner(new File(fileName));
			
			while(!in.nextLine().contains("<Nodes>")); 
			in.nextLine(); 
			
			String currLine = in.nextLine(); 
			String[] vertexInfo = currLine.split("\t"); 
			while(!currLine.contains("</Nodes>")) { 
				graphMap.put(new Vertex(vertexInfo[0].charAt(0), vertexInfo[1]), new TreeSet<Edge>()); 
				currLine = in.nextLine(); 
				vertexInfo = currLine.split("\t"); 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	/*
	 * Grabs the source, destination, and costs of each edge from the 
	 * specified file. Stores each as an edge object and puts them in 
	 * the graph. 
	 */
	private void loadEdge(String fileName) { 
		try {
			Scanner in = new Scanner(new File(fileName));
			while(!in.nextLine().contains("<Edges>")); 
			in.nextLine(); 
			
			String currLine = in.nextLine(); 
			String[] edgeInfo = currLine.split("\t");
			Vertex vIn = null; 
			Vertex vOut = null; 
			while(!currLine.contains("</Edges>")) { 
				vIn = getVertexFromChar(edgeInfo[0].charAt(0));
				vOut = getVertexFromChar(edgeInfo[1].charAt(0));
				Edge e = new Edge(vIn, vOut, Integer.parseInt(edgeInfo[2]), Integer.parseInt(edgeInfo[3]));
				graphMap.get(getVertexFromChar(edgeInfo[0].charAt(0))).add(e);
				
				currLine = in.nextLine(); 
				edgeInfo = currLine.split("\t"); 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	/*
	 * Grabs the x and y coordinates of each vertex from the specified file 
	 * and stores them in the appropriate vertex object. 
	 */
	private void loadXY(String fileName) {
		try {
			Scanner in = new Scanner(new File(fileName));
			
			while(!in.nextLine().contains("<Nodes>")); 
			in.nextLine(); 
			
			String currLine = in.nextLine(); 
			String[] vertexInfo = currLine.split("\t"); 
			Vertex v;
			while(!currLine.contains("</Nodes>")) { 
				v = getVertexFromChar(vertexInfo[0].charAt(0));
				v.setX(Integer.parseInt(vertexInfo[2]));
				v.setY(Integer.parseInt(vertexInfo[3]));
				currLine = in.nextLine(); 
				vertexInfo = currLine.split("\t"); 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	/*
	 * Displays each vertex and the set of its edges
	 */
	@Override
	public String toString() {
		String ret = ""; 
		for(Vertex v : graphMap.keySet()) { 
			ret += v + " | ";
			for(Edge e : graphMap.get(v)) { 
				ret += e + " " + (useDistCost ? e.getDistCost() : e.getTimeCost()) + ", ";
			}
			ret += "\n"; 
		}
		return ret; 
	}
	
	/*
	 * Return a Vertex object whose symbol matches
	 * the char passed in. 
	 */
	public Vertex getVertexFromChar(char symbol) {
		for (Vertex v : graphMap.keySet()) {
			if (symbol == v.getSymbol()) return v;
		}
		return null;
	}
	
	/*
	 * Sorts the locations in alphabetical order 
	 * in an array. 
	 */
	public Object[] toArray() {
		Object[] ret = graphMap.keySet().toArray(); 
		Arrays.sort(ret);
		return ret; 
	}
		
	// ----------------------------------------------------------------------------- Getters / Setters
	public static boolean isReturnAddress() {
		return returnAddress;
	}

	public static void setReturnAddress(boolean returnAddress) {
		Graph.returnAddress = returnAddress;
		Path.returnAddress = returnAddress; 
	}

	public Map<Vertex, TreeSet<Edge>> getGraphMap() {
		return graphMap;
	}

	public void setGraphMap(Map<Vertex, TreeSet<Edge>> graphMap) {
		this.graphMap = graphMap;
	}

	public static boolean isUseDistCost() {
		return useDistCost;
	}

	public static void setUseDistCost(boolean condition) {
		useDistCost = condition;
		Edge.setUseDistCost(condition);
		Path.setUseDistCost(condition);
	}	
}