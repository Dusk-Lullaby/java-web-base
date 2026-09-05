package com.sonnet.jsp.pojo;

public class Score {

    private String name;

    private double score;

    public Score(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public Score() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
