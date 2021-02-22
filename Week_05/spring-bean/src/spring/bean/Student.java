package spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    String code;
    String name;

    public void init() {
        System.out.println("Student 初始化 init()");
        System.out.println("名字：" + name + ",编码：" + code);
    }

    public void say() {
        System.out.println(name + "说：这世界多么美好。。。。");
    }
}
