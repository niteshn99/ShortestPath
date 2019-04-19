package za.co.discovery.assignment.niteshGupta;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.discovery.assignment.niteshGupta.algorithm.model.Edge;
import za.co.discovery.assignment.niteshGupta.algorithm.model.Graph;
import za.co.discovery.assignment.niteshGupta.algorithm.model.Vertex;
import za.co.discovery.assignment.niteshGupta.algorithm.DijkstraAlgorithm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDijkstraAlgorithm {
    private List<Vertex> nodes;
    private List<Edge> edges;

    @Test
    public void testExcute() {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (Integer i = 0; i < 11; i++) {
            Vertex location = new Vertex(i, "Node_" + i, "Node_" + i);
            nodes.add(location);
        }

        addEdge(0, 0, 1, 85.0, 12.0);
        addEdge(1, 0, 2, 217.0, 12.0);
        addEdge(2, 0, 4, 173.0, 12.0);
        addEdge(3, 2, 6, 186.0, 12.0);
        addEdge(4, 2, 7, 103.0, 12.0);
        addEdge(5, 3, 7, 183.0, 12.0);
        addEdge(6, 5, 8, 250.0, 12.0);
        addEdge(7, 8, 9, 84.0, 12.0);
        addEdge(8, 7, 9, 167.0, 12.0);
        addEdge(9, 4, 9, 502.0, 12.0);
        addEdge(10, 9, 10, 40.0, 12.0);
        addEdge(11, 1, 10, 600.0, 12.0);

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0), false);
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(10));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }

    }

    private void addEdge(Integer laneId, int sourceLocNo, int destLocNo,
                         Double duration, Double traffic) {
        Edge edge = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration, traffic);
        edges.add(edge);
    }
}
