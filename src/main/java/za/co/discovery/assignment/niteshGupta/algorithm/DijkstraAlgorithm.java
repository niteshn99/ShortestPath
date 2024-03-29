package za.co.discovery.assignment.niteshGupta.algorithm;

import za.co.discovery.assignment.niteshGupta.algorithm.model.Edge;
import za.co.discovery.assignment.niteshGupta.algorithm.model.Graph;
import za.co.discovery.assignment.niteshGupta.algorithm.model.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Purpose of this class is to implement Dijkestra algorithm to find shortest path in given network graph
 */
public class DijkstraAlgorithm {
    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Double> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    /**
     * This method calculates distances for each nodes with its neighbours nodes based on edges
     * @param source
     * @param withTraffic
     */
    public void execute(Vertex source, Boolean withTraffic) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Double>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(source, 0.0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node, withTraffic);
        }
    }

    /**
     * Finds minimal distance of all nodes from provided node (from origin)
     * @param node
     * @param withTraffic
     */
    private void findMinimalDistances(Vertex node, Boolean withTraffic) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target, withTraffic)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target, withTraffic));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    /**
     * Returns total distance between origin node and destination node.
     * @param node
     * @param target
     * @param withTraffic
     * @return
     */
    private double getDistance(Vertex node, Vertex target, Boolean withTraffic) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                if(withTraffic) {
                    return edge.getDistance() + edge.getTraffic();
                } else {
                    return edge.getDistance();
                }
            }
        }
        throw new RuntimeException("Failed to calculate distance");
    }

    /**
     * Returns all connected nodes for provided node
     * @param node
     * @return
     */
    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    /**
     * Returns shortest distance node from all available neighbours
     * @param vertexes
     * @return
     */
    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    /**
     * Check whether given node has been already computed or not
     * @param vertex
     * @return
     */
    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    /**
     * Returns shortest distance of destination
     * @param destination
     * @return
     */
    private Double getShortestDistance(Vertex destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    /**
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
