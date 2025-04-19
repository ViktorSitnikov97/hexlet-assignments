package exercise;
class SafetyList {
    // BEGIN
    private int[] array;
    private int size = 0;

    public SafetyList() {
        array = new int[16];
    }

    public synchronized void add(int num) {
        if (array.length == size) {
            int[] extendedData = new int[array.length * 2];
            System.arraycopy(array, 0, extendedData, 0, array.length);
            array = extendedData;
        }
        array[size++] = num;
    }

    public int get(int index) {
        return array[index];
    }

    public int getSize() {
        return size;
    }
    // END
}
