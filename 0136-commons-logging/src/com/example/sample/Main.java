package com.example.sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/// Commons Logging的特色是，它可以挂接不同的日志系统，并通过配置文件指定挂接的日志系统。
///
/// 默认情况下，Commons Loggin自动搜索并使用Log4j(Log4j是另一个流行的日志系统)，如果没有找到Log4j，再使用JDK Logging。
///
/// Commons Logging定义了6个日志级别:
/// - FATAL
/// - ERROR
/// - WARNING
/// - INFO，默认级别，因此INFO级别以下的日志，不会被打印出来。
/// - DEBUG
/// - TRACE
public class Main {
    /// 使用Commons Logging时，如果在静态方法中引用 Log，通常直接定义一个静态类型变量
    static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        // 使用Commons Logging只需要和两个类打交道，并且只有两步:
        // 第一步，通过 LogFactory 获取 Log 类的实例;
        // 第二步，使用 Log 实例的方法打日志。
//        Log log = LogFactory.getLog(Main.class);
        log.info("start...");
        log.debug("DEBUG");
        log.warn("end.");
        /*
        运行上述代码，肯定会得到编译错误。
        为Commons Logging是一个第三方提供的库，必须先下载下来：
        https://commons.apache.org/proper/commons-logging/download_logging.cgi

        在终端把工作目录切换到 0136-commons-logging，
        然后用 javac 编译 Main.java，编译的时候要指定 classpath，不然编译器找不到我们引用的 org.apache.commons.logging 包：

        $ javac -cp lib/commons-logging-1.3.5.jar src/com/example/sample/Main.java

        如果编译成功，那么当前目录下就会多出一个 Main.class 文件:
        现在可以执行这个 Main.class ，使用 java 命令，也必须指定 classpath ，命令如下:

        $ java -cp lib/commons-logging-1.3.5.jar:src com.example.sample.Main
        */

        /// 引入Log4j，与使用 Commons Logging 一样，只是把 Log4j 相关文件加入 classpath。
        ///
        /// 用 javac 编译 Main.java：
        /// javac -cp "$(echo lib/*.jar | tr ' ' ':'):src/log4j2.xml" src/com/example/sample/Main.java
        /// ⚠️注意，未来发行版的 javac 可能会禁用批注处理
        ///
        /// 执行这个 Main.class：
        /// java -cp "$(echo lib/*.jar | tr ' ' ':'):src/log4j2.xml:src" com.example.sample.Main
        ///
        /// 最佳实践
        ///
        /// 在开发阶段，始终使用Commons Logging接口来写入日志，并且开发阶段无需引入Log4j。如果要把日志写入文件，
        /// 只需要把正确的配置文件和Log4j相关的jar包放入 classpath，就可以自动把日志切换成使用Log4j写入，无需修改任何代码。
    }
}

class Person {
    /// 在实例方法中引用 Log ，通常定义一个实例变量:
    ///
    /// 虽然也可以用 LogFactory.getLog(Person.class)，但是现在方式有个非常大的好处，就是子类可以直接使用该 log 实例
    protected final Log log =  LogFactory.getLog(getClass()); // LogFactory.getLog(Person.class)

    void foo() {
        // 此外，Commons Logging的日志方法，例如 ，除了标准的 info(String) 外，
        // 还提供了一个非常有用的重载方法 info(String, Throwable) ，这使得记录异常更加简单
        try {
            // ...
        } catch (Exception e) {
            log.error("got exception!", e);
        }
    }
}

class Student extends Person {
    void bar() {
        // 由于Java类的动态特性，子类获取的 log 字段实际上相当于 LogFactory.getLog(Student.class)，但却是从父类继承而来，并且无需改动代码。
        log.info("bar");
    }
}

/// 36.2、Log4j
///
/// 前面介绍了Commons Logging，可以作为“日志接口”来使用。而真正的“日志实现”可以使用 Log4j。
/// Log4j是一种非常流行的日志框架，是一个组件化设计的日志系统。
/// log.info("User signed in.");
///  │
///  │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
///  ├──▶│ Appender │───▶│  Filter  │───▶│  Layout  │───▶│ Console  │
///  │   └──────────┘    └──────────┘    └──────────┘    └──────────┘
///  │
///  │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
///  ├──▶│ Appender │───▶│  Filter  │───▶│  Layout  │───▶│   File   │
///  │   └──────────┘    └──────────┘    └──────────┘    └──────────┘
///  │
///  │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
///  └──▶│ Appender │───▶│  Filter  │───▶│  Layout  │───▶│  Socket  │
///      └──────────┘    └──────────┘    └──────────┘    └──────────┘
/// 当我们使用Log4j输出一条日志时，Log4j自动通过不同的Appender把同一条日志输出到不同的目的地。
/// - console：输出到屏幕
/// - file：输出到文件
/// - socket：通过网络输出到远程计算机
/// - jdbc：输出到数据库
///
/// 在输出日志的过程中，通过Filter来过滤哪些log需要被输出，哪些log不需要被输出。例如，仅输出 ERROR 级别的日志。
/// 最后，通过Layout来格式化日志信息，例如，自动添加日期、时间、方法名称等信息。
///
/// 上述结构虽然复杂，但我们在实际使用的时候，并不需要关心Log4j的API，而是通过配置文件来配置它。
/// 以XML配置为例，使用Log4j的时候，我们把一个 log4j2.xml的文件放到 classpath 下就可以让 Log4j 读取配置文件并按照我们的配置来输出日志。
///
/// 虽然配置Log4j比较繁琐，但一旦配置完成，使用起来就非常方便。对上面的配置文件，凡是 INFO 级别的日志，会自动输出到屏幕，而 ERROR 级别的日志，不但会输出到屏幕，
/// 还会同时输出到文件。并且，一旦日志文件达到指定大小(1MB)，Log4j就会自动切割新的日志文件，并最多保留10份。
///
/// 有了配置文件还不够，Log4j是一个第三方库，我们需要联网下载
/// https://logging.apache.org/log4j/2.x/download.html
///
/// 解压后，把以下 3个jar包放到 classpath 中:
/// - log4j-api-2.25.3.jar
/// - log4j-core-2.25.3.jar
/// - log4j-jcl-2.25.3.jar
///
/// 因为Commons Logging会自动发现并使用Log4j，所以，把上一节下载的 commons-logging-1.3.5.jar 也放到 classpath 中。
/// 要打印日志，只需要按Commons Logging的写法写，不需要改动任何代码，就可以得到Log4j的日志输出。