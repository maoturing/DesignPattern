package structural.decorator.v2;

/**
 * 煎饼的装饰者鸡蛋
 * 重点:被装饰者煎饼为成员变量
 */
public class EggDecorator extends AbstractDecorator {

    public EggDecorator(ABattercake aBattercake) {
        super(aBattercake);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + "加一个鸡蛋";
    }

    @Override
    public int cost() {
        return super.cost() +1;
    }
}
