package com.score.entity;

public class User {
    private String id;
    private String name;
    private String pwd;

    public User(String id,String name,String pwd){
        this.id = id;
        this.name =name;
        this.pwd = pwd;
        System.out.println("构造User父类初始化完成");
    }

    public void showMenu(){
        System.out.println("1.通用功能 0.退出");
    }

    public void showInfo(){
        System.out.println("编号: " + id + " | 姓名: " + name);
    }

    public final boolean verifyPassword(String inputPwd){
        return this.pwd.equals(inputPwd);
    }

    public String getId(){ return id;}
    public String getName(){ return name;}
}
