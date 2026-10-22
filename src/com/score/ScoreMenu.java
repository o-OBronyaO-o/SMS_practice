package com.score;

import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("===== 欢迎来到 ScoreMS =====");
            System.out.println("1. 成绩录入");
            System.out.println("2. 成绩查询");
            System.out.println("3. 成绩统计");
            System.out.println("0. 退出系统");
            System.out.print("请输入您的选择：");

            int choice = scanner.nextInt();


            switch (choice) {
                case 1:
                    System.out.println("--- 成绩录入校验 ---");

                    // 改用读取字符串，然后再转换
                    System.out.print("任课教师是否已登录？(T/F): ");
                    String loginInput = scanner.next();
                    boolean isLogin = loginInput.equalsIgnoreCase("T"); // 输入T或t都算true

                    System.out.print("是否在成绩提交窗口期？(T/F): ");
                    String windowInput = scanner.next();
                    boolean inWindow = windowInput.equalsIgnoreCase("T");

                    System.out.print("请输入单科成绩(0-100): ");
                    double score = scanner.nextDouble();

                    // 下面的单层 if 短路逻辑
                    if (isLogin && inWindow && score >= 0 && score <= 100) {
                        System.out.println("✅ 录入成功，成绩为：" + score);
                    } else {
                        System.out.println("❌ 录入失败：不满足录入条件");
                    }
                    break;

                case 2:
                    System.out.println("进入成绩查询模块");
                    break;
                case 3:
                    System.out.println("--- 成绩统计模块 ---");
                    double[] scores = {85.5, 45.0, 92.0, 58.5, 76.0};
                    int failCount = 0;
                    double sum = 0;
                    double max = scores[0];

                    System.out.println("班级成绩单：");
                    for (int i = 0; i < scores.length; i++) {
                        double currentScore = scores[i];
                        sum += currentScore; // 累加总分

                        // 找出最高分
                        if (currentScore > max) {
                            max = currentScore;
                        }

                        System.out.println("第 " + (i + 1) + " 名学生成绩: " + currentScore);

                        // continue 体验：如果及格，跳过本次循环，不执行下面的补考逻辑
                        if (currentScore >= 60) {
                            continue;
                        }

                        failCount++;
                    }

                    double average = sum / scores.length;
                    System.out.printf("班级平均分: %.2f%n", average);
                    System.out.println("班级最高分: " + max);
                    System.out.println("不及格（需补考）人数: " + failCount);
                    break;

                case 0:
                    System.out.println("感谢使用，再见");
                    isRunning = false;
                    break;
                default:
                    System.out.println("输入有误，请重新选择");
            }

            System.out.println("========================================");
        }
        scanner.close();
    }

}
