package com.pirates.frts.domain;
/*

* readerId represents the unique id corresponding to the unique machine installed at each station
* locationName represents the name of bus/metro station
* locationId represents the unique id corresponding to a particular location
**
* **/
public class Location {
    private String readerId;
    private String locationName;
    private String locationId;

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

}
