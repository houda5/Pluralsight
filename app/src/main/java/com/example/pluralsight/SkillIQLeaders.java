package com.example.pluralsight;

public class SkillIQLeaders extends learners {
    private String score ;

    public SkillIQLeaders(String name, String country, String badgeUrl,String score) {
        super(name, country, badgeUrl);
        this.score = score;
    }

    public String getscore() {
        return score;
    }

    // Get the score of learning and the country with same sentence
    public String getDetail() {
        return score+ " skill IQ Scores, "+super.getCountry();
    }

}

