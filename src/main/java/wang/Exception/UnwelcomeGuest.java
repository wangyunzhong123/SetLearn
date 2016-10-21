package wang.Exception;

/**
 * Created by tianxi on 10/21/16.
 */
public class UnwelcomeGuest {
//    public static final long GUEST_USER_ID = -1;
//    private static final long USER_ID;
//
//    static {
//        try {
//            USER_ID = getUserIdFromEnvironment();
//        } catch (IdUnavailableException e) {
//            USER_ID = GUEST_USER_ID;
//            System.out.println("Logging in as guest");
//        }
//    }
//
//    private static long getUserIdFromEnvironment()
//            throws IdUnavailableException {
//        throw new IdUnavailableException();
//    }
//
//    public static void main(String[] args) {
//        System.out.println("User ID: " + USER_ID);
//    }
}

class IdUnavailableException extends Exception {
}

/*

该程序并不能编译。如果你尝试着去编译它, 你将看到和一条错误信息。



*问题出在哪里了?USER_ID 域是一个空 final(blank final),它是一个在声明中没有进行初始化操作的 final 域。很明显,只有在对 USER_ID
赋值失败时,才会在 try 语句块中抛出异常,因此,在 catch 语句块中赋值是相 当安全的。不管怎样执行静态初始化操作语句块,只会对
 USER_ID 赋值一次,这正是空 final 所要求的。为什么编译器不知道这些呢? 要确定一个程序是否可以不止一次地对一个空 final 进行赋值
是一个很困难的问题。事实上,这是不可能的。这等价于经典的停机问题,它通常被认为是不可能解决的。为了能够编写出一个编译器,语言规范在
这一点上采用了保守的方式。在程序中,一个空 final 域只有在它是明确未赋过值的地方才可以被赋值。规范长篇大论,对此术语提供了一个准
确的但保守的定义。 因为它是保守的,所以编译器必须拒绝某些可以证明是安全的程序。这个谜题就展示了这样的一个程序。幸运的是, 你不必
为了编写 Java 程序而去学习那些骇人的用于明确赋值的细节。通常明确赋值规则不会有任何妨碍。如果碰巧你编写了一个真的可能会对一个
空final 赋值超过一次的程序,编译器会帮你指出的。只有在极少的情况下,就像本谜题一样, 你才会编写出一个安全的程序, 但是它并不满足规
范的形式化要求。编译器的抱怨就好像是你编写了一个不安全的程序一样,而且你必须修改你的程序以满足它。


*
* */