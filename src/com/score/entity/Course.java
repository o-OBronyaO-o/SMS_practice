package com.score.entity;

public class Course {
    // 课程类型常量（集中管理，避免魔法字符串散落）
    public static final String TYPE_MANDATORY = "必考";
    public static final String TYPE_FIRST = "首选";
    public static final String TYPE_SECOND = "再选";

    // 普通课程总评权重（单一权威来源，Course 与 NormalRule 均引用此处）
    public static final double DAILY_WEIGHT = 0.3;
    public static final double FINAL_WEIGHT = 0.7;

    private String courseName;
    private double dailyScore;
    private double finalScore;
    private String courseType;

    public Course(String courseName, double dailyScore, double finalScore, String courseType) {
        this.courseName = courseName;
        this.courseType = courseType;
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

    // 计算总评成绩（权重引用本类常量）
    public double getTotal() {
        return dailyScore * DAILY_WEIGHT + finalScore * FINAL_WEIGHT;
    }

    public String getCourseType() { return courseType; }
}