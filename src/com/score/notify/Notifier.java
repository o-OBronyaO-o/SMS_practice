package com.score.notify;

public interface Notifier {
    // 抽象方法，默认就是 public abstract
    void send(String target, String content);

    // default 方法，子类不写也能直接调用
    default String channelName() {
        return "通用渠道";
    }
}