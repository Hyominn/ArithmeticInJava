package com.op.demo;

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
    
    class EqualExample {
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
    }
}
