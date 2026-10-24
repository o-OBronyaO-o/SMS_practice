package com.score.entity;

public class Student {
    // 1. 封装：字段全用 private，外界不能直接改
    private String studentId;
    private String name;
    private int age;
    private String major;
    // 增加 Course 数组字段（假设最多选 5 门课）
    private Course[] courses = new Course[6];
    private int courseCount = 0; // 记录当前已经选了几门课
    private int mandatoryCount = 0; // 必考数（最多3）
    private int firstCount = 0;     // 首选数（最多1）
    private int secondCount = 0;

    // 2. 无参构造（如果你只写了有参构造，这个默认的就会消失）
    public Student() {
        System.out.println("[无参构造] 创建了一个空白学生对象");
    }

    // 3. 全参构造
    public Student(String studentId, String name, int age, String major) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.major = major;
    }

    // 4. 两参构造（方法重载）：批量导入时只知道学号姓名
    public Student(String studentId, String name) {
        // 用 this(...) 调用全参构造，必须在第一行！
        this(studentId, name, 18, "未分配专业");
    }

    // 5. Getter 和 Setter 提供安全的访问通道
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getMajor() { return major; }

    // 重点体验这里的 setter 校验：拒绝非法年龄
    public void setAge(int age) {
        if (age < 0 || age > 150) {
            System.out.println("⚠️ 警告：年龄非法(" + age + ")，拒绝赋值，保持原值！");
            return; // 直接结束，不执行 this.age = age
        }
        this.age = age;
    }

    public void setName(String name) { this.name = name; }

    // 6. 打印信息
    public void display() {
        System.out.println("学号:" + studentId + " | 姓名:" + name + " | 年龄:" + age + " | 专业:" + major);
    }
    // 1. 重写 toString：决定直接打印对象时显示什么
    @Override
    public String toString() {
        return "学号:" + studentId + " | 姓名:" + name + " | 年龄:" + age;
    }

    // 2. 重写 equals：我们规定，只要学号相同，就是同一个学生！
    @Override
    public boolean equals(Object obj) {
        // 1. 如果两个引用指向同一个地址（就是你说的同一个指针），直接返回 true
        if (this == obj) return true;
        // 2. 如果传进来的是个 null，或者根本不是 Student 类，直接返回 false
        if (obj == null || getClass() != obj.getClass()) return false;

        // 3. 强转成 Student 进行比较
        Student other = (Student) obj;
        // 4. 比较学号内容是否相同
        return this.studentId.equals(other.studentId);
    }

    // 3. 重写 hashCode（Java 规矩：重写了 equals 就必须重写 hashCode）
    @Override
    public int hashCode() {
        return java.util.Objects.hash(studentId);
    }

    // 这个方法体现了 this 的第三种用法：作为参数传递自身
    public void register(com.score.service.ScoreService service) {
        // this 就是当前调用这个方法的对象（比如 s1 或 s2）
        service.addStudent(this);
    }

    // 添加课程的方法
    public void addCourse(Course course) {
        String type = course.getCourseType();

        // 1. 校验必考（3门）
        if ("必考".equals(type)) {
            if (mandatoryCount >= 3) {
                System.out.println("❌ 选课失败：必考科目已经满3门了！");
                return;
            }
            mandatoryCount++;
        }
        // 2. 校验首选（1门）
        else if ("首选".equals(type)) {
            if (firstCount >= 1) {
                System.out.println("❌ 选课失败：首选科目（物理/历史）只能选1门！");
                return;
            }
            firstCount++;
        }
        // 3. 校验再选（2门）
        else if ("再选".equals(type)) {
            if (secondCount >= 2) {
                System.out.println("❌ 选课失败：再选科目只能选2门！");
                return;
            }
            secondCount++;
        } else {
            System.out.println("❌ 未知的课程类别！");
            return;
        }

        // 校验通过，存入数组
        if (courseCount < courses.length) {
            courses[courseCount] = course;
            courseCount++;
            System.out.println("✅ 选课成功：" + this.name + " 选了 [" + type + "] " + course.getCourseName());
        } else {
            System.out.println("❌ 选课失败：总课程数量已达上限！");
        }
    }
}