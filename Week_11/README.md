学习笔记

第 21 课作业实践
1、（选做）按照课程内容，动手验证Hibernate和Mybatis缓存。
2、（选做）使用spring或guava cache，实现业务数据的查询缓存。
3、（挑战☆）编写代码，模拟缓存穿透，击穿，雪崩。
4、（挑战☆☆）自己动手设计一个简单的cache，实现过期策略。



第 22 课作业实践
1、（挑战☆）基于其他各类场景，设计并在示例代码中实现简单demo：
1）实现分数排名或者排行榜；
2）实现全局ID生成；
3）基于Bitmap实现id去重；
4）基于HLL实现点击量计数。
5）以redis作为数据库，模拟使用lua脚本实现前面课程的外汇交易事务。
2、（挑战☆☆）升级改造项目：
1）实现guava cache的spring cache适配；
2）替换jackson序列化为fastjson或者fst，kryo；
3）对项目进行分析和性能调优。
3、（挑战☆☆☆）以redis作为基础实现上个模块的自定义rpc的注册中心。