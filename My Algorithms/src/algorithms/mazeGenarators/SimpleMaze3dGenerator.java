package algorithms.mazeGenarators;

import java.util.Random;
import algorithms.mazeGenarators.Position;

public class SimpleMaze3dGenerator extends Maze3dAlgorithms{
	private Random rand = new Random();
	
	private Maze3d maze3d;
	
	/**
	 * randomizeWalls method sets random walls in the maze
	 */
	private void randomizeWalls(){
		// sets random FREE or WALL for every cell in the maze
		
		for (int level = 0; level < maze3d.gettLevels(); level++)
			for (int row = 0; row < maze3d.gettRows(); row++)
				for (int colm = 0; colm < maze3d.gettColumns(); colm++)
					maze3d.setValue(level, row, colm, rand.nextInt(2));
	}
	
	/**
	 * buildBasicFrame method sets walls around the maze  - frame the maze
	 */
	public void buildBasicFrame(){
		for (int l = 0; l < maze3d.gettLevels(); l++)
			for(int r = 0; r < maze3d.gettRows(); r++){
				maze3d.setWall(l, r, 0);
				maze3d.setWall(l, r, maze3d.gettColumns()-1);
			}
		
		for (int c = 0; c < maze3d.gettColumns(); c++)
			for(int r = 0; r < maze3d.gettRows(); r++){
				maze3d.setWall(0, r, c);
				maze3d.setWall(maze3d.gettLevels()-1, r, c);
			}
		
		for (int l = 0; l < maze3d.gettLevels(); l++)
			for(int c = 0; c < maze3d.gettColumns(); c++){
				maze3d.setWall(l, 0, c);
				maze3d.setWall(l, maze3d.gettRows()-1, c);
			}
	}
	
	/**
	 * randomSnEPositon method choose randomly a position in the maze
	 * in order to set start and end position
	 * @return Position Returns random position in the maze (not in frames)
	 */
	public Position randomSnEPositon(){
		// Random column not in frame
		int column = rand.nextInt(maze3d.gettColumns()-2);
		column++; //+1 to insure its not on frame
		
		// Random row not in frame
		int row = rand.nextInt(maze3d.gettRows()-2);
		row++; //+1 to insure its not on frame
		
		// Random level not in frame
		int level = rand.nextInt(maze3d.gettLevels()-2);
		level++; //+1 to insure its not on frame
			
		return new Position(level, row, column);
	}
	
	/**
	 * digPath method digging free spaces from start to end
	 * to ensure there is a possible way out
	 * @param start is the start Position of the maze
	 * @param end is the end Position of the maze
	 */
	public void digPath(Position start, Position end){
		int lev = start.getLevel();
		int row = start.getRow();
		
		//Freeing order is level->row->column
		// sets FREE (room) through every level of start end points
		if(start.getLevel() < end.getLevel()){
			for (lev = start.getLevel() ; lev <= end.getLevel() ; lev++)
				maze3d.setWall(lev, start.getRow(), start.getColumn());
			
			lev=lev-1;
		}
		else{
			for (lev = start.getLevel() ; lev >= end.getLevel() ; lev--)
				maze3d.setWall(lev, start.getRow(), start.getColumn());
			
			lev=lev+1;
		}
		
		// sets FREE (room) through every row of start end points
		if(start.getRow() < end.getRow()){
			for (row = start.getRow() ; row <= end.getRow() ; row++)
				maze3d.Freeing(lev, row, start.getColumn());
			
			row=row-1;
		}
		else{
			for (row = start.getRow() ; row >= end.getRow() ; row--)
				maze3d.Freeing(lev, row, start.getColumn());
			
			row=row+1;
		}

		// sets FREE (room) through every column of start end points
		if(start.getColumn() < end.getColumn())
			for (int i = start.getColumn() ; i <= end.getColumn() ; i++)
				maze3d.Freeing(lev, row, i);
		else
			for (int i = start.getColumn() ; i >= end.getColumn(); i--)
				maze3d.Freeing(lev, row, i);
		
	}
	
	public Maze3d generate(int levels, int rows, int columns){
		this.maze3d = new Maze3d(levels+2, rows+2, columns+2); //Frame+dividers
		
		randomizeWalls();
		buildBasicFrame(); //generating the frame
		
		
		Position startPos = randomSnEPositon();
		Position endPos = startPos;
		
		
		// generate a new end position while its the start position (start !=end)
		while(startPos == endPos)
			endPos = randomSnEPositon();
		
		maze3d.setStartPosition(startPos);
		maze3d.setEndPosition(endPos);
		
		//insure path
		digPath(startPos, endPos);
		
		return maze3d;
		
	}
}
