package com.score.rule;

import com.score.util.SystemConfig;

public class PeRule extends ScoreRule {
    public PeRule() {
        super("体育课程");
    }
    @Override
    public double calcTotal(double daily, double fin) {
        // 平时与期末任一成绩达及格线（SystemConfig.PASS_LINE）视为达标，返回100，否则返回较差成绩
        if (daily >= SystemConfig.PASS_LINE || fin >= SystemConfig.PASS_LINE) {
            return 100.0;
        }
        return Math.min(daily, fin);
    }
}