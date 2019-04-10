package structural.decorator.v2;

/**
 * 抽象的
 */
public class Battercake extends ABattercake {
    @Override
    public String getDesc() {
        return "煎饼";
    }

    @Override
    public int cost() {
        return 8 ;
    }
}
