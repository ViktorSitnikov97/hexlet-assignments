package exercise;

// BEGIN
public class MaxThread extends Thread {

    private int[] array;
    private int maxValue;
    @Override
    public void run() {
        maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (maxValue < array[i]) {
                maxValue = array[i];
            }
        }
    }
    public int getMaxValue() {
        return this.maxValue;
    }
    public void setArray(int[] array) {
        this.array = array;
    }
}
// END
