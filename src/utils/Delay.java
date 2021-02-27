package utils;

public class Delay {

    public static void delay(int ms) {
        try
        { Thread.sleep(ms); }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

}
