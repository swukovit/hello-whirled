package threads.creation;

/**
 * Creates a thread which creates a thread which....
 *
 * To abuse your system, have each thread enter an infinite loop, including the main thread.
 */
public class Nesting extends Thread {

    public Nesting(String name) {
        super(name);
    }

    static protected Integer threadCount = 0;

    public void run() {

        System.out.println(this.getClass().getName() + " #" + this.getName() + " is running now.");

        Thread t = new Nesting((++threadCount).toString());
        t.start();
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Nesting((++threadCount).toString());
        t.start();

        Thread.sleep(100);

        System.exit(-1);
    }
}


/* output:

...
threads.creation.Nesting #116 is running now.
threads.creation.Nesting #117 is running now.
threads.creation.Nesting #118 is running now.

Process finished with exit code -1

 */
