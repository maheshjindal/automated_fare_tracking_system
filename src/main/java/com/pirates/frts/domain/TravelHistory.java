package com.pirates.frts.domain;

/***
 * Maps the particular user with a particular route
 * routeId: represents the same unique id used to define a particular route
 * userId: represents the same unique id used to define a particular user
 * travelId: represents the unique id to uniquely identify particular travel entry
 * date: represents the date of travel
 *
 * **/
public class TravelHistory {

    private String routeId;
    private String userId;
    private String travelId;
    private String date;

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}