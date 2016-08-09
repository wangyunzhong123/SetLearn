package wang.Thread;

/**
 * Created by tianxi on 8/9/16.
 */

/*
* 1. yield()介绍
yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保证在当前线程调用yield()之后，
其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！
* */
public class Yield1 {
    public static void main(String[] args){
        ThreadA11 t1 = new ThreadA11("t1");
        ThreadA11 t2 = new ThreadA11("t2");
        t1.start();
        t2.start();
    }
    /*
    * 结果说明：
“线程t1”在能被4整数的时候，并没有切换到“线程t2”。这表明，yield()虽然可以让线程由“运行状态”进入到“就绪状态”；但是，它不一定会让其它线程获取CPU执行权(即，其它线程进入到“运行状态”)，即使这个“其它线程”与当前调用yield()的线程具有相同的优先级。
    * */
}
class ThreadA11 extends Thread{
    public ThreadA11(String name){
        super(name);
    }
    public synchronized void run(){
        for(int i=0; i <10; i++){
            System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
            // i整除4时，调用yield
            if (i%4 == 0)
                Thread.yield();
        }
    }
}