package spring;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.bean.Student;

public class XmlConfigSpringDemo {
//    @Autowired
//    HalfAutoConfigTest halfAutoConfigTest;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("xmlApplicationContext.xml");
        Student student = (Student)context.getBean("student123");
        student.say();
    }

//    public void say(){
//        halfAutoConfigTest.say();
//    }
}
