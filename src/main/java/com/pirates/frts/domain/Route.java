package com.pirates.frts.domain;

/***
 * Contains required information about bus/train route
 * originId: represents the same location id for the origin
 * destinationId: represents the location id for the destination
 * distance : represents the distance between origin and destination
 * fare: represents the cost of travel
 * routeid: represents the unique route id corresponding to particular route
 * serviceType: refers to the type of service user is getting (can be either bus/metro service)
 *
 * ****/

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
