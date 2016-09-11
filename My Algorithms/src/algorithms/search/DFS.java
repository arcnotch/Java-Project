package algorithms.search;


import java.util.ArrayList;
import java.util.List;


public class DFS<T> extends CommonSearcher<T> {
	
	private ArrayList<State<T>> BlockedList = new ArrayList<State<T>>();
	
	@Override
	public Solution<T> search(Searchable<T> state) {
		State<T> startState = state.getStartState();
		return DFSfunc(state, startState);
	}
	
	/**
	 * DFSfunc method search the end of Searchable object from the start of the object
	 * @param s is the Searchable object
	 * @param currentState is the current state
	 * @return Solution<T> Returns the Solution of the Searchable object
	 */
	public Solution<T> DFSfunc(Searchable<T> state, State<T> thisState){
		BlockedList.add(thisState); //Where I was
		
		if (thisState.equals(state.getEndState())) { //you got to the end
			return BackTrace(thisState);
		}
			
		List<State<T>> Neighbors = state.getAllPossibleStates(thisState); //List of possible neighbors

		for (State<T> Near : Neighbors){
			if(!BlockedList.contains(Near)){ //If I never visited this place before
				addNode();
				Near.setPrevState(thisState);
				Near.setCost(thisState.getCost() + state.getMovingCost(thisState, Near)); //Update the cost
				
				
				DFSfunc(state, Near); //recur it
			}
		}
		return null;
	}
	
	/**
	 * ArrayContains method check if a specific state is in the blocked list
	 * @param state is the state we want to check
	 * @return Boolean Returns true if state in blocked list
	 */
	public boolean ArrayContains(State<T> state){
        for(int i=0; i < BlockedList.size(); i++){
            if(BlockedList.get(i).equals(state))
                return true;
        }
        return false;
    }

}
