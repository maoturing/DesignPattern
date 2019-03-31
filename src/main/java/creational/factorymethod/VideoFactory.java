package creational.factorymethod;

/**
 * 工厂方法模式
 * 定义创建对象的工厂接口，具体创建对象由实现类完成，不同的产品有不同的实现类
 * <p>
 * 使用场景：常见对象需要大量重复的代码
 * 应用层不依赖产品类实例如何被创建、实现等细节
 * 一个类通过其子类来制定创建哪个对象
 * <p>
 * 优点：易使用，用户只关心所需产品的对应工厂，无需关注创建细节
 * 易扩展，加入新产品时只需实现工厂接口的新子类，遵守开闭原则
 * 缺点：类的个数过多，增加复杂度
 * 增加了系统的抽象性和理解难度
 *
 * @author mao 2019-3-31 22:53
 */
public interface VideoFactory {
    /**
     * 获取Video，返回的Video类型由子类决定，具体在子类中实现，
     */
    Video getVideo();
}