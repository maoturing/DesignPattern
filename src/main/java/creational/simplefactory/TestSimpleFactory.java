package creational.simplefactory;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.Locale;

/**
 * 测试简单工厂
 *
 * @author mao  2019-3-29 12:30:40
 */
public class TestSimpleFactory {
    /**
     * 直接new的方法，应用层会依赖JavaVideo
     *
     * @param args
     */
    public static void main(String[] args) {
        Video video = new JavaVideo();
        video.produce();
    }

    /**
     * 应用层只依赖VideoFactory，不依赖javaVideo
     */
    @org.junit.Test
    public void test() {
        VideoFactory videoFactory = new VideoFactory();
        Video javaVideo = videoFactory.getVideo("java");
        javaVideo.produce();
    }


    @org.junit.Test
    public void test2() throws Exception {
        VideoFactory videoFactory = new VideoFactory();
        Video javaVideo = videoFactory.getVideo2(JavaVideo.class);
        javaVideo.produce();
    }

    /**
     * Calendar抽象类使用了简单工厂模式
     *
     * @throws Exception
     * @see Calendar#createCalendar(java.util.TimeZone, java.util.Locale)
     */
    @org.junit.Test
    public void testCalendar() throws Exception {
        Calendar jp = Calendar.getInstance(new Locale("jp"));
        System.out.println(jp.getTime());
    }

    /**
     * JDBC获取驱动时也使用的简单工厂模式，利用mysql驱动的url，从已注册驱动中尝试获取连接，然后返回。具体见下面这个类的668行
     *
     * @throws Exception
     * @see DriverManager#getConnection(java.lang.String, java.util.Properties, java.lang.Class)
     * <p>
     * 加载Driver类会先执行静态代码块，注册mysql驱动到DriverManager
     * @see com.mysql.jdbc.Driver
     */
    @Test
    public void testMysqlConn() throws Exception {
        // 加载Driver类会先执行静态代码块，注册mysql驱动到DriverManager
        Class.forName("com.mysql.jdbc.Driver");

        // 从注册驱动中获取mysql连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/select_test");
    }

}
