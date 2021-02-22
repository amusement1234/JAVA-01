package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.bean.Student;

@Configuration
public class JavaAutoConfigTest {
    @Bean(name = "student",initMethod = "init")
    public Student createStudent(){
        System.out.println("java config配置，创建Student实例。。。。。。。");
        return new Student("1111","张三");
    }
}
