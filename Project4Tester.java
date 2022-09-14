public class Project4Tester {

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
 * Description: Tester class
 * 
 */
	
	public static void main(String[] args) {
		
		// -------------------------------------------------------- GRAPH TESTING
		Graph graphTest = new Graph("MapInformation.txt", "MapInformationXY.txt"); 
				
		System.out.println(graphTest.toString());
				
		Vertex v1 = graphTest.getVertexFromChar('A');
		Vertex v2 = graphTest.getVertexFromChar('K');

		Vertex v3 = graphTest.getVertexFromChar('R');
		
		for (Vertex v : graphTest.getGraphMap().keySet()) {
			System.out.println("" + v.getSymbol() + " " + v.getX() + " " + v.getY());
		}

		System.out.println(Dijkstra.shortestPath(graphTest, v1, v2).getPathStr());
		System.out.println(Dijkstra.getTotalCost());
		System.out.println(Dijkstra.shortestPath(graphTest, v1, v2).getPathStr());
		System.out.println(Dijkstra.getTotalCost());
		
		System.out.println();
		System.out.println("A --> R");
		System.out.println(Dijkstra.shortestPath(graphTest, v1, v3).getPathStr());
		System.out.println(Dijkstra.getTotalCost());
		
	}
}
