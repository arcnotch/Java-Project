package algorithms.search;


import java.util.ArrayList;
import java.util.List;


public class Solution<T> {
	private List<State<T>> stateArr = new ArrayList<State<T>>();
	
	
	
	public List<State<T>> getStates() {
		return stateArr;
	}

	/**
	 * stateArr method sets the state list
	 * @param states is the list of states 
	 */
	public void stateArr(List<State<T>> newSArray) {
		this.stateArr = newSArray;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (State<T> stt : stateArr) {
			builder.append(stt.toString()).append(" ");//toString<as state implemented it> and adds space " "
		}
		return builder.toString();
	}
	
}