package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
 //   public static void main(String[] args) {}
    public static Map<String, Integer> getMinMax(int[] inpDat)  {
        Thread ThreadOne = new MaxThread(inpDat);
        Thread ThreadTwo = new MinThread(inpDat);


        ThreadOne.start();
        LOGGER.info("Thread Thread-1 started");
        ThreadTwo.start();
        LOGGER.info("Thread Thread-2 started");

        try {
            ThreadOne.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Thread Thread-1 ended");
        try {
            ThreadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Thread Thread-2 ended");
        Map<String, Integer> minMax = new HashMap<>();
        minMax.put("min", MinThread.getMin());
        minMax.put("max", MaxThread.getMax());
        return minMax;
    }
    
    // END
}
