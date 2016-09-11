package algorithms.mazeGenarators;

public abstract class Maze3dAlgorithms implements Maze3dGenerator{
	
	/**
	 * measureAlgorithmTime method measures the time it takes to generate a maze (any kind)
	 * @param level - total levels of the maze
	 * @param row - total rows of the maze
	 * @param col - total columns of the maze
	 * @return String Return the time it took to generate the maze
	 */
	public String measureAlgorithmTime(int level, int row, int col){
		
		long begin = System.currentTimeMillis();
		generate(level, row, col);
		long end = System.currentTimeMillis();
		return String.valueOf(end-begin);
	}
}
