package type;

import java.util.function.Consumer;

/**
 * @author will
 * @date 2019/12/14
 */
public class TypeTest {
    public static void main(String[] args) {

        func(new Object() {
            private void doSomething() {
                System.out.println(this);
            }
        }, obj -> obj.doSomething());
    }

    private static <T> void func(T obj, Consumer<T> consumer) {
        consumer.accept(obj);
    }
}
