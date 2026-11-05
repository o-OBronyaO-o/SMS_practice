package com.score.inner;

import com.score.entity.StudentUser;
import com.score.util.SystemConfig;

public class ScoreServiceInner {
    // 外部类的私有字段：及格线复用全局配置，避免项目里出现两套标准
    // 注意：PASS_LINE 是 static final 的【编译期常量】，javac 会直接把 60.0 内联进这里，
    // 所以读取它不会触发 SystemConfig 的静态代码块（控制台看不到“类加载完成”那行）。
    // 这与 MainTest 里调用 increaseCount()、judgeLevel() 等静态方法会触发类初始化的情形不同（JLS 12.4.1）。
    private double passLine = SystemConfig.PASS_LINE;

    // 【服务级模拟值】它只记录“上一次被修改的分数”，不按学生维度存储：
    // 首次修改时 oldV 恒为 0.0，连续给不同学生改分时留痕会串味。
    // 真实业务应从持久层（数据库）查该学生的旧成绩。
    private double currentScore;

    // 【核心】成员内部类：没有 static 修饰
    public class ScoreChangeListener {
        // 内部类的方法可以【直接】访问外部类的 private 字段 passLine！
        public void onChange(StudentUser stu, double oldV, double newV) {
            System.out.println("📝 [留痕] 学生: " + stu.getName() + "，修改人: 教务处");
            System.out.println("   成绩由 " + oldV + " 变更为 " + newV);

            if (newV < passLine) {
                System.out.println("🚨 [预警] 成绩低于及格线！需通知班主任！");
            }
        }
    }

    // 模拟修改成绩的业务方法
    public void updateScore(StudentUser stu, double newV) {
        if (stu == null) {
            System.out.println("⚠️ [拦截] 学生对象为空，本次修改已忽略");
            return;
        }

        double oldV = this.currentScore;
        this.currentScore = newV;

        // 实例化内部类，并触发回调
        ScoreChangeListener listener = new ScoreChangeListener();
        listener.onChange(stu, oldV, newV);
    }
}