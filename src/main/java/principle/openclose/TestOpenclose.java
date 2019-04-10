package principle.openclose;

import org.junit.Test;

public class TestOpenclose {
    @Test
    public void test() {
        ICourse javaCouse = new JavaCourse(1, "javase课程", 399.0);
        System.out.println(javaCouse);
    }

    /**
     *
     */
    @Test
    public void test2() {
        ICourse javaCouse = new JavaDiscountCourse(1, "javase课程", 399.0);
        JavaDiscountCourse course = (JavaDiscountCourse) javaCouse;
        System.out.println("原价:" + course.getOriginalPrice() + " 折后价格: " + course.getPrice());
    }
}
