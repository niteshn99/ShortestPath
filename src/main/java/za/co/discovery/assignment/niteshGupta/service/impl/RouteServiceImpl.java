package za.co.discovery.assignment.niteshGupta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.niteshGupta.entity.Route;
import za.co.discovery.assignment.niteshGupta.repository.RouteRepository;
import za.co.discovery.assignment.niteshGupta.service.RouteService;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public void setRouteRepository(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> retrieveRoutes() {
        List<Route> routes = routeRepository.findAll();
        return routes;
    }

    public Route retrieveRouteByOrigionAndDestination(String origion, String destination) {
        Route route = routeRepository.findByOriginAndDestination(origion, destination);
        return route;
    }

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    public void deleteRoute(Route route) {
        routeRepository.delete(route);
    }
}
