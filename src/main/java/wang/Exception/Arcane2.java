package wang.Exception;

/**
 * Created by tianxi on 10/21/16.
 */
public class Arcane2 {
    public static void main(String[] args) {
        try {
            // If you have nothing nice to say, say nothing
        } catch(Exception e) {
            System.out.println("This can't happen");
        }
    }
}

/*
* 基于同样的理由,第二个程序,Arcane2,看起来应该是不可以编译的,但是它却可以。它之所以可以编译,是因为它唯一的
 * catch 子句检查了 Exception。尽管在这一点上十分含混不清,但是捕获 Exception 或 Throwble 的 catch 子句是合法的,
* 不管与其相对应的 try 子句的内容为何。尽管 Arcane2 是一个合法的程序,但是 catch 子句的内容永远的不会被执行,这个程序什么都不会打印
* */