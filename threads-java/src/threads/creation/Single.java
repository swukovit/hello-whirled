package threads.creation;

/**
 * Creates a single thread, extending Thread.
 */
public class Single extends Thread {

    @Override
    public void run() {
        System.out.println("Thread is running now.");
    }

    public static void main(String[] args) {
        Single thread = new Single();
        thread.start();
    }
}

/* output:

Thread is running now.

Process finished with exit code 0

 */
