学习笔记

---

# 1.🍃毕业总结：（一学期学习心得总结），写在 README 文件里即可。
# 2.🍃毕业作业：分别用 100 个字以上的一段话，加上一幅图 (架构图或脑图)，总结自己对下列技术的关键点思考和经验认识

---

感谢秦老师和助教以及班主任和同学们这段时间的陪伴，收货了蛮多的。第一遍感觉挺难的，有时候视频直播看不懂还要看第二遍。有落后的时候，也有追赶的时候。最近一周终于把作业进度追上了。前面有些忘记了，等到结业之后再整体刷一波。

---

## 1)JVM

### 1.1 字节码

Java用字节码技术实现了跨平台的功能，.java文件通过编辑器生成.class字节码文件。这样java虚拟机就能运行了。
JVM 是一台基于栈的计算机器。每个线程都有一个独属于自己的线程栈（JVM Stack），用于存储 栈帧（Frame）。

### 1.2 类加载器

通过类加载器加载字节码文件，生成对象实例。

有3种类加载器：
1. 启动类加载器（BootstrapClassLoader） 
2. 扩展类加载器（ExtClassLoader） 
3. 应用类加载器（AppClassLoader）

### 1.3 内存模型

计算机运行时有很多个进程，java进程属于其中之一。java进程中有栈、堆、非堆、jvm自身的内容。

栈中有很多个线程栈，每个线程栈中有很多个栈帧，栈帧里有局部变量表、返回值、操作数栈、class指针。
堆中有年轻代和老年代，年轻代有新生代eden、存活区s0和s1。
非堆中有元数据区、css、code cache，元数据区有方法区，方法区有常量池。


### 1.4 GC

因为系统的内存是有限的，所以需要有清理回收，就有了GC。

GC有3种算法：清除算法、复制算法、整理算法

常用的GC有：串行GC、并行GC、CMS GC、G1 GC。

串行GC：对年轻代使用的是复制算法，对老年代使用的是整理算法。都会触发暂停stw。 

并行GC：和串行GC一样，对年轻代使用的是复制算法，对老年代使用的是整理算法。都会触发暂停stw。主要目的是增加了吞吐量。 

CMS GC：对年轻代使用的是复制算法，对老年代使用的是清理算法。没有明显的暂停。主要目的是低延迟。 

G1 GC：用的是复制算法，不分为年轻代和老年代，二刷划分多个小块堆。每一块有的是年轻代、有的是老年代。每次只处理一部分内存块，垃圾最多的小块最先清理。适用于堆内存较大，GC时间可控的场景。 

### 1.5 JVM调优问题

1、高分配速率 
引起：导致很大的minor GC开销，影响吞吐量。
解决：增加年轻代大小（蓄水池）。可以减少GC频率。

2、过早提升（意思是从年轻代提升到老年代的速度过快）
引起：老年代填充的越快，major gc频率越高，影响吞吐量。
解决：让年轻代存储暂存的数据。可以增加年轻代大小、减少批处理的数量。


---

## 2)NIO 

### 2.1 IO模型

阻塞式IO（BIO）：用循环一直调用accept方法，有请求时，建立通信。后面的请求只能等待当前的操作完成之后。

非阻塞IO（NIO)：第一步不阻塞，第二部是阻塞的。

IO多路复用：单独用一个线程监控，当有数据到了，通知用户进程。与非阻塞IO类似，只是多了一个线程。

信号驱动IO：与BIO和NIO不一样在于数据准备阶段不会阻塞用户线程。当收到信号后立马调用recvfrom()去收数据。

异步IO：用户进程发出系统调用立即返回，内核等待数据完成。将数据拷贝到用户进程缓冲区，之后发送信号告诉进程IO执行完成了。

更加通俗化的描述如下：

同步阻塞IO：去饭馆吃饭，去了之后都在门口等着不能离开 。
同步非阻塞IO：去了饭馆吃饭，就可以去干别的了，时不时回来看看。
同步阻塞IO复用：去了饭馆门口，饭馆有个服务员，服务员有位了就让你进去。
同步非阻塞信号驱动：去了饭馆吃饭，去了之后领个号，就可以去干别的了。到你了，直接微信给你弹消息让你来。
异步IO模型：你去签个到，就去干别的了，做好了，直接找你去送过来。


