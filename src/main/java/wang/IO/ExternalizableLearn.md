如果一个类要完全负责自己的序列化，则实现Externalizable接口，而不是Serializable接口。

Externalizable接口定义包括两个方法writeExternal()与readExternal()。需要注意的是：声明类实现Externalizable接口会有重大的安全风险。writeExternal()与readExternal()方法声明为public，恶意类可以用这些方法读取和写入对象数据。如果对象包含敏感信息，则要格外小心。

下面，我们修改之前的SerialTest1.java测试程序；将其中的Box由“实现Serializable接口” 改为 “实现Externalizable接口”。