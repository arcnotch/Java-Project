package algorithms.mazeGenarators;

public interface Maze3dGenerator {
	/**
	 * generate method generates and returns a new Maze3d
	 * by levels, rows and columns
	 * @param levels is the total levels
	 * @param rows is the total rows
	 * @param colms is the total columns
	 * @return Maze3d Returns a Maze3d object
	 */
	Maze3d generate(int level, int row, int col);
	
	/**
	 * measureAlgorithmTime method measures the time it takes to generate a maze (any kind)
	 * @param level - total levels of the maze
	 * @param row - total rows of the maze
	 * @param col - total columns of the maze
	 * @return String Return the time it took to generate the maze
	 */
	String measureAlgorithmTime(int level, int row, int col);
}
