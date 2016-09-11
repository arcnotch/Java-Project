package strategy.choosecell;

import algorithms.mazeGenarators.Position;
import strategy.choosecell.CellChoice;
import java.util.ArrayList;

public class LastCell implements CellChoice {
	public Position getCell(ArrayList<Position> c){
		return c.get(1);
	}
}