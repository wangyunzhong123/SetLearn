ByteArrayInputStream 介绍
ByteArrayInputStream 是字节数组输入流。它继承于InputStream。
它包含一个内部缓冲区，该缓冲区包含从流中读取的字节；通俗点说，它的内部缓冲区就是一个字节数组，而ByteArrayInputStream本质就是通过字节数组来实现的。
我们都知道，InputStream通过read()向外提供接口，供它们来读取字节数据；而ByteArrayInputStream 的内部额外的定义了一个计数器，它被用来跟踪 read() 方法要读取的下一个字节。


InputStream 函数列表

复制代码
// 构造函数
InputStream()

             int     available()
             void    close()
             void    mark(int readlimit)
             boolean markSupported()
             int     read(byte[] buffer)
abstract     int     read()
             int     read(byte[] buffer, int offset, int length)
synchronized void    reset()
             long    skip(long byteCount)
复制代码
ByteArrayInputStream 函数列表

复制代码
// 构造函数
ByteArrayInputStream(byte[] buf)
ByteArrayInputStream(byte[] buf, int offset, int length)

synchronized int         available()
             void        close()
synchronized void        mark(int readlimit)
             boolean     markSupported()
synchronized int         read()
synchronized int         read(byte[] buffer, int offset, int length)
synchronized void        reset()
synchronized long        skip(long byteCount)
复制代码