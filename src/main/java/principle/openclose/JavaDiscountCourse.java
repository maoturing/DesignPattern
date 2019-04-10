package principle.openclose;

/**
 * 新增JavaDiscountCourse类,表示打折的Java课程
 * 新增了打折功能,但是不需要修改ICourse,JavaCourse,防止修改原有代码引起bug,可能会导致通过测试的稳定功能出现问题
 *
 * 通过修改JavaCourse的getPrice方法也可以实现该功能,但是如果打折活动发生变化,加入优惠券,就导致又需要频繁修改JavaCourse
 * 修改JavaDiscountCourse相比JavaCourse影响范围更小,出错范围更可控
 *
 *
 * 维护人员最乐意做的事情就是扩展一个类,而不是修改一个类,不管原有的代码写的多么优秀,
 * 让维护人员读懂代码,然后再修改,是一件很痛苦的事情
 *
 * 软件应该通过扩展来实现新功能,而不是通过修改已有的代码来实现
 */
public class JavaDiscountCourse extends JavaCourse {
    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getOriginalPrice() {
        return super.getPrice();
    }
    @Override
    public Double getPrice() {
        return super.getPrice() * 0.8;
    }
}
