package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class BFS<T> extends CommonSearcher<T> {

	private Set<State<T>> BlockedList = new HashSet<State<T>>();
	private PriorityQueue<State<T>> PriQueList = new PriorityQueue<State<T>>();
	
	
	@Override
	public Solution<T> search(Searchable<T> search) {
		
		State<T> sState = search.getStartState();
		PriQueList.add(sState);
		
		while (!PriQueList.isEmpty()) {
			
			State<T> thisState = PriQueList.poll();
			BlockedList.add(thisState);
			
			State<T> endState = search.getEndState();
			if (thisState.equals(endState)) {
				return BackTrace(thisState);
			}
			
			List<State<T>> Neighbors = search.getAllPossibleStates(thisState);
			for (State<T> Near : Neighbors) {
				if (!PriQueList.contains(Near) && !BlockedList.contains(Near)) {
					addNode();
					Near.setPrevState(thisState);
					Near.setCost(thisState.getCost() + search.getMovingCost(thisState, Near));
					PriQueList.add(Near);
				}
				
				else {
					double newPathCost = thisState.getCost() + search.getMovingCost(thisState, Near);
					if (Near.getCost() > newPathCost) {
						Near.setCost(newPathCost);
						Near.setPrevState(thisState);
						
						if (!PriQueList.contains(Near)) {
							PriQueList.add(Near);
						}
						else { // must notify the priority queue about the change of cost
							PriQueList.remove(Near);
							PriQueList.add(Near);
						}
					}
				}			
			}
		}
		return null;
	}

}