学习笔记

作业内容：



## 1（可选）、自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和for，然后自己分析一下对应的字节码，有问题群里讨论。

源码：
``` public class app {
    public static void main(String[] args) {
        int a = 1;
        int b = 10;
        int c = a + b;
        int d = a / b;
        for (int i = 0; i < b; i++) {
            b++;
        }

        if (a > b) {
            return;
        }
    }

}
```


查看字节码：
```
E:\JavaClass\JavaCode\out\production\JavaCode>javap -c -verbose app.class
Classfile /E:/JavaClass/JavaCode/out/production/JavaCode/app.class
  Last modified 2021年1月12日; size 546 bytes
  MD5 checksum 2dad5e5d61e381584f018320fc0577b8
  Compiled from "app.java"
public class app
  minor version: 0
  major version: 55
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #2                          // app
  super_class: #3                         // java/lang/Object
  interfaces: 0, fields: 0, methods: 2, attributes: 1
Constant pool:
   #1 = Methodref          #3.#25         // java/lang/Object."<init>":()V
   #2 = Class              #26            // app
   #3 = Class              #27            // java/lang/Object
   #4 = Utf8               <init>
   #5 = Utf8               ()V
   #6 = Utf8               Code
   #7 = Utf8               LineNumberTable
   #8 = Utf8               LocalVariableTable
   #9 = Utf8               this
  #10 = Utf8               Lapp;
  #11 = Utf8               main
  #12 = Utf8               ([Ljava/lang/String;)V
  #13 = Utf8               i
  #14 = Utf8               I
  #15 = Utf8               args
  #16 = Utf8               [Ljava/lang/String;
  #17 = Utf8               a
  #18 = Utf8               b
  #19 = Utf8               c
  #20 = Utf8               d
  #21 = Utf8               StackMapTable
  #22 = Class              #16            // "[Ljava/lang/String;"
  #23 = Utf8               SourceFile
  #24 = Utf8               app.java
  #25 = NameAndType        #4:#5          // "<init>":()V
  #26 = Utf8               app
  #27 = Utf8               java/lang/Object
{
  public app();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1    //查找上面常量池中的#1，初始化构造函数。              // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 1: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lapp;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=6, args_size=1
         0: iconst_1		//1压入栈顶
         1: istore_1		//a 存放在本地变量表槽位1
         2: bipush        10	//10压入栈顶
         4: istore_2		//b 存放在本地变量表槽位2
         5: iload_1		//a入栈。取本地变量表槽位1位置变量(a)入栈
         6: iload_2		//b入栈
         7: iadd		//a+b
         8: istore_3		//c
         9: iload_1		//a入栈
        10: iload_2		//b入栈
        11: idiv		//a/b
        12: istore        4	//d
        14: iconst_0		//0压入栈顶
        15: istore        5	//i
        17: iload         5	//i入栈
        19: iload_2		//b入栈
        20: if_icmpge     32	//如果 iload>=iload_2, 跳转到32，return
        23: iinc          2, 1	//b++ 直接在本地表里表中进行加法
        26: iinc          5, 1	//i++
        29: goto          17	//跳转到17，继续循环
        32: iload_1		//a入栈
        33: iload_2		//b入栈
        34: if_icmple     38	//如果a>=b，跳到38
        37: return
        38: return
      LineNumberTable:
        line 3: 0		//第3行代码 int a = 1   》》对应》》 0: iconst_1
        line 4: 2		//第4行代码 int b = 10; 》》对应》》 2: bipush        10	//10压入栈顶
        line 5: 5
        line 6: 9
        line 7: 14
        line 8: 23
        line 7: 26
        line 11: 32
        line 12: 37
        line 14: 38
      LocalVariableTable:		//本地变量表
					//Start：是变量生效的位置 Length：是生效的长度 Slot：是变量放的槽
        Start  Length  Slot  Name   Signature	
           17      15     5     i   I
            0      39     0  args   [Ljava/lang/String;
            2      37     1     a   I	//a在本地变量表槽位1，在位置2-39有效
            5      34     2     b   I
            9      30     3     c   I
           14      25     4     d   I
					//StackMapTable参考 https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.7.4
      StackMapTable: number_of_entries = 3
        frame_type = 255 /* full_frame */
          offset_delta = 17
          locals = [ class "[Ljava/lang/String;", int, int, int, int, int ]
          stack = []
        frame_type = 250 /* chop */
          offset_delta = 14
        frame_type = 5 /* same */
}
SourceFile: "app.java"
```







## 2（必做）、自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法， 此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。

见src文件夹




## 3（必做）、画一张图，展示 Xmx、Xms、Xmn、Metaspace、DirectMemory、Xss
这些内存参数的关系。



-Xmx:最大堆大小
-Xms:初始堆大小
-Xmn：年轻代大小

-XX:MetaspaceSize，初始空间大小
-XX:MaxMetaspaceSize，最大空间，默认是没有限制的。

-Xss:设置每个线程的堆栈大小

画图见截图

## 4（可选）、检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。
注意：

1、对于线上有流量的系统，慎重使用jmap命令。

2、如果没有线上系统，可以自己 run 一个 web/java 项目。或者直接查看idea进程。



