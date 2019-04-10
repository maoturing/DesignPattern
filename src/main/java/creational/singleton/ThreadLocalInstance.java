package creational.singleton;

/**
 * 线程中的单例模式，一个线程中实例唯一
 */
public class ThreadLocalInstance {
    private ThreadLocalInstance() {
    }

    private static final ThreadLocal<ThreadLocalInstance> local = new ThreadLocal<ThreadLocalInstance>() {
        @Override
        protected ThreadLocalInstance initialValue() {
            return new ThreadLocalInstance();
        }
    };

    public static ThreadLocalInstance getInstance() {
        return local.get();
    }
}
