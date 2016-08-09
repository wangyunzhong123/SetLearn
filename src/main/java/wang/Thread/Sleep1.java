package wang.Thread;

/**
 * Created by tianxi on 8/9/16.
 */

/*
* 1. sleep()介绍
sleep() 定义在Thread.java中。
sleep() 的作用是让当前线程休眠，即当前线程会从“运行状态”进入到“休眠(阻塞)状态”。sleep()会指定休眠时间，线程休眠的时间会大于/等于该休眠时间；在线程重新被唤醒时，它会由“阻塞状态”变成“就绪状态”，从而等待cpu的调度执行。
* */
public class Sleep1 {


    public static void main(String[] args){
        ThreadA t1 = new ThreadA("t1");
        t1.start();
    }
    /*
    * 结果说明：
程序比较简单，在主线程main中启动线程t1。t1启动之后，当t1中的计算i能被4整除时，t1会通过Thread.sleep(100)休眠100毫秒。
    * */
}
class ThreadA extends Thread{
    public ThreadA(String name){
        super(name);
    }
    public synchronized void run() {
        try {
            for(int i=0; i <10; i++){
                System.out.printf("%s: %d\n", this.getName(), i);
                // i能被4整除时，休眠100毫秒
                if (i%4 == 0)
                    Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}