package com.example.topic;

public class ActiveMQConsumer {

    private static final String URL = "tcp://192.168.3.78:61616";

    private static final String TOPIC_NAME = "topic-demo";

    public void consumerTopic() throws JMSException {
        //1.创建ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);

        //2.创建连接
        Connection connection = connectionFactory.createConnection();

        //3.启动连接
        connection.start();

        //4.创建会话
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        //5.创建一个目标
        Destination destination = session.createTopic(TOPIC_NAME);
        //6.创建消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //7.创建消息监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage= (TextMessage) message;
                try {
                    System.out.println("消费Topic消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
