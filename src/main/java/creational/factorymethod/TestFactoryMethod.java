package creational.factorymethod;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Launcher;
import sun.net.www.protocol.http.Handler;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestFactoryMethod {
    /**
     * 测试工厂方法，利用JavaVideoFactory生产Java视频
     */
    @Test
    public void test() {
        VideoFactory videoFactory = new JavaVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();
    }

    /**
     * 测试扩展后新增的ScalaVideo，我们不需要修改原来的代码，只需要新增ScalaVideoFactory即可
     */
    @Test
    public void test2() {
        VideoFactory videoFactory = new ScalaVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();
    }

    /**
     * 源码案例：
     *
     * @see java.util.Collection 工厂接口，相当于VideoFactory
     * @see java.util.Collection#iterator() 抽象接口的方法，具体获取产品由子类实现
     * @see java.util.ArrayList 实现子类相当于JavaVideoFactory，与之相同的还有LinkList，HashSet等，
     * @see java.util.ArrayList#iterator() 实现类的工厂方法，该方法返回具体的产品
     * @see java.util.Iterator 抽象产品类，相当于Video
     * @see java.util.ArrayList.Itr 具体的产品，相当于JavaVideo
     */
    @Test
    public void testItr() {
        // 获取具体的迭代器需要知道具体的工厂，即ArrayList
        List<String> list = new ArrayList<>();
        list.add("mwq");

        // 获取产品iter，产品的创建逻辑在java.util.ArrayList#iterator()方法中，产品类型是java.util.ArrayList.Itr
        // 同样的，如果JDK中以后增加了新的迭代器（产品），不用修改已有的类，只需新增实现类即可，符合开闭原则
        Iterator<String> iter = list.iterator();
        // iter instanceof ArrayList.Itr == true; Itr是私有类，可以在debug中测试观察
    }

    /**
     * @see java.net.URLStreamHandlerFactory 工厂接口
     * @see Launcher.Factory 工厂实现类
     * @see Launcher.Factory#createURLStreamHandler(java.lang.String) 实现类的工厂方法
     * @see URLStreamHandler 抽象产品类
     * @see Handler 具体产品类
     */
    @Test
    public void testURL() {
    }

    /**
     * 这里容易混淆，测试用例中的LoggerFactory.getLogger是简单工厂模式，根据输入的类型，返回响应的Logger
     * @see org.slf4j.LoggerFactory#getLogger(java.lang.String) 是工厂方法模式，根据具体的工厂，返回具体的产品
     *
     * 区分简单工厂与工厂方法，简单工厂获取不同的产品是一个工厂类，根据参数进行区分，要点在于工厂类和参数；
     * 工厂方法获取不同产品是根据不同的实现工厂，要点在与实现工厂和工厂方法
     *
     * @see org.slf4j.ILoggerFactory 工厂接口
     * @see ch.qos.logback.classic.LoggerContext 工厂实现类，除此之外还有两个实现类
     * @see org.slf4j.ILoggerFactory#getLogger(java.lang.String) 工厂方法,获取具体产品的实现工厂，然后获取Logger
     * @see org.slf4j.Logger 抽象产品类
     *
     */
    @Test
    public void testLogger(){
        Logger logger = LoggerFactory.getLogger(TestFactoryMethod.class);
    }
}
