package za.co.discovery.assignment.niteshGupta.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.niteshGupta.entity.Route;
import za.co.discovery.assignment.niteshGupta.service.RouteService;

import java.util.List;

/**
 * Rest controller for route entity
 * This exposes all CRUD methods to manipulate route entity object.
 */
@RestController
@RequestMapping("api/v1/routes")
public class RouteController {
    @Autowired
    private RouteService routeService;

    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/all")
    public List<Route> getRoutes() {
        List<Route> routes = routeService.retrieveRoutes();
        return routes;
    }

    @PostMapping("/")
    public Route editRoute(Route route) {
        return routeService.saveRoute(route);
    }

    @DeleteMapping("/")
    public String deleteRoute(Route route) {
        routeService.deleteRoute(route);
        return "Successfully deleted";
    }
}
