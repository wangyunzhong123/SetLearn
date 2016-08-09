java 管道介绍
在java中，PipedOutputStream和PipedInputStream分别是管道输出流和管道输入流。
它们的作用是让多线程可以通过管道进行线程间的通讯。在使用管道通信时，必须将PipedOutputStream和PipedInputStream配套使用。
使用管道通信时，大致的流程是：我们在线程A中向PipedOutputStream中写入数据，这些数据会自动的发送到与PipedOutputStream对应
 的PipedInputStream中，进而存储在PipedInputStream的缓冲中；此时，线程B通过读取PipedInputStream中的数据。就可以实现，线程A和线程B的通信。
 
 
 运行结果：
 this is a short message
 
 说明：
 
 (01) 
 in.connect(out); 
 将“管道输入流”和“管道输出流”关联起来。查看PipedOutputStream.java和PipedInputStream.java中connect()的源码；我们知道 out.connect(in); 等价于 in.connect(out);
 (02)
 t1.start(); // 启动“Sender”线程
 t2.start(); // 启动“Receiver”线程
 先查看Sender.java的源码，线程启动后执行run()函数；在Sender.java的run()中，调用writeShortMessage();
 writeShortMessage();的作用就是向“管道输出流”中写入数据"this is a short message" ；这条数据会被“管道输入流”接收到。下面看看这是如何实现的。
 先看write(byte b[])的源码，在OutputStream.java中定义。PipedOutputStream.java继承于OutputStream.java；OutputStream.java中write(byte b[])的源码如下：
 
 public void write(byte b[]) throws IOException {
     write(b, 0, b.length);
 }
 实际上write(byte b[])是调用的PipedOutputStream.java中的write(byte b[], int off, int len)函数。查看write(byte b[], int off, int len)的源码，我们发现：它会调用 sink.receive(b, off, len); 进一步查看receive(byte b[], int off, int len)的定义，我们知道sink.receive(b, off, len)的作用就是：将“管道输出流”中的数据保存到“管道输入流”的缓冲中。而“管道输入流”的缓冲区buffer的默认大小是1024个字节。
 
 至此，我们知道：t1.start()启动Sender线程，而Sender线程会将数据"this is a short message"写入到“管道输出流”；而“管道输出流”又会将该数据传输给“管道输入流”，即而保存在“管道输入流”的缓冲中。
 
 
 接下来，我们看看“用户如何从‘管道输入流’的缓冲中读取数据”。这实际上就是Receiver线程的动作。
 t2.start() 会启动Receiver线程，从而执行Receiver.java的run()函数。查看Receiver.java的源码，我们知道run()调用了readMessageOnce()。
 而readMessageOnce()就是调用in.read(buf)从“管道输入流in”中读取数据，并保存到buf中。
 通过上面的分析，我们已经知道“管道输入流in”的缓冲中的数据是"this is a short message"；因此，buf的数据就是"this is a short message"。
 