package wang.IO;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by tianxi on 8/9/16.
 */
public class SerializableLearnTest {

    private static final String TMP_FILE = ".serialabletest2.txt";

    @Test
    public void testMain() throws Exception {
        testWrite();
        testRead();

        /*
        * 源码说明：

    (01) 程序的作用很简单，就是演示：先将“基本类型数据”和“HashMap对象”，通过对象输出流保存到文件中；之后，再通过对象输入流，将这些保存的数据读取出来。

    (02) testWrite()函数说明。testWrite()的作用就是，先将“基本类型数据”和“HashMap对象”，通过对象输出流保存到文件中。
           首先，新建文件TMP_FILE的文件输出流对象(即FileOutputStream对象)，再创建该文件输出流的对象输出流(即ObjectOutputStream对象)。
           然后，通过 writeBoolean(), writeByte(), ... , writeDouble() 等一系列函数将“Boolean, byte, char, ... , double等基本数据类型”写入到对象输出流中。实际上，相当于将这些内容写入到文件TMP_FILE中。
          最后，新建HashMap对象map，并通过out.writeObject(map) 将map写入到对象输出流中。实际上，相当于map写入到文件TMP_FILE中。
    关于HashMap的更多知识，可以参考“Java 集合系列10之 HashMap详细介绍(源码解析)和使用示例”。

    (03) testRead()函数说明。testRead()的作用就是，从文件中读出testWrite()写入的对象。
           首先，新建文件TMP_FILE的文件输入流对象(即FileInputStream对象)，再创建该文件输入流的对象输入流(即ObjectInputStream对象)。
           然后，通过in.readObject() 从对象输入流中读取出testWrite()对象。实际上，相当于从文件TMP_FILE中读取出这些对象。
        * */
    }

