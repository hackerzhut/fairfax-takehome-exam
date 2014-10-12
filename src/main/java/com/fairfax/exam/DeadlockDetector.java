package com.fairfax.exam;

import java.util.List;

import com.fairfax.graph.SingleUnitResourceAllocationGraph;
import com.fairfax.graph.Vertex;
import com.fairfax.util.FlatFileReader;

public class DeadlockDetector {
	
	/**
	 * Helper method to generate Single Unit Directed Resource Allocation Wait-For Graph.
	 * @param records list of records from the input data
	 * @return DirectedGraph<Integer> directed Graph
	 */
	private static SingleUnitResourceAllocationGraph<Integer> createDirectedGraph(List<List<String>> records) {
		
		SingleUnitResourceAllocationGraph<Integer> directedGraph = new SingleUnitResourceAllocationGraph<Integer>();
		try {

			for (List<String> record : records) {

				String direction = record.get(2);
				int processId = Integer.valueOf(record.get(0)), resourceId = Integer
						.valueOf(record.get(1));

				Vertex<Integer> processVertex = directedGraph
						.findVertexById(processId);
				Vertex<Integer> resourceVertex = directedGraph
						.findVertexById(resourceId);

				if (processVertex == null)
					processVertex = new Vertex<Integer>(Integer.valueOf(record
							.get(0)));
				if (resourceVertex == null)
					resourceVertex = new Vertex<Integer>(Integer.valueOf(record
							.get(1)));

				directedGraph.addVertex(processVertex);
				directedGraph.addVertex(resourceVertex);

				if ("W".equals(direction))
					directedGraph.addEdge(processVertex, resourceVertex);
				if ("H".equals(direction))
					directedGraph.addEdge(resourceVertex, processVertex);
			}

		} catch (Exception ex) {
			throw new RuntimeException("Error parsing records: "
					+ ex.getMessage());
		}

		return directedGraph;
	}
	
	public static void main(String[] args) {
		
		// check to see if the input file exists
	    if(args.length < 1) {
	        System.out.println("Error, usage: java ClassName inputfile");
	        System.exit(1);
	    }
	    
	    FlatFileReader flatFileReader 	= new FlatFileReader(args[0]);
	    List<List<String>> records 		= flatFileReader.readFile();
	    
	    /**
	     * Since it is a wait-for directed RAG it is assumed that records have unique resource ids and process ids
	     * because in a directed graph it is possible to self-loop
	     */
	    SingleUnitResourceAllocationGraph<Integer> directedGraph = createDirectedGraph(records);
	    
	    System.out.println((directedGraph.hasCycle()) ? "BAD": "GOOD");
	    

	}

}
