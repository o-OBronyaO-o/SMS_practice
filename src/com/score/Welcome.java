package com.score; // 声明包名，对应 src/com/score 目录

import java.util.Scanner; // 导入 Scanner 类，用于读取键盘输入
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Welcome { // 公共类，文件名必须与类名一致

    // 程序入口，JVM 启动时自动调用
    public static void main(String[] args) {

        // ===== 顶部欢迎界面（分隔线 + 标题）=====
        System.out.println("=================================================="); // 打印分隔线
        System.out.println("          ScoreMS 学生成绩管理系统"); // 打印系统名称
        System.out.println("                 版本号：v0.1"); // 打印版本号
        System.out.println("          开发团队：ScoreMS 研发小组"); // 打印团队署名
        System.out.println("=================================================="); // 打印分隔线

        // ===== 主菜单预览 =====
        System.out.println("                  主 菜 单"); // 菜单标题
        System.out.println("          1. 成绩录入"); // 菜单项 1
        System.out.println("          2. 成绩查询"); // 菜单项 2
        System.out.println("          3. 成绩统计"); // 菜单项 3
        System.out.println("          0. 退出");     // 菜单项 0
        System.out.println("请选择功能（输入数字后按回车）："); // 操作提示

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前系统时间：" + now.format(formatter));
        System.out.println("----------------------------------------");

        // ===== 读取用户输入（姓名 + 角色）=====
        Scanner scanner = new Scanner(System.in); // 创建 Scanner 对象，System.in 代表键盘输入

        System.out.print("请输入您的姓名："); // 提示输入姓名（print 不换行，光标停在同一行）
        String name = scanner.next(); // 读取用户输入的一个字符串（以空格/回车为结束），存入 name

        System.out.print("请选择角色（1-教师，2-学生）："); // 提示选择角色
        int role = scanner.nextInt(); // 读取用户输入的一个整数，存入 role

        // ===== 根据角色拼接称呼，生成个性化欢迎语 =====
        String title; // 声明一个字符串变量，用来保存"老师/同学"等称呼
        if (role == 1) { // 如果输入的是 1，表示教师
            title = "老师"; // 教师称呼为"老师"
        } else { // 否则（包括输入 2 或其他值）按学生处理
            title = "同学"; // 学生称呼为"同学"
        }

        // 输出个性化欢迎语，字符串用 + 拼接
        System.out.println("欢迎您，" + name + title + "！当前系统版本v0.1");

        scanner.close(); // 用完后关闭 Scanner，释放资源（好习惯）
    }
}
