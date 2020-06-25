package com.op.demo;

import java.util.HashSet;

import static com.op.util.Print.print;

/**
 * Object 通用方法
 * <p>
 * public boolean equals(Object obj)
 * <p>
 * protected native Object clone() throws CloneNotSupportedException
 * <p>
 * public String toString()
 * <p>
 * public final native Class<?> getClass()
 * <p>
 * protected void finalize() throws Throwable {}
 * <p>
 * public final native void notify()
 * <p>
 * public final native void notifyAll()
 * <p>
 * public final native void wait(long timeout) throws InterruptedException
 * <p>
 * public final void wait(long timeout, int nanos) throws InterruptedException
 * <p>
 * public final void wait() throws InterruptedException
 *
 * @Author: NZY
 * @Date: 2020/6/19 10:49
 */
public class MyObject {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        myObject.myHashCode();
        print();
    }
    
    void myEquals() {
        Integer x = 1;
        Integer y = 1;
        Integer z = 1;
        /*
         Ⅰ 自反性
         true
         */
        print(x.equals(x));
        /*
        Ⅱ 对称性
        true
         */
        print(x.equals(y) == y.equals(x));
        /*
        Ⅲ 传递性
        true
         */
        if (x.equals(y) && y.equals(z)) {
            x.equals(z);
        }
        /*
        Ⅳ 一致性
        多次调用 equals() 方法结果不变
         */
        print(x.equals(y) == x.equals(y));
        /*
        Ⅴ 与 null 的比较
        对任何不是 null 的对象 x 调用 x.equals(null) 结果都为 false
         */
        print(x.equals(null));
        /*
        实现
        检查是否为同一个对象的引用，如果是直接返回 true；
        检查是否是同一个类型，如果不是，直接返回 false；
        将 Object 对象进行转型；
        判断每个关键域是否相等。
         */
        MyObject myObject = new MyObject();
        EqualExample equalExample1 = new EqualExample(1, 1, 1);
        EqualExample equalExample2 = new EqualExample(1, 1, 1);
        print(equalExample1.equals(equalExample2));
        
    }
    
    static class EqualExample {
        private int x;
        private int y;
        private int z;
        
        public EqualExample(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            
            EqualExample that = (EqualExample) o;
            
            if (x != that.x) {
                return false;
            }
            if (y != that.y) {
                return false;
            }
            return z == that.z;
        }
        
        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + x;
            result = 31 * result + y;
            result = 31 * result + z;
            return result;
        }
    }
    
    void myHashCode() {
        /*
        hashCode() 返回哈希值，而 equals() 是用来判断两个对象是否等价。
        等价的两个对象散列值一定相同，但是散列值相同的两个对象不一定等价.
        这是因为计算哈希值具有随机性，两个值不同的对象可能计算出相同的哈希值。
        在覆盖 equals() 方法时应当总是覆盖 hashCode() 方法，保证等价的两个对象哈希值也相等。
        HashSet 和 HashMap 等集合类使用了 hashCode() 方法来计算对象应该存储的位置，
        因此要将对象添加到这些集合类中，需要让对应的类实现 hashCode() 方法。
        下面的代码中，新建了两个等价的对象，并将它们添加到 HashSet 中。
        我们希望将这两个对象当成一样的，只在集合中添加一个对象。
        但是 EqualExample 没有实现 hashCode() 方法，因此这两个对象的哈希值是不同的，最终导致集合添加了两个等价的对象。
         */
        EqualExample e1 = new EqualExample(1, 1, 1);
        EqualExample e2 = new EqualExample(1, 1, 1);
        System.out.println(e1.equals(e2));
        // true
        HashSet<EqualExample> set = new HashSet<>();
        set.add(e1);
        set.add(e2);
        // 未重写 hashCode() 方法之前返回 2 重写之后返回 1
        print(set.size());
        
        /*
        理想的哈希函数应当具有均匀性，即不相等的对象应当均匀分布到所有可能的哈希值上。
        这就要求了哈希函数要把所有域的值都考虑进来。可以将每个域都当成 R 进制的某一位，然后组成一个 R 进制的整数。
        R 一般取 31，因为它是一个奇素数，如果是偶数的话，当出现乘法溢出，信息就会丢失，
        因为与 2 相乘相当于向左移一位，最左边的位丢失。
        并且一个数与 31 相乘可以转换成移位和减法：31*x == (x<<5)-x，编译器会自动进行这个优化。
         */
    }
    
    void myToString() {
        // 默认返回 ToStringExample@4554617c 这种形式，其中 @ 后面的数值为散列码的无符号十六进制表示。
        ToStringExample example = new ToStringExample(123);
        System.out.println(example.toString());
        // ToStringExample@4554617c
    }
    
    static class ToStringExample {
        
        private int number;
        
        public ToStringExample(int number) {
            this.number = number;
        }
    }
    
}
