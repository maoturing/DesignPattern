package structural.decorator.v1;

/**
 * 加香肠鸡蛋的煎饼
 */
public class BattercakeWithEggSausage extends BattercakeWithEgg{
    @Override
    public String getDesc() {
        return super.getDesc() + "加一根香肠";
    }

    // 加香肠2元
    @Override
    public int cost() {
        return super.cost() + 2;
    }
}
