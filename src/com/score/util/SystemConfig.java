package com.score.util;

public class SystemConfig {
    // 1. 静态常量（全校统一标准）
    public static final String SCHOOL_NAME = "郑州西亚斯学院";
    public static final double PASS_LINE = 60.0;
    public static final double FULL_SCORE = 100.0;

    // 2. 静态变量（全局唯一计数器，记录一共录入了多少条成绩）
    private static int totalRecordCount = 0;

    // 3. 静态代码块：类加载时只执行一次
    static {
        System.out.println("[SystemConfig] 类加载完成，全局配置已就绪。学校：" + SCHOOL_NAME);
    }

    // 4. 静态方法：操作静态变量
    public static void increaseCount() {
        totalRecordCount++;
    }

    public static int getTotalRecordCount() {
        return totalRecordCount;
    }

    // 5. 静态方法：按及格线判断等级
    public static String judgeLevel(double score) {
        if (score >= 90) return "优";
        if (score >= 80) return "良";
        if (score >= 70) return "中";
        if (score >= PASS_LINE) return "及格";
        return "不及格";
    }
}