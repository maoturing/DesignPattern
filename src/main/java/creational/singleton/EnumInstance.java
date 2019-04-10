package creational.singleton;

/**
 * 使用枚举实现单例模式
 * 线程安全，并发性能好，反射安全，序列化安全
 * 通过反编译，我们可以看到EnumInstance构造函数私有，继承自抽象类Enum，静态块中对Instance初始化
 * 缺点：无法延迟加载
 * 参考文档：https://juejin.im/post/5b50b0dd6fb9a04f932ff53f
 *
 */
public enum EnumInstance {
    INSTANCE;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumInstance getInstance(){
        return INSTANCE;
    }
}
