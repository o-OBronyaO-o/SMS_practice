package com.score.test;

import com.score.entity.Course;
import com.score.entity.Student;

public class OopTest {
    public static void main(String[] args) {
        System.out.println("===== 1. 体验构造方法重载 =====");
        Student s1 = new Student("20230101", "李明"); // 调用两参构造
        Student s2 = new Student("20230102", "王芳", 20, "软件工程"); // 全参构造

        s1.display();
        s2.display();

        System.out.println("\n===== 2. 体验封装校验（setter） =====");
        s1.setAge(-5); // 会被 setter 拦截
        Course c1 = new Course("高等数学", 85.0, -10.0, Course.TYPE_MANDATORY); // 期末成绩会被拦截并重置为0

        System.out.println("\n===== 3. 体验引用传递的坑 =====");
        // s3 拿的是 s1 的钥匙，指向同一个内存地址
        Student s3 = s1;
        s3.setName("李四");

        System.out.print("修改 s3 的名字后，查看 s1 的名字：");
        s1.display(); // 结果发现 s1 的名字也变成了“李四”！

        System.out.println("\n===== 4. 挑战任务：重写官方方法 =====");
        // 直接打印对象
        System.out.println("打印对象: " + s1); // 默认会输出内存地址，重写后输出你的字符串

        Student s4 = new Student("20230101", "假李明");
        System.out.println("比较 s1 == s4 (内存地址): " + (s1 == s4));           // false (两个不同的对象)
        System.out.println("比较 s1.equals(s4) (学号内容): " + s1.equals(s4));   // true (学号都是20230101)
    }
}