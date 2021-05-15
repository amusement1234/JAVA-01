package com.example;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;


public class ActiveMQProducer<T> {

    private static final String URL = "tcp://192.168.3.78:61616";

    private static final String QUEUE_NAME = "queue-demo";

    public void sendQueue(T t) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            //1.创建ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
            //2.创建连接
            connection = connectionFactory.createConnection();
            //3.启动连接
            connection.start();
            //4.创建会话
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            //5.创建一个目标
            Destination destination = session.createQueue(QUEUE_NAME);
            // 6.创建生产者
            MessageProducer producer = session.createProducer(destination);
            String message = JSON.toJSONString(t);
            TextMessage textMessage = session.createTextMessage(message);
            System.out.println("发送Queue消息：" + message);
            producer.send(textMessage);
        } finally {
            if (null != session) {
                session.close();
            }
            if (null != connection) {
                connection.stop();
                connection.close();
            }
        }
    }
}
