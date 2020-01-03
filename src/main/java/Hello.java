import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author will
 * @date 2019/12/30
 */
public class Hello {

    private static void streamShow() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i * 10);
        }
        list.forEach(x -> {int a = x + 1; System.out.println(a);});
        System.out.println(list.parallelStream().filter(x -> x > 50).count());
    }

    private static void pong() {
        System.out.print("pong");
    }

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> System.out.print("pong"));
        t.run();
        System.out.print("ping");


    }

}
