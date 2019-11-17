package com.pirates.frts.domain;

/***
 * Contains required information about bus/train route
 * originId: represents the same location id for the origin
 * destinationId: represents the location id for the destinationL
 * distance : represents the distance between origin and destination
 * fare: represents the cost of travel
 * routeid: represents the unique route id corresponding to particular route
 * serviceType: refers to the type of service user is getting (can be either bus/metro service)
 *
 * ****/

public class Route {
    private String originId;
    private String destinationId;
    private float distance;
    private float fare;
    private String routeId;
    private String serviceType;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }


    public String getOriginId() {
        return originId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public float getDistance() {
        return distance;
    }

    public float getFare() {
        return fare;
    }

    public String getRouteId() {
        return routeId;
    }


}
