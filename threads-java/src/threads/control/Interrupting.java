package threads.control;

/**
 * Creates multiple named threads, pauses the main thread briefly, then interrupts them. Extending Thread.
 */
public class Interrupting extends Thread {


    public Interrupting(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(this.getClass().getName() + " #" + this.getName() + " is running now.");

        while (true) {
            if (Thread.interrupted())
                break;
        }
        System.out.println(this.getClass().getName() + " #" + this.getName() + " is interrupted.");
    }

    public static void main(String[] args) {

        Thread threads[] = new Interrupting[10];

        for (int i = 0; i < 10; ++i) {
            threads[i] = new Interrupting("" + i);  // name each thread by index
            threads[i].start();
        }

        for (Integer i = 0; i < 10; ++i) {
            threads[i].interrupt();
        }
    }
}

/* output:

threads.control.Interrupting #1 is running now.
threads.control.Interrupting #2 is running now.
threads.control.Interrupting #3 is running now.
threads.control.Interrupting #0 is running now.
threads.control.Interrupting #5 is running now.
threads.control.Interrupting #4 is running now.
threads.control.Interrupting #5 is interrupted.
threads.control.Interrupting #0 is interrupted.
threads.control.Interrupting #3 is interrupted.
threads.control.Interrupting #2 is interrupted.
threads.control.Interrupting #1 is interrupted.
threads.control.Interrupting #9 is running now.
threads.control.Interrupting #9 is interrupted.
threads.control.Interrupting #8 is running now.
threads.control.Interrupting #6 is running now.
threads.control.Interrupting #4 is interrupted.
threads.control.Interrupting #6 is interrupted.
threads.control.Interrupting #8 is interrupted.
threads.control.Interrupting #7 is running now.
threads.control.Interrupting #7 is interrupted.

Process finished with exit code 0

 */
