package threads.creation;

/**
 * Creates a single thread, extending Thread, and names it.
 */
public class Naming extends Thread {

    public Naming(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(this.getName() + ", thread is running now.");
    }

    public static void main(String[] args) {

        Thread thread = new Naming("Hihi");
        thread.start();

        // Static method so no 'this' available, so call currentThread()
        System.out.println("The main thread is: " + Thread.currentThread().getName());
    }
}

/* output:

The main thread is: main
Hihi, thread is running now.

Process finished with exit code 0

 */
