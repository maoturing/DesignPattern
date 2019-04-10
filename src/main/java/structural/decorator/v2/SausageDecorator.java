package structural.decorator.v2;

/**
 * 煎饼的装饰者香肠
 */
public class SausageDecorator extends AbstractDecorator {

    public SausageDecorator(ABattercake aBattercake) {
        super(aBattercake);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + "加一根香肠";
    }

    @Override
    public int cost() {
        return super.cost() +2;
    }
}
