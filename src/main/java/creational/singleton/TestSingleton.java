package creational.singleton;

import org.junit.Test;
import singleton.HungrySingleton;

import java.io.*;
import java.lang.reflect.Constructor;

public class TestSingleton {
    @Test
    public void testLazySingleton(){
        LazySingleton instance = LazySingleton.getInstance();
    }

    /**
     * 测试多线程环境下懒汉式单例模式
     * debug模式下将断点suspend设置为Thread，然后手动干预，让线程1,2同时通过if(lazySingleton == null) 判断，然后执行完线程1并输出结果，然后再执行线程2并输出结果
     * 注意线程1没有return结果之前，线程2获取单例成功后，会修改线程1返回的单例对象
     */
    @Test
    public void testLazySingleton2(){
        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println("实例1：" + instance);
        }).start();

        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println("实例2：" + instance);
        }).start();
    }

    /**
     * 测试懒汉式的线程安全版本
     * @see LazySingleton#getInstance2()
     */
    @Test
    public void testLazySingleton3(){
        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance2();
            System.out.println("实例1：" + instance);
        }).start();

        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance2();
            System.out.println("实例2：" + instance);
        }).start();
    }

    /**
     * 测试静态内部类单例模式，debug模式下检测实例是否为延迟加载
     */
    @Test
    public void testStaticInnerClassSingleton(){
        StaticInnerClassSingleton instance = StaticInnerClassSingleton.getInstance();
    }

    /**
     * HungrySingletonV2可以通过测试，HungrySingleton无法通过测试
     *
     * 测试懒汉式单例模式是否序列化与反序列化安全
     * 通过序列化与反序列化获取了多个实例对象，违反了单例模式的规范
     * 解决方法是在HungrySingletonV2中添加readResolve方法
     *
     * @see creational.singleton.HungrySingletonV2#readResolve()
     */
    @Test
    public void testHungrySingletonSerializable() throws IOException, ClassNotFoundException {
        HungrySingletonV2 instance = HungrySingletonV2.getInstance();
        File file = new File("singleton.bin");
        // 将对象序列化保存到文件当中
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(instance);

        // 从文件中将对象反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        HungrySingletonV2 newInstance = (HungrySingletonV2) ois.readObject();
        System.out.println(instance);
        System.out.println(newInstance);

        assert instance == newInstance;
    }

    /**
     * 测试反射安全
     *
     */
    @Test
    public void testReflate() throws Exception {
        HungrySingletonV2 instance = HungrySingletonV2.getInstance();

        Class clazz = HungrySingletonV2.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        HungrySingletonV2 newInstance = (HungrySingletonV2) constructor.newInstance();

        System.out.println(instance);
        System.out.println(newInstance);

        assert instance == newInstance;
    }
}