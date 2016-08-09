package wang;

/**
 * Created by tianxi on 8/9/16.
 */

/*
* equals() 的作用是 用来判断两个对象是否相等。

equals() 定义在JDK的Object.java中。通过判断两个对象的地址是否相等(即，是否是同一个对象)来区分它们是否相等。源码如下：

public boolean equals(Object obj) {
    return (this == obj);
}
既然Object.java中定义了equals()方法，这就意味着所有的Java类都实现了equals()方法，所有的类都可以通过equals()去比较两个对象是否相等。 但是，我们已经说过，使用默认的“equals()”方法，等价于“==”方法。因此，我们通常会重写equals()方法：若两个对象的内容相等，则equals()方法返回true；否则，返回fasle。

下面根据“类是否覆盖equals()方法”，将它分为2类。
(01) 若某个类没有覆盖equals()方法，当它的通过equals()比较两个对象时，实际上是比较两个对象是不是同一个对象。这时，等价于通过“==”去比较这两个对象。
(02) 我们可以覆盖类的equals()方法，来让equals()通过其它方式比较两个对象是否相等。通常的做法是：若两个对象的内容相等，则equals()方法返回true；否则，返回fasle。
* */
public class HasCodeAndEquals {

    public static void main(String[] args) {
        // 新建2个相同内容的Person对象，
        // 再用equals比较它们是否相等
        Person p1 = new Person("eee", 100);
        Person p2 = new Person("eee", 100);
        System.out.printf("%s\n", p1.equals(p2));
        /*
        * 结果分析：
       我们通过 p1.equals(p2) 来“比较p1和p2是否相等时”。实际上，调用的Object.java的equals()方法，即调用的 (p1==p2) 。它是比较“p1和p2是否是同一个对象”。
       而由 p1 和 p2 的定义可知，它们虽然内容相同；但它们是两个不同的对象！因此，返回结果是false。
        * */
    }

    /**
     * @desc Person类。
     */
    private static class Person {
        int age;
        String name;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return name + " - " +age;
        }
    }

}
