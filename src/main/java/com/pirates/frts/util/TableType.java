package com.pirates.frts.util;

public enum TableType {
    ROUTE("route"),
    USER("user"),
    CARD_INFORMATION("card_information"),
    LOCATION("location"),
    USER_PATH_TRACKER("user_path_tracker"),
    TRAVEL_HISTORY("travel_history");

    private String tableType;

    TableType(String tableType) {
        this.tableType = tableType;
    }

    public String getName() {
        return tableType;
    }
}
