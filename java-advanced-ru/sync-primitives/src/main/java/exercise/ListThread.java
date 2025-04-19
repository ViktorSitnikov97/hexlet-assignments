package exercise;

import java.sql.Time;
import java.time.Instant;
import java.util.Random;
import java.util.Timer;

// BEGIN
public class ListThread extends Thread {

    private final SafetyList safetyList;

    private final Random random;

    public ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            System.out.println("Current: " + Thread.currentThread().getName());
            System.out.printf("Time: %s " + Thread.currentThread().getName() + "\n", Instant.now().toString());
            for (int i = 0; i < 1000; i++) {
                Thread.sleep(1);
                safetyList.add(random.nextInt(100));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Time: %s " + Thread.currentThread().getName() + "\n", Instant.now().toString());
        System.out.println("Finished: " + Thread.currentThread().getName());
    }
}

// END
