package algorithms.search;

public interface Searcher<T> {
	
    /**
     * search method gets a Searchable object and searches the end from the start
     * @param sul is the Searchable object
     * @return Solution<T> Returns Solution object
     */
    public Solution<T> search(Searchable<T> sul);
    
    
    
    /**
     * getNumberOfNodes method returns the total number of nodes were evaluated by the search algorithm
     * @return int Returns the number of nodes evaluated in the search
     */
    public int getNumberOfNodes();
}