package com.fairfax.graph;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A directed graph data structure used for deadlock detection in operating systems and 
 * relational database systems.
 * @author Saravanakumar P
 * @version $Revision$
 * @param <T>
 */

public class SingleUnitResourceAllocationGraph<T> implements Graph<T> {

	private Set<Vertex<T>> vertices;
	private Set<Edge<T>> 	edges;

	/**
	 * Constructor which creates a new directed graph with empty
	 * vertices and edges
	 */
	public SingleUnitResourceAllocationGraph() {
		vertices 	= new HashSet<Vertex<T>>();
		edges 		= new HashSet<Edge<T>>();
	}
	

	public Set<Vertex<T>> getVertices() {
		return vertices;
	}

	public Set<Edge<T>> getEdges() {
		return edges;
	}

	/**
	 * Add a vertex to the directed graph if it doesn't exists
	 * @param vertex the Vertex to add
	 * @return true if the vertex was added, false if it was already present.
	 */
	public boolean addVertex(Vertex<T> vertex) {
		return (!vertices.contains(vertex)) ? vertices.add(vertex): false;
	}
	
	/**
	 * Search the verticies with vertex id.
	 * @param vertexId the vertex id which is used to uniquely identify vertex
	 * @return the vertex with the matching vertex id
	 */
	public Vertex<T> findVertexById(int id) {
		Vertex<T> match = null;
		for (Vertex<T> vertex : vertices) {
			if (vertex.getVertexId() == id) {
				match = vertex;
				break;
			}
		}
		return match;
	}

	/**
	 * Add a directed edge to the graph.
	 * @param source source vertex
	 * @param destination destination vertex
	 * @return true if the Edge is added and false for vice-versa
	 * @throws NoSuchElementException if any of the source or destination doesn't exist
	 * from the graph.
	 */
	public boolean addEdge(Vertex<T> source, Vertex<T> destination) {

		if (!vertices.contains(source) || !vertices.contains(destination))
			throw new NoSuchElementException("Both nodes must be in the graph.");
		
		if(!source.isDestinationAlreadyExists(destination)){
			Edge<T> edge = new Edge<T>(destination);
			source.addEdge(edge);
			edges.add(edge);
			return true;
		}
		return false;
	}

	/** 
     * Returns true if graph contains a cycle or false vice-versa
     * @return true if cycle exists, else false.
     */
    public boolean hasCycle() {
    	
        final Set<Vertex<T>> visited = new HashSet<Vertex<T>>(),
        		completed = new HashSet<Vertex<T>>();

        /*
         * In addition to visited vertices we need to keep track of vertices 
         * currently in recursion stack of function for DFS traversal. 
         */
        for (Vertex<T> vertex: vertices) {
            if (depthFirstSearch(vertex, visited, completed)) 
            	return true;
        }
        return false;
    }
    
    /** 
     * Returns true if graph contains a cycle or false for vice-versa
     * @param vertex the current vertex whose outgoing edges that needs to be traversed.
     * @param visited the vertices visited so far. 
     * @param completed completed vertices during the recursion. 
     * @return true if graph contains a cycle else return false;
     */
    private boolean depthFirstSearch ( Vertex<T> vertex, Set<Vertex<T>> visited, Set<Vertex<T>> completed) {

        if (visited.contains(vertex)) {
            if (completed.contains(vertex)) return false;
            /*Vertices are in a loop if the vertex is already visited and not in completed stack*/
            return true;
        }

        visited.add(vertex);
        
        for(Edge<T> edge: vertex.getOutgoingEdges()){
        	if (depthFirstSearch(edge.getDestination(), visited, completed)) return true;
        }
        completed.add(vertex);
        return false;
    }

	

}
