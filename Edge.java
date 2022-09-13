import java.awt.Color;
import java.awt.Graphics;

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
 * Description: Each Edge object has an incoming and 
 * outgoing vertex, time and distance cost, and a 
 * variable to determine which cost should be used 
 * to calculate a path.  
 */

public class Edge implements Comparable<Edge> {

	// ----------------------------------------------------------------------------- Properties
	private Vertex vertexFrom;	// Vertex at the starting point of the edge
	private Vertex vertexTo; 	// Vertex at the ending point of the edge
	private int timeCost;
	private int distCost; 
	private static boolean useDistCost = false;
	
	// ----------------------------------------------------------------------------- Constructors
	public Edge(Vertex vertexFrom, Vertex vertexTo, int timeCost, int distCost) { 
		this.vertexFrom = vertexFrom; 
		this.vertexTo = vertexTo;
		this.timeCost = timeCost;
		this.distCost = distCost; 
	}
	
	// ----------------------------------------------------------------------------- Methods
	/*
	 * Returns the symbol of the vertex that the edge
	 * points to. 
	 */
	@Override
	public String toString() {
		return "" + vertexTo.getSymbol(); 
	}
	
	/*
	 * Puts edges in ascending order based on cost - 
	 * time or distance cost, based on value of 
	 * useDistCost. If edges have same cost, comparison
	 * is based on symbol of  the vertex the edge comes 
	 * from. 
	 */
	@Override
	public int compareTo(Edge e) {
		if (!useDistCost) {
			if (this.timeCost == e.getTimeCost()) {
				return this.vertexTo.getSymbol() - e.getVertexTo().getSymbol();
			} else {
				return this.timeCost - e.getTimeCost();
			}
		} else {
			if (this.distCost == e.getDistCost()) {
				return this.vertexTo.getSymbol() - e.getVertexTo().getSymbol();
			} else {
				return this.distCost - e.getDistCost();
			}
		}
	}
	
	// ----------------------------------------------------------------------------- Getters & Setters 
	public Vertex getVertexFrom() {
		return vertexFrom;
	}

	public Vertex getVertexTo() {
		return vertexTo;
	}

	public int getTimeCost() {
		return timeCost;
	}

	public int getDistCost() {
		return distCost;
	}

	public void setVertexFrom(Vertex vertexFrom) {
		this.vertexFrom = vertexFrom;
	}

	public void setVertexTo(Vertex vertexTo) {
		this.vertexTo = vertexTo;
	}

	public void setTimeCost(int timeCost) {
		this.timeCost = timeCost;
	}

	public void setDistCost(int distCost) {
		this.distCost = distCost;
	}
		
	public static boolean getUseDistCost() {
		return useDistCost;
	}

	public static void setUseDistCost(boolean condition) {
		useDistCost = condition;
	}	
	
}