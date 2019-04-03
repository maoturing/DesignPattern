package creational.singleton;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

/**
 * 静态内部类的单例模式
 * 基于类初始化的延迟加载解决方案，类线程安全
 * <p>
 * 原理：Java类加载中初始化阶段是线程安全的，多个线程去初始化类，只有一个能初始化成功，
 * 利用该特点保证了InnerClass.staticInnerClassSingleton实例只有唯一一个，由于类只有在使用时才会被加载，即第一次调用
 * getInstance()方法时才会加载InnerClass，然后类初始化阶段会给staticInnerClassSingleton赋值，
 * 这样既保证了线程安全，又保证了延迟加载
 * <p>
 * 参考文档：深入JVM 7.3.5
 *
 * @author mao 2019-4-3 14:21
 */
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {
    }

    /**
     * 内部类加载时机与外部类没有关系，InnerClass在遇到getstatic指令时（即获取类的静态字段时InnerClass.staticInnerClassSingleton），
     * 加载InnerClass，然后完成对InnerClass的类初始化，创建staticInnerClassSingleton实例，即延迟加载
     *
     * 参考文档：深入jvm7.2 类加载的时机
     */
    private static class InnerClass {
        private static StaticInnerClassSingleton staticInnerClassSingleton = new StaticInnerClassSingleton();
    }

    /**
     * debug时可以看到，先进入该方法，然后才类初始化InnerClass，
     * 给staticInnerClassSingleton赋值，即为延迟加载
     */
    public static StaticInnerClassSingleton getInstance() {
        return InnerClass.staticInnerClassSingleton;
    }
}
