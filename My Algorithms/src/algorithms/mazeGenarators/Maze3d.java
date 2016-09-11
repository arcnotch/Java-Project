package algorithms.mazeGenarators;

import algorithms.mazeGenarators.Position;

import java.util.ArrayList;

public class Maze3d {
	public final int FREE = 0;
	public final int WALL = 1;
	
	private int[][][] maze_3d;
	private int tColumns;
	private int tRows;
	private int tLevels;
	
	private Position startPos;
	private Position endPos;
	
	

	public Maze3d(int level, int row, int col) {
		super();
		tLevels = level;
		tRows = row;
		tColumns = col;
		
		this.maze_3d = new int[tLevels][tRows][tColumns];
	}	
	
	public Maze3d(int[][][] maze_3d) {
		super();
		this.maze_3d = maze_3d;
	}
	
	/**
	 * Maze3d method is a constructor to make maze_3d from byte array
	 * @param byte_array This is byte array generated
	 */
	public Maze3d(byte[] byte_array){
		int counter = 9; //For loop
		int[] intB = new int[byte_array.length];
		for (int i = 0; i < intB.length; i++) {
			intB[i] = (int) byte_array[i];
		}
		
		//First 3 spots are the size of the maze
		tLevels = intB[0];
		tRows = intB[1];
		tColumns = intB[2];
		
		//another 6 spots are for the start and end positions
		startPos = new Position(intB[3], intB[4], intB[5]);
		endPos = new Position(intB[6], intB[7], intB[8]);
		this.maze_3d = new int[tLevels][tRows][tColumns];
		
		//read the maze it self
		for(int l = 0; l < tLevels; l++){
			for(int r = 0; r < tRows; r++){
				for(int c = 0; c < tColumns; c++){
					this.maze_3d[l][r][c] = intB[counter];
					counter++;
				}
			}
		}
	}
	
	public int[][][] getmaze_3d() {
		return maze_3d;
	}
	
	public int gettLevels() {
		return tLevels;
	}
	public int gettRows() {
		return tRows;
	}
	public int gettColumns() {
		return tColumns;
	}
	
	/**
	 * getVal method gets specific point by x,y,z in the maze and returns it's value
	 * @param level is the given level
	 * @param row is the given row
	 * @param col is the given column
	 * @return int Returns if the cell is free or blocked
	 */
	public int getVal(int level, int row, int col){
		return this.maze_3d[level][row][col];
	}
	
	public Position getStartPosition() {
		return startPos;
	}
	public Position getendPosition() {
		return endPos;
	}
	
	/**
	 * getPossibleMoves method calculates the total possible ways by a Position and return it by list of positions
	 * @param pos is the checked Position
	 * @return ArrayList<Position> Returned list of free nearby Positions
	 */
	public ArrayList<Position> getPossibleMoves(Position pos){
		ArrayList<Position> possible_pos = new ArrayList<Position>();
		int level = pos.getLevel();
		int row = pos.getRow();
		int col = pos.getColumn();
		
		// Down
		if((level-1 >= 0) && getVal(level-1, row, col) == 0){
			possible_pos.add(new Position(level-1, row, col)); 
		}
		// Up
		if((level+1 < this.tLevels) && ( getVal(level+1, row, col) == 0)){
			possible_pos.add(new Position(level+1, row, col)); 
		}
		
		// Backward
		if((row-1 < this.tRows) && getVal(level, row-1, col) == 0){
			possible_pos.add(new Position(level, row-1, col)); 
		}
		// Forward
		if((row+1 < this.tRows) && getVal(level, row+1, col) == 0){
			possible_pos.add(new Position(level, row+1, col)); 
		}
		
		// Right
		if((col+1 < this.tColumns) && getVal(level, row, col+1) == 0){
			possible_pos.add(new Position(level, row, col+1)); 
		}
		// Left
		if((col-1 < this.tColumns) && getVal(level, row, col-1) == 0){
			possible_pos.add(new Position(level, row, col-1)); 
		}
		
		return possible_pos;
	}
	
