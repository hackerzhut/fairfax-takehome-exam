

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.fairfax.graph.SingleUnitResourceAllocationGraph;
import com.fairfax.graph.Vertex;

public class DeadLockDetectionTest {
	
	SingleUnitResourceAllocationGraph<Integer> directedGraph = null;
	
	@Before
	public void setUp() throws Exception {
		directedGraph = new SingleUnitResourceAllocationGraph<Integer>();
	}
	
	@Test
	public void testEmptyRAGForCycle(){
		assertEquals("Must return false", false, directedGraph.hasCycle());
	}
	
	@Test
	public void testRAGWithOnlyVertices(){
		Vertex<Integer> p1Vertex = new Vertex<Integer>(123);
		Vertex<Integer> r1Vertex = new Vertex<Integer>(1);
		directedGraph.addVertex(p1Vertex);
		directedGraph.addVertex(r1Vertex);
		assertEquals("Must return false", false, directedGraph.hasCycle());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRAGForExceptions(){
		directedGraph.addEdge(new Vertex<Integer>(123), new Vertex<Integer>(1));
	}
	
	
	@Test
	public void testSimpleRAGWithCycle() {
		
		Vertex<Integer> p1Vertex = new Vertex<Integer>(123);
		Vertex<Integer> r1Vertex = new Vertex<Integer>(1);
		Vertex<Integer> p2Vertex = new Vertex<Integer>(456);
		Vertex<Integer> r2Vertex = new Vertex<Integer>(2);
		
		directedGraph.addVertex(p1Vertex);
		directedGraph.addVertex(r1Vertex);
		directedGraph.addVertex(p2Vertex);
		directedGraph.addVertex(r2Vertex);
		
		directedGraph.addEdge(r1Vertex, p1Vertex);
		directedGraph.addEdge(r2Vertex, p2Vertex);
		directedGraph.addEdge(p1Vertex, r2Vertex);
		directedGraph.addEdge(p2Vertex, r1Vertex);
		
		assertEquals("Must return true", true, directedGraph.hasCycle());
		
	}
	
	
	@Test
	public void testSimpleRAGWithNoCycle() {
		
		Vertex<Integer> p1Vertex = new Vertex<Integer>(123);
		Vertex<Integer> r1Vertex = new Vertex<Integer>(1);
		Vertex<Integer> p2Vertex = new Vertex<Integer>(456);
		Vertex<Integer> r2Vertex = new Vertex<Integer>(2);
		Vertex<Integer> r3Vertex = new Vertex<Integer>(3);
		
		directedGraph.addVertex(p1Vertex);
		directedGraph.addVertex(r1Vertex);
		directedGraph.addVertex(p2Vertex);
		directedGraph.addVertex(r2Vertex);
		directedGraph.addVertex(r3Vertex);
		
		directedGraph.addEdge(r1Vertex, p1Vertex);
		directedGraph.addEdge(r2Vertex, p2Vertex);
		directedGraph.addEdge(p1Vertex, r2Vertex);
		directedGraph.addEdge(p2Vertex, r3Vertex);
		
		assertEquals("Must return true", false, directedGraph.hasCycle());
		
	}
	
	
	@Test
	public void testRAGWithNoCycle() {
		
		Vertex<Integer> p1Vertex = new Vertex<Integer>(123);
		Vertex<Integer> r1Vertex = new Vertex<Integer>(1);
		Vertex<Integer> p2Vertex = new Vertex<Integer>(456);
		Vertex<Integer> r2Vertex = new Vertex<Integer>(2);
		Vertex<Integer> r3Vertex = new Vertex<Integer>(3);
		
		directedGraph.addVertex(p1Vertex);
		directedGraph.addVertex(r1Vertex);
		directedGraph.addVertex(p2Vertex);
		directedGraph.addVertex(r2Vertex);
		directedGraph.addVertex(r3Vertex);
		
		directedGraph.addEdge(r1Vertex, p1Vertex);
		directedGraph.addEdge(r2Vertex, p2Vertex);
		directedGraph.addEdge(p1Vertex, r2Vertex);
		directedGraph.addEdge(p2Vertex, r3Vertex);
		
		assertEquals("Must return true", false, directedGraph.hasCycle());
		
	}
	
	@Test
	public void testComplexRAGWithCycle() {
	
		Vertex<Integer> p1Vertex = new Vertex<Integer>(123);
		Vertex<Integer> r1Vertex = new Vertex<Integer>(1);
		Vertex<Integer> p2Vertex = new Vertex<Integer>(456);
		Vertex<Integer> r2Vertex = new Vertex<Integer>(2);
		Vertex<Integer> p3Vertex = new Vertex<Integer>(789);
		Vertex<Integer> r3Vertex = new Vertex<Integer>(3);
		
		directedGraph.addVertex(p1Vertex);
		directedGraph.addVertex(r1Vertex);
		directedGraph.addVertex(p2Vertex);
		directedGraph.addVertex(r2Vertex);
		directedGraph.addVertex(p3Vertex);
		directedGraph.addVertex(r3Vertex);
		
		directedGraph.addEdge(r1Vertex, p1Vertex);
		directedGraph.addEdge(r2Vertex, p2Vertex);
		directedGraph.addEdge(r3Vertex, p3Vertex);
		
		directedGraph.addEdge(p2Vertex, r3Vertex);
		directedGraph.addEdge(p2Vertex, r1Vertex);
		directedGraph.addEdge(p3Vertex, r2Vertex);
		
		assertEquals("Must return true", true, directedGraph.hasCycle());
		
	}
	
	
	@Test
	public void testComplexRAGWithNoCycle() {
	
		Vertex<Integer> p1Vertex = new Vertex<Integer>(123);
		Vertex<Integer> r1Vertex = new Vertex<Integer>(1);
		Vertex<Integer> p2Vertex = new Vertex<Integer>(456);
		Vertex<Integer> r2Vertex = new Vertex<Integer>(2);
		Vertex<Integer> p3Vertex = new Vertex<Integer>(789);
		Vertex<Integer> r3Vertex = new Vertex<Integer>(3);
		
		directedGraph.addVertex(p1Vertex);
		directedGraph.addVertex(r1Vertex);
		directedGraph.addVertex(p2Vertex);
		directedGraph.addVertex(r2Vertex);
		directedGraph.addVertex(p3Vertex);
		directedGraph.addVertex(r3Vertex);
		
		directedGraph.addEdge(r1Vertex, p1Vertex);
		directedGraph.addEdge(r2Vertex, p2Vertex);
		directedGraph.addEdge(r3Vertex, p3Vertex);
		
		directedGraph.addEdge(p2Vertex, r3Vertex);
		directedGraph.addEdge(p2Vertex, r1Vertex);
		directedGraph.addEdge(p3Vertex, r1Vertex);
		
		assertEquals("Must return true", false, directedGraph.hasCycle());
		
	}

}
