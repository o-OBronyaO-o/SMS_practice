package com.score.rule;

import com.score.entity.Course;

public class NormalRule extends ScoreRule {
    public NormalRule() {
        super("普通课程");
    }
    @Override
    public double calcTotal(double daily, double fin) {
        // 权重统一引用 Course（单一权威来源）
        return daily * Course.DAILY_WEIGHT + fin * Course.FINAL_WEIGHT;
    }
}