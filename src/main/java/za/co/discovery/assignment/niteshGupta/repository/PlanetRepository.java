package za.co.discovery.assignment.niteshGupta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.niteshGupta.entity.Planet;

import java.util.Optional;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
    @Query("select p from Planet p where p.node = ?1")
    Planet findByNode(String node);

}
