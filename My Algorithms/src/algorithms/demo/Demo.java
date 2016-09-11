package algorithms.demo;


import algorithms.mazeGenarators.*;
import algorithms.search.*;


public class Demo {
	private static void run(Maze3dGenerator mazeGen)
	{
		
		SearchableAdapter newmaze = new SearchableAdapter(mazeGen.generate(10,10,10));
//		System.out.println(maze.getM3d().toString());
		
		Searcher<Position> BFS = new BFS<Position>();
		BFS.search(newmaze);
		System.out.println("BFS:");
		System.out.println(BFS.getNumberOfNodes());
		Searcher<Position> DFS = new DFS<Position>();
		DFS.search(newmaze);
		System.out.println("DFS:");
		System.out.println(DFS.getNumberOfNodes());
	}

	public static void main(String[] args) {
		run(new GrowingTreeGenerator());
	}
}
