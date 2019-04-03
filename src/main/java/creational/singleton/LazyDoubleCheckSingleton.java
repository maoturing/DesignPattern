package creational.singleton;

/**
 * 双重检测单例模式
 * 对懒汉式的改进，保证了延迟加载的同时，避免了无用的加锁解锁的性能消耗
 * volatile防止重排序
 *
 * @author mao 2019-4-3 13:51
 */
public class LazyDoubleCheckSingleton {
    // volatile防止重排序
    private volatile static LazyDoubleCheckSingleton instance = null;
    private LazyDoubleCheckSingleton() {
    }

    /**
     * 相比懒汉式线程安全版本{@link LazySingleton#getInstance2()}，缩小了加锁的范围，
     * 如果实例已初始化，多个线程同时获取实例，就能并发获取，不会进入锁的范围，没有锁开销，性能更好
     */
    public static LazyDoubleCheckSingleton getInstance() {

        if (instance == null) {
            // 1. 如果实例未初始化，多个线程同时获取实例，利用synchronized保证线程安全，只会创建一个实例
            // 2. 如果实例已初始化，多个线程同时获取实例，就能并发获取，不会进入锁的范围，没有锁开销，性能更好
            // 3. 也就是说除了第一次创建实例，以后获取实例时，都不需要加锁解锁操作
            synchronized (LazyDoubleCheckSingleton.class) {
                if(instance == null){
                    /*
                     * 1. 分配内存
                     * 2. 初始化
                     * 3. 设置变量instance指向对应的内存
                     *
                     * 2和3步骤有可能会重排序，导致其他线程判断时发现instance不为null，
                     * 然后返回instance，但其实此时instance的初始化操作(步骤2)还没有完成，导致出错
                     */
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
