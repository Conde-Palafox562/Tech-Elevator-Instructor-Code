package com.techelevator.model;

public class Truck {

    private int truckId;
    private boolean active;
    private String acceptedPayment;
    private int cuisineId;
    private String restaurantLink;
    private String phone;
    private String currentLocation;

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAcceptedPayment() {
        return acceptedPayment;
    }

    public void setAcceptedPayment(String acceptedPayment) {
        this.acceptedPayment = acceptedPayment;
    }

    public int getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(int cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getRestaurantLink() {
        return restaurantLink;
    }

    public void setRestaurantLink(String restaurantLink) {
        this.restaurantLink = restaurantLink;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
}
