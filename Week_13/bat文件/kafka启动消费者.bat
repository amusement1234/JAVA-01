@echo off
title kafka启动消费者
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic kafka-test-topic --from-beginning
@pause