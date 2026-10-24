package com.score.entity;

public class AdminUser extends User {
    // 管理员关联一个成绩实体（实验五的 Student）
    private Student relatedStudent;

    public AdminUser(String id, String name, String pwd) {
        super(id, name, pwd);
        System.out.println("[构造] AdminUser管理员初始化完成！");
    }

    // 绑定学生对象
    public void bindStudent(Student student) {
        this.relatedStudent = student;
    }

    // 查询绑定的学生成绩
    public void queryStudentScore() {
        if (relatedStudent != null) {
            System.out.print("管理员查询 -> ");
            relatedStudent.display();
        } else {
            System.out.println("管理员尚未绑定学生数据！");
        }
    }

    @Override
    public void showMenu() {
        System.out.println("1. 授权管理  2. 绑定学生  3. 查学生成绩  0. 退出");
    }
}