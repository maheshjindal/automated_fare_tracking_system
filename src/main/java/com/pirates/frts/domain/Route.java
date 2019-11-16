package com.pirates.frts.domain;

public class Route {
    private String originLocationId;
    private String destinationOriginId;
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
        this.originLocationId = originLocationId;
    }

    public void setDestinationOriginId(String destinationOriginId) {
        this.destinationOriginId = destinationOriginId;
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
        return originLocationId;
    }

    public String getDestinationOriginId() {
        return destinationOriginId;
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
