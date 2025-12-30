package com.example.sample;

import javax.xml.XMLConstants;

/**
 * 从Java 9开始，引入模块，主要是为了解决“依赖”这个问题。如果 a.jar 必须依赖另一个 b.jar 才能运行，
 * 那我们应该给 a.jar 加点说明啥的，让程序在编译和运行的时候能自动定位到 b.jar ，这种自带“依赖关系”的class容器就是模块。
 * 为了表明Java模块化的决心，从Java 9开始，原有的Java标准库已经由一个单一巨大的 rt.jar 分拆成了几十个模块，
 * 这些模块以.jmod 扩展名标识，可以在 $JAVA_HOME/jmods 目录下找到它们: java.base.jmod、java.desktop.jmod等。
 * <p>
 * 模块之间的依赖关系已经被写入到模块内的 module-info.class 文件了。 所有的模块都直接或间接地依赖 java.base 模块，
 * 只有 java.base 模块不依赖任何模块，它可以被看作是“根模块”，好比所有的类都是从 Object 直接或间接继承而来。
 */
public class Main {
    public static void main(String[] args) {
        Greeting g = new Greeting();
        System.out.println(g.sayHello(XMLConstants.XML_NS_PREFIX));
    }
}
/*
 1、在终端把工作目录切换到 0130-oop-module，在当前目录下编译所有的 .java 文件，并存放到 bin 目录下

    $ javac -d bin src/module-info.java src/com/example/sample/*.java

 2、把 bin 目录下所有 class 文件打包成 jar，在打包的时候注意传入 --main--class 参数，让它能自己定位 main 方法说在类

    $ jar --create --file hello.jar --main-class com.example.sample.Main -C bin .

 3、在当前目录下得到了 hello.jar，它和普通jar包并无区别，可以直接使用命令 java -jar hello.jar 来运行它。
    但是我们的目标是创建模块，所以继续使用JDK自带的 jmod 命令把一个jar包转换成模块:

    $ jmod create --class-path hello.jar hello.jmod

    在 macOS 中可能会出现 zsh: command not found: jmod 错误，https://blog.zzhow.com/article/Tips3

 4、运行模块

    $ java --module-path hello.jmod --module hello.world

    结果是一个错误：
    Error occurred during initialization of boot layer
    java.lang.module.FindException: JMOD format not supported at execution time: hello.jmod
    原因是.jmod不能放入--module-path。换成.jar就没问题。

    $ java --module-path hello.jar --module hello.world

5、前面讲了，为了支持模块化，Java9首先带头把自己的一个巨大无比的 rt.jar 拆成了几十个.jmod 模块，原因就是，运行Java程序的时候，
   实际上我们用到的JDK模块，并没有那么多。不需要的模块，完全可以删除。过去发布一个Java应用程序，要运行它，必须下载一个完整的JRE，
   再运行jar包。而完整的JRE块头很大，有100多M。怎么给JRE瘦身呢?

   现在，JRE自身的标准库已经分拆成了模块，只需要带上程序用到的模块，其他的模块就可以被裁剪掉。怎么裁剪JRE呢?
   并不是说把系统安装的JRE给删掉部分模块，而是“复制”一份JRE，但只带上用到的模块。为此JDK提供了 jlink 命令来干这件事。命令如下:

   $ jlink --module-path hello.jmod --add-modules java.base,java.xml,hello.world --output jre/
   ⚠️注意，Android Studio 自带的 JBR 没有 jmods 目录，所以使用标准java环境变量执行 jlink 命令裁剪 JRE。=> Trae IDE 得出的结论

6、这样就打包出了一个JRE，试试直接运行

   $ jre/bin/java --module hello.world

   要分发我们自己的Java应用程序，只需要把这个 jre 目录打个包给对方发过去，对方直接运行上述命令即可，既不用下载安装JDK，
   也不用知道如何配置我们自己的模块，极大地方便了分发和部署。
 */
