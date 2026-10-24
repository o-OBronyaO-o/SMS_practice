package com.score.entity;

public class TeacherUser extends User {
    private String title; // 职称

    public TeacherUser(String id, String name, String pwd, String title) {
        super(id, name, pwd);
        this.title = title;
        System.out.println("[构造] TeacherUser子类初始化完成！");
    }

    @Override
    public void showMenu() {
        System.out.println("1. 成绩录入  2. 成绩修改  3. 成绩查询  0. 退出");
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println(" | 职称: " + title + " | 角色: 教师");
    }
}