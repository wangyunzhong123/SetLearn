package wang.Thread;

/**
 * Created by tianxi on 8/9/16.
 */
public class SynchronizedLearn5_3 {
    Something3 x = new Something3();
    Something3 y = new Something3();

    // 比较(03) x.cSyncA()与y.cSyncB()
    private void test3() {
        // 新建t31, t31会调用 x.isSyncA()
        Thread t31 = new Thread(
                new Runnable() {
                    public void run() {
                        x.cSyncA();
                    }
                }, "t31");

        // 新建t32, t32会调用 x.isSyncB()
        Thread t32 = new Thread(
                new Runnable() {
                    public void run() {
                        y.cSyncB();
                    }
                }, "t32");


        t31.start();  // 启动t31
        t32.start();  // 启动t32
    }

    public static void main(String[] args) {
        SynchronizedLearn5_3 demo = new SynchronizedLearn5_3();

        demo.test3();
    }
    /*
    * 03) 不能被同时访问。因为cSyncA()和cSyncB()都是static类型，x.cSyncA()相当于Something.isSyncA()，y.cSyncB()相当于Something.isSyncB()，因此它们共用一个同步锁，不能被同时反问。
    * */
}
class Something3 {
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