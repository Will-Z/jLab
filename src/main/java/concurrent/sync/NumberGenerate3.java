package concurrent.sync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.currentThread;

/**
 * 使用 同步队列 BlockingQueue 实现两线程交替打印奇偶数
 *
 * @author will
 * @date 2020/1/5
 */
public class NumberGenerate3 {
    private static final int TOT = 10;
    private static Object obj = new Object();
    private static BlockingQueue<Object> oddQueue = new ArrayBlockingQueue<>(1);
    private static BlockingQueue<Object> evenQueue = new ArrayBlockingQueue<>(1);

    private static void oddNum() {
        int num = 1;
        while (num <= TOT) {
            try {
                oddQueue.take();
                System.out.println(currentThread().getName() + ":  " + num);
                num += 2;
                evenQueue.put(obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void evenNum() {
        int num = 2;
        while (num <= TOT) {
            try {
                evenQueue.take();
                System.out.println(currentThread().getName() + ":  " + num);
                num += 2;
                oddQueue.put(obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "  " + new NumberGenerate3().getClass().getSimpleName());
        oddQueue.put(obj);
        Thread odd = new Thread(() -> oddNum());
        Thread even = new Thread(NumberGenerate3::evenNum);
        odd.start();
        even.start();
        odd.join();
        even.join();
    }
}
