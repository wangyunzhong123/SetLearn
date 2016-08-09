1. 序列化是的作用和用途
序列化，就是为了保存对象的状态；而与之对应的反序列化，则可以把保存的对象状态再读出来。
简言之：序列化/反序列化，是Java提供一种专门用于的保存/恢复对象状态的机制。

一般在以下几种情况下，我们可能会用到序列化：
a）当你想把的内存中的对象状态保存到一个文件中或者数据库中时候； 
b）当你想用套接字在网络上传送对象的时候； 
c）当你想通过RMI传输对象的时候。

源码说明：

(01) 程序的作用很简单，就是演示：先将Box对象，通过对象输出流保存到文件中；之后，再通过对象输入流，将文件中保存的Box对象读取出来。

(02) Box类说明。Box是我们自定义的演示类，它被用于序列化的读写。Box实现了Serialable接口，因此它支持序列化操作；即，Box支持通过ObjectOutputStream去写入到输出流中，并且支持通过ObjectInputStream从输入流中读取出来。

(03) testWrite()函数说明。testWrite()的作用就是，新建一个Box对象，然后将该Box对象写入到文件中。
       首先，新建文件TMP_FILE的文件输出流对象(即FileOutputStream对象)，再创建该文件输出流的对象输出流(即ObjectOutputStream对象)。
       a) 关于FileInputStream和FileOutputStream的内容，可以参考“java io系列07之 FileInputStream和FileOutputStream”。
       b) 关于ObjectInputStream和ObjectOutputStream的的更多知识，可以参考“java io系列05之 ObjectInputStream 和 ObjectOutputStream”
       然后，新建Box对象。
       最后，通过out.writeObject(box) 将box写入到对象输出流中。实际上，相当于将box写入到文件TMP_FILE中。

(04) testRead()函数说明。testRead()的作用就是，从文件中读出Box对象。
       首先，新建文件TMP_FILE的文件输入流对象(即FileInputStream对象)，再创建该文件输入流的对象输入流(即ObjectInputStream对象)。
       然后，通过in.readObject() 从对象输入流中读取出Box对象。实际上，相当于从文件TMP_FILE中读取Box对象。

通过上面的示例，我们知道：我们可以自定义类，让它支持序列化(即实现Serializable接口)，从而能支持对象的保存/恢复。
若要支持序列化，除了“自定义实现Serializable接口的类”之外；java的“基本类型”和“java自带的实现了Serializable接口的类”，都支持序列化。我们通过下面的示例去查看一下。
