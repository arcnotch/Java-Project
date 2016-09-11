package strategy.choosecell;

import algorithms.mazeGenarators.Position;
import java.util.ArrayList;

public interface CellChoice {
	/**
	 * getCell method returns Position from Position list
	 * in a way choose to implements
	 * @param c ArrayList of position to choose position from
	 * @return Position This returns a position in a way choose to implement
	 */
	Position getCell(ArrayList<Position> c);
}