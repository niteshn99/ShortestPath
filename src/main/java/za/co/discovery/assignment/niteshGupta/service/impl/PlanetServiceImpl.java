package za.co.discovery.assignment.niteshGupta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.niteshGupta.entity.Planet;
import za.co.discovery.assignment.niteshGupta.repository.PlanetRepository;
import za.co.discovery.assignment.niteshGupta.service.PlanetService;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    public void setPlanetRepository(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<Planet> retrievePlanets() {
        List<Planet> planets = planetRepository.findAll();
        return planets;
    }

    public Planet retrievePlanetByNode(String node) {
        Planet planet = planetRepository.findByNode(node);
        return planet;
    }

    public Planet savePlanet(Planet planet) {
        return planetRepository.save(planet);
    }

    public void deletePlanet(Planet planet) {
        planetRepository.delete(planet);
    }
}
