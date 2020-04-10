package com.example.trending;

public class Items {
    private String name;
    private String descriptions;
    private String stars;
    private String language;
    private String forks;

    public Items (){}

    public Items (String name,String descriptions){//, String stars, String language, String forks){
        this.name = name;
        this.descriptions = descriptions;
//        this.stars = stars;
//        this.language = language;
//        this.forks = forks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
