package za.co.discovery.assignment.niteshGupta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import za.co.discovery.assignment.niteshGupta.algorithm.DijkstraAlgorithm;
import za.co.discovery.assignment.niteshGupta.algorithm.model.Edge;
import za.co.discovery.assignment.niteshGupta.algorithm.model.Graph;
import za.co.discovery.assignment.niteshGupta.algorithm.model.Query;
import za.co.discovery.assignment.niteshGupta.algorithm.model.Vertex;
import za.co.discovery.assignment.niteshGupta.entity.Planet;
import za.co.discovery.assignment.niteshGupta.entity.Route;
import za.co.discovery.assignment.niteshGupta.service.PlanetService;
import za.co.discovery.assignment.niteshGupta.service.RouteService;

import java.util.*;

/**
 * Web controller for front end.
 * This exposes different routes to access front end and bind them with respective methods.
 */

@Controller
public class WebController {

    @Autowired
    private PlanetService planetService;

    public void setPlanetService(PlanetService planetService) {
        this.planetService = planetService;
    }

    @Autowired
    private RouteService routeService;

    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }

    private List<Vertex> nodes;
    private List<Edge> edges;
    private String alert = null;

    /**
     * This method binds home page get request and serves thymeleaf template for home page.
     * Serves: index.html
     * */
    @GetMapping("/")
    public String index(Model model) {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();

        List<Planet> planets = planetService.retrievePlanets();
        List<Route> routes = routeService.retrieveRoutes();

        for(Planet planet: planets) {
            Vertex location = new Vertex(planet.getId(), planet.getNode(), planet.getName());
            nodes.add(location);
        }

        for(Route route: routes) {
            Planet originPlanet = planetService.retrievePlanetByNode(route.getOrigin());
            Planet destinationPlanet = planetService.retrievePlanetByNode(route.getDestination());
            /*
            * Routes have some planet node like L' which doesn't exists.
            * Assuming it as typo mistake and moving forward by taking it as L
            * */
            if(originPlanet == null)
                originPlanet = planetService.retrievePlanetByNode(route.getOrigin().replace("'",""));
            if(destinationPlanet == null)
                destinationPlanet = planetService.retrievePlanetByNode(route.getDestination().replace("'",""));

            if(originPlanet != null && destinationPlanet != null) {
                Edge lane = new Edge(route.getId(),
                        new Vertex(originPlanet.getId(), originPlanet.getNode(), originPlanet.getName()),
                        new Vertex(destinationPlanet.getId(), destinationPlanet.getNode(), destinationPlanet.getName()),
                        route.getDistance(),
                        route.getTraffic());
                edges.add(lane);
            }

        }

        model.addAttribute("nodes", nodes);
        model.addAttribute("edges", edges);
        Query query = new Query();
        model.addAttribute("query", query);

        return "index";
    }

    /**
     * This method binds home page post request and serves thymeleaf template for home page.
     * Serves: shortestpath.html
     * */
    @PostMapping("/")
    public String shortestPath(@ModelAttribute Query query, Model model) {
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        Vertex originVertex;
        Vertex destinationVertex;
        LinkedList<Vertex> path = new LinkedList<>();
        LinkedList<Edge> shortestPath = new LinkedList<>();
        Vertex currentStop = null;
        Vertex nextStop = null;

        Planet originPlanet = planetService.retrievePlanetByNode(query.getSource());
        Planet destinationPlanet = planetService.retrievePlanetByNode(query.getDestination());
        /*
         * Routes have some planet node like L' which doesn't exists.
         * Assuming it as typo mistake and moving forward by taking it as L
         * Correction done in data itself, so below code not required any more.
         * */
        /*if(originPlanet == null)
            originPlanet = planetService.retrievePlanetByNode(query.getSource().replace("'",""));
        if(destinationPlanet == null)
            destinationPlanet = planetService.retrievePlanetByNode(query.getDestination().replace("'",""));*/

        if(originPlanet != null && destinationPlanet != null) {
            originVertex = new Vertex(originPlanet.getId(), originPlanet.getNode(), originPlanet.getName());
            destinationVertex = new Vertex(destinationPlanet.getId(), destinationPlanet.getNode(), destinationPlanet.getName());
            dijkstra.execute(originVertex, query.getWithTraffic());
            path = dijkstra.getPath(destinationVertex);
        }
        model.addAttribute("path", path);
        if(path != null) {
            Iterator<Vertex> pathIterator = path.iterator();
            while(pathIterator.hasNext()) {
                if(currentStop == null){
                    currentStop = pathIterator.next();
                }
                if(pathIterator.hasNext()){
                    nextStop = pathIterator.next();
                }
                if(currentStop != null && nextStop != null) {
                    Route route = routeService.retrieveRouteByOrigionAndDestination(currentStop.getNode(), nextStop.getNode());
                    Edge edge = new Edge(route.getId(),
                            new Vertex(currentStop.getId(), currentStop.getNode(), currentStop.getName()),
                            new Vertex(nextStop.getId(), nextStop.getNode(), nextStop.getName()),
                            route.getDistance(),
                            route.getTraffic());
                    shortestPath.add(edge);
                }
                currentStop = nextStop;
                nextStop = null;
            }
        }

        model.addAttribute("shortestpath", shortestPath);

        return "shortestpath";
    }

    /**
     * This method binds planets page get request and serves thymeleaf template for planets page.
     * Serves: planets.html
     * */
    @GetMapping("/planets")
    public String getPlanets(Model model) {
        nodes = new ArrayList<Vertex>();
        Planet planetEdit = new Planet();
        Planet planetDelete = new Planet();
        List<Planet> planets = planetService.retrievePlanets();
        for(Planet planet: planets) {
            Vertex location = new Vertex(planet.getId(), planet.getNode(), planet.getName());
            nodes.add(location);
        }
        model.addAttribute("nodes", nodes);
        model.addAttribute("planetEdit", planetEdit);
        model.addAttribute("planetDelete", planetDelete);
        model.addAttribute("alert", alert);
        alert = null;
        return "planets";
    }

    @PostMapping("/planets")
    public RedirectView savePlanet(@ModelAttribute Planet planetEdit) {
        Planet planet = planetService.savePlanet(planetEdit);
        System.out.println(planetEdit.getId());
        alert = "Planet list updated successfully";

        return new RedirectView("/planets");
    }

    @DeleteMapping("/planets")
    public RedirectView deletePlanet(@ModelAttribute Planet planetDelete) {
        planetService.deletePlanet(planetDelete);
        alert = "Planet deleted successfully.";
        return new RedirectView("/planets");
    }

    /*
    * Route Routers
    * */
    @GetMapping("/routes")
    public String getRoutes(Model model) {
        Route routeEdit = new Route();
        Route routeDelete = new Route();
        List<Route> routes = routeService.retrieveRoutes();

        model.addAttribute("routes", routes);
        model.addAttribute("routeEdit", routeEdit);
        model.addAttribute("routeDelete", routeDelete);
        model.addAttribute("alert", alert);
        alert = null;
        return "routes";
    }

    @PostMapping("/routes")
    public RedirectView saveRoute(@ModelAttribute Route routeEdit) {
        Route route = routeService.saveRoute(routeEdit);
        alert = "Route list updated successfully";

        return new RedirectView("/routes");
    }

    @DeleteMapping("/routes")
    public RedirectView deleteRoute(@ModelAttribute Route routeDelete) {
        routeService.deleteRoute(routeDelete);
        alert = "Route deleted successfully.";
        return new RedirectView("/routes");
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }


}
