package wang.Exception;

/**
 * Created by tianxi on 10/21/16.
 */
interface Type1 {
    void f() throws CloneNotSupportedException;
}

interface Type2 {
    void f() throws InterruptedException;
}

interface Type3 extends Type1, Type2 {
}

public class Arcane3 implements Type3 {
    public void f() {
        System.out.println("Hello world");
    }
    public static void main(String[] args) {
        Type3 t3 = new Arcane3();
        t3.f();
    }
}

/*
* 第三个程序,Arcane3,看起来它也不能编译。方法 f 在 Type1 接口中声明要抛出被检查异常 CloneNotSupportedException,并且在 Type2 接
口中声明要抛出被检查异常 InterruptedException。Type3 接口继承了 Type1 和 Type2,因此, 看起来在静态类型为 Type3 的对象上调用
方法 f 时, 有潜在可能会抛出这些异常。一个方法必须要么捕获其方法体可以抛出的所有被检查异常, 要么声明它将抛出这些异常。Arcane3
的 main 方法在静态类型为 Type3 的对象上调用了方法 f,但它对 CloneNotSupportedException 和 InterruptedExceptioin 并没有作这些
处理。那么,为什么这个程序可以编译呢?


上述分析的缺陷在于对“Type3.f 可以抛出在 Type1.f 上声明的异常和在 Type2.f 上声明的异常”所做的假设。这并不正确,因为每一个接口都
限制了方法 f 可以抛出的被检查异常集合。一个方法可以抛出的被检查异常集合是它所适用的所有类型声明要抛出的被检查异常集合的交集,而
不是合集。因此,静态类型为 Type3 的对象上的 f 方法根本就不能抛出任何被检查异常。因此,Arcane3可以毫无错误地通过编译,并且打印
 Hello world。

* */
