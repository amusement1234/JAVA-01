学习笔记

第 23 课作业实践	Week12(23-24) 
1、（必做）配置redis的主从复制，sentinel高可用，Cluster集群。


redis的主从复制 
环境：Windows10，redis版本3.0.504，redis的密码为123 
redis配置文件： 
redis.windows.6379.conf 
redis.windows.6380.conf 
redis.windows.6381.conf 

流程： 
1、复制为redis.windows.conf，改名为redis.windows.6379.conf 
2、在D:\software\Redis-x64-3.0.504目录，复制redis.windows.conf，改名为redis.windows.6380.conf， 
3、修改内容： 
port 6380 
slaveof 127.0.0.1 6379 
masterauth 123 //6379的密码 
 
4、在D:\software\Redis-x64-3.0.504目录，复制redis.windows.conf，改名为redis.windows.6381.conf， 
5、修改内容： 
port 6381 
slaveof 127.0.0.1 6379 
masterauth 123 //6379的密码 

6、分别启动： 
redis-server.exe redis.windows.conf 
redis-server.exe redis.windows.6380.conf 
redis-server.exe redis.windows.6380.conf 


7、测试主从复制： 

D:\software\Redis-x64-3.0.504>redis-cli.exe -h 127.0.0.1 -p 6379 -a 123 
127.0.0.1:6379> info replication 
# Replication 
role:master 
connected_slaves:2 
slave0:ip=127.0.0.1,port=6380,state=online,offset=72073,lag=1 
slave1:ip=127.0.0.1,port=6381,state=online,offset=72073,lag=1 
master_repl_offset:72073 
repl_backlog_active:1 
repl_backlog_size:1048576 
repl_backlog_first_byte_offset:2 
repl_backlog_histlen:72072 


D:\software\Redis-x64-3.0.504>redis-cli.exe -h 127.0.0.1 -p 6380 -a 123 
127.0.0.1:6380> info replication 
# Replication 
role:slave 
master_host:127.0.0.1 
master_port:6379 
master_link_status:up 
master_last_io_seconds_ago:3 
master_sync_in_progress:0 
slave_repl_offset:74216 
slave_priority:100 
slave_read_only:1 
connected_slaves:0 
master_repl_offset:0 
repl_backlog_active:0 
repl_backlog_size:1048576 
repl_backlog_first_byte_offset:0 
repl_backlog_histlen:0 



sentinel高可用：主从切换 

redis配置文件：redis.windows.sentinel16379.conf 

添加内容： 
port 16379 
bind 127.0.0.1 
sentinel monitor mymaster 127.0.0.1 6379 1 
sentinel down-after-milliseconds mymaster 6000 
sentinel failover-timeout mymaster 18000  
sentinel auth-pass mymaster 123  
sentinel config-epoch mymaster 11  

启动sentinel：redis-server.exe redis.windows.sentinel16379.conf --sentinel 
运行：redis-cli.exe -h 127.0.0.1 -p 16379 
查看： 
127.0.0.1:16379> info Sentinel 
# Sentinel 
sentinel_masters:1 
sentinel_tilt:0 
sentinel_running_scripts:0 
sentinel_scripts_queue_length:0 
master0:name=mymaster,status=ok,address=127.0.0.1:6379,slaves=2,sentinels=1 

关闭6379之后，发现其中6380设为master了，重新打开6379之后，6379变为slave。对应的配置文件也相应修改了。 



Cluster集群： 

1、redis配置文件： 
redis.windows.cluster.26379.conf 
redis.windows.cluster.26380.conf 
redis.windows.cluster.26381.conf 


2、分别添加内容： 
port 26379 
cluster-enabled yes 
cluster-config-file nodes.26379.conf 
cluster-node-timeout 15000 
appendonly yes 


port 26380 
cluster-enabled yes 
cluster-config-file nodes.26380.conf 
cluster-node-timeout 15000 
appendonly yes 


