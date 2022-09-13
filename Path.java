import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JComponent;

/**
 * @apiNote
 * CSE 274: Project 04 Final Project GPS
 * Professor Salman
 * 05/05/2022
 * 
 * @author Caleb Alexander
 * @author Brendan Han
 * @author Julia Loncala
 * 
 * Description: Path objects have an ArrayList of vertices which 
 * denote each step of the path and an ArrayList of edges that 
 * connect the vertices.  The path also has a String that contains
 * the vertex symbols of the path. Total cost of the path is 
 * maintained by accumulating the cost of each edge in the path.  
 */

public class Path implements Comparable<Path> {
	
	// ----------------------------------------------------------------------------- Properties
	private ArrayList<Vertex> path = new ArrayList<Vertex>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private String pathStr; // A String of symbols denoting a path from one vertex to another
	private int totalTimeCost;
	private int totalDistCost;
	private static boolean useDistCost = false;
	public static boolean returnAddress; 
	
	// ----------------------------------------------------------------------------- Constructors
	public Path(Vertex start) {
		path.add(start);
		pathStr = "" + start.getSymbol();
	}
	
	public Path(Path pathCopy) {
		this.path = new ArrayList<Vertex>(pathCopy.getPath());
		this.edges = new ArrayList<Edge>(pathCopy.getEdges());
		this.pathStr = new String(pathCopy.getPathStr());
		this.totalDistCost = pathCopy.getTotalDistCost();
		this.totalTimeCost = pathCopy.getTotalTimeCost();
	}
	
	// ----------------------------------------------------------------------------- Methods
	
	/*
	 * Adds the specified vertex to the path list of
	 * vertices, and adds the specified edge to the 
	 * list of edges. Accumulates the cost of each 
	 * edge and adds the vertex symbol to path
	 * string. 
	 */
	public void add(Edge edgeTo, Vertex vertexNext) {
		path.add(vertexNext);
		edges.add(edgeTo);
		
		totalTimeCost += edgeTo.getTimeCost();
		totalDistCost += edgeTo.getDistCost();
		pathStr += "" + vertexNext.getSymbol();
	}
	
	/*
	 * Orders the paths in ascending order based on 
	 * cost - time or distance cost dependent on
	 * useDistCost variable. 
	 */
	@Override
	public int compareTo(Path p) {
		if (!useDistCost) return this.totalTimeCost - p.totalTimeCost;
		else return this.totalDistCost - p.totalDistCost;
	}
	
	/*
	 * Returns the set of edges of the last vertex
	 * in this path. 
	 */
	public TreeSet<Edge> getChildren() {
		Vertex v = path.get(path.size() - 1);
		return v.getEdges();
	}
	
	/*
	 * Returns the last vertex in the path - the 
	 * destination of the path. 
	 */
	public Vertex getLastVertex() {
		return path.get(path.size() - 1);
	}
	
	/*
	 * Returns each vertex symbol in the path. 
	 */
	@Override
	public String toString() {
		return getPathStr();
	}
	
	/*
	 * Formats the path by displaying one step / line. Includes the cost
	 * of each step, and the total cost of the entire path - either time 
	 * or distance cost, depending on boolean value. 
	 */
	public String displayString() { 
		String ret = "";
		int totalCost = 0; 
		
		if(returnAddress) { 
			if(this.getPathStr().length() == 1) return String.format("%s     %d %s", this.path.get(0).getAddress(), totalCost, (Graph.useDistCost ? "miles" : "minutes")); 
			for(int i = 0; i < this.getPathStr().length() - 1; i++) { 
				ret += String.format("%s --> %s     %d %s\n", this.path.get(i).getAddress(), this.path.get(i + 1).getAddress(), getCost(path.get(i)), (Graph.useDistCost ? "miles" : "minutes")); 
				totalCost += getCost(path.get(i)); 
			}
		} else {
			if(this.getPathStr().length() == 1) return String.format("%c     %d %s", this.getPathStr().charAt(0), totalCost, (Graph.useDistCost ? "miles" : "minutes")); 
			for(int i = 0; i < this.getPathStr().length() - 1; i++) { 
				ret += String.format("%s --> %s     %d %s\n", this.getPathStr().charAt(i), this.getPathStr().charAt(i + 1), getCost(path.get(i)), (Graph.useDistCost ? "miles" : "minutes")); 
				totalCost += getCost(path.get(i)); 
			}
		}
		return ret + String.format("\nDuration of trip: %d %s", totalCost, (Graph.useDistCost ? "miles" : "minutes"));
	}
	
	/*
	 * Checks if the specified vertex is part of 
	 * the path. 
	 */
	public boolean contains(Vertex comp) {
		for (Vertex v : path) {
			if (v.equals(comp)) return true;
		}
		return false;
	}
	
	/*
	 * Returns the cost of an edge based on the starting
	 * vertex of that edge. Cost is returned as time or 
	 * distance, dependent on value of useDistCost variable. 
	 */
	public int getCost(Vertex vStart) { 
		for(int i = 0; i < path.size(); i++)
			if(Graph.useDistCost) {
				if(path.get(i).equals(vStart)) {
					return path.get(i).edgeBetween(path.get(i + 1)).getDistCost();
				}
			} else { 
				if(path.get(i).equals(vStart)) {
					return path.get(i).edgeBetween(path.get(i + 1)).getTimeCost();
				}
			}
		return 0; 
	}

	// ----------------------------------------------------------------------------- Getters / Setters
	public ArrayList<Vertex> getPath() {
		if(path.isEmpty()) return null;
		return path;
	}
	
	public void setPath(ArrayList<Vertex> path) {
		this.path = path;
	}
	
	public int getTotalTimeCost() {
		return totalTimeCost;
	}
	
	public void setTotalTimeCost(int totalTimeCost) {
		this.totalTimeCost = totalTimeCost;
	}
	
	public int getTotalDistCost() {
		return totalDistCost;
	}
	
	public void setTotalDistCost(int totalDistCost) {
		this.totalDistCost = totalDistCost;
	}

	public String getPathStr() {
		return pathStr;
	}

	public void setPathStr(String pathStr) {
		this.pathStr = pathStr;
	}

	public static boolean isUseDistCost() {
		return useDistCost;
	}

	public static void setUseDistCost(boolean condition) {
		useDistCost = condition;
	}


	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
}