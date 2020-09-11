package com.example.pluralsight;

public class LearningLeaders extends learners {
    private  String hours;

    public LearningLeaders(String name, String country, String badgeUrl, String hours) {
        super(name, country, badgeUrl);
        this.hours = hours;
    }

    public String getHours() {
        return hours;
    }
    public String getDetail() {
        return hours+ " learning hours, "+super.getCountry();
    }
}
