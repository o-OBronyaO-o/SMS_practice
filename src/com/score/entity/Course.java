package com.score.entity;

public class Course {
    private String courseName;
    private double dailyScore;
    private double finalScore;

    public Course(String courseName, double dailyScore, double finalScore) {
        this.courseName = courseName;
        setDailyScore(dailyScore);
        setFinalScore(finalScore);
    }

    public String getCourseName() { return courseName; }

    public double getDailyScore() { return dailyScore; }
    public void setDailyScore(double dailyScore) {
        if (dailyScore < 0 || dailyScore > 100) {
            System.out.println("⚠️ 警告：平时成绩非法，强制重置为 0！");
            this.dailyScore = 0;
        } else {
            this.dailyScore = dailyScore;
        }
    }

    public double getFinalScore() { return finalScore; }
    public void setFinalScore(double finalScore) {
        if (finalScore < 0 || finalScore > 100) {
            System.out.println("⚠️ 警告：期末成绩非法，强制重置为 0！");
            this.finalScore = 0;
        } else {
            this.finalScore = finalScore;
        }
    }

    // 计算总评成绩
    public double getTotal() {
        return dailyScore * 0.3 + finalScore * 0.7;
    }
}