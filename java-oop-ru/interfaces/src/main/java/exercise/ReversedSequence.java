package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final String strReversed;

    public ReversedSequence(String str) {
        this.strReversed = getStrReversed(str);
    }

    private String getStrReversed(String str) {
        char[] array = str.toCharArray();
        char[] arrayNew = new char[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            arrayNew[arrayNew.length - 1 - i] = array[i];
        }
        return new String(arrayNew);
    }

    @Override
    public char charAt(int index) {
        char[] array = this.strReversed.toCharArray();
        return array[index];
    }

    @Override
    public int length() {
        return this.strReversed.length();
    }

    @Override
    public String toString() {
        return this.strReversed;
    }
    @Override
    public String subSequence(int start, int end) {
        return this.strReversed.substring(start, end);
    }

}
// END
