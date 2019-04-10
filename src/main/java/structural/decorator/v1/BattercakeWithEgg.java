package structural.decorator.v1;

/**
 * 加蛋的煎饼
 */
public class BattercakeWithEgg extends Battercake{
    @Override
    public String getDesc() {
        return super.getDesc() + "加一个鸡蛋";
    }

    // 加蛋需要加1元
    @Override
    public int cost() {
        return super.cost() + 1;
    }
}
