package com.example.explorapp.models;

public class UserPreference {
    private long preferenceId;
    private long userId;
    private long categoryId;
    private int interestLevel;

    private String categoryName;
    private String categoryIcon;
    private String categoryColor;

    public UserPreference() {
    }

    public UserPreference(long preferenceId, long userId, long categoryId,
                              int interestLevel) {
        this.preferenceId = preferenceId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.interestLevel = interestLevel;
    }

    public long getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(long preferenceId) {
        this.preferenceId = preferenceId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public int getInterestLevel() {
        return interestLevel;
    }

    public void setInterestLevel(int interestLevel) {
        this.interestLevel = interestLevel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getCategoryColor() {
        return categoryColor;
    }

    public void setCategoryColor(String categoryColor) {
        this.categoryColor = categoryColor;
    }
}
