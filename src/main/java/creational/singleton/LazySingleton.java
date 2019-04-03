package creational.singleton;

import org.junit.Test;

/**
 * 懒汉式单例模式
 * 延迟加载，使用时才创建对象
 * 线程不安全
 *
 * @author mao 2019-4-2 02:03
 */
public class LazySingleton {
    private static LazySingleton lazySingleton = null;
    private LazySingleton() {};

    public static LazySingleton getInstance() {
        if(lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

    /**
     * 线程安全的版本
     * 线程0进入方法后，状态是Running；线程1状态是Monitor，无法进入方法，必须等待线程0执行完毕
     * 缺点：同步锁开销较大，锁定的是类的Class对象，影响范围较大，性能较差
     *       如果已经存在实例，多个线程同时获取实例时也必须顺序获取，无法并发获取，性能差
     */
    public synchronized static LazySingleton getInstance2() {
        if(lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
