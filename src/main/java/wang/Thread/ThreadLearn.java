package wang.Thread;

/**
 * Created by tianxi on 8/9/16.
 */
public class ThreadLearn {

    public static void main(String[] args) {
        // 启动3个线程t1,t2,t3；每个线程各卖10张票！
        MyThread t1=new MyThread();
        MyThread t2=new MyThread();
        MyThread t3=new MyThread();
        t1.start();
        t2.start();
        t3.start();
        /*
        * 结果说明：
        (01) MyThread继承于Thread，它是自定义个线程。每个MyThread都会卖出10张票。
        (02) 主线程main创建并启动3个MyThread子线程。每个子线程都各自卖出了10张票。
        * */
    }

}
class MyThread extends Thread{
    private int ticket=10;
    public void run(){
        for(int i=0;i<20;i++){
            if(this.ticket>0){
                System.out.println(this.getName()+" 卖票：ticket"+this.ticket--);
            }
        }
    }
};