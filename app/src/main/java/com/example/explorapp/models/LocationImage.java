package com.example.explorapp.models;

public class LocationImage {
    private long imageId;
    private long locationId;
    private String imageUrl;
    private boolean isPrimary;
    private int order;
    private long uploadDate;

    public LocationImage() {
        this.isPrimary = false;
        this.order = 0;
    }

    public LocationImage(long imageId, long locationId, String imageUrl,
                              boolean isPrimary, int order, long uploadDate) {
        this.imageId = imageId;
        this.locationId = locationId;
        this.imageUrl = imageUrl;
        this.isPrimary = isPrimary;
        this.order = order;
        this.uploadDate = uploadDate;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(long uploadDate) {
        this.uploadDate = uploadDate;
    }
}
