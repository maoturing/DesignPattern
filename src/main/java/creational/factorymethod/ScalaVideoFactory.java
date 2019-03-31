package creational.factorymethod;

/**
 * 扩展性：之前只有JavaVideo和PythonVideo类型，
 * 后来新增了Scala类型，新建ScalaVideoFactory实现VideoFactory接口即可，
 * 不需要修改原有的代码，遵守了开闭原则。而简单工厂模式扩展时可能需要修改原有的代码
 *
 * @see creational.simplefactory.VideoFactory#getVideo(java.lang.String)  此处扩展时需要修改
 */
public class ScalaVideoFactory implements VideoFactory {

    @Override
    public Video getVideo() {
        return new ScalaVideo();
    }
}
