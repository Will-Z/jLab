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

    // cpu 占有率相关实验
    private static void cpuLoad() {

        int seconds = 300;
        int busyTime = 10;
        int idleTime = 10;

        ExecutorService tp = Executors.newFixedThreadPool(8);
        long start = System.currentTimeMillis();
        Runnable r = () -> {
            try {
                for (;;) {
                    if (System.currentTimeMillis() - start >= seconds * 1000) {
                        break;
                    }
                    long point = System.currentTimeMillis();
                    while (System.currentTimeMillis() - point <= busyTime) ;
                    Thread.sleep(idleTime);
                }
            } catch (InterruptedException e) {

            }
        };

        for (int i = 0; i < 2; i++ ) {
            tp.execute(r);
        }
        tp.shutdown();

    }

    private static void memLoad() throws InterruptedException {
        final int _10M = 10 * 1024 * 1024;
        final int _100M = 100 * 1024 * 1024;

        int[] arr = new int[_100M];
        System.out.println("[use] 400MB");
        new CountDownLatch(1).await();
    }


    public static void main(String[] args) throws Exception {
//        cpuLoad();
//        memLoad();



//        List.of("a", "b", "c").forEach(System.out::println);
        List<Integer> a = List.of(1,2,3);
        a.add(44);

        System.out.println(11);
    }

}