### 2.2 Netty

Netty是一个网络开发框架，是异步、事件驱动、基于Nio的。

有高吞吐、低延迟、低开销、零拷贝、可扩容的特点。

高性能：高并发、高吞吐、低延迟、容量。


---

## 3) 并发编程

### 3.1多线程

多线程可以并行计算，提升cpu使用率。

线程有3种状态：就绪、暂停、运行。

Thread：start()创建新线程、join/sleep/wait/notify。其中sleep释放CPU、wait释放对象锁、join内部调用wait。

Runnable接口：run()本线程调用。


### 3.2 线程安全

多线程竞争同一资源时，会产生线程安全的问题。需要用锁控制。

并发性质：
1、原子性：对变量的读取和赋值都是原子性的操作。不可中断。
2、可见性：当变量被volatile（不能保证原子性）修饰时，会保证修改的值马上更新到主存。也可以通过synchrond和lock保证可见性，在释放锁之前刷到主存中。
3、有序性：volatile可以保证线程间的有序性。happens-before原则。

相关的关键字：
1、synchronized：使用对象头标记字、用到了偏向锁。
2、volatile：每次读取都从主内存刷数据。适用于单线程写、多线程读。建议能不用，就不用。可以用Atomic替代。
3、final：不允许继承、不允许修改。最新的值会保证其他线程可见。推荐最大化使用。


### 3.3 线程池

线程池：
1、Excutor：sumit/execute。其中submit有返回值、execute没有返回值。
2、ExcutorService：execute/shutdown。
3、ThreadFactory
4、Excutors：工具类，创建线程的
5、Callable：call，有返回值。
6、Future：cancel/get


线程池数量的设置：
1、CPU密集型：线程池大小设为N或N+1。
2、IO密集型：线程池大小设置为2N或2N+2


### 3.4 锁

1、Lock：lock/tryLock/unLock
2、读写锁，写锁是独占的。
3、基础接口Condition：await/signal
4、LockSupport：锁当前线程的。park/unpark，暂停与恢复。


锁的使用：
1、降低锁的范围。
2、细分锁的粒度。


### 3.5 并发原子类

AtomicLong：无锁技术。通过volatile保证读写都可见、使用cas指令，作为乐观锁实现，通过自旋重试保证写入。压力大是会导致自旋重试变多，资源消耗大。

LongAddr：通过分段思想改进。拆分然后求和。采用的是多路归并思想。例如：快排、G1 GC、ConcurrentHashMap。

LongAdder的改进思路：
1、AtomicInteger和AtomicLong里的value是所有线程竞争读写的热点数据；
2、将单个value拆分成跟线程一样多的数组Cell[]。
3、每个线程写自己的Cell[i]++，最后对数组求和。

### 3.6 并发工具类

AQS：队列同步器，抽象了竞争的资源与线程队列。
有独占和恭喜2种共享方式。

Semaphore：信号量。
准入数量 N，N =1 则等价于独占锁。
使用场景：同一时间控制并发线程数。类似十字路口的控制。

CountdownLatch：await、countDown、getCount。不可重用。基于AQS实现。
阻塞主线程，N个子线程满足条件时主线程继续。
使用场景：Master 线程等待 Worker 线程把任务执行完。
示例: 等所有人干完手上的活，一起去吃饭。

CyclicBarrier：await、reset。
场景: 任务执行到一定阶段, 等待其他任务对齐，阻塞N个线程时所有线程被唤醒继续。
示例: 等待所有人都到达，再一起开吃。

Future、FutureTask、CompletableFuture。

### 3.7 线程安全类型

List：ArrayList、LinkedList、Vector、Stack。
Set：LinkedSet、HashSet、TreeSet。
Queue->Deque->LinkedList。

Map：HashMap、LinkedHashMap、TreeMap。
Dictionary->HashTable->Properties。


ArrayList：基于数组，默认大小10，扩容乘以1.5。有读写冲突。

LinkedList。使用双向指针，有读写冲突。

