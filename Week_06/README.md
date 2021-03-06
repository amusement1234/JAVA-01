学习笔记 

Week06 作业题目（周三）：第11课 
1.（选做）尝试使用 Lambda/Stream/Guava 优化之前作业的代码。 
2.（选做）尝试使用 Lambda/Stream/Guava 优化工作中编码的代码。 
3.（选做）根据课上提供的材料，系统性学习一遍设计模式，并在工作学习中思考如何用设计模式解决问题。 
4.（选做）根据课上提供的材料，深入了解 Google 和 Alibaba 编码规范，并根据这些规范，检查自己写代码是否符合规范，有什么可以改进的。 

Week06 作业题目（周日）：第12课 

1.（选做）基于课程中的设计原则和最佳实践，分析是否可以将自己负责的业务系统进行数据库设计或是数据库服务器方面的优化 
2.（必做）基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。 
3.（选做）尽可能多的从“常见关系数据库”中列的清单，安装运行，并使用上一题的 SQL 测试简单的增删改查。 
4.（选做）基于上一题，尝试对各个数据库测试 100 万订单数据的增删改查性能。 
5.（选做）尝试对 MySQL 不同引擎下测试 100 万订单数据的增删改查性能。 
6.（选做）模拟 1000 万订单数据，测试不同方式下导入导出（数据备份还原）MySQL 的速度，包括 jdbc 程序处理和命令行处理。思考和实践，如何提升处理效率。
7.（选做）对 MySQL 配置不同的数据库连接池（DBCP、C3P0、Druid、Hikari），测试增删改查 100 万次，对比性能，生成报告。 



4题答案：



5题答案： 
存储引擎，执行速度从快到慢依次 merory>archive>myisam>innodb

6题答案：
导出1000w数据，耗时88s，文件大小515MB。
导入1000w数据，解析24分钟，导入25分钟。共60分钟。

7题答案：


数据量：100w
存储引擎：	Innodb	myisam	archive	merory 
连接池：hikari 	16672	9211	6666	5984 
	c3p0  	11735	9526	6782	6223 
	dbcp  	13149	9667	6529	6073 
	druid 	11712	9782	6732	5873 



mysql存储引擎=merory，单表最多155232行。重启mysql服务数据会丢失。 

需要支持更多行，需要添加下面的到my.ini文件： 
tmp_table_size = 256M 
max_heap_table_size = 256M 



1000w条数据查询：
select count(*) from demo;花费48s
select count(1) from demo;花费39s
select count(id) from demo;花费38s


1000w数据导出：
转储为sql文件，花费70s。文件大小515MB。


批量导入：
bin>   mysql -uroot -p123456 -Dtest<d:\test\ss.sql
mysql> source  E:ydj\test.sql




批量插入100w代码如下：

配置：

jdbcUrl=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT&rewriteBatchedStatements=true
（rewriteBatchedStatements=true 支持批量插入）

代码：
    final static int MAX = 1000000;

    @Test
    @SneakyThrows
    public void run() {
        long beginMs = System.currentTimeMillis();
                                  //Innodb/myisam/archive/merory
//        jdbcHandler2("hikari"); //16672,9211,6666,5984
//        jdbcHandler2("c3p0");   //11735,9526,6782,6223
//        jdbcHandler2("dbcp");   //13149,9667,6529,6073
         jdbcHandler2("druid"); //11712,9782,6732,5873

        int execMs = Math.toIntExact((System.currentTimeMillis() - beginMs));
        System.out.println(execMs);

}

    private void jdbcHandler2(final String type) {
        Connection conn = null;
        if (("druid").equals(type)) {
            conn = druidDataSource.getConnection();
        } else if (("dbcp").equals(type)) {
            conn = dbcpDataSource.getConnection();
        } else if (("c3p0").equals(type)) {
            conn = c3p0DataSource.getConnection();
        } else if (("hikari").equals(type)) {
            conn = hikariDataSource.getConnection();
        }

        try {
            // 关闭自动提交:
            //conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement("insert into demo(name) values(?)");

            for (int i = 1; i <= MAX; i++) {
                ps.setString(1, "张三" + i);
                ps.addBatch();
            }
            int[] r = ps.executeBatch();
            System.out.println(r.length);

        } catch (SQLException e) {
            // 回滚事务:
            // conn.rollback();
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close(); //归还连建
            }
        }
    }