port 26381 
cluster-enabled yes 
cluster-config-file nodes.26381.conf 
cluster-node-timeout 15000 
appendonly yes 


3、下载ruby 
redis-trib.rb是一款由Redis官方提供的集群管理工具，能够大量减少集群搭建的时间。 
redis-trib是一个 Ruby 程序，所以要安装Ruby，下载rubyinstaller-3.0.1-1-x64.exe 
https://rubyinstaller.org/downloads/ 

4、下载rubygems 
https://rubygems.org/pages/download，解压，备用 

下载redis-3.0.5.gem，备用 
https://rubygems.org/gems/redis/versions/3.0.5 

5、下载windows版本的redis3.0.504，找到Source code (tar.gz)，解压后，找到文件src/redis-trib.rb备用 
https://github.com/MicrosoftArchive/redis/releases

6、运行 
D:\software\rubygems-3.2.17>ruby setup.rb 
D:\software>gem install redis 
D:\software>gem install --local D:\software\redis-3.0.5.gem 
D:\software>ruby redis-trib.rb create --replicas 0 127.0.0.1:26379 127.0.0.1:26380 127.0.0.1:26381 127.0.0.1:26382 

备注：redis-trib.rb的版本必须与redis版本一致，不然会报错 

执行结果： 
>>> Creating cluster 
Connecting to node 127.0.0.1:26379: OK 
Connecting to node 127.0.0.1:26380: OK 
Connecting to node 127.0.0.1:26381: OK 
>>> Performing hash slots allocation on 3 nodes... 
Using 3 masters: 
127.0.0.1:26379 
127.0.0.1:26380 
127.0.0.1:26381 
M: e069f0e61d4799a5f0571b71b1f59196dfa156c9 127.0.0.1:26379 
   slots:0-5460 (5461 slots) master 
M: b080ea0904fb9b63cf3674f595577819c10bbde3 127.0.0.1:26380 
   slots:5461-10922 (5462 slots) master 
M: 8aab86ee835418ac0b710140d12e7d01ecbc451d 127.0.0.1:26381 
   slots:10923-16383 (5461 slots) master 
Can I set the above configuration? (type 'yes' to accept): yes 
>>> Nodes configuration updated 
>>> Assign a different config epoch to each node 
>>> Sending CLUSTER MEET messages to join the cluster 
Waiting for the cluster to join.. 
>>> Performing Cluster Check (using node 127.0.0.1:26379) 
M: e069f0e61d4799a5f0571b71b1f59196dfa156c9 127.0.0.1:26379 
   slots:0-5460 (5461 slots) master 
M: b080ea0904fb9b63cf3674f595577819c10bbde3 127.0.0.1:26380 
   slots:5461-10922 (5462 slots) master 
M: 8aab86ee835418ac0b710140d12e7d01ecbc451d 127.0.0.1:26381 
   slots:10923-16383 (5461 slots) master 
[OK] All nodes agree about slots configuration. 
>>> Check for open slots... 
>>> Check slots coverage... 
[OK] All 16384 slots covered. 
 

7、输入cluster info查看结果 
127.0.0.1:26379> cluster info 
cluster_state:ok 
cluster_slots_assigned:16384 
cluster_slots_ok:16384 
cluster_slots_pfail:0 
cluster_slots_fail:0 
cluster_known_nodes:3 
cluster_size:3 
cluster_current_epoch:3 
cluster_my_epoch:1 
cluster_stats_messages_sent:3942 
cluster_stats_messages_received:3942 
127.0.0.1:26379> set a a 
(error) MOVED 15495 127.0.0.1:26381 
127.0.0.1:26379> 

8、扩容，添加26382节点 
D:\software>redis-trib.rb add-node 127.0.0.1:26382 127.0.0.1:26379 


提示报错了： 
[ERR] Node 127.0.0.1:26382 is not empty. Either the node already knows other nodes (check with CLUSTER NODES) or contains some key in database 0. 

