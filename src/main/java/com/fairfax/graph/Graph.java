package com.fairfax.graph;

/**
 * Basic Graph Interface
 * @author Saravanakumar P
 * @version $Revision$
 * @param <T>
 */
public interface Graph<T> {
	
	  boolean addVertex(Vertex<T> v); // Adds a vertex to the graph
	  boolean addEdge(Vertex<T> from, Vertex<T> to);
	  boolean hasCycle(); // Checks and tells whether the graph has any looping

}
