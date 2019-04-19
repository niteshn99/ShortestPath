package za.co.discovery.assignment.niteshGupta.algorithm.model;

/**
 * Purpose of this class is to hold nodes connectivity information
 */
public class Edge {
    private final Integer id;
    private final Vertex source;
    private final Vertex destination;
    private final double distance;
    private final double traffic;

    /**
     * Constructor takes id, source, destination, distance, and traffic as input parameter
     * @param id
     * @param source
     * @param destination
     * @param distance
     * @param traffic
     */
    public Edge(Integer id, Vertex source, Vertex destination, double distance, double traffic) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.traffic = traffic;
    }

    /**
     * Return id of edge
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns destination of edge
     * @return destination
     */
    public Vertex getDestination() {
        return destination;
    }

    /**
     * Return source of edge
     * @return source
     */
    public Vertex getSource() {
        return source;
    }

    /**
     * Return distance of edge
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Return traffic of edge
     * @return traffic
     */
    public double getTraffic() {
        return traffic;
    }

    /**
     * Retuns source and destination as string of edge
     * @return
     */
    @Override
    public String toString() {
        return source + " " + destination;
    }
}