	@Override
	public boolean equals(Object objec) {
		if(objec.getClass() == this.getClass()){
			Maze3d casted = (Maze3d)objec;
			
			if(casted.toString().equals(this.toString())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * getCrossSectionByZ method is used to receive 2d maze by level
	 * @param lev is the level
	 * @return int[][] Returns int - 2d Maze of the tRows and tColumns of the specific level
	 */
	public int[][] getCrossSectionByZ(int lev){
		if(lev < tLevels && lev >= 0){
			int[][] maze2D = new int[tRows][tColumns];
			
			for (int i = 0; i < tRows; i++) {
				for (int j = 0; j < tColumns; j++) {
					maze2D[i][j] = this.getVal(lev, i, j); //copy value
				}
			}
			
			return maze2D;
		}else{
			throw(new IndexOutOfBoundsException("invalid boundries"));
		}
	}
	
	/**
	 * getCrossSectionByY method is used to receive 2d maze by row
	 * @param row is the row
	 * @return int[][] Returns int - 2d Maze of the tLevels and tColumns of the specific row
	 */
	public int[][] getCrossSectionByY(int row){
		if(row < tRows && row >= 0){
			int[][] maze2D = new int[tLevels][tColumns];
			
			for (int i = 0; i < tLevels; i++) {
				for (int j = 0; j < tColumns; j++) {
					maze2D[i][j] = this.getVal(i, row, j);//copy value
				}
			}

			return maze2D;
		}else{
			throw(new IndexOutOfBoundsException("invalid boundries"));
		}
	}
	
	/**
	 * getCrossSectionByX method is use to receive 2d maze by column
	 * @param col is the column
	 * @return int[][] Returns int - 2d Maze of the tLevels and tRows of the specific column
	 */
	public int[][] getCrossSectionByX(int col){
		if(col < tColumns && col >= 0){
			int[][] maze2D = new int[tLevels][tRows];
			
			for (int i = 0; i < tLevels; i++) {
				for (int j = 0; j < tRows; j++) {
					maze2D[i][j] = this.getVal(i, j, col);//copy value
				}
			}
			
			return maze2D;
		}else{
			throw(new IndexOutOfBoundsException("invalid boundries"));
		}
	}

	//Sets new start position
	public void setStartPosition(Position startPosition) {
		this.startPos = startPosition;
		this.Freeing(startPos);
	}
	//Sets new end position
	public void setEndPosition(Position endPosition) {
		this.endPos = endPosition;
	}
	
	/**
	 * setWall sets the value of WALL in a specific location in the maze
	 * @param level is the wanted level
	 * @param row is the wanted row 
	 * @param col is the wanted column
	 */
	public void setWall(int level, int row, int col){
		this.maze_3d[level][row][col] = WALL;
	}
	
	/**
	 * setWall method set the value WALL in a specific Position in the maze
	 * @param pos is the wanted Position 
	 */
	public void setWall(Position pos){
		this.maze_3d[pos.getLevel()][pos.getRow()][pos.getColumn()] = WALL;
	}
	
	/**
	 * Freeing method set the value FREE in a specific location in the maze
	 * @param level is the wanted level
	 * @param row is the wanted row 
	 * @param col is the wanted column
	 */
	public void Freeing(int level, int row, int col){
		this.maze_3d[level][row][col] = FREE;
	}
	
	/**
	 * Freeing method set the value WALL in a specific Position in the maze
	 * @param pos is the wanted Position 
	 */
	public void Freeing(Position pos){
		this.maze_3d[pos.getLevel()][pos.getRow()][pos.getColumn()] = FREE;
	}
	
	public void setmaze_3d(int[][][] maze_3d) {
		this.maze_3d = maze_3d;
	}
	
	/**
	 * setValue method sets the wanted value in a specific location in the maze
	 * @param level is the wanted level
	 * @param row is the wanted row 
	 * @param col is the wanted column
	 * @param value is the wanted value
	 */
	public void setValue(int level, int row, int col, int value){
		this.maze_3d[level][row][col] = value;
	}
	
	/**
	 * toByteArray method returns bytes list of the maze properties and structure
	 * The order of the  generated list:
	 * tLevels, tRows, tColumns - represent the size of the maze
	 * start level, start row, start column - represent the location of the start position
	 * end level, end row, end column - represent the location of the end position
	 * than the value of [0][0][0], [0][0][1] ... [tLevels][tRows][tColumns] - the maze it self
	 * @return byte[] Returns the list of data (the package of the maze)
	 */
	public byte[] toByteArray(){
		byte[] array = new byte[(3*3)+(tLevels*tRows*tColumns)];
		//maze size
		array[0] = (byte) tLevels;
		array[1] = (byte) tRows;
		array[2] = (byte) tColumns;
		//start position
		array[3] = (byte) startPos.getLevel();
		array[4] = (byte) startPos.getRow();
		array[5] = (byte) startPos.getColumn();
		//end position
		array[6] = (byte) endPos.getLevel();
		array[7] = (byte) endPos.getRow();
		array[8] = (byte) endPos.getColumn();
		int count = 9;
		//Copy the maze
		for(int l = 0; l < tLevels; l++){
			for(int r = 0; r < tRows; r++){
				for(int c = 0; c < tColumns; c++){
					array[count]=(byte)maze_3d[l][r][c];
					count++;
				}
			}
		}
		
		return array;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int l = 0; l < this.tLevels; l++){
			builder.append("\n\n");
			for (int r = 0; r < this.tRows; r++) {
				for (int c = 0; c < this.tColumns; c++) {
					if(l == startPos.getLevel() && r == startPos.getRow() && c == startPos.getColumn())
						builder.append("S ");
					else if(l == endPos.getLevel() && r == endPos.getRow() && c == endPos.getColumn())
						builder.append("E ");
					else
						builder.append(this.maze_3d[l][r][c] + " ");
				}
				builder.append("\n");
			}
		}
		
		return builder.toString();
	}

}
