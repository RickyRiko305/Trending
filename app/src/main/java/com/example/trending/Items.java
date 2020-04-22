package com.example.trending;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class Items {
    @SerializedName("author")
    private String author;

    @SerializedName("name")
    private String project_name;

    @SerializedName("description")
    private String descriptions;

    @SerializedName("stars")
    private String stars;

    @SerializedName("language")
    private String language;

    @SerializedName("forks")
    private String forks;

    @SerializedName("avatar")
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

    public static Comparator<Items> nameComparator = new Comparator<Items>() {

        public int compare(Items s1, Items s2) {
            String name1 = s1.getAuthor().toUpperCase();
            String name2 = s2.getAuthor().toUpperCase();

            return name1.compareTo(name2);
        }
    };

    public static Comparator<Items> starComparator = new Comparator<Items>() {

        public int compare(Items s1, Items s2) {
            String stringStar1 = s1.getStars().toUpperCase();
            String stringStar2 = s2.getStars().toUpperCase();

            int star1 = Integer.parseInt(stringStar1);
            int star2 = Integer.parseInt(stringStar2);

            return star1-star2;

        }
    };
}
