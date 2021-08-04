流的操作规律：
之所以要弄清楚这个规律，是因为流对象太多，开发时不知道用哪个对象合适。
想要知道开发时用到哪些对象。只要通过四个明确即可。
1，明确源和目的(汇)
    源：InputStream  Reader
    目的：OutputStream  Writer
2，明确数据是否是纯文本数据。
    源：是纯文本：Reader
        否：InputStream
    目的：是纯文本 Writer
        否：OutputStream
到这里，就可以明确需求中具体要使用哪个体系。
3，明确具体的设备。
    源设备：
        硬盘：File
        键盘：System.in
        内存：数组
        网络：Socket流
    目的设备：
        硬盘：File
        控制台：System.out
        内存：数组
        网络：Socket流
4，是否需要其他额外功能。
    1，是否需要高效(缓冲区);
        是，就加上buffer.
    2，转换。