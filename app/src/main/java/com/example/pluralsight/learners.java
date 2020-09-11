package com.example.pluralsight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class learners  {
    private  String name;
    private  String country;
    private String badgeUrl;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getbadgeUrl() {
        return badgeUrl;
    }

    public learners(String name, String country, String badgeUrl) {
        this.name = name;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }
}