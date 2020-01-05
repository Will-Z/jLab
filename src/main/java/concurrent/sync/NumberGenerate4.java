package concurrent.sync;

import java.util.concurrent.Semaphore;

/**
 * 使用 Semaphore的 acquire() release() 实现两线程的交替打印
 *
 * @author will
 * @date 2020/1/5
 */
public class NumberGenerate4 {
    private static final int TOT = 10;
    private static int state = 1;
    private static Semaphore oddSema = new Semaphore(1);
    private static Semaphore evenSema = new Semaphore(1);


    private static void oddNum() {
        int num = 1;
        while (num <= TOT) {
            while (state == 0) {
                try {
                    oddSema.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":  " + num);
            num += 2;
            state = 0;
            evenSema.release();
        }
    }

    private static void evenNum() {
        int num = 2;
        while (num <= TOT) {
            while (state == 1) {
                try {
                    evenSema.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":  " + num);
            num += 2;
            state = 1;
            oddSema.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new NumberGenerate4().getClass().getSimpleName() + "  " + Thread.currentThread());
        Thread odd = new Thread(NumberGenerate4::oddNum);
        Thread even = new Thread(NumberGenerate4::evenNum);
        odd.start();
        even.start();
        odd.join();
        even.join();
    }

}
