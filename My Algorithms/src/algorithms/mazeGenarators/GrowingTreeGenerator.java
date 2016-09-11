package algorithms.mazeGenarators;

import java.util.ArrayList;
import java.util.Random;
import strategy.choosecell.*;
import algorithms.mazeGenarators.Direction;

public class GrowingTreeGenerator extends Maze3dAlgorithms {
	private Random rand = new Random();
	
	private Maze3d maze3d;
	Position endPos;
	private ArrayList<Position> WhereIWas = new ArrayList<Position>();
	private CellChoice Choice = new RandomCell();
	
	/**
	 * chooseRandomPos method generate a Position by random
	 * @return Return the random Position
	 */
	private Position chooseRandomPos() {
		//Generate random ints  from 0 to size of Levels,Rows or Columns of the maze
		// %2==0 it must be odd number. odd = path (maze design)
		int lev = rand.nextInt(maze3d.gettLevels());
		while (lev % 2 == 0)
			lev = rand.nextInt(maze3d.gettLevels());
		
		int row = rand.nextInt(maze3d.gettRows());
		while (row % 2 == 0)
			row = rand.nextInt(maze3d.gettRows());
		
		int col = rand.nextInt(maze3d.gettColumns());
		while (col % 2 == 0)
			col = rand.nextInt(maze3d.gettColumns());
		
		if(maze3d.getVal(lev, row, col) == 0)
			return chooseRandomPos();
		
		return new Position(lev, row, col);		
	}
		
	@Override
	public Maze3d generate(int level, int row, int col) {
		maze3d = new Maze3d(level*2+1, row*2+1, col*2+1); // Includes frame and walls
		
		// Sets walls in every spot
		for (int l = 0; l < maze3d.gettLevels(); l++)
			for (int r = 0; r < maze3d.gettRows(); r++)
				for (int c = 0; c < maze3d.gettColumns(); c++)
					maze3d.setValue(l, r, c, 1); 
		
		Position startPos = chooseRandomPos();
		
		maze3d.setStartPosition(startPos);
		
		endPos = chooseRandomPos();
		maze3d.setEndPosition(endPos);

		// Digger
		GrowingTree(startPos);
		
		return maze3d;
	}
	
	
	/**
	 * GrowingTree method digging by position it gets and make room randomly nearby
	 * @param thisPos The current position
	 */
	private void GrowingTree(Position thisPos) {
		Position nPos;

		if (thisPos == null){
			return; // End
		}
		
		WhereIWas.add(0, thisPos); // Add this position (currect position) to stack
		
		// get possible moves (where can I go)
		ArrayList<Direction> dirs = getPossibleDirections(thisPos);
		
		// make sure there is directions
		if (dirs.size() == 0)
			dirs.add(Direction.BLOCK);

		// choose random direction
		int dirction = rand.nextInt(dirs.size());
		
		// digging (make room) by the random position
		//it breaks the wall and make room in the direction's cell
		switch (dirs.get(dirction)) {
		case Right:
			maze3d.Freeing(thisPos.getLevel(), thisPos.getRow(), thisPos.getColumn() + 1);
			maze3d.Freeing(thisPos.getLevel(), thisPos.getRow(), thisPos.getColumn() + 2);
			nPos = new Position(thisPos.getLevel(), thisPos.getRow(), thisPos.getColumn() + 2);
		break;
		case Left:
			maze3d.Freeing(thisPos.getLevel(), thisPos.getRow(), thisPos.getColumn() - 1);
			maze3d.Freeing(thisPos.getLevel(), thisPos.getRow(), thisPos.getColumn() - 2);
			nPos = new Position(thisPos.getLevel(), thisPos.getRow(), thisPos.getColumn() - 2);
		break;
		case Forward:
			maze3d.Freeing(thisPos.getLevel(), thisPos.getRow() + 1, thisPos.getColumn());
			maze3d.Freeing(thisPos.getLevel(), thisPos.getRow() + 2, thisPos.getColumn());
			nPos = new Position(thisPos.getLevel(), thisPos.getRow() + 2, thisPos.getColumn());
		break;
		case Backward:
			maze3d.Freeing(thisPos.getLevel(), thisPos.getRow() - 1, thisPos.getColumn());
			maze3d.Freeing(thisPos.getLevel(), thisPos.getRow() - 2, thisPos.getColumn());
			nPos = new Position(thisPos.getLevel(), thisPos.getRow() - 2, thisPos.getColumn());
		break;
		case Up:
			maze3d.Freeing(thisPos.getLevel() + 1, thisPos.getRow(), thisPos.getColumn());
			maze3d.Freeing(thisPos.getLevel() + 2, thisPos.getRow(), thisPos.getColumn());
			nPos = new Position(thisPos.getLevel() + 2, thisPos.getRow(), thisPos.getColumn());
		break;
		case Down:
			maze3d.Freeing(thisPos.getLevel() - 1, thisPos.getRow(), thisPos.getColumn());
			maze3d.Freeing(thisPos.getLevel() - 2, thisPos.getRow(), thisPos.getColumn());
			nPos = new Position(thisPos.getLevel() - 2, thisPos.getRow(), thisPos.getColumn());
		break;
		default:
			// If the stack has at least 1 way back to go
			if(WhereIWas.size() >= 2)
				nPos = Choice.getCell(WhereIWas);
			else
				nPos = null; // End and exit at the end of the method
			
			// if the stack is empty
			if(WhereIWas.size() >= 1)
				WhereIWas.remove(0);
			if(WhereIWas.size() >= 1)
				WhereIWas.remove(0);
		break;
		}
		
		// Keep digging
		GrowingTree(nPos);
	}
	
