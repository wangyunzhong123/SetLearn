package wang.Thread;

/**
 * Created by tianxi on 8/9/16.
 */
public class SynchronizedLearn1_1 {

    /*
    * 第一条
    当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程对“该对象”的该“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
    下面是“synchronized代码块”对应的演示程序。
    * */
    public static void main(String[] args) {
        Runnable demo = new MyRunable();     // 新建“Runnable对象”

        Thread t1 = new Thread(demo, "t1");  // 新建“线程t1”, t1是基于demo这个Runnable对象
        Thread t2 = new Thread(demo, "t2");  // 新建“线程t2”, t2是基于demo这个Runnable对象
        t1.start();                          // 启动“线程t1”
        t2.start();                          // 启动“线程t2”
    }
    /*
    * 结果说明：
    run()方法中存在“synchronized(this)代码块”，而且t1和t2都是基于"demo这个Runnable对象"创建的线程。
    这就意味着，我们可以将synchronized(this)中的this看作是“demo这个Runnable对象”；因此，
    线程t1和t2共享“demo对象的同步锁”。所以，当一个线程运行的时候，另外一个线程必须等待“运行线程”释放“demo的同步锁”之后才能运行。
    * */
}
class MyRunable implements Runnable {

//    @Override
    public void run() {
        synchronized(this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }
}