package principle.denpendenceinversion;

/**
 * 学生Geely,两个行为:学习java,学习scala
 *
 * 面向实现编程:需要经常修改,
 */
public class Geely {

    public void studyJavaCourse() {
        System.out.println("geely在学习Java课程");
    }

    public void studyScalaCourse() {
        System.out.println("geely在学习Scala课程");
    }
}
