package principle.denpendenceinversion;

import org.junit.Test;

public class TestDependence {
    /**
     * 测试
     *
     */
    @Test
    public void test() {
        Geely geely = new Geely();
        geely.studyJavaCourse();
        geely.studyScalaCourse();
    }
}
