package algorithms.search;


import java.util.List;


public abstract class CommonSearcher<T> implements Searcher<T>{
	protected int numOfNodes;

	@Override
	public int getNumberOfNodes() {		
		return numOfNodes;
	}
	
	public void addNode(){
		numOfNodes++;
	}
	
	
	/**
	* BackTrace method is building the Solution object
	* @param endState is the final state.
	* @return Solution<T> Returns Solution object within the list of states from start to end - THE SOLUTION!
	*/
	protected Solution<T> BackTrace(State<T> endState) {
		Solution<T> Solut = new Solution<T>();
		State<T> ThisState = endState;
		List<State<T>> ArrayStates = Solut.getStates();
		
		while (ThisState != null) {
			ArrayStates.add(0, ThisState);
			ThisState = ThisState.getPrevState();
		}
		Solut.stateArr(ArrayStates);
		
		return Solut;
	}

}