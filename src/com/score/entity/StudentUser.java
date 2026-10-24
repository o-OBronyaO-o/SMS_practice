package com.score.entity;

public class StudentUser extends User {
    private String className; // 班级

    // 子类构造必须调用 super(...)
    public StudentUser(String id, String name, String pwd, String className) {
        super(id, name, pwd); // 必须在第一行！
        this.className = className;
        System.out.println("[构造] StudentUser子类初始化完成！");
    }

    // 方法重写：加上 @Override 注解，让编译器帮你检查语法
    @Override
    public void showMenu() {
        System.out.println("1. 查询本人成绩  2. 修改口令  0. 退出");
    }

    // 方法重写：利用 super 复用父类逻辑，再补充子类信息
    @Override
    public void showInfo() {
        super.showInfo(); // 先打印父类的编号和姓名
        System.out.println(" | 班级: " + className + " | 角色: 学生");
    }
}