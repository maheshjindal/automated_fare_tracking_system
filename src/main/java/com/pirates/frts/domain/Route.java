package com.pirates.frts.domain;

public class Route {
    private String originId;
    private String destinationId;
    private double distance;
    private double fare;
    private String routeId;
    private String serviceType;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setOriginLocationId(String originLocationId) {
        this.originId = originLocationId;
    }

    public void setDestinationOriginId(String destinationOriginId) {
        this.destinationId = destinationOriginId;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }


    public String getOriginLocationId() {
        return originId;
    }

    public String getDestinationOriginId() {
        return destinationId;
    }

    public double getDistance() {
        return distance;
    }

    public double getFare() {
        return fare;
    }

    public String getRouteId() {
        return routeId;
    }


}
