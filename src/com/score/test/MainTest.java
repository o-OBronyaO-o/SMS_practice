package com.score.test;

import com.score.entity.Student;
import com.score.service.ScoreService;

public class MainTest {
    public static void main(String[] args) {
        // 注意：这里会触发 SystemConfig 的静态代码块打印
        System.out.println("===== 系统启动 =====");

        ScoreService service = new ScoreService();

        // 跨包创建实体
        Student s1 = new Student("20230101", "李明");
        Student s2 = new Student("20230102", "王芳");

        // 把学生录入系统（注意这里跨包了，且调用了 static 计数器）
        s1.register(service);
        s2.register(service);

        System.out.println("\n===== 验证跨包 static 计数 =====");
        // 在另一个类里直接读取静态变量
        System.out.println("最终全校统计录入数: " + com.score.util.SystemConfig.getTotalRecordCount());

        // 1. 你可以试试在这里写 s1.studentId = "123";
        // 会报错！因为 studentId 是 private 的，这就是跨包的“最小可见原则”。
    }
}