    /**
     * ObjectOutputStream 测试函数
     */
    private static void testWrite() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(TMP_FILE));
            out.writeBoolean(true);    // 写入Boolean值
            out.writeByte((byte)65);// 写入Byte值
            out.writeChar('a');     // 写入Char值
            out.writeInt(20131015); // 写入Int值
            out.writeFloat(3.14F);  // 写入Float值
            out.writeDouble(1.414D);// 写入Double值
            // 写入HashMap对象
            HashMap map = new HashMap();
            map.put("one", "red");
            map.put("two", "green");
            map.put("three", "blue");
            out.writeObject(map);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * ObjectInputStream 测试函数
     */
    private static void testRead() {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(TMP_FILE));
            System.out.printf("boolean:%b\n" , in.readBoolean());
            System.out.printf("byte:%d\n" , (in.readByte()&0xff));
            System.out.printf("char:%c\n" , in.readChar());
            System.out.printf("int:%d\n" , in.readInt());
            System.out.printf("float:%f\n" , in.readFloat());
            System.out.printf("double:%f\n" , in.readDouble());
            // 读取HashMap对象
            HashMap map = (HashMap) in.readObject();
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry)iter.next();
                System.out.printf("%-6s -- %s\n" , entry.getKey(), entry.getValue());
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 我们在介绍序列化定义时，说过“序列化/反序列化，是专门用于的保存/恢复对象状态的机制”。
    从中，我们知道：序列化/反序列化，只支持保存/恢复对象状态，即仅支持保存/恢复类的成员变量，但不支持保存类的成员方法！
    但是，序列化是不是对类的所有的成员变量的状态都能保存呢？
    答案当然是否定的！
    (01) 序列化对static和transient变量，是不会自动进行状态保存的。
            transient的作用就是，用transient声明的变量，不会被自动序列化。
    (02) 对于Socket, Thread类，不支持序列化。若实现序列化的接口中，有Thread成员；在对该类进行序列化操作时，编译会出错！
            这主要是基于资源分配方面的原因。如果Socket，Thread类可以被序列化，但是被反序列化之后也无法对他们进行重新的资源分配；再者，也是没有必要这样实现。

    下面，我们还是通过示例来查看“序列化对static和transient的处理”。
    * */
    @Test
    public void testMain2() throws Exception {
        // 将“对象”通过序列化保存
        testWrite2();
        // 将序列化的“对象”读出来
        testRead2();
    }
    /**
     * 将Box对象通过序列化，保存到文件中
     */
    private static void testWrite2() {
        try {
            // 获取文件TMP_FILE对应的对象输出流。
            // ObjectOutputStream中，只能写入“基本数据”或“支持序列化的对象”
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(TMP_FILE));
            // 创建Box对象，Box实现了Serializable序列化接口
            Box2 box = new Box2("desk", 80, 48);
            // 将box对象写入到对象输出流out中，即相当于将对象保存到文件TMP_FILE中
            out.writeObject(box);
            // 打印“Box对象”
            System.out.println("testWrite box: " + box);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 从文件中读取出“序列化的Box对象”
     */
    private static void testRead2() {
        try {
            // 获取文件TMP_FILE对应的对象输入流。
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(TMP_FILE));
            // 从对象输入流中，读取先前保存的box对象。
            Box2 box = (Box2) in.readObject();
            // 打印“Box对象”
            System.out.println("testRead  box: " + box);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    * 我们对前面的SerialTest4.java进行简单修改，以达到：序列化存储static和transient变量的目的。
    * */
    @Test
    public void testMain3() throws Exception {
        // 将“对象”通过序列化保存
        testWrite3();
        // 将序列化的“对象”读出来
        testRead3();
        /*
        * 程序说明：

    “序列化不会自动保存static和transient变量”，因此我们若要保存它们，则需要通过writeObject()和readObject()去手动读写。
    (01) 通过writeObject()方法，写入要保存的变量。writeObject的原始定义是在ObjectOutputStream.java中，我们按照如下示例覆盖即可：

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();// 使定制的writeObject()方法可以利用自动序列化中内置的逻辑。
        out.writeInt(ival);      // 若要保存“int类型的值”，则使用writeInt()
        out.writeObject(obj);    // 若要保存“Object对象”，则使用writeObject()
    }
    (02) 通过readObject()方法，读取之前保存的变量。readObject的原始定义是在ObjectInputStream.java中，我们按照如下示例覆盖即可：

    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{
        in.defaultReadObject();       // 使定制的readObject()方法可以利用自动序列化中内置的逻辑。
        int ival = in.readInt();      // 若要读取“int类型的值”，则使用readInt()
        Object obj = in.readObject(); // 若要读取“Object对象”，则使用readObject()
        * */
    }
    /**
     * 将Box对象通过序列化，保存到文件中
     */
    private static void testWrite3() {
        try {
            // 获取文件TMP_FILE对应的对象输出流。
            // ObjectOutputStream中，只能写入“基本数据”或“支持序列化的对象”
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(TMP_FILE));
            // 创建Box对象，Box实现了Serializable序列化接口
            Box3 box = new Box3("desk", 80, 48);
            // 将box对象写入到对象输出流out中，即相当于将对象保存到文件TMP_FILE中
            out.writeObject(box);
            // 打印“Box对象”
            System.out.println("testWrite box: " + box);
            // 修改box的值
            box = new Box3("room", 100, 50);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 从文件中读取出“序列化的Box对象”
     */
    private static void testRead3() {
        try {
            // 获取文件TMP_FILE对应的对象输入流。
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(TMP_FILE));
            // 从对象输入流中，读取先前保存的box对象。
            Box3 box = (Box3) in.readObject();
            // 打印“Box对象”
            System.out.println("testRead  box: " + box);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * Box类“支持序列化”。因为Box实现了Serializable接口。
 *
 * 实际上，一个类只需要实现Serializable即可实现序列化，而不需要实现任何函数。
 */
class Box2 implements Serializable {
    private static int width;
    private transient int height;
    private String name;

    public Box2(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "["+name+": ("+width+", "+height+") ]";
    }
}

/**
 * Box类“支持序列化”。因为Box实现了Serializable接口。
 *
 * 实际上，一个类只需要实现Serializable即可实现序列化，而不需要实现任何函数。
 */
class Box3 implements Serializable {
    private static int width;
    private transient int height;
    private String name;

    public Box3(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();//使定制的writeObject()方法可以利用自动序列化中内置的逻辑。
        out.writeInt(height);
        out.writeInt(width);
        //System.out.println("Box--writeObject width="+width+", height="+height);
    }

    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{
        in.defaultReadObject();//defaultReadObject()补充自动序列化
        height = in.readInt();
        width = in.readInt();
        //System.out.println("Box---readObject width="+width+", height="+height);
    }

    @Override
    public String toString() {
        return "["+name+": ("+width+", "+height+") ]";
    }
}