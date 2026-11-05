package com.score.inner;

import com.score.entity.StudentUser;
import java.util.Comparator;

public class StudentComparators {
    // 静态嵌套类：加了 static，它不持有外部类 StudentComparators 的引用
    public static class ByIdAsc implements Comparator<StudentUser> {
        @Override
        public int compare(StudentUser a, StudentUser b) {
            // null 防御：元素为 null、或元素里的 id 为 null 时都不抛 NPE，统一沉到数组末尾
            Comparator<StudentUser> byId = Comparator.comparing(StudentUser::getId,
                    Comparator.nullsLast(Comparator.naturalOrder()));
            return Comparator.nullsLast(byId).compare(a, b);
        }
    }
}