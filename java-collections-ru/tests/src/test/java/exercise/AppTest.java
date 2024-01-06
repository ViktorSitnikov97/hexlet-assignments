package exercise;

//import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN

        // test1
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> expected1 = new ArrayList<>(Arrays.asList(1, 2));
        int count1 = 2;
        List<Integer> actual1 = App.take(numbers1, count1);
        Assertions.assertEquals(expected1, actual1);

        // test2
        List<Integer> numbers2 = new ArrayList<>(Arrays.asList(5, 7, 1));
        List<Integer> expected2 = new ArrayList<>(Arrays.asList(5, 7, 1));
        int count2 = 8;
        List<Integer> actual2 = App.take(numbers2, count2);
        Assertions.assertEquals(expected2, actual2);

        // test3-4
        List<Integer> numbers3 = new ArrayList<>();
        List<Integer> expected3 = new ArrayList<>();
        int count3 = 0;
        List<Integer> actual3 = App.take(numbers3, count3);
        Assertions.assertEquals(expected3, actual3);

        // test4-3
        List<Integer> expected4 = new ArrayList<>();
        int count4 = 10;
        List<Integer> actual4 = App.take(numbers3, count4);
        Assertions.assertEquals(expected4, actual4);
        // END
    }
}
