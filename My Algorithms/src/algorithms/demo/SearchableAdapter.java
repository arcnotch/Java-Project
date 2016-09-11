package algorithms.demo;


import java.util.ArrayList;

import algorithms.search.Searchable;
import algorithms.search.State;
import algorithms.mazeGenarators.*;


public class SearchableAdapter implements Searchable<Position> {
	private Maze3d maz3d;
	
	public SearchableAdapter(Maze3d m3d)
	{
		this.maz3d = m3d;
	}
	@Override
	public State<Position> getStartState()
	{
		return new State<Position>(this.maz3d.getStartPosition());
	}
	public Maze3d getM3d() {
		return maz3d;
	}
	public void setM3d(Maze3d m3d) {
		this.maz3d = m3d;
	}
	@Override
	public State<Position> getEndState()
	{
		return new State<Position>(this.maz3d.getendPosition());
	}
	@Override
	public double getMovingCost(State<Position> thisState, State<Position> Near) {
		return 10;
	}
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> Sp)
	{
		ArrayList<Position> arrayPosList =  this.maz3d.getPossibleMoves(Sp.getVal());
		ArrayList<State<Position>> arrSP = new ArrayList<State<Position>>() ;
		if(arrayPosList == null) return null ;
		for(Position p : arrayPosList)
		{
			arrSP.add(new State<Position>(p));
	    }
		
		return arrSP ;
	}


}