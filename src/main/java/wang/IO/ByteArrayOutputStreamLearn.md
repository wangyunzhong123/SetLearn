ByteArrayOutputStream 介绍
ByteArrayOutputStream 是字节数组输出流。它继承于OutputStream。
ByteArrayOutputStream 中的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。可使用 toByteArray() 和 toString() 获取数据。


OutputStream 函数列表

我们来看看ByteArrayOutputStream的父类OutputStream的函数接口。

复制代码
// 构造函数
OutputStream()

         void    close()
         void    flush()
         void    write(byte[] buffer, int offset, int count)
         void    write(byte[] buffer)
abstract void    write(int oneByte)
复制代码
ByteArrayOutputStream 函数列表

复制代码
// 构造函数
ByteArrayOutputStream()
ByteArrayOutputStream(int size)

             void    close()
synchronized void    reset()
             int     size()
synchronized byte[]  toByteArray()
             String  toString(int hibyte)
             String  toString(String charsetName)
             String  toString()
synchronized void    write(byte[] buffer, int offset, int len)
synchronized void    write(int oneByte)
synchronized void    writeTo(OutputStream out)
复制代码