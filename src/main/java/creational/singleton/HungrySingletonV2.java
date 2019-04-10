package creational.singleton;

import java.io.Serializable;

/**
 * 饿汉式单例模式V2版本
 * 序列化防御：添加readResolve方法
 * 反射防御：构造方法添加添加反射防御
 * 针对懒汉式的反射防御，无法完全避免，如果先反射，再获取实例，仍会破坏单例模式，
 * 因为懒汉式创建实例晚，反射调用能通过hungrySingleton为null校验
 *
 * @author mao 2019-4-3 13:20
 */
public class HungrySingletonV2 implements Serializable {
    // static表示变量在类加载时创建，final表示类加载完成时变量要赋值
    public static final HungrySingletonV2 hungrySingleton = new HungrySingletonV2();

    private HungrySingletonV2() {
        // 实例在类加载时期已经创建，反射调用时判断hungrySingleton不为空时，抛出异常，防止反射调用
        if(hungrySingleton != null) {
            // 必须是RuntimeException，否则需要需要throw异常比较麻烦
            throw new RuntimeException("单例构造器进制反射调用");
        }
    }

    public static HungrySingletonV2 getInstance() {
        return hungrySingleton;
    }

    /**
     * 用于保证序列化与反序列化安全
     * 如果不写这个方法，反序列化时发现没有这个方法，则会利用反射创建新的对象
     * 过程比较复杂，debug该方法运行测试用例{@link TestSingleton#testHungrySingletonSerializable()}，看源码
     */
    private Object readResolve() {
        return hungrySingleton;
    }
}
