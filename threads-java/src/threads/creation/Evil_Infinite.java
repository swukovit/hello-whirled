package threads.creation;

/**
 * Creates a thread which creates a thread which....
 *
 * To abuse your system, have each thread enter an infinite loop, including the main thread.
 */
public class Evil_Infinite extends Thread {


    public Evil_Infinite(String name) {
        super(name);
    }

    static protected Integer threadCount = 0;

    public void run() {
        System.out.println(this.getClass().getSimpleName() + " #" + this.getName() + " is running now.");

        Thread t = new Evil_Infinite((++threadCount).toString());
        t.start();

    }

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Evil_Infinite((++threadCount).toString());
        t.start();

        Thread.sleep(100);

        System.exit(-1);
    }
}


/* output:

...
Evil_Infinite #118 is running now.
Evil_Infinite #119 is running now.
Evil_Infinite #120 is running now.
Evil_Infinite #121 is running now.

Process finished with exit code -1

 */
