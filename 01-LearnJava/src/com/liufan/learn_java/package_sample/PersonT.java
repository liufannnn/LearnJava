/**
 * 在定义 class 的时候，我们需要在第一行声明这个 class 属于哪个包(包名)
 * <p>
 * 没有定义包名的 class，它使用的是默认包，非常容易引起名字冲突，因此，不推荐不写包名的做法。
 * <p>
 * 包没有父子关系。java.util 和 java.util.zip 是不同的包，两者没有任何继承关系。
 */
package com.liufan.learn_java.package_sample;

/*
 在现实中，如果小明写了一个 Person 类，小红也写了一个 Person 类，
 现在，小白既想用小明的 Person ，也想用小红的 Person ，怎么办?
 如果小军写了一个 Arrays 类，恰好JDK也自带了一个 Arrays 类，如何解决类名冲突?
*/

/**
 * 在Java中，我们使用 package 来解决名字冲突。
 * Java定义了一种名字空间，称之为包: package。一个类总是属于某个包.类名。
 * <p>
 * 例如这里的 PersonT 存放在 package_sample 下面，完整类名是 com.liufan.learn_java.package_sample.PersonT
 */
public class PersonT {
    /// package, 包作用域
    ///
    /// 位于同一个包的类，可以访问包作用域的字段和方法。不用 public、 protected、 private 修饰的字段和方法就是包作用域。
    void hello() {
        System.out.println("Hello");
    }
}