package za.co.discovery.assignment.niteshGupta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.niteshGupta.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("select r from Route r where r.origin = ?1 and r.destination = ?2")
    Route findByOriginAndDestination(String origin, String destination);
}
