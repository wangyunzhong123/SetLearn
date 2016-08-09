package wang.Thread;

/**
 * Created by tianxi on 8/9/16.
 */

/*
* 第三条

当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程对“该对象”的其他的“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
我们将上面的例子中的nonSynMethod()方法体的也用synchronized(this)修饰。修改后的源码如下：
* */
public class SynchronizedLearn3 {
    public static void main(String[] args) {
        final Count3 count = new Count3();
        // 新建t1, t1会调用“count对象”的synMethod()方法
        Thread t1 = new Thread(
                new Runnable() {
                    public void run() {
                        count.synMethod();
                    }
                }, "t1");

        // 新建t2, t2会调用“count对象”的nonSynMethod()方法
        Thread t2 = new Thread(
                new Runnable() {
                    public void run() {
                        count.nonSynMethod();
                    }
                }, "t2");


        t1.start();  // 启动t1
        t2.start();  // 启动t2
    }
    /*
    * 结果说明：
       主线程中新建了两个子线程t1和t2。t1和t2运行时都调用synchronized(this)，
       这个this是Count对象(count)，而t1和t2共用count。因此，在t1运行时，t2会被阻塞，
       等待t1运行释放“count对象的同步锁”，t2才能运行。
    * */
}
class Count3 {

    // 含有synchronized同步块的方法
    public void synMethod() {
        synchronized(this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " synMethod loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }

    // 也包含synchronized同步块的方法
    public void nonSynMethod() {
        synchronized(this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " nonSynMethod loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }
}