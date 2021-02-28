package utils;

public class Delay {

    /**
     * Add a delay to the animation.
     * @param ms Milliseconds of delay.
     */
    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
