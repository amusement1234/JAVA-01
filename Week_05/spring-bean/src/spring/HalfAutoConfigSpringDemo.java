package spring;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.config.HalfAutoConfigTest;

public class HalfAutoConfigSpringDemo {
//    @Autowired
//    HalfAutoConfigTest halfAutoConfigTest;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("scanApplicationContext.xml");
        HalfAutoConfigTest halfAutoConfigTest = (HalfAutoConfigTest)context.getBean("halfAutoConfigTest");
        halfAutoConfigTest.say();
    }

//    public void say(){
//        halfAutoConfigTest.say();
//    }
}
