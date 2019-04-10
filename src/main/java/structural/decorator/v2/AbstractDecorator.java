package structural.decorator.v2;

/**
 * 抽象的装饰者,为煎饼扩展功能:加蛋,加香肠
 * 重点: 被装饰者aBattercake为成员变量,即利用组合的方式;
 *       装饰者继承自被装饰者
 *
 * @author mao 2019-4-4 21:13
 */
public class AbstractDecorator extends ABattercake{
    private ABattercake aBattercake;

    public AbstractDecorator(ABattercake aBattercake) {
        this.aBattercake = aBattercake;
    }

    @Override
    public String getDesc() {
        return this.aBattercake.getDesc();
    }

    @Override
    public int cost() {
        return this.aBattercake.cost();
    }
}
