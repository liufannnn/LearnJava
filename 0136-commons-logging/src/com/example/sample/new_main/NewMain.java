package com.example.sample.new_main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/// 36.3、SLF4J 和 Logback
///
/// 分别下载：
/// - [SLF4J](https://www.slf4j.org/download.html)
/// - [Logback](https://logback.qos.ch/download.html)
///
/// 用 javac 编译 NewMain.java：
/// javac -cp "$(echo lib/new_lib/*.jar | tr ' ' ':'):src/logback.xml" src/com/example/sample/new_main/NewMain.java
///
/// 执行这个 NewMain.class：
/// java -cp "$(echo lib/new_lib/*.jar | tr ' ' ':'):src/logback.xml:src" com.example.sample.new_main.NewMain
public class NewMain {
    static final Logger logger = LoggerFactory.getLogger(NewMain.class);

    public static void main(String[] args) {
        int score = 99;
        logger.info("start... score: {}", score);
        logger.debug("DEBUG");
        logger.warn("end. score: {}", score);
    }
}
