package exercise;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.HashSet;

// BEGIN
public class App {
    public static LinkedHashMap genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        if (data1.isEmpty() && data2.isEmpty()) {
            return result;
        }

        Set<String> setFromData1 = new HashSet<>();
        Set<String> setFromData2 = new HashSet<>();

        for (Map.Entry<String, Object> pair1 : data1.entrySet()) {
            setFromData1.add(pair1.getKey());
        }
        for (Map.Entry<String, Object> pair2 : data2.entrySet()) {
            setFromData2.add(pair2.getKey());
        }

        Set<String> intersection = new HashSet<>(setFromData1);
        intersection.retainAll(setFromData2);

        for (String key : intersection) {
            if (data1.get(key).equals(data2.get(key))) {
                result.put(key, "unchanged");
            } else {
                result.put(key, "changed");
            }
        }
        System.out.println(intersection);

        Set<String> union = new HashSet<>();
        union.addAll(setFromData1);
        union.addAll(setFromData2);

        Set<String> symDiff = new HashSet<>(union);
        symDiff.removeAll(intersection);
        System.out.println(symDiff);

        for (String key : symDiff) {
            if (data1.containsKey(key)) {
                result.put(key, "deleted");
            } else {
                result.put(key, "added");
            }
        }

        return result;

    }

//    public static void main(String[] args) {
//        Map<String, Object> data1 = new HashMap<>(
//                Map.of("one", "eon", "two", "two", "four", true)
//        );
//        System.out.println(data1); //=> {two=two, four=true, one=eon}
//
//        Map<String, Object> data2 = new HashMap<>(
//                Map.of("two", "own", "zero", 4, "four", true)
//        );
//        System.out.println(data2); //=> {zero=4, two=own, four=true}
//
//        Map<String, String> result = App.genDiff(data1, data2);
//        System.out.println(result); //=> {four=unchanged, one=deleted, two=changed, zero=added}
//    }
}
//END
