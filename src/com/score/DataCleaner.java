package com.score;

public class DataCleaner {
    // 正则集中管理（避免同一模式多处重复）
    private static final String ID_REGEX = "\\d{8}";
    private static final String PHONE_REGEX = "1[3-9]\\d{9}";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PHONE_MASK_REGEX = "(1[3-9]\\d)\\d{4}(\\d{4})";
    private static final String PHONE_MASK_REPLACEMENT = "$1****$2";

    public static void main(String[] args) {
        System.out.println("===== 任务3：正则基础校验与脱敏 =====");

        // 1. 基础校验
        String studentId = "20230101";
        String phone = "13812345678";
        String email = "student@edu.cn";

        // \\d{8} 表示8个数字
        System.out.println("学号是否合法: " + studentId.matches(ID_REGEX));
        // 1开头，第二位3-9，后面9个数字
        System.out.println("手机号是否合法: " + phone.matches(PHONE_REGEX));
        // 简易邮箱校验
        System.out.println("邮箱是否合法: " + email.matches(EMAIL_REGEX));

        // 2. 手机号脱敏 (中间4位替换为****)
        // 正则里的 ( ) 是分组，$1 和 $2 代表取第一组和第二组的内容
        String maskedPhone = phone.replaceAll(PHONE_MASK_REGEX, PHONE_MASK_REPLACEMENT);
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
            // 字段数不足（需访问到下标3），跳过该行避免 ArrayIndexOutOfBoundsException
            if (fields.length < 4) {
                System.out.println("⚠️ 字段数不足，跳过: " + record);
                continue;
            }
            String id = fields[0];
            String name = fields[1].trim(); // trim() 去除首尾空格
            String phone_data = fields[2];
            String email_data = fields[3];

            // 校验
            boolean isIdValid = id.matches(ID_REGEX);
            boolean isPhoneValid = phone_data.matches(PHONE_REGEX);
            boolean isEmailValid = email_data.matches(EMAIL_REGEX);

            if (isIdValid && isPhoneValid && isEmailValid) {
                validCount++;
                // 手机脱敏
                String masked = phone_data.replaceAll(PHONE_MASK_REGEX, PHONE_MASK_REPLACEMENT);
                System.out.println("✅ " + id + " | " + name + " | " + masked + " | " + email_data);
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