package com.example.explorapp.models;

public class Review {
    private long reviewId;
    private long userId;
    private long locationId;
    private int rating;
    private String comment;
    private long visitDate;
    private long reviewDate;
    private int likes;
    private boolean verified;

    private String userFirstName;
    private String userLastName;
    private String userProfilePicture;

    public Review() {
        this.likes = 0;
        this.verified = false;
    }

    public Review(long reviewId, long userId, long locationId, int rating,
                  String comment, long visitDate, long reviewDate, int likes,
                  boolean verified) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.locationId = locationId;
        this.rating = rating;
        this.comment = comment;
        this.visitDate = visitDate;
        this.reviewDate = reviewDate;
        this.likes = likes;
        this.verified = verified;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(long visitDate) {
        this.visitDate = visitDate;
    }

    public long getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(long reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    public String getUserFullName() {
        return userFirstName + " " + userLastName;
    }
}
