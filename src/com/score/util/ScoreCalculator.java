package com.score.util;
import com.score.entity.Course;

// 加了 final，这个类就绝育了，谁也别想继承它！
public final class ScoreCalculator {
    // 内部直接调用实验五的 Course.getTotal()
    public static double total(Course c) {
        return c.getTotal();
    }
}