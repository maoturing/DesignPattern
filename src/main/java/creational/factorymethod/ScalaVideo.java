package creational.factorymethod;

/**
 * 新增的视频种类，用于演示工厂方法的扩展
 */
public class ScalaVideo extends Video {

    @Override
    public void produce() {
        System.out.println("录制Scala课程视频");
    }
}
