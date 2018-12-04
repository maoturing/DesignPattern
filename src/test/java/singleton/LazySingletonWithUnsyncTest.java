package singleton;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试 懒汉-无锁 单例模式
 * {@link LazySingletonWithUnsync} Test
 */
@Slf4j
public class LazySingletonWithUnsyncTest {
    private static final int CONCURRENT_THREAD_NUMBER = 50;

    /**
     * 使用CyclicBarrier来实现多个线程同时获取实例,
     * 使用CountDownLatch在等待所有线程结束后, 再运行主线程, 判断获取的实例是否为单例
     *
     * @throws Exception
     */
    @Test
    public void concurrentGetSingletonWithCyclicBarrierTest() throws Exception {
        final CyclicBarrier barrier = new CyclicBarrier(CONCURRENT_THREAD_NUMBER);
        final CountDownLatch latch = new CountDownLatch(CONCURRENT_THREAD_NUMBER);

        // 统计并发获取到的单例对象引用地址，用于验证获取到的单例是否是同一个
        final List<String> instanceUrl = new Vector<>();    // Vector线程安全

        for (int i = 0; i < CONCURRENT_THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    barrier.await();    // 挂起当前线程, 当所有线程都到达这一步时, 再一起向下执行

                    LazySingletonWithUnsync instance = LazySingletonWithUnsync.getInstance();
                    log.debug("当前时间: {}, 单例: {}", System.currentTimeMillis(), instance.toString());

                    instanceUrl.add(instance.toString());
                    latch.countDown();  // 将count的值减1
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        latch.await();  // 挂起主线程, 当count减为0时才继续向下执行

        log.debug("模拟并发线程数：{}, 实际并发线程数：{}", CONCURRENT_THREAD_NUMBER, instanceUrl.size());
        Assert.assertEquals("线程数：" + CONCURRENT_THREAD_NUMBER, "线程数：" + instanceUrl.size());

        // 验证并发获取到的单例是否是同一个实例, 失败表示获取的不是一个实例, 线程不安全
        this.verifySingletonsIsSameOrNotWithAssert(instanceUrl);
    }

    /**
     * workThreadLatch的作用是待所有线程创建完毕之后, 再同时获取实例
     * mainThreadLatch的作用是待所有线程执行完毕之后, 再判断获取的实例是否为单例
     *
     * @throws Exception
     */
    @Test
    public void concurrentGetSingletonWithCountDownLatchTest() throws Exception {
        final CountDownLatch workThreadLatch = new CountDownLatch(CONCURRENT_THREAD_NUMBER);
        final CountDownLatch mainThreadLatch = new CountDownLatch(CONCURRENT_THREAD_NUMBER);

        // 统计并发获取到的单例对象引用地址，用于验证获取到的单例是否是同一个
        final List<String> instanceUrl = new Vector<>();

        for (int i = 0; i < CONCURRENT_THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    workThreadLatch.await();    // 等待所有线程创建完毕之后, 即count == 0时, 唤醒所有线程一起向下执行

                    LazySingletonWithUnsync instance = LazySingletonWithUnsync.getInstance();
                    log.debug("当前时间: {}, 单例: {}", System.currentTimeMillis(), instance.toString());

                    instanceUrl.add(instance.toString());
                    mainThreadLatch.countDown();    // 当一条线程执行完毕之后, count减1
                } catch (Exception e) {
                    log.error("线程 {} 异常: {}", Thread.currentThread().getName(), e);
                }
            }).start();

            workThreadLatch.countDown();    // 当一条线程创建完毕之后, count减1
        }

        mainThreadLatch.await();    // 等待所有线程执行完毕之后, 唤醒主线程, 判断获取的实例是否为单例

        log.debug("模拟并发线程数：{}, 实际并发线程数：{}", CONCURRENT_THREAD_NUMBER, instanceUrl.size());
        Assert.assertEquals("线程数：" + CONCURRENT_THREAD_NUMBER, "线程数：" + instanceUrl.size());

        // 验证并发获取到的单例是否是同一个实例
        this.verifySingletonsIsSameOrNotWithAssert(instanceUrl);
    }

    /**
     * 使用反射来破坏 懒汉-无锁 单例模式
     *
     * @throws Exception
     */
    @Test
    public void breakSingletonByReflectionTest() throws Exception {
        LazySingletonWithUnsync singleton = LazySingletonWithUnsync.getInstance();

        Constructor<LazySingletonWithUnsync> constructor = LazySingletonWithUnsync.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazySingletonWithUnsync singletonFromReflection = constructor.newInstance();

        log.debug("正常单例：{}", singleton.toString());
        log.debug("反射单例：{}", singletonFromReflection.toString());

        Assert.assertNotEquals(singleton.toString(), singletonFromReflection.toString());
        ;
    }

    /**
     * 验证并发获取到的单例是否是同一个实例
     */
    private void verifySingletonsIsSameOrNotWithAssert(final List<String> instanceUrl) {
        for (int i = 0; i < instanceUrl.size(); i++) {
            if (i == instanceUrl.size() - 1) {
                Assert.assertEquals(instanceUrl.get(i - 1), instanceUrl.get(i));
                break;
            }

            Assert.assertEquals(instanceUrl.get(i), instanceUrl.get(i + 1));
        }
    }

}
