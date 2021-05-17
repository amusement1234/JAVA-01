学习笔记


第 25 课作业实践	Week13(25-26) 
1、（必做）搭建一个3节点Kafka集群，测试功能和性能；实现spring kafka下对kafka集群 
的操作，将代码提交到github。 


Windows配置kafka2.8.0。流程如下：
1、下载kafka：
https://www.apache.org/dyn/closer.cgi?path=/kafka/2.8.0/kafka-2.8.0-src.tgz 


2、修改配置文件：D:\software\kafka_2.13-2.8.0\config\server.properties
listeners=PLAINTEXT://localhost:9092
advertised.listeners=PLAINTEXT://localhost:9092
log.dirs=D:\\software\\kafka_2.13-2.8.0\\logs\\kafka-logs


3、启动zk：
D:\software\kafka_2.13-2.8.0\bin\windows>zookeeper-server-start.bat ..\..\config\zookeeper.properties


4、启动kafka：
D:\software\kafka_2.13-2.8.0\bin\windows>kafka-server-start.bat ..\..\config\server.properties

5、相关命令

创建一个主题：
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka-test-topic

 

查看创建的主题列表
kafka-topics.bat --list --zookeeper localhost:2181

kafka-topics.bat --zookeeper localhost:2181 --describe --topic testk

启动生产者：
kafka-console-producer.bat --broker-list localhost:9092 --topic kafka-test-topic

启动消费者：

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic kafka-test-topic --from-beginning

6、kafka集群配置

在D:\software\kafka_2.13-2.8.0\config目录，添加3个文件kafka9001.properties、kafka9002.properties、kafka9003.properties


7、执行操作测试

创建带有副本的topic：3分区2副本
kafka-topics.bat --zookeeper localhost:2181 --create --topic test32 --partitions 3 --replication-factor 2

查看分区信息：kafka-topics.bat --zookeeper localhost:2181 --describe --topic test32
结果：Topic: test32   TopicId: qc152gTqTcmFrgR1xx-khA PartitionCount: 3       ReplicationFactor: 2    Configs:
        Topic: test32   Partition: 0    Leader: 2       Replicas: 2,3   Isr: 2,3
        Topic: test32   Partition: 1    Leader: 3       Replicas: 3,1   Isr: 3,1
        Topic: test32   Partition: 2    Leader: 1       Replicas: 1,2   Isr: 1,2

启动生产者：
kafka-console-producer.bat --bootstrap-server localhost:9003 --topic test32

启动消费者：
kafka-console-consumer.bat --bootstrap-server localhost:9002 --topic test32 --from-beginning


8、执行性能测试：
生产者：插入10w条数据，1k字节。限流每秒2k条数据。结果：每秒生产4w-5w
kafka-producer-perf-test.bat --topic test32 --num-records 100000 --record-size 1000 --throughput 2000 --producer-props bootstrap.servers=localhost:9002

消费者：10w条数据，1个线程，预取1MB，12s，结果：每秒消费10w
kafka-consumer-perf-test.bat --bootstrap-server localhost:9002 --topic test32 --fetch-size 1048576 --messages 100000 --threads 1





第 26 课作业实践	Week13(25-26) 
2、（必做）思考和设计自定义MQ第二个版本或第三个版本，写代码实现其中至少一
个功能点，把设计思路和实现代码，提交到github。 
