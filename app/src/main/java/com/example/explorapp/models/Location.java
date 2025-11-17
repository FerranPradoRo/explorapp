package com.example.explorapp.models;

public class Location {
    private long locationId;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private String address;
    private String city;
    private Long categoryId;
    private String categoryName; // Campo extra del JOIN
    private double averageCost;
    private String openingTime;
    private String closingTime;
    private String phone;
    private String website;
    private float popularityScore;
    private long creationDate;
    private boolean active;

    public Location() {
        this.active = true;
    }

    public Location(long locationId, String name, String description,
                        double latitude, double longitude, String address, String city,
                        Long categoryId, double averageCost, String openingTime,
                        String closingTime, String phone, String website,
                        float popularityScore, long creationDate, boolean active) {
        this.locationId = locationId;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.categoryId = categoryId;
        this.averageCost = averageCost;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.phone = phone;
        this.website = website;
        this.popularityScore = popularityScore;
        this.creationDate = creationDate;
        this.active = active;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(double averageCost) {
        this.averageCost = averageCost;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public float getPopularityScore() {
        return popularityScore;
    }

    public void setPopularityScore(float popularityScore) {
        this.popularityScore = popularityScore;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSchedule() {
        if (openingTime != null && closingTime != null) {
            return openingTime + " - " + closingTime;
        }
        return "No disponible";
    }
}
