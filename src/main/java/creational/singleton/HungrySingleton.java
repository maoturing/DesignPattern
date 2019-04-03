package creational.singleton;
/**
 * 饿汉式单例模式
 * 类加载时即创建实例，不会出现线程安全问题
 * 缺点是类加载时创建实例比较浪费内存
 *
 * @author mao 2019-4-3 13:20
 */
public class HungrySingleton {
    // static表示变量在类加载时创建，final表示类加载完成时变量要赋值
    public static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}
