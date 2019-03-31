package creational.simplefactory;

/**
 * 简单工程模式
 */
public class VideoFactory {
    /**
     * 根据参数返回对应的Video
     * 缺点：如果后面新增ScalaVideo，需要修改VideoFactory类，违反开闭原则
     *
     * @param type
     * @return
     */
    public Video getVideo(String type) {
        if ("java".equalsIgnoreCase(type)) {
            return new JavaVideo();
        } else if ("python".equalsIgnoreCase(type)) {
            return new PythonVideo();
        }
        return null;
    }

    /**
     * 使用类名获取对象,避免了getVideo()中新增业务，需要频繁修改的问题，遵守了开闭原则
     *
     * @param c
     * @return
     */
    public Video getVideo2(Class c) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Video video = null;
        video = (Video) Class.forName(c.getName()).newInstance();
        return video;
    }
}
