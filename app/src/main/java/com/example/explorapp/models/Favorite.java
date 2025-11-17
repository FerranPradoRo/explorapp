package com.example.explorapp.models;

public class Favorite {
    private long favoriteId;
    private long userId;
    private long locationId;
    private long dateAdded;
    private String personalNotes;

    private Location location;

    public Favorite() {
    }

    public Favorite(long favoriteId, long userId, long locationId,
                    long dateAdded, String personalNotes) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.locationId = locationId;
        this.dateAdded = dateAdded;
        this.personalNotes = personalNotes;
    }

    public long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getPersonalNotes() {
        return personalNotes;
    }

    public void setPersonalNotes(String personalNotes) {
        this.personalNotes = personalNotes;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
