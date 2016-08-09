package wang;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by tianxi on 8/9/16.
 */

/*
* java 反射机制。通俗来讲呢，就是在运行状态中，我们可以根据“类的部分已经的信息”来还原“类的全部的信息”。这里“类的部分已经的信息”，可以是“类名”或“类的对象”等信息。“类的全部信息”就是指“类的属性，方法，继承关系和Annotation注解”等内容。

举个简单的例子：假设对于类ReflectionTest.java，我们知道的唯一信息是它的类名是“com.skywang.Reflection”。这时，我们想要知道ReflectionTest.java的其它信息(比如它的构造函数，它的成员变量等等)，要怎么办呢？
这就需要用到“反射”。通过反射，我们可以解析出ReflectionTest.java的完整信息，包括它的构造函数，成员变量，继承关系等等。

在了解了“java 反射机制”的概念之后，接下来思考一个问题：如何根据类的类名，来获取类的完整信息呢？

这个过程主要分为两步：
第1步：根据“类名”来获取对应类的Class对象。
第2步：通过Class对象的函数接口，来读取“类的构造函数，成员变量”等信息。
下面，我们根据示例来加深对这个概念的理解。示例如下(Demo1.java):

复制代码
package com.skywang.test;

import java.lang.Class;

public class Demo1 {

    public static void main(String[] args) {

        try {
            // 根据“类名”获取 对应的Class对象
            Class<?> cls = Class.forName("com.skywang.test.Person");

            // 新建对象。newInstance()会调用类不带参数的构造函数
            Object obj = cls.newInstance();

            System.out.println("cls="+cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Person {
    public Person() {
        System.out.println("create Person");
    }
}
复制代码
运行结果：
create Person
cls=class com.skywang.test.Person

说明：
(01) Person类的完整包名是"com.skywang.test.Person"。而 Class.forName("com.skywang.test.Person"); 这一句的作用是，就是根据Person的包名来获取Person的Class对象。
(02) 接着，我们调用Class对象的newInstance()方法，创建Person对象。
* */
public class ReflectionLearn {

    /*
    * 1 获取Class对象的方法

        我这里总结了4种常用的“获取Class对象”的方法：
        方法1：Class.forName("类名字符串") （注意：类名字符串必须是全称，包名+类名）
        方法2：类名.class
        方法3：实例对象.getClass()
        方法4："类名字符串".getClass()

        下面，我们通过示例演示这4种方法。示例如下:
    * */
    public static void main(String[] args) {

        try {
            // 方法1：Class.forName("类名字符串")  （注意：类名字符串必须是全称，包名+类名）
            //Class cls1 = Class.forName("com.skywang.test.Person");
            Class<?> cls1 = Class.forName("wang.Person");
            //Class<Person> cls1 = Class.forName("com.skywang.test.Person");

            // 方法2：类名.class
            Class cls2 = Person.class;

            // 方法3：实例对象.getClass()
            Person person = new Person();
            Class cls3 = person.getClass();

            // 方法4："类名字符串".getClass()
            String str = "com.skywang.test.Person";
            Class cls4 = str.getClass();

            System.out.printf("cls1=%s, cls2=%s, cls3=%s, cls4=%s\n", cls1, cls2, cls3, cls4);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
    // 人
    class Person {
        private Gender gender;  // 性别
        protected int age;        // 年龄
        public String name;    // 姓名

        public Person() {
            this.name = "unknown";
            this.age = 0;
            this.gender = Gender.FEMALE;
            System.out.println("call--\"private Person()\"");
        }

        protected Person(String name) {
            this.name = name;
            this.age = 0;
            this.gender = Gender.FEMALE;
            System.out.println("call--\"protected Person(String name)\"");
        }

        public Person(String name, int age, Gender gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            System.out.println("call--\"public Person(String name, int age, Gender gender)\"");
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
            this.gender = Gender.FEMALE;
            //内部类在构造方法中
            class InnerA {
            }
            // 获取InnerA的Class对象
            Class cls = InnerA.class;

            // 获取“封闭该内部类(InnerA)”的构造方法
            Constructor cst = cls.getEnclosingConstructor();

            System.out.println("call--\"public Person(String name, int age)\" cst=" + cst);
        }

        // 获取”姓名“。权限是 public
        public String getName() {
            return name;
        }
        // 设置”姓名“。权限是 public
        public void setName(String name) {
            this.name = name;
        }
        // 获取”年龄“。权限是 protected
        protected int getAge() {
            return age;
        }
        // 设置”年龄“。权限是 protected
        protected void setAge(int age) {
            this.age = age;
        }
        // 获取“性别”。权限是 private
        private Gender getGender() {
            return gender;
        }
        // 设置“性别”。权限是 private
        private void setGender(Gender gender) {
            this.gender = gender;
        }


        // getInner() 中有内部类InnerB，用来测试getEnclosingMethod()
        public void getInner() {
            // 内部类
            class InnerB{
            }
            // 获取InnerB的Class对象
            Class cls = InnerB.class;

            // 获取“封闭该内部类(InnerB)”的构造方法
            Method cst = cls.getEnclosingMethod();

            System.out.println("call--\"getInner()\" cst="+cst);
        }

        @Override
        public String toString() {
            return "(" + name + ", " + age + ", " + gender + ")";
        }
    }

    // 枚举类型。表示“性别”
    enum Gender {
        MALE, FEMALE
    }

