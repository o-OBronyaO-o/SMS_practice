package com.score.rule;

public class PeRule extends ScoreRule {
    public PeRule() {
        super("体育课程");
    }
    @Override
    public double calcTotal(double daily, double fin) {
        // 平时与期末任一成绩>=60视为达标，返回100，否则返回较差成绩
        if (daily >= 60 || fin >= 60) {
            return 100.0;
        }
        return Math.min(daily, fin);
    }
}