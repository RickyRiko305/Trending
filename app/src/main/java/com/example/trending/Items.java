package com.example.trending;

public class Items {
    private String author;
    private String project_name;
    private String descriptions;
    private String stars;
    private String language;
    private String forks;
    private String profile_image;
    private boolean expanded;

    public Items (){}

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public Items (String author, String project_name, String descriptions, String stars, String language, String forks, String profile_image){
        this.author = author;
        this.project_name = project_name;
        this.descriptions = descriptions;
        this.stars = stars;
        this.language = language;
        this.forks = forks;
        this.profile_image = profile_image;
        this.expanded = false;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
