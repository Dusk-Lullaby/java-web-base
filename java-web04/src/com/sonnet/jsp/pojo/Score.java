package com.sonnet.jsp.pojo;

import java.util.Date;

public class Score {

    private String name;

    private double score;

    private Date examDate = new Date();

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

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
