package com.score;

public class DataCleaner {
    public static void main(String[] args) {
        System.out.println("===== 任务3：正则基础校验与脱敏 =====");

        // 1. 基础校验
        String studentId = "20230101";
        String phone = "13812345678";
        String email = "student@edu.cn";

        // \\d{8} 表示8个数字
        System.out.println("学号是否合法: " + studentId.matches("\\d{8}"));
        // 1开头，第二位3-9，后面9个数字
        System.out.println("手机号是否合法: " + phone.matches("1[3-9]\\d{9}"));
        // 简易邮箱校验
        System.out.println("邮箱是否合法: " + email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));

        // 2. 手机号脱敏 (中间4位替换为****)
        // 正则里的 ( ) 是分组，$1 和 $2 代表取第一组和第二组的内容
        String maskedPhone = phone.replaceAll("(1[3-9]\\d)\\d{4}(\\d{4})", "$1****$2");
        System.out.println("手机号脱敏结果: " + maskedPhone);


        System.out.println("\n===== 挑战任务：批量数据清洗 =====");
        // 造 5 条脏数据 (模拟 10 条，这里精简一下)
        String[] rawData = {
                "20230101, 张 三 ,13812345678,zhangsan@edu.cn", // 合法(注意姓名有空格)
                "2023012,李四,13800001111,lisi@edu.cn",          // 学号不够8位
                "20230103,王五,12345678901,wangwu@edu.cn",       // 手机号第二位不对
                "20230104,赵六,13912345678,zhaoliu#edu.cn",      // 邮箱缺@
                "20230105,孙七,15099998888,sunqi@edu.cn"         // 合法
        };

        int validCount = 0;
        int idError = 0, phoneError = 0, emailError = 0;

        System.out.println("--- 合法名单 ---");
        for (String record : rawData) {
            // 用逗号切分字符串
            String[] fields = record.split(",");
            String id = fields[0];
            String name = fields[1].trim(); // trim() 去除首尾空格
            String phone_data = fields[2];
            String email_data = fields[3];

            // 校验
            boolean isIdValid = id.matches("\\d{8}");
            boolean isPhoneValid = phone_data.matches("1[3-9]\\d{9}");
            boolean isEmailValid = email_data.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

            if (isIdValid && isPhoneValid && isEmailValid) {
                validCount++;
                // 手机脱敏
                String masked = phone.replaceAll("(1[3-9]\\d)\\d{4}(\\d{4})", "$1****$2");
                System.out.println("✅ " + id + " | " + name + " | " + masked + " | " + email);
            } else {
                // 统计错误原因
                if (!isIdValid) idError++;
                if (!isPhoneValid) phoneError++;
                if (!isEmailValid) emailError++;
                System.out.println("❌ 脏数据拦截: " + record);
            }
        }

        System.out.println("\n--- 清洗统计 ---");
        System.out.println("合法数据: " + validCount + " 条");
        System.out.println("学号错误: " + idError + " 条，手机号错误: " + phoneError + " 条，邮箱错误: " + emailError + " 条");
    }
}