java 时间架构图

Java 的Calendar, Date和DateFormat的关系图如下：

说明：

(01) milliseconds 表示毫秒。

       milliseconds = “实际时间” - “1970-01-01 00:00:00”。Calendar 和 Date依赖于 milliseconds，从而表示时间。

(02) Calendar表示日期/时间。

       它是一个抽象类，依赖于milliseconds。GregorianCalendar是Calendar的子类，通常我们通过Calendar.getInstance() 获取Calendar实例时，实际上返回的是 GregorianCalendar 对象。

       Calendar和Locale关联，而Locale代表区域；Locale的值不同时，Calendar的日期/时间也不同。

       Calendar和TimeZone关联，而TimeZone代表时区；不同的时区，Calendar的日期/时间也不同。

(03) Date 表示日期/时间。

       它也依赖于 milliseconds实现。

       在 JDK 1.1 之前，通常是通过Data操作“年月日时分秒”。不过，由于Date的相关 API 不易于实现国际化。从 JDK 1.1 开始，应该使用 Calendar 类来操作“年月日时分秒”，同时可以通过 DateFormat 类来格式化和解析日期字符串。Date 中的相应方法已废弃。

(04) DateFormat是格式化/解析“日期/时间”的工具类。

       它是Date的格式化工具，它能帮助我们格式化Date，进而将Date转换成我们想要的String字符串供我们使用。

       它是一个抽象类。通常，我们通过getInstance(), getDateInstance()和getDateTimeInstance() 等获取DateFormat实例时；实际上是返回的SimpleDateFormat对象。
       
## Calendar 介绍 

### Calendar 定义

public abstract class Calendar implements Serializable, Cloneable, Comparable<Calendar> {}
Calendar 是一个抽象类。

它的实现，采用了设计模式中的工厂方法。表现在：当我们获取Calendar实例时，Calendar会根据传入的参数来返回相应的Calendar对象。获取Calendar实例，有以下两种方式：
1) 当我们通过 Calendar.getInstance() 获取日历时，默认的是返回的一个GregorianCalendar对象。
    GregorianCalendar是Calendar的一个实现类，它提供了世界上大多数国家/地区使用的标准日历系统。
2) 当我们通过 Calendar.getInstance(TimeZone timezone, Locale locale) 或 Calendar.getInstance(TimeZone timezone) 或 Calendar.getInstance(Locale locale)获取日历时，是返回“对应时区(zone) 或 地区(local)等所使用的日历”。
    例如，若是日本，则返回JapaneseImperialCalendar对象。

参考如下代码：


复制代码
 1 public static Calendar getInstance()
 2 {
 3     // 调用createCalendar()创建日历
 4     Calendar cal = createCalendar(TimeZone.getDefaultRef(), Locale.getDefault());
 5     cal.sharedZone = true;
 6     return cal;
 7 }
 8 
 9 
10 public static Calendar getInstance(TimeZone zone)
11 {
12     // 调用createCalendar()创建日历
13     return createCalendar(zone, Locale.getDefault());
14 }
15 
16 
17 public static Calendar getInstance(Locale aLocale) {
18     // 调用createCalendar()创建日历
19     Calendar cal = createCalendar(TimeZone.getDefaultRef(), aLocale);
20     cal.sharedZone = true;
21     return cal;
22 }
23 
24 public static Calendar getInstance(TimeZone zone,
25                    Locale aLocale)
26 {
27     // 调用createCalendar()创建日历
28     return createCalendar(zone, aLocale);
29 }
30 
31 private static Calendar createCalendar(TimeZone zone,
32                    Locale aLocale)
33 {
34     // (01) 若地区是“th”，则返回BuddhistCalendar对象
35     // (02) 若地区是“JP”，则返回JapaneseImperialCalendar对象
36     if ("th".equals(aLocale.getLanguage())
37         && ("TH".equals(aLocale.getCountry()))) {
38         return new sun.util.BuddhistCalendar(zone, aLocale);
39     } else if ("JP".equals(aLocale.getVariant())
40        && "JP".equals(aLocale.getCountry())
41        && "ja".equals(aLocale.getLanguage())) {
42         return new JapaneseImperialCalendar(zone, aLocale);
43     }        
44 
45     // (03) 否则，返回GregorianCalendar对象
46     return new GregorianCalendar(zone, aLocale);    
47 }
复制代码
当我们获取Calendar实例之后，就可以通过Calendar提供的一些列方法来操作日历。