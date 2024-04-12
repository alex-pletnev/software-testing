package itmo.st.task2;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void testGraphInitialization() {
        Graph graph = new Graph(5);
        assertNotNull(graph);
        assertNotNull(graph.getPq());
        assertEquals(5, graph.getDist().length);
        for (var adj : graph.getAdj()) {
            assertTrue(adj.isEmpty());
        }
    }

    @Test
    void testAddEdge() {
        Graph graph = new Graph(2);
        graph.addEdge(0, 1, 10);
        assertEquals(1, graph.getAdj().get(0).size());
        assertEquals(1, graph.getAdj().get(1).size());
        assertEquals(10, graph.getAdj().get(0).get(0).cost);
        assertEquals(0, graph.getAdj().get(1).get(0).vertex);
    }

    @Test
    void testDijkstraNoEdges() {
        Graph graph = new Graph(2);
        graph.dijkstra(0);
        assertEquals(0, graph.getDist()[0]);
        assertEquals(Integer.MAX_VALUE, graph.getDist()[1]);
    }

    @Test
    void testDijkstraWithEdges() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.dijkstra(0);
        assertEquals(0, graph.getDist()[0]);
        assertEquals(1, graph.getDist()[1]);
        assertEquals(3, graph.getDist()[2]);
    }

    @Test
    void testDijkstraComplexGraph() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 1);
        graph.addEdge(0, 3, 9);
        graph.addEdge(0, 2, 4);
        graph.dijkstra(0);
        assertEquals(0, graph.getDist()[0]);
        assertEquals(5, graph.getDist()[1]);
        assertEquals(4, graph.getDist()[2]);
        assertEquals(5, graph.getDist()[3]);
    }
}
