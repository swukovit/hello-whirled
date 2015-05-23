package threads.creation;

/**
 * Creates a single thread, extending Thread, and names it.
 */
public class RunVsStart extends Thread {

    public RunVsStart(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " thread is running now.");
    }

    public static void main(String[] args) {

        Thread thread = new RunVsStart("newly created");
        thread.run();   // executes in context of main thread
        thread.start(); // executes in context of created thread

    }
}

/* output:

main thread is running now.
newly created thread is running now.

Process finished with exit code 0

 */