List线程安全：
1、可以加锁，加上synchronized。
2、Arrays.asList，只能修改。
3、Collections.unmodifiableList，只可增加删除、不许修改。

由于List并发读写不安全，使用副本机制改进，引入了CopyOnWriteArrayList。

CopyOnWriteArrayList：写加锁，写在copy副本上。
1、插入时，在新副本操作。
2、删除时，如果删除是末尾元素，直接使用N-1的元素创建一个新数组。如果是其他元素，创建新数组，将剩余元素复制到新数组。
3、读取不加锁。
4、使用迭代器，直接拿快照。（淘宝商品每次下单，生成商品信息的快照）

HashMap：用空间换时间，冲突不大时，查找效率高。
初始容量16，扩容乘以2，负载因子0.75，当链表长度到8或者数组长度到64时，使用红黑树。
有安全问题：写冲突、读写可能死循环、keys无顺序。

LinkedHashMap：继承HashMap，Entry添加双向链表，用途：保证有序。有安全问题，同HashMap。

由于HashMap和LinkedHashMap并发读写不安全，使用分段锁或cas，引入了ConcurrentHashMap。

ConcurrentHashMap：分段锁，默认16个Segment，可以降低锁的粒度。可以理解为Segment[]分库，HashEntry[]分表。
Segment初始化后，不可扩容。

Java8，摒弃了分段锁，直接使用大数组。


### 3.8 并发编程

ThreadLocal：每个线程一个副本，
Stream：xxx.stream().parallel()。

加锁需要考虑：粒度、性能、重入、公平、自旋锁、场景。

线程间协作与通信：
1、线程间共享：static/实例变量、lock、synchronized。
2、线程间协作：Thread的join()、wait/notify/notifyAll、Future/Callable、CountdownLatch、CyclicBarrier。


---

## 4)Spring 和 ORM 等框架

### 4.1 Spring

6大模块：
1、Core：Bean、Context、AOP。
2、Testing：Mock、TestContext。
3、DataAccess：Tx、JDBC、ORM。
4、Spring MVC/WebFlux：web。
5、Integration：remoting、JMS、WS。
6、Languages：Kotlin/Groovy。

Spring AOP：加一个中间层代理（字节码增强）来实现所有对象
的托管。

接口类型：默认使用JdkProxy
非接口类型：默认使用CGlib。

字节码增强与反射的区别：类似于基因操作技术与拍CT。

Spring Bean：Bean加载过程：创建对象、属性赋值、初始化、注销接口注册

Spring XML：自动化配置


### 4.2 Spring Boot

两大核心原理：
1、自动化配置：简化配置核心。基于Configuration，EnableXX，Condition
2、spring-boot-starter：脚手架核心。整合各种第三方类库，协同工具

约定大于配置，主要是方便。比如：默认的配置文件：application.properties 或 application.yml 文件

@SpringBootApplication
SpringBoot 应用标注在某个类上说明这个类是 SpringBoot 的主配置类，SpringBoot 就会运行
这个类的 main 方法来启动 SpringBoot 项目。


### 4.3 JDBC与数据库连接池

JDBC 定义了数据库交互接口：DriverManager、Connection、Statement、ResultSet。后来又加了DataSource--Pool


数据库连接池：
C3P0、
DBCP--Apache CommonPool、
Druid、
Hikari、


### 4.4 ORM：Hibernate/MyBatis

Hibernate：它将 POJO 与数据库表建立映射关系，是一个全自动的 orm 框架，hibernate 可以自动生成 SQL 语句，自动执行，

Hibernate 里可以使用 HQL、Criteria、Native SQL三种方式操作数据库。


MyBatis：它支持定制化 SQL、存储过程以及高级映射。

MyBatis 可以使用简单的 XML或注解来配置和映射原生信息，将接口和 Java 的 POJOs(Plain Old JavaObjects,普通的 Java 对象)映射成数据库中的记录。

Mybatis 优点：原生 SQL（XML 语法），直观，对 DBA 友好。
Hibernate 优点：简单场景不用写 SQL（HQL、Cretiria、SQL）。
Mybatis 缺点：繁琐，可以用 MyBatis-generator、MyBatis-Plus 之类的插件。
Hibernate 缺点：对 DBA 不友好


