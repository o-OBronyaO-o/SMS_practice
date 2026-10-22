package com.score;

import java.util.Arrays;

public class ScoreAdvanced {
    public static void main(String[] args) {
        System.out.println("===== 任务1：一维数组统计 =====");
        // 1. 声明长度为5的数组并直接初始化（模拟录入）
        double[] scores = {85.0, 45.5, 92.0, 58.0, 76.5};

        // 2. 排序并输出（Arrays.sort 会原地修改数组）
        Arrays.sort(scores);
        System.out.println("排序后的成绩: " + Arrays.toString(scores));

        // 3. 计算总分、平均分、最高分、最低分
        double sum = 0;
        double max = scores[0]; // 排序后最后一个就是最大
        double min = scores[0]; // 排序后第一个就是最小

        for (double s : scores) {
            sum += s;
        }
        double avg = sum / scores.length;

        System.out.printf("平均分: %.2f, 最高分: %.2f, 最低分: %.2f%n", avg, scores[scores.length - 1], scores[0]);


        System.out.println("\n===== 任务2：String 比较与学号解析 =====");
        // 1. 经典的 == 与 equals 坑
        String sysPwd = "score123";             // 存在字符串常量池
        String inputPwd = new String("score123"); // new 出来的在堆内存

        System.out.println("inputPwd == sysPwd 的结果: " + (inputPwd == sysPwd));         // false! 比的是内存地址
        System.out.println("inputPwd.equals(sysPwd) 的结果: " + inputPwd.equals(sysPwd)); // true! 比的是内容

        // 2. 学号解析：提取年份和学院代码
        String studentId = "2023-0101";
        // 用 indexOf 找到分隔符的位置
        int dashIndex = studentId.indexOf('-');
        if (dashIndex != -1) { // 如果找到了 '-'
            String year = studentId.substring(0, dashIndex);
            String collegeCode = studentId.substring(dashIndex + 1);
            System.out.println("解析结果 -> 入学年份: " + year + ", 学院代码: " + collegeCode);
        }
    }
}