package com.liufan.learn_java;

import com.liufan.learn_java.package_sample.PersonT;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.logging.Logger;

/// 一个Java源文件可以包含多个类的定义，但只能定义一个public类，且public类名必须与文件名一致。
/// 如果要定义多个public类，必须拆到多个Java源文件中。
///
/// 在Java中，没有明确写 extends 的类，编译器会自动加上 extends Object。
/// 所以除了 Object，任何类都会继承自某个类。
///
/// Java只允许一个class继承自一个类，因此一个类有且仅有一个父类。只有 Object 特殊，它没有父类。
public class Main {
    public static void main(String[] args) {
        System.out.println("惯例：Hello World!");
        intMethod();
    }

    // 1、变量
    // 在Java中，变量类型分为两种:基本类型和引用类型。

    // <editor-fold defaultstate="collapsed" desc="1.1、基本类型">
    /**
     * 整型
     * <p>
     * 计算机内存的最小存储单元是字节(byte)，一个字节8个比特(bit)。二进制范围00000000 ~ 11111111，换算成十进制是0 ~ 255，换算成十六进制是00 ~ ff。
     * <p>
     * Java只定义了带符号的整型，因此最高位的bit表示符号位(0表示正数，1表示负数)。
     * <p>
     * @byte  字节数1，值范围-128 ~ 127
     * @short 字节数2，值范围-32768 ~ 32767
     * @int   字节数4，值范围-2147483648 ~ 2147483647，默认值为0
     * @long  字节数8，值范围-9223372036854775808 ~ 9223372036854775807
     * @值范围公式 -2^(二进制位数-1) ~ 2^(二进制位数-1)-1，二进制位数：字节数 * 8
     */
    static void intMethod() {
        int i1 = 2_000_000_000; // 加下划线更容易识别
        int i2 = -2_000_000_000;
        int i3 = 0b1000000000; // 二进制表示的512
        int i4 = 0xff0000; // 十六进制表示的16711680

        long n1 = 9000000000000000000L; // long型的结尾需要加L
        long n2 = 900; // 没有加L，此处900为int，但int类型可以赋值给long
        // int i6 = 900L; // 错误:不能把long型赋值给int
    }

    /**
     * 浮点型
     * <p>
     * 浮点类型的数就是小数，因为小数用科学计数法表示的时候，小数点是可以“浮动”的，如1234.5可 以表示成12.345x10^2，也可以表示成1.2345x10^3，所以称为浮点数。
     * <p>
     * @float  字节数4，值范围Float.MIN_VALUE  ~ Float.MAX_VALUE
     * @double 字节数8，值范围Double.MIN_VALUE ~ Double.MAX_VALUE
     */
    static void floatMethod() {
        float f1 = 3.14f;
        float f2 = 3.14e38f; // 科学计数法表示的3.14x10^38
        // float f3 = 1.0; // 错误:不带f结尾的是double类型，不能赋值给float

        double d1 = 1.79e308;
        double d2 = -1.79e308;
        double d3 = 4.9e-324; // 科学计数法表示的4.9x10^-324
    }

    /**
     * 布尔类型
     * <p>
     * 布尔类型 boolean 只有 true 和 false 两个值，默认值为false
     * <p>
     * Java语言对布尔类型的存储并没有做规定，因为理论上存储布尔类型只需要1bit，但是通常JVM内部会把boolean表示为4字节整数。
     */
    static void booleanMethod() {}

