package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {

    private int[] array;
    private int minValue;
    @Override
    public void run() {
        minValue = Arrays.stream(array)
                .min()
                .orElseThrow(() -> new RuntimeException("Array is empty"));
    }
    public int getMinValue() {
        return this.minValue;
    }
    public void setArray(int[] array) {
        this.array = array;
    }
}
// END
