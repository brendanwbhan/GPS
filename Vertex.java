import java.util.TreeSet;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

import java.awt.Graphics;
import java.awt.Color;

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
 * Description: Each Vertex object has a unique symbol,
 * address, set of edges, and coordinates. The set of edges
 * describes the outgoing connections of that vertex. 
 */

public class Vertex implements Comparable<Vertex> {

	// ----------------------------------------------------------------------------- Properties
	private char symbol;			// Letter of location
	private String address; 		// Location address
	private int x, y; 				// Coordinates on graph
	private TreeSet<Edge> edges;	// Set of edges corresponding to each vertex
	private static final int HEIGHT = 20, WIDTH = 20;
	
	// ----------------------------------------------------------------------------- Constructors
	public Vertex(char symbol, String address) { 
		this.symbol = symbol;
		this.address = address; 
	}
	
	public Vertex(char symbol, String address, int x, int y) { 
		this(symbol, address);
		this.x = x; 
		this.y = y; 
	}
	
	// ----------------------------------------------------------------------------- Methods
	/*
	 * Adds the specified edge to this vertex's set of edges. 
	 */
	public void addEdge(Edge e) { 
		edges.add(e);
	}
	
	/*
	 * Prints this vertex's symbol and address in the 
	 * format: "A, 123 Main St."
	 */
	@Override
	public String toString() {
		return String.format("%c, %s", this.symbol, this.address); 
	}
	
	/*
	 * Returns the edge that connects this vertex and 
	 * another specified vertex. 
	 */
	public Edge edgeBetween(Vertex v) { 
		for(Edge e : edges) 
			if(e.getVertexTo().equals(v)) return e; 
		return null; 
	}
	
	/*
	 * Vertices will be sorted in alphabetical order
	 * based on their symbol. 
	 */
	@Override
	public int compareTo(Vertex v) { // Locations will be in alphabetical order based on their symbol
		return this.symbol - v.symbol; 
	}
	
	// ----------------------------------------------------------------------------- Getters & Setters
	public char getSymbol() {
		return symbol;
	}
	
	public String getSymbolString() {
		return "" + symbol;
	}

	public String getAddress() {
		return address;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public TreeSet<Edge> getEdges() {
		return edges;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setEdges(TreeSet<Edge> edges) {
		this.edges = edges;
	}
}
