package za.co.discovery.assignment.niteshGupta.service;

import za.co.discovery.assignment.niteshGupta.entity.Planet;
import za.co.discovery.assignment.niteshGupta.entity.Route;

import java.util.List;

public interface RouteService {
    public List<Route> retrieveRoutes();
    public Route retrieveRouteByOrigionAndDestination(String origion, String destination);
    public Route saveRoute(Route route);
    public void deleteRoute(Route route);
}
