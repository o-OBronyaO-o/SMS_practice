package com.score.entity;

public class AuditUser extends AdminUser {
    public AuditUser(String id, String name, String pwd) {
        super(id, name, pwd);
        System.out.println("[构造] AuditUser审计员初始化完成！");
    }
}