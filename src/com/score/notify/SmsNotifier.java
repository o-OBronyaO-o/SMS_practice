package com.score.notify;

public class SmsNotifier implements Notifier {
    @Override
    public void send(String target, String content) {
        System.out.println("📱 [短信发送至 " + target + "] " + content);
    }
    // 注意：这里没有重写 channelName，它会直接沿用父接口的 "通用渠道"
}