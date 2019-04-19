package za.co.discovery.assignment.niteshGupta.algorithm.model;

import java.util.List;

/**
 * Purpose of this class is to facilated graph formation based on provided nodes and edges
 */
public class Graph {
    private final List<Vertex> vertexes;
    private final List<Edge> edges;

    /**
     * Constructor method to initialize private variables
     * @param vertexes
     * @param edges
     */
    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    /**
     * Returns list of all nodes in graph
     * @return vertexes
     */
    public List<Vertex> getVertexes() {
        return vertexes;
    }

    /**
     * Returns list of all edges in graph
     * @return edges
     */
    public List<Edge> getEdges() {
        return edges;
    }



}