JPA：java持久化api，是一套基于ORM的规范。核心EntityManager


### 4.5 Java8 Lambda & Stream

Lambda表达式是一个匿名函数，

Predicate<T> 有参数、条件判断
Function<T, R> 有参数、有返回值
Consumer<T> 无返回值
Supplier<T> 无参数、有返回值


Stream（流）是一个来自数据源的元素队列并支持聚合操作。可以进行聚合操作。Stream有2个基本特征：Pipelining、内部迭代。


### 4.6 Lombok & Guava

Lombok：基于字节码增强，编译期处理。
注解：
@Setter @Getter
@Data
@XXXConstructor
@Builder
@ToString
@Slf4j


Guava：有集合（multisets, multimaps, tables, bidirectional maps）、
缓存（支持多种过期策略）、
并发（完成后触发回调Future）、
字符串处理(分割、连接、填充等)、
IO、验证的方法。

Guava的好处：标准化、高效可靠、优化。


### 4.7 设计原则 & 设计模式

设计原则：
SOLID：单一、开发封闭、里氏替换、接口分离、依赖倒置。

最小知识原则，kiss，高内聚低耦合。

设计模式：GOF23种
1、创建型：工厂方法、抽象工厂、建造者、原型、单例。
2、结构型：适配器、桥接、组合、装饰、外观、享元、代理。
3、行为型：解释器、模板方法、责任链、命令、迭代器、中介、备忘录、观察者、状态、策略、访问者。


模式的3个层次：解决方案层面（架构模式）、组件层面（框架模式）、代码层面（设计模式）。

反模式：死用模式。

单元测试：单元测试：集成测试、自动化端到端测试。


---

## 5)MySQL 数据库和 SQL


### 5.1 关系数据库

DQL：数据查询。selete、where、order by、group by、having。
DML：数据操作。insert、update、delete。
DDL：数据定义。create、alter、drop。


### 5.2 Mysql

Mysql：
4.0支持InnoDB、事务
5.7：近期使用最多（多主、MGR、json）
8.0 最新版。

Mysql存储：

binlog 二进制日志文件：记录主数据库服务器的 DDL 和 DML 操作

Mysql执行流程：查询缓存、解析器、预处理器、查询优化器、查询执行引擎、存储引擎。

执行引擎区别：
innodb：最多65TB、支持事务、支持索引、是行锁、支持外键。
myisam：256TB、支持索引、表锁、支持数据压缩。


mysql的sql执行顺序：from、on、join、where、group by、having和聚合函数、selete、order by、limit。

mysql索引原理：数据按页分块，当一个数据用到，周围的也容易用到。


innodb：使用B+书实现聚集索引。单表不超过2000w。


### 5.3 Mysql事务与锁

ACID。

表级锁：
1、共享意向锁：IS
2、排它锁：IX
3、Insert意向锁。 Insert 操作设置的间隙锁

冲突：XX、XIX、XS、XIS、IXS。
兼容：IXIX、IXIS、SS、ISS、ISIS。


行级锁(InnoDB)
1、记录锁(Record): 始终锁定索引记录，注意隐藏的聚簇索引;
2、间隙锁(Gap):
3、临键锁(Next-Key): 记录锁+间隙锁的组合; 可“锁定”表中不存在记录
4、谓词锁(Predicat): 空间索引


死锁:阻塞与互相等待、增删改和锁定读、死锁检测与自动回滚、锁粒度与程序设计。


4种隔离级别：
1、读未提交RU，很少使用，不能保证一致性，有脏读。非锁方式执行。
2、读已提交RC。每次查询都会读取最新的快照，半一致性读，有不可重复度、幻读。锁定索引记录。而不是记录之间的间隙。
3、可重复读RR。InnoDB的默认隔离级别，使用事务第一次读取时创建的快照，有多版本技术。使用唯一索引查询条件时，只锁定记录，不锁定间隙。其他查询条件，会锁定索引范围，通过间隙锁或临键锁来阻止其他会话在这个
范围中插入值。可能出现幻读，需要加锁。
4、串行化。串行执行，资源消耗最大。


