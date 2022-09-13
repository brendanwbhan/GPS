import java.util.PriorityQueue;
import java.util.TreeSet;

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
 * Description: This class calculates the shortest path from one vertex
 * to another on a specified graph, using Dijkstra's algorithm.  All
 * possible paths are put into a PriorityQueue which is prioritized by
 * lowest cost.  When the path with appropriate starting and ending
 * points is first in the queue, it will be returned as the shortest
 * path. 
 */

public class Dijkstra {

	// ----------------------------------------------------------------------------- Properties
	private static int totalCost;
	private static PriorityQueue<Path> pathQueue = new PriorityQueue<Path>();

	// ----------------------------------------------------------------------------- Methods
	/*
	 * Calculates the shortest path from the specified starting to ending
	 * vertex. All possible paths are considered but the path with the smallest
	 * cost (time or distance - dependent on useDistCost variable) is returned. 
	 * If two paths have the same cost, the path that was considered first is 
	 * returned. 
	 */
	public static Path shortestPath(Graph graph, Vertex start, Vertex end) {
		TreeSet<String> visited = new TreeSet<String>();
		Path headPath = new Path(start);
		Path newPath;
		pathQueue.add(headPath);
		
		while(!pathQueue.isEmpty()) {
			if (pathQueue.peek().getLastVertex().equals(end)) {
				totalCost = graph.useDistCost ? pathQueue.peek().getTotalDistCost() : pathQueue.peek().getTotalTimeCost();
				return pathQueue.peek();
			}
			headPath = pathQueue.remove();
			newPath = headPath;
			for (Edge e : headPath.getChildren()) {
				if (newPath.contains(e.getVertexTo())) ;
				else {
					newPath = new Path(headPath);
					newPath.add(e, e.getVertexTo());
					if(graph.useDistCost) { 
						if (!visited.contains("" + e.getVertexTo().getSymbolString() + e.getDistCost())) {
							pathQueue.add(new Path(newPath));
							visited.add("" + e.getVertexTo().getSymbolString() + e.getDistCost());
						}
					} else { // Use time cost  
						if (!visited.contains("" + e.getVertexTo().getSymbolString() + e.getTimeCost())) {
							pathQueue.add(new Path(newPath));
							visited.add("" + e.getVertexTo().getSymbolString() + e.getTimeCost());
						}
					}	
				}	
			}
		}
		return null; 
	}

	// ----------------------------------------------------------------------------- Getters / Setters
	public static int getTotalCost() {
		return totalCost;
	}

	public static void setTotalCost(int totalCost) {
		Dijkstra.totalCost = totalCost;
	}

	public static PriorityQueue<Path> getPathQueue() {
		return pathQueue;
	}

	public static void setPathQueue(PriorityQueue<Path> pathQueue) {
		Dijkstra.pathQueue = pathQueue;
	}	
}
