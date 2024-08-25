package com.za.co._27four.assignment.luntu.services;

import com.za.co._27four.assignment.luntu.entity.RouteResponse;
import com.za.co._27four.assignment.luntu.entity.Routes;
import com.za.co._27four.assignment.luntu.entity.Traffic;
import com.za.co._27four.assignment.luntu.rapisotory.RoutesRepository;
import com.za.co._27four.assignment.luntu.rapisotory.TrafficRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Service
public class PathService {
    @Autowired
    private RoutesRepository routeRepository;

    @Autowired
    private TrafficRepository trafficRepository;

    /**
     * Finds the shortest path from the source to the destination considering traffic delays.
     *
     * @param source The starting planet node.
     * @param destination The destination planet node.
     * @return RouteResponse containing the shortest path and effective distance.
     */
    public RouteResponse findShortestPath(String source, String destination) {
        // Use Dijkstra's algorithm to find the shortest path

        // Priority queue to hold the planets to be processed
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(node -> node.distance));

        // Map to store the minimum distance to each planet
        Map<String, Double> distances = new HashMap<>();

        // Map to store the previous planet in the path for reconstruction
        Map<String, String> previous = new HashMap<>();

        // Initialize distances
        routeRepository.findAll().forEach(route -> {
            distances.put(route.getPlanetOrigin(), Double.MAX_VALUE);
            distances.put(route.getPlanetDestination(), Double.MAX_VALUE);
        });
        distances.put(source, 0.0);

        // Add the source to the priority queue
        pq.add(new Node(source, 0.0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
             String currentPlanet = current.planet;

            // Process all neighbors of the current planet
            List<Routes> neighbors = routeRepository.findByOrigin(currentPlanet);
            for (Routes route : neighbors) {
                String neighbor = route.getPlanetDestination();
                double newDist = distances.get(currentPlanet) + getEffectiveDistance(currentPlanet, neighbor);

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, currentPlanet);
                    pq.add(new Node(neighbor, newDist));
                }
            }
        }

        // Reconstruct the shortest path
        List<String> path = new ArrayList<>();
        for (String at = destination; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        // Return the route response
        return new RouteResponse(path, source,destination);
    }

    /**
     * Calculates the effective distance between two planets considering traffic delays.
     *
     * @param origin The origin planet node.
     * @param destination The destination planet node.
     * @return The effective distance including traffic delays.
     */
    private double getEffectiveDistance(String origin, String destination) {
        // Find the route between origin and destination
     Routes route = routeRepository.findByOriginAndDestination(origin, destination);

        // Find the traffic delay between origin and destination
        Traffic traffic = trafficRepository.findByOriginAndDestination(origin, destination);

        double distance = route.getDistance();
        if (traffic != null) {
            distance += traffic.getDelay();
        }
        return distance;
    }

    /**
     * Node class to represent a planet node in the priority queue.
     */
    private static class Node {
        String planet;
        double distance;

        Node(String planet, double distance) {
            this.planet = planet;
            this.distance = distance;
        }
    }
}
