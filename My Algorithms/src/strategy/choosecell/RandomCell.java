package strategy.choosecell;

import java.util.Random;
import java.util.ArrayList;
import algorithms.mazeGenarators.Position;
import strategy.choosecell.CellChoice;

public class RandomCell implements CellChoice {
	
	private Random rand = new Random();
	
	public Position getCell(ArrayList<Position> c){
		int random_cell = rand.nextInt(c.size());
		return c.get(random_cell);
	}
}