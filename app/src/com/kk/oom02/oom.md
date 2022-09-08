#### Java运行时数据区
   Java 运行时数据区分为两部分，一部分是线程私有，一部分为线程公有；

线程私有又分为三块

1. 程序计数器
   1. 可以看作当前线程所执行字节码的行号指示器；是程序控制流的指示器。
   2. 因为java 虚拟机是多线程的，每个线程都需要有自己单独程序计数器，这样多线程切换的时候就不会相互影响；
   3. 如果线程正在执行的是一个Java方法，这个计数器记录的是正在执行的虚拟机字节码指令的地
   址；如果正在执行的是本地（Native）方法，这个计数器值则应为空（Undefined）。所以此区域不会出现OutOfMemoryError
2. java虚拟机栈
   1. 虚拟机栈描述的是Java方法执行的线程内存模型：
3. 本地方法栈















java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid12932.hprof ...
Heap dump file created [28123057 bytes in 0.062 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
at java.util.Arrays.copyOf(Arrays.java:3210)
at java.util.Arrays.copyOf(Arrays.java:3181)
at java.util.ArrayList.grow(ArrayList.java:267)
at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:241)
at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:233)
at java.util.ArrayList.add(ArrayList.java:464)
at com.kk.oom02.HeapOOM.main(HeapOOM.java:19)

#### 对于Java堆 OOM，
1. 开启 -XX：+HeapDumpOnOutOf-MemoryError 
2. 关闭k8s容器自动重启
3. 把dump下来的文件
4. 通过jvisualvm 进行分析
   1. 如果是内存泄漏，可进一步通过工具查看泄漏对象到GC Roots的引用链，找到泄漏对象是通过怎 
   样的引用路径、与哪些GC Roots相关联，才导致垃圾收集器无法回收它们，根据泄漏对象的类型信息
   以及它到GC Roots引用链的信息，一般可以比较准确地定位到这些对象创建的位置，进而找出产生内
   存泄漏的代码的具体位置。
   2. 如果不是内存泄漏，换句话说就是内存中的对象确实都是必须存活的，那就应当检查Java虚拟机 
   的堆参数（-Xmx与-Xms）设置，与机器的内存对比，看看是否还有向上调整的空间。再从代码上检查
   是否存在某些对象生命周期过长、持有状态时间过长、存储结构设计不合理等情况，尽量减少程序运
   行期的内存消耗。



