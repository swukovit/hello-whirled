package threads.creation;

/**
 * Creates an anonymous class extending Thread
 */
public class Anonymous extends Thread {

    public static void main(String[] args) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread of anonymous class '" + this.getClass().getName() + "', " +
                        "subclass of '" + this.getClass().getSuperclass().getName() + "', is running now.");
            }
        };
        thread.start();
    }
}

/* output:

Thread of anonymous class 'threads.creation.Anonymous$1', subclass of 'java.lang.Thread', is running now.

Process finished with exit code 0

 */
