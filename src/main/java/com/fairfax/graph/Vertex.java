package com.fairfax.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * A named graph vertex with optional data.
 * @author Saravanakumar P
 * @version $Revision$
 * @param <T>
 */

public class Vertex<T> {

	private int vertexId;
	private T vertexData;
	private List<Edge<T>> outgoingEdges;
  
  /**
   * Create a Vertex with vertexId
   * @param vertexId id associated with Vertex
   */
	public Vertex(int vertexId) {
		 this(vertexId, null);
	}
	
	/**
	 * Create a Vertex with vertexId and vertex data
	 * @param vertexData process/resource data associated with Vertex
	 * @param vertexId process/resource id associated with Vertex
	 */
	
	public Vertex(int vertexId, T vertexData) {
		outgoingEdges = new ArrayList<Edge<T>>();
		this.vertexId = vertexId;
		this.vertexData = vertexData;
	}

   /**
    * @return the possibly null name of the vertex
    */
	public int getVertexId() {
		return vertexId;
	}
	/**
	 * Returns the Vertex data
	 * @return vertexData
	 */
	public T getVertexData() {
		return vertexData;
	}

	/**
	 * Returns the number of outgoing edges
	 * @return List<Edge<T>> list of edges
	 */
	public List<Edge<T>> getOutgoingEdges() {
		return outgoingEdges;
	}
	
	/**
	 * Search the outgoing edges looking for an edge whose destination is same the parameter
	 * @param dest the destination
	 * @return the outgoing edge going to destination if one exists, null otherwise.
	 */
	public boolean isDestinationAlreadyExists(Vertex<T> dest) {
		for (Edge<T> e : outgoingEdges) {
			if (e.getDestination().vertexId == dest.vertexId)
				return true;
		}
		return false;
	}
	
	/**
	 * Add an edge to the vertex. If edge.from is this vertex, its an outgoing
	 * edge. If edge.to is this vertex, its an incoming edge. If neither from or
	 * to is this vertex, the edge is not added.
	 * @param e the edge to add
	 * @return true if the edge was added, false otherwise
	 */
	public boolean addEdge(Edge<T> e) {
		outgoingEdges.add(e);
		return true;
	}
	
	/**
	 * Generates hashCode based on the vertexId
	 */
	public int hashCode(){
		int seed = 37,
				hashCode = 1;
		hashCode = hashCode * seed + this.vertexId;
		return hashCode;
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object otherObject){
		
		if(!(otherObject instanceof Vertex))
			return false;
		
		Vertex<T> otherVertex = (Vertex<T>)otherObject;
		return (otherVertex.vertexId == this.vertexId);
	}

	public String toString() {
		return String.valueOf(this.vertexId);
	}

}