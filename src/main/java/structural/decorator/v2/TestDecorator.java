package structural.decorator.v2;

import org.junit.Test;

public class TestDecorator {
    /**
     * 相比利用继承实现, 装饰者模式结构更加清晰易扩展
     * 核心在与被扩展的类是装饰者的成员变量
     */
    @Test
    public void test() {
        ABattercake aBattercake = new Battercake();
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new SausageDecorator(aBattercake);
        System.out.println(aBattercake.getDesc() + "销售价格:" + aBattercake.cost());
    }
}
