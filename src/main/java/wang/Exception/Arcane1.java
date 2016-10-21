package wang.Exception;

/**
 * Created by tianxi on 10/21/16.
 */
import java.io.IOException;

public class Arcane1 {

//    public static void main(String[] args) {
//        try {
//            System.out.println("Hello world");
//        } catch(IOException e) {
//            System.out.println("I've never seen println fail!");
//        }
//    }
}

/*
* 编译出错
* 但是这个程序不能编译,因为 println 方法没有声明会抛出任何被检查异常,
* Arcane1.java:9: exception java.io.IOException is never thrown in body of corresponding try statement
* */