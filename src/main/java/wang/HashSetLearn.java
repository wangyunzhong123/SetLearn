package wang;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by tianxi on 8/9/16.
 */

/*
* HashSet 简介

HashSet 是一个没有重复元素的集合。
它是由HashMap实现的，不保证元素的顺序，而且HashSet允许使用 null 元素。
HashSet是非同步的。如果多个线程同时访问一个哈希 set，而其中至少一个线程修改了该 set，
那么它必须 保持外部同步。这通常是通过对自然封装该 set 的对象执行同步操作来完成的。如果不存在这样的对象，
则应该使用 Collections.synchronizedSet 方法来“包装” set。最好在创建时完成这一操作，以防止对该 set 进行意外的不同步访问：
* */
public class HashSetLearn {

    public static void main(String[] args) {
        // 新建HashSet
        HashSet set = new HashSet();

        // 添加元素 到HashSet中
        for (int i=0; i<5; i++)
            set.add(""+i);

        // 通过Iterator遍历HashSet
        iteratorHashSet(set) ;

        // 通过for-each遍历HashSet
        foreachHashSet(set);
    }

    /*
     * 通过Iterator遍历HashSet。推荐方式
     */
    private static void iteratorHashSet(HashSet set) {
        for(Iterator iterator = set.iterator();
            iterator.hasNext(); ) {
            System.out.printf("iterator : %s\n", iterator.next());
        }
    }

    /*
     * 通过for-each遍历HashSet。不推荐！此方法需要先将Set转换为数组
     */
    private static void foreachHashSet(HashSet set) {
        String[] arr = (String[])set.toArray(new String[0]);
        for (String str:arr)
            System.out.printf("for each : %s\n", str);
    }

}
