package wang;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by tianxi on 8/9/16.
 */

/*
* WeakHashMap简介

    WeakHashMap 继承于AbstractMap，实现了Map接口。
    和HashMap一样，WeakHashMap 也是一个散列表，它存储的内容也是键值对(key-value)映射，而且键和值都可以是null。
   不过WeakHashMap的键是“弱键”。在 WeakHashMap 中，当某个键不再正常使用时，会被从WeakHashMap中被自动移除。更精确地说，对于一个给定的键，其映射的存在并不阻止垃圾回收器对该键的丢弃，这就使该键成为可终止的，被终止，然后被回收。某个键被终止时，它对应的键值对也就从映射中有效地移除了。
    这个“弱键”的原理呢？大致上就是，通过WeakReference和ReferenceQueue实现的。 WeakHashMap的key是“弱键”，即是WeakReference类型的；ReferenceQueue是一个队列，它会保存被GC回收的“弱键”。实现步骤是：
    (01) 新建WeakHashMap，将“键值对”添加到WeakHashMap中。
           实际上，WeakHashMap是通过数组table保存Entry(键值对)；每一个Entry实际上是一个单向链表，即Entry是键值对链表。
   (02) 当某“弱键”不再被其它对象引用，并被GC回收时。在GC回收该“弱键”时，这个“弱键”也同时会被添加到ReferenceQueue(queue)队列中。
   (03) 当下一次我们需要操作WeakHashMap时，会先同步table和queue。table中保存了全部的键值对，而queue中保存被GC回收的键值对；同步它们，就是删除table中被GC回收的键值对。
   这就是“弱键”如何被自动从WeakHashMap中删除的步骤了。

和HashMap一样，WeakHashMap是不同步的。可以使用 Collections.synchronizedMap 方法来构造同步的 WeakHashMap。
* */

/*
* 从图中可以看出：
(01) WeakHashMap继承于AbstractMap，并且实现了Map接口。
(02) WeakHashMap是哈希表，但是它的键是"弱键"。WeakHashMap中保护几个重要的成员变量：table, size, threshold, loadFactor, modCount, queue。
　　table是一个Entry[]数组类型，而Entry实际上就是一个单向链表。哈希表的"key-value键值对"都是存储在Entry数组中的。
　　size是Hashtable的大小，它是Hashtable保存的键值对的数量。
　　threshold是Hashtable的阈值，用于判断是否需要调整Hashtable的容量。threshold的值="容量*加载因子"。
　　loadFactor就是加载因子。
　　modCount是用来实现fail-fast机制的
　　queue保存的是“已被GC清除”的“弱引用的键”。
* */
public class WeakHashMapLearn {

    public static void main(String[] args) throws Exception {
        testWeakHashMapAPIs();
    }

    private static void testWeakHashMapAPIs() {
        // 初始化3个“弱键”
        String w1 = new String("one");
        String w2 = new String("two");
        String w3 = new String("three");
        // 新建WeakHashMap
        Map wmap = new WeakHashMap();

        // 添加键值对
        wmap.put(w1, "w1");
        wmap.put(w2, "w2");
        wmap.put(w3, "w3");

        // 打印出wmap
        System.out.printf("\nwmap:%s\n",wmap );

        // containsKey(Object key) :是否包含键key
        System.out.printf("contains key two : %s\n",wmap.containsKey("two"));
        System.out.printf("contains key five : %s\n",wmap.containsKey("five"));

        // containsValue(Object value) :是否包含值value
        System.out.printf("contains value 0 : %s\n",wmap.containsValue(new Integer(0)));

        // remove(Object key) ： 删除键key对应的键值对
        wmap.remove("three");

        System.out.printf("wmap: %s\n",wmap );



        // ---- 测试 WeakHashMap 的自动回收特性 ----

        // 将w1设置null。
        // 这意味着“弱键”w1再没有被其它对象引用，调用gc时会回收WeakHashMap中与“w1”对应的键值对
        w1 = null;
        // 内存回收。这里，会回收WeakHashMap中与“w1”对应的键值对
        System.gc();

        // 遍历WeakHashMap
        Iterator iter = wmap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry en = (Map.Entry)iter.next();
            System.out.printf("next : %s - %s\n",en.getKey(),en.getValue());
        }
        // 打印WeakHashMap的实际大小
        System.out.printf(" after gc WeakHashMap size:%s\n", wmap.size());
    }

}
