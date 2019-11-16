package com.pirates.frts.util;

import com.pirates.frts.domain.TravelHistory;

public class TravelHistoryMapper {
    String travelId;
    TravelHistory travelHistory;

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public TravelHistory getTravelHistory() {
        return travelHistory;
    }

    public void setTravelHistory(TravelHistory travelHistory) {
        this.travelHistory = travelHistory;
    }
}
