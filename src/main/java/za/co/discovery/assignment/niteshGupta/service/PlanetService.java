package za.co.discovery.assignment.niteshGupta.service;

import za.co.discovery.assignment.niteshGupta.entity.Planet;

import java.util.List;

public interface PlanetService {
    public List<Planet> retrievePlanets();
    public Planet retrievePlanetByNode(String node);
    public Planet savePlanet(Planet planet);
    public void deletePlanet(Planet planet);
}
