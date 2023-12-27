package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {
    public static boolean scrabble(String str1, String str2) {

        if (str1.length() < str2.length()) {
            return false;
        }
        String str1LowerCase = str1.toLowerCase();
        String str2LowerCase = str2.toLowerCase();

        char[] array1 = str1LowerCase.toCharArray();
        char[] array2 = str2LowerCase.toCharArray();

        String[] charToString1 = new String[array1.length];
        String[] charToString2 = new String[array2.length];

        for (int i = 0; i < array1.length; i++) {
            charToString1[i] = String.valueOf(array1[i]);
        }
        for (int i = 0; i < array2.length; i++) {
            charToString2[i] = String.valueOf(array2[i]);
        }

        List<String> list1 = new ArrayList<>(Arrays.asList(charToString1));
        List<String> list2 = new ArrayList<>(Arrays.asList(charToString2));

        list1.retainAll(list2);

        list1.sort((s1, s2) -> String.CASE_INSENSITIVE_ORDER.compare(s1, s2));
        list2.sort((s1, s2) -> String.CASE_INSENSITIVE_ORDER.compare(s1, s2));

        System.out.println(list1);
        System.out.println(list2);

        var index1 = 0;
        var index2 = 0;

        List<String> result = new ArrayList<>();

        while (index1 < list1.size() && index2 < list2.size()) {
            if (list1.get(index1).equals(list2.get(index2))) {
                result.add(list2.get(index2));
                index1++;
                index2++;
            } else {
                index1++;
            }
        }

        return result.size() == list2.size();
    }
    public static void main(String[] args) {
        System.out.println(App.scrabble("rkqodlw", "woRld"));
        System.out.println(App.scrabble("begsdhhtsexoult", "Hexlet"));
        System.out.println(App.scrabble("thlxertwq", "hexlet"));
        System.out.println(App.scrabble("jvayu", "java"));

    }
}
//END
