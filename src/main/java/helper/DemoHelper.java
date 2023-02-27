package helper;

public class DemoHelper {

    /**
     * To slow down the execution
     * For demo purposes only!
     */
    public static void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
