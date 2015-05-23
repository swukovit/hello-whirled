package threads.creation;

/**
 * Creates multiple named threads, pauses the main thread briefly, then interrupts them. Extending Thread.
 */
public class Multiple extends Thread {


    public Multiple(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(this.getClass().getSimpleName() + " #" + this.getName() + " is running now.");

        while (true) {
            if (Thread.interrupted())
                break;
        }
        System.out.println(this.getClass().getSimpleName() + " #" + this.getName() + " is interrupted.");
    }

    public static void main(String[] args) {

        Multiple threads[] = new Multiple[10];

        for (Integer i = 0; i < 10; ++i) {
            threads[i] = new Multiple(i.toString());
            threads[i].start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Integer i = 0; i < 10; ++i) {
            threads[i].interrupt();
        }
    }
}

/* output:

Multiple #0 is running now.
Multiple #3 is running now.
Multiple #2 is running now.
Multiple #1 is running now.
Multiple #6 is running now.
Multiple #5 is running now.
Multiple #4 is running now.
Multiple #9 is running now.
Multiple #7 is running now.
Multiple #8 is running now.
Multiple #0 is interrupted.
Multiple #2 is interrupted.
Multiple #1 is interrupted.
Multiple #7 is interrupted.
Multiple #8 is interrupted.
Multiple #9 is interrupted.
Multiple #5 is interrupted.
Multiple #3 is interrupted.
Multiple #6 is interrupted.
Multiple #4 is interrupted.

Process finished with exit code 0

 */
