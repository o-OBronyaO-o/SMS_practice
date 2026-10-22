package com.score.service;

import com.score.entity.Student;
import com.score.util.SystemConfig;

public class ScoreService {

    // 公共入口，接收 Student 实体
    public void addStudent(Student student) {
        // 每录入一个学生，就调用一次全局计数器
        SystemConfig.increaseCount();

        System.out.println("录入成功！当前系统累计录入: " + SystemConfig.getTotalRecordCount() + " 人次");

        // 测试静态方法：判断成绩等级
        System.out.println("该生及格线参考等级(默认录入60分): " + SystemConfig.judgeLevel(60.0));
    }
}