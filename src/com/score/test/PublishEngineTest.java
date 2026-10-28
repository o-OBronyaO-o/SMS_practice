package com.score.test;

import com.score.entity.User;
import com.score.entity.StudentUser;
import com.score.entity.TeacherUser;
import com.score.rule.*;
import com.score.notify.*;
import java.time.LocalDateTime;

public class PublishEngineTest {

    // 核心发布方法：全部用父类/接口接收，体现多态！
    public static void publish(User u, ScoreRule rule, Notifier n, double daily, double fin) {
        double total = rule.calcTotal(daily, fin);
        String level = rule.getLevel(total);

        // 挑战任务：审计日志（利用反射 getSimpleName 拿到类名）
        String logTime = LocalDateTime.now().toString();
        System.out.println("----------------------------------------");
        System.out.println("【审计日志】时间: " + logTime + " | 用户: " + u.getName()
                + " | 算法: " + rule.getClass().getSimpleName()
                + " | 渠道: " + n.getClass().getSimpleName());

        String content = "课程类型:" + rule.getCourseType() + " | 总评:" + total + " | 等级:" + level;

        // 任务3：instanceof 判断与多态
        if (u instanceof StudentUser) {
            n.send(u.getId(), content);
        } else if (u instanceof TeacherUser) {
            System.out.println("📝 [教师端日志] 已生成学生成绩单: " + content);
        }
    }

    public static void main(String[] args) {
        User stu = new StudentUser("S01", "李明", "123", "软工1班");
        User tea = new TeacherUser("T01", "张老师", "123", "教授");

        ScoreRule normal = new NormalRule();
        ScoreRule practice = new PracticeRule();
        ScoreRule pe = new PeRule();

        Notifier email = new EmailNotifier();
        Notifier sms = new SmsNotifier();

        System.out.println("===== 组合1：学生 + 普通课程 + 邮件 =====");
        publish(stu, normal, email, 85.5, 78.0);

        System.out.println("\n===== 组合2：学生 + 体育课程(达标) + 短信 =====");
        publish(stu, pe, sms, 60.0, 50.0); // 应该返回 100

        System.out.println("\n===== 组合3：教师 + 实践课程 + 短信 =====");
        publish(tea, practice, sms, 90.0, 95.0);

        System.out.println("\n===== 坑点测试：向下转型异常 =====");
        try {
            // 故意把学生账号强转为教师
            TeacherUser wrongTeacher = (TeacherUser) stu;
        } catch (ClassCastException e) {
            System.out.println("❌ 捕获到异常: 不能把学生强转为教师！(ClassCastException)");
        }

        System.out.println("\n===== 挑战任务：复合通知（内部持有多个Notifier）=====");
        Notifier composite = new Notifier() {
            @Override
            public void send(String target, String content) {
                email.send(target, content);
                sms.send(target, content);
            }
        };
        publish(stu, normal, composite, 80, 80);
    }
}