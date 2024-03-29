package com.pirates.frts.domain;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

/****
 * An entity used to track the path while a particular user is travelling
 * sourceLocationId: represents the same location id used to uniquely identify the source
 * destinationLocationId : represents the same location id to uniquely identify the destination
 * inTime : represents the time user tagged in the rfid
 * outTime: represents the time user tagged out the rfid
 * userid: is the same user id used to uniquely identify the user
 * ***/

@JsonAutoDetect
public class UserPathTracker {
    private String sourceLocationId;
    private String destinationLocationId;
    private String inTime;
    private String outTime;
    private String serviceType;
    private String rfId;
    private String userId;

    public String getSourceLocationId() {
        return sourceLocationId;
    }

    public void setSourceLocationId(String sourceLocationId) {
        this.sourceLocationId = sourceLocationId;
    }

    public String getDestinationLocationId() {
        return destinationLocationId;
    }

    public void setDestinationLocationId(String destinationLocationId) {
        this.destinationLocationId = destinationLocationId;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getRfId() {
        return rfId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
