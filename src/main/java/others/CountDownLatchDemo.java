package others;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        int noOfThreads = 5;
        // Declare the count down latch based on the number of threads you need
        // to wait on
        final CountDownLatch executionCompleted = new CountDownLatch(noOfThreads);
        for (int i = 0; i < noOfThreads; i++) {
            new Thread() {

                @Override
                public void run() {

                    System.out.println("I am executed by :" + Thread.currentThread().getName());
                    try {
                        // Dummy sleep
                        Thread.sleep(3000);
                        // One thread has completed its job
                        executionCompleted.countDown();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }.start();
        }

        try {
            // Wait till the count down latch opens.In the given case till five
            // times countDown method is invoked
            executionCompleted.await();
            System.out.println("All over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}