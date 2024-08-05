package com.za.co._27four.assignment.luntu.entity;

import java.util.List;

public class RouteResponse {
    private List<String> path;
    private double totalDistance;
    private String source;
    private String destination;

    public RouteResponse(List<String> path, double totalDistance, String source, String destination) {
        this.path = path;
        this.totalDistance = totalDistance;
        this.source = source;
        this.destination = destination;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
