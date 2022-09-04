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
at com.kk.oom.HeapOOM.main(HeapOOM.java:19)

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


