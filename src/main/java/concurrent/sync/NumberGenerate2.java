package concurrent.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock 和 Condition的 await() signal() 来实现两线程交替打印
 *
 *
 * @author will
 * @date 2020/1/5
 */
public class NumberGenerate2 {
    private static final int TOT = 10;
    private static int  state = 1;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition oddCon = lock.newCondition();
    private static Condition evenCon = lock.newCondition();

    private static void oddNum() {
        int num = 1;
        while (num <= TOT) {
            while (state == 0) {
                lock.lock();
                try {
                    oddCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":  " + num);
            num += 2;
            state = 0;
            lock.lock();
            evenCon.signal();
            lock.unlock();
        }
    }

    private static void evenNum() {
        int num = 2;
        while (num <= TOT) {
            while (state == 1) {
                lock.lock();
                try {
                    evenCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":  " + num);
            num += 2;
            state = 1;
            lock.lock();
            oddCon.signal();
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println(new NumberGenerate2().getClass().getSimpleName());
        Thread odd = new Thread(NumberGenerate2::oddNum);
        Thread even = new Thread(NumberGenerate2::evenNum);
        odd.start();
        even.start();
        odd.join();
        odd.join();
    }
}
