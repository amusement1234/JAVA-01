@echo off
title kafka����������
kafka-console-producer.bat --broker-list localhost:9092 --topic kafka-test-topic
@pause