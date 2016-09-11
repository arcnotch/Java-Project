package algorithms.search;

import java.util.List;

public interface Searchable<T> {
	
	/**
	 * getStartState method returns the searchable start
	 * @return State<T> Returns the start of searchable object
	 */
	State<T> getStartState();
	
	/**
	 * getEndState method returns the searchable end
	 * @return State<T> Returns the end of searchable object
	 */
	State<T> getEndState();
	
	/**
	 * getMovingCost method returns the price of stepping from currState to neighbor
	 * @param currState is the initial state
	 * @param neighbor is the nearby wanted state (neighbor)
	 * @return double Returns the total cost of the move
	 */
	double getMovingCost(State<T> thisState, State<T> Neighbor);
	
	/**
	 * getAllPossibleStates method returns a list of nearby possible states by a specific state
	 * @param s is the specific state
	 * @return List<State<T>> Returns a list of possible states
	 */
	List<State<T>> getAllPossibleStates(State<T> s);
}