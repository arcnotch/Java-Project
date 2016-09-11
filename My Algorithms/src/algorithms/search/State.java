package algorithms.search;

public class State<T> implements Comparable<State<T>> {
	private State<T> prevState;
	private double cost;
	private T val;
	private String Key;
	
	//C'tors by String,State<T> and object T
	public State(String k) {
		this.Key = k;
	}
	
	public State(State<T> s){
		this.prevState = s.getPrevState();
		this.Key = s.getKey();
		this.cost = s.getCost();
		this.val = s.getVal();
	}
	
	public State(T object){
		this.Key = "";
		this.cost = 0;
		this.val = object;
		this.prevState = null;
	}
	
	public double getCost() {
		return cost;
	}
	public T getVal() {
		return val;
	}
	public State<T> getPrevState() {
		return prevState;
	}
	public String getKey() {
		return Key;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	public void setVal(T s) {
		this.val = s;
	}
	public void setPrevState(State<T> prev) {
		this.prevState = prev;
	}
	public void setKey(String k) {
		this.Key = k;
	}
	
	@Override
	public String toString() {
		return val.toString(); //toString as T implemented it
	}
	
	@Override
	public int compareTo(State<T> s) {
		return (int)(this.getCost() - s.getCost());	
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object objec) {
		if(objec.getClass() == this.getClass()) 
		{
			return val.equals(((State<T>)objec).val); //By value
		}
		return false;
	}

}