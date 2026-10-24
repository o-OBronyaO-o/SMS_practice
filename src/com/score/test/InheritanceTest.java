package com.score.test;

import com.score.entity.*;

public class InheritanceTest {
    public static void main(String[] args) {
        System.out.println("===== 1. 验证构造链（父先子后） =====");
        // 注意看控制台打印顺序
        StudentUser stu = new StudentUser("S01", "李明", "123456", "软工1班");

        System.out.println("\n===== 2. 验证方法重写与信息展示 =====");
        stu.showInfo();

        System.out.println("\n===== 3. 体验动态绑定（多态） =====");
        // 父类引用指向子类对象
        User u = new TeacherUser("T01", "张老师", "admin", "教授");
        u.showMenu(); // 究竟执行父类的还是子类的？看控制台结果！

        System.out.println("\n===== 4. 验证 final 口令校验 =====");
        // 尝试 stu.verifyPassword 没问题；但如果你去子类重写它，会直接报错！
        System.out.println("口令正确吗？ " + stu.verifyPassword("123456"));

        System.out.println("\n===== 5. 挑战任务：三级构造链 =====");
        // 这里会依次打印：User -> AdminUser -> AuditUser 的日志
        AuditUser audit = new AuditUser("A01", "王审计", "888");

        System.out.println("\n===== 6. 账号体系与成绩实体合流 =====");
        // 造一个实验五的成绩实体
        Student scoreStudent = new Student("20230101", "赵六", 20, "计算机");
        Course c1 = new Course("高数", 85, 90,"must");
        scoreStudent.addCourse(c1); // (假设实验五加了 addCourse 方法)

        // 管理员绑定该学生
        AdminUser admin = new AdminUser("AD01", "教务主任", "root");
        admin.bindStudent(scoreStudent);
        admin.queryStudentScore();
    }
}