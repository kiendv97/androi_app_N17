package com.example.quizapp;

public class PointDTO {
    private String testName;
    private String point;

    public PointDTO(String testName, String point) {
        this.testName = testName;
        this.point = point;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
