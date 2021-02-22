package spring;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.bean.Student;

public class JavaConfigSpringDemo {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("spring");
        Student student = (Student)context.getBean("student");
        student.say();
    }

}
