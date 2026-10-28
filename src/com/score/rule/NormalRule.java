package com.score.rule;

public class NormalRule extends ScoreRule {
    public NormalRule() {
        super("普通课程");
    }
    @Override
    public double calcTotal(double daily, double fin) {
        return daily * 0.3 + fin * 0.7;
    }
}