package principle.openclose;

/**
 * 课程类,用于演示开闭原则
 *
 * @author mao  2019-4-5 01:02
 */
public interface ICourse {
    Integer getId();
    String getName();
    Double getPrice();

    // Double getDisCount();  //打折活动,添加打折价格,所有的实现类都需要修改;接口是契约,不应该经常修改
}