cmd窗口全部关闭，添加删除appendonly.aof、nodes.26382.conf重试就成功了。 

结果如下： 
d:\software>redis-trib.rb add-node 127.0.0.1:26382 127.0.0.1:26379 
>>> Adding node 127.0.0.1:26382 to cluster 127.0.0.1:26379 
Connecting to node 127.0.0.1:26379: OK 
Connecting to node 127.0.0.1:26381: OK 
Connecting to node 127.0.0.1:26380: OK 
>>> Performing Cluster Check (using node 127.0.0.1:26379) 
M: e069f0e61d4799a5f0571b71b1f59196dfa156c9 127.0.0.1:26379 
   slots:0-5460 (5461 slots) master 
   0 additional replica(s) 
M: 8aab86ee835418ac0b710140d12e7d01ecbc451d 127.0.0.1:26381 
   slots:10923-16383 (5461 slots) master 
   0 additional replica(s) 
M: b080ea0904fb9b63cf3674f595577819c10bbde3 127.0.0.1:26380 
   slots:5461-10922 (5462 slots) master 
   0 additional replica(s) 
[OK] All nodes agree about slots configuration. 
>>> Check for open slots... 
>>> Check slots coverage...  
[OK] All 16384 slots covered. 
Connecting to node 127.0.0.1:26382: OK 
>>> Send CLUSTER MEET to node 127.0.0.1:26382 to make it join the cluster. 
[OK] New node added correctly. 



检查一下： 
d:\software>redis-trib.rb check 127.0.0.1:26379 
Connecting to node 127.0.0.1:26379: OK 
Connecting to node 127.0.0.1:26382: OK 
Connecting to node 127.0.0.1:26381: OK 
Connecting to node 127.0.0.1:26380: OK 
>>> Performing Cluster Check (using node 127.0.0.1:26379) 
M: e069f0e61d4799a5f0571b71b1f59196dfa156c9 127.0.0.1:26379 
   slots:0-5460 (5461 slots) master 
   0 additional replica(s) 
M: 9a906288f1ec775da49d785aac418a68db5437bc 127.0.0.1:26382 
   slots: (0 slots) master 
   0 additional replica(s) 
M: 8aab86ee835418ac0b710140d12e7d01ecbc451d 127.0.0.1:26381 
   slots:10923-16383 (5461 slots) master 
   0 additional replica(s) 
M: b080ea0904fb9b63cf3674f595577819c10bbde3 127.0.0.1:26380 
   slots:5461-10922 (5462 slots) master 
   0 additional replica(s) 
[OK] All nodes agree about slots configuration. 
>>> Check for open slots... 
>>> Check slots coverage... 
[OK] All 16384 slots covered. 

修复node：redis-trib.rb fix 127.0.0.1:26382 

查看节点状态： 
127.0.0.1:26381>cluster nodes 
b080ea0904fb9b63cf3674f595577819c10bbde3 127.0.0.1:26380 master - 0 1620988264421 2 connected 10440-10922 
8aab86ee835418ac0b710140d12e7d01ecbc451d 127.0.0.1:26381 myself,master - 0 0 3 connected 10923-16383 
9a906288f1ec775da49d785aac418a68db5437bc 127.0.0.1:26382 master - 0 1620988265421 4 connected 5461-10439 
e069f0e61d4799a5f0571b71b1f59196dfa156c9 127.0.0.1:26379 master - 0 1620988266421 1 connected 0-5460 



第 24 课作业实践	Week12(23-24) 
1、（必做）搭建ActiveMQ服务，基于JMS，写代码分别实现对于queue和topic的消息 
生产和消费，代码提交到github。 

搭建ActiveMQ服务： 
下载ActiveMQ：http://activemq.apache.org/download.html 

下载解压后，双击打开D:\software\apache-activemq-5.16.2\bin\win64\activemq.bat 

打开http://127.0.0.1:8161/admin，用户名密码：admin admin 

