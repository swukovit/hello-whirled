package threads.creation;

/**
 * Creates a single thread, implementing Runnable instead of extending Thread.
 */
public class ByImplementingRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread is running now.");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ByImplementingRunnable());
        thread.start();
    }
}

/* output:

Thread is running now.

Process finished with exit code 0

 */
