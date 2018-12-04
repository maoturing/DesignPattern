package singleton;

/**
 * 编写单例模式的代码其实很简单，就分了三步：
 * 1. 将构造函数私有化
 * 2. 在类的内部创建实例
 * 3. 提供获取唯一实例的方法
 * <p>
 * 饿汉式：类初始化时直接创建对象
 * 优点:
 * 1. 代码逻辑简单, 线程安全
 * 缺点:
 * 2. 如果该实例是一个大对象, 或从始至终都没被使用过, 则会造成内存浪费。
 */
public class HungrySingleton {
    // 1.将构造函数私有化，不可以通过new的方式来创建对象
    private HungrySingleton() {
    }

    // 2.在类的内部创建自行实例
    private static HungrySingleton counter = new HungrySingleton();

    // 3.提供获取唯一实例的方法
    public static HungrySingleton getInstance() {
        return counter;
    }
}
