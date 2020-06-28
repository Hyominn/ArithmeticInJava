package com.op.demo;

import com.op.util.Print;

import java.util.Arrays;
import java.util.List;

import static com.op.util.Print.print;
import static com.op.util.Print.println;

/**
 * lambda 表达式
 * <p>
 * Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
 * <p>
 * Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
 * <p>
 * 使用 Lambda 表达式可以使代码变的更加简洁紧凑。
 *
 * @Author: NZY
 * @Date: 2020/6/26 9:34
 */
public class MyLambda {
    public static void main(String[] args) {
        MyLambda myLambda = new MyLambda();
        myLambda.myLambdaDemo();
        println();
        myLambda.myMethodReference();
        
    }
    
    void myLambdaDemo() {
        /*
        可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
        可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
        可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
        可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
        
        Lambda 表达式可以具有零个，一个或多个参数。
        可以显式声明参数的类型，也可以由编译器自动从上下文推断参数的类型。例如 (int a) 与刚才相同 (a)。
        参数用小括号括起来，用逗号分隔。例如 (a, b) 或 (int a, int b) 或 (String a, int b, float c)。
        空括号用于表示一组空的参数。例如 () -> 42。
        当有且仅有一个参数时，如果不显式指明类型，则不必使用小括号。例如 a -> return a*a。
        Lambda 表达式的正文可以包含零条，一条或多条语句。
        如果 Lambda 表达式的正文只有一条语句，则大括号可不用写，且表达式的返回值类型要与匿名函数的返回类型相同。
        如果 Lambda 表达式的正文有一条以上的语句必须包含在大括号（代码块）中，且表达式的返回值类型要与匿名函数的返回类型相同。
         */
        
        /*
        类型声明
        Lambda can be replaced with method reference
         */
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        
        int a = 10, b = 5;
        println("10 + 5 = " + this.operate(a, b, addition));
        println("10 - 5 = " + this.operate(a, b, subtraction));
        println("10 x 5 = " + this.operate(a, b, multiplication));
        println("10 / 5 = " + this.operate(a, b, division));
        
        // 不用括号
        GreetingService greetService1 = message -> println("Hello " + message);
        // 用括号
        GreetingService greetService2 = (message) -> println("Hello " + message);
        
        greetService1.sayMessage("nzy");
        greetService2.sayMessage("Google");
        println();
        
        /// 线程初始化 Todo:使用线程池
        /*
        // Old way
        new Thread(new Runnable() {
                    @Override
            public void run() {
                System.out.println("Hello world");
            }
        }).start();


        // New way
        new Thread(
                () -> System.out.println("Hello world")
        ).start();
         */
        
        /*
         遍例输出（方法引用） 输出给定数组的所有元素的简单代码。
         请注意，还有一种使用 Lambda 表达式的方式。
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        for (Integer n : list) {
            println(n);
        }
        // 使用 -> 的 Lambda 表达式 只有一个参数可以省略参数的定义圆括号
        list.forEach(n -> println(n));
        // 使用 :: 的 Lambda 表达式
        list.forEach(Print::println);
        println();
        
        /*
        逻辑操作 输出通过逻辑判断的数据
        使用 Predicate
         */
        print("输出所有数字：");
        println(list, (n) -> true);
        print("不输出：");
        println(list, (n) -> false);
        print("输出偶数：");
        println(list, (n) -> n % 2 == 0);
        print("输出奇数：");
        println(list, (n) -> n % 2 == 1);
        print("输出大于 5 的数字：");
        println(list, (n) -> n > 5);
        println();
        
        /*
        Stream API 示例
        java.util.stream.Stream接口 和 Lambda 表达式一样，都是 Java 8 新引入的。
        所有 Stream 的操作必须以 Lambda 表达式为参数。
        Stream 接口中带有大量有用的方法，比如 map() 的作用就是将 input Stream 的每个元素，
        映射成 output Stream 的另外一个元素。

        下面的例子，我们将 Lambda 表达式 x -> x*x 传递给 map() 方法，将其应用于流的所有元素。
        之后，我们使用 forEach 打印列表的所有元素。
         */
        for (Integer n : list) {
            int x = n * n;
            System.out.println(x);
        }
        // new way
        list.stream().map((x) -> x * x).forEach(Print::println);
    
        /*
        下面的示例中，我们给定一个列表，然后求列表中每个元素的平方和。
        这个例子中，我们使用了 reduce() 方法，这个方法的主要作用是把 Stream 元素组合起来。
         */
        int sum = 0;
        for (Integer n : list) {
            int x = n * n;
            sum = sum + x;
        }
        println(sum);
        
        // new way
        sum = list.stream().map(x -> x * x).reduce((x, y) -> x + y).orElse(0);
        println(sum);
        sum = list.stream().map(x -> x * x).reduce(Integer::sum).orElse(0);
        println(sum);
        
        /*
        Lambda 表达式和匿名类之间的区别
        this 关键字。
            对于匿名类 this 关键字解析为匿名类，而对于 Lambda 表达式，this 关键字解析为包含写入 Lambda 的类。
        编译方式。
            Java 编译器编译 Lambda 表达式时，会将其转换为类的私有方法，再进行动态绑定。
         */
    }
    
    void myMethodReference() {
        // 实现加法
        MathOperation addition = (int a, int b) -> a + b;
        // 类型推断
        addition = (a, b) -> a + b;
        // 双冒号操作符
        addition = Integer::sum;
        /*
        双冒号（::）操作符是 Java 中的方法引用。
        当们使用一个方法的引用时，目标引用放在 :: 之前，目标引用提供的方法名称放在 :: 之后，即 目标引用 :: 方法 。
         */
        
        /*
        @FunctionalInterface 是在 Java 8 中添加的一个新注解
        用于指示接口类型，声明接口为 Java 语言规范定义的功能接口。
        Java 8 还声明了 Lambda 表达式可以使用的功能接口的数量。
        当您注释的接口不是有效的功能接口时， @FunctionalInterface 会产生编译器级错误。
        功能接口只能有一个抽象方法。
         */
        // 通过匿名内部类调用
        WorkerInterface work = new WorkerInterface() {
            @Override
            public void doWork() {
                println("通过匿名内部类调用实现功能接口");
            }
        };
        work.doWork();
    
        /*
        通过 Lambda 表达式调用
        Lambda 表达式实际上是一个对象。我们可以将 Lambda 表达式赋值给一个变量，就可像其它对象一样调用。
         */
        work = () -> println("通过 Lambda 表达式调用实现功能接口");
        work.doWork();
    }
    
    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
    
    interface MathOperation {
        /**
         * 运算操作
         *
         * @param a 运算数 a
         * @param b 运算数 b
         * @return int
         * @date 2020/6/26 9:55
         */
        int operation(int a, int b);
    }
    
    interface GreetingService {
        /**
         * 打印消息
         *
         * @param message 消息
         * @return void
         * @author nzy
         * @creed: Talk is cheap,show me the code
         * @date 2020/6/26 9:57
         */
        void sayMessage(String message);
    }
    
    @FunctionalInterface
    interface WorkerInterface {
        /**
         * FunctionalInterface Demo
         *
         * @author nzy
         * @date 2020/6/26 11:44
         */
        void doWork();
    }
}
