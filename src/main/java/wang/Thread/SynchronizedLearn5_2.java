package wang.Thread;

/**
 * Created by tianxi on 8/9/16.
 */
public class SynchronizedLearn5_2 {

    Something2 x = new Something2();
    Something2 y = new Something2();

    // 比较(02) x.isSyncA()与y.isSyncA()
    private void test2() {
        // 新建t21, t21会调用 x.isSyncA()
        Thread t21 = new Thread(
                new Runnable() {
                    public void run() {
                        x.isSyncA();
                    }
                }, "t21");

        // 新建t22, t22会调用 x.isSyncB()
        Thread t22 = new Thread(
                new Runnable() {
                    public void run() {
                        y.isSyncA();
                    }
                }, "t22");


        t21.start();  // 启动t21
        t22.start();  // 启动t22
    }

    public static void main(String[] args) {
        SynchronizedLearn5_2 demo = new SynchronizedLearn5_2();

        demo.test2();
    }
    /*
    * (02) 可以同时被访问。因为访问的不是同一个对象的同步锁，x.isSyncA()访问的是x的同步锁，而y.isSyncA()访问的是y的同步锁。
    * */

}
class Something2 {
    public synchronized void isSyncA(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncA");
            }
        }catch (InterruptedException ie) {
        }
    }
    public synchronized void isSyncB(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncB");
            }
        }catch (InterruptedException ie) {
        }
    }
    public static synchronized void cSyncA(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : cSyncA");
            }
        }catch (InterruptedException ie) {
        }
    }
    public static synchronized void cSyncB(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : cSyncB");
            }
        }catch (InterruptedException ie) {
        }
    }
}