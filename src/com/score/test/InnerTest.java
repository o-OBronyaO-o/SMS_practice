package com.score.test;

import com.score.entity.StudentUser;
import com.score.inner.ScoreServiceInner;
import com.score.inner.StudentComparators;
import java.util.Arrays;
import java.util.Comparator;

public class InnerTest {
    // 注意：所有的执行逻辑必须在 main 方法里面！
    public static void main(String[] args) {
        // 1. 准备测试数据
        StudentUser s1 = new StudentUser("S01", "张三", "123", "计科1班");
        StudentUser s2 = new StudentUser("S02", "李四", "123", "计科1班");
        StudentUser s3 = new StudentUser("S03", "王五", "123", "计科1班");

        StudentUser[] students = {s1, s2, s3};

        System.out.println("\n===== 1. 匿名内部类排序（按学号降序） =====");

        // 2. 匿名内部类实现 Comparator
        Arrays.sort(students, new Comparator<StudentUser>() {
            @Override
            public int compare(StudentUser a, StudentUser b) {
                // 打印 this 的类名，验证匿名类的本质
                System.out.println("当前匿名类名: " + this.getClass().getName());
                // null 防御：id 为空的学生排在末尾，且整个元素为 null 也不会抛 NPE
                Comparator<StudentUser> byIdAsc = Comparator.comparing(StudentUser::getId,
                        Comparator.nullsLast(Comparator.naturalOrder()));
                // 降序排列：reversed 后再把 null 沉底
                return Comparator.nullsLast(byIdAsc.reversed()).compare(a, b);
            }
        });

        for (StudentUser s : students) {
            System.out.println("学生: " + s.getName() + " | 学号: " + s.getId());
        }

        // 3. 静态嵌套类实现 Comparator
        System.out.println("\n===== 2. 静态嵌套类排序（按学号升序） =====");
        Arrays.sort(students, new StudentComparators.ByIdAsc());

        for (StudentUser s : students) {
            System.out.println("学生: " + s.getName() + " | 学号: " + s.getId());
        }

        // 4. 成员内部类：回调里直接读到了外部类的 private 字段 passLine
        System.out.println("\n===== 3. 成员内部类成绩回调（低于及格线触发预警） =====");
        ScoreServiceInner service = new ScoreServiceInner();
        service.updateScore(s1, 55.0);  // 低于及格线，应触发预警
        service.updateScore(s2, 82.0);  // 高于及格线，不触发预警
        service.updateScore(null, 70.0); // 验证学生判空拦截
    }
}