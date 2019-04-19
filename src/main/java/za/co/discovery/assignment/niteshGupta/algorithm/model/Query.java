package za.co.discovery.assignment.niteshGupta.algorithm.model;

/**
 * Purpose of this class to facilitate query functionality on top of graph
 * This class holds values from front end page and pass it between thymeleaf and java program
 */
public class Query {
    private String source;
    private String destination;
    private Boolean withTraffic;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Boolean getWithTraffic() {
        return withTraffic;
    }

    public void setWithTraffic(Boolean withTraffic) {
        this.withTraffic = withTraffic;
    }
}