    /**
     * 字符类型
     * <p>
     * 字符类型char表示一个字符。Java的char类型除了可表示标准的ASCII外，还可以表示一个Unicode字符
     */
    static void charMethod() {
        char a = 'a';
        char zh = '中';
        System.out.println(a + zh);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="1.2、引用类型">
    /**
     * 引用类型，所有 class 和 interface 类型
     * <p>
     * 引用类型的变量类似于C语言的指针，它内部存储一个“地址”，指向某个对象在内存的位置。
     * 引用类型可以赋值为 null，表示空，但基本类型不能赋值为 null。
     * 引用类型不能用 == 比较，只能用 equals()
     */
    static void classFunc() {
        String s = "惯例：Hello World!";
        System.out.println(s);

        /// 1.2.1、空值null
        /// 引用类型的变量可以指向一个空值 null ，它表示不存在，即该变量不指向任何对象。
        String s1 = null; // s1是null
        String s2 = s1; // s2也是null
        String s3 = ""; // s3指向空字符串，不是null
        // 注意要区分空值 null 和空字符串 "" ，空字符串是一个有效的字符串对象，它不等于 null 。
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="1.2.2、包装类型(Integer、Float、Boolean等)">

    static void wrapperClassFunc() {
        /// 1.2.2、包装类型(Wrapper Class)
        ///
        /// public final class Integer {
        ///     private final int value; // 所有包装类型都是不变类
        /// }
        ///
        /// 把一个基本类型包装成引用类型，比如，想要把 int 包装成引用类型 Integer
        /// 实际上，因为包装类型非常有用，Java核心库为每种基本类型都提供了对应的包装类型：
        /// boolean java.lang.Boolean
        /// byte    java.lang.Byte
        /// short   java.lang.Short
        /// int     java.lang.Integer
        /// long    java.lang.Long
        /// float   java.lang.Float
        /// double  java.lang.Double
        /// char    java.lang.Character
        // int 和 Integer 可以相互转换
        int i = 100;
        Integer n = Integer.valueOf(i);
        int x = n.intValue();

        /// 自动装箱(Auto Boxing)，int 变为 Integer 的赋值写法
        Integer n1 = 100; // 编译器自动使用Integer.valueOf(int)
        /// 自动拆箱(Auto Unboxing)，Integer 变为 int 的赋值写法
        int x1 = n1; // 编译器自动使用Integer.intValue()
        /// 装箱和拆箱会影响代码的执行效率，因为编译后的 class 代码严格区分基本类型和引用类型的。
        /// 并且，自动拆箱执行时可能会报 NullPointerException
        // public class Main {
        //    public static void main(String[] args) {
        //        Integer n = null;
        //        int i = n;
        //    }
        // }

        /// 1.2.2.1、包装类型-最佳实践
        /// 我们把能创建“新”对象的静态方法称为静态工厂方法，Integer.valueOf() 就是静态工厂方法，它尽可能地返回缓存的实例以节省内存。
        /// 创建新对象时，优先选用静态工厂方法而不是 new 操作符。
//        Integer m1 = new Integer(100); // 方法1
        Integer m2 = Integer.valueOf(100); // 方法2✅
        // 方法2更好，它可能始终返回同一个 Integer，而方法1总是创建新的 Integer 实例。
        // 方法2把内部优化留给 Integer 的实现者去做，即使在当前版本没有优化，也有可能在下一个版本进行优化。

        /// 1.2.2.2、包装类型-进制转换
        /// 在计算机内存中，只用二进制表示，不存在十进制或十六进制的表示方法，int n = 100，在内存中总是以4字节的二进制表示。
        /// 00000000 00000000 00000000 01100100
        /// 我们经常使用的 System.out.println(n) 是依靠核心库自动把整数格式化为10进制输出并显示在屏幕上。
        /// 这里我们注意到程序设计的一个重要原则:数据的存储和显示要分离
        int xx1 = Integer.parseInt("100"); // 100
        System.out.println(xx1);
        int x2 = Integer.parseInt("100", 16); // 256,因为按16进制解析
        System.out.println(x2);

        System.out.println(Integer.toString(100)); // "100",表示为10进制
        System.out.println(Integer.toString(100, 36)); // "2s",表示为36进制
        System.out.println(Integer.toHexString(100)); // "64",表示为16进制
        System.out.println(Integer.toOctalString(100)); // "144",表示为8进制
        System.out.println(Integer.toBinaryString(100)); // "1100100",表示为2进制

        /// 1.2.2.3、包装类型-静态变量
        // boolean只有两个值true/false，其包装类型只需要引用Boolean提供的静态字段:
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        // int可表示的最大/最小值:
        int max = Integer.MAX_VALUE; // 2147483647
        int min = Integer.MIN_VALUE; // -2147483648
        // long类型占用的bit和byte数量:
        int sizeOfLong = Long.SIZE; // 64 (bits)
        int bytesOfLong = Long.BYTES; // 8 (bytes)
        /// 最后，所有的整数和浮点数的包装类型都继承自 Number，因此可以非常方便地直接通过包装类 型获取各种基本类型:
        // 向上转型为Number:
        Number num = Integer.valueOf(999); // new Integer(999);
        // 获取byte, int, long, float, double: byte b = num.byteValue();
        int in = num.intValue();
        long ln = num.longValue();
        float ff = num.floatValue();
        double df = num.doubleValue();

        /// 1.2.2.4、包装类型-处理无符号整型
        /// 在Java中，并没有无符号整型(Unsigned)的基本数据类型。 byte、 short、 int 和long 都是带符号整型，最高位是符号位。
        /// 而C语言则提供了CPU支持的全部数据类型，包括无符号整型。无符号整型和有符号整型的转换在Java中就需要借助包装类型的静态方法完成。
        ///
        /// 例如，byte 是有符号整型，范围是 -128 ~ +127 ，但如果把 byte 看作无符号整型，它的范围是 0 ~ 255。
        /// 我们把一个负的 byte 按无符号整型转换为 int:
        byte xxx = -1;
        byte yyy = 127;
        System.out.println(Byte.toUnsignedInt(xxx)); // 255
        System.out.println(Byte.toUnsignedInt(yyy)); // 127
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="1.2.2.5、任意大小包装类型(BigInteger、BigDecimal)">

    /// 任意大小包装类型
    ///
    /// 1.2.2.5、BigInteger 不会有范围限制，但缺点是速度比较慢
    static void bigIntegerFunc() {
        // 如果我们使用的整数范围超过了 long 型怎么办? 这个时候，就只能用软件来模拟一个大整数。
        // java.math.BigInteger 就是用来表示任意大小的整数。
        // BigInteger 内部用一个 int[] 数组来模拟一个非常大的整数
        BigInteger bi = new BigInteger("2");
        System.out.println(bi.pow(5)); // 幂
        // 对 BigInteger 做运算的时候，只能使用实例方法，例如 +
        System.out.println(bi.add(BigInteger.valueOf(10)));

//        bi.longValue(); // 可以把 BigInteger 转换成基本类型 doubleValue() 等
        // 如果 BigInteger 表示的范围超过了基本类型的范围，转换时将丢失高位信息，即结果不一定是准确的。
        // 如果需要准确地转换成基本类型，可以使用 longValueExact() 等方法
//        bi.longValueExact(); // 如果超出了 long 型的范围，会抛出 ArithmeticException

        // 如果 BigInteger 的值甚至超过了 float 的最大范围(3.4x10^38)
        BigInteger n = new BigInteger("999999").pow(99);
        float f = n.floatValue();
        System.out.println(f); // Infinity
    }

    /// 1.2.2.5.1、BigDecimal
    static void bigDecimalFunc() {
        BigDecimal bd = new BigDecimal("123.4567");
        System.out.println(bd.multiply(bd)); // 15241.55677489

        BigDecimal d1 = new BigDecimal("123.45");
        BigDecimal d2 = new BigDecimal("123.4500");
        BigDecimal d3 = new BigDecimal("1234500");
        // scale() 表示小数位数
        System.out.println(d1.scale()); // 2位小数位数
        System.out.println(d2.scale()); // 4
        System.out.println(d3.scale()); // 0

        BigDecimal d11 = new BigDecimal("123.4500");
        // stripTrailingZeros() 可以将一个 BigDecimal 格式化为一个相等的，但去掉了末尾0的 BigDecimal
        BigDecimal d22 = d1.stripTrailingZeros();
        System.out.println(d11.scale()); // 4
        System.out.println(d22.scale()); // 2, 因为去掉了00

        BigDecimal d33 = new BigDecimal("1234500");
        BigDecimal d44 = d33.stripTrailingZeros();
        System.out.println(d33.scale()); // 0
        // 如果一个 BigDecimal 的 scale() 返回负数，如 -2 表示这个数是个整数，并且末尾有2个0
        System.out.println(d44.scale()); // -2

        /// 可以对一个 BigDecimal 设置它的 scale，如果精度比原始值低，那么按照指定的方法进行四舍五入或者直接截断:
        BigDecimal d111 = new BigDecimal("123.456789");
        BigDecimal d222 = d111.setScale(4, RoundingMode.HALF_UP); // 四舍五入， 123.4568
        BigDecimal d333 = d111.setScale(4, RoundingMode.DOWN); // 直接截断， 123.4567
        System.out.println(d222);
        System.out.println(d333);

        /// 对 BigDecimal 做加、减、乘时，精度不会丢失，
        /// 但是做除法时，存在无法除尽的情况，这时，就必须指定精度以及如何进行截断:
        BigDecimal d1111 = new BigDecimal("123.456");
        BigDecimal d2222 = new BigDecimal("23.456789");
//        BigDecimal d4444 = d1111.divide(d2222); // 报错:ArithmeticException，因为除不尽
        BigDecimal d3333 = d1111.divide(d2222, 10, RoundingMode.HALF_UP); // 保留10位小数并四舍五入
        System.out.println(d3333);

        /// 还可以对 BigDecimal 做除法的同时求余数
        BigDecimal n = new BigDecimal("12.345");
        BigDecimal m = new BigDecimal("0.12");
        // 调用 divideAndRemainder() 方法时，返回的数组包含两个 BigDecimal，分别是商和余数
        BigDecimal[] dr = n.divideAndRemainder(m);
        System.out.println(dr[0]); // 商   102.0，商总是整数
        System.out.println(dr[1]); // 余数 0.105，余数不会大于除数 m

        /// 我们可以利用这个方法判断两个 BigDecimal 是否是整数倍数:
        BigDecimal n1 = new BigDecimal("1.0");
        BigDecimal m1 = new BigDecimal("0.25");
        BigDecimal[] dr1 = n1.divideAndRemainder(m1);
        if (dr1[1].signum() == 0) {
            System.out.println("n1是m1的整倍数");
        } else {
            System.out.println("n1不是m1的整倍数");
        }

        /// 比较 BigDecimal
        // 在比较两个 BigDecimal 的值是否相等时，要特别注意，使用 equals() 方法不但要求两个BigDecimal 的值相等，还要求它们的 scale() 相等:
        BigDecimal d11111 = new BigDecimal("123.456");
        BigDecimal d22222 = new BigDecimal("123.45600");
        System.out.println(d11111.equals(d22222)); // false
        System.out.println(d11111.equals(d22222.stripTrailingZeros())); // true
        /// 必须使用 compareTo() 方法来比较，0代表相等，-1代表小于，1代表大于
        System.out.println(d11111.compareTo(d22222)); // 0 = 相等, -1 = d1 < d2, 1 = d1 > d2
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="1.2.3、枚举类">
    /// 1.2.3、枚举类，枚举类是一种引用类型
    ///
    /// 前面我们讲到，引用类型比较，要使用 equals() 方法，但 enum 类型可以例外。
    /// 这是因为 enum 类型的每个常量在JVM中只有一个唯一实例，所以可以直接用 == 比较。
    ///
    /// 通过 enum 定义的枚举类，和其他的 class 有什么区别?
    /// - 定义的 enum 类型总是继承自 java.lang.Enum ，且无法被继承；
    /// - 只能定义出 enum 的实例，而无法通过 new 操作符创建 enum 的实例;
    /// - 定义的每个实例都是引用类型的唯一实例;
    /// - 可以将 enum 类型用于 switch 语句。
    enum Color {
        RED(100, "红色"),
        GREEN(101, "绿色"),
        BLUE(102, "蓝色");

        // 编译器编译出的 class 大概就像这样，所以，编译后的 enum 类和普通 class 并没有任何区别。
        /*
        // 继承自Enum，标记为final class // 每个实例均为全局唯一:
        public final class Color extends Enum {
            public static final Color RED = new Color();
            public static final Color GREEN = new Color();
            public static final Color BLUE = new Color();
            // private构造方法，确保外部无法调用new操作符:
            private Color() {}
        }
        */

        /// 枚举类的字段也可以是非final类型，即可以在运行期修改，但是不推荐这样做!
        public final int value;
        private final String chinese;

        private Color(int value, String chinese) {
            this.value = value;
            this.chinese = chinese;
        }

        /// 默认情况下，对枚举常量调用 toString() 会返回和 name() 一样的字符串。
        ///
        /// 但是 toString() 可以被复写，name() 则不行
        @Override
        public String toString() {
            return this.chinese;
        }
    }

    static void enumFunc() {
        Color color = Color.RED;
        if (color == Color.RED) {
            System.out.println("红色!");
        }
        System.out.println(color.name()); // 返回常量名 "RED"
        System.out.println(color.ordinal()); // 返回常量顺序 0
        /// 改变枚举常量定义的顺序就会导致 ordinal() 返回值发生变化
        /// 但是，如果不小心修改了枚举的顺序，编译器是无法检查出这种逻辑错误的。
        /// 要编写健壮的代码，就不要依靠 ordinal() 的返回值。因为 enum 本身是 class ，
        /// 所以我们可以定义 private 的 构造方法，并且，给每个枚举常量添加字段
        System.out.println(color.value);
        System.out.println(color.toString());

        switch (color) {
            case RED:
                System.out.println("红色!!");
                break;
            case BLUE:
                System.out.println("蓝色!!");
                break;
            case GREEN:
                System.out.println("绿色!!");
                break;
            default:
                throw new RuntimeException("cannot process" + color);
        }

        switch (color) {
            case RED -> System.out.println("红色!!");
            case BLUE -> System.out.println("蓝色!!");
            case GREEN -> System.out.println("绿色!!");
            default -> throw new RuntimeException("cannot process" + color);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="1.2.4、记录类 Data Class">

    /// 1.2.4、记录类 Data Class
    ///
    /// 使用 String、 Integer 等包装类型的时候，这些类型都是不变类，一个不变类具有以下特点:
    /// - 定义 class 时使用 final，无法派生子类;
    /// - 每个字段使用 final，保证创建实例后无法修改任何字段。
    final class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            // 这是我们编写的Compact Constructor:
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException();
            }
            this.x = x;
            this.y = y;
        }

        public int x() {
            return this.x;
        }
        public int y() {
            return this.y;
        }

        @Override
        public String toString() {
            return String.format("Point[x=%s, y=%s]", x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point p) {
                return (this.x == p.x) && (this.y == p.y);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    /// 1.2.4.1、Java 14 开始，引入 Record 类
    record PointData(int x, int y) {
        /// 1.2.4.2、编写Compact Constructor对参数进行验证
        ///
        /// 编译器默认按照 record 声明的变量顺序自动创建一个构造方法，并在方法内给字段赋值。
        ///
        /// 那么问题来了，如果我们要检查参数，应该怎么办?
        public PointData {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException();
            }
        }

        /// 1.2.4.3、一种常用的静态方法 of()，用来创建 PointData 实例
        public static PointData of(int x, int y) {
            return new PointData(x, y);
        }

        public static PointData of() {
            return of(0, 0);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="1.3、var关键字">
    /**
     * var关键字
     * <p>
     * 类型的名字太长，写起来比较麻烦，这个时候，如果想省略变量类型，可以使用var关键字。
     */
    static void varMethod() {
        // StringBuilder sb = new StringBuilder();
        // 编译器会根据赋值语句自动推断出变量sb的类型是StringBuilder
        var sb = new StringBuilder();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="2、常量">
    /**
     * 常量
     * <p>
     * 定义变量的时候，如果加上final修饰符，这个变量就变成了常量。为了和变量区分开来，根据习惯，常量名通常全部大写。
     * <p>
     * 常量的作用是用有意义的变量名来避免魔术数字(Magic number)，例如不要在代码中到处写3.14，而是定义一个常量。如果将来需要提高计算精度，我们只需要在常量的定义处修改，例如改成3.1416，而不必在所有地方替换3.14。
     */
    static void finalMethod() {
        // final double PI = 3.14;
        // PI = 3.1416; // compile error!
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="3、整数运算">
    /**
     * 整数运算
     * <p>
     * 整数的数值表示不但是精确的，而且整数运算永远是精确的，即使是除法也是精确的，因为两个整数相除只能得到结果的整数部分。
     */
    static void intArithmetic() {
        int x = 12345 / 67; // 184
        System.out.println(x);
        // 求余运算
        int y = 12345 % 67; // 12345÷67的余数是17
        System.out.println(y);
    }

    /**
     * 溢出
     * <p>
     * 整数由于存在范围限制，如果计算结果超出了范围，就会产生溢出，而溢出不会出错 ，却会得到一个奇怪的结果。
     */
    static void arithmeticOverflow() {
        int x = 2147483640;
        int y = 15;
        int sum = x + y;
        /*
          0111 1111 1111 1111 1111 1111 1111 1000
        + 0000 0000 0000 0000 0000 0000 0000 1111
        -----------------------------------------
          1000 0000 0000 0000 0000 0000 0000 0111
         */
        System.out.println(sum); // -2147483641

        double a = 2147483640;
        double b = 15;
        double ab = a + b;
        System.out.println(ab); // 2147483655
    }

    /**
     * 自增、自减
     * <p>
     * 注意 ++ 写在前面和后面计算结果是不同的， ++n 表示先加1再引用n， n++ 表示先引用n再加 1。
     */
    static void simpleArithmetic() {
        // 简写运算符
        int c = 10;
        c += 10; // 20
        System.out.println(c);
        c -= 10; // 10
        System.out.println(c);
        c *= 3;  // 30
        System.out.println(c);
        c /= 15; // 2
        System.out.println(c);

        // 自增、自减
        c = 5;
        c++; // 6
        System.out.println(c);
        c--; // 5
        System.out.println(c);
        ++c; // 6，不建议把 ++ 运算混入到常规运算中，容易自己把自己搞懵了。
        System.out.println(c);
    }

    /**
     * 移位运算
     * <p>
     * 在计算机中，整数总是以二进制的形式表示。
     * <p>
     * 对 byte 和 short 类型进行移位时，会首先转换为 int 再进行位移。
     * <p>
     * 仔细观察可发现，左移实际上就是不断地×2，右移实际上就是不断地÷2。
     */
    static void shiftOperation() {
        int n = 7;      // 00000000 0000000 0000000 00000111
        int a = n << 1; // 0000000 0000000 0000000 000001110 左移1位14
        int b = n >> 1; // 0000000 0000000 0000000 000000011 右移1位3
        System.out.println(a);
        System.out.println(b);

        int c = -536870912; // 11100000 00000000 00000000 00000000
        // 对一个负数进行右移，最高位的 1 不动
        int d = c >> 1;     // 11110000 00000000 00000000 00000000 右移1位
        System.out.println(d);
        int e = c << 1;     // 11000000 00000000 00000000 00000000 左移1位
        // System.out.println(Integer.toBinaryString(e));
        System.out.println(e);

        // 无符号的右移运算，它的特点是不管符号位，右移后高位总是补 0
        int f = c >>> 1;    // 01110000 00000000 00000000 00000000
        System.out.println(f);
    }

    /**
     * 位运算
     * <p>
     * @与(&) 两个数同时为 1 ，结果才为 1
     * @或(|) 任意一个为 1 ，结果就为 1
     * @非(~) 0 和 1 互换
     * @异或(^) 如果两个数不同，结果为 1 ，否则为 0
     */
    static void bitOperation() {
        int i = 167776589; // 00001010 00000000 00010001 01001101 // 10.0.17.77
        int n = 167776512; // 00001010 00000000 00010001 00000000 // 10.0.17.0
                           // & --------------------------------- // 通过&运算，可以快速判断一个IP是否在给定的网段内
                           // 00001010 00000000 00010001 00000000
        int in = i & n;
        System.out.println(in + ", " + Integer.toBinaryString(in));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="3.1、运算优先级">
    /**
     * 运算优先级
     * ()
     * !、~、++、--
     * *、/、%
     * +、-
     * >>、<<、>>>
     * &
     * |
     * +=、-=、*=、/=
     */
    static void operationalPriority() {}
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="3.2、类型自动提升与强制转型">
    /**
     * 类型自动提升与强制转型
     * <p>
     * 出范围的强制转型会得到错误的结果，原因是转型时， int 的两个高位字节直接被扔掉，仅保留了低位的两个字节
     */
    static void automaticTypePromotionAndCoercion() {
        short s = 1234;
        int i = 123456;
        // short y = s + i; // 编译错误!
        int x = s + i; // s自动转型为int
        System.out.println(x);

        short si = (short)i; // 将 int 强制转型为 short
        System.out.println(s);

        int i1 = 1234567;
        short s1 = (short) i1; // -10617，错误的结果
        System.out.println(s1);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="4、浮点数运算">
    /**
     * 浮点数运算
     * <p>
     * 只能进行加减乘除这些数值计算，不能做位运算和移位运算。因为浮点数常常无法精确表示，浮点数运算会产生误差。
     * <p>
     * 所以比较两个浮点数是否相等，正确的比较方法是判断两个浮点数之差的绝对值是否小于一个很小的数。
     */
    static void floatCalculation() {
        /// 在计算机中，浮点数虽然表示的范围大，但是，浮点数有个非常重要的特点，就是浮点数常常无法精确表示。
        /// 如浮点数 0.1 在计算机中就无法精确表示，因为十进制的 0.1 换算成二进制是一个无限循环小数，
        /// 很显然，无论使用 float 还是 double ，都只能存储一个 0.1 的近似值。但是 0.5 这个浮点数又可以精确地表示。
        double x = 1.0 / 10;
        double y = 1 - 9.0 / 10;
        // 观察x和y是否相等
        System.out.println(x);
        System.out.println(y);

        /// 4.1、正确判断浮点数是否相等
        double r = Math.abs(x - y);
        if (r < 0.00001) {
            System.out.println("可以认为(x,y)相等");
        } else {
            System.out.println("不相等");
        }

        /// 4.2、类型提升
        int n = 5;
        /// 需要特别注意，在一个复杂的四则运算中，两个整数的运算不会出现自动提升的情况。
        // double d = 1.2 + 24 / n; // 5.2，原因是计算 24/5 这个子表达式时，按两个整数进行运算，结果为整数4 。
        double d = 1.2 + 24.0 / n; // 6.0
        System.out.println(d);

        /// 4.3、溢出
        /// 整数运算在除数为 0 时会报错，而浮点数运算在除数为 0 时，不会报错，但会返回几个特殊值
        double d1 = 0.0 / 0;  // NaN，not a number
        double d2 = 1.0 / 0;  // Infinity，无穷大
        double d3 = -1.0 / 0; // -Infinity，负无穷大
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);

        /// 4.4、强制转型
        /// 可以将浮点数强制转型为整数。在转型时，浮点数的小数部分会被丢掉。
        int n1 = (int)12.3; // 12
        int n2 = (int)12.7; // 12
        int n3 = (int)-12.7; // -12
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        /// 如果转型后超过了整型能表示的最大范围，将返回整型的最大值。
        int n4 = (int)1.2e20; // 2147483647
        System.out.println(n4 + ", int max value:" + Integer.MAX_VALUE);

        /// 4.4.1、强制转型-四舍五入
        /// 如果要进行四舍五入，可以对浮点数加上 0.5 再强制转型
        double n5 = 2.6;
        double n6 = 2.2;
        int n5Int = (int)(n5 + 0.5); // 3
        int n6Int = (int)(n6 + 0.5); // 2
        System.out.println(n5Int);
        System.out.println(n6Int);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="5、布尔运算">
    /**
     * 布尔运算，是一种关系运算，包括以下几类:
     * @比较运算 >、>=、<、<=、==、!=
     * @与运算 &&
     * @或运算 ||
     * @非运算 !
     * <p>
     * @优先级 ! 大于 (>、>=、<、<=) 大于 (==、!=) 大于 && 大于 ||
     */
    static void boolCalculation() {
        /// 5.1、短路运算
        /// 如果一个布尔运算的表达式能提前确定结果，则后续的计算不再执行，直接返回结果。
        // 因为 false && x 的结果总是 false，无论 x 是 true 还是 false，因此与运算在确定第一个值为 false 后，
        // 不再继续计算，而是直接返回 false。
        boolean b = 5 < 3;
        boolean result = b && (5 / 0 > 0); // 这时候 5 / 0 则不会报错
        System.out.println(result);

        /// 5.2、三元运算符
        // 注意到三元运算 b ? x : y 会首先计算 b，如果 b 为 true ，则只计算 x ，否则只计算 y 。
        // 此外 x 和 y 的类型必须相同，因为返回值不是 boolean ，而是 x 和 y 之一。
        int n = -100;
        int x = n >= 0 ? n : -n; // 100
        System.out.println(x);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="6、字符和字符串">
    /**
     * 字符和字符串，在Java中是不同的类型
     * @char 是基本数据类型，一个 char 保存一个Unicode字符
     * @String 是引用类型
     */
    static void charAndString() {
        // 因为Java在内存中总是使用Unicode表示字符，所以一个英文字符和一个中文字符都用一个 char 类型表示，
        // 它们都占用两个字节。要显示一个字符的Unicode编码，只需将 char 类型直接赋值给 int 类型即可
        int n1 = 'A'; // 字母“A”的Unicodde编码是65
        int n2 = '中'; // 汉字“中”的Unicode编码是20013
        System.out.println(n1);
        System.out.println(n2);
        // 还可以直接用转义字符 \\u+Unicode编码(16进制) 来表示一个字符:
        char c3 = '\u0041'; // 'A'，因为十六进制0041 = 十进制65
        char c4 = '\u4e2d'; // '中'，因为十六进制4e2d = 十进制20013
        System.out.println(c3);
        System.out.println(c4);

        /// 6.1、字符串
        ///      常见的转义字符：
        ///      \" 表示字符 "
        ///      \' 表示字符 '
        ///      \\ 表示字符 \
        ///      \n 表示换行符
        ///      \r 表示回车符
        ///      \t 表示Tab
        ///      \\u#### 表示一个Unicode编码的字符
        String s = ""; // 空字符串，包含0个字符
        String s1 = "A"; // 包含一个字符
        String s2 = "中文 ABC"; // 包含6个字符，其中有一个空格
        String s3 = "ABC\n\u0041\u4e2d"; // 包含6个字符: ABC换行符A中
        System.out.println(s3);

        /// 6.2、字符串连接
        ///      可以使用 + 连接任意字符串和其他数据类型
        ///      如果用 + 连接字符串和其他数据类型，会将其他数据类型先自动转型为字符串，再连接
        int age = 25;
        String s4 = "age is " + age;
        System.out.println(s4); // age is 25

        /// 6.3、多行字符串
        // 我们要表示多行字符串，使用+号连接会非常不方便:
        String s5 = "first line \n"
                + "second line \n"
                + "end";
        System.out.println(s5);
        // 从Java 13开始，字符串可以用 """...""" 表示多行字符串(Text Blocks)了
        s5 = """
                SELECT * FROM users
                WHERE id > 100
                ORDER BY name DESC-A
                """;
        // 上述多行字符串实际上是5行，在最后一个 DESC 后面还有一个 \n
        System.out.println(s5);
        s5 = """
                SELECT * FROM users
        WHERE id > 100
                ORDER BY name DESC-B""";
        // 如果多行字符串的排版不规则，总是以最短的行首空格为基准
        System.out.println(s5);

        /// 6.4、字符串不可变
        String str = "Hello";
        System.out.println(str); // Hello
        str = "World";
        System.out.println(str); // World
        // 观察上面的执行结果， str 字符串并没有变，变得是 str 的指向
        // 执行 String str = "Hello"; 时，JVM虚拟机先创建字符串 "Hello"，然后把字符串变量 str 指向它
        // 紧接着，执行 str = "World"; 时，JVM虚拟机先创建字符串 "World" ，然后把字符串变量 str 指向它
        // 原来的字符串 "Hello" 还在，只是我们无法通过变量 str 访问它而已。
        // 因此，字符串的不可变是指字符串内容不可变。至于变量，可以一会指向字符串 "Hello" ，一会指向字符串 "World" 。
        String str1 = "hello";
        String t = str1;
        str1 = "world";
        System.out.println(t); // t是"hello"还是"world"?
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="7、数组类型">
    /// 数组类型(类型[]，如 int[])
    ///
    /// @特点1 数组所有元素初始化为默认值，整型都是 0 ，浮点型是 0.0 ，布尔型是 false
    /// @特点2 数组是引用类型，一旦创建后，大小就不可改变
    /// @特点3 要访问数组中的某一个元素，需要使用索引，数组索引从 0 开始。
    static void arrayMethod() {
        /*
        int[] ns = new int[5];
        ns[0] = 68;
        ns[1] = 79;
        ns[2] = 91;
        ns[3] = 85;
        ns[4] = 62;
        */
        // int[] ns = new int[] { 68, 79, 91, 85, 62};
        int[] ns;
        ns = new int[] { 68, 79, 91, 85, 62 };
        System.out.println(ns.length); // 5
        ns = new int[] { 1, 2, 3 };
        System.out.println(ns.length); // 3
        // 数组大小变了吗?看上去好像是变了，但其实根本没变。
        // 对于变量 ns 来说，程序先创建了 { 68, 79, 91, 85, 62 } 这个数组，然后由 ns 去指向这个数组
        // 执行 ns = new int[] { 1, 2, 3 }; 时，ns 指向一个新的数组，原数组并没有改变
    }

    /**
     * 7.1、字符串数组
     */
    static void stringArrayMethod() {
        String[] names = { "ABC", "XYZ", "zoo" };
        String s = names[1];
        // 原来 names[1] 指向的字符串 "XYZ" 并没有改变，仅仅是将 向 "XYZ" 改成了指向 "cat"；
        // 其结果是字符串 "XYZ" 再也无法通过 names[1] 访问到了
        names[1] = "cat";
        System.out.println(s); // s是"XYZ"还是"cat"?
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="8、输入和输出">
    /**
     * 输出
     */
    static void printMethon() {
        System.out.println("START"); // print line
        System.out.print("A,");
        System.out.print("B");
    }

    /// 8.1、格式化输出
    ///
    /// [常用占位符，更多格式化参数参考官网链接](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Formatter.html#syntax)：
    /// @%d 格式化输出整数
    /// @%x 格式化输出十六进制整数
    /// @%f 格式化输出浮点数，占位符还可以带格式，例如 %.2f 表示显示两位小数。
    /// @%e 格式化输出科学计数法表示的浮点数
    /// @%s 格式化字符串，如果你不确定用啥占位符，那就始终用%s，因为它可以显示任何数据类型
    static void printfMethon() {
        double d = 3.1415926;
        System.out.printf("%.2f\n", d);
        System.out.printf("%.4f\n", d);

        int n = 12345000;
        System.out.printf("n=%d, hex=%08x", n, n); // 两个%占位符必须传入两个数
    }

    /**
     * 8.2、输入
     */
    static void inputMethod() {
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象
        System.out.print("Input your name: ");    // 打印提示
        String name = scanner.nextLine();         // 读取一行输入并获取字符串
        System.out.print("Input your age: ");
        int age = scanner.nextInt();              // 读取一行输入并获取整数
        System.out.printf("Hi, %s, you are %d\n", name, age);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="9、if条件语句">
    static void ifMethod() {
        /// 9.1 if 条件判断
        int n = 70;
        if (n < 60)
            System.out.println("不及格"); // 只有一行语句时，可以省略花括号{}
        // 但是，省略花括号并不总是一个好主意。假设某个时候，突然想给 if 语句块增加一条语句时
        // 由于使用缩进格式，很容易把两行语句都看成 if 语句的执行块
        /// 在使用git这些版本控制系统自动合并时更容易出问题，所以不推荐忽略花括号的写法。
            System.out.println("要加油"); // 注意这条语句不是if语句块的一部分

        if (n >= 90) {
            System.out.println("优秀");
        } else if (n >= 60) {
            System.out.println("及格了");
            /*
            串联，相当于：
            if (n >= 90) {
               System.out.println("优秀");
            } else {
               if (n >= 60) {
               } else {
               }
            }
             */
        } else {
            System.out.println("挂科了");
        }
        System.out.println("END");

        /// 9.2 在串联使用多个 if 时，从上到下匹配，匹配后不再继续匹配，要特别注意判断顺序
        // 正确的方式是 > 按照判断范围从大到小，< 从小到大依次判断
        n = 100;
        if (n >= 60) {
            System.out.println("及格了"); // 先判断 n >= 60 成功后，后续 else 不再执行了。
        } else if (n >= 90) {
            System.out.println("优秀");
        } else {
            System.out.println("挂科了");
        }

        /// 9.3 使用 if 判断，要注意边界条件
        // 前面讲过了浮点数在计算机中常常无法精确表示，并且计算可能出现误差，因此判断浮点数相等用 == 判断不靠谱
        double x = 1 - 9.0 / 10;
        // if (x == 0.1) {
        if (Math.abs(x - 0.1) < 0.00001) {
            System.out.println("x is 0.1");
        } else {
            System.out.println("x is NOT 0.1");
        }

        /// 9.4 判断引用类型相等，使用 equal() 方法
        String s1 = "hello";
        String s2 = "HELLO".toLowerCase();
        System.out.println(s1);
        System.out.println(s2);
        /*
        if (s1 == s2) {
            System.out.println("s1 == s2");
        } else {
            System.out.println("s1 != s2"); // false
        }
        */
        s1 = null;
        // if (s1.equals(s2)) { // 如果此时 s1 为 null，会报 NullPointerException
        // 要避免这个问题，可以使用短路运算符 &&
        if (s1 != null && s1.equals(s2)) {
            System.out.println("s1 等于 s2");
        } else {
            System.out.println("s1 不等于 s2");
        }
        // 还可以把一定不是 null 的对象 放到前面: if ("he".equals(s1))
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="10、switch条件语句">
    /**
     * switch条件语句，从上到下匹配
     * @case case语句没有花括号{}，而且case语句具有穿透性，漏写break将导致意想不到的结果
     */
    static void switchMethod() {
        int option = 2;
        switch (option) {
            case 1:
                System.out.println("Selected 1");
                break;
            case 2:
                System.out.println("Selected 2");
                // break;
            case 3:
            case 4:
                System.out.println("Selected 3、4");
                break;
            default:
                System.out.println("Selected other");
                break;
        }
        /// 使用 switch 语句时，只要保证有 break，case 的顺序不影响程序逻辑
        /// 但是仍然建议按照自然顺序排列，便于阅读。
        switch (option) {
            case 1:
                System.out.println("Selected 1");
                break;
            case 3:
                System.out.println("Selected 3");
                break;
            case 2:
                System.out.println("Selected 2");
                break;
            default:
                System.out.println("Selected other");
                break;
        }

        /// 10.1、字符串匹配时，是比较“内容相等”
        String fruit = "apple";
        switch (fruit) {
            case "apple":
                System.out.println("Selected apple");
                break;
            case "pear":
                System.out.println("Selected pear");
                break;
            case "mango":
                System.out.println("Selected mango");
                break;
            default:
                System.out.println("No fruit selected");
                break;
        }

        /// 10.2、Java 12开始，switch 升级，类似模式匹配
        ///       保证只有一种路径会被执行，并且不需要 break 语句
        switch (fruit) {
            case "apple" -> System.out.println("(Java12)Selected apple");
            case "pear" -> {
                System.out.println("(Java12)Selected pear");
            }
            case "mango" -> System.out.println("(Java12)Selected mango");
            default -> System.out.println("(Java12)No fruit selected");
        }
        ///       还可以直接返回值
        int opt = switch (fruit) {
            case "apple" -> 1;
            case "pear", "mango" -> {
                yield 2;
            }
            default -> 0;
        }; // 注意赋值语句要以;结束
        System.out.println("opt = " + opt);
    }
    // </editor-fold>\

    // <editor-fold defaultstate="collapsed" desc="11、while循环，do-while循环">
    /**
     * while循环，先判断条件，再执行循环
     */
    static void whileMethod() {
        int sum = 0;
        int n = 1;
        while (n <= 100) {
            sum += n;
            n ++;
        }
        System.out.println(sum);

        ///  11.1、死循环
        // 如果循环条件永远满足，那这个循环就变成了死循环。死循环将导致100%的CPU占用，用户会感觉 电脑运行缓慢
        // 表面上看下面的 while 循环是一个死循环，但是Java的 int 类型有最大值，达到最大值后，再加1会变成负数，结果意外退出了 while 循环
        while (n > 0) {
            sum = sum + n;
            n ++;
        }
        System.out.println(n); // -2147483648
    }

    /**
     * 11.2、do-while循环
     * <p>先执行循环，再判断条件，条件满足时继续循环，条件不满足时退出。
     * <p>可见，do-while循环至少循环一次
     */
    static void doWhileMethod() {
        int sum = 0;
        int n = 1;
        do {
            sum = sum + n;
            n ++;
        } while (n <= 100); // 一样要注意死循环
        System.out.println(sum);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="12、for循环，for-each循环">
    /**
     * for循环，Java使用的最广的循环
     * <p>会先初始化计数器，然后在每次循环前检测循环条件，在每次循环后更新计数器。
     */
    static void forMethod() {
        int[] ns = { 1, 4, 9, 16, 25 };
        int sum = 0;
        /// 使用 for 循环时，千万不要在循环体内修改计数器! 在循环体中修改计数器常常导致莫名其妙的逻辑错误。
        for (int i=0; i<ns.length; /* i++ */ i=i+2) {
            sum = sum + ns[i];
            // i = i + 1; // 如果希望只访问索引号为偶数的数组元素，应该把 for 循环改写
        }
        System.out.println(sum);

        /// 12.1、变量 i 不要定义在 for 循环外
        int i;
        for (i=0; i<ns.length; i++) {
            System.out.println(ns[i]);
        }
        int iNew = i; // 仍然可以使用i
        // 退出 for 循环后，变量 i 仍然可以被访问，这就破坏了变量应该把访问范围缩到最小的原则。

        /// 12.2 灵活使用for循环
        ///      通常不推荐这样写，但是某些情况下，是可以省略 for 循环的某些语句的。
        // 不设置结束条件:
//        for (int n=0; ; n++) {} // 死循环
        // 不设置结束条件和更新语句:
//         for (int n=0;;) {} // 死循环
        // 什么都不设置:
//        for (;;) {} // 死循环

        /// 12.3 for-each循环，它可以更简单地遍历数组
        for (int n : ns) {
            System.out.println(n);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="13、break和continue">
    /**
     * 循环中的break和continue
     */
    static void breakAndContinue() {
        /// 13.1、break，跳出当前循环
        int sum = 0;
        for (int i=1; ; i++) { // 死循环
            sum = sum + i;
            if (i == 100) {
                break;
            }
        }
        System.out.println(sum);

//        for (int i=1; i<=10; i++) {
//            System.out.println("i = " + i);
//            for (int j=1; j<=10; j++) {
//                System.out.println("j = " + j);
//                if (j >= i) {
//                    break;
//                }
//            }
//            // break跳到这里，但不会跳出外层 for 循环。
//            System.out.println("breaked");
//        }

        /// 13.2、continue，提前结束本次循环，直接继续执行下次循环
        ///       在多层嵌套的循环中， continue 语句同样是结束本次自己所在的循环。
        sum = 0;
        for (int i=1; i<=10; i++) {
            System.out.println("begin i = " + i);
            if (i % 2 == 0) {
                continue;
            }
            sum = sum + i;
            System.out.println("end i = " + i);
        }
        System.out.println(sum);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="14、冒泡排序">
    /**
     * 冒泡排序
     */
    static void arrayForEach() {
        /// 14.1、冒泡排序
        ///       冒泡排序的特点是，每一轮循环后，最大的一个数被交换到末尾，因此下一轮循环就可以“刨除”最后的数，
        ///       每一轮循环都比上一轮循环的结束位置靠前一位。
        int[] ns = { 6, 5, 4, 3 };
        System.out.println(Arrays.toString(ns)); // 排序前
//        for (int i = 0; i < ns.length - 1; i++) {
//            System.out.println("外层for: " + i);
//            for (int j = 0; j < ns.length - i - 1; j++) {
//                if (ns[j] > ns[j+1]) {
//                    // 交换 ns[j] 和 ns[j+1]
//                    int tmp = ns[j];
//                    ns[j] = ns[j+1];
//                    ns[j+1] = tmp;
//                }
//                System.out.println("内层for: " + j + ", " + Arrays.toString(ns));
//            }
//        }
        Arrays.sort(ns);
        System.out.println(Arrays.toString(ns)); // 升序排序后
        /// 必须注意，对整型数组排序实际上修改了数组本身，ns 指向的数组内容已经完全变了
        /// 在内存中 ns -> [6|5|4|3] 排序后中变为 ns -> [3|4|5|6]
        /// 但是
        /// 对字符串数组进行排序
        /// String[] nStr = { "banana", "apple", "pear" };
        /// Arrays.sort(nStr); // { "apple", "banana", "pear" }
        /// 在内存中 nStr -> ["banana"|"apple"|"pear"] 排序后中还是 nStr -> ["banana"|"apple"|"pear"]
        /// 但是 nStr 数组的每个元素指向变化了

        int[] ns1 = { 28, 12, 89, 73, 65, 18, 96, 50, 8, 36 }; // 排序前:
        System.out.println(Arrays.toString(ns1));
        boolean hasSwap; // 标记本轮是否发生交换（优化点）
        // 外层循环：控制排序轮数（最多需要n-1轮）
        // 原理：数组有n个元素，最多需要n-1轮排序（例如：2 个元素只需 1 轮，3 个元素最多 2 轮）；
        //      每轮将未排序的最大元素放到末尾，已排序元素无需再比较
        // 变量i的含义：表示已完成排序的元素个数（每轮结束后，末尾多 1 个有序元素）
        for (int i = 0; i < ns1.length - 1; i++) {
            hasSwap = false; // 初始化：本轮未发生交换
            // 内层循环：核心比较与交换
            // 原理：每轮只需遍历未排序的前半部分（末尾i个元素已有序，无需比较）；
            // 变量j的含义：遍历未排序部分的索引，每次比较arr[j]和arr[j+1]；
            for (int j = 0; j < ns1.length - i - 1; j++) {
                if (ns1[j] < ns1[j+1]) {
                    int tmp = ns1[j];
                    ns1[j] = ns1[j+1];
                    ns1[j+1] = tmp;
                    hasSwap = true; // 标记本轮发生了交换
                }
            }
            if (!hasSwap) {
                // 优化：若本轮无交换，说明数组已完全有序，直接退出循环
                // 场景：若某一轮遍历中没有发生任何交换，说明数组已完全有序（例如：数组[8,12,18,28,36,50,65,73,89,96]）；
                // 作用：避免后续不必要的轮次，将最佳时间复杂度从O(n²)优化到O(n)（数组已有序时）。
                break;
            }
        }
        System.out.println(Arrays.toString(ns1)); // 降序排序后
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="15、多维数组">
    /**
     * 多维数组
     */
    static void multidimensionalArray() {
        /// 15.1、二维数组
        ///       每个数组元素的长度并不要求相同
        int[][] ns = {
                { 1, 2, 3, 4},
                { 5, 6, 7 },
                { 9, 10, 11, 12 }
        };
        System.out.println(ns[1][2]); // 7
        System.out.println(Arrays.deepToString(ns));
        int[] arr0 = ns[0];
        System.out.println(Arrays.toString(arr0));

        /// 15.2 三维数组
        // 理论上，我们可以定义任意的N维数组。但在实际应用中，除了二维数组在某些时候还能用得上，更 高维度的数组很少使用。
//        int[][][] ns3 = {
//                {
//                        {1, 2, 3},
//                        {4, 5, 6},
//                        {7, 8, 9}
//                },
//                {
//                        {10, 11},
//                        {12, 13}
//                },
//                {
//                        {14, 15, 16},
//                        {17, 18}
//                }
//        };
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="16、命令行参数 args">
    static void commandLineParameters(String[] args) {
        // 这个命令行参数由JVM接收用户输入并传给 main 方法
        // 必须在命令行中执行，先编译
        // javac Main.java
        // 执行的时候，给它传递一个 -version 参数
        // java Main.java -version
        for (String arg : args) {
            if ("-version".equals(arg)) {
                 System.out.println("v 1.0");
            }
        }
    }
    // </editor-fold>
}

// <editor-fold defaultstate="collapsed" desc="17、class、instance和构造方法">
/**
 * class 是一种对象模版，它定义了如何创建实例，因此，class本身就是一种数据类型
 * <p>instance是对象实例，instance是根据class创建的实例，可以创建多个instance，每个instance 类型相同，但各自属性可能不相同
 */
class Person {
    /// 字段(field) name，字段用来描述一个类的特征
    protected String name = "Unamed"; // 未初始化时引用类型默认初始化为 null
    protected int age = 13; // 未初始化时基本类型使用默认值

    /**
     * 17.1、构造方法，如果我们自定义了一个构造方法，那么编译器就不再自动创建默认构造方法
     * <p>和普通方法相比，构造方法的名称就是类名，且没有返回值(也没有 void )
     * <p>调用构造方法，必须用 new 操作符
     */
    public Person(String name, int age) {
        /*
        前面我们并没有为 Person 类编写构造方法，为什么可以调用 new Person()

        原因是如果一个类没有定义构造方法，编译器会自动为我们生成一个默认构造方法，它没有参数，也没有执行语句，类似这样:
        class Person {
           public Person() {
           }
        }
         */
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        // 多个构造方法
        this(name, 18); // 调用另一个构造方法Person(String, int)
    }

    /**
     * 17.2、还想保留不带参数的构造方法
     */
    public Person() {
        this("Li"); // 调用另一个构造方法Person(String)
    }

    /// get方法
    public String getName() {
        return name; // 相当于this.name
    }
    /// set方法
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("invalid name");
        }
        /// 隐含的变量 this ，它始终指向当前实例。
        this.name = name.strip();
    }

    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("invalid age value");
        }
        this.age = age;
    }

    static void func() {
        /// 17.3、创建对象初始化顺序
        /// 创建一个Person类型的实例，并通过变量 ming 指向它
        ///
        /// 问题：既对字段进行初始化，又在构造方法中对字段进行初始化，字段的初始值是啥？
        ///
        /// 在Java中，创建对象实例的时候，按照如下顺序进行初始化:
        /// 1. 先初始化字段，例如 int age = 13; 表示字段初始化为 13；
        /// 2. 执行构造方法的代码进行初始化；
        Person ming = new Person("Xiao Ming", 12);
        System.out.println(ming.age);
        Person fan = new Person("Fan");
        System.out.println(fan.age);
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="18、可变参数">

class Group {
    private String[] names;

    /**
     * 可变参数
     * <p>可变参数用 类型... 定义，可变参数相当于数组类型
     */
    public void setNames(String... names) {
        this.names = names;
    }

    /**
     * 为什么不把可变参数改写为 String[] 类型
     */
    public void setNamesArray(String[] names) {
        this.names = names;
    }
    static void func() {
        Group group = new Group();
        /// 1、调用方需要自己先构造 String[]，比较麻烦。
        group.setNamesArray(new String[] { "Xiao Ming", "Xiao Hong", "Xiao Jun" });
        /// 2、调用方可以传入 null
        group.setNamesArray(null);
        /// 3、而可变参数可以保证无法传入 null ，因为传入0个参数时，接收到的实际值是一个空数组而不是 null
//        group.setNames(null);
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="19、参数绑定">

class PersonParamBind {
    private int age;
    private String[] fullname;

    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getFullname() {
        return this.fullname[0] + " " + this.fullname[1];
    }
    public void setFullname(String[] fullname) {
        this.fullname = fullname;
    }

    static void func() {
        PersonParamBind p = new PersonParamBind();
        int n = 15;
        p.setAge(n);
        System.out.println(p.age);
        n = 20;
        /// 19.1、基本类型参数绑定
        ///       基本类型参数的传递，是调用方值的复制。双方各自的后续修改，互不影响。
        System.out.println(p.age); // 15

        String[] fullname = { "Liu", "Fan" };
        p.setFullname(fullname);
        System.out.println(p.getFullname()); // "Liu Fan"
//        fullname = new String[] {"laz", "ss"}; // fullname 指向了新的数组，但是p.fullname指向还是原数组
//        System.out.println(p.getFullname()); // "Liu Fan"
        /// 19.2、引用类型参数绑定
        ///       引用类型参数的传递，调用方的变量，和接收方的参数变量，指向的是同一个对象。
        ///       双方任一方对这个对象的修改，都会影响对方(因为指向同一个对象嘛)。
        fullname[0] = "Lau";
        System.out.println(p.getFullname()); // "Lau Fan"
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="20、方法重载 Overload">

class MethodOverload {
    /// 在一个类中，我们可以定义多个方法。如果有一系列方法，它们的功能都是类似的，只有参数有所不同，
    /// 那么，可以把这一组方法名做成同名方法。
    ///
    /// 这种方法名相同，但各自的参数不同，称为方法重载( Overload )
    ///
    /// 方法重载的目的是，功能类似的方法使用同一名字，更容易记住，因此，调用起来更简单。
    ///
    /// 注意:方法重载的返回值类型通常都是相同的。
    public void hello() {
        System.out.println("Hello, world!");
    }
    public void hello(String name) {
        System.out.println("Hello, " + name + "!");
    }
    public void hello(String name, int age) {
        if (age < 18) {
            System.out.println("Hi, " + name + "!");
        } else {
            System.out.println("Hello, " + name + "!");
        }
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="21、继承">

/**
 * 正常情况下，只要某个class没有 final 修饰符，那么任何类都可以从该class继承。
 */
class Person1 {
    protected String name;
    protected int age;
    public Person1(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

/**
 * 21、继承
 * <p>子类(扩展类)自动获得了父类(超类、基类)的所有字段，严禁定义与父类重名的字段!
 */
class Student1 extends Person1 {
    /// 继承有个特点，就是子类无法访问父类的 private 字段或者 private 方法。
    ///
    /// 用 protected 修饰的字段可以被子类访问。
    protected int score; // 严禁定义与父类重名的字段!

    public Student1(String name, int age, int score) {
        /// 如果父类没有默认的构造方法，子类就必须显式调用 super() 并给出参数以便让编译器定位到父类的一个合适的构造方法。
        /// 这里还顺带引出了另一个问题:即子类 不会继承 任何父类的构造方法。子类默认的构造方法是编译 器自动生成的，不是继承的。
        super(name, age);
        this.score = score;
    }

    public String hello() {
        /// super 关键字表示父类(超类)。子类引用父类的字段时，可以用 super.fieldName
        return "Hello, " + super.name;
    }

    static void func() {
        Student1 s = new Student1("a", 10, 100);
        System.out.println(s.name);

        /// 21.1 向上转型
        ///      因为 Student1 继承自 Person1，因此它拥有 Person1 的全部功能。
        ///      向上转型实际上是把一个子类型安全地变为更加抽象的父类型:
        Person1 p1 = s; // upcasting, ok
        Object o1 = p1; // upcasting, ok
        Object o2 = s; // upcasting, ok

        /// 21.2 向下转型
        ///      如果把一个父类类型强制转型为子类类型，就是向下转型
        Person1 p2 = new Person1("p2", 46);
        // Person1 类型 p1 实际指向 Student1 实例, 在向下转型的时候，把 p1 转型为 Student1 会成功, 因为 p1 确实指向 Student1 实例
        Student1 s1 = (Student1) p1; // downcasting, ok
        // Person1 类型变量 p2 实际指向 Person1 实例。 在向下转型的时候，把 p2 转型为 Student1 会失败，
        // 因为 p2 的实际类型是 Person1 ，不能把父类变为子类，因为子类功能比父类多，多的功能无法凭空变出来。
//        Student1 s2 = (Student1) p2; // runtime error! ClassCastException!

        /// 21.3 instanceof 操作符
        /// 为了避免向下转型出错，Java提供了 instanceof 操作符，可以先判断一个实例究竟是不是某种类型
        System.out.println(p2 instanceof Person1); // true
        System.out.println(p2 instanceof Student1); // false

        System.out.println(p1 instanceof Person1); // true
        System.out.println(p1 instanceof Student1); // true

        Student1 n = null;
        // instanceof 实际上判断一个变量所指向的实例是否是指定类型，或者这个类型的子类。如果一个引用变量为 null ，
        // 那么对任何 instanceof 的判断都为 false
        System.out.println(n instanceof Student1); // false

        Object obj = "hello obj";
        if (obj instanceof String) { // 利用这个判断条件
            // 只有判断成功才会向下转型
            String str = (String) obj;
            System.out.println(str.toUpperCase());
        }

        /// 21.3.1 Java 14 开始，判断 instanceof 后，可以直接转型为指定变量，避免再次强制转型
        if (obj instanceof String str) {
            System.out.println(str.toUpperCase() + " Java 14");
        }
    }
}

/**
 * 21.4、Java15，限定范围继承
 * <p> 允许使用 sealed 修饰 class，并通过 permits 明确写出能够从该 class 继承的子类名称。
 */
sealed class Shape permits Rect /*, Circle, Triangel*/ {
}
final class Rect extends Shape {
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="22、多态">

class Person2 {
    protected String name;

    public void run() {
        System.out.println("Person2.run");
    }
}
class Student2 extends Person2 {
    /**
     * Override和Overload不同的是，如果方法签名不同，就是Overload，Overload方法是一个新方法;
     * 如果方法签名相同，并且返回值也相同，就是 Override
     * <p>
     * 注意 @Override 不是必须的
     */
    @Override
    public void run() {
        System.out.println("Student2.run");
    }
    /// 方法重载 Overload，因为参数不同
    void run(String s) {
    }

    /// 方法名相同，方法参数相同，但方法返回值不同，也是Overload。但在Java程序中，出现这种情况，编译器会报错。
//    int run(String s) {
//    }

    static void func() {
        /// 22、多态
        /// 我们已经知道，引用变量的声明类型可能与其实际类型不符
        /// 实际上调用的方法是 Student2 的 run() 方法，因此可得出结论。
        /// Java的实例方法调用是基于运行时的实际类型的动态调用，而非变量的声明类型。
        /// 这个非常重要的特性在面向对象编程中称之为多态。它的英文拼写非常复杂:Polymorphic。
        /// 多态是指，针对某个类型的方法调用，其真正执行的方法取决于运行时期实际类型的方法。
        Person2 p = new Student2();
        p.run(); // 应该打印Person2.run还是Student2.run?
        // 有同学会说，从上面的代码一看就明白，肯定调用的是 Student 的 run() 方法啊。
    }

    static void runTwice(Person2 p) {
        p.run(); // 我们是无法知道传入的参数实际类型究竟是 Person2, 还是他的子类
    }
}

/// 22.1、多态的应用
///      假设我们定义一种收入，需要给它报税，那么先定义一个 Income 类
class Income {
    protected double income;

    public Income(double income) {
        this.income = income;
    }

    public double getTax() {
        return  income * 0.1; // 税率 10%
    }
}

class Salary extends Income {

    public Salary(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        if (income <= 5000) {
            return 0;
        }
        return (income - 5000) * 0.2;
    }
}

class StateCouncilSpecialAllowance extends Income { // 享受国务院津贴

    public StateCouncilSpecialAllowance(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        return 0;
    }
}

class Tax {
    static double totalTax(Income... incomes) {
        double total = 0;
        for (Income income : incomes) {
            total = total + income.getTax();
        }
        return total;
    }

    static void calculate() {
        // 给一个有普通收入、工资收入和享受国务院特殊津贴的小伙伴算税:
        Income[] incomes = new Income[] {
                new Income(3000),
                new Salary(10000),
                new StateCouncilSpecialAllowance(15000)
        };
        System.out.println(Tax.totalTax(incomes));
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="23、覆写 Object 方法">

/// 因为所有的 class 最终都继承自 Object，而 Object 定义了几个重要的方法:
/// toString()：把 instance 输出为 String
/// equals()：  判断两个instance是否逻辑相等;
/// hashCode()：计算一个instance的哈希值。
class Person3 {
    protected String name;

    @Override
    public String toString() {
        return "Person3:name=" + name;
    }

    @Override
    public boolean equals(Object obj) {
        // 当且仅当obj为Person3类型:
        if (obj instanceof Person3 p) {
            // 并且name字段相同时，返回true:
            return this.name.equals(p.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="24、阻止继承 final 修饰符">

/// final 修饰的类不能被继承
/// final 修饰的方法不能被覆写Override
/// final 修饰的字段在初始化后不能重新赋值
final class Person4 {
    /// 用 final 修饰的字段在初始化后不能被修改
    public final String name;

    public Person4(String name) {
        this.name = name; // 可以在构造方法中初始化final字段
    }

    /// final 修饰的方法不能被 Override 覆盖（覆写）
    public final String hello() {
        return "Hello，" + this.name;
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="25、抽象类 abstract class">
/// 25.1、抽象类
///
/// 我们无法实例化一个抽象类，抽象类本身被设计成只能用于被继承，可以强迫子类实现其定义的抽象方法。
abstract class Person5 {
    protected String name;

    /// 把一个方法声明为 abstract，表示它是一个抽象方法，本身没有实现任何方法语句。
    ///
    /// 因为这个抽象方法本身是无法执行的，所以， Person5 类也无法被实例化。必须把 Person5 类本身也声明为 abstract
    abstract void run();
}

class Student5 extends Person5 {
    @Override
    void run() {
        System.out.println("Student5.run");
    }
}

class Teacher5 extends Person5 {
    @Override
    void run() {
        System.out.println("Teacher5.run");
    }

    /**
     * 25.2、面向抽象编程
     */
    static void func() {
        Person5 p1 = new Student5();
        Person5 p2 = new Teacher5();
        Teacher5.personRun(p1);
        Teacher5.personRun(p2);
    }
    static void personRun(Person5 p) {
        p.run();
    }
}

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="26、接口 interface">
/// 如果一个抽象类没有字段，所有方法全部都是抽象方法，就可以把该抽象类改写为接口 interface
///
/// 所谓 interface ，就是比抽象类还要抽象的纯抽象接口，因为它连字段都不能有。
///
/// 接口定义的所有方法默认都是 public abstract 的，所以这两个修饰符不需要写出来
interface Person6 extends HelloYo {
    void run();

    /**
     * 实现类可以不必覆写 default 方法
     * <p>
     * default 方法的目的是，当我们需要给接口新增一个方法时，会涉及到修改全部子类。如果新增的是 default 方法，
     * 那么子类就不必全部修改，只需要在需要覆写的地方去覆写新增方法。
     */
    default String getName() {
        return "Person6";
    }
}

/// interface 是一个纯抽象类，所以它不能定义实例字段。
///
/// 但是 interface 是可以有静态字段的，并且静态字段必须为 final 类型。
interface HelloYo {
    void hello();

    /// 实际上，因为 interface 的字段只能是 public static final 类型，所以我们可以把这些修饰符都去掉
    ///
    /// 编译器会自动把该字段变为 public static final 类型。
    int MALE = 1; // public static final int MALE = 1;
}

/// 接口可以实现多个
class Student6 implements Person6 /* , HelloX */  {
    @Override
    public void run() {
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void hello() {
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="27、静态字段和静态方法 static">

/// 实例字段在每个实例中都有自己的一个独立“空间”，但是静态字段只有一个共享“空间”，所有实例都会共享该字段。
class Person7 {
    String name;
    int age;
    /// 27.1、静态字段
    static int number;

    Person7(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /// 27.2、静态方法
    static void func() {
        Person7 ming = new Person7("Ming", 12);
        Person7 hong = new Person7("Hong", 18);
        ming.number = 88;
        System.out.println(hong.number); // 88
        /// 对于静态字段，无论修改哪个实例的静态字段，效果都是一样的:所有实例的静态字段都被修改了，原因是静态字段并不属于实例
        /// 虽然实例可以访问静态字段，但是它们指向的其实都是 Person7 class 的静态字段。
        ///
        /// 因此，不推荐用 (实例变量.静态字段) 去访问静态字段，因为在Java程序中，实例对象并没有静态字段。
        /// 在代码中，实例对象能访问静态字段只是因为编译器可以根据实例类型自动转换为 类名.静态字段
        hong.number = 99;
        System.out.println(ming.number); // 99
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="28、package 和 import">

/// 在一个 class 中，我们总会引用其他的 class
class TestPackage {
    static void func() {
        /// 28.1、直接写出完整类名
//        var p = new com.liufan.learn_java.package_sample.PersonT(); // 很显然，每次写完整类名比较痛苦。

        /// 28.2、import 语句，import com.liufan.learn_java.package_sample.PersonT;
        ///
        ///                   import com.liufan.learn_java.package_sample.*;
        ///                          表示把这个包下面的所有 class 都导入进来(但不包括子包的 class )
        ///                          不推荐这种写法，因为在导入了多个包后，很难看出 PersonT 类属于哪个包
        ///
        ///                   import static com.liufan.learn_java.package_sample.PersonT.*;
        ///                          它可以导入一个类的静态字段和静态方法，很少使用
        ///
        /// 如果有两个 class 名称相同，
        /// 例如 mr.jun.Arrays 和 java.util.Arrays，那么只能 import 其中一个，另一个必须写完整类名。
        ///
        /// 最佳实践：
        ///          为了避免名字冲突，我们需要确定唯一的包名。推荐的做法是使用倒置的域名来确保唯一性。
        ///          要注意不要和 java.lang 包的类重名，即自己的类不要使用这些名字：String、System等
        ///          要注意也不要和JDK常用类重名：java.util.List、java.text.Format等
        PersonT p = new PersonT();
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="29、内部类与作用域public、package、protected、 private">

/// public,    定义为 public 的 class 、 interface 可以被其他任何类访问
///            定义为 public 的 field 、 method 可以被其他类访问，前提是首先有访问 class 的权限
///
/// private,   定义为 private 的 field 、 method 无法被其他类访问
///            private 访问权限被限定在 class 的内部，而且与方法声明顺序无关。
///            推荐把 private 方法放到后面，因为 public 方法定义了类对外提供的功能，阅读代码的时候，应该先关注 public 方法
///
/// protected, 定义为 protected 的字段和方法可以被子类访问，以及子类的子类
///
/// package,   包作用域是指一个类允许访问同一个 package 的没有 public、 private 修饰的 class，以及没有 public、protected、private 修饰的字段和方法。
///
/// 最佳实践    如果不确定是否需要 public ，就不声明为 public ，即尽可能少地暴露对外的字段和方法。
///            把方法定义为 package 有助于测试，因为测试类和被测试类只要位于同一个 package，测试代码就可以访问被测试类的 package 权限方法。
///            一个.java 文件只能包含一个 public 类，但可以包含多个非 public 类。如果有 public 类，文件名必须和 public 类的名字相同。
class Outer {

    private String name;

    private static String NAME = "OUTER";

    Outer(String name) {
        this.name = name;
    }

    protected void hi() {
    }

    private static void hello() {
        System.out.println("private hello");
    }

    /// 29.1、内部类(Inner class)
    ///
    /// 它与普通类有个最大的不同， 就是Inner Class的实例不能单独存在，必须依附于一个Outer Class的实例。
    class Inner {
        void hi() {
            /// Java支持嵌套类，如果一个类内部还定义了嵌套类，那么嵌套类拥有访问 private 的权限
            Outer.hello();
        }
    }

    /// 29.2、匿名类(Anonymous Class)
    ///
    /// 一种定义Inner Class的方法，它不需要在Outer Class中明确地定义这个Class，而是在方法内部，通过匿名类(Anonymous Class)来定义。
    void asyncHello() {
        // Runnable 本身是接口， 接口是不能实例化的，所以这里实际上是定义了一个实现了 Runnable 接口的匿名类，
        // 并且通过 new 实例化该匿名类，然后转型为 Runnable
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // 匿名类和 Inner Class一样，可以访问Outer Class的 private 字段和方法。
                // 之所以我们要定义匿名类，是因为在这里我们通常不关心类名，比直接定义Inner Class可以少写很多代码。
                // 观察Java编译器编译后的文件可以发现， Outer 类被编译为 Outer.class ，而匿名类被编译为 Outer$1.class 。
                // 如果有多个匿名类，Java编译器会将每个匿名类依次命名为 Outer$2.class、Outer$3.class
                System.out.println("匿名类Hello, " + Outer.this.name);
            }
        };
        new Thread(r).start();

        /// 除了接口外，匿名类也完全可以继承自普通类
//        HashMap<String, String> map1 = new HashMap<>(); // 普通HashMap实例
//        HashMap<String, String> map2 = new HashMap<>() {}; // 匿名类
//        HashMap<String, String> map3 = new HashMap<>() { // 匿名类
//            {
//                put("A", "1");
//                put("B", "2");
//            }
//        };
//        System.out.println(map3.get("A"));
    }

    /**
     * 29.3、静态内部类(Static Nested Class)
     *
     * 静态内部类与内部类(Inner Class)有很大不同，他不再依附Outer实例，而是一个完全独立的类，因此无法引用Outer.this
     */
    static class StaticNested {
        void hello() {
            System.out.println("Hello, " + Outer.NAME);
        }
    }

    static void func () {
        /// 要实例化一个 Inner，我们必须首先创建一个 Outer 的实例，然后，调用 Outer 实例的 new 来创建 Inner 实例
        Outer outer = new Outer("A");
        /// 这是因为Inner Class除了有一个 this 指向它自己，还隐含地持有一个Outer Class实例，
        /// 可以用 Outer.this 访问这个实例。所以，实例化一个Inner Class不能脱离Outer实例。
        ///
        /// 观察Java编译器编译后的 文件可以发现， Outer 类被编译为 Outer.class ，而 Inner 类被编译为 Outer$Inner.class。
        Outer.Inner inner = outer.new Inner();
        inner.hi(); // 内部类(Inner Class)

        outer.asyncHello(); // 匿名类(Anonymous Class)

        Outer.StaticNested s = new Outer.StaticNested();
        s.hello(); // 静态内部类(Static Nested Class)
    }
}

class OuterChild extends Outer {

    OuterChild(String name) {
        super(name);
    }

    void test() {
        hi(); // 可以访问protected方法
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="30、classpath、jar包和模块">

/// 30、classpath
///
/// classpath 是JVM用到的一个环境变量，它用来指示JVM如何搜索class。
/// 因为Java是编译型语言，源码文件是.java，而编译后的.class文件才是真正可以被JVM执行的字节码。
/// 因此，JVM需要知道，如果要加载一个abc.xyz.Hello的类，应该去哪搜索对应的Hello.class文件。
/// 所以，classpath就是一组目录的集合，它设置的搜索路径与操作系统相关。
/// 例如，在Windows系统上，用;分隔，带空格的目录用""括起来，可能长这样：
///
/// C:\work\project1\bin;C:\shared;"D:\My Documents\project1\bin"
///
/// 在 macOS 和 Linux 系统上，用:分隔，可能长这样：
///
/// /usr/shared:/usr/local/bin:/home/liufan/bin
///
/// 现在我们假设classpath是 .;C:\work\project1\bin;C:\shared 当JVM在加载 abc.xyz.Hello 这个类时，会依次查找：
/// <当前目录>\abc\xyz\Hello.class
/// C:\work\project1\bin\abc\xyz\Hello.class
/// C:\shared\abc\xyz\Hello.class
///
/// 注意到 . 代表当前目录。如果JVM在某个路径下找到了对应的class文件，就不再往后继续搜索。如果所有路径下都没有找到，就报错。
///
/// classpath的设定方法有两种：
/// - 在系统环境变量中设置classpath环境变量，不推荐，那样会污染整个系统环境；
/// - 在启动JVM时设置classpath变量，推荐。
///
/// java -classpath .;C:\work\project1\bin;C:\shared abc.xyz.Hello
///
/// 或者使用 -cp 的简写：
/// java -cp .;C:\work\project1\bin;C:\shared abc.xyz.Hello
///
/// 没有设置系统环境变量，也没有传入-cp 参数，那么JVM默认的classpath为 . ，即当前目录：
/// java abc.xyz.Hello
/// 上述命令告诉JVM只在当前目录搜索Hello.class。
///
/// ***在IDE中运行Java程序，IDE自动传入的-cp参数是当前工程的bin目录和引入的jar包。***
///
/// 我们在自己编写的class中，会引用Java核心库的class，例如，String、ArrayList等。不需要告诉JVM如何去Java核心库查找class，JVM怎么可能笨到连自己的核心库在哪都不知道！
/// 不要把任何Java核心库添加到classpath中！JVM根本不依赖classpath加载核心库！
///
/// 更好的做法是，不要设置classpath！默认的当前目录 . 对于绝大多数情况都够用了。
/// 假设我们有一个编译后的Hello.class，它的包名是com.example，当前目录是C:\work，那么，目录结构必须如下：
///
/// C:\work
/// └─ com
///    └─ example
///       └─ Hello.class
///
/// 运行这个Hello.class必须在当前目录下使用如下命令：
///
/// $ C:\work> java -cp . com.example.Hello
/// JVM根据classpath设置的. 在当前目录下查找com.example.Hello，即实际搜索文件必须位于com/example/Hello.class。如果指定的.class文件不存在，或者目录结构和包名对不上，均会报错。

/// 30.1、jar包
///
/// 如果有很多.class文件，散落在各层目录中，肯定不便于管理。如果能把目录打一个包，变成一个文件，就方便多了。
/// jar包就是用来干这个事的，它可以把package组织的目录层级，以及各个目录下的所有文件（包括.class文件和其他文件）都打成一个jar文件，这样一来，无论是备份，还是发给客户，就简单多了。
/// jar包实际上就是一个zip格式的压缩文件，而jar包相当于目录。如果我们要执行一个jar包的class，就可以把jar包放到 classpath中：
///
/// java -cp ./hello.jar abc.xyz.Hello
/// 这样JVM会自动在hello.jar文件里去搜索某个类。
///
/// jar包制作参考「30.2、模块」的「0130-oop-module 项目」查看

/// 30.2、模块
///
/// 自 Java 9 引入模块之后，Java的class访问权限 public、protected、private和默认的包访问权限，需要做些调整。
/// 确切地说，class的这些访问权限只在一个模块内有效，模块和模块之间，例如a模块要访问b模块的某个class，必要条件是b模块明确地导出了可以访问的包。
///
/// 举个例子:我们编写的模块 hello.world 用到了模块 java.xml 的一个类 javax.xml.XMLConstants, 我们之所以能直接使用这个类，
/// 是因为模块 java.xml 的 moduel-info.java 中声明若干导出：
///
/// module java.xml {
///     exports java.xml;
///     exports javax.xml.catalog;
///     exports javax.xml.datatype;
///     ...
/// }
///
/// 只有它声明的导出的包，外部代码才被允许访问。
///
/// 更多内容打开 0130-oop-module 项目查看
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="31、字符串操作大全">

/// String 是一个引用类型，本身也是一个 class。因为字符串太常用 Java 对 String 有特殊处理，可以用 "..." 表示一个字符串。
/// String s1 = "Hello";
/// 实际上字符串在 String 内部是通过 char[] 来表示的，因此下面写法也是可以的
/// String s2 = new String(new char[] { 'H', 'e', 'l', 'l', 'o' });
///
/// Java字符串的一个重要特点就是字符串不可变。这种不可变性是通过内部的 private final char[] 字段，以及没有任何修改 char[] 的方法实现的
class StringFunc {
    static void stringComparison() {
        /// 31.1、字符串比较
        String s1 = "hello";
        String s2 = "hello";
        System.out.println(s1 == s2);      // true
        System.out.println(s1.equals(s2)); // true
        /// 从表面上看，两个字符串比较结果都为 true，但实际上那只是Java编译器在编译期，会自动把所有相同的字符串当作一个对象放入常量池，自然 s1 和 s2 的引用就是相同的。
        /// 所以这种 == 比较返回 true 纯属巧合，像以下这样写，就不行了
        String s3 = "HELLO".toLowerCase();
        System.out.println(s1 == s3);      // false
        System.out.println(s1.equals(s3)); // true

        /// 31.2、搜索字符串子串
        "Hello".indexOf("l"); // 2
        "Hello".lastIndexOf("l"); // 3
        "Hello".startsWith("He"); // true
        "Hello".endsWith("lo"); // true

        /// 31.3、提取字符串子串
        "Hello".substring(2); // "llo"
        System.out.println("Hello".substring(2, 4)); // "ll"

        /// 31.4、去除首尾空白字符
        ///       trim() 并没有改变字符串的内容，而是返回了一个新字符串
        System.out.println(" \tHello\r\n ".trim()); // "Hello"
        ///       strip() 方法也可以移除字符串首尾空白字符。和 trim() 不同的是，类似中文的空格字符 \u3000 也会被移除
        System.out.println("\u3000 Hello呀\u3000".strip()); // "Hello呀"
        System.out.println(" Hello 去左空格 ".stripLeading()); // "Hello 去左空格 "
        System.out.println(" Hello 去右空格 ".stripTrailing()); // " Hello去右空格"

        /// 31.5、判断字符串是否为空和空白字符串
        "".isEmpty(); // true，因为字符串长度为0
        " ".isEmpty(); // false，因为字符串长度不为0
        " \n".isBlank(); // true，因为只包含空白字符
        " Hello ".isBlank(); // false，因为包含非空白字符

        /// 31.6、替换子字符串
        String s = "hello";
        s.replace('l', 'w'); // "hewwo"，所有字符'l'被替换为'w'
        s.replace("ll", "~~"); // "he~~o"，所有子串"ll"被替换为"~~"
        ///       正则表达式替换
        String ss = "A,,B;C ,D";
        System.out.println(ss.replaceAll("[\\,\\;\\s]+", ",")); // "A,B,C,D"

        /// 31.7、分割字符串
        String sss = "A,B,C,D";
        String[] sssArray = sss.split("\\,"); // { "A", "B", "C", "D" }
        System.out.println(Arrays.toString(sssArray));

        /// 31.8、拼接字符串
        String[] arr = { "A", "B", "C" };
        System.out.println(String.join("***", arr));

        /// 31.9、格式化字符串，格式化输出参考 8.1 节（格式化输出）
        String formatStr = "Hi %s, your score is %d!";
//        System.out.println(formatStr.formatted("Alice", 80));
        System.out.println(String.format("Hi %s, your score is %.2f!", "Bob", 59.5));

        /// 31.10、类型转换，任意基本类型或引用类型转换为字符串
        String.valueOf(123); // 123
        String.valueOf(45.67); // "45.67"
        String.valueOf(true); // "true"
        String.valueOf(new Object()); // 类似java.lang.Object@636be97c

        /// 31.10.1、把字符串转换为其他类型
        // 转为 int 类型
        int n1 = Integer.parseInt("123"); // 123
        int n2 = Integer.parseInt("ff", 16); // 十六进制转换，255
        // 转换为 boolean 类型
        boolean b1 = Boolean.parseBoolean("true"); // true
        boolean b2 = Boolean.parseBoolean("false");  // false
        System.out.println(Boolean.parseBoolean("TRUE")); // true
        System.out.println(Boolean.parseBoolean("TruE")); // true
        System.out.println(Boolean.parseBoolean("FALSE")); // false
        System.out.println(Boolean.parseBoolean("YES")); // false，识别不了的字符串都是false

        /// 31.11、与 char[] 互相转换
        char[] cs = "Hello".toCharArray(); // String -> char[]
        String csStr = new String(cs);     // char[] -> String
        System.out.println(csStr);
        // 修改了 char[] 数组， String 并不会改变
        // 这是因为通过 new String(cs) 创建新的 String 实例时，它并不会直接引用传入的 char[]，而是会复制一份
        cs[0] = 'X';
        System.out.println(csStr);

        /// 31.11.1、如果传入的对象有可能改变，我们需要复制而不是直接引用
        int[] scores = new int[] { 88, 77, 51, 66 };
        Score sc = new Score(scores);
        sc.printScores(); // [88, 77, 51, 66]
        scores[2] = 99;
        sc.printScores(); // [88, 77, 99, 66]，这不是一个安全的设计

        /// 31.12、字符编码，在Java内存中String和char总是以Unicode编码表示
        ///
        /// ASCII，美国国家标准学会制定了一套英文字母、数字和常用符号的编码，它占用1个字节，编码范围从0-127，最高位始终为0
        /// GB2312，要把汉字纳入计算机编码，很显然一个字节是不够的，GB2312使用2字节表示一个汉字，其中第一个字节的最高位始终为1，以便和 ASCII 编码区分开。
        /// Unicode，为了统一全球所有语言的编码，全球统一码联盟发布了Unicode编码，它把世界上主要语言都纳入同一个编码，这样中文、日文、韩文和其他语言就不会冲突。编码需要两个或者更多字节表示。
        /// UTF-8，因为英文字符的Unicode编码高字节总是00，大量英文文本会浪费空间，所以出现了UTF-8编码。
        ///        它是一种变长编码，用来把固定长度的Unicode编码变成1~4字节的变长编码。
        ///        UTF-8编码的另一个好处是容错能力强。如果传输过程中某些字符出错，不会影响后续字符，
        ///        因为UTF-8编码依靠高字节位来确定一个字符究竟是几个字节，它经常用来作为传输编码。
        byte[] b1Str = "Hello".getBytes(); // 按系统默认编码转换，不推荐
//        byte[] b2Str = "Hello".getBytes("UTF-8"); // 按UTF-8编码转换
//        byte[] b3Str = "Hello".getBytes("GBK"); // 按GBK编码转换
        /// 转换为 byte[] 优先考虑UTF-8编码
        byte[] b4Str = "Hello".getBytes(StandardCharsets.UTF_8); // 按UTF-8编码转换
        // 把已知的 byte[] 转换为 String
        System.out.println(new String(b4Str, StandardCharsets.UTF_8));

        // 对于不同版本的JDK，String 类在内存中有不同的优化方式。
        // 具体来说，早期JDK版本的 String 总是以 char[] 存储
        // public final class String {
        //    private final char[] value;
        //    private final int offset;
        //    private final int count;
        // }
        //
        // 而较新的JDK版本 String 则以 byte[] 存储。如果 String 仅包含 ASCII 字符，则每个 byte 存储一个字符，
        // 否则，每两个 byte 存储一个字符。
        // public final class String {
        //    private final byte[] value;
        //    private final byte coder; // 0 = LATIN1, 1 = UTF16
        // }
    }
}

class Score {
    private int[] scores;
    public Score(int[] scores) {
        this.scores = scores; // 这不是一个安全的设计

        /*
        if (scores == null) {
            this.scores = new int[0];
        } else {
//            this.scores = Arrays.copyOf(scores, scores.length);
            // 性能略优，底层native方法
            this.scores = new int[scores.length];
            System.arraycopy(scores, 0, this.scores, 0, scores.length);
        }
        */
    }

    public void printScores() {
        System.out.println(Arrays.toString(scores));
    }

    // 示例：安全的获取数组方法
    public int[] getScores() {
        return Arrays.copyOf(this.scores, this.scores.length);
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="31.12、可变字符串 StringBuilder 和 链式方法">

/**
 * 31.12、StringBuilder（可变字符串）
 */
class StringBuilderFunc {
    static void func() {
        // Java编译器对 String 做了特殊处理，使得我们可以直接用 + 拼接字符串。
//        String s = "";
//        for (int i = 0; i < 1000; i++) {
//            s = s + "," + i;
//        }
        // 虽然可以直接拼接字符串，但是，在循环中，每次循环都会创建新的字符串对象，然后扔掉旧的字符串。
        // 这样，绝大部分字符串都是临时对象，不但浪费内存，还会影响GC效率。
        //
        // 注意⚠️
        // 对于普通的字符串 + 操作，并不需要我们将其改写为 StringBuilder，因为Java编译器在编译时就自动把多个连续的 + 操作
        // 编码为 StringConcatFactory 的操作。在运行期，StringConcatFactory 会自动把字符串连接操作优化为数组复制或者 StringBuilder 操作
        //
        // 你可能还听说过 StringBuffer，这是Java早期的一个 StringBuilder 线程安全版本，它通过同步来保证多个线程操作
        // StringBuffer 也是安全的，但是同步会带来执行速度的下降。现在很少使用。

        /// 为了能高效拼接字符串，Java标准库提供了 StringBuilder，它是一个可变对象，可以预分配缓冲区，
        /// 这样，往 StringBuilder 中新增字符时，不会创建新的临时对象
        StringBuilder sb = new StringBuilder(1024);
        /// 支持链式操作
        sb.append("Mr ")
                .append("Bob")
                .append("!")
                .insert(0, "Hello, ");
        for (int i = 0; i < 10; i++) {
            sb.append(',');
            sb.append(i);
        }
        System.out.println(sb.toString());

        // 自定义链式方法
        Adder adder = new Adder();
        adder.add(5)
                .inc()
                .add(4);
        System.out.println(adder.value());
    }
}

/// 31.12.1、自定义链式方法
class Adder {
    private int sum = 0;
    public Adder add(int n) {
        sum += n;
        return this;
    }
    public Adder inc() {
        sum ++;
        return this;
    }
    public int value() {
        return sum;
    }
}

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="31.13、StringJoiner 分隔符拼接字符串数组">

/**
 * 31.13、StringJoiner, 分隔符拼接字符串数组
 */
class StringJoinerFunc {
    static void func() {
        // 要高效拼接字符串，应该使用 StringBuilder
        String[] names = { "Bob", "Alice", "Grace" };
//        var sb = new StringBuilder();
//        sb.append("Hello ");
//        for (String name : names) {
//            sb.append(name).append(", ");
//        }
//        // 注意去掉最后的", "
//        sb.delete(sb.length() - 2, sb.length());
//        sb.append("!");
        // 类似上面用分隔符拼接数组的需求很常见，所以Java标准库还提供了一个 StringJoiner 来干这个事
        var sj = new StringJoiner(",", "Hello ", "!");
        for (String name : names) {
            sj.add(name);
        }
        System.out.println(sj.toString());

        /// 31.13.1、String.join()
        /// 这个方法在内部使用了 StringJoiner 来拼接字符串，在不需要指定“开头”和“结尾”的时候用这个更方便
        var s = String.join("-", names);
        System.out.println(s);
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="32、JavaBean">

/// 32、JavaBean
/// 在Java中，有很多 class 的定义都符合这样的规范：
/// - 若干 private 实例字段;
/// - 通过 public 方法来读写实例字段。
class PersonJavaBean {
    /// 我们通常把一组对应的读方法( getter )和写方法( setter )称为属性( property )，如 name 属性
    private String name;
    /// boolean 字段比较特殊，它的getter方法一般命名为 isXyz()
    private boolean child;
    /// 只有 getter 的属性称为只读属性(read-only)，只读属性很常见
    private int age;
    /// 只有 setter 的属性称为只写属性(write-only)，只写属性不常见
    private String nickname;

    /// getter方法 getXyz
    public String getName() { return this.name; }
    /// setter方法 setXyz
    public void setName(String name) { this.name = name; }

    public boolean isChild() { return child; }
    public void setChild(boolean child) { this.child = child; }

    public int getAge() { return age; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    /// 属性只需要定义 getter 和 setter 方法，不一定需要对应的字段。
    public boolean isAdult() { return age >= 18; }

    /*
    // 枚举JavaBean属性，Android 的 Java 不支持

    static void func() {
        // import java.beans.*;
        BeanInfo info = Introspector.getBeanInfo(PersonJavaBean.class); // 注意 class 属性是从 Object 继承 的 getClass() 方法带来的。
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            System.out.println(pd.getName());
            System.out.println("  " + pd.getReadMethod());
            System.out.println("  " + pd.getWriteMethod());
        }
    }
    */
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="33、常用工具类(Math、HexFormat、Ramdom、SecureRandom)">
/// 33、常用工具类
class ToolFunc {
    /// 33.1、Math
    ///
    /// Java标准库还提供了一个 StrictMath，它提供了和 Math 几乎一模一样的方法。这两个类的区别在于，由于浮点数计算存在误差，
    /// 不同的平台(例如x86和ARM)计算的结果可能不一致(指误差不同)，因此，StrictMath 保证所有平台计算结果都是完全相同的。
    /// 而 Math 会尽量针对平台优化计算速度，所以，绝大多数情况下，使用 Math 就足够了。
    static void mathFunc() {
        /// 绝对值
        Math.abs(-100);    // 100
        Math.abs(-1.2f); // 1.2

        /// 取最大或最小值
        Math.max(100, 99);  // 100
        Math.min(1.2, 2.3); // 1.2

        /// 计算x^y次方
        Math.pow(2, 10); // 2的10次方=1024

        /// 计算√￣x
        Math.sqrt(2); // 1.414...

        /// 计算e^x次方
        Math.exp(2); // 7.389...

        /// 计算以e为底的对数
        Math.log(4); // 1.386...

        /// 计算以10为底的对数:
        Math.log10(100); // 2

        /// 三角函数
        Math.sin(3.14); // 0.00159...
        Math.cos(3.14); // -0.9999...
        Math.tan(3.14); // -0.0015...
        Math.asin(1.0); // 1.57079...
        Math.acos(1.0); // 0.0

        /// Math还提供了几个数学常量
        double pi = Math.PI; // 3.14159...
        double e = Math.E; // 2.7182818...
        Math.sin(Math.PI / 6); // sin(π/6) = 0.5

        /// 生成一个随机数x，x的范围是 0 <= x < 1，[0,1)
        Math.random(); // 0.53907... 每次都不一样

        /// 如果我们要生成一个区间在 [MIN, MAX) 的随机数，可以借助 Math.random() 实现，计算如下：
        double x = Math.random(); // x的范围是[0,1)
        double min = 10;
        double max = 50;
        double y = x * (max - min) + min; // y的范围是[10,50)
    }

    /// 33.2、HexFormat
    ///
    /// 处理 byte[] 数组时，我们经常需要与十六进制字符串转换，自己写起来比较麻烦，用Java标准库提供的 HexFormat 则可以方便地帮我们转换。
    static void hexFormat() {
//        byte[] data = "Hello".getBytes();
//        HexFormat hf = null;
//        hf = HexFormat.of();
//        String hexData = hf.formatHex(data); // 48656c6c6f
//        System.out.println(hexData);
//
//        /// 定制转换格式
//        HexFormat hf1 = HexFormat.ofDelimiter(" ").withPrefix("0x").withUpperCase();
//        System.out.println(hf1.formatHex(data)); //0x48 0x65 0x6C 0x6C 0x6F
//
//        /// 从十六进制字符串 转换 byte[] 数组
//        byte[] bs = HexFormat.of().parseHex("48656c6c6f");
//        System.out.println(new String(bs));
    }

    /// 33.3、Random
    ///
    /// Random 用来创建伪随机数。所谓伪随机数，是指只要给定一个初始的种子，产生的随机数序列是完全一样的。
    static void randomFunc() {
        Random r = new Random();
//        System.out.println(r.nextInt()); // 每次都不一样
        System.out.println(r.nextInt(10)); // 生成一个[0,10)之间的int，必须填入正数，填负数报错

        /// 有童鞋问，每次运行程序，生成的随机数都是不同的，没看出 伪随机数 的特性来。
        /// 这是因为我们创建 Random 实例时，如果不给定种子，就使用系统当前时间戳作为种子，因此每次运行时，种子不同，得到的伪随机数序列就不同。
        ///
        /// 前面我们使用的 Math.random() 实际上内部调用了 Random 类，所以它也是伪随机数，只是我们无法指定种子。
        Random r1 = new Random(12345);
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt(100));
        }
        
        /// 33.3.1、SecureRandom，安全随机数
        ///
        /// 有伪随机数，就有真随机数。实际上真正的真随机数只能通过量子力学原理来获取，而我们想要的是一个不可预测的安全的随机数
        ///
        /// SecureRandom 无法指定种子，它使用RNG(random number generator)算法。JDK的 SecureRandom 实际上有多种不同的底层实现，
        /// 有的使用安全随机种子加上伪随机数算法来产生安全的随机数，
        /// 有的使用真正的随机数生成器。实际使用的时候，可以优先获取高强度的安全随机数生成器，如果没有提供，再使用普通等级的安全随机数生成器
        ///
        /// 在密码学中，安全的随机数非常重要。如果使用不安全的伪随机数，所有加密体系都将被攻破。因此，时刻牢记必须使用 SecureRandom 来产生安全的随机数。
//        SecureRandom sr = null;
//        try {
//            sr = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器
//        } catch (NoSuchAlgorithmException e) {
//            sr = new SecureRandom(); // 获取普通的安全随机数生成器
//        }
//        byte[] buffer = new byte[16];
//        sr.nextBytes(buffer); // 用安全随机数填充buffer
//        System.out.println(Arrays.toString(buffer));
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="34、异常">

/// 34、异常
///
/// 异常 Throwable 目前有2个体系
///
/// 1. Error 表示严重的错误，程序对此一般无能为力，例如：
///    - OutOfMemoryError：内存耗尽
///    - NoClassDefFoundError：无法加载某个Class
///    - StackOverflowError：栈溢出
/// 1. Exception 则是运行时的错误，它可以被捕获并处理。
///    - 某些异常是应用程序逻辑处理的一部分，应该捕获并处理，例如：
///      - NumberFormatException：数值类型的格式错误
///      - FileNotFoundException：未找到文件
///      - SocketException：读取网络失败
///    - 还有一些异常是程序逻辑编写不对造成的，应该修复程序本身。例如:
///      - NullPointerException：对某个 null 的对象调用方法或字段
///      - IndexOutOfBoundsException：数组索引越界
///
/// Exception 又分为两大类：
/// - RuntimeException 以及它的子类；
/// - 非 RuntimeException (包括 IOException、ReflectiveOperationException 等等)。
///   - 编译器对RuntimeException及其子类不做强制捕获要求，不是指应用程序本身不应该捕获并处理RuntimeException。是否需要捕获，具体问题具体分析。
///
/// Java规定:
/// - 必须捕获的异常，包括 Exception 及其子类，但不包括 RuntimeException 及其子类，这种类型的异常称为Checked Exception。
/// - 不需要捕获的异常，包括 Error 及其子类，RuntimeException 及其子类。
///
/// Java标准库定义的常用异常：
///
/// Exception
/// ├─ RuntimeException
/// │  ├─ NullPointerException 空指针异常
/// │  ├─ IndexOutOfBoundsException
/// │  ├─ SecurityException
/// │  └─ IllegalArgumentException
/// │     └─ NumberFormatException
/// ├─ IOException
/// │  ├─ UnsupportedCharsetException
/// │  ├─ FileNotFoundException
/// │  └─ SocketException
/// ├─ ParseException
/// ├─ GeneralSecurityException
/// ├─ SQLException
/// └─ TimeoutException
class ThrowableFunc {
    static void func() {
        byte[] bs = toGBK("中文");
        System.out.println(Arrays.toString(bs));

        try {
            byte[] bs1 = toGBKThrows("中文");
            System.out.println(Arrays.toString(bs1));
        } catch (UnsupportedEncodingException e) {
            /// 捕获异常后不处理的方式是非常不好的，即使真的什么也做不了，也要先把异常记录下来
            ///
            /// 所有异常都可以调用 printStackTrace() 方法打印异常栈，这是一个简单有用的快速打印异常的方法。
            e.printStackTrace();
        }
    }
    static byte[] toGBK(String s) {
        try {
            return s.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            // 如果系统不支持GBK编码，会捕获到UnsupportedEncodingException:
            e.printStackTrace();
            return s.getBytes(); // 尝试使用默认编码
        }
    }
    static byte[] toGBKThrows(String s) throws UnsupportedEncodingException {
        return s.getBytes("GBK");
    }

    /// 34.1、多 catch 语句
    ///
    /// JVM捕获到异常后，从上到下匹配 catch 语句，匹配到某个 catch，然后不再继续匹配
    /// - 因此 catch 的顺序非常重要：子类必须写在前面
    static void func1() {
        /*
        try {
             process1();
             process2();
             process3();
        } catch (IOException e) {
             System.out.println("IO error");
        } catch (UnsupportedEncodingException e) { // 永远捕获不到，正确写法是 子类 放在前面
             System.out.println("Bad encoding");
        }
        */
    }

    /// 34.2、finally 语句
    /// - finally 语句不是必须的，可写可不写
    /// - finally 总是最后执行
    ///
    /// 如果没有发生异常，就正常执行 try { ... } 语句块，然后执行 finally。如果发生了异常，就中断执行 try { ... } 语句块，
    /// 然后跳转执行匹配的 catch 语句块，最后执行 finally。
    static void func2() {
        /*
        try {
             process1();
             process2();
             process3();
             System.out.println("无论如何都希望执行的语句");
        } catch (UnsupportedEncodingException e) {
             System.out.println("Bad encoding");
             System.out.println("无论如何都希望执行的语句");
        } catch (IOException e) {
             System.out.println("IO error");
             System.out.println("无论如何都希望执行的语句");
        }
        */
        /// 无论是否有异常发生，如果我们都希望执行一些语句，例如清理工作，怎么写?
        /// 上面那样写，重复代码太多
//        try {
//            process1();
//            process2();
//            process3();
//        } catch (UnsupportedEncodingException e) {
//            System.out.println("Bad encoding");
//        } catch (IOException e) {
//            System.out.println("IO error");
//        } finally {
//            System.out.println("无论如何都希望执行的语句");
//        }
    }

    /// 34.3、方法声明了可能抛出的异常，所以可以不写 catch
    static void process(String file) throws IOException {
        try {
            // ...
        } finally {
            System.out.println("无论如何都希望执行的语句");
        }
    }

    /// 34.4、捕获多种异常
    static void catchingMultipleExceptions() {
        /*
        try {
             process1();
             process2();
             process3();
        } catch (IOException e) {
             System.out.println("Bad input");
        } catch (NumberFormatException e) {
             System.out.println("Bad input");
        } catch (Exception e) {
             System.out.println("Unknown error");
        }
        */
        /// IOException 和 NumberFormatException 的代码块是相同的，所以我们可以把它两用 | 合并到一起
//        try {
//            process1();
//            process2();
//            process3();
//        } catch (IOException | NumberFormatException e) {
//            System.out.println("Bad input");
//        } catch (Exception e) {
//            System.out.println("Unknown error");
//        }
    }

    /// 34.5、异常的传播
    ///
    /// 当某个方法抛出了异常时，如果当前方法没有捕获异常，异常就会被抛到上层调用方法，直到遇到某个 try...catch 被捕获为止
    static void exceptionPropagation() {
        try {
            process1();
        } catch (Exception e) {
            e.printStackTrace(); // 对于调试错误非常有用
        }
    }

    static void process1() {
        process2();
    }

    static void process2() {
        Integer.parseInt(null); // 会抛出NumberFormatException
    }

    /// 34.6、抛出异常
    static void throwException() {
        try {
            process1(null);
        } catch (Exception e) {
            e.printStackTrace();
            // 这说明新的异常丢失了原始异常信息，我们已经看不到原始异常 NullPointerException 的信息了。
            // 为了能追踪到完整的异常栈，在构造异常的时候，把原始的 Exception 实例传进去，新的 Exception 就可以持有原始 Exception 信息。对上述代码改进如下:
            // throw new IllegalArgumentException(e);
        }

        /// 控制台输出结果：
        /// catched
        /// finally
        /// 异常信息
        ///
        /// 第一行打印了 catched ，说明进入了 catch 语句块。第二行打印了 finally ，说明执行了 finally 语句块。
        /// 因此，在 catch 中抛出异常，不会影响 finally 的执行。JVM会先执行 finally ，然后抛出异常。
//        try {
//            Integer.parseInt("abc");
//        } catch (Exception e) {
//            System.out.println("catched");
//            throw new RuntimeException(e);
//        } finally {
//            System.out.println("finally");
//        }

        /// 34.6.1、异常屏蔽
        ///
        /// finally 抛出异常后，原来在 catch 中准备抛出的异常就“消失”了，因为只能抛出一个异常。没有被抛出的异常称为“被屏蔽”的异常(Suppressed Exception)。
//        try {
//            Integer.parseInt("abc");
//        } catch (Exception e) {
//            System.out.println("catched");
//            throw new RuntimeException(e);
//        } finally {
//            System.out.println("finally");
//            throw new IllegalArgumentException();
//        }

        /// 34.6.2、保存原始异常，一起抛出
        /// 但是，在极少数的情况下，我们需要获知所有的异常。
        ///
        /// 绝大多数情况下，在 finally 中不要抛出异常。因此，我们通常不需要关心 Suppressed Exception
//        Exception origin = null;
//        try {
//            Integer.parseInt("abc");
//        } catch (Exception e) {
//            origin = e;
//            throw e;
//        } finally {
//            Exception e = new IllegalArgumentException();
//            if (origin != null) {
//                e.addSuppressed(origin); // 把原始异常添加进来
//                // e.getSuppressed(); // 可以获取所有的 Suppressed Exception
//            }
//            throw e;
//        }
    }

    static void process2(String s) {
        if (s == null) {
//            NullPointerException e = new NullPointerException();
//            throw e;
            throw new NullPointerException(); // 绝大部分抛出异常的代码都会合并写成一行
        }
    }

    static void process1(String s) {
        try {
            process2(s);
        } catch (NullPointerException e) {
            // 如果一个方法捕获了某个异常后，又在 catch 子句中抛出新的异常，就相当于把抛出的异常类型 “转换”了
//            throw new IllegalArgumentException();
            /// 在代码中获取原始异常可以使用 Throwable.getCause() 方法。如果返回 null ，说明已经是“根 异常”了。
            System.out.println(e.getCause()); // null
            /// 捕获到异常并再次抛出时，一定要留住原始异常，否则很难定位第一案发现场!
            throw new IllegalArgumentException(e);
        }
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="34.7、NullPointerException">
/// 34.7、NullPointerException
///
/// 空指针异常，如果一个对象为 null ，调用其方法或访问其字段就会产生，这个异常通常是由JVM抛出的。
/// 指针这个概念实际上源自C语言，Java语言中并无指针。我们定义的变量实际上是引用，Null Pointer更确切地说是Null Reference，不过两者区别不大。
class NullPointerExceptionFunc {
    /// 如果遇到 NullPointerException，我们应该如何处理?首先，必须明确 NullPointerException 是一种代码逻辑错误。
    /// 遵循原则是早暴露，早修复，严禁使用 try-catch 来隐藏这种编码错误。
    ///
    /// 好的编码习惯可以极大地降低 NullPointerException 产生：
    /// - 字符串使用 "" 而不是默认的 null
    /// - 数组使用 XXXClass[0] 而不是默认的 null
    static String[] func() {
        String s =  ""; // 变量初始化
        return new String[0]; // 返回值为空数组
    }

     static class Person {
        private String name = ""; // 成员变量初始化
        String[] names = new String[2];
        Address address = new Address();
    }

    /// 34.7.1、如果调用方一定要根据 null 判断，比如返回 null 表示文件不存在，那么考虑返回 Optional<T>
    ///
    /// 这样调用方必须通过 Optional.isPresent() 判断是否有结果。
//    static Optional<String> readFromFile(String file) {
//        if (!fileExist(file)) {
//            return Optional.empty();
//        }
//        ...
//    }

    /// 34.7.2、定位 NullPointerException
    ///
    /// 如果产生了 NullPointerException，例如，调用 a.b.c.x() 时产生了 ，原因可能是:
    /// - a 是 null；
    /// - a.b 是 null；
    /// - a.b.c 是 null
    ///
    /// 确定到底是哪个对象是 null 以前只能打印这样的日志：
    /// System.out.println(a);
    /// System.out.println(a.b);
    /// System.out.println(a.b.c);
    ///
    /// 从Java 14开始，如果产生了 NullPointerException，JVM可以给出详细的信息告诉我们 null 对象到底是谁。我们来看例子:
    static void nullPointerFunc() {
        Person p = new Person();
        System.out.println(p.address.city.toLowerCase());
        /// 这种增强的 NullPointerException 详细信息是Java 14新增的功能，但默认是关闭的，我们可以给JVM添加一个参数启用它：
        /// java -XX:+ShowCodeDetailsInExceptionMessages Main.java
    }

    static class Address {
        String city;
        String street;
        String zipcode;
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="34.8、自定义异常">

/// 34.8、自定义异常
///
/// 当我们在代码中需要抛出异常时，尽量使用JDK已定义的异常类型。例如，参数检查不合法，应该抛 IllegalArgumentException。
///
/// 自定义新的异常类型，一个常见的做法是自定义一个 BaseException 作为“根异常”，然后，派生出各种业务类型的异常。
/// BaseException 通常建议从 RuntimeException 派生。
class BaseException extends RuntimeException {
    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="35、断言 assert">

/// 35、断言 assert，是一种调试方式
///
/// 断言失败时会抛出 AssertionError 导致程序结束退出。因此，断言不能用于可恢复的程序错误，只应该用于开发和测试阶段。
///
/// 实际开发中，很少使用断言。更好的方法是编写单元测试 JUnit。
class AssertFunc {
    static void func() {
        double x = Math.abs(-123.45);
        assert x < 0 : "x must >= 0"; // 可选的断言消息，AssertionError 的时候会带上消息 "x must >= 0" ，更加便于调试。
        // 上述代码 x 肯定大于0，断言肯定失败。执行上述代码，发现程序并未抛出 AssertionError 而是正常打印了 x 的值。
        // 这是因为JVM默认关闭断言指令，即遇到 assert 语句就自动忽略了，不执行。
        // 要执行 assert 语句，必须给Java虚拟机传递 -enableassertions (可简写为 -ea )参数启用断言。所以，上述程序必须在命令行下运行才有效果:
        //
        // $ java -ea Main.java
        //
        // 还可以有选择地对特定地类启用断言，命令行参数是: -ea:com.liufan.learn_java.Main ，表示 只对 com.liufan.learn_java.Main 这个类启用断言。
        // 或者对特定地包启用断言，命令行参数是: -ea:com.liufan.learn_java... (注意结尾有3 个 . )，表示对 com.liufan.learn_java 这个包启动断言。
        System.out.println(x);
    }

    /// 35.1、对于可恢复的程序错误，不应该使用断言
    static void sort(int[] arr) {
//        assert arr != null;
        /// 应该抛出异常并在上层捕获
        if (arr == null) {
            throw new IllegalArgumentException("array cannot be null");
        }
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="36、日志、Commons Logging、Log4j">

/// 36、日志 JDK Logging
///
/// 什么是日志?日志就是 Logging，它的目的是为了取代 System.out.println()。
///
/// 输出日志，而不是用 System.out.println() ，有以下几个好处:
/// - 可以设置输出样式，避免自己每次都写 "ERROR: " + var ;
/// - 可以设置输出级别，禁止某些级别输出。例如，只输出错误日志;
/// - 可以被重定向到文件，这样可以在程序运行结束后查看日志;
/// - 可以按包名控制日志级别，只输出某些包打的日志......总之就是好处很多
///
/// JDK的 Logging 定义了7个日志级别，从严重到普通:
/// - SEVERE
/// - WARNING
/// - INFO，默认级别，因此INFO级别以下的日志，不会被打印出来。
/// - CONFIG
/// - FINE
/// - FINER
/// - FINEST
///
/// 使用Java标准库内置的Logging有以下局限，因此，Java标准库内置的Logging使用并不是非常广泛:
/// - Logging系统在JVM启动时读取配置文件并完成初始化，一旦开始运行 main() 方法，就无法修改配置;
/// - 配置不太方便，需要在JVM启动时传递参数 -Djava.util.logging.config.file=<config-file-name>
class LoggingFunc {
    static void func() {
        Logger logger = Logger.getGlobal();
        logger.info("start process...");
        logger.warning("memory is running out...");
        logger.fine("ignored.");
        logger.severe("process will be terminated...");
    }
}

/// 36.1、日志 Commons Logging，负责充当日志API
///
/// 和Java标准库提供的日志不同，Commons Logging是一个第三方日志库，它是由Apache创建的日志模块。
/// 默认情况下，Commons Loggin自动搜索并使用Log4j(Log4j是另一个流行的日志系统)，如果没有找到Log4j，再使用JDK Logging。
///
/// 更多内容打开 0136-commons-logging 项目查看

/// 36.2、Log4j，负责实现日志底层
///
/// 前面介绍了Commons Logging，可以作为“日志接口”来使用。而真正的“日志实现”可以使用 Log4j。
/// Log4j是一种非常流行的日志框架，是一个组件化设计的日志系统。
///
/// 更多内容打开 0136-commons-logging 项目查看
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="36.3、日志 SLF4J 和 Logback">

/// 36.3、SLF4J 和 Logback
///
/// 前面介绍了Commons Logging和Log4j这一对好基友，它们一个负责充当日志API，一个负责实现 日志底层，搭配使用非常便于开发。
/// 有的童鞋可能还听说过SLF4J和Logback。这两个东东看上去也像日志，它们又是啥?
/// 其实 SLF4J 类似于Commons Logging，也是一个日志接口，而 Logback 类似于Log4j，是一个日志的实现。
/*
 为什么有了Commons Logging和Log4j，又会蹦出来SLF4J和Logback? 这是因为Java有着非常悠久的开源历史，
 不但 OpenJDK 本身是开源的，而且我们用到的第三方库，几乎全部都是开源的。开源生态丰富的一个特点就是，同一个功能，可以找到若干种互相竞争的开源库。

 因为对Commons Logging的接口不满意，有人就搞了SLF4J。因为对Log4j的性能不满意，有人就搞了Logback。
 我们先来看看SLF4J对Commons Logging的接口有何改进。在Commons Logging中，我们要打印日志，有时候得这么写:

 int score = 99;
 p.setScore(score);
 log.info("Set score " + score + " for Person " + p.getName() + " ok.");

 拼字符串是一个非常麻烦的事情，所以SLF4J的日志接口改进成这样了:

 int score = 99;
 p.setScore(score);
 logger.info("Set score {} for Person {} ok.", score, p.getName());

 我们靠猜也能猜出来，SLF4J的日志接口传入的是一个带占位符的字符串，用后面的变量自动替换占位符，所以看起来更加自然。
 如何使用SLF4J?它的接口实际上和Commons Logging几乎一模一样:

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 class Main {
     final Logger logger = LoggerFactory.getLogger(getClass());
 }

 对比一下Commons Logging和SLF4J的接口:
 Commons Logging                       SLF4J
 org.apache.commons.logging.Log        org.apache.commons.logging.LogFactory
 org.slf4j.Logger                      org.slf4j.LoggerFactory

 不同之处就是 Log 变成了 Logger， LogFactory 变成了 LoggerFactory。
 */
/// 从目前的趋势来看，越来越多的开源项目从Commons Logging加Log4j转向了SLF4J加Logback。
///
/// 更多内容打开 0136-commons-logging 项目查看
// </editor-fold>

/// 37、反射
///
/// 反射就是 Reflection，Java的反射是指程序在运行期可以拿到一个对象的所有信息。
/// 反射是为了解决在运行期，对某个实例一无所知的情况下，如何调用其方法。