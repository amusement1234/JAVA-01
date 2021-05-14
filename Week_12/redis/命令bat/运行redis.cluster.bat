@echo off
redis-cli.exe --cluster create 127.0.0.1:26379 127.0.0.1:26380 127.0.0.1:26381 --cluster-replicas 1
@pause