package com.score.notify;

public class EmailNotifier implements Notifier {
    @Override
    public void send(String target, String content) {
        System.out.println("📧 [邮件发送至 " + target + "] " + content);
    }

    @Override
    public String channelName() {
        return "邮件渠道"; // 重写了 default 方法
    }
}