package concurrent.sync;

/**
 * 使用 synchronized 和 object.wait() object.notify() 实现两线程交替打印奇偶数
 *
 * @author will
 * @date 2020/1/5
 */
public class NumberGenerate {
    private static final int TOT = 10;

    // 1: print odd num.  0: print even num
    private static int state = 1;
    private static Object evenLock = new Object();
    private static Object oddLock = new Object();



    private static void oddNum() {
        int num = 1;
        while (num <= TOT) {
            while (state == 0) {
                synchronized(oddLock) {
                    try {
                        oddLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + ":  " + num);
            num += 2;
            state = 0;
            synchronized(evenLock) {
                evenLock.notify();
            }
        }
    }

    private static void evenNum() {
        int num = 2;
        while (num <= TOT) {
            while (state == 1) {
                synchronized(evenLock) {
                    try {
                        evenLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + ":  " + num);
            num += 2;
            state = 1;
            synchronized(oddLock) {
                oddLock.notify();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread odd = new Thread(NumberGenerate::oddNum);
        Thread even = new Thread(NumberGenerate::evenNum);
        odd.start();
        even.start();
        odd.join();
        even.join();

    }

}
