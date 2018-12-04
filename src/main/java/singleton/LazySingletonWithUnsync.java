package singleton;


import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 懒汉式-无锁：当使用时再创建对象,线程不安全
 * 优点:
 * 1.由于懒汉式延时加载特性，使用该实例时才实例化，解决了饿汉式浪费内存资源的问题
 * 缺点:
 * 1.线程不安全, 只能在单线程环境下使用
 * 2.反序列化，反射{@link LazySingletonWithUnsyncTest}与克隆可破坏单例
 */
public class LazySingletonWithUnsync {

    // 1.将构造函数私有化，不可以通过new的方式来创建对象
    private LazySingletonWithUnsync() throws InterruptedException {
        // 模拟一个大对象初始化, 便于测试线程安全
//            Thread.sleep(1000);
    }

    // 2.1先不创建对象，等用到的时候再创建
    private static LazySingletonWithUnsync counter = null;

    // 2.2调用到这个方法了，证明是要被用到的了
    public static LazySingletonWithUnsync getInstance() throws InterruptedException {

        /**
         * 3. 如果这个对象引用为null，就创建对象并返回
         * 当第一个获取实例线程a,运行到if语句的作用域中,此时线程b也运行到此处,判断counter为null,就会进入if, 并创建实例赋给counter,
         * 当线程a,b执行结束后, 后续的线程访问, 就是正常的单例了
         * 因为只有起初并发执行的线程会出问题, 后续的线程访问都是正常的,导致这种问题很难排查
         */
        if (counter == null) {
            counter = new LazySingletonWithUnsync();
        }
        return counter;
    }
}
