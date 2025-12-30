/// 这是模块的描述文件, hello.world 是模块名称, 它的命名规范与包一致
module hello.world {
    exports com.example.sample; // 如果外部代码想要访问我们 hello.world 模块中的类，必须先导出。

    requires java.base; // 可不写，任何模块都会自动引入 java.base
    requires java.xml;  // 引用的其他模块名
}