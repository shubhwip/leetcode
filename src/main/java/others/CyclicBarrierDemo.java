package others;

import java.util.LinkedHashMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    static CyclicBarrier barrier = new CyclicBarrier(100, new Runnable() {
        public void run() {
            System.out.println("clean up job after all tasks are done.");
        }
    });

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new MyCallable(barrier));
            t.start();
        }
    }

}

class MyCallable implements Runnable {
    private CyclicBarrier b = null;

    public MyCallable(CyclicBarrier b) {
        this.b = b;
    }

    @Override
    public void run() {
        try {
            //do something
            System.out.println(Thread.currentThread().getName() + " is waiting for barrier after completing his job.");
            b.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