undo log：撤销日志。保证事务的原子性。
一条insert对应一条delete的undo log，
一个update对应相反update的undo log。
保存位置：在system tablespace（mysql5.7）、undo tablespaces(mysql8.0)

redo log：重做日志。保证事务的持久性，防止事务提交后数据未刷新到磁盘就掉电或崩溃。是一个环，有check point、write pos。先写日志、再写磁盘。

日志文件：ib_logfile0/ib_logfile1。


MVCC：多版本并发控制。使InnoDB支持一致性读：RC、RR。让查询不被阻塞，每行数据都存在一个版本号，在数据库领域使用不普遍。

MVCC的实现机制：隐藏列、事务链表、Read view、回滚段（通过undo log)。


sql优化：
1、大批量写入。
2、数据更新。
3、模糊查询。
4、连接查询。驱动表的选择，避免笛卡尔积。
5、索引失效。减少or可以用union。放弃所有条件组合都走索引。
6、查询sql的设计。

常见场景：
1、实现主键ID。自增、时间戳、雪花算法。
2、高效分页。重写count、反序、带id、非精确分页。
3、乐观锁和悲观锁。


---

## 6) 分库分表

### 6.1 从单机到集群

单机遇到的问题：容量有限、难以扩容。读写压力大、可用性不足。

读写压力》主从复制。
高可用性》MHA、MGR。
容量问题》分库分表。
一致性问题》XA、柔性事务。


### 6.2 mysql主从复制 & 读写分离 & 高可用

核心是
1、主库写 binlog
2、从库 relay log

主从复制的局限性
1、主从延迟问题
2、应用侧需要配合读写分离框架
3、不解决高可用问题


mysql读写分离


mysql高可用：高可用意味着，更少的不可服务时间。一般用SLA/SLO衡量。


常见的一些策略：
1、多个实例不在一个主机/机架上
2、跨机房和可用区部署
3、两地三中心容灾高可用方案


主从切换：
1、手动切换
2、lvs+keepalived
3、MHA，可以30s内实现主从切换
4、MGR：主复制。
5、mysql cluster
6、orchestrator


### 6.3 数据库垂直拆分

单数据库的问题有：无法执行DDL、无法备份、影响性能和稳定性。

垂直拆分的优点：
1、单库（单表）变小，便于管理和维护
2、对性能和容量有提升作用
3、改造后，系统和数据复杂度降低
4、可以作为微服务改造的基础

缺点：
1、库变多，管理变复杂
2、对业务系统有较强的侵入性
3、改造过程复杂，容易出故障
4、拆分到一定程度就无法继续拆分

垂直拆分的一般做法
1、梳理清楚拆分范围和影响范围
2、检查评估和重新影响到的服务
3、准备新的数据库集群复制数据
4、修改系统配置并发布新版上线


### 6.4 数据库水平拆分

分为：分库、分表、分库分表三类


分库还是分表，如何选择：
1、一般情况下，如果数据本身的读写压力较大，磁盘 IO 已经成为瓶颈，那么分库比分表要好。
2、相反的情况下，可以尽量多考虑分表，降低单表的数据量，从而减少单表操作的时间，同时也能在单个数据库上使用并行操作多个表来增加处理能力。

分库分表有什么优点：
1、解决容量问题
2、比垂直拆分对系统影响小
3、部分提升性能和稳定性

缺点：
1、集群规模大，管理复杂
2、复杂 SQL 支持问题（业务侵入性、性能）
3、数据迁移问题
4、一致性问题


Java 框架层面：TDDL、Apache ShardingSphere-JDBC。

中间件层面：DRDS（商业闭源）、Apache ShardingSphere-Proxy、MyCat/DBLE、Cobar、Vitness、KingShard。


数据迁移的方式：
1、全量
2、全量+增量
3、binlog+全量+增量
4、迁移工具 ShardingSphere-scaling


---

## 7)RPC 和微服务

### 7.1 RPC

RPC是基于接口的远程服务调用。

常见的RPC技术
1、Corba/RMI/.NET Remoting
2、JSON RPC, XML RPC，WebService(Axis2, CXF)
3、Hessian, Thrift, Protocol Buffer, gRPC

