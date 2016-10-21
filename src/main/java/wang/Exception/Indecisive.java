package wang.Exception;

/**
 * Created by tianxi on 10/21/16.
 */
public class Indecisive {

    public static void main(String[] args) {
        System.out.println(decision());
    }

    private static boolean decision() {
        try {
            return true;
        } finally {
            return false;
        }
    }
}
/*
* 结果说明：
　　在一个 try-finally 语句中,finally 语句块总是在控制权离开 try 语句块时执行的。无论 try 语句块是正常结束的,还是意外结束的, 情况都是如此。
　　一条语句或一个语句块在它抛出了一个异常,或者对某个封闭型语句执行了一个 break 或 continue,或是象这个程序一样在方法中执行了一个return 时,
    将发生意外结束。它们之所以被称为意外结束,是因为它们阻止程序去按顺序执行下面的语句。当 try 语句块和 finally 语句块都意外结束时, try 语句
    块中引发意外结束的原因将被丢弃, 而整个 try-finally 语句意外结束的原因将于 finally 语句块意外结束的原因相同。在这个程序中,在 try 语句块中的
    return 语句所引发的意外结束将被丢弃, try-finally 语句意外结束是由 finally 语句块中的 return 而造成的。

　　简单地讲, 程序尝试着 (try) (return) 返回 true, 但是它最终 (finally) 返回(return)的是 false。丢弃意外结束的原因几乎永远都不是你想要的行为,
    因为意外结束的最初原因可能对程序的行为来说会显得更重要。对于那些在 try 语句块中执行 break、continue 或 return 语句,只是为了使其行为被 finally
     语句块所否决掉的程序,要理解其行为是特别困难的。总之,每一个 finally 语句块都应该正常结束,除非抛出的是不受检查的异常。 千万不要用一个 return、break、continue
     或 throw 来退出一个 finally 语句块,并且千万不要允许将一个受检查的异常传播到一个 finally 语句块之外去。对于语言设计者, 也许应该要求 finally 语句块在未出现不
     受检查的异常时必须正常结束。朝着这个目标,try-finally 结构将要求 finally 语句块可以正常结束。return、break 或 continue 语句把控制权传递到 finally 语句块之
     外应该是被禁止的, 任何可以引发将被检查异常传播到 finally 语句块之外的语句也同样应该是被禁止的。
*
*
* */