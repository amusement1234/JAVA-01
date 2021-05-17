@echo off
title kafka启动生产者
kafka-console-producer.bat --broker-list localhost:9092 --topic kafka-test-topic
@pause