### 7.2 Dubbo

dubbo有6大核心功能：面向接口代理的高性能RPC调用，智能负载均衡，服务自动注册和发现，高度可扩展能力，运行期流量调度，可视化的服务治理与运维。

整体架构：config、proxy、registry、cluster、monitor、protocol、exchange、transport、serialize。

### 7.3 分布式服务化

分布式服务化：直连调用，侧边增强

配置中心（ConfigCenter）：管理系统需要的配置参数信息
注册中心（RegistryCenter）：管理系统的服务注册、提供发现和协调能力
元数据中心（MetadataCenter）：管理各个节点使用的元数据信息
服务注册。
服务发现。
服务集群。
服务路由。
服务负载均衡。
服务过滤。
服务流控。

### 7.4 微服务

微服务发展历程
1、响应式微服务。
2、服务网格与云原生。
3、数据库网格。
4、单元化架构。


什么时候用微服务：随着复杂度升高，单体架构的生产力快速下降，而微服务相对平稳。


六大最佳实践：
1、遗留系统改造
2、恰当粒度拆分
3、扩展立方体
4、自动化管理
5、分布式事务
6、完善监控体系



---

## 8) 分布式缓存

### 8.1 缓存技术
缓存的加载时机：启动全量加载、懒加载（同步使用加载、延迟异步加载）。

本地缓存：spring cache。
远程缓存redis、memcached。

过期策略：FIFO或LRU、按固定时间过期、按业务时间加权。

缓存穿透：大量并发查找不存在的key，导致压力都到数据库。
缓存击穿：某个key失效，正好有大量并发请求访问这个key。
缓存雪崩：某一刻发生大规模的缓存失效，会有大量请求直接打到数据库。


### 8.2 Redis

Redis有5种数据结构：string（尽量用整数，有精度问题）、map、list、set、sorted set。

Redis到底是单线程还是多线程：
1、IO线程：Redis6之前，单线程。Redis6之后多线程NIO。
2、内存处理线程：单线程（高性能的核心）。


Redis6大使用场景：业务数据缓存、业务数据处理、全局一致性计数、高效统计计数、发布订阅与Stream、分布式锁。

Redis事务：multi、exec、discard。

Redis性能优化：内存优化（压缩数据）、CPU优化（不要阻塞）。

Redis Cluster：分片，通过一致性hash，将数据分配到多个服务器节点。设计16384个哈希槽。


---

## 9) 分布式消息队列

### 9.1 消息队列

mq的4大作用：
1、异步通信。
2、系统解耦。
3、削峰平谷。
4、可靠通信。

mq处理模式，有点对点、发布订阅。

消息处理的保障，有三种qos：至多一次、至少一次、精确一次。

消息的有序性：同一个queue或topic可以保障有序，分区的消息无法保障。

3代消息队列：
1、active mq、rabbitmq
2、kafka、rocket mq
3、apache pulsar


### 9.2 ActiveMQ

使用最广

ActiveMQ的使用场景：
1、所有需要使用消息队列的地方；
2、订单处理、消息通知、服务降级等等；
3、特别地，纯java实现，支持嵌入到应用系统。

### 9.3 Kafka

Kafka：是一种分布式、基于发布订阅的消息系统。有消息持久化、高吞吐率、顺序传输、支持离线数据处理和实时数据处理、支持水平扩展。

kafka有：broker（kafka集群中一个或多个服务器）、topic（消息类别）、partition、producer、consumer、
consumer group。

Topic特性
1、通过partition增加可扩展性
2、通过顺序写入达到高吞吐
3、多副本增加容错性


### 9.4 RabbitMQ

rabbitmq概念：queue/exchange/routekey/binding。

### 9.5 RocketMQ


RocketMQ与Kafka的关系：作为Kafka的重新实现版，没太大本质区别（百事可口）
1、纯Java开发，用不用zk。
2、支持延迟投递，消息追溯。
3、多个队列使用一个日志文件，所以不存在kafka过多topic问题。


### 9.6 Plusar

基于topic，支持namespace和多租户


