package com.kk.oom;

import java.util.HashSet;
import java.util.Set;

/**
 * VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M
 * jdk 1.6 运行
 *
 * Exception in thread "Reference Handler" java.lang.OutOfMemoryError: PermGen space
 * 	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:123)
 * Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
 * 	at java.lang.String.intern(Native Method)
 * 	at com.kk.oom.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java from InputFileObject:20)
 * Disconnected from the target VM, address: '127.0.0.1:13487', transport: 'socket'
 *
 *  jdk 1.8运行
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=6M; support was removed in 8.0
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=6M; support was removed in 8.0
 * Connected to the target VM, address: '127.0.0.1:13527', transport: 'socket'
 *
 * @author k 2022/9/5 23:45
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
