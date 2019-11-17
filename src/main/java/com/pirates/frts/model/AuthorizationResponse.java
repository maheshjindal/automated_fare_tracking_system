package com.pirates.frts.model;

public class AuthorizationResponse {
    private boolean isAllowed;
    private float routeFare;
    private float cardLimit;

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    public float getRouteFare() {
        return routeFare;
    }

    public void setRouteFare(float routeFare) {
        this.routeFare = routeFare;
    }

    public float getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(float cardLimit) {
        this.cardLimit = cardLimit;
    }
}
