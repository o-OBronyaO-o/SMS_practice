package com.score.rule;

public class PracticeRule extends ScoreRule {
    public PracticeRule() {
        super("实践课程");
    }
    @Override
    public double calcTotal(double daily, double fin) {
        return daily * 0.6 + fin * 0.4;
    }
}