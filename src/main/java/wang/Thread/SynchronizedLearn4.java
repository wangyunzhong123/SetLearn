package wang.Thread;

/**
 * Created by tianxi on 8/9/16.
 */

/*
* 3. synchronized方法 和 synchronized代码块
“synchronized方法”是用synchronized修饰方法，而 “synchronized代码块”则是用synchronized修饰代码块。

synchronized方法示例

public synchronized void foo1() {
    System.out.println("synchronized methoed");
}
synchronized代码块

public void foo2() {
    synchronized (this) {
        System.out.println("synchronized methoed");
    }
}
synchronized代码块中的this是指当前对象。也可以将this替换成其他对象，例如将this替换成obj，则foo2()在执行synchronized(obj)时就获取的是obj的同步锁。


synchronized代码块可以更精确的控制冲突限制访问区域，有时候表现更高效率。下面通过一个示例来演示：
* */
public class SynchronizedLearn4 {

    public synchronized void synMethod() {
        for(int i=0; i<1000000; i++)
            ;
    }

    public void synBlock() {
        synchronized( this ) {
            for(int i=0; i<1000000; i++)
                ;
        }
    }

    public static void main(String[] args) {
        SynchronizedLearn4 demo = new SynchronizedLearn4();

        long start, diff;
        start = System.currentTimeMillis();                // 获取当前时间(millis)
        demo.synMethod();                                // 调用“synchronized方法”
        diff = System.currentTimeMillis() - start;        // 获取“时间差值”
        System.out.println("synMethod() : "+ diff);

        start = System.currentTimeMillis();                // 获取当前时间(millis)
        demo.synBlock();                                // 调用“synchronized方法块”
        diff = System.currentTimeMillis() - start;        // 获取“时间差值”
        System.out.println("synBlock()  : "+ diff);
    }

}
