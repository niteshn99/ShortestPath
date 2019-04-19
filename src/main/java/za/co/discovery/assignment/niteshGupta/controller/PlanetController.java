package za.co.discovery.assignment.niteshGupta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.niteshGupta.entity.Planet;
import za.co.discovery.assignment.niteshGupta.service.PlanetService;

import java.util.List;

/**
 * Rest controller for planet entity
 * This exposes all CRUD methods to manipulate planet entity object.
 */
@RestController
@RequestMapping("api/v1/planet")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    public void setPlanetService(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/")
    public List<Planet> getPlanet() {
        List<Planet> planets = planetService.retrievePlanets();
        return planets;
    }

    @PostMapping("/")
    public Planet editPlanet(Planet planet) {
        return planetService.savePlanet(planet);
    }

    @DeleteMapping("/")
    public String deletePlanet(Planet planet) {
        planetService.deletePlanet(planet);
        return "Successfully deleted";
    }

}
