package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] array) {
        MinThread threadMinValue = new MinThread();
        threadMinValue.setArray(array);

        MaxThread threadMaxValue = new MaxThread();
        threadMaxValue.setArray(array);

        threadMinValue.start();
        LOGGER.log(Level.INFO, "Thread " + threadMinValue.getName() + " started");
        threadMaxValue.start();
        LOGGER.log(Level.INFO, "Thread " + threadMaxValue.getName() + " started");
        try {
            threadMinValue.join();
            LOGGER.log(Level.INFO, "Thread " + threadMinValue.getName() + " finished");
            threadMaxValue.join();
            LOGGER.log(Level.INFO, "Thread " + threadMaxValue.getName() + " finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        Map<String, Integer> result = new HashMap<>();
        result.put("min", threadMinValue.getMinValue());
        result.put("max", threadMaxValue.getMaxValue());
        return result;
    }
    // END
}
