package za.co.discovery.assignment.niteshGupta.algorithm.model;

/**
 * Purpose of this class is to provide a structure to hold node information
 */
public class Vertex {
    final private Integer id;
    final private String node;
    final private String name;

    public Vertex(Integer id, String node, String name) {
        this.id = id;
        this.node = node;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNode() {
        return node;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
