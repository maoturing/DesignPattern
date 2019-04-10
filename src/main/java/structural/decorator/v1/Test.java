package structural.decorator.v1;

public class Test {
    /**
     * 测试使用继承扩展煎饼的情况
     */
    @org.junit.Test
    public void test() {
        Battercake battercake = new Battercake();
        System.out.println(battercake.getDesc() + "销售价格:" + battercake.cost());

        BattercakeWithEgg battercakeWithEgg = new BattercakeWithEgg();
        System.out.println(battercakeWithEgg.getDesc() + "销售价格:" + battercakeWithEgg.cost());

        BattercakeWithEggSausage battercakeWithEggSausage = new BattercakeWithEggSausage();
        System.out.println(battercakeWithEggSausage.getDesc() + "销售价格:" + battercakeWithEggSausage.cost());
    }
}
