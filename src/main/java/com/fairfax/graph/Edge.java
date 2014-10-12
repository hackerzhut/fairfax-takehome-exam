package com.fairfax.graph;

/**
 * A directed, weighted edge in a graph
 * @author Saravanakumar P
 * @version $Revision$
 * @param <T>
 */
public class Edge<T> {

  private Vertex<T> destination;

  /**
   * Create an edge between source and destination.
   * @param from the source vertex
   * @param to the destination vertex
   */
  public Edge(Vertex<T> destination) {
    this.destination = destination;
  }

  /**
   * Get the destination vertex
   * @return destination vertex
   */
  public Vertex<T> getDestination() {
    return destination;
  }

  public String toString() {
	  return destination.toString();
  }
}