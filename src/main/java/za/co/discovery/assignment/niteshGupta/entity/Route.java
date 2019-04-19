package za.co.discovery.assignment.niteshGupta.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ROUTES")
public class Route {
    @Id
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer id;

    @Column(name = "ORIGIN")
    private String origin;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "DISTANCE")
    private Double distance;

    @Column(name = "TRAFFIC")
    private Double Traffic;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getTraffic() {
        return Traffic;
    }

    public void setTraffic(Double traffic) {
        Traffic = traffic;
    }
}
