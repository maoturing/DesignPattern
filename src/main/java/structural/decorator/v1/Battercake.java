package structural.decorator.v1;

/**
 * 煎饼类
 *
 * @author mao  2019-4-4 20:30
 */
public class Battercake {
    public String getDesc(){
        return "煎饼";
    }

    /**
     * 返回价格
     */
    public int cost() {
        return 8;
    }
}
