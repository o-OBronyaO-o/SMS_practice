package com.score.rule;

import com.score.util.SystemConfig;

public abstract class ScoreRule {
    private String courseType;

    public ScoreRule(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseType() {
        return courseType;
    }

    // 核心：抽象方法，子类必须实现！
    public abstract double calcTotal(double daily, double fin);

    // 模板方法：非抽象，子类直接继承复用（调用实验六的全局配置）
    public String getLevel(double total) {
        return SystemConfig.judgeLevel(total);
    }
}