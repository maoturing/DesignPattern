package structural.flyweight;

import org.junit.Test;

public class TestFlyweight {
    private static final String departments[] = {"RD", "QA", "PM"};
    @Test
    public void test(){
        for (int i = 0; i < 10; i++) {
            String department = departments[(int)Math.random() * departments.length];
            Manager manager = (Manager) EmployeeFactory.getManager(department);

        }
    }
}