	/**
	 * getPossibleDirections method returns a list of possible directions to go through by Position it gets
	 * @param thisPos is the position the method makes the check on
	 * @return ArrayList<Direction> Returns a list of possible Directions 
	 */
	private ArrayList<Direction> getPossibleDirections(Position thisPos) {
		ArrayList<Direction> directs = new ArrayList<Direction>();
		
		//Check for possible dirrections by existing in frame and pathes
		if ((thisPos.getLevel() + 2 >= 0) && (thisPos.getLevel() + 2 < maze3d.gettLevels()) && 
				maze3d.getVal(thisPos.getLevel() + 2, thisPos.getRow(), thisPos.getColumn()) == maze3d.WALL) {
			directs.add(Direction.Up);
		}
		
		if ((thisPos.getLevel() - 2 >= 0) && (thisPos.getLevel() - 2 < maze3d.gettLevels()) && 
				maze3d.getVal(thisPos.getLevel() - 2, thisPos.getRow(), thisPos.getColumn()) == maze3d.WALL) {
			directs.add(Direction.Down);
		}
		
		if ((thisPos.getRow() + 2 >= 0) && (thisPos.getRow() + 2 < maze3d.gettRows()) && 
				maze3d.getVal(thisPos.getLevel(), thisPos.getRow() + 2, thisPos.getColumn()) == maze3d.WALL) {
			directs.add(Direction.Forward);
		}
		
		if ((thisPos.getRow() - 2 >= 0) && (thisPos.getRow() - 2 < maze3d.gettRows()) && 
				maze3d.getVal(thisPos.getLevel(), thisPos.getRow() - 2, thisPos.getColumn()) == maze3d.WALL) {
			directs.add(Direction.Backward);
		}
		
		if ((thisPos.getColumn() + 2 >= 0) && (thisPos.getColumn() + 2 < maze3d.gettColumns()) && 
				maze3d.getVal(thisPos.getLevel(), thisPos.getRow(), thisPos.getColumn() + 2) == maze3d.WALL) {
			directs.add(Direction.Right);
		}
		
		if ((thisPos.getColumn() - 2 >= 0) && (thisPos.getColumn() - 2 < maze3d.gettColumns()) && 
				maze3d.getVal(thisPos.getLevel(), thisPos.getRow(), thisPos.getColumn() - 2) == maze3d.WALL) {
			directs.add(Direction.Left);
		}
		
		return directs;
	}
}
