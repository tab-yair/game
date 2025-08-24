import java.util.ArrayList;
import java.util.List;

public class Test {
    public class A {





        private void func() {
            List<Number> a = new ArrayList<>();
            a.add(Integer.valueOf(1));
        }
    }

    public class B extends A {
        private void func() {
        }
    }

    public class C extends A {
        private void statFunc() {
            A a = new A();
            a.setI(13);
        }
    }
}