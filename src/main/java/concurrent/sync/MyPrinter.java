package concurrent.sync;

/**
 * 两线程交替输出AB     CMB
 *
 * @author will
 * @date 2020/1/4
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyPrinter implements Runnable {
    private static final int PRINT_COUNT = 10;
    private final ReentrantLock reentrantLock;
    private final Condition thisCondtion;
    private final Condition nextCondtion;
    private final char printChar;

    public MyPrinter(ReentrantLock reentrantLock, Condition thisCondtion, Condition nextCondition,char printChar) {
        this.reentrantLock = reentrantLock;
        this.nextCondtion = nextCondition;
        this.thisCondtion = thisCondtion;
        this.printChar = printChar;
    }

    @Override
    public void run() {
        reentrantLock.lock();
        try {
            for (int i = 0; i < PRINT_COUNT; i++) {
                System.out.print(printChar);
                try {
                    nextCondtion.signal();
                    if (i < PRINT_COUNT - 1) {
                        thisCondtion.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();

        Thread printerA = new Thread(new MyPrinter(lock, conditionA, conditionB, 'A'));
        Thread printerB = new Thread(new MyPrinter(lock, conditionB, conditionA, 'B'));

        printerA.start();
        Thread.sleep(1000);
        printerB.start();
    }
}