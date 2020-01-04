package jvmlab.load;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author will
 * @date 2020/1/4
 */



public class Load {
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

        for (int i = 0; i < 8; i++ ) {
            tp.execute(r);
        }
        tp.shutdown();

    }

    // 内存占用
    private static void memLoad() throws InterruptedException {
        final int _10M = 10 * 1024 * 1024;
        final int _100M = 100 * 1024 * 1024;

        int[] arr = new int[_100M];
        System.out.println("[use] 400MB");
        new CountDownLatch(1).await();
    }

    public static void main(String[] args) throws Exception{
        cpuLoad();
//        memLoad();
    }

